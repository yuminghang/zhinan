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
import com.project.zhinan.bean.ShangquanBean;
import com.project.zhinan.fragment.HomeTabFragmentSons.Shangquan;

public class ShangquanItemAdapter extends BaseAdapter {

    private List<ShangquanBean> objects = new ArrayList<ShangquanBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ShangquanItemAdapter(Context context,List<ShangquanBean> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects=objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public ShangquanBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.shangquan_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((ShangquanBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(ShangquanBean object, ViewHolder holder) {
        //TODO implement
        Glide.with(context).load(object.getImgUrl()).into(holder.ivBack);
        holder.tvName.setText(object.getStoreName());
        holder.tvDistance.setText(object.getLocation());
    }

    protected class ViewHolder {
        private ImageView ivBack;
    private TextView tvName;
    private TextView tvDistance;

        public ViewHolder(View view) {
            ivBack = (ImageView) view.findViewById(R.id.iv_back);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvDistance = (TextView) view.findViewById(R.id.tv_distance);
        }
    }
}
