package com.project.zhinan.fragment.HomeTabFragmentSons;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.project.zhinan.R;
import com.project.zhinan.adapter.QianggouItemAdapter;
import com.project.zhinan.bean.QiangGouBean;
import com.project.zhinan.net.HttpUtils;

import java.util.ArrayList;

public class Qianggou extends Fragment {
    private ListView mShangquanListView;
    private ArrayList<QiangGouBean.SeckillInfoBean.ItemListBean> qianggous;
    private QianggouItemAdapter qianggouItemAdapter;
    //    public Qianggou() {
//        this.url = ConstantValue.BaseUr12 +
//                "com/try?file=a";
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shangquan, null);
        mShangquanListView = (ListView) view.findViewById(R.id.lv_shangquan);
        mShangquanListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });
        qianggous = new ArrayList<>();
        qianggouItemAdapter = new QianggouItemAdapter(getContext(), qianggous);
        mShangquanListView.setAdapter(qianggouItemAdapter);
        getWebData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void getWebData() {
        HttpUtils.doGetAsyn("http://120.27.41.245:2888/jd.json", new HttpUtils.CallBack() {
            @Override
            public void onRequestComplete(String result) {
                Gson gson = new Gson();
                QiangGouBean qiangGouBean = gson.fromJson(result, QiangGouBean.class);
                qianggous.addAll(qiangGouBean.getSeckillInfo().getItemList());
                mShangquanListView.post(new Runnable() {
                    @Override
                    public void run() {
                     qianggouItemAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }


}
