/*
 * Copyright (c) 2014,KJFrameForAndroid Open Source Project,张涛.
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
package org.kymjs.kjframe.ui;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.sales.realestate.android.R;
import com.sales.realestate.android.demo.bean.NavigationItemBean;

import org.kymjs.kjframe.utils.KJLoger;

import java.util.List;

/**
 * Activity's framework,the developer shouldn't extends it<br>
 * <p/>
 * <b>创建时间</b> 2014-3-1 <br>
 * <b>最后修改时间</b> 2014-10-17<br>
 *
 * @author kymjs (https://github.com/kymjs)
 * @version 1.8
 */
public abstract class FrameActivity extends FragmentActivity implements
        OnClickListener, I_BroadcastReg, I_KJActivity, I_SkipActivity {

    public static final int WHICH_MSG = 0X37210;

    public static int TITLE_RES_ID = 0;
    public static int ROOT_RES_ID = 0;

    private LayoutInflater mInflater;   //实例化layout使用的类
    protected View contentView;                    //页面中间UI部分
    protected List<NavigationItemBean> bottomButtons;


    /**
     * 设置当前activity标题状态
     */
    public static enum ActionBarMode {
        NOTITLE, CUSTOMTITILE
    }

    public static enum BottomNavigation {
        HOME, NOBOTTOMSCROLL, JUSTNOSCROLL,JUSTNOBOTTOM,ALL
    }

    public BottomNavigation mBottomNavigation = BottomNavigation.NOBOTTOMSCROLL;

    public ActionBarMode mActionBarMode = ActionBarMode.NOTITLE;

    public void setActivityActionMode(ActionBarMode mActionBarMode) {
        this.mActionBarMode = mActionBarMode;
    }


    public void setmBottomNavigation(BottomNavigation mBottomNavigation) {
        this.mBottomNavigation = mBottomNavigation;
    }

    /**
     * 一个私有回调类，线程中初始化数据完成后的回调
     */
    private interface ThreadDataCallBack {
        void onSuccess();
    }

    private static ThreadDataCallBack callback;

    // 当线程中初始化的数据初始化完成后，调用回调方法
    private static Handler threadHandle = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if (msg.what == WHICH_MSG) {
                callback.onSuccess();
            }
        }

        ;
    };

    /**
     * 如果调用了initDataFromThread()，则当数据初始化完成后将回调该方法。
     */
    protected void threadDataInited() {
    }

    /**
     * 在线程中初始化数据，注意不能在这里执行UI操作
     */
    @Override
    public void initDataFromThread() {
        callback = new ThreadDataCallBack() {
            @Override
            public void onSuccess() {
                threadDataInited();
            }
        };
    }

    @Override
    public void initData() {
    }

    @Override
    public void initWidget() {
    }

    // 仅仅是为了代码整洁点
    private void initializer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                initDataFromThread();
                threadHandle.sendEmptyMessage(WHICH_MSG);
            }
        }).start();
        initData();
        initWidget();
        initBottomNavagation();
    }

    /**
     * listened widget's click method
     */
    @Override
    public void widgetClick(View v) {
    }

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    @Override
    public void registerBroadcast() {
    }

    @Override
    public void unRegisterBroadcast() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (mActionBarMode) {
            case NOTITLE:
                requestWindowFeature(Window.FEATURE_NO_TITLE);
                break;
            case CUSTOMTITILE:
                requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
                break;
        }
        setRootView(); // 必须放在annotate之前调用
        switch (mActionBarMode) {
            case CUSTOMTITILE:
                if (TITLE_RES_ID != 0) {
                    getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                            TITLE_RES_ID);
                }
                break;
        }
        AnnotateUtil.initBindView(this);
        initializer();
        registerBroadcast();
    }

    public void setTitileResId(int resId) {
        TITLE_RES_ID = resId;
    }

    public void setRootViewResId(int resId) {
        ROOT_RES_ID = resId;
    }

    public abstract void initBottomNavagation();

    @Override
    public void setRootView() {
        setContentView(R.layout.view_root);
        this.mInflater = LayoutInflater.from(this);
        if (ROOT_RES_ID != 0) {
            ScrollView bodyView = (ScrollView) findViewById(R.id.scrollview_body);
            LinearLayout bodyView2 = (LinearLayout) findViewById(R.id.linearlayout_body);
            switch (mBottomNavigation) {

                case ALL :
                    setContentView(ROOT_RES_ID);
                    break;
                case NOBOTTOMSCROLL:
                    bodyView.setVisibility(View.GONE);
                    bodyView2.setVisibility(View.VISIBLE);
                    contentView = mInflater.inflate(ROOT_RES_ID, bodyView2);
                    findViewById(R.id.root_navigation).setVisibility(View.GONE);
                    break;
                case HOME:
                    bodyView.setVisibility(View.VISIBLE);
                    bodyView2.setVisibility(View.GONE);
                    contentView = mInflater.inflate(ROOT_RES_ID, bodyView);
                    findViewById(R.id.root_navigation).setVisibility(View.VISIBLE);
                    break;
                case JUSTNOSCROLL:
                    bodyView.setVisibility(View.GONE);
                    bodyView2.setVisibility(View.VISIBLE);
                    contentView = mInflater.inflate(ROOT_RES_ID, bodyView2);
                    findViewById(R.id.root_navigation).setVisibility(View.VISIBLE);
                    break;
                case JUSTNOBOTTOM:
                    bodyView.setVisibility(View.VISIBLE);
                    bodyView2.setVisibility(View.GONE);
                    contentView = mInflater.inflate(ROOT_RES_ID, bodyView);
                    findViewById(R.id.root_navigation).setVisibility(View.GONE);
                    break;

            }
        }
    }

    @Override
    protected void onDestroy() {
        unRegisterBroadcast();
        super.onDestroy();
    }

    /**
     * 用Fragment替换视图
     *
     * @param resView        将要被替换掉的视图
     * @param targetFragment 用来替换的Fragment
     */
    public void changeFragment(int resView, KJFragment targetFragment) {
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        transaction.replace(resView, targetFragment, targetFragment.getClass()
                .getName());
        transaction.commit();
    }

    /**
     * 用Fragment替换视图
     *
     * @param resView        将要被替换掉的视图
     * @param targetFragment 用来替换的Fragment
     */
    public void changeFragment(int resView, SupportFragment targetFragment) {
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(resView, targetFragment, targetFragment.getClass()
                .getName());
        transaction.commit();
    }

}
