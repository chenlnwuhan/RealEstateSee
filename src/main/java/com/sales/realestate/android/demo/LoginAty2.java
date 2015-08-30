package com.sales.realestate.android.demo;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.google.gson.Gson;
import com.sales.realestate.android.AppContext;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.UserInfo;
import com.sales.realestate.android.utils.AppInit;
import com.sales.realestate.android.utils.AppUtils;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.CipherUtils;
import org.kymjs.kjframe.utils.KJLoger;
import org.kymjs.kjframe.utils.SPUtils;
import org.kymjs.kjframe.utils.StringUtils;

/**
 * Created by chenlin on 2015/7/20.
 */
public class LoginAty2 extends KJActivity {

    @BindView(id = R.id.view_toast_login_err)
    public RelativeLayout toastLoginErr;

    @BindView(id = R.id.text_login_name)
    public EditText editTextLoginName;

    @BindView(id = R.id.linearlayout_all)
    public LinearLayout linearlayout_all;

    @BindView(id = R.id.text_login_password)
    public EditText editTextLoginPassword;

    @BindView(id = R.id.text_login_verifycode)
    public EditText editTextLoginVeryfycode;

    @BindView(id = R.id.view_verifycode)
    public LinearLayout linearLayoutVerifycode;

    @BindView(id = R.id.textview_login_err)
    public TextView textview_login_err;

    @BindView(id = R.id.button_verifycode, click = true)
    public Button imageViewVerifycode;

    @BindView(id = R.id.button_login, click = true)
    public Button buttonLogin;


    public boolean isVerification = false;
    public String veryficationName = "";
    public String password;
    public String loginId = "";
    public String errorLoginId = "";
    public boolean isFinish;


    private class FinishRefresh extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                int i = 0;
                while (i < 5) {
                    KJLoger.debug(System.currentTimeMillis() + ":" + i);
                    i++;
                    String channelId = (String) SPUtils.get2(LoginAty2.this, "BAIDU_CHANNELID", "");
                    if (!StringUtils.isEmpty(channelId)) {
                        break;
                    } else {
                        Thread.sleep(1000);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            KJLoger.debug(System.currentTimeMillis() + ":doInBackground");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            KJLoger.state(LoginAty2.this.getClass().getName(), System.currentTimeMillis() + "baiduHandler");
            buttonLogin.setClickable(true);
            String channelId = (String) SPUtils.get2(LoginAty2.this, "BAIDU_CHANNELID", "");
            if (!StringUtils.isEmpty(channelId)) {
                HttpBusiness.getBaiDuCloud(channelId, new LoginHttpBusiness());
            } else {
                isFinish = true;
                skipActivity(LoginAty2.this, MainActivity.class);
            }
        }
    }

