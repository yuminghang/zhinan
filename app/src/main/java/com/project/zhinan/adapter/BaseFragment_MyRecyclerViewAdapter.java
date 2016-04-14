package com.project.zhinan.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.project.zhinan.R;
import com.project.zhinan.activity.WebviewActivity;
import com.project.zhinan.api.Urls;
import com.project.zhinan.bean.jsonbean;
import com.project.zhinan.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ymh on 2016/4/13.
 */
public class BaseFragment_MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private jsonbean datas;

    public BaseFragment_MyRecyclerViewAdapter(Context context, jsonbean datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newsinglepic_layout, parent, false);
        return new SinglePicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SinglePicViewHolder singlePicViewHolder = (SinglePicViewHolder) holder;
        jsonbean.ResultEntity.ItemsEntity.BrandsEntity s = datas.getResult().getItems().getBrands().get(position);
        if (!StringUtil.isNullOrEmpty(s.getPms_activetips())) {
            singlePicViewHolder.newstitle_TextView.setText(s.getPms_activetips());
        } else {
            singlePicViewHolder.newstitle_TextView.setVisibility(View.GONE);
        }
        int index1 = s.getAgio().indexOf(">");
        int index2 = s.getAgio().indexOf("</span>");
        if (index2 - index1 > 1) {
            String agio = s.getAgio().substring(index1 + 1, index2) + "折起";
            singlePicViewHolder.tv1.setText(agio);
        } else {
            singlePicViewHolder.tv1.setVisibility(View.GONE);
        }
        singlePicViewHolder.tv2.setText(s.getBrand_name());
        Glide.with(context).load(s.getMobile_image_one()).into(singlePicViewHolder.iv_Pic);
    }

    @Override
    public int getItemCount() {
        return datas.getResult().getItems().getBrands().size();
    }


    class SinglePicViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_Pic;
        public TextView newstitle_TextView, tv1, tv2, tv3;

        public SinglePicViewHolder(View view) {
            super(view);
            iv_Pic = (ImageView) view.findViewById(R.id.iv_pic);
            newstitle_TextView = (TextView) view.findViewById(R.id.newstitle_TextView);
            tv1 = (TextView) view.findViewById(R.id.tv1);
            tv2 = (TextView) view.findViewById(R.id.tv2);
            tv3 = (TextView) view.findViewById(R.id.tv3);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", Urls.DetailUrl);
                    Intent intent = new Intent(context, WebviewActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}
