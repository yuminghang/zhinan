package com.project.zhinan.fragment.HomeTabFragmentSons;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.project.zhinan.R;
import com.project.zhinan.activity.MapActivity;
import com.project.zhinan.activity.ShangQuanActivity;
import com.project.zhinan.adapter.ShangquanItemAdapter;
import com.project.zhinan.bean.ShangquanBean;

import java.util.ArrayList;


public class Shangquan extends Fragment {
    private ListView mShangquanListView;
    private ArrayList<ShangquanBean> shangquanBeens;
    private ShangquanItemAdapter shangquanItemAdapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shangquan1, null);
        Button btn = (Button) view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MapActivity.class));

            }
        });
        return view;
    }

}
