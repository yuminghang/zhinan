package com.project.zhinan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project.zhinan.R;
import com.project.zhinan.bean.newsbean;

import java.util.List;

/**
 * Created by ymh on 2016/4/26.
 */
public class newsDetailAdapter extends BaseAdapter {
    List<newsbean> list;
    Context context;
    TextView tv;
    private View view;

    public newsDetailAdapter(Context context, List<newsbean> list) {
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
        view = View.inflate(context, R.layout.columnitem_layout, null);
        tv = (TextView) view.findViewById(R.id.tv);
        tv.setText(" [" + list.get(position).getCategory() + "]   " + list.get(position).getTitle());
        return view;
    }
}
