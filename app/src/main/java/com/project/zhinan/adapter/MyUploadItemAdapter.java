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

import java.util.ArrayList;
import java.util.List;

public class MyUploadItemAdapter extends BaseAdapter {

    private List<IPublish.SuccessEntity> objects = new ArrayList<IPublish.SuccessEntity>();

    private Context context;
    private LayoutInflater layoutInflater;

    public MyUploadItemAdapter(Context context, ArrayList<IPublish.SuccessEntity> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects=objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public IPublish.SuccessEntity getItem(int position) {
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
        initializeViews((IPublish.SuccessEntity)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(IPublish.SuccessEntity object, ViewHolder holder) {
        //TODO implement
        String[] strings = object.getAddesc().split("----");
        Glide.with(context).load(object.getImgurl()).placeholder(R.mipmap.pic1).into(holder.ivPic);
        try {
            holder.tv1.setText(strings[1]);
            holder.tv2.setText(strings[0]);
            holder.tv3.setText(strings[2]);
        }catch (Exception e){

        }

    }

    protected class ViewHolder {
        private ImageView ivPic;
    private LinearLayout relativeLayout;
    private TextView tv2;
    private TextView tv1;
    private TextView tv3;

        public ViewHolder(View view) {
            ivPic = (ImageView) view.findViewById(R.id.iv_pic);
            relativeLayout = (LinearLayout) view.findViewById(R.id.relativeLayout);
            tv2 = (TextView) view.findViewById(R.id.tv2);
            tv1 = (TextView) view.findViewById(R.id.tv1);
            tv3 = (TextView) view.findViewById(R.id.tv3);
        }
    }
}
