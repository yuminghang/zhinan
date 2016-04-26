package com.project.zhinan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.zhinan.R;

import java.util.List;
import java.util.Random;

/**
 * Created by ymh on 2016/4/23.
 */
public class channelAdapter extends BaseAdapter {
    List<String> list;
    Context context;
    TextView tv;
    private View view;
    private ImageView iv_arrow;
    public static int pos = 0;

    public channelAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = View.inflate(context, R.layout.channelitem_layout, null);
        tv = (TextView) view.findViewById(R.id.tv);
        iv_arrow = (ImageView) view.findViewById(R.id.iv_arrow);
        tv.setText(list.get(position));
        if (position == pos) {
            iv_arrow.setVisibility(View.VISIBLE);
        } else {
            iv_arrow.setVisibility(View.INVISIBLE);
        }
        return view;
    }
}

