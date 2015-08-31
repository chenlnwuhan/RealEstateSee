package com.sales.realestate.android.demo.more;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sales.realestate.android.CommomKey;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.AddressJson;
import com.sales.realestate.android.bean.NewsInfo;
import com.sales.realestate.android.bean.VesrionJson;
import com.sales.realestate.android.view.adapter.ListAdapterAddress;
import com.sales.realestate.android.view.popupwindow.CustomFormPW;
import com.sales.realestate.android.view.popupwindow.MoreCheckupPW;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.StringUtils;

/**
 * Created by chenlin on 2015/7/20.
 */
public class MoreAboutAty extends KJActivity {

    @BindView(id = R.id.title_image_left, click = true)
    private ImageView titleImageLeft;
    @BindView(id = R.id.imageview_checkupdate, click = true)
    private ImageView imageview_checkupdate;

    public VesrionJson mBuildListJson ;
    public MoreCheckupPW mMoreCheckupPW;
    @BindView(id = R.id.title_name)
    public TextView textViewTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivityActionMode(ActionBarMode.CUSTOMTITILE);
        setTitileResId(R.layout.view_title_simple);
        setRootViewResId(R.layout.aty_more_about);
        setmBottomNavigation(BottomNavigation.NOBOTTOMSCROLL);
        super.onCreate(savedInstanceState);
    }

    public class MoreAboutAtyBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_CHECKVERSION:
                    if (isError) {
                        toast("检查版本错误！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            if (errorMessage.equals("NoData")||errorMessage.equals("LatestVersion")) {

                            }else{
                                mBuildListJson = gson.fromJson(returnStr, VesrionJson.class);
                                GlobalVarible.setVSERION(mBuildListJson);
                            }
                            if (mMoreCheckupPW == null) {
                                mMoreCheckupPW = new MoreCheckupPW(MoreAboutAty.this);
                                mMoreCheckupPW.setBuyType(1);
                                mMoreCheckupPW.setOnDismissListener(new PopupWindow.OnDismissListener() {
                                    @Override
                                    public void onDismiss() {
                                    }
                                });
                            }
                            mMoreCheckupPW.setmBuildListJson(mBuildListJson);
                            mMoreCheckupPW.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                        } catch (Exception e) {
                            toast("检查版本解析错误！");
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
            case R.id.imageview_checkupdate:
                HttpBusiness.checkVersion(GlobalVarible.VERSION_NAME, new MoreAboutAtyBusiness());
                break;
        }
    }

    @Override
    public void initData() {
        super.initData();
    }

    public void initTitle(int pozition) {
        textViewTitle.setText("关于");
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle(0);
    }


}
