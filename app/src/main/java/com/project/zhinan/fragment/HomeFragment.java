package com.project.zhinan.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.project.zhinan.R;
import com.project.zhinan.adapter.MyFragmentPagerAdapter;
import com.project.zhinan.api.Urls;
import com.project.zhinan.base.fragment.BaseFragment;
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
import com.project.zhinan.fragment.HomeTabFragmentSons.Qianggou;
import com.project.zhinan.fragment.HomeTabFragmentSons.Qiche;
import com.project.zhinan.fragment.HomeTabFragmentSons.Shangquan;
import com.project.zhinan.fragment.HomeTabFragmentSons.Tongxun;
import com.project.zhinan.fragment.HomeTabFragmentSons.Xiuxian;
import com.project.zhinan.fragment.HomeTabFragmentSons.Yiyao;
import com.project.zhinan.fragment.HomeTabFragmentSons.Zhaopin;
import com.project.zhinan.fragment.HomeTabFragmentSons.Zhaoshang;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    Context context;
    LayoutInflater mlayoutInflater;
    private View view;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private LinearLayout linear_layout;
    private ArrayList<BaseFragment> fragmentList;
    MyFragmentPagerAdapter myFragmentPagerAdapter;
    static ArrayList<String> titleContainer = new ArrayList<String>();

    public HomeFragment(Context context) {
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homefragment_layout, container, false);
        findViews();
        initViewpager();
        return view;
    }

    private void findViews() {
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        linear_layout = (LinearLayout) view.findViewById(R.id.linear_layout);
    }

    private void initViewpager() {
        initTitle();
        fragmentList = new ArrayList<BaseFragment>();
        Qianggou qianggouFragment = new Qianggou(Urls.QianggouUrl);
        Hongbao hongbaoFragment = new Hongbao(Urls.HongbaoUrl);
        Baoxiang baoxiangFragment = new Baoxiang(Urls.BaoxiangUrl);
        Huodong huodong = new Huodong(Urls.HuodongUrl);
        Shangquan shangquan = new Shangquan(Urls.ShangquanUrl);
        Jiaoyu jiaoyu = new Jiaoyu(Urls.Jiaoyu);
        Lvyou lvyou = new Lvyou(Urls.LvyouUrl);
        Fangchan fangchan = new Fangchan(Urls.FangchanUrl);
        Jingcai jingcai = new Jingcai(Urls.JiancaiUrl);
        Yiyao yiyao = new Yiyao(Urls.YiyaoUrl);
        Canyin canyin = new Canyin(Urls.CanyinUrl);
        Xiuxian xiuxian = new Xiuxian(Urls.XiuxianUrl);
        Zhaoshang zhaoshang = new Zhaoshang(Urls.ZhaoshangUrl);
        Zhaopin zhaopin = new Zhaopin(Urls.ZhaopinUrl);
        Qiche qiche = new Qiche(Urls.QicheUrl);
        Jinrong jinrong = new Jinrong(Urls.JinrongUrl);
        Tongxun tongxun = new Tongxun(Urls.TongxunUrl);
        Meizhuang meizhuang = new Meizhuang(Urls.MeizhuangUrl);
        Fuwu fuwu = new Fuwu(Urls.FuwuUrl);
        Gongyi gongyi = new Gongyi(Urls.GongyiUrl);

        fragmentList.add(qianggouFragment);
        fragmentList.add(hongbaoFragment);
        fragmentList.add(baoxiangFragment);
        fragmentList.add(huodong);
        fragmentList.add(shangquan);
        fragmentList.add(jiaoyu);
        fragmentList.add(lvyou);
        fragmentList.add(fangchan);
        fragmentList.add(jingcai);
        fragmentList.add(yiyao);
        fragmentList.add(canyin);
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


    private static void initTitle() {
        //页签项
        titleContainer.add("抢购");
        titleContainer.add("红包");
        titleContainer.add("宝箱");
        titleContainer.add("活动");
        titleContainer.add("商圈");
        titleContainer.add("教育");
        titleContainer.add("旅游");
        titleContainer.add("房产");
        titleContainer.add("建材");
        titleContainer.add("医药");
        titleContainer.add("餐饮");
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

}
