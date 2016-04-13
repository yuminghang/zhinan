package com.project.zhinan.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.zhinan.R;


/**
 * Created by ymh on 2016/3/11.
 */
public class FabuFragment extends Fragment {

    private View view;
//    private Context context;
//
//    public FabuFragment(Context context) {
//        this.context = context;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fabufragment_layout, container, false);
        return view;
    }


}
