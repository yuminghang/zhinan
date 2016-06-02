package com.project.zhinan.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.zhinan.R;
import com.project.zhinan.bean.QiangGouBean;

public class QianggouItemAdapter extends BaseAdapter {

    private ArrayList<QiangGouBean.SeckillInfoBean.ItemListBean> objects = new ArrayList<QiangGouBean.SeckillInfoBean.ItemListBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public QianggouItemAdapter(Context context, ArrayList<QiangGouBean.SeckillInfoBean.ItemListBean> shangquanBeens) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects=shangquanBeens;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public QiangGouBean.SeckillInfoBean.ItemListBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.qianggou_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((QiangGouBean.SeckillInfoBean.ItemListBean) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(QiangGouBean.SeckillInfoBean.ItemListBean object, ViewHolder holder) {
        //TODO implement
        holder.tvName.setText(object.getWname());
        Glide.with(context).load(object.getImageurl()).placeholder(R.mipmap.zhinan).into(holder.ivImg);
        holder.tvNowPrice.setText(object.getMiaoShaPrice());
        holder.tvOldPrice.setText(object.getJdPrice());
        holder.tvOldPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        holder.tvRemain.setText("已抢购"+(int)(Math.random()*100+1)+"%");
//        holder.tvQianggou
    }

    protected class ViewHolder {
        private ImageView ivImg;
        private TextView tvName;
        private TextView tvNowPrice;
        private TextView tvOldPrice;
        private TextView tvRemain;
        private TextView tvQianggou;

        public ViewHolder(View view) {
            ivImg = (ImageView) view.findViewById(R.id.iv_img);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvNowPrice = (TextView) view.findViewById(R.id.tv_now_price);
            tvOldPrice = (TextView) view.findViewById(R.id.tv_old_price);
            tvRemain = (TextView) view.findViewById(R.id.tv_remain);
            tvQianggou = (TextView) view.findViewById(R.id.tv_qianggou);
        }
    }
}
