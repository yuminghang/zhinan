package com.project.zhinan.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.project.zhinan.MyApplication;
import com.project.zhinan.R;
import com.project.zhinan.activity.AboutActivity;
import com.project.zhinan.activity.AccountActivity;
import com.project.zhinan.activity.HongBaoActivity;
import com.project.zhinan.activity.LoginActivity;
import com.project.zhinan.activity.MyAssets;
import com.project.zhinan.activity.MyCollection;
import com.project.zhinan.activity.MyUpload;
import com.project.zhinan.activity.RegisterActivity;
import com.project.zhinan.activity.ServiceActivity;
import com.project.zhinan.activity.ZNActActivity;
import com.project.zhinan.utils.ToolFor9Ge;
import com.project.zhinan.view.MyPopupWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SettingFragment extends Fragment {
    private View view;
    private LinearLayout mLayoutEmptyinclude;
    private LinearLayout mLayoutUserinclude;
    private ListView mListViewListView;
    private LinearLayout mLayoutContentLinearLayout;
    private ArrayList<String> strings;
    private SharedPreferences sharedPreferences;
    private boolean isLogin;
    //    private Context context;
//
//    public SettingFragment(Context context) {
//        this.context = context;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.settingfragment_layout, container, false);
        initView();
        return view;
    }

    @Override
    public void onResume() {
        initData();
        super.onResume();
    }

    private void initData() {
        strings = new ArrayList<String>();
        strings.add("账户与安全");
        strings.add("我的资产");
        strings.add("我的收藏");
        strings.add("我的上传");
        strings.add("我的红包");
        strings.add("指南客服");
        strings.add("指南活动");
        strings.add("法律条款");
        List<Map<String, Object>> listems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < strings.size(); i++) {
            Map<String, Object> listem = new HashMap<String, Object>();
            listem.put("name", strings.get(i));
            listems.add(listem);
        }

        sharedPreferences = getContext().getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean("isLogin", false);
        String name = sharedPreferences.getString("name", null);
        int account = sharedPreferences.getInt("account", 0);
        mListViewListView.setAdapter(new SimpleAdapter(getContext(), listems, R.layout.settingitem, new String[]{"name"}, new int[]{R.id.item_name}));
        if (isLogin) {
            MyApplication.getInstance().setLoginIn();
            mLayoutEmptyinclude.setVisibility(View.GONE);
            mLayoutUserinclude.setVisibility(View.VISIBLE);
            initLoginData(name, account);
        } else {
            mLayoutEmptyinclude.setVisibility(View.VISIBLE);
            mLayoutUserinclude.setVisibility(View.GONE);
            initUnLoginData();
        }
        mListViewListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        iftoDetail(AccountActivity.class);
                        break;
                    case 1:
                        if (ToolFor9Ge.checkNetworkInfo(getContext())) {
                            iftoDetail(MyAssets.class);
                        } else {
                            Toast.makeText(getContext(), "没有网络!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        if (ToolFor9Ge.checkNetworkInfo(getContext())) {
                            iftoDetail(MyCollection.class);
                        } else {
                            Toast.makeText(getContext(), "没有网络!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:
                        if (ToolFor9Ge.checkNetworkInfo(getContext())) {
                            iftoDetail(MyUpload.class);
                        } else {
                            Toast.makeText(getContext(), "没有网络!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 4:
                        if (ToolFor9Ge.checkNetworkInfo(getContext())) {
                            iftoDetail(HongBaoActivity.class);
                        } else {
                            Toast.makeText(getContext(), "没有网络!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 5:
                        intent.setClass(getContext(), ServiceActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent.setClass(getContext(), ZNActActivity.class);
                        startActivity(intent);
                        break;
                    case 7:
                        intent.setClass(getContext(), AboutActivity.class);
                        startActivity(intent);
//                        new MyPopupWindow(getActivity(), view, pos);
                        break;
                    default:
                        break;
                }

            }
        });
    }

    private void iftoDetail(Class c) {
        Intent intent = new Intent();
        if (isLogin) {
            intent.setClass(getContext(), c);
        } else {
            intent.setClass(getContext(), LoginActivity.class);
        }
        startActivity(intent);
    }

    /**
     * 已经登录的操作
     *
     * @param name
     * @param account
     */
    private void initLoginData(String name, int account) {
        TextView txtName = (TextView) mLayoutUserinclude.findViewById(R.id.txtName);
        TextView txtJifen = (TextView) mLayoutUserinclude.findViewById(R.id.txtJifen);
        txtName.setText(name);
        txtJifen.setText("积分：" + account);
        mLayoutUserinclude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AccountActivity.class));
            }
        });

    }

    /**
     * 未登录操作
     */
    private void initUnLoginData() {
        TextView bt_setting_login = (TextView) mLayoutEmptyinclude.findViewById(R.id.bt_setting_login);
        TextView bt_setting_register = (TextView) mLayoutEmptyinclude.findViewById(R.id.bt_setting_register);
        //登录
        bt_setting_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        //注册
        bt_setting_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initView() {
        mLayoutEmptyinclude = (LinearLayout) view.findViewById(R.id.layoutEmpty);
        mLayoutUserinclude = (LinearLayout) view.findViewById(R.id.layoutUser);
        mListViewListView = (ListView) view.findViewById(R.id.listView);
        mLayoutContentLinearLayout = (LinearLayout) view.findViewById(R.id.layoutContent);
    }


}
