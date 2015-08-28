package com.sales.realestate.android.demo.todo;

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
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.ApplyJson;
import com.sales.realestate.android.bean.SpinnerItemInfo;
import com.sales.realestate.android.bean.ToDoListInfo;
import com.sales.realestate.android.demo.MainActivity;
import com.sales.realestate.android.view.adapter.ActionBarSpinnerAdapter;
import com.sales.realestate.android.view.adapter.ListAdapterTodoList;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.StringUtils;

import java.util.ArrayList;

/**
 * Created by cc on 2015/7/24.
 */
public class ToDoListDetailAty extends KJActivity {
    @BindView(id = R.id.title_image_left, click =true)
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
    public ArrayList<ToDoListInfo> listViewList = new ArrayList<ToDoListInfo>();
    public ArrayList<SpinnerItemInfo> listViewTitleList = new ArrayList<SpinnerItemInfo>();

    public ListAdapterTodoList adapterList;
    public ActionBarSpinnerAdapter adapterTitleList;
    public static String titleName = "";

    public int currentPosition;

    public class ToDoListDetailHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_KEY_DEFINETION_LIST:
                case HttpBusiness.HTTP_KEY_APPLYLIST:
                    if (isError) {
                        toast(textViewTitle.getText().toString() + "列表未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            ApplyJson mApplyJson = new ApplyJson();
                            if (!errorMessage.equals("NoData")) {
                                mApplyJson = gson.fromJson(returnStr, ApplyJson.class);
                            }

                            if (myPageIndex == 1) {
                                if((Integer.valueOf(ApplyType)-1)<0){
                                    listViewList = mApplyJson.CustomerList;
                                }else{
                                    listViewList = mApplyJson.applyInfoList;
                                }
                                adapterList = new ListAdapterTodoList(listViewList);
                                mListView.setAdapter(adapterList);
                            } else {
                                if((Integer.valueOf(ApplyType)-1)<0){
                                    GlobalVarible.initPageObject(listViewList, mApplyJson.CustomerList, myPageIndex);
                                }else{
                                    GlobalVarible.initPageObject(listViewList, mApplyJson.applyInfoList, myPageIndex);

                                }
                            }
                            adapterList.notifyDataSetChanged();
                        } catch (Exception e) {
                            toast(textViewTitle.getText().toString() + "解析错误！");
                        }
                    }
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivityActionMode(ActionBarMode.CUSTOMTITILE);
        setTitileResId(R.layout.view_title_left_button);
        setRootViewResId(R.layout.aty_todolist_detail);
        setmBottomNavigation(BottomNavigation.NOBOTTOMSCROLL);
        super.onCreate(savedInstanceState);
    }

    public String ApplyType = "0";
    public int applyIndex = 0;
    public int applyType = 0;

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            ApplyType = bundle.getString("ApplyType");
            if (!StringUtils.isEmpty(ApplyType)) {
                int type = Integer.valueOf(ApplyType)-1;
                applyType = type / 4;
                applyIndex = type % 4 + applyType;
                type = GlobalVarible.findSpinnerIndexById(ApplyType,listViewTitleList);
                initTitle(type);
                GlobalVarible.initTitleListFocus(listViewTitleList, -1, type);
                adapterTitleList.notifyDataSetChanged();
                if (firstPoint != -1) {
                    myPageIndex=1;
                    firstPoint++;
                    if((Integer.valueOf(ApplyType)-1)<0){

                        HttpBusiness.getCustomDefineListHTTP(myPageIndex + "", new ToDoListDetailHttpBusiness());
                    }else{
                        HttpBusiness.getApplyHttp(myPageIndex + "", applyIndex+"", applyType+"", new ToDoListDetailHttpBusiness());
                    }
                }
            }
        }
    }

    @Override
    public void initData() {
        super.initData();
        /**
         * 这个控件不初始化会造成间隔堆积到顶部
         */
        adapterList = new ListAdapterTodoList(listViewList);
        listViewTitleList = GlobalVarible.getTITLE_TODO_LIST();
        adapterTitleList = new ActionBarSpinnerAdapter(listViewTitleList);
        GlobalVarible.initTitleListFocus(listViewTitleList, -1, 0);

    }

    public void initTitle(int pozition) {
        currentPosition = Integer.valueOf(listViewTitleList.get(pozition).getId());;
        textViewTitle.setText(listViewTitleList.get(pozition).getName());
        titleName = listViewTitleList.get(pozition).getName();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initListView();
        initTitlePopup();
        initTitle(0);

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
            int type = Integer.valueOf(ApplyType)-1;
            if(type<0){
                HttpBusiness.getCustomDefineListHTTP(myPageIndex + "", new ToDoListDetailHttpBusiness());
            }else{
                HttpBusiness.getApplyHttp(myPageIndex + "", applyIndex+"", applyType+"", new ToDoListDetailHttpBusiness());
            }
        }
    }

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
                myPageIndex = GlobalVarible.getPageIndex(listViewList);
                new FinishRefresh().execute();

            }
        });

        mListView.setAdapter(adapterList);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                if ((position - 1) >= 0) position--;
                String  checkPoint1 = listViewList.get(position).IsExamine;
                if (!StringUtils.isEmpty(checkPoint1)){
                    if(!listViewList.get(position).IsExamine.equals("未审核")){
                        return ;
                    }
                }else{
                    if(listViewList.get(position).CustomerValid.equals("已处理")){
                        return ;
                    }
                }

                bundle.putString("BuildingID", MainActivity.BuildingID);
                bundle.putString("CustomerID", listViewList.get(position).CustomerID);
                bundle.putString("ApplyID", listViewList.get(position).ApplyID);
                if (currentPosition == 0) {
                    ToDoListDetailAty.this.showActivity(ToDoListDetailAty.this, ConfirmCustomDefinitionAty.class, bundle);
                }
                if (currentPosition == 1) {
                    ToDoListDetailAty.this.showActivity(ToDoListDetailAty.this, ConfirmCustomSeeAty.class, bundle);
                }
                if (currentPosition == 2) {
                    ToDoListDetailAty.this.showActivity(ToDoListDetailAty.this, ConfirmCustomFormAty.class, bundle);
                }
                if (currentPosition == 3) {
                    ToDoListDetailAty.this.showActivity(ToDoListDetailAty.this, ConfirmCustomBuyAty.class, bundle);
                }
                if (currentPosition == 4) {
                    ToDoListDetailAty.this.showActivity(ToDoListDetailAty.this, ConfirmCustomDoneAty.class, bundle);
                }
                if (currentPosition == 5) {
                    ToDoListDetailAty.this.showActivity(ToDoListDetailAty.this, ConfirmCustomFormAty.class, bundle);
                }
                if (currentPosition == 6) {
                    ToDoListDetailAty.this.showActivity(ToDoListDetailAty.this, ConfirmCustomBuyAty.class, bundle);
                }
                if (currentPosition == 7) {
                    ToDoListDetailAty.this.showActivity(ToDoListDetailAty.this, ConfirmCustomDoneAty.class, bundle);
                }
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
                    int position1 = Integer.valueOf(listViewTitleList.get(position).getId());
                    ApplyType = position1 + "";
                    position1--;
                    adapterList.notifyDataSetChanged();
                    myPageIndex = 1;

                    applyType = position1 / 4;
                    applyIndex = position1 % 4 + applyType;
                    myPageIndex = 1;
                    if(position1<0){
                        HttpBusiness.getCustomDefineListHTTP(myPageIndex + "", new ToDoListDetailHttpBusiness());

                    }else{
                        HttpBusiness.getApplyHttp(myPageIndex + "", applyIndex + "", applyType + "", new ToDoListDetailHttpBusiness());

                    }
                }
                popupTitleDropDown.dismiss();
            }
        });
        listViewPopupTitle.setAdapter(adapterTitleList);
        popupTitleDropDown = new PopupWindow(listViewPopupTitle, (int) getResources().getDimension(R.dimen.title_spinner_item_width), ActionBar.LayoutParams.WRAP_CONTENT, true);
        popupTitleDropDown.setBackgroundDrawable(new BitmapDrawable());
        popupTitleDropDown.setOutsideTouchable(true);
    }
    public int firstPoint = 0;
    public int myPageIndex = 1;
}
