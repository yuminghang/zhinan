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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.zhinan.R;
import com.project.zhinan.activity.HongbaoWebViewActivity;
import com.project.zhinan.activity.WebviewActivity;
import com.project.zhinan.adapter.HongbaoListAdapter;
import com.project.zhinan.base.fragment.BaseFragment;
import com.project.zhinan.bean.hongbaobean;
import com.project.zhinan.utils.Cache;
import com.project.zhinan.utils.ConstantValue;

import java.util.List;


public class Hongbao extends Fragment {

    private View view;
    private ListView listView;
    private hongbaobean list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hongbao_fragmentlayout, null);
        initData();
        initView();
        return view;
    }

    private void initData() {
        Gson gson = new Gson();
        list = (gson.fromJson(Cache.Hongbao_Cache, new TypeToken<hongbaobean>() {
        }.getType()));
        }

    private void initView() {
        listView = (ListView) view.findViewById(R.id.listView);
        HongbaoListAdapter adapter = new HongbaoListAdapter(getActivity(), list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), HongbaoWebViewActivity.class));
            }
        });
    }
}
