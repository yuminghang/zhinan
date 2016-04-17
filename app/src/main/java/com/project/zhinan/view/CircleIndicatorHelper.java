package com.project.zhinan.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by deng on 16-4-17.
 */
public class CircleIndicatorHelper {
    private CirclePageIndicator mCircleIndicator;
    private Context mContext;

    public CircleIndicatorHelper(Context context) {
        mCircleIndicator = new CirclePageIndicator(context);
        mContext = context;
    }

    public void setViewpager(ViewPager viewPager) {
        ViewGroup parentView = (ViewGroup) viewPager.getParent();
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, dpToPx(8));
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mCircleIndicator.setLayoutParams(lp);

        parentView.addView(mCircleIndicator);
        mCircleIndicator.setViewPager(viewPager);
    }

    public void setFillColor(String colorString) {
        int color = Color.parseColor(colorString);
        mCircleIndicator.setFillColor(color);
    }

    public void setDefaultColor(String colorString) {
        int color = Color.parseColor(colorString);
        mCircleIndicator.setFillColor(color);
    }

    public void setRadius(int radius) {
        mCircleIndicator.setRadius(dpToPx(radius));
    }

    private int dpToPx(int dp) {
        DisplayMetrics resourec = mContext.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resourec);
    }
}
