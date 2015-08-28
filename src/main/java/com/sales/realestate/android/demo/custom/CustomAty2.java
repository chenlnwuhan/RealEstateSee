package com.sales.realestate.android.demo.custom;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.sales.realestate.android.CommomKey;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.CustomInfo;
import com.sales.realestate.android.bean.CustomJson;
import com.sales.realestate.android.ifs.GroupViewIFS;
import com.sales.realestate.android.view.adapter.ListAdapterCustom;
import com.sales.realestate.android.view.popupwindow.CustomBuyPW;
import com.sales.realestate.android.view.popupwindow.CustomDistributionPW;
import com.sales.realestate.android.view.popupwindow.CustomDonePW;
import com.sales.realestate.android.view.popupwindow.CustomFormPW;
import com.sales.realestate.android.view.popupwindow.CustomSeePW;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;

/**
 * Created by chenlin on 2015/7/20.
 */
public class CustomAty2 extends KJActivity {
    @BindView(id = R.id.title_image_left, click = true)
    private ImageView title_image_left;
    @BindView(id = R.id.title_name)
    public TextView textViewTitle;


    @BindView(id = R.id.textview_custom_selectnum)
    public TextView textview_custom_selectnum;

    @BindView(id = R.id.listview_mail)
    PullToRefreshListView mListView;


    public ArrayList<CustomInfo> listViewCustomList = new ArrayList<CustomInfo>();
    public ListAdapterCustom adapterCustomList;

    public CustomSeePW mCustomSeePW;
    public CustomDistributionPW mCustomDistributionPW;
    public CustomFormPW mCustomFormPW;
    public CustomBuyPW mCustomBuyPW;
    public CustomDonePW mCustomDonePW;

    @BindView(id = R.id.textview_custom_first, click = true)
    public TextView textview_custom_first;
    @BindView(id = R.id.textview_custom_second, click = true)
    public TextView textview_custom_second;
    @BindView(id = R.id.textview_custom_third, click = true)
    public TextView textview_custom_third;
    @BindView(id = R.id.textview_custom_fourth, click = true)
    public TextView textview_custom_fourth;

    @BindView(id = R.id.textview_musthiden)
    public TextView textview_musthiden;

    @BindView(id = R.id.view_musthiden)
    public View view_musthiden;

    public static String BuildingID;
    public static int titleIndex = 0;
    public static String propertyId = "0";
    public int myPageIndex;

    public ArrayList<String> selectCustomIds = new ArrayList<String>();
    public static ArrayList<Integer> selectCustomIds2 = new ArrayList<Integer>();

    /**
     * 1 成交
     * 2 认购
     * 3 认筹
     * 4 分配
     */
    public int popupType;

