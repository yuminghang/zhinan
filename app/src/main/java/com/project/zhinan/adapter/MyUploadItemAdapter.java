package com.project.zhinan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.zhinan.R;
import com.project.zhinan.bean.IPublish;
import com.project.zhinan.bean.MyUploadBean;

import java.util.ArrayList;
import java.util.List;

public class MyUploadItemAdapter extends BaseAdapter {

    private List<MyUploadBean.DataBean> objects = new ArrayList<MyUploadBean.DataBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public MyUploadItemAdapter(Context context, ArrayList<MyUploadBean.DataBean> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public MyUploadBean.DataBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.my_upload_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((MyUploadBean.DataBean) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(MyUploadBean.DataBean object, ViewHolder holder) {
        //TODO implement
        String imgurl = object.getImgurls().get(0);
        Glide.with(context).load(imgurl).placeholder(R.mipmap.pic1).into(holder.ivPic);
        try {
            holder.coins.setText("剩余金币:" + object.getIcons());
            holder.title.setText(object.getTitle());
        } catch (Exception e) {

        }

    }

    protected class ViewHolder {
        private ImageView ivPic;

        private TextView title;
        private TextView coins;

        public ViewHolder(View view) {
            ivPic = (ImageView) view.findViewById(R.id.iv_pic);
            title = (TextView) view.findViewById(R.id.title);
            coins = (TextView) view.findViewById(R.id.coins);
        }
    }
}
