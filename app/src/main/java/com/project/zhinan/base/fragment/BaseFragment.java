package com.project.zhinan.base.fragment;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.zhinan.R;
import com.project.zhinan.adapter.BaseFragment_MyRecyclerViewAdapter;
import com.project.zhinan.bean.jsonbean;
import com.project.zhinan.utils.Cache;
import com.project.zhinan.utils.HttpUtils;
import com.project.zhinan.view.HomeFragment_MyViewPager;

import java.util.ArrayList;

/**
 * Created by ymh on 2016/4/11.
 */
public class BaseFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private BaseFragment_MyRecyclerViewAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    public jsonbean datas;
    private String resString;
    private Gson gson;
    private String url;
    private HomeFragment_MyViewPager viewPager;
    private ArrayList<ImageView> mImageViewList;
    private static int[] mImageIds = new int[]{R.mipmap.pic2, R.mipmap.pic1, R.mipmap.pic3};
    private LinearLayout ll_LinearLayout;

    public BaseFragment(String url) {
        this.url = url;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            datas = (gson.fromJson(msg.getData().getString("content"), new TypeToken<jsonbean>() {
            }.getType()));
//            mAdapter = new BaseFragment_MyRecyclerViewAdapter(getActivity(), datas);
//            mAdapter.
            mAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        ll_LinearLayout = (LinearLayout) view.findViewById(R.id.ll_LinearLayout);
        viewPager = (HomeFragment_MyViewPager) view.findViewById(R.id.viewPager);
        gson = new Gson();
//        getCacheData(); //获取缓存数据
        HttpUtils.getData(url, handler); //获取网络数据
//        mAdapter.notifyDataSetChanged();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        initViewPager();
        ll_LinearLayout.requestDisallowInterceptTouchEvent(true);
        datas = (gson.fromJson(Cache.data, new TypeToken<jsonbean>() {
        }.getType()));
        mAdapter = new BaseFragment_MyRecyclerViewAdapter(getActivity(), datas);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initViewPager() {
        mImageViewList = new ArrayList<ImageView>();
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView iv = new ImageView(getActivity());
            iv.setImageResource(mImageIds[i]);
            mImageViewList.add(iv);
        }
        viewPager.setAdapter(new PagerAdapter() {

            //viewpager中的组件数量
            @Override
            public int getCount() {
                return mImageIds.length;
            }

            //滑动切换的时候销毁当前的组件
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(mImageViewList.get(position));
            }

            //每次滑动的时候生成的组件
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(mImageViewList.get(position));
                return mImageViewList.get(position);
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getItemPosition(Object object) {
                return super.getItemPosition(object);
            }
        });
//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrollStateChanged(int arg0) {
//                Log.e("test", arg0 + " ");
//
//            }
//
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
////                Log.e("caonima", position + " " + positionOffset + " " + positionOffsetPixels);
////                if (position == mImageIds.length - 1 && lastPos == positionOffset && !isScroll) {
////                    viewPager.setCurrentItem(0);
////                    isScroll = true;
////                } else if (position == 0 && lastPos == positionOffset && !isScroll) {
////                    viewPager.setCurrentItem(mImageIds.length - 1);
////                    isScroll = true;
////                }
////                lastPos = positionOffset;
//            }
//
//            @Override
//            public void onPageSelected(int arg0) {
//                if (arg0 == 0) {
//                    len = 0;
//                } else {
//                    len = mPointWidth * arg0;
//                }
//                isScroll = false;
//                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewBluePoint
//                        .getLayoutParams();// 获取当前红点的布局参数
//                params.leftMargin = len;// 设置左边距
//                params.bottomMargin = DensityUtils.dp2px(getActivity(), 2);
//                viewBluePoint.setLayoutParams(params);// 重新给小红点设置布局参数
//            }
//        });
    }
}