    public class LoginHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onFailure(int uid, int errorNo, String strMsg) {
            super.onFailure(uid, errorNo, strMsg);
            imageViewVerifycode.setClickable(true);
        }

        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {

                case HttpBusiness.HTTP_SENDMESSAGE:

                    if (isError) {
                        AppContext.getCurrentActivity().toast("验证码失败！");
                        imageViewVerifycode.setClickable(true);
                    } else {
                        try {
                            toast("发送验证码成功!");
                            new FinishRefreshV().execute();
                        } catch (Exception e) {
                            AppContext.getCurrentActivity().toast("发送验证码解析失败！");
                            imageViewVerifycode.setClickable(true);
                        }
                    }
                    break;
                case HttpBusiness.HTTP_KEY_BAIDU_CLOUD:

                    if (isError) {
                        AppContext.getCurrentActivity().toast("消息推送绑定失败！");
                    } else {
                        try {
                            //               AppContext.getCurrentActivity().toast("消息推送绑定成功！");
                            AppInit.baidubindstore("1");
                            isFinish = true;
                            skipActivity(LoginAty2.this, MainActivity.class);
                        } catch (Exception e) {
                            AppContext.getCurrentActivity().toast("消息推送绑定结果解析错误！");
                        }
                    }
                    break;

                case HttpBusiness.HTTP_KEY_LOGIN:
                    if (isError) {

                        toastLoginErr.setVisibility(View.VISIBLE);
                        textview_login_err.setText(errorMessage);
                        if (errorNo.equals("2001")) {
                            errorLoginId = loginId;
                            linearLayoutVerifycode.setVisibility(View.VISIBLE);
                            imageViewVerifycode.setClickable(true);
                        }
                    } else {
                        KJLoger.state(LoginAty2.this.getClass().getName(), System.currentTimeMillis() + "OK");
                        Gson gson = new Gson();
                        UserInfo mUserInfo = gson.fromJson(returnStr, UserInfo.class);
                        GlobalVarible.IS_LOGIN = true;
                        AppInit.storeUserInfo(mUserInfo.UserID, mUserInfo.UserName, password, mUserInfo.Token, mUserInfo.RoleID, mUserInfo.RoleName, mUserInfo.Mobile, mUserInfo.HeadImg);
                        String channelId = (String) SPUtils.get2(LoginAty2.this, "BAIDU_CHANNELID", "");
                        KJLoger.state(LoginAty2.this.getClass().getName(), System.currentTimeMillis() + "OK1");
                        PushManager.startWork(getApplicationContext(),
                                PushConstants.LOGIN_TYPE_API_KEY,
                                AppUtils.getMetaValue(LoginAty2.this, "api_key"));
                        if (StringUtils.isEmpty(channelId)) {
                            showProgress();
                            buttonLogin.setClickable(false);
                            new FinishRefresh().execute();
                        } else {
                            HttpBusiness.getBaiDuCloud(channelId, new LoginHttpBusiness());
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.button_login:
                loginId = editTextLoginName.getText().toString();
                if (errorLoginId.equals(loginId)) {
                    isVerification = true;
                } else {
                    isVerification = false;
                }
                password = editTextLoginPassword.getText().toString();
                String veryfycode = editTextLoginVeryfycode.getText().toString();
                if (StringUtils.isEmpty(loginId) || StringUtils.isEmpty(password)) {
                    toastLoginErr.setVisibility(View.VISIBLE);
                    textview_login_err.setText("用户名和密码不能为空！");
                    return;
                } else if (isVerification && StringUtils.isEmpty(veryfycode)) {
                    toastLoginErr.setVisibility(View.VISIBLE);
                    textview_login_err.setText("验证码不能为空！");
                    return;
                } else {
                    toastLoginErr.setVisibility(View.GONE);
                    password = CipherUtils.encryptToSHA(password).toUpperCase();
                    password = CipherUtils.md5(password).toUpperCase();
                    if (!errorLoginId.equals(loginId)) {
                        HttpBusiness.getLogin(loginId, password, new LoginHttpBusiness());
                    } else {
                        HttpBusiness.getMessageLogin(loginId, password, veryfycode, new LoginHttpBusiness());
                    }

                }
                break;
            case R.id.button_verifycode:
                imageViewVerifycode.setClickable(false);
                HttpBusiness.sendMessage(loginId, new LoginHttpBusiness());
                break;


        }
    }

    private class FinishRefreshV extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            int i = 60;
            while (i > 1 && (!isFinish)) {
                try {
                    Thread.sleep(1000);
                    i--;
                    publishProgress(i + "");
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            imageViewVerifycode.setClickable(true);
            imageViewVerifycode.setText("发送验证码");
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            imageViewVerifycode.setText(values[0] + "秒后重发!");
        }
    }
//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        toast("1111"+event);
//        if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
//            /*隐藏软键盘*/
//            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            return true;
//        }
//        return super.dispatchKeyEvent(event);
//    }
    private View.OnKeyListener onKeyListener = new View.OnKeyListener() {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            toast("1111"+keyCode+"1111"+event);

            if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                /*隐藏软键盘*/
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if(inputMethodManager.isActive()){
                    inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                }

                toast("1111");

                return true;
            }
            return false;
        }
    };
    @Override
    public void initWidget() {
//        editTextLoginName.setOnKeyListener(onKeyListener);
        /*
        linearlayout_all.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){

            @Override
            public void onGlobalLayout(){

                //比较Activity根布局与当前布局的大小
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if(inputMethodManager.isActive()){
                    toast("gogogo");
                }
                int heightDiff = linearlayout_all.getRootView().getHeight()- linearlayout_all.getHeight();
                if(heightDiff >100){
                    toast(heightDiff+"");
                }else{
                    toast(heightDiff+"");
                }
            }
        });
        */
        super.initWidget();
        linearLayoutVerifycode.setVisibility(View.GONE);
        isVerification = false;
        editTextLoginName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus){
//                    LinearLayout.LayoutParams mLayoutParams = (LinearLayout.LayoutParams) linearlayout_all.getLayoutParams();
//                    mLayoutParams.setMargins(0,-200,0,0);
//                    linearlayout_all.setLayoutParams(mLayoutParams);
//                }else{
//                    LinearLayout.LayoutParams mLayoutParams = (LinearLayout.LayoutParams) linearlayout_all.getLayoutParams();
//                    mLayoutParams.setMargins(0,0,0,0);
//                    linearlayout_all.setLayoutParams(mLayoutParams);
//                }

                String userLogin = editTextLoginName.getText().toString();
                if ((!StringUtils.isEmpty(veryficationName)) && (veryficationName.equals(userLogin))) {

                } else {
                    linearLayoutVerifycode.setVisibility(View.GONE);
                    isVerification = false;
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivityActionMode(ActionBarMode.NOTITLE);
        setRootViewResId(R.layout.aty_login2);
        setmBottomNavigation(BottomNavigation.ALL);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (GlobalVarible.IS_LOGIN) {
            this.showActivity(this, MainActivity.class);
        }
    }
}
