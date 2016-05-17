package com.project.zhinan.fragment.HomeTabFragmentSons;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.project.zhinan.R;
import com.project.zhinan.activity.ShangQuanActivity;
import com.project.zhinan.adapter.ShangquanItemAdapter;
import com.project.zhinan.bean.ShangquanBean;

import java.util.ArrayList;


public class Shangquan extends Fragment {
    private ListView mShangquanListView;
    private ArrayList<ShangquanBean> shangquanBeens;
    private ShangquanItemAdapter shangquanItemAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shangquan, null);
        mShangquanListView = (ListView) view.findViewById(R.id.lv_shangquan);
        mShangquanListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ShangQuanActivity.class);
                intent.putExtra("imgUrl",shangquanBeens.get(position).getImgUrl());
                intent.putExtra("name",shangquanBeens.get(position).getStoreName());
                startActivity(intent);

            }
        });
        shangquanBeens = new ArrayList<>();
        shangquanItemAdapter = new ShangquanItemAdapter(getContext(), shangquanBeens);
        mShangquanListView.setAdapter(shangquanItemAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        shangquanBeens.clear();
        shangquanBeens.add(new ShangquanBean("http://gw.alicdn.com/tfscom/TB1L0bXLXXXXXbNXFXXq6xXFXXX_760x760q90.jpg","4.51km","银泰百货西安钟楼店"));
        shangquanBeens.add(new ShangquanBean("http://gw.alicdn.com/tfscom/TB1slS0LXXXXXa2XVXXq6xXFXXX_760x760q90.jpg","4.80km","五环钟楼旗舰店"));
        shangquanBeens.add(new ShangquanBean("http://gw.alicdn.com/tfscom/TB1I0LrLXXXXXXYXXXXq6xXFXXX_760x760q90.jpg","4.51km","五环NOVO南大街店"));
        shangquanBeens.add(new ShangquanBean("http://gw.alicdn.com/tfscom/TB1fhLjLXXXXXamXpXXq6xXFXXX_760x760q90.jpg","5.22km","五环骡马市店"));
        shangquanBeens.add(new ShangquanBean("http://gw.alicdn.com/tfscom/TB19HLjLXXXXXX8XpXXq6xXFXXX_760x760q90.jpg","7.10km","曲江银泰城"));
        shangquanBeens.add(new ShangquanBean("http://gw.alicdn.com/tfscom/TB1GISVLXXXXXbEaXXXq6xXFXXX_760x760q90.jpg","8.40km","五环Outlets交大工厂店"));
        shangquanBeens.add(new ShangquanBean("http://gw.alicdn.com/tfscom/TB1eASZLXXXXXXlaXXXq6xXFXXX_760x760q90.jpg","11.33km","五环Outlets北郊工厂店"));
        shangquanItemAdapter.notifyDataSetChanged();
    }
}
