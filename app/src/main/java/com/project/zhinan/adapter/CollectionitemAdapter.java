package com.project.zhinan.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.zhinan.R;

public class CollectionitemAdapter extends BaseAdapter {

    private List<String> objects = new ArrayList<String>();

    private Context context;
    private LayoutInflater layoutInflater;

    public CollectionitemAdapter(Context context, ArrayList<String> myObjects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects=myObjects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public String getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.collectionitem, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((String)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(String object, ViewHolder holder) {
        //TODO implement
    }

    protected class ViewHolder {
        private ImageView ivPic;
    private LinearLayout relativeLayout;
    private TextView tv2;
    private TextView tv1;
    private TextView tv3;

        public ViewHolder(View view) {
            ivPic = (ImageView) view.findViewById(R.id.iv_pic);
            relativeLayout = (LinearLayout) view.findViewById(R.id.relativeLayout);
            tv2 = (TextView) view.findViewById(R.id.tv2);
            tv1 = (TextView) view.findViewById(R.id.tv1);
            tv3 = (TextView) view.findViewById(R.id.tv3);
        }
    }
}
