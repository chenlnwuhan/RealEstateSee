package com.sales.realestate.android.view.popupwindow;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.sales.realestate.android.AppContext;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.CustomInfo;
import com.sales.realestate.android.bean.MailJson;
import com.sales.realestate.android.bean.PropertyConsultant;
import com.sales.realestate.android.demo.custom.CustomAty;
import com.sales.realestate.android.ifs.GroupViewIFS;
import com.sales.realestate.android.view.adapter.GridViewAdapterCustom;
import com.sales.realestate.android.view.adapter.ListAdapterMail;

import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;

/**
 * Created by cc on 2015/7/23.
 */
public class CustomDistributionPW extends PopupWindow {
    private View conentView;

    public int selectPoint ;
    public TextView textview_popup_distributename;
    public ArrayList<CustomInfo> selectCustomList = new ArrayList<CustomInfo>();
    public ArrayList<PropertyConsultant> mPropertyConsultantList = new ArrayList<PropertyConsultant>();
    public PullToRefreshGridView gridview_custom;
    public GridViewAdapterCustom mGridViewAdapterCustom;
    public  ArrayList<String> selectCustomIds = new ArrayList<String>();
    public String idList ="";
    TextView textview_custom_distribution_cancel ;
    TextView textview_custom_distribution_ok ;

    public CustomDistributionPW(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popup_custom_distribution, null);
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);

        initPopUpWindows();
        initView();

        // 刷新状态
        this.update();
    }
    public class CustomDistributeHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_KEY_CUSTOM_DISTRIBUTE:
                    if (isError) {
                        AppContext.getCurrentActivity().toast("客户分配失败！");
                    } else {
                        try {
                            AppContext.getCurrentActivity().toast("客户分配成功！");
                            textview_custom_distribution_cancel.setClickable(false);
                            textview_custom_distribution_ok.setClickable(false);
                            mHandler.postDelayed(mRunnable, GlobalVarible.GLOBALDELAY);
                        } catch (Exception e) {
                            AppContext.getCurrentActivity().toast("客户分配结果解析错误！");
                        }
                    }
                    break;
            }
        }
    }

    public Handler mHandler = new Handler();

    public Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            CustomDistributionPW.this.dismiss();
        }
    };


    public void initPopUpWindows(){
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);

        //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
        this.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    public void refresh(){
        selectPoint = -1 ;
        idList = "";
        selectCustomList = new ArrayList<CustomInfo>();
        mGridViewAdapterCustom.setAllClear();
        mGridViewAdapterCustom.notifyDataSetChanged();
        String userName = "";
        for(int i =0 ;i< selectCustomIds.size();i++){
            CustomInfo mCustomInfo = GlobalVarible.findCustomByCustomIdInCustomAnalyseList(selectCustomIds.get(i));
            if(mCustomInfo!=null){
                selectCustomList.add(mCustomInfo);
                userName += ","+mCustomInfo.CustomerName ;
                idList += ","+mCustomInfo.CustomerID ;
            }
        }
        if(selectCustomList.size()==0){
            AppContext.getCurrentActivity().toast("没有选择客户！");
        }else{
            userName = userName.substring(1);
            idList  = idList.substring(1);
            textview_popup_distributename.setText(userName);
        }
        textview_custom_distribution_cancel.setClickable(true);
        textview_custom_distribution_ok.setClickable(true);
    }
    public void initView(){
        gridview_custom = (PullToRefreshGridView) conentView.findViewById(R.id.gridview_custom);
        textview_popup_distributename = (TextView) conentView.findViewById(R.id.textview_popup_distributename);

        mPropertyConsultantList = GlobalVarible.getPropertyConsultantList();
        mGridViewAdapterCustom = new GridViewAdapterCustom(mPropertyConsultantList);
        mGridViewAdapterCustom.mGroupViewIFS = new GroupViewIFS() {
            @Override
            public void itemOnCheck(int Position) {
                selectPoint = Position ;
            }

            @Override
            public void itemUnCheck(int Position) {
                selectPoint = -1 ;
            }
        };
        gridview_custom.setMode(PullToRefreshBase.Mode.DISABLED);
        gridview_custom.setAdapter(mGridViewAdapterCustom);
        gridview_custom.getRefreshableView().setNumColumns(4);
        gridview_custom.getRefreshableView().setVerticalSpacing(10);
        gridview_custom.getRefreshableView().setHorizontalSpacing(10);
         textview_custom_distribution_cancel = (TextView) conentView.findViewById(R.id.textview_custom_distribution_cancel);
         textview_custom_distribution_ok = (TextView) conentView.findViewById(R.id.textview_custom_distribution_ok);
        textview_custom_distribution_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDistributionPW.this.dismiss();
            }
        });
        textview_custom_distribution_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectPoint<0){
                    AppContext.getCurrentActivity().toast("请选择置业顾问！");
                }else{
                    HttpBusiness.getCustomDistribute(mPropertyConsultantList.get(selectPoint).UserID,CustomAty.BuildingID,idList,new CustomDistributeHttpBusiness());
                }
            }
        });
    }
}
