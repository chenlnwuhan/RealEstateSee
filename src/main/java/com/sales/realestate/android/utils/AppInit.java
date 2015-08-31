package com.sales.realestate.android.utils;

import android.content.Context;

import com.baidu.android.pushservice.PushManager;
import com.sales.realestate.android.AppContext;
import com.sales.realestate.android.CommomKey;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.demo.MainActivity;
import com.sales.realestate.android.demo.custom.CustomAty;
import com.sales.realestate.android.demo.custom.CustomAty2;

import org.kymjs.kjframe.http.HttpConfig;
import org.kymjs.kjframe.ui.KJActivityStack;
import org.kymjs.kjframe.utils.AppUtils;
import org.kymjs.kjframe.utils.SPUtils;
import org.kymjs.kjframe.utils.ScreenUtils;
import org.kymjs.kjframe.utils.StringUtils;
import org.kymjs.kjframe.utils.SystemTool;

/**
 * Created by chenlin on 2015/7/28.
 */
public class AppInit {
    public static final String SP_KEY_USERLOGINID = "SP_KEY_USERLOGINID";
    public static final String SP_KEY_USER_PASSWORD= "SP_KEY_USER_PASSWORD";
    public static final String SP_KEY_USER_TOKEN= "SP_KEY_USER_TOKEN";
    public static final String SP_KEY_USER_NAME= "SP_KEY_USER_NAME";
    public static final String SP_KEY_USER_ROLE_ID= "SP_KEY_USER_ROLE_ID";
    public static final String SP_KEY_USER_ROLE_NAME= "SP_KEY_USER_ROLE_NAME";
    public static final String SP_KEY_USER_PHONE= "SP_KEY_USER_PHONE";
    public static final String SP_KEY_USER_HEAD= "SP_KEY_USER_HEAD";

    public static boolean getIsLogin(){
        if(!GlobalVarible.IS_LOGIN){
            initUserInfo();
        }
        if(StringUtils.isEmpty(GlobalVarible.USER_ID)){
            GlobalVarible.IS_LOGIN = false ;
            return false ;
        }
        GlobalVarible.IS_LOGIN = true ;
        return true ;

    }

    public static void initUserInfo(){
        GlobalVarible.USER_ID = (String) SPUtils.get(AppContext.mApplication, SP_KEY_USERLOGINID, "");
        GlobalVarible.USER_PASSWORD = (String) SPUtils.get(AppContext.mApplication, SP_KEY_USER_PASSWORD, "");
        GlobalVarible.USER_TOKEN = (String) SPUtils.get(AppContext.mApplication, SP_KEY_USER_TOKEN, "");
        GlobalVarible.USER_NAME = (String) SPUtils.get(AppContext.mApplication, SP_KEY_USER_NAME, "");
        GlobalVarible.ROLE_ID = (String) SPUtils.get(AppContext.mApplication, SP_KEY_USER_ROLE_ID, "");
        GlobalVarible.ROLE_NAME = (String) SPUtils.get(AppContext.mApplication, SP_KEY_USER_ROLE_NAME, "");
        GlobalVarible.USER_PHONE = (String) SPUtils.get(AppContext.mApplication, SP_KEY_USER_PHONE, "");
        GlobalVarible.USER_HEAD = (String) SPUtils.get(AppContext.mApplication, SP_KEY_USER_HEAD, "");
        HttpConfig.sCookie = ";_token="+GlobalVarible.USER_TOKEN+";_userid="+GlobalVarible.USER_ID;
    }

    public static void initAppInfo(){

        if(StringUtils.isEmpty( GlobalVarible.IMEI)){
            GlobalVarible.IMEI = SystemTool.getPhoneIMEI(AppContext.mApplication);
            GlobalVarible.IMEI = "1234567890";
        }
        GlobalVarible.VERSION_ID  = SystemTool.getAppVersionCode(AppContext.mApplication);
        GlobalVarible.VERSION_NAME= AppUtils.getVersionName(AppContext.mApplication);
    }

    public static void storeUserInfo(String userId,String userName,String password,String token,String roleId,String roleName,String moblie,String head){
        GlobalVarible.USER_ID = userId;
        GlobalVarible.USER_PASSWORD = password;
        GlobalVarible.USER_TOKEN = token;
        GlobalVarible.USER_NAME = userName;
        GlobalVarible.ROLE_ID = roleId;
        GlobalVarible.ROLE_NAME = roleName;
        GlobalVarible.USER_PHONE = moblie;
        GlobalVarible.USER_HEAD = head;
        HttpConfig.sCookie = ";_token="+token+";_userid="+userId;
 //       GlobalVarible.ROLE_NAME =USER_PHONE
//        HttpConfig.sCookie = token;

        SPUtils.put(AppContext.mApplication, SP_KEY_USERLOGINID, GlobalVarible.USER_ID);
        SPUtils.put(AppContext.mApplication, SP_KEY_USER_PASSWORD, GlobalVarible.USER_PASSWORD);
        SPUtils.put(AppContext.mApplication, SP_KEY_USER_TOKEN, GlobalVarible.USER_TOKEN);
        SPUtils.put(AppContext.mApplication, SP_KEY_USER_NAME, GlobalVarible.USER_NAME);
        SPUtils.put(AppContext.mApplication, SP_KEY_USER_ROLE_ID, GlobalVarible.ROLE_ID);
        SPUtils.put(AppContext.mApplication, SP_KEY_USER_ROLE_NAME, GlobalVarible.ROLE_NAME);
        SPUtils.put(AppContext.mApplication, SP_KEY_USER_PHONE, GlobalVarible.USER_PHONE);
        SPUtils.put(AppContext.mApplication, SP_KEY_USER_HEAD, GlobalVarible.USER_HEAD);
    }

    public static void baidubindstore(String abc){
        GlobalVarible.IS_BIND = abc ;
        SPUtils.put(AppContext.mApplication, "BAIDU", GlobalVarible.IS_BIND);

    }
    public static void checkBaidu(){
        GlobalVarible.IS_BIND = (String) SPUtils.get(AppContext.mApplication, "BAIDU", "");
    }

    public static void loginOutUserInfo(){
        GlobalVarible.IS_LOGIN = false;
        GlobalVarible.USER_ID = "";
        GlobalVarible.USER_PASSWORD = "";
        GlobalVarible.USER_TOKEN = "";
        GlobalVarible.USER_NAME = "";
        GlobalVarible.ROLE_ID = "";
        GlobalVarible.ROLE_NAME = "";
        HttpConfig.sCookie = "";

        MainActivity.BuildingID = "";
        CustomAty.BuildingID = "";
        CustomAty2.BuildingID = "";
        CustomAty.propertyId = "";
        CustomAty2.propertyId = "";
        GlobalVarible.cleanData();
        SPUtils.clear(AppContext.mApplication);
        unBindBaidu();
        KJActivityStack.create().finishAllActivity();
    }
    /**
     * 程序初始化时，初始化用户信息
     *
     */
    public static void Init(){
        getIsLogin();
        initAppInfo();
        checkBaidu();
    }

    public static void unBindBaidu(){
        PushManager.stopWork(AppContext.getCurrentActivity().getApplicationContext());
    }
}
