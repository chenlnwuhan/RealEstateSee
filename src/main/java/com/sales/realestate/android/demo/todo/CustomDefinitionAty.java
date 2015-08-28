package com.sales.realestate.android.demo.todo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.CustomDefineJson;
import com.sales.realestate.android.bean.CustomInfo;
import com.sales.realestate.android.demo.custom.CustomDetailAty;
import com.sales.realestate.android.view.adapter.ListAdapterCustomDefinition;
import com.sales.realestate.android.view.adapter.ListAdapterNews;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;

/**
 * Created by cc on 2015/7/24.
 */
public class CustomDefinitionAty extends KJActivity {
    @BindView(id = R.id.title_image_left, click = true)
    private ImageView titleImageLeft;
    @BindView(id = R.id.listview_mail)
    PullToRefreshListView mListView;
    @BindView(id = R.id.title_name)
    public TextView textViewTitle;
    public int firstPoint = 0 ;


    public ArrayList<CustomInfo> listViewList = new ArrayList<CustomInfo>();

    public ListAdapterCustomDefinition adapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivityActionMode(ActionBarMode.CUSTOMTITILE);
        setTitileResId(R.layout.view_title_simple);
        setRootViewResId(R.layout.aty_custom_definition);
        setmBottomNavigation(BottomNavigation.NOBOTTOMSCROLL);
        super.onCreate(savedInstanceState);
    }

    public class CustomDefineHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_KEY_CUSTOMDEFINE:
                    if (isError) {
                        toast("客户界定列表未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            if (errorMessage.equals("NoData")) {
                                return;
                            }
                            CustomDefineJson mCustomDefineJson = gson.fromJson(returnStr, CustomDefineJson.class);
                            if (myPageIndex == 1) {
                                GlobalVarible.setCustomDefineList(mCustomDefineJson.customerList);
                                listViewList = GlobalVarible.getCustomDefineList();
                                adapterList = new ListAdapterCustomDefinition(listViewList);
                                mListView.setAdapter(adapterList);
                            } else {
                                GlobalVarible.initPageObject(listViewList,mCustomDefineJson.customerList, myPageIndex);
                            }

                            adapterList.notifyDataSetChanged();
                        } catch (Exception e) {
                            toast("客户界定解析错误！");
                        }
                    }
                    break;
            }
        }
    }
    public int myPageIndex;
    @Override
    protected void onStart() {
        super.onStart();
        if(firstPoint==0){
            myPageIndex = 1;
            firstPoint++;
            HttpBusiness.getCustomDefineHTTP(myPageIndex+"", new CustomDefineHttpBusiness());
        }

    }
    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        textViewTitle.setText("客户界定");
        initListView();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
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
            HttpBusiness.getCustomDefineHTTP(myPageIndex + "", new CustomDefineHttpBusiness());

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
                if ((position - 1) >=0) position--;
                if(!listViewList.get(position).isValidate){
                    return ;
                }
                bundle.putString("customId", listViewList.get(position).CustomerID);
                showActivity(CustomDefinitionAty.this, CustomDetailAty.class, bundle);
            }
        });
    }
}
