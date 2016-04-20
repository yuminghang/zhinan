package com.project.zhinan.base.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.zhinan.R;
import com.project.zhinan.activity.WebviewActivity;
import com.project.zhinan.adapter.NewsinglepicLayoutAdapter;
import com.project.zhinan.api.Urls;
import com.project.zhinan.bean.jsonbean;
import com.project.zhinan.utils.HttpUtils;
import com.project.zhinan.view.CircleIndicatorHelper;
import com.project.zhinan.view.HomeFragment_MyViewPager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import cn.refactor.library.SmoothCheckBox;

/**
 * Created by ymh on 2016/4/11.
 */
public class BaseFragment extends Fragment {
    private static final int VPRUN = 1;
    private ListView mlv;
    public static jsonbean datas;
    private String resString;
    private Gson gson;
    public String url;
    private HomeFragment_MyViewPager viewPager;
    private ArrayList<ImageView> mImageViewList;
    private static int[] mImageIds = new int[]{R.mipmap.pic2, R.mipmap.pic1, R.mipmap.pic3};
    public ArrayList<jsonbean.ResultEntity.ItemsEntity.BrandsEntity> objects;
    private View home_vp;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case VPRUN:
                    int currentItem = viewPager.getCurrentItem();
                    if (currentItem < mImageIds.length - 1) {
                        viewPager.setCurrentItem(currentItem + 1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                    break;
                case HttpUtils.DATA_GET:
                    datas = (gson.fromJson(msg.getData().getString("content"), new TypeToken<jsonbean>() {
                    }.getType()));
                    objects.addAll(datas.getResult().getItems().getBrands());
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };
    private NewsinglepicLayoutAdapter adapter;
    private SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
//        ll_LinearLayout = (LinearLayout) view.findViewById(R.id.ll_LinearLayout);
        datas = new jsonbean();
        home_vp = inflater.inflate(R.layout.homefragment_myviewpager, null, false);
        viewPager = (HomeFragment_MyViewPager) home_vp.findViewById(R.id.home_viewPager);

        gson = new Gson();
//        getCacheData(); //获取缓存数据
        mlv = (ListView) view.findViewById(R.id.recyclerview);


        initViewPager();
//        ll_LinearLayout.requestDisallowInterceptTouchEvent(true);
//        datas = (gson.fromJson(Cache.data, new TypeToken<jsonbean>() {
//        }.getType()));
        objects = new ArrayList<jsonbean.ResultEntity.ItemsEntity.BrandsEntity>();


        mlv.addHeaderView(home_vp);
        adapter = new NewsinglepicLayoutAdapter(getActivity(), objects);
        mlv.setAdapter(adapter);

        mlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("-----------------" + i + "---------------------");
                Bundle bundle = new Bundle();
                bundle.putString("url", Urls.DetailUrl);
                Intent intent = new Intent(getContext(), WebviewActivity.class);
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(VPRUN);
            }
        }, 0, 2000);
        HttpUtils.getData(url, handler); //获取网络数据

        return view;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initViewPager() {
        mImageViewList = new ArrayList<ImageView>();
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView iv = new ImageView(getActivity());
            iv.setImageResource(mImageIds[i]);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
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
        CircleIndicatorHelper circleIndicatorHelper = new CircleIndicatorHelper(getContext());
        circleIndicatorHelper.setViewpager(viewPager);
        circleIndicatorHelper.setFillColor("#FFD547EB");
        circleIndicatorHelper.setDefaultColor("#FFD547EB");
        circleIndicatorHelper.setRadius(4);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) {
//                Log.e("test", arg0 + " ");

            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int arg0) {
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
            }
        });
    }

}
