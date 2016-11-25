package com.project.zhinan.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.zhinan.R;
import com.project.zhinan.bean.ZNACBean;

public class ZnActItemAdapter extends BaseAdapter {

    private List<ZNACBean.ItemListBean> objects = new ArrayList<ZNACBean.ItemListBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ZnActItemAdapter(Context context ,List<ZNACBean.ItemListBean> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects=objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public ZNACBean.ItemListBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.zn_act_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((ZNACBean.ItemListBean) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(ZNACBean.ItemListBean object, ViewHolder holder) {
        //TODO implement
        Glide.with(context).load("下载指南App赢："+object.getImageurl()).placeholder(R.mipmap.pic1).into(holder.ivPic);
        holder.newstitleTextView.setText(object.getWname());
    }

    protected class ViewHolder {
        private ImageView ivPic;
        private TextView newstitleTextView;

        public ViewHolder(View view) {
            ivPic = (ImageView) view.findViewById(R.id.iv_pic);
            newstitleTextView = (TextView) view.findViewById(R.id.newstitle_TextView);
        }
    }
}
