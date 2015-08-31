package com.sales.realestate.android.demo.analyse;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.CustomInfo;
import com.sales.realestate.android.bean.CustomJson;
import com.sales.realestate.android.bean.SpinnerItemInfo;
import com.sales.realestate.android.demo.MainActivity;
import com.sales.realestate.android.demo.custom.CustomDetailAty;
import com.sales.realestate.android.view.adapter.ActionBarSpinnerAdapter;
import com.sales.realestate.android.view.adapter.ListAdapterCustom;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.StringUtils;

import java.util.ArrayList;

/**
 * Created by cc on 2015/7/21.
 */
public class CustomAnalyseDetailAty extends KJActivity {
    @BindView(id = R.id.title_image_left, click = true)
    private ImageView titleImageLeft;
    @BindView(id = R.id.listview_mail)
    PullToRefreshListView mListView;
    @BindView(id = R.id.title_name)
    public TextView textViewTitle;

    @BindView(id = R.id.title_linerlayout, click = true)
    LinearLayout linearLayoutTitle;

    @BindView(id = R.id.title_center_location)
    LinearLayout linearLayoutTitleCenterLocation;

    private PopupWindow popupTitleDropDown = null;
    private ListView listViewPopupTitle;
    public ArrayList<CustomInfo> listViewCustomList = new ArrayList<CustomInfo>();
    public ArrayList<SpinnerItemInfo> listViewTitleList = new ArrayList<SpinnerItemInfo>();

    public ListAdapterCustom adapterCustomList;
    public ActionBarSpinnerAdapter adapterTitleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivityActionMode(ActionBarMode.CUSTOMTITILE);
        setTitileResId(R.layout.view_title_left_button);
        setRootViewResId(R.layout.aty_custom_analysis_detail);
        setmBottomNavigation(BottomNavigation.NOBOTTOMSCROLL);
        super.onCreate(savedInstanceState);
    }

    public int firstPoint = 0 ;

    public String customType = "0";
    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            customType = bundle.getString("customType");
            if(!StringUtils.isEmpty(customType)){
                int type =  Integer.valueOf(customType);
                type = GlobalVarible.findSpinnerIndexById(type + "", listViewTitleList);
                GlobalVarible.initTitleListFocus(listViewTitleList, -1, type);
                initTitle(type);

                if(firstPoint==0){
                    firstPoint++;
                    myPageIndex = 1;
                    HttpBusiness.getCustomList(myPageIndex + "",customType, MainActivity.BuildingID,new CustomAnalyseDetailBusiness());
                }
            }
        }
    }

    public class CustomAnalyseDetailBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_KEY_TABLE_CUSTOMLIST:
                    if (isError) {
                        toast("客户列表未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            CustomJson mCustomJson = gson.fromJson(returnStr, CustomJson.class);
                            if (myPageIndex == 1) {
                                listViewCustomList = mCustomJson.customerListSub;
                                adapterCustomList = new ListAdapterCustom(listViewCustomList);
                                adapterCustomList.adapterType = 6;
                                mListView.setAdapter(adapterCustomList);
                            } else {
                                GlobalVarible.initPageObject(listViewCustomList, mCustomJson.customerListSub, myPageIndex);
                            }
                            adapterCustomList.notifyDataSetChanged();
                        } catch (Exception e) {
                            toast("客户列表解析错误！");
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
            case R.id.title_linerlayout:
                adapterTitleList.notifyDataSetChanged();
                popupTitleDropDown.showAsDropDown(linearLayoutTitleCenterLocation, 0, -5);
                break;
            case R.id.title_image_left:
                onBackPressed();
                break;
        }
    }

    @Override
    public void initData() {
        super.initData();

        adapterCustomList = new ListAdapterCustom(listViewCustomList);
        adapterCustomList.adapterType = 6;

        listViewTitleList = GlobalVarible.getTITLE_CUSTOM_ANALYSE_LIST();
        adapterTitleList = new ActionBarSpinnerAdapter(listViewTitleList);
        GlobalVarible.initTitleListFocus(listViewTitleList, -1, 0);
    }

    public void initTitle(int pozition) {
        textViewTitle.setText(listViewTitleList.get(pozition).getName());
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initListView();
        initTitlePopup();
        initTitle(0);

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
            HttpBusiness.getCustomList(myPageIndex + "", customType, MainActivity.BuildingID, new CustomAnalyseDetailBusiness());
        }
    }
    public int myPageIndex = 1;
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
                showActivity(CustomAnalyseDetailAty.this, CustomDetailAty.class, bundle);
            }
        });
    }

    public void initTitlePopup() {
        listViewPopupTitle = new ListView(this);
        listViewPopupTitle.setDivider(null);
        listViewPopupTitle.setSelector(new ColorDrawable(Color.TRANSPARENT));
        listViewPopupTitle.setCacheColorHint(Color.TRANSPARENT);
        listViewPopupTitle.setDividerHeight(1);
        listViewPopupTitle.setVerticalScrollBarEnabled(false);
        listViewPopupTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!listViewTitleList.get(position).isCurrent) {
                    initTitle(position);
                    GlobalVarible.initTitleListFocus(listViewTitleList, -1, position);
                    customType = listViewTitleList.get(position).getId();
                    myPageIndex = 1;
                    HttpBusiness.getCustomList(myPageIndex + "",customType, MainActivity.BuildingID,new CustomAnalyseDetailBusiness());
                }
                popupTitleDropDown.dismiss();
            }
        });
        listViewPopupTitle.setAdapter(adapterTitleList);
        popupTitleDropDown = new PopupWindow(listViewPopupTitle, (int) getResources().getDimension(R.dimen.title_spinner_item_width), ActionBar.LayoutParams.WRAP_CONTENT, true);
        popupTitleDropDown.setBackgroundDrawable(new BitmapDrawable());
        popupTitleDropDown.setOutsideTouchable(true);
    }
}
