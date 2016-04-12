package com.project.zhinan.base.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.zhinan.R;

/**
 * Created by ymh on 2016/4/11.
 */
public class BaseFragment extends Fragment {
    private String url;

    public BaseFragment(String url) {
        this.url = url;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_huodong, container, false);
    }
}
