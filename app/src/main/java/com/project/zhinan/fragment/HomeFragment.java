package com.project.zhinan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.zhinan.R;
import com.project.zhinan.adapter.MyFragmentPagerAdapter;
import com.project.zhinan.fragment.HomeTabFragmentSons.Baoxiang;
import com.project.zhinan.fragment.HomeTabFragmentSons.Canyin;
import com.project.zhinan.fragment.HomeTabFragmentSons.Fangchan;
import com.project.zhinan.fragment.HomeTabFragmentSons.Fuwu;
import com.project.zhinan.fragment.HomeTabFragmentSons.Gongyi;
import com.project.zhinan.fragment.HomeTabFragmentSons.Hongbao;
import com.project.zhinan.fragment.HomeTabFragmentSons.Huodong;
import com.project.zhinan.fragment.HomeTabFragmentSons.Jiaoyu;
import com.project.zhinan.fragment.HomeTabFragmentSons.Jingcai;
import com.project.zhinan.fragment.HomeTabFragmentSons.Jinrong;
import com.project.zhinan.fragment.HomeTabFragmentSons.Lvyou;
import com.project.zhinan.fragment.HomeTabFragmentSons.Meizhuang;
import com.project.zhinan.fragment.HomeTabFragmentSons.Qiche;
import com.project.zhinan.fragment.HomeTabFragmentSons.Quanbu;
import com.project.zhinan.fragment.HomeTabFragmentSons.Shangquan;
import com.project.zhinan.fragment.HomeTabFragmentSons.Tongxun;
import com.project.zhinan.fragment.HomeTabFragmentSons.Xiuxian;
import com.project.zhinan.fragment.HomeTabFragmentSons.Yiyao;
import com.project.zhinan.fragment.HomeTabFragmentSons.Zhaopin;
import com.project.zhinan.fragment.HomeTabFragmentSons.Zhaoshang;
import com.zaaach.citypicker.CityPickerActivity;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    static ArrayList<String> titleContainer = new ArrayList<String>();
    public ViewPager mViewPager;
    public ArrayList<Fragment> fragmentList;
    //    Context context;
    LayoutInflater mlayoutInflater;
    MyFragmentPagerAdapter myFragmentPagerAdapter;
    private View view;
    private TabLayout mTabLayout;
    private LinearLayout linear_layout;
    private TextView city;

    public HomeFragment() {
    }

    private static void initTitle() {
        //页签项
        titleContainer.add("全部");
        titleContainer.add("教育");
        titleContainer.add("商圈");
        titleContainer.add("旅游");
        titleContainer.add("房产");
        titleContainer.add("建材");
        titleContainer.add("医药");
        titleContainer.add("餐饮");
//        titleContainer.add("抢购");
        titleContainer.add("一元抢购");
        titleContainer.add("宝箱");
        titleContainer.add("活动");
        titleContainer.add("休闲");
        titleContainer.add("招商");
        titleContainer.add("招聘");
        titleContainer.add("汽车");
        titleContainer.add("金融");
        titleContainer.add("通讯");
        titleContainer.add("美妆");
        titleContainer.add("服务");
        titleContainer.add("公益");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homefragment_layout, container, false);
        findViews();
        initViewpager();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void findViews() {
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        city = (TextView) view.findViewById(R.id.city);
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动
                startActivityForResult(new Intent(getActivity(), CityPickerActivity.class), 0);
            }
        });
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        linear_layout = (LinearLayout) view.findViewById(R.id.linear_layout);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (data != null) {
                String content = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                city.setText(content);
            }
        }
    }

    private void initViewpager() {
        initTitle();
        fragmentList = new ArrayList<Fragment>();
        Quanbu quanbu = new Quanbu();
        Jiaoyu jiaoyu = new Jiaoyu();
        Shangquan shangquan = new Shangquan();
        Lvyou lvyou = new Lvyou();
        Fangchan fangchan = new Fangchan();
        Jingcai jiancai = new Jingcai();
        Yiyao yiyao = new Yiyao();
        Canyin canyin = new Canyin();
//        Qianggou qianggouFragment = new Qianggou();
        Hongbao hongbaoFragment = new Hongbao();
        Baoxiang baoxiangFragment = new Baoxiang();
        Huodong huodong = new Huodong();
        Xiuxian xiuxian = new Xiuxian();
        Zhaoshang zhaoshang = new Zhaoshang();
        Zhaopin zhaopin = new Zhaopin();
        Qiche qiche = new Qiche();
        Jinrong jinrong = new Jinrong();
        Tongxun tongxun = new Tongxun();
        Meizhuang meizhuang = new Meizhuang();
        Fuwu fuwu = new Fuwu();
        Gongyi gongyi = new Gongyi();


        fragmentList.add(quanbu);
        fragmentList.add(jiaoyu);
        fragmentList.add(shangquan);
        fragmentList.add(lvyou);
        fragmentList.add(fangchan);
        fragmentList.add(jiancai);
        fragmentList.add(yiyao);
        fragmentList.add(canyin);
//        fragmentList.add(qianggouFragment);
        fragmentList.add(hongbaoFragment);
        fragmentList.add(huodong);
        fragmentList.add(baoxiangFragment);
        fragmentList.add(xiuxian);
        fragmentList.add(zhaoshang);
        fragmentList.add(zhaopin);
        fragmentList.add(qiche);
        fragmentList.add(jinrong);
        fragmentList.add(tongxun);
        fragmentList.add(meizhuang);
        fragmentList.add(fuwu);
        fragmentList.add(gongyi);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), fragmentList, titleContainer);
        mViewPager.setAdapter(myFragmentPagerAdapter);
        mViewPager.setCurrentItem(0);//设置当前显示标签页为第一页
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(myFragmentPagerAdapter);
    }

}
