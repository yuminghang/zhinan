package com.project.zhinan.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.zhinan.R;
import com.project.zhinan.utils.StatusBarUtil;


/**
 * Created by ymh on 2016/3/11.
 */
public class FaxianFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.faxianfragment_layout, container, false);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
    }

}
