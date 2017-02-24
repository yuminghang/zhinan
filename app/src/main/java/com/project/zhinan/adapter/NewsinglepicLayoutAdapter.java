package com.project.zhinan.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.zhinan.MyApplication;
import com.project.zhinan.R;
import com.project.zhinan.activity.MyCollection;
import com.project.zhinan.bean.bean_version2;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class NewsinglepicLayoutAdapter extends BaseAdapter {

    private List<bean_version2.DataEntity> objects;

    private Context context;
    private LayoutInflater layoutInflater;
    private int collect;
    private SharedPreferences.Editor edit;
    private SharedPreferences sharedPreferences;
//    private BaseFragment baseFragment;

    public NewsinglepicLayoutAdapter(Context context, ArrayList<bean_version2.DataEntity> objects) {
        this.objects = objects;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
//        this.baseFragment = baseFragment;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public bean_version2.DataEntity getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        sharedPreferences = context.getSharedPreferences("collect", Context.MODE_PRIVATE);
//        edit = sharedPreferences.edit();
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.newsinglepic_layout, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(final bean_version2.DataEntity object, final ViewHolder holder, final int position) {
        holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getInstance().isLogin()) {
                    if (holder.cb.isChecked()) {
                        ColletionTask colletionTask = new ColletionTask();
                        colletionTask.execute(object);
//                        Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
                    } else {
                        CancelTask cancelTask = new CancelTask();
                        cancelTask.execute(object);
                    }
                } else {
                    holder.cb.setChecked(false);
                    Toast.makeText(context, "请先登录！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.newstitleTextView.setText(object.getKey()); //广告关键词
        holder.tv2.setText(object.getTitle());//广告标题
//        if (object.getImgurls() instanceof String) {
//            Glide.with(context).load(object.getImgurls()).placeholder(R.mipmap.pic1).into(holder.ivPic);//广告图片
//        } else {
        try {
            Glide.with(context).load(object.getImgurls()[0]).placeholder(R.mipmap.bg).into(holder.ivPic);//广告图片
        } catch (Exception e) {
            Glide.with(context).load(R.mipmap.bg).into(holder.ivPic);
        }
//        }

    }

    private class ColletionTask extends AsyncTask<bean_version2.DataEntity, String, String> {

        @Override
        protected String doInBackground(bean_version2.DataEntity... params) {
            OkHttpClient client = new OkHttpClient();
            SharedPreferences cookie = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
            String my_cookie = cookie.getString("my_cookie", "");
            bean_version2.DataEntity param = params[0];
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "adid=" + param.getUuid() +
                    "&order_id=" + param.getOrderno() +
                    "&title=" + param.getTitle() +
                    "&imgurl=" + param.getImgurls()[0]);
            Request request = new Request.Builder()
                    .url("http://120.27.41.245:2888/collection_c")
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
            if (s == null) {
                Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
            } else {
                if (s.contains("success")) {
                    Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "收藏失败", Toast.LENGTH_SHORT).show();
                }
            }
            super.onPostExecute(s);
        }
    }

    private class CancelTask extends AsyncTask<bean_version2.DataEntity, String, String> {

        private bean_version2.DataEntity object;

        @Override
        protected String doInBackground(bean_version2.DataEntity... params) {
            OkHttpClient client = new OkHttpClient();
            object = params[0];
            SharedPreferences cookie = context.getSharedPreferences("cookie", Context.MODE_PRIVATE);
            String my_cookie = cookie.getString("my_cookie", "");
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "adid=" +
                    params[0].getUuid());
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
            } else {
                Toast.makeText(context, "取消收藏失败", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(s);
        }
    }


    protected class ViewHolder {
        private ImageView ivPic;
        private TextView newstitleTextView;
        private TextView tv2;
        private TextView tv3;
        private CheckBox cb;
        private ImageView havesee;

        public ViewHolder(View view) {
            ivPic = (ImageView) view.findViewById(R.id.iv_pic);
            havesee = (ImageView) view.findViewById(R.id.havesee);
            newstitleTextView = (TextView) view.findViewById(R.id.newstitle_TextView);
            cb = (CheckBox) view.findViewById(R.id.scb);
            tv2 = (TextView) view.findViewById(R.id.tv2);
            tv3 = (TextView) view.findViewById(R.id.tv3);
        }
    }
}
