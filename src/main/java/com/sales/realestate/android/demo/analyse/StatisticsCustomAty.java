package com.sales.realestate.android.demo.analyse;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
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
import com.sales.realestate.android.bean.CustomInfo;
import com.sales.realestate.android.bean.CustomJson;
import com.sales.realestate.android.bean.SpinnerItemInfo;
import com.sales.realestate.android.bean.TableVolumeInfo;
import com.sales.realestate.android.bean.VisitCustomJson;
import com.sales.realestate.android.bean.VisitCustomerVolumeInfo;
import com.sales.realestate.android.view.calendaer.CalendarCard;
import com.sales.realestate.android.view.calendaer.CardGridItem;
import com.sales.realestate.android.view.calendaer.OnCellItemClick;
import com.sales.realestate.android.demo.analyse.StatisticsVolumeAty.TableHttpBusiness;
import com.sales.realestate.android.demo.custom.CustomDetailAty;
import com.sales.realestate.android.view.adapter.ActionBarSpinnerAdapter;
import com.sales.realestate.android.view.adapter.ListAdapterCustom;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.DateTimeUtil;
import org.kymjs.kjframe.utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by chenlin on 2015/7/20.
 */
public class StatisticsCustomAty extends KJActivity {
    @BindView(id = R.id.title_image_left,click = true)
    private ImageView titleImageLeft;
    @BindView(id = R.id.title_name)
    public TextView textViewTitle;

    @BindView(id = R.id.title_linerlayout, click = true)
    LinearLayout linearLayoutTitle;

    @BindView(id = R.id.title_center_location)
    LinearLayout linearLayoutTitleCenterLocation;

    private PopupWindow popupTitleDropDown = null;
    private ListView listViewPopupTitle;

    public ArrayList<SpinnerItemInfo> listViewTitleList = new ArrayList<SpinnerItemInfo>();
    public ActionBarSpinnerAdapter adapterTitleList;
    public int myPageIndex;

    @BindView(id = R.id.textview_table_time_start, click = true)
    public TextView textview_table_time_start;
    @BindView(id = R.id.textview_table_time_end, click = true)
    public TextView textview_table_time_end;

    @BindView(id = R.id.textview_table_volume_number)
    public TextView textview_table_volume_number;
    @BindView(id = R.id.textview_table_volume_number_all)
    public TextView textview_table_volume_number_all;

    @BindView(id = R.id.listview_mail)
    PullToRefreshListView mListView;
    public ArrayList<CustomInfo> listViewCustomList = new ArrayList<CustomInfo>();
    public ListAdapterCustom adapterCustomList;

    public int timeType = 0 ;
    public String time ;

    Builder builder ;
    AlertDialog mAlertDialog ;
    CalendarCard card;
    private int selectId = -1;
    public ArrayList<CustomInfo> thisCustomList = new ArrayList<CustomInfo>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivityActionMode(ActionBarMode.CUSTOMTITILE);
        setTitileResId(R.layout.view_title_left_button);
        setRootViewResId(R.layout.aty_statistics_custom);
        setmBottomNavigation(BottomNavigation.NOBOTTOMSCROLL);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.textview_table_time_start :
                if(timeType!=0)return ;
                timeType = 1;
                showCalendarDialog();
                break;
            case R.id.textview_table_time_end :
                if(timeType!=0)return ;
                timeType = 2;
            	showCalendarDialog();
            	break;
            case R.id.title_image_left:
                onBackPressed();
                break;
            case R.id.title_linerlayout:
                adapterTitleList.notifyDataSetChanged();
                popupTitleDropDown.showAsDropDown(linearLayoutTitleCenterLocation, 0, -5);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
       
