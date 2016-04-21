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
import com.project.zhinan.activity.MainActivity;
import com.project.zhinan.base.fragment.BaseFragment;
import com.project.zhinan.bean.jsonbean;
import com.project.zhinan.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;


public class NewsinglepicLayoutAdapter extends BaseAdapter {

    private List<jsonbean.ResultEntity.ItemsEntity.BrandsEntity> objects;

    private Context context;
    private LayoutInflater layoutInflater;
    private int collect;
    private SharedPreferences.Editor edit;
    private SharedPreferences sharedPreferences;
    private BaseFragment baseFragment;

    public NewsinglepicLayoutAdapter(Context context, ArrayList<jsonbean.ResultEntity.ItemsEntity.BrandsEntity> objects, BaseFragment baseFragment) {
        this.objects = objects;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.baseFragment = baseFragment;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public jsonbean.ResultEntity.ItemsEntity.BrandsEntity getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        sharedPreferences = context.getSharedPreferences("collect", Context.MODE_PRIVATE);
        edit = sharedPreferences.edit();
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.newsinglepic_layout, null);
            convertView.setTag(new ViewHolder(convertView));
        }

        initializeViews((jsonbean.ResultEntity.ItemsEntity.BrandsEntity) getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(final jsonbean.ResultEntity.ItemsEntity.BrandsEntity object, final ViewHolder holder, final int position) {
        //TODO implement
        if (sharedPreferences.getInt("" + object.getBrand_id(), 0) == 1) {
            holder.cb.setChecked(true);
        } else {
            holder.cb.setChecked(false);
        }
        holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getInstance().isLogin()) {
                    if (holder.cb.isChecked()) {
                        Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
                        edit.putInt(object.getBrand_id() + "", 1);
                        edit.commit();
                    } else {
                        Toast.makeText(context, "取消收藏", Toast.LENGTH_SHORT).show();
                        edit.putInt(object.getBrand_id() + "", 0);
                        edit.commit();
                    }
                    MyApplication.count++;
                    context.getSharedPreferences("collect_upload_state", Context.MODE_PRIVATE).edit().putInt("isUpload", 1).commit();
                } else {
                    holder.cb.setChecked(false);
                    Toast.makeText(context, "请先登录！", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        holder.cb.setChecked(true);
        if (!StringUtil.isNullOrEmpty(object.getPms_activetips())) {
            holder.newstitleTextView.setVisibility(View.VISIBLE);
            holder.newstitleTextView.setText(object.getPms_activetips());
        } else {
            holder.newstitleTextView.setVisibility(View.INVISIBLE);
        }
        int index1 = object.getAgio().indexOf(">");
        int index2 = object.getAgio().indexOf("</span>");
        if (index2 - index1 > 1) {
            String agio = object.getAgio().substring(index1 + 1, index2) + "折起";
            holder.tv1.setText(agio);
        } else {
            holder.tv1.setVisibility(View.GONE);
        }
        holder.tv2.setText(object.getBrand_name());
        Glide.with(context).load(object.getMobile_image_one()).placeholder(R.mipmap.pic1).into(holder.ivPic);
    }

    protected class ViewHolder {
        private ImageView ivPic;
        private TextView newstitleTextView;
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private CheckBox cb;

        public ViewHolder(View view) {
            ivPic = (ImageView) view.findViewById(R.id.iv_pic);
            newstitleTextView = (TextView) view.findViewById(R.id.newstitle_TextView);
            cb = (CheckBox) view.findViewById(R.id.scb);
            tv1 = (TextView) view.findViewById(R.id.tv1);
            tv2 = (TextView) view.findViewById(R.id.tv2);
            tv3 = (TextView) view.findViewById(R.id.tv3);
        }
    }
}
