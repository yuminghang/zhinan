package com.project.zhinan.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ymh on 2016/4/14.
 */
public class HomeFragment_MyViewPager extends ViewPager {

    /**
     * 11个子页签水平滑动的Viewpager, 暂时不用
     *
     * @author Kevin
     */

    public HomeFragment_MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeFragment_MyViewPager(Context context) {
        super(context);
    }

    /**
     * 事件分发, 请求父控件及祖宗控件是否拦截事件
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);// 用getParent去请求,
        return super.dispatchTouchEvent(ev);
    }
}