       Bundle bundle = getIntent().getExtras();
        if(bundle!=null && selectId == -1){
            String data = bundle.getString("customType");
            if(!StringUtils.isEmpty(data)){
                int type =  Integer.valueOf(data);
                initTitle(type);  
                selectId = type;
                if(firstPoint==0) {

                    firstPoint++;
                    HttpBusiness.getVisitVolume(textview_table_time_start.getText().toString(), textview_table_time_end.getText().toString(), new TableHttpBusiness(), listViewTitleList.get(type).getId());
                }
                GlobalVarible.initTitleListFocus(listViewTitleList, -1, type);
            }
        }

    }
    public int firstPoint = 0 ;
    public void initTitle(int pozition) {
        textViewTitle.setText(listViewTitleList.get(pozition).getName());
    }

    @Override
    public void initData() {
        super.initData();

        listViewTitleList = GlobalVarible.getTITLE_STATISTICS_CUSTOM();
        adapterTitleList = new ActionBarSpinnerAdapter(listViewTitleList);
        GlobalVarible.initTitleListFocus(listViewTitleList, -1, 0);

    }

    @Override
    public void initWidget() {
        super.initWidget();
        textViewTitle.setText("成交量");
        initListView();
        initTitle(0);
        initTitlePopup();
        textview_table_time_start.setText(DateTimeUtil.getDateBeforeMonth());
        textview_table_time_end.setText(DateTimeUtil.getCurrDateStr());
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
            HttpBusiness.getVolumeVisitCustom(myPageIndex + "", textview_table_time_start.getText().toString(), textview_table_time_end.getText().toString(), new TableHttpBusiness(), listViewTitleList.get(selectId).getId());

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
                StatisticsCustomAty.this.showActivity(StatisticsCustomAty.this, CustomDetailAty.class, bundle);
            }
        });
    }
    
    public void showCalendarDialog() {
        if(builder==null){
            builder = new Builder(this);
            Calendar cal = Calendar.getInstance();
            card = new CalendarCard(this);
            card.setDateDisplay(cal);
            card.notifyChanges();

            builder.setView(card);
            card.setOnCellItemClick(new OnCellItemClick() {
                @Override
                public void onCellClick(View v, CardGridItem item) {
                    time = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(item.getDate().getTime());
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    timeType = 0;
                }
            });

            builder.setPositiveButton("确认", new OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                        	HttpBusiness.getVisitVolume(textview_table_time_start.getText().toString(), textview_table_time_end.getText().toString(), new TableHttpBusiness());
                        	if (timeType == 1) {
                                if (!StringUtils.isEmpty(time)) {
                                    if(textview_table_time_start.getText().equals(time)){

                                    }else{
                                        String beginTime = time;
                                        String endTime =  textview_table_time_end.getText().toString();
                                        if((DateTimeUtil.compare_date(beginTime, endTime)<=0)){
                                            textview_table_time_start.setText(time);
                                            HttpBusiness.getVisitVolume(textview_table_time_start.getText().toString(), textview_table_time_end.getText().toString(), new TableHttpBusiness(),listViewTitleList.get(selectId).getId());
                                        }else{
                                            toast("开始日期不能在结束日期之后！");
                                        }
                                    }


                                }

                            } else {
                                if(textview_table_time_end.getText().equals(time)){

                                }else{
                                    String beginTime = textview_table_time_start.getText().toString();
                                    String endTime =  time;
                                    if((DateTimeUtil.compare_date(beginTime,endTime)<=0)){
                                        textview_table_time_end.setText(time);
                                        HttpBusiness.getVisitVolume(textview_table_time_start.getText().toString(), textview_table_time_end.getText().toString(), new TableHttpBusiness(),listViewTitleList.get(selectId).getId());
                                    }else{
                                        toast("开始日期不能在结束日期之后！");
                                    }
                                }

                            }
                            dialog.cancel();
                        }
                    }

            );
            mAlertDialog = builder.create();
        }else{
        }

        mAlertDialog.show();
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
                initTitle(position);
                GlobalVarible.initTitleListFocus(listViewTitleList, -1, position);
                selectId = position;
                HttpBusiness.getVisitVolume(textview_table_time_start.getText().toString(), textview_table_time_end.getText().toString(), new TableHttpBusiness(),listViewTitleList.get(selectId).getId());
                popupTitleDropDown.dismiss();
            }
        });
        listViewPopupTitle.setAdapter(adapterTitleList);
        popupTitleDropDown = new PopupWindow(listViewPopupTitle, (int) getResources().getDimension(R.dimen.title_spinner_item_width), ActionBar.LayoutParams.WRAP_CONTENT, true);
        popupTitleDropDown.setBackgroundDrawable(new BitmapDrawable());
        popupTitleDropDown.setOutsideTouchable(true);
    }
    
    
    public class TableHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_KEY_TIME_VISIT_VOLUME:
                    if (isError) {
                        toast("到访量未读取！");
                    } else {
                        if (errorMessage.equals("NoData")) {
                            return;
                        }
                        Gson gson = new Gson();
                        try {
                        	VisitCustomerVolumeInfo mTableVolumeInfo = gson.fromJson(returnStr, VisitCustomerVolumeInfo.class);                           
                            textview_table_volume_number_all.setText(mTableVolumeInfo.AllCount );
                            myPageIndex = 1 ;
                            HttpBusiness.getVolumeVisitCustom(myPageIndex+"", textview_table_time_start.getText().toString(), textview_table_time_end.getText().toString(), new TableHttpBusiness(),listViewTitleList.get(selectId).getId());
                            GlobalVarible.initTitleListFocus(listViewTitleList, "(" + mTableVolumeInfo.AllCount+ ")");
                            initTitle(selectId);
                        } catch (Exception e) {
                            toast("到访量解析错误！");
                        }
                    }
                    break;
                case HttpBusiness.HTTP_KEY_TIME_VISIT_CUSTOM:
                    if (isError) {
                        toast("到访客户列表未读取！");
                    } else {
                        if (errorMessage.equals("NoData")) {
                            return;
                        }
                        Gson gson = new Gson();
                        try {
                            VisitCustomJson mCustomJson = gson.fromJson(returnStr, VisitCustomJson.class);
                            if (myPageIndex == 1) {
                                thisCustomList = mCustomJson.VisList;
                                listViewCustomList = thisCustomList;
                                adapterCustomList = new ListAdapterCustom(listViewCustomList);
                                adapterCustomList.adapterType = 4 ;
                                mListView.setAdapter(adapterCustomList);
                            } else {
                                GlobalVarible.initPageObject(listViewCustomList, mCustomJson.VisList, myPageIndex);
                            }
                            adapterCustomList.notifyDataSetChanged();

                        } catch (Exception e) {
                            e.printStackTrace();
                            toast("到访客户列表解析错误！");
                        }
                    }
                    break;
            }
        }
    }

    }
