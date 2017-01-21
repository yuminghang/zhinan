package com.project.zhinan.base.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.zhinan.R;
import com.project.zhinan.activity.QianggouDetailActivity;
import com.project.zhinan.adapter.NewsinglepicLayoutAdapter;
import com.project.zhinan.bean.bean_version2;
import com.project.zhinan.utils.HttpUtils;
import com.project.zhinan.view.CircleIndicatorHelper;
import com.project.zhinan.view.HomeFragment_MyViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by ymh on 2016/4/11.
 */

public class BaseFragment_version2 extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    public  bean_version2 datas;
    public static int lastRead = 0;
    public static int[] isRead = new int[21];
    private final int VPRUN = 1;
    public ListView mlv;
    public String url;
    public ArrayList<bean_version2.DataEntity> objects;
    public ImageView back_to_top;
    private List<bean_version2.DataEntity> topList = new ArrayList<>();
    private String resString;
    private Gson gson;
    private HomeFragment_MyViewPager viewPager;
    private ArrayList<ImageView> mImageViewList = new ArrayList<>();
    PagerAdapter viewPagerAdapter = new PagerAdapter() {

        //viewpager中的组件数量
        @Override
        public int getCount() {
            return topList.size();
        }

        //滑动切换的时候销毁当前的组件
        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            if (mImageViewList != null && mImageViewList.size() > position) {
                container.removeView(mImageViewList.get(position));
            }
        }

        //每次滑动的时候生成的组件
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            try {
                container.addView(mImageViewList.get(position));
                final int pos = position;
                ImageView imageView = mImageViewList.get(position);
                final bean_version2.DataEntity entity = topList.get(position);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        Intent intent = new Intent(getContext(), QianggouDetailActivity.class);
                        bundle.putStringArray("img_urls", entity.getImgurls());
                        intent.putExtras(bundle);
                        intent.putExtra("pos", pos);
                        intent.putExtra("key", entity.getKey());
                        intent.putExtra("read", entity.getRead());
                        intent.putExtra("uuid", entity.getUuid());
                        getContext().startActivity(intent);
                    }
                });
                return mImageViewList.get(position);
            } catch (Exception e) {

            }
            return "";
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
    };
    private View home_vp;
    private NewsinglepicLayoutAdapter adapter;
    private SharedPreferences sharedPreferences;
    private SwipeRefreshLayout mSwipeLayout;
    private CircleIndicatorHelper circleIndicatorHelper;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case VPRUN:
                    int currentItem = viewPager.getCurrentItem();
                    if (currentItem < topList.size() - 1) {
                        viewPager.setCurrentItem(currentItem + 1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                    break;
                case HttpUtils.DATA_GET:
                    try {
                        datas = (gson.fromJson(msg.getData().getString("content"), new TypeToken<bean_version2>() {
                        }.getType()));
                        objects.clear();
                        topList.clear();
                        mImageViewList.clear();
                        objects.addAll(datas.getData());
                        int max = Math.min(3, objects.size());
                        for (int i = 0; i < max; i++) {
                            bean_version2.DataEntity dataEntity = objects.get(i);
                            topList.add(dataEntity);
                        }
                    }catch (Exception e){

                    }

//                    if (firstAttempt == 0) {
//                        firstAttempt = 1;
                    initViewPager();
//                    }
                    viewPagerAdapter.notifyDataSetChanged();
                    adapter.notifyDataSetChanged();
                    mSwipeLayout.setRefreshing(false);
                    break;
                default:
                    break;
            }
        }
    };
    private int firstAttempt = 0;
    private Timer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        datas = new bean_version2();
        home_vp = inflater.inflate(R.layout.homefragment_myviewpager, null, false);
        viewPager = (HomeFragment_MyViewPager) home_vp.findViewById(R.id.home_viewPager);
        gson = new Gson();
//        getCacheData(); //获取缓存数据
        mlv = (ListView) view.findViewById(R.id.recyclerview);
        initRefresh(view);

        objects = new ArrayList<>();
        mlv.addHeaderView(home_vp);
        adapter = new NewsinglepicLayoutAdapter(getActivity(), objects);
        mlv.setAdapter(adapter);

        mlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                System.out.println("-----------------" + i + "---------------------");
                Bundle bundle = new Bundle();
                Intent intent = new Intent(getContext(), QianggouDetailActivity.class);
                bundle.putStringArray("img_urls", objects.get(i - 1).getImgurls());
                intent.putExtras(bundle);
                intent.putExtra("pos", i - 1);
                intent.putExtra("key", objects.get(i - 1).getKey());
                intent.putExtra("read", objects.get(i - 1).getRead());
                intent.putExtra("uuid", objects.get(i - 1).getUuid());

                getContext().startActivity(intent);
            }
        });
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(VPRUN);
            }
        }, 0, 3000);
        HttpUtils.getData(url, handler); //获取网络数据
        return view;
    }

    public void initRefresh(View view) {
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeLayout.setOnRefreshListener(this);
//        mSwipeLayout.setColorScheme(getResources().getColor(R.color.title_color));
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initViewPager() {
        mImageViewList = new ArrayList<ImageView>();
        for (int i = 0; i < topList.size(); i++) {
            ImageView iv = new ImageView(getActivity());
            Glide.with(getActivity()).load(topList.get(i).getImgurls()[0]).into(iv);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageViewList.add(iv);
        }

        viewPager.setAdapter(viewPagerAdapter);
        circleIndicatorHelper = new CircleIndicatorHelper(getContext());

        circleIndicatorHelper.setViewpager(viewPager);
        circleIndicatorHelper.setFillColor("#FFD547EB");
        circleIndicatorHelper.setDefaultColor("#FFD547EB");
        circleIndicatorHelper.setRadius(4);
    }

    @Override
    public void onRefresh() {
        HttpUtils.getData(url, handler); //获取网络数据
    }

    @Override
    public void onPause() {
        super.onPause();
        if (timer != null) {
            timer.cancel();
        }
    }
}
