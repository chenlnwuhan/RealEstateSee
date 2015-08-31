package com.sales.realestate.android.demo;

import android.os.Bundle;
import android.os.Handler;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.sales.realestate.android.AppContext;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.R;
import com.sales.realestate.android.demo.custom.CustomAty;
import com.sales.realestate.android.utils.AppInit;
import com.sales.realestate.android.utils.AppUtils;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.utils.ScreenUtils;
import org.kymjs.kjframe.utils.StringUtils;

/**
 * Created by chenlin on 2015/7/31.
 */
public class InitAty extends KJActivity {

    public Handler mHander = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setActivityActionMode(ActionBarMode.NOTITLE);
        setRootViewResId(R.layout.aty_loading);
        super.onCreate(savedInstanceState);
    }

    public void bindBaidu(){
        PushManager.startWork(getApplicationContext(),
                PushConstants.LOGIN_TYPE_API_KEY,
                AppUtils.getMetaValue(InitAty.this, "api_key"));
    }
    public void  goToAcitity(){
        if (GlobalVarible.IS_LOGIN) {
            bindBaidu();
            this.skipActivity(this, MainActivity.class);
        }else{
            this.skipActivity(this, LoginAty.class);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(GlobalVarible.SCRREN_WIDTH==0){
            GlobalVarible.SCRREN_WIDTH = ScreenUtils.getScreenWidth(AppContext.getCurrentActivity());
            GlobalVarible.SCRRENT_HEIGHT = ScreenUtils.getScreenHeight(AppContext.getCurrentActivity());;
        }
        MainActivity.BuildingID = "";
        mHander.postDelayed(new Runnable() {
            @Override
            public void run() {
                AppInit.Init();
                goToAcitity();
            }
        }, 1000);
    }
}
