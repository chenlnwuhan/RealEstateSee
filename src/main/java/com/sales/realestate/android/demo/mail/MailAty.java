package com.sales.realestate.android.demo.mail;

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
import com.handmark.pulltorefresh.library.PullToRefreshListView2;
import com.handmark.pulltorefresh.library.SwipeListView;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.MailInfo;
import com.sales.realestate.android.bean.MailJson;
import com.sales.realestate.android.bean.SpinnerItemInfo;
import com.sales.realestate.android.view.adapter.ActionBarSpinnerAdapter;
import com.sales.realestate.android.view.adapter.ListAdapterMail;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;

/**
 * Created by chenlin on 2015/7/20.
 */
public class MailAty extends KJActivity {

    @BindView(id = R.id.title_image_left, click = true)
    private ImageView titleImageLeft;
    @BindView(id = R.id.listview_mail)
    PullToRefreshListView2 mListView;
    @BindView(id = R.id.title_name)
    public TextView textViewTitle;

    public String maileType = "1" ;

    @BindView(id = R.id.title_linerlayout, click = true)
    LinearLayout linearLayoutTitle;

    @BindView(id = R.id.title_center_location)
    LinearLayout linearLayoutTitleCenterLocation;

    private PopupWindow popupTitleDropDown = null;
    private ListView listViewPopupTitle;
    public ArrayList<MailInfo> listViewMailList = new ArrayList<MailInfo>();
    public ArrayList<SpinnerItemInfo> listViewTitleList = new ArrayList<SpinnerItemInfo>();

    public ListAdapterMail adapterMailList;
    public ActionBarSpinnerAdapter adapterTitleList;

    public class MailHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {

                case HttpBusiness.HTTP_DELMAIL:
                    if (isError) {
                        toast("邮件删除未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            toast("邮件删除成功！");
                            HttpBusiness.getMails(maileType, new MailHttpBusiness());
                        } catch (Exception e) {
                            toast("邮件删除解析错误！");
                        }
                    }
                    break;
                case HttpBusiness.HTTP_KEY_MAILLIST:
                    if (isError) {
                        toast("邮件列表未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            MailJson mBuildListJson = gson.fromJson(returnStr, MailJson.class);
                            GlobalVarible.setMailList(mBuildListJson.Maillist);
                            listViewMailList = GlobalVarible.getMAIL_LIST();
                            adapterMailList = new ListAdapterMail(listViewMailList);
                            adapterMailList.setOnRightItemClickListener(new ListAdapterMail.onRightItemClickListener() {

                                @Override
                                public void onRightItemClick(View v, int position) {
                                    HttpBusiness.delMessage(listViewMailList.get(position).MailID,new MailHttpBusiness());

                                }
                            });
                            SwipeListView mList = (SwipeListView) mListView.getRefreshableView();
                            adapterMailList.mRightWidth = mList.getRightViewWidth();
                            mListView.setAdapter(adapterMailList);
                            mListView.getRefreshableView();
                            adapterMailList.notifyDataSetChanged();
                        } catch (Exception e) {
                            toast("邮件列表解析错误！");
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
        setRootViewResId(R.layout.aty_mail_news);
        setmBottomNavigation(BottomNavigation.NOBOTTOMSCROLL);
        super.onCreate(savedInstanceState);
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
    protected void onStart() {
        super.onStart();
        if(firstPoint!=-1) {
            firstPoint++;
            HttpBusiness.getMails(maileType, new MailHttpBusiness());
        }
    }
    public int firstPoint = 0 ;
    @Override
    public void initData() {
        super.initData();

        listViewMailList = GlobalVarible.getMAIL_LIST();
        adapterMailList = new ListAdapterMail(listViewMailList);
        adapterMailList.setOnRightItemClickListener(new ListAdapterMail.onRightItemClickListener() {

            @Override
            public void onRightItemClick(View v, int position) {
                HttpBusiness.delMessage(listViewMailList.get(position).MailID,new MailHttpBusiness());

            }
        });
        listViewTitleList = GlobalVarible.getTITLE_MAIL_LIST();
        adapterTitleList = new ActionBarSpinnerAdapter(listViewTitleList);
        GlobalVarible.initTitleListFocus(listViewTitleList, -1, 1);
    }

    public void initTitle(int pozition) {
        textViewTitle.setText(listViewTitleList.get(pozition).getName());
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle(1);
        initListView();
        initTitlePopup();

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
            HttpBusiness.getMails(maileType, new MailHttpBusiness());

        }
    }

    public void initListView() {
        mListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        
        mListView.setOnRefreshListener(new OnRefreshListener2() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase refreshView) {
                new FinishRefresh().execute();
                adapterMailList.notifyDataSetChanged();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase refreshView) {
                new FinishRefresh().execute();
                adapterMailList.notifyDataSetChanged();

            }
        });

        SwipeListView mList = (SwipeListView) mListView.getRefreshableView();
        adapterMailList.mRightWidth = mList.getRightViewWidth();
        mListView.setAdapter(adapterMailList);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                if ((position - 1) >= 0) position--;
                bundle.putString("mailId", listViewMailList.get(position).MailID);
                MailAty.this.showActivity(MailAty.this, MailDetailAty.class, bundle);
            }
        });
//        mList.setRemoveListener(new SlideCutListView.RemoveListener() {
//            @Override
//            public void removeItem(SlideCutListView.RemoveDirection direction, int position) {
//                if ((position - 1) >= 0) position--;
//                HttpBusiness.delMessage(listViewMailList.get(position).MailID,new MailHttpBusiness());
//            }
//        });

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
                    maileType = listViewTitleList.get(position).getId();
                    HttpBusiness.getMails(maileType, new MailHttpBusiness());
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