    public GroupViewIFS mGroupViewIFS = new GroupViewIFS() {
        @Override
        public void itemOnCheck(int Position) {
            if (selectCustomIds2.contains(Position)) {
                return;
            }
            selectCustomIds2.add(Position);
            String checkId = listViewCustomList.get(Position).CustomerID;

            selectCustomIds.add(checkId);
            if (selectCustomIds2.size() > 1) {
                textview_custom_first.setEnabled(false);
                textview_custom_second.setEnabled(false);
                textview_custom_third.setEnabled(false);
                if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_DIRECTOR)) {
                    textview_custom_fourth.setEnabled(true);
                } else if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_MANAGER)) {
                    textview_custom_fourth.setEnabled(true);
                } else if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_PROPERTY)) {
                    textview_custom_fourth.setEnabled(false);

                }
            }else{
                CustomInfo mCustomInfo = listViewCustomList.get(Position);
                if(mCustomInfo.CustomerStatus.equals("1")){
                    textview_custom_first.setEnabled(false);
                    textview_custom_second.setEnabled(false);
                    textview_custom_third.setEnabled(false);
                    textview_custom_fourth.setEnabled(true);
                }else if(mCustomInfo.CustomerStatus.equals("2")){
                    textview_custom_first.setEnabled(true);
                    textview_custom_second.setEnabled(true);
                    textview_custom_third.setEnabled(true);
                    if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_DIRECTOR)) {
                        textview_custom_fourth.setEnabled(true);
                    } else if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_MANAGER)) {
                        textview_custom_fourth.setEnabled(true);
                    } else if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_PROPERTY)) {
                        textview_custom_fourth.setEnabled(false);
                    }
                }else if(mCustomInfo.CustomerStatus.equals("3")){
                    textview_custom_first.setEnabled(true);
                    textview_custom_second.setEnabled(true);
                    textview_custom_third.setEnabled(false);
                    if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_DIRECTOR)) {
                        textview_custom_fourth.setEnabled(true);
                    } else if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_MANAGER)) {
                        textview_custom_fourth.setEnabled(true);
                    } else if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_PROPERTY)) {
                        textview_custom_fourth.setEnabled(false);
                    }
                }else if(mCustomInfo.CustomerStatus.equals("4")){
                    textview_custom_first.setEnabled(true);
                    textview_custom_second.setEnabled(false);
                    textview_custom_third.setEnabled(false);
                    if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_DIRECTOR)) {
                        textview_custom_fourth.setEnabled(true);
                    } else if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_MANAGER)) {
                        textview_custom_fourth.setEnabled(true);
                    } else if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_PROPERTY)) {
                        textview_custom_fourth.setEnabled(false);
                    }
                }else if(mCustomInfo.CustomerStatus.equals("5")){
                    textview_custom_first.setEnabled(false);
                    textview_custom_second.setEnabled(false);
                    textview_custom_third.setEnabled(false);
                    if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_DIRECTOR)) {
                        textview_custom_fourth.setEnabled(false);
                    } else if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_MANAGER)) {
                        textview_custom_fourth.setEnabled(false);
                    } else if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_PROPERTY)) {
                        textview_custom_fourth.setEnabled(false);
                    }
                }
            }
            textview_custom_selectnum.setText("已选:" + selectCustomIds.size() + "人");

        }

        @Override
        public void itemUnCheck(int Position) {
            String unCheckId = listViewCustomList.get(Position).CustomerID;
            for (int i = 0; i < selectCustomIds.size(); i++) {
                if (unCheckId.equals(selectCustomIds.get(i))) {
                    selectCustomIds.remove(i);
                    selectCustomIds2.remove(i);
                    i--;
                }
            }
            if(selectCustomIds2.size()==1){
                CustomInfo mCustomInfo = listViewCustomList.get(Position);
                if(mCustomInfo.CustomerStatus.equals("1")){
                    textview_custom_first.setEnabled(false);
                    textview_custom_second.setEnabled(false);
                    textview_custom_third.setEnabled(false);
                    textview_custom_fourth.setEnabled(true);
                }else if(mCustomInfo.CustomerStatus.equals("2")){
                    textview_custom_first.setEnabled(true);
                    textview_custom_second.setEnabled(true);
                    textview_custom_third.setEnabled(true);
                    if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_DIRECTOR)) {
                        textview_custom_fourth.setEnabled(true);
                    } else if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_MANAGER)) {
                        textview_custom_fourth.setEnabled(true);
                    } else if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_PROPERTY)) {
                        textview_custom_fourth.setEnabled(false);
                    }
                }else if(mCustomInfo.CustomerStatus.equals("3")){
                    textview_custom_first.setEnabled(true);
                    textview_custom_second.setEnabled(true);
                    textview_custom_third.setEnabled(false);
                    if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_DIRECTOR)) {
                        textview_custom_fourth.setEnabled(true);
                    } else if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_MANAGER)) {
                        textview_custom_fourth.setEnabled(true);
                    } else if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_PROPERTY)) {
                        textview_custom_fourth.setEnabled(false);
                    }
                }else if(mCustomInfo.CustomerStatus.equals("4")){
                    textview_custom_first.setEnabled(true);
                    textview_custom_second.setEnabled(false);
                    textview_custom_third.setEnabled(false);
                    if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_DIRECTOR)) {
                        textview_custom_fourth.setEnabled(true);
                    } else if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_MANAGER)) {
                        textview_custom_fourth.setEnabled(true);
                    } else if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_PROPERTY)) {
                        textview_custom_fourth.setEnabled(false);
                    }
                }else if(mCustomInfo.CustomerStatus.equals("5")){
                    textview_custom_first.setEnabled(false);
                    textview_custom_second.setEnabled(false);
                    textview_custom_third.setEnabled(false);
                    if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_DIRECTOR)) {
                        textview_custom_fourth.setEnabled(false);
                    } else if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_MANAGER)) {
                        textview_custom_fourth.setEnabled(false);
                    } else if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_PROPERTY)) {
                        textview_custom_fourth.setEnabled(false);
                    }
                }
            }else if(selectCustomIds2.size()==0){
                textview_custom_first.setEnabled(true);
                textview_custom_second.setEnabled(true);
                textview_custom_third.setEnabled(true);
                textview_custom_fourth.setEnabled(true);
            }
            textview_custom_selectnum.setText("已选:" + selectCustomIds.size() + "人");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivityActionMode(ActionBarMode.CUSTOMTITILE);
        setTitileResId(R.layout.view_title_simple);
        setRootViewResId(R.layout.aty_custom);
        setmBottomNavigation(BottomNavigation.NOBOTTOMSCROLL);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initWidget() {
        super.initWidget();

        initListView();
        initTitle(titleIndex);
        initView();
    }

    public void initView() {
        if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_DIRECTOR)) {
            title_image_left.setVisibility(View.VISIBLE);
            textview_custom_first.setVisibility(View.VISIBLE);
            textview_custom_second.setVisibility(View.VISIBLE);
            textview_custom_third.setVisibility(View.VISIBLE);
            textview_custom_fourth.setVisibility(View.VISIBLE);
        } else if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_MANAGER)) {
            title_image_left.setVisibility(View.GONE);
            textview_custom_first.setVisibility(View.VISIBLE);
            textview_custom_second.setVisibility(View.VISIBLE);
            textview_custom_third.setVisibility(View.VISIBLE);
            textview_custom_fourth.setVisibility(View.VISIBLE);
            textview_custom_first.setText("申请\n成交");
            textview_custom_second.setText("申请\n认购");
        } else if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_PROPERTY)) {
            title_image_left.setVisibility(View.VISIBLE);
            textview_custom_first.setVisibility(View.VISIBLE);
            textview_custom_second.setVisibility(View.VISIBLE);
            textview_custom_third.setVisibility(View.VISIBLE);
            textview_custom_fourth.setVisibility(View.VISIBLE);

            textview_custom_first.setText("申请\n成交");
            textview_custom_second.setText("申请\n认购");
            textview_custom_third.setText("申请\n认筹");
            textview_custom_fourth.setText("确认\n带看");
        }
    }

    @Override
    public void initData() {
        super.initData();

        if (GlobalVarible.ROLE_ID.equals("3")) {
            propertyId = GlobalVarible.USER_ID;
        }
        selectCustomIds = new ArrayList<String>();
        selectCustomIds2 = new ArrayList<Integer>();
        textview_custom_first.setEnabled(true);
        textview_custom_second.setEnabled(true);
        textview_custom_third.setEnabled(true);
        textview_custom_fourth.setEnabled(true);

    }

    public void showPopUpWindow(int type) {
        if (type == 4 && selectCustomIds.size() == 0) {
            toast("请选择客户！");
            return;
        } else if (type != 4 && selectCustomIds.size() != 1) {
            toast("请只选择1个客户！");
            return;
        }
        switch (type) {
            case 4:
                if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_PROPERTY)) {
                    if (mCustomSeePW == null) {
                        mCustomSeePW = new CustomSeePW(this);
                        mCustomSeePW.setBuyType(1);
                        mCustomSeePW.setOnDismissListener(new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                            }
                        });
                    }
                    mCustomSeePW.selectCustomIds = selectCustomIds;
                    mCustomSeePW.refresh();
                    mCustomSeePW.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                } else {
                    if (mCustomDistributionPW == null) {
                        mCustomDistributionPW = new CustomDistributionPW(this);
                        mCustomDistributionPW.setOnDismissListener(new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                            }
                        });
                    }
                    mCustomDistributionPW.selectCustomIds = selectCustomIds;
                    mCustomDistributionPW.refresh();
                    mCustomDistributionPW.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                }

                break;
            case 3:
                if (mCustomFormPW == null) {
                    mCustomFormPW = new CustomFormPW(this);
                    mCustomFormPW.setBuyType(1);
                    if (!GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_PROPERTY)) {
                        mCustomFormPW.setBuyType(2);
                    }
                    mCustomFormPW.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {

                        }
                    });
                }
                mCustomFormPW.selectCustomIds = selectCustomIds;
                mCustomFormPW.refresh();
                mCustomFormPW.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                break;
            case 2:
                if (mCustomBuyPW == null) {
                    mCustomBuyPW = new CustomBuyPW(this);
                    mCustomBuyPW.setBuyType(1);
                    if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_DIRECTOR)) {
                        mCustomBuyPW.setBuyType(2);
                    }
                    mCustomBuyPW.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {

                        }
                    });
                }
                mCustomBuyPW.selectCustomIds = selectCustomIds;
                mCustomBuyPW.refresh();
                mCustomBuyPW.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                break;
            case 1:
                if (mCustomDonePW == null) {
                    mCustomDonePW = new CustomDonePW(this);
                    mCustomDonePW.setBuyType(1);
                    if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_DIRECTOR)) {
                        mCustomDonePW.setBuyType(2);
                    }
                    mCustomDonePW.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {

                        }
                    });
                }
                mCustomDonePW.selectCustomIds = selectCustomIds;
                mCustomDonePW.refresh();
                mCustomDonePW.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                break;

        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.textview_custom_first:
                showPopUpWindow(1);
                break;
            case R.id.textview_custom_second:
                showPopUpWindow(2);
                break;
            case R.id.textview_custom_third:
                showPopUpWindow(3);
                break;
            case R.id.textview_custom_fourth:
                showPopUpWindow(4);
                break;
            case R.id.title_image_left:
                onBackPressed();
                break;
        }
    }

    public class CustomSearchResultHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_KEY_TABLE_CUSTOMSEARCH:
                    if (isError) {
                        toast("客户列表未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            CustomJson mCustomJson = gson.fromJson(returnStr, CustomJson.class);
                            if (myPageIndex == 1) {
                                listViewCustomList = mCustomJson.customList;
                                GlobalVarible.setCustomAnalyseList(listViewCustomList);
                                adapterCustomList = new ListAdapterCustom(listViewCustomList);
                                adapterCustomList.adapterType = 0;

                                if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_PROPERTY)) {
                                    adapterCustomList.adapterType = 9;

                                }
                                adapterCustomList.mGroupViewIFS = mGroupViewIFS;
                                mListView.setAdapter(adapterCustomList);
                                selectCustomIds = new ArrayList<String>();
                                textview_custom_selectnum.setText("已选:" + selectCustomIds.size() + "人");
                                selectCustomIds2 = new ArrayList<Integer>();
                                textview_custom_first.setEnabled(true);
                                textview_custom_second.setEnabled(true);
                                textview_custom_third.setEnabled(true);
                                textview_custom_fourth.setEnabled(true);
                            } else {
                                GlobalVarible.initPageObject(listViewCustomList, mCustomJson.customList, myPageIndex);
                            }
                            adapterCustomList.notifyDataSetChanged();
                            initTitle("(" + mCustomJson.totalCount + ")");
                        } catch (Exception e) {
                            toast("客户列表解析错误！");
                        }
                    }
                    break;
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            customName = bundle.getString("customName");
            customPhone = bundle.getString("customPhone");
            buildId = bundle.getString("buildId");
            dicdid = bundle.getString("dicdid");
            customType = bundle.getString("customType");
            levelId = bundle.getString("levelId");
            propertyId = bundle.getString("propertyId");
            if (GlobalVarible.ROLE_ID.equals("3")) {
                propertyId = GlobalVarible.USER_ID;
            }
            if (customType.equals("0")) {
                customType = "";
            }
            selectCustomIds = new ArrayList<String>();
            myPageIndex = 1;
            if (firstPoint != -1) {
                myPageIndex = 1;
                firstPoint++;
                HttpBusiness.getCustomSearch(myPageIndex + "", propertyId, customType, buildId, customPhone, customName, levelId, dicdid, new CustomSearchResultHttpBusiness());
            }
            if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_PROPERTY)) {
                textview_musthiden.setVisibility(View.GONE);
                view_musthiden.setVisibility(View.GONE);
            }
        }

    }

    public int firstPoint = 0;

    public void initTitle(int pozition) {
        textViewTitle.setText("搜索结果");
    }

    public void initTitle(String add) {
        int t = textViewTitle.getText().toString().indexOf("(");
        if (t > 0) {
            textViewTitle.setText(textViewTitle.getText().toString().substring(0, t));
        }
        textViewTitle.setText(textViewTitle.getText().toString() + add);
    }

    private class FinishRefresh extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mListView.onRefreshComplete();
            HttpBusiness.getCustomSearch(myPageIndex + "", propertyId, customType, buildId, customPhone, customName, levelId, dicdid, new CustomSearchResultHttpBusiness());

        }
    }

    String customName = "";
    String customPhone = "";
    String buildId = "";
    String dicdid = "";
    String customType = "";
    String levelId = "";

    public void initListView() {
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase refreshView) {
                myPageIndex = 1;
                new FinishRefresh().execute();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase refreshView) {
                myPageIndex = GlobalVarible.getPageIndex(listViewCustomList);
                new FinishRefresh().execute();

            }
        });

        mListView.setAdapter(adapterCustomList);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                if ((position - 1) >= 0) position--;
                bundle.putString("customId", listViewCustomList.get(position).CustomerID);
                showActivity(CustomAty2.this, CustomDetailAty.class, bundle);
            }
        });
    }

}
