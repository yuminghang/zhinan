package com.project.zhinan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private LinearLayout LinearLayout1, LinearLayout2, LinearLayout3;
    //faxianfragment_layout

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.faxian_layout, container, false);
        LinearLayout1 = (LinearLayout) view.findViewById(R.id.LinearLayout1);
        LinearLayout2 = (LinearLayout) view.findViewById(R.id.LinearLayout2);
        LinearLayout3 = (LinearLayout) view.findViewById(R.id.LinearLayout3);
        LinearLayout1.setOnClickListener(this);
        LinearLayout2.setOnClickListener(this);
        LinearLayout3.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.LinearLayout1:
                startActivity(new Intent(getActivity(), TvActivity.class));
                break;
            case R.id.LinearLayout2:
                startActivity(new Intent(getActivity(), NewsActivity.class));
                break;
            case R.id.LinearLayout3:
                startActivity(new Intent(getActivity(), VideoActivity.class));
                break;
        }
    }

}
