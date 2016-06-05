package com.project.zhinan.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.zhinan.R;
import com.project.zhinan.activity.HongbaoWebViewActivity;
import com.project.zhinan.bean.hongbaocache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by ymh on 2016/5/17.
 */
public class HongbaoListAdapter extends BaseAdapter {
    Activity activity;
    hongbaocache list;

    public HongbaoListAdapter(Activity activity, hongbaocache list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.getList().size();
    }

    @Override
    public Object getItem(int position) {
        return list.getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder myHolder;
        if (convertView == null) {
            convertView = View.inflate(activity, R.layout.hongbaofragment_item, null);
            myHolder = new MyHolder();
            myHolder.pic = (ImageView) convertView.findViewById(R.id.pic);
            myHolder.desc = (TextView) convertView.findViewById(R.id.desc);
            myHolder.price = (TextView) convertView.findViewById(R.id.price);
            convertView.setTag(myHolder);
        } else {
            myHolder = (MyHolder) convertView.getTag();
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, HongbaoWebViewActivity.class));

            }
        });
//        Glide.with(activity).load(list.getList().get(position).getHead_pic()).placeholder(R.drawable.useme).into(myHolder.pic);
        switch (position){
            case 0:myHolder.pic.setImageResource(R.drawable.y1);
                break;
            case 1:myHolder.pic.setImageResource(R.drawable.y2);
                break;
            case 2:myHolder.pic.setImageResource(R.drawable.y3);
                break;
            case 3:myHolder.pic.setImageResource(R.drawable.y4);
                break;
            case 4:myHolder.pic.setImageResource(R.drawable.y5);
                break;
        }
        myHolder.desc.setText(list.getList().get(position).getTitle());
        myHolder.price.setText("￥" + list.getList().get(position).getPrice());
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(activity, "抢购成功！请等待开奖", Toast.LENGTH_LONG).show();
//            }
//        });
        return convertView;
    }

    class MyHolder {
        ImageView pic;
        TextView desc, price;
    }
}
