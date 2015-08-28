package com.sales.realestate.android.demo.more;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.AddressInfo;
import com.sales.realestate.android.bean.AddressJson;
import com.sales.realestate.android.view.adapter.ListAdapterAddress;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;

/**
 * Created by cc on 2015/7/23.
 */
public class AddresListAty extends KJActivity {

    @BindView(id = R.id.title_image_left, click = true)
    private ImageView titleImageLeft;
    @BindView(id = R.id.listview_mail)
    PullToRefreshListView mListView;
    @BindView(id = R.id.title_name)
    public TextView textViewTitle;

    public ArrayList<AddressInfo> listViewCustomList = new ArrayList<AddressInfo>();
    public ListAdapterAddress adapterCustomList;

    public class AddresHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_KEY_ADDRESS:
                    if (isError) {
                        toast("通讯录信息未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            AddressJson mBuildListJson = gson.fromJson(returnStr, AddressJson.class);
                            GlobalVarible.setAddressList(mBuildListJson.BookList);
                            listViewCustomList = GlobalVarible.getADDRESS_LIST();
                            adapterCustomList = new ListAdapterAddress(listViewCustomList);
                            initListView();
                        } catch (Exception e) {
                            toast("通讯录信息解析错误！");
                        }
                    }
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivityActionMode(ActionBarMode.CUSTOMTITILE);
        setTitileResId(R.layout.view_title_simple);
        setRootViewResId(R.layout.aty_addres_list);
        setmBottomNavigation(BottomNavigation.NOBOTTOMSCROLL);
        super.onCreate(savedInstanceState);
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

    @Override
    public void initData() {
        super.initData();


    }

    @Override
    public void initWidget() {
        super.initWidget();
        textViewTitle.setText("通讯录");

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
            HttpBusiness.getAddress(new AddresHttpBusiness());

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        HttpBusiness.getAddress(new AddresHttpBusiness());
    }

    public void initListView() {
        mListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase refreshView) {
                new FinishRefresh().execute();
                adapterCustomList.notifyDataSetChanged();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase refreshView) {
                new FinishRefresh().execute();
                adapterCustomList.notifyDataSetChanged();

            }
        });

        mListView.setAdapter(adapterCustomList);
        mListView.getRefreshableView().setDividerHeight(0);
    }
}
