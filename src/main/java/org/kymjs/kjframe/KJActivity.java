/*
 * Copyright (c) 2014, 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kymjs.kjframe;

import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.FrameActivity;
import org.kymjs.kjframe.ui.KJActivityStack;
import org.kymjs.kjframe.utils.KJLoger;
import org.kymjs.kjframe.utils.StringUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.sales.realestate.android.AppContext;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.R;
import com.sales.realestate.android.UrlApiConfig;
import com.sales.realestate.android.bean.JsonInfo;
import com.sales.realestate.android.demo.InitAty;

/**
 * @author kymjs (https://github.com/kymjs)
 */
public abstract class KJActivity extends FrameActivity {

    public static long lastToastTime = 0 ;
    public static String lastToastString = "";
    public static final int MESSAGE_CANCEL_WATING_DIALOG = 9999;
    public ProgressDialog mProgress;
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_CANCEL_WATING_DIALOG:
                    cancelProgress();
                    break;
            }
        }
    };
    @Override
    public void initBottomNavagation() {

    }

    /**
     * 当前Activity状态
     */
    public static enum ActivityState {
        RESUME, PAUSE, STOP, DESTROY
    }

    public Activity aty;
    /** Activity状态 */
    public ActivityState activityState = ActivityState.DESTROY;



    /***************************************************************************
     * 
     * print Activity callback methods
     * 
     ***************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        aty = this;
        KJActivityStack.create().addActivity(this);
        KJLoger.state(this.getClass().getName(), "---------onCreat ");
        super.onCreate(savedInstanceState);
        mProgress = new ProgressDialog(this);
        mProgress.setCancelable(false);
    }

    /**
     * 显示等待框UI操作
     * @throws
     */
    public void showProgress() {
        if( null != mProgress  &&!mProgress.isShowing()) {
            try {
                mProgress.show();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            mProgress.setContentView(R.layout.dialog_wait);
            mHandler.removeCallbacks(runnable2);
            mHandler.postDelayed(runnable,8000);

        }
    }


    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            realCancelProgress();
        }
    };

    Runnable runnable2=new Runnable() {
        @Override
        public void run() {
            realCancelProgress();
        }
    };
    /**
     * 等待框UI操作
     * @throws
     */
    public void cancelProgress() {
        mHandler.removeCallbacks(runnable2);
        mHandler.postDelayed(runnable2,400);
    }
    /**
     * 等待框UI操作
     * @throws
     */
    public void realCancelProgress(){

        /**
         * 要记住，有些时候，就是登出时，只要登出没有成功，就不能取消等待框
         */
//		if(isNeedToBlockMprogress)return ;
        if(null!=mProgress && mProgress.isShowing()){
            try {
                mProgress.cancel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mHandler.removeCallbacks(runnable);
    }


    @Override
    protected void onStart() {
        super.onStart();
        AppContext.setCurrentActivity(this);
        KJLoger.state(this.getClass().getName(), "---------onStart ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityState = ActivityState.RESUME;
        AppContext.setCurrentActivity(this);
        KJLoger.state(this.getClass().getName(), "---------onResume ");
        if(!GlobalVarible.IS_LOGIN&&(this.getClass().getName().indexOf("LoginAty")<0)&&(this.getClass().getName().indexOf("InitAty")<0)){
            skipActivity(this, InitAty.class );
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityState = ActivityState.PAUSE;
        KJLoger.state(this.getClass().getName(), "---------onPause ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        activityState = ActivityState.STOP;
        KJLoger.state(this.getClass().getName(), "---------onStop ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        AppContext.setCurrentActivity(this);
        KJLoger.state(this.getClass().getName(), "---------onRestart ");
    }

    @Override
    protected void onDestroy() {
        activityState = ActivityState.DESTROY;
        KJLoger.state(this.getClass().getName(), "---------onDestroy ");
        super.onDestroy();
        KJActivityStack.create().finishActivity(this);
    }

    /**
     * skip to @param(cls)，and call @param(aty's) finish() method
     */
    @Override
    public void skipActivity(Activity aty, Class<?> cls) {
        showActivity(aty, cls);
        aty.finish();
    }

    /**
     * skip to @param(cls)，and call @param(aty's) finish() method
     */
    @Override
    public void skipActivity(Activity aty, Intent it) {
        showActivity(aty, it);
        aty.finish();
    }

    /**
     * skip to @param(cls)，and call @param(aty's) finish() method
     */
    @Override
    public void skipActivity(Activity aty, Class<?> cls, Bundle extras) {
        showActivity(aty, cls, extras);
        aty.finish();
    }

    /**
     * show to @param(cls)，but can't finish activity
     */
    @Override
    public void showActivity(Activity aty, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(aty, cls);
        aty.startActivity(intent);
    }

    /**
     * show to @param(cls)，but can't finish activity
     */
    @Override
    public void showActivity(Activity aty, Intent it) {
        aty.startActivity(it);
    }

    /**
     * show to @param(cls)，but can't finish activity
     */
    @Override
    public void showActivity(Activity aty, Class<?> cls, Bundle extras) {
        Intent intent = new Intent();
        intent.putExtras(extras);
        intent.setClass(aty, cls);
        aty.startActivity(intent);
    }
    public void toast(String text) {
        if(StringUtils.isEmpty(text))return ;
        long tmplastToastTime = System.currentTimeMillis();
        if((Math.abs(tmplastToastTime-lastToastTime)>2500)||(!lastToastString.equals(text))){
            lastToastTime = tmplastToastTime ;
            lastToastString = text;
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
            KJLoger.debug(text);
        }
    }
}
