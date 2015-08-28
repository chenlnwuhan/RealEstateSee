package com.sales.realestate.android.view.popupwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.baidu.android.pushservice.PushManager;
import com.sales.realestate.android.AppContext;
import com.sales.realestate.android.R;
import com.sales.realestate.android.demo.LoginAty;
import com.sales.realestate.android.demo.MainActivity;
import com.sales.realestate.android.demo.custom.CustomAty;
import com.sales.realestate.android.demo.more.MoreAty;
import com.sales.realestate.android.utils.AppInit;

import org.kymjs.kjframe.ui.KJActivityStack;

/**
 * Created by cc on 2015/7/23.
 */
public class MoreLoginoutPW extends PopupWindow {
    private View conentView;

    private Button button_more_loginout_cancel;
    private Button button_more_loginout_ok;
    /**
     * 1 认购申请
     * 2 认购确定
     */
    public int buyType = 1;

    public void setBuyType(int typeIndex) {
        buyType = typeIndex;
        if (buyType == 1) {

        } else {

        }
    }

    public MoreLoginoutPW(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popup_more_loginup, null);
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);

        initPopUpWindows();
        initView();
        // 刷新状态
        this.update();
    }

    public void initPopUpWindows() {
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);

        //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
        this.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    public void initView() {
        button_more_loginout_cancel = (Button) conentView.findViewById(R.id.button_more_loginout_cancel);
        button_more_loginout_ok = (Button) conentView.findViewById(R.id.button_more_loginout_ok);
        button_more_loginout_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoreLoginoutPW.this.dismiss();
            }
        });
        button_more_loginout_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppInit.loginOutUserInfo();
                MoreLoginoutPW.this.dismiss();
                AppContext.getCurrentActivity().skipActivity(AppContext.getCurrentActivity(), LoginAty.class);
            }
        });

    }

}
