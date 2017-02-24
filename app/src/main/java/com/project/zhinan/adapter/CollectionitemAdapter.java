package com.project.zhinan.adapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.zhinan.R;
import com.project.zhinan.bean.CollectionBeans;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class CollectionitemAdapter extends BaseAdapter {

    private List<CollectionBeans.DataBean> objects;

    private Context context;
    private LayoutInflater layoutInflater;
    private CancelTask cancelTask;

    public CollectionitemAdapter(Context context, ArrayList<CollectionBeans.DataBean> myObjects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = myObjects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public CollectionBeans.DataBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.collectionitem, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(final CollectionBeans.DataBean object, ViewHolder holder) {
        //TODO implement
        Glide.with(context).load(object.getImgurl()).into(holder.ivPic);
        holder.title.setText(object.getTitle());
        holder.cancel_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelTask = new CancelTask();
                cancelTask.execute(object);
            }
        });
    }

    private class CancelTask extends AsyncTask<CollectionBeans.DataBean, String, String> {

        private CollectionBeans.DataBean object;

        @Override
        protected String doInBackground(CollectionBeans.DataBean... params) {
            OkHttpClient client = new OkHttpClient();
            object = params[0];
            SharedPreferences cookie = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
            String my_cookie = cookie.getString("my_cookie", "");
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "adid=" +
                    params[0].getAdid());
            Request request = new Request.Builder()
                    .url("http://120.27.41.245:2888/collection_d")
                    .post(body)
                    .addHeader("content-type", "application/x-www-form-urlencoded")
                    .addHeader("cache-control", "no-cache")
                    .addHeader("Cookie", my_cookie)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if (s.contains("success")) {
                Toast.makeText(context, "取消收藏成功", Toast.LENGTH_SHORT).show();
                objects.remove(object);
                notifyDataSetChanged();
            } else {
                Toast.makeText(context, "取消收藏失败", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(s);
        }
    }

    protected class ViewHolder {
        private ImageView ivPic;
        private TextView cancel_collection;
        private TextView title;

        public ViewHolder(View view) {
            ivPic = (ImageView) view.findViewById(R.id.iv_pic);
            cancel_collection = (TextView) view.findViewById(R.id.cancel_collection);
            title = (TextView) view.findViewById(R.id.title);
        }
    }
}
