package com.sales.realestate.android.demo.more;

import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.MailInfo;
import com.sales.realestate.android.bean.NewsInfo;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.CipherUtils;
import org.kymjs.kjframe.utils.StringUtils;

/**
 * Created by chenlin on 2015/7/20.
 */
public class MoreChangePasswordAty extends KJActivity {

    @BindView(id = R.id.title_image_left, click = true)
    private ImageView titleImageLeft;

    @BindView(id = R.id.title_name)
    public TextView textViewTitle;

    @BindView(id = R.id.edittext_more_changepass_0)
    public EditText edittext_more_changepass_0;
    @BindView(id = R.id.edittext_more_changepass_1)
    public EditText edittext_more_changepass_1;
    @BindView(id = R.id.edittext_more_changepass_2)
    public EditText edittext_more_changepass_2;

    @BindView(id = R.id.button_more_loginout, click = true)
    public Button button_more_loginout;

    @BindView(id = R.id.view_toast_changepassword_err)
    public View view_toast_changepassword_err;
    @BindView(id = R.id.textview_changepassword_err)
    public TextView textview_changepassword_err;

    public Handler mHandler = new Handler();

    public Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            cancel();
        }
    };

    public void cancel() {
        if ((view_toast_changepassword_err.getVisibility() == View.VISIBLE) && (textview_changepassword_err.getText().equals("密码更改成功！"))) {
            button_more_loginout.setClickable(true);
            onBackPressed();
        }
        view_toast_changepassword_err.setVisibility(View.GONE);
        textview_changepassword_err.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivityActionMode(ActionBarMode.CUSTOMTITILE);
        setTitileResId(R.layout.view_title_simple);
        setRootViewResId(R.layout.aty_more_changepassword);
        setmBottomNavigation(BottomNavigation.NOBOTTOMSCROLL);
        super.onCreate(savedInstanceState);
    }

    public class ChangePasswordHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_KEY_CHANGEPASSWORD:
                    if (isError) {
                        textview_changepassword_err.setText(errorMessage);
                    } else {
                        try {
                            view_toast_changepassword_err.setVisibility(View.VISIBLE);
                            textview_changepassword_err.setText("密码更改成功！");
                            button_more_loginout.setClickable(false);
                            mHandler.postDelayed(mRunnable, GlobalVarible.GLOBALDELAY);
                        } catch (Exception e) {
                            toast(textViewTitle.getText().toString() + "解析错误！");
                        }
                    }
                    break;
            }

        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.title_image_left:
                onBackPressed();
                break;
            case R.id.button_more_loginout:
                checkChangePasswork();
                break;
        }
    }

    public void checkChangePasswork() {
        String oldPW = edittext_more_changepass_0.getText().toString();
        String newPW1 = edittext_more_changepass_1.getText().toString();
        String newPW2 = edittext_more_changepass_2.getText().toString();
        String newPW1MD5 = CipherUtils.encryptToSHA(newPW1).toUpperCase();
        newPW1MD5 = CipherUtils.md5(newPW1MD5).toUpperCase();

        if (StringUtils.isEmpty(oldPW) || StringUtils.isEmpty(newPW1) || StringUtils.isEmpty(newPW2)) {
            view_toast_changepassword_err.setVisibility(View.VISIBLE);
            textview_changepassword_err.setText("请填入密码！");
        } else if (!newPW1.equals(newPW2)) {
            view_toast_changepassword_err.setVisibility(View.VISIBLE);
            textview_changepassword_err.setText("新密码两次输入不一致！");

        } else {
            oldPW = CipherUtils.encryptToSHA(oldPW).toUpperCase();
            oldPW = CipherUtils.md5(oldPW).toUpperCase();
            if (!oldPW.equals(GlobalVarible.USER_PASSWORD)) {
                view_toast_changepassword_err.setVisibility(View.VISIBLE);
                textview_changepassword_err.setText("原密码不正确！");

            } else if (oldPW.equals(newPW1MD5)) {
                view_toast_changepassword_err.setVisibility(View.VISIBLE);
                textview_changepassword_err.setText("新旧密码一致！");
            } else {
                HttpBusiness.getChangePasswordHTTP(newPW1MD5, new ChangePasswordHttpBusiness());
            }

        }
        mHandler.postDelayed(mRunnable, GlobalVarible.GLOBALDELAY);
    }

    @Override
    public void initData() {
        super.initData();
    }

    public void initTitle(int pozition) {
        textViewTitle.setText("更改密码");
    }

    @Override
    public void initWidget() {
        super.initWidget();
        view_toast_changepassword_err.setVisibility(View.GONE);
        initTitle(0);
    }


}
