package com.sales.realestate.android.demo.more;

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
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.UrlApiConfig;
import com.sales.realestate.android.bean.BuildJson;
import com.sales.realestate.android.bean.MailInfo;
import com.sales.realestate.android.bean.NewsInfo;
import com.sales.realestate.android.bean.NewsJson;
import com.sales.realestate.android.bean.SpinnerItemInfo;
import com.sales.realestate.android.demo.mail.MailDetailAty;
import com.sales.realestate.android.view.adapter.ActionBarSpinnerAdapter;
import com.sales.realestate.android.view.adapter.ListAdapterCustom;
import com.sales.realestate.android.view.adapter.ListAdapterMail;
import com.sales.realestate.android.view.adapter.ListAdapterNews;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.StringUtils;

import java.util.ArrayList;

/**
 * Created by chenlin on 2015/7/20.
 */
public class PromotionsNewAty extends KJActivity {

    @BindView(id = R.id.title_image_left, click = true)
    private ImageView titleImageLeft;
    @BindView(id = R.id.listview_mail)
    PullToRefreshListView mListView;
    @BindView(id = R.id.title_name)
    public TextView textViewTitle;

    public String newsType ;
    public int myPageIndex;
    public class NewsHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_KEY_NOTICE:
                    if (isError) {
                        toast(textViewTitle.getText().toString()+"未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            if (errorMessage.equals("NoData")) {
                                return;
                            }
                            NewsJson mNewsJson = gson.fromJson(returnStr, NewsJson.class);
                            if (myPageIndex == 1) {
                                GlobalVarible.setNewsList(mNewsJson.Newslist);
                                listViewMailList = GlobalVarible.getNewsList();
                                adapterMailList = new ListAdapterNews(listViewMailList);
                                mListView.setAdapter(adapterMailList);
                            } else {
                                GlobalVarible.initPageObject(listViewMailList, mNewsJson.Newslist, myPageIndex);
                            }
                            adapterMailList.notifyDataSetChanged();
                        } catch (Exception e) {
                            toast(textViewTitle.getText().toString()+"解析错误！");
                        }
                    }
                    break;
            }
        }
    }
    public ArrayList<NewsInfo> listViewMailList = new ArrayList<NewsInfo>();
    public ListAdapterNews adapterMailList;

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            String data = bundle.getString("newType");
            if (!StringUtils.isEmpty(data)) {
                if(data.equals("新闻")){
                    textViewTitle.setText("新闻公告");
                    newsType = "1";
                }else{
                    textViewTitle.setText("楼盘活动");
                    newsType = "2";
                }
                if(firstPoint==0) {
                    firstPoint++;
                myPageIndex = GlobalVarible.getPageIndex(listViewMailList);
                HttpBusiness.getNoticesListHTTP(newsType, myPageIndex + "", new NewsHttpBusiness());
                }
            }
        }
    }
    public int firstPoint = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivityActionMode(ActionBarMode.CUSTOMTITILE);
        setTitileResId(R.layout.view_title_simple);
        setRootViewResId(R.layout.aty_more_news);
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
        initListView();

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
            HttpBusiness.getNoticesListHTTP(newsType, myPageIndex + "", new NewsHttpBusiness());

        }
    }

    public void initListView() {
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        
        mListView.setOnRefreshListener(new OnRefreshListener2() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase refreshView) {
                myPageIndex = 1;
                new FinishRefresh().execute();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase refreshView) {
                myPageIndex = GlobalVarible.getPageIndex(listViewMailList);
                new FinishRefresh().execute();

            }
        });

        mListView.setAdapter(adapterMailList);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                if ((position - 1) >=0) position--;
                bundle.putString("newsType", newsType);
                bundle.putString("newsId", listViewMailList.get(position).NewsID);
                PromotionsNewAty.this.showActivity(PromotionsNewAty.this, NewsDetailAty.class, bundle);
            }
        });
    }


}
