package com.project.zhinan.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.project.zhinan.R;
import com.project.zhinan.activity.MainActivity;
import com.project.zhinan.base.fragment.BaseFragment;

/**
 * Created by ymh on 2016/4/22.
 */
public class MyPopupWindow {
    private final int pos;
    private Activity activity;
    private View myPopView;
    private PopupWindow popupwindow;
    private EditText et;
    private Button btn;

    public MyPopupWindow(Activity activity, View view, int pos) {
        this.activity = activity;
        init(view);
        this.pos = pos;
    }

    private void init(View view) {
        initpopWindow(view);
        initAnimation();
    }

    private void initAnimation() {
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.6f;
        activity.getWindow().setAttributes(lp);
        popupwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f;
                activity.getWindow().setAttributes(lp);
            }
        });
    }

    private void initpopWindow(View view) {
        myPopView = LayoutInflater.from(activity).inflate(R.layout.popupwindow_layout, null, false);
        et = (EditText) myPopView.findViewById(R.id.et);
        btn = (Button) myPopView.findViewById(R.id.btn);
        // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
        popupwindow = new PopupWindow(myPopView, 1080, 600, true);
        // 设置动画效果
        popupwindow.setAnimationStyle(R.style.AnimationFade);
        //设置在showAtLocation之前才能起作用
        popupwindow.setBackgroundDrawable(new BitmapDrawable());
//        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.back));
        popupwindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseFragment.lastRead = pos;
                BaseFragment.isRead[pos] = 1;
                popupwindow.dismiss();
//                Intent intent = new Intent();
//                activity.startActivity(new Intent(activity, MainActivity.class));
                activity.finish();
            }
        });
    }
}
