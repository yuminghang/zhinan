package com.project.zhinan.adapter;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.project.zhinan.bean.bean_version2;

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
                        Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "取消收藏", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    holder.cb.setChecked(false);
                    Toast.makeText(context, "请先登录！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.newstitleTextView.setText(object.getKey()); //广告关键词
        holder.tv1.setText((Math.random() * 10 + "").substring(0, 3) + "折起");//折扣
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

    protected class ViewHolder {
        private ImageView ivPic;
        private TextView newstitleTextView;
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private CheckBox cb;
        private ImageView havesee;

        public ViewHolder(View view) {
            ivPic = (ImageView) view.findViewById(R.id.iv_pic);
            havesee = (ImageView) view.findViewById(R.id.havesee);
            newstitleTextView = (TextView) view.findViewById(R.id.newstitle_TextView);
            cb = (CheckBox) view.findViewById(R.id.scb);
            tv1 = (TextView) view.findViewById(R.id.tv1);
            tv2 = (TextView) view.findViewById(R.id.tv2);
            tv3 = (TextView) view.findViewById(R.id.tv3);
        }
    }
}
