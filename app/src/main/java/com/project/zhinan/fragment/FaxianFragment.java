package com.project.zhinan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.zhinan.R;
import com.project.zhinan.activity.NewsActivity;
import com.project.zhinan.activity.TvActivity;
import com.project.zhinan.activity.VideoActivity;


/**
 * Created by ymh on 2016/3/11.
 */
public class FaxianFragment extends Fragment implements View.OnClickListener {


    private View view;
    //    private ListView listView1;
//    private ListView listView2;
//    private channelAdapter channeladapter;

    private ImageView iv1, iv2, iv3;
    private TextView tv1, tv2, tv3;


    //faxianfragment_layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.faxian_layout, container, false);
        iv1 = (ImageView) view.findViewById(R.id.iv1);
        iv2 = (ImageView) view.findViewById(R.id.iv2);
        iv3 = (ImageView) view.findViewById(R.id.iv3);
        tv1 = (TextView) view.findViewById(R.id.tv1);
        tv2 = (TextView) view.findViewById(R.id.tv2);
        tv3 = (TextView) view.findViewById(R.id.tv3);
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
//        listView1 = (ListView) view.findViewById(R.id.listview_1);
//        listView2 = (ListView) view.findViewById(R.id.listview_2);
//        initData();
//        addListener();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv1:
            case R.id.tv1:
                startActivity(new Intent(getActivity(), TvActivity.class));
                break;
            case R.id.iv2:
            case R.id.tv2:
                startActivity(new Intent(getActivity(), VideoActivity.class));
                break;
            case R.id.iv3:
            case R.id.tv3:
                startActivity(new Intent(getActivity(), NewsActivity.class));
                break;
        }
    }


//

//
//    private void addListener() {
//        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                list2.clear();
//                if (position % 2 == 0) {
//                    list2.addAll(datasWeishi);
//                } else {
//                    list2.addAll(datasNonglin);
//                }
//                columnadapter.notifyDataSetChanged();
//            }
//        });
//        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                new MyFaxianPopupWindow(getActivity(), view);
//            }
//        });
//    }


}
