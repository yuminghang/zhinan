package com.project.zhinan.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.zhinan.R;
import com.project.zhinan.bean.hongbaobean;
import com.project.zhinan.fragment.HomeTabFragmentSons.Hongbao;

import java.util.List;

/**
 * Created by ymh on 2016/5/17.
 */
public class HongbaoListAdapter extends BaseAdapter {
    Activity activity;
    hongbaobean list;

    public HongbaoListAdapter(Activity activity, hongbaobean list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.getResult().getList().size();
    }

    /**
     * "existingTimes": 50,
     * "status": 1,
     * "goods": {
     * "desc": "唯一的不同，就是处处不同",
     * "priceBase": 0,
     * "gpic": [
     * "http://onegoods.nosdn.127.net/goods/898/2cd29cf69a2e9fd530f099aa8c379a16.png",
     * "http://onegoods.nosdn.127.net/goods/898/c1c9382c9087cdc93d8e1bf4c6acf929.png",
     * "http://onegoods.nosdn.127.net/goods/898/d3dc4b84825a35c50e2b5504d2b636cc.png"
     * ],
     * "totalTimes": 7280,
     * "tag": "",
     * "gname": "Apple iPhone6s Plus 64G 颜色随机",
     * "priceType": 1,
     * "addAttributes": "00000000000",
     * "property": "0",
     * "priceUnit": 1,
     * "regularBuyMax": 1,
     * "showVideos": [],
     * "price": 7280,
     * "buyUnit": 10,
     * "gid": 898,
     * "buyable": true,
     * "flagList": [
     * {
     * "id": 0,
     * "startDate": 0,
     * "title": "十元专区",
     * "titleBg": "#8c68d6",
     * "endDate": 0,
     * "pic": "http://mimg.127.net/p/yymobile/lib/img/common/icon/icon_ten.png",
     * "gids": null,
     * "seoKey": null
     * }
     * ],
     * "brand": 1,
     * "wishSetable": 0,
     * "typeId": 1,
     * "goodsType": 2
     * },
     * "limitTime": 0,
     * "isLimit": 0,
     * "period": 305170896,
     * "totalPeriod": 59013,
     * "isJoined": 0,
     * "lastPeriod": 1
     *
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return list.getResult().getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(activity, R.layout.hongbaofragment_item, null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView totalnumber = (TextView) view.findViewById(R.id.totalnumber);
        ImageView add = (ImageView) view.findViewById(R.id.add);
        Glide.with(activity).load(list.getResult().getList().get(position).getGoods().getGpic().get(2)).into(iv);
        title.setText(list.getResult().getList().get(position).getGoods().getGname());
        totalnumber.setText("还需众筹数量：" + (list.getResult().getList().get(position).getGoods().getPrice() - 3510));
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "抢购成功！请等待开奖", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
