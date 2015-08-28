package com.sales.realestate.android.demo.custom;


import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.CustomInfo;
import com.sales.realestate.android.bean.FollowDetailInfo;
import com.sales.realestate.android.bean.FollowMessageInfo;
import com.sales.realestate.android.bean.FollowStateInfo;
import com.sales.realestate.android.view.adapter.ListAdapterCustom;
import com.sales.realestate.android.view.adapter.ListAdapterFollow;
import com.sales.realestate.android.view.popupwindow.CustomDetailFollowPW;
import com.sales.realestate.android.view.popupwindow.CustomDetailTypePW;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.StringUtils;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by chenlin on 2015/7/20.
 */
public class CustomDetailAty extends KJActivity {
    @BindView(id = R.id.title_image_left, click = true)
    private ImageView titleImageLeft;
    @BindView(id = R.id.title_name)
    public TextView textViewTitle;

    CustomDetailFollowPW mCustomDetailFollowPW;
    CustomDetailTypePW mCustomDetailTypePW;
    @BindView(id = R.id.imageview_custom_detail_type, click = true)
    public ImageView imageview_custom_detail_type;

    @BindView(id = R.id.relativelayout_custom_follow_add, click = true)
    public RelativeLayout relativelayout_custom_follow_add;
    @BindView(id = R.id.relativelayout_custom_detail_base, click = true)
    public RelativeLayout relativelayout_custom_detail_base;
    @BindView(id = R.id.textview_table_volume)
    public TextView textview_table_volume;
    @BindView(id = R.id.view_table_volume)
    public View view_table_volume;
    @BindView(id = R.id.relativelayout_custom_detail_base_sub)
    public LinearLayout relativelayout_custom_detail_base_sub;
    @BindView(id = R.id.textview_table_volume_number)
    public TextView textview_table_volume_number;
    @BindView(id = R.id.textview_table_volume_number_all)
    public TextView textview_table_volume_number_all;


    @BindView(id = R.id.relativelayout_custom_detail_follow, click = true)
    public RelativeLayout relativelayout_custom_detail_follow;
    @BindView(id = R.id.textview_table_rate)
    public TextView textview_table_rate;
    @BindView(id = R.id.view_table_rate)
    public View view_table_rate;
    @BindView(id = R.id.linearLayout_custom_detail_follow_sub)
    public LinearLayout linearLayout_custom_detail_follow_sub;

    @BindView(id = R.id.listview_mail)
    PullToRefreshListView mListView;

    @BindView(id = R.id.textview_custom_name)
    public TextView textview_custom_name;
    @BindView(id = R.id.textview_custom_valid)
    public TextView textview_custom_valid;
    @BindView(id = R.id.textview_custom_sex)
    public TextView textview_custom_sex;
    @BindView(id = R.id.textview_custom_detail_type)
    public TextView textview_custom_detail_type;

    @BindView(id = R.id.textview_custom_detail_name)
    public TextView textview_custom_detail_name;
    @BindView(id = R.id.textview_custom_detail_phone)
    public TextView textview_custom_detail_phone;
    @BindView(id = R.id.textview_custom_detail_way1)
    public TextView textview_custom_detail_way1;
    @BindView(id = R.id.textview_custom_detail_way2)
    public TextView textview_custom_detail_way2;

    @BindView(id = R.id.textview_custom_detail_way3)
    public TextView textview_custom_detail_way3;
    @BindView(id = R.id.textview_custom_detail_time)
    public TextView textview_custom_detail_time;
    @BindView(id = R.id.textview_custom_detail_will)
    public TextView textview_custom_detail_will;

    public String customId = "0";
    public FollowMessageInfo mFollowMessageInfo = new FollowMessageInfo();
    public FollowDetailInfo mFollowDetailInfo = new FollowDetailInfo();

    @BindView(id = R.id.first_point1)
    public ImageView first_point1;
    @BindView(id = R.id.first_textview1)
    public TextView first_textview1;
    @BindView(id = R.id.second_textview1)
    public TextView second_textview1;
    @BindView(id = R.id.third_textview1)
    public TextView third_textview1;

    @BindView(id = R.id.first_line1)
    public View first_line1;
    @BindView(id = R.id.first_point2)
    public ImageView first_point2;
    @BindView(id = R.id.first_textview2)
    public TextView first_textview2;
    @BindView(id = R.id.second_textview2)
    public TextView second_textview2;
    @BindView(id = R.id.third_textview2)
    public TextView third_textview2;

    @BindView(id = R.id.first_line2)
    public View first_line2;
    @BindView(id = R.id.first_point3)
    public ImageView first_point3;
    @BindView(id = R.id.first_textview3)
    public TextView first_textview3;
    @BindView(id = R.id.second_textview3)
    public TextView second_textview3;
    @BindView(id = R.id.third_textview3)
    public TextView third_textview3;

    @BindView(id = R.id.first_line3)
    public View first_line3;
    @BindView(id = R.id.first_point4)
    public ImageView first_point4;
    @BindView(id = R.id.first_textview4)
    public TextView first_textview4;
    @BindView(id = R.id.second_textview4)
    public TextView second_textview4;
    @BindView(id = R.id.third_textview4)
    public TextView third_textview4;
    /**
     * 1 类型更新
     * 2 更近更新
     * 0 无
     */
    public int reflashType = 0;

    public ArrayList<FollowDetailInfo> listViewMailList = new ArrayList<FollowDetailInfo>();
    public ListAdapterFollow adapterMailList;

    public CustomInfo mCustomInfo = new CustomInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivityActionMode(ActionBarMode.CUSTOMTITILE);
        setTitileResId(R.layout.view_title_simple);
        setRootViewResId(R.layout.aty_custom_detail);
        setmBottomNavigation(BottomNavigation.NOBOTTOMSCROLL);
        super.onCreate(savedInstanceState);
    }

    public void onrefresh() {
        textview_custom_name.setText("");
        textview_custom_valid.setText("");
        textview_custom_sex.setText("");
        textview_custom_detail_type.setText("");
        textview_custom_detail_name.setText("");
        textview_custom_detail_phone.setText("");
        textview_custom_detail_way1.setText("");
        textview_custom_detail_way2.setText("");
        textview_custom_detail_way3.setText("");
        textview_custom_detail_time.setText("");
        textview_custom_detail_will.setText("");
        imageview_custom_detail_type.setClickable(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            customId = bundle.getString("customId");
            if (StringUtils.isEmpty(customId)) {
                toast("客户ID为空！");
                return;
            }
            HttpBusiness.getCustomDetail(customId, new CustomDetailHttpBusiness());
        }
    }

    public class CustomDetailHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_KEY_CUSTOMDETAIL:
                    if (isError) {
                        toast("客户详情未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            if (errorMessage.equals("NoData")) {
                                toast("服务器返回客户信息为空！");
                                return;
                            }
                            mCustomInfo = gson.fromJson(returnStr, CustomInfo.class);
                            textview_custom_name.setText(mCustomInfo.CustomerName);
                            if (mCustomInfo.CustomerValid.indexOf("有效") >= 0) {
                                textview_custom_valid.setTextColor(getResources().getColor(R.color.global_text_vailed));
                            } else {
                                textview_custom_valid.setTextColor(getResources().getColor(R.color.global_text_red));
                            }
                            textview_custom_valid.setText("[" + mCustomInfo.CustomerValid + "]");
                            textview_custom_sex.setText(mCustomInfo.Sex);
                            textview_custom_detail_type.setText(mCustomInfo.Customerlevel + "级");
                            textview_custom_detail_name.setText(mCustomInfo.CustomerName);
                            textview_custom_detail_phone.setText(mCustomInfo.Mobile);
                            String[] tmp = mCustomInfo.FromAgentInfo.split("\\|");
                            if (tmp.length == 3) {
                                textview_custom_detail_way1.setText(tmp[0]);
                                textview_custom_detail_way2.setText(tmp[1]);
                                textview_custom_detail_way3.setText(tmp[2]);
                            } if (tmp.length == 4) {
                                textview_custom_detail_way1.setText(tmp[0]);
                                textview_custom_detail_way2.setText(tmp[1]);
                                textview_custom_detail_way3.setText(tmp[2]+tmp[3]);
                            } else {
                                textview_custom_detail_way1.setText(mCustomInfo.FromAgentInfo);
                            }
                            textview_custom_detail_time.setText(mCustomInfo.AddTime);
                            textview_custom_detail_will.setText(mCustomInfo.CustomerThink);
                            imageview_custom_detail_type.setClickable(true);
                            mCustomInfo.CustomerID = customId;
                            myPageIndex = 1;
                            HttpBusiness.getCusomFollowList(myPageIndex+"",mCustomInfo.CustomerID, new CustomDetailHttpBusiness());

                        } catch (Exception e) {
                            toast("客户详情解析错误！");
                        }
                    }
                    break;
                case HttpBusiness.HTTP_KEY_CUSTOM_FOLLOWINFO_LIST:
                    if (isError) {
                        toast("客户跟进信息未读取！");
                    } else {
                        Gson gson = new Gson();
                        mFollowMessageInfo = gson.fromJson(returnStr, FollowMessageInfo.class);
                        ArrayList<FollowStateInfo> customerState = mFollowMessageInfo.customerState;
                        initCustomStats(customerState);
                        try {
                            if (errorMessage.equals("NoData")) {
                            //    toast("客户未有更多跟进信息！");
                                return;
                            } else {
                                if (myPageIndex == 1) {
                                    listViewMailList = mFollowMessageInfo.follws;
                                    try{
                                        if(listViewMailList.size()==1&&listViewMailList.get(0).ID.equals("-1")){
                                            return ;
                                        }
                                    }catch(Exception e){
                                        e.printStackTrace();
                                        return ;
                                    }

                                    adapterMailList = new ListAdapterFollow(listViewMailList);
                                    mListView.setAdapter(adapterMailList);
                                } else {
                                    GlobalVarible.initPageObject(listViewMailList, mFollowMessageInfo.follws, myPageIndex);
                                }
                                adapterMailList.notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            toast("客户跟进信息解析错误！");
                        }
                    }
                    break;
                case HttpBusiness.HTTP_KEY_CUSTOM_FOLLOWINFO_DETAIL:
                    if (isError) {
                        toast("客户跟进详细信息未读取！");
                    } else {
                        Gson gson = new Gson();
                        mFollowDetailInfo = new FollowDetailInfo();
                        mFollowDetailInfo = gson.fromJson(returnStr, FollowDetailInfo.class);
                        try {
                            if (errorMessage.equals("NoData")) {
                                toast("客户未有详细跟进信息！");
                                return;
                            } else {
                                initCustomFollowType();
                                mCustomDetailFollowPW.setCustomInfo(mCustomInfo);
                                mCustomDetailFollowPW.setFollowDetailInfo(mFollowDetailInfo);
                                mCustomDetailFollowPW.setBuyType(2);
                                mCustomDetailFollowPW.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                            }
                        } catch (Exception e) {
                            toast("客户跟进详细信息解析错误！");
                        }
                    }
                    break;
            }
        }
    }

    public void initCustomStats(ArrayList<FollowStateInfo> customerState) {
        for (int i = 0; i < 4; i++) {
            String followString = "";
            String followCode = "";
            if (customerState.size() > i) {
                FollowStateInfo mFollowStateInfo = customerState.get(i);
                followCode = mFollowStateInfo.FollowCode;
                if (!StringUtils.isEmpty(mFollowStateInfo.FollowInfo)) {
                    followString = mFollowStateInfo.FollowInfo;
                }
            }
            if (i == 0) {
                if (!followCode.equals("1")) {
                    first_point1.setImageResource(R.drawable.pic_custom_detail_point_no);
                    first_textview1.setTextColor(getResources().getColor(R.color.detail_text_no));
                    second_textview1.setVisibility(View.GONE);
                    third_textview1.setVisibility(View.GONE);
                } else {
                    first_point1.setImageResource(R.drawable.pic_custom_detail_point_on);
                    first_textview1.setTextColor(getResources().getColor(R.color.detail_text_on));
                    second_textview1.setVisibility(View.VISIBLE);
                    third_textview1.setVisibility(View.VISIBLE);
                    second_textview1.setTextColor(getResources().getColor(R.color.detail_text_on));
                    third_textview1.setTextColor(getResources().getColor(R.color.detail_text_on));
                    if (!StringUtils.isEmpty(followString)) {
                        String[] followStringT = followString.split("\\|");
                        if (followStringT.length > 1)
                            second_textview1.setText(followStringT[1]);
                        third_textview1.setText(followStringT[0]);
                    }
                }
            }
            if (i == 1) {
                if (!followCode.equals("1")) {
                    first_line1.setBackgroundColor(getResources().getColor(R.color.detail_line_no));
                    first_point2.setImageResource(R.drawable.pic_custom_detail_point_no);
                    first_textview2.setTextColor(getResources().getColor(R.color.detail_text_no));
                    second_textview2.setVisibility(View.GONE);
                    third_textview2.setVisibility(View.GONE);
                } else {
                    first_line1.setBackgroundColor(getResources().getColor(R.color.detail_line_on));
                    first_point2.setImageResource(R.drawable.pic_custom_detail_point_on);
                    first_textview2.setTextColor(getResources().getColor(R.color.detail_text_on));
                    second_textview2.setVisibility(View.VISIBLE);
                    third_textview2.setVisibility(View.VISIBLE);
                    second_textview2.setTextColor(getResources().getColor(R.color.detail_text_on));
                    third_textview2.setTextColor(getResources().getColor(R.color.detail_text_on));
                    if (!StringUtils.isEmpty(followString)) {
                        String[] followStringT = followString.split("\\|");
                        if (followStringT.length > 1)
                            second_textview2.setText(followStringT[1]);
                        third_textview2.setText(followStringT[0]);
                    }
                }
            }
            if (i == 2) {
                if (!followCode.equals("1")) {
                    first_line2.setBackgroundColor(getResources().getColor(R.color.detail_line_no));
                    first_point3.setImageResource(R.drawable.pic_custom_detail_point_no);
                    first_textview3.setTextColor(getResources().getColor(R.color.detail_text_no));
                    second_textview3.setVisibility(View.GONE);
                    third_textview3.setVisibility(View.GONE);
                } else {
                    first_line2.setBackgroundColor(getResources().getColor(R.color.detail_line_on));
                    first_point3.setImageResource(R.drawable.pic_custom_detail_point_on);
                    first_textview3.setTextColor(getResources().getColor(R.color.detail_text_on));
                    second_textview3.setVisibility(View.VISIBLE);
                    third_textview3.setVisibility(View.VISIBLE);
                    second_textview3.setTextColor(getResources().getColor(R.color.detail_text_on));
                    third_textview3.setTextColor(getResources().getColor(R.color.detail_text_on));
                    if (!StringUtils.isEmpty(followString)) {
                        String[] followStringT = followString.split("\\|");
                        if (followStringT.length > 1)
                            second_textview3.setText(followStringT[1]);
                        third_textview3.setText(followStringT[0]);
                    }
                }
            }
            if (i == 3) {
                if (!followCode.equals("1")) {
                    first_line3.setBackgroundColor(getResources().getColor(R.color.detail_line_no));
                    first_point4.setImageResource(R.drawable.pic_custom_detail_point_no);
                    first_textview4.setTextColor(getResources().getColor(R.color.detail_text_no));
                    second_textview4.setVisibility(View.GONE);
                    third_textview4.setVisibility(View.GONE);
                } else {
                    first_line3.setBackgroundColor(getResources().getColor(R.color.detail_line_on));
                    first_point4.setImageResource(R.drawable.pic_custom_detail_point_on);
                    first_textview4.setTextColor(getResources().getColor(R.color.detail_text_on));
                    second_textview4.setVisibility(View.VISIBLE);
                    third_textview4.setVisibility(View.VISIBLE);
                    second_textview4.setTextColor(getResources().getColor(R.color.detail_text_on));
                    third_textview4.setTextColor(getResources().getColor(R.color.detail_text_on));
                    if (!StringUtils.isEmpty(followString)) {
                        String[] followStringT = followString.split("\\|");
                        if (followStringT.length > 1)
                            second_textview4.setText(followStringT[1]);
                        third_textview4.setText(followStringT[0]);
                    }

                }
            }

        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.relativelayout_custom_detail_base:
                initPageView(1);
                break;
            case R.id.relativelayout_custom_detail_follow:
                initPageView(2);
                break;
            case R.id.title_image_left:
                onBackPressed();
                break;
            case R.id.relativelayout_custom_follow_add:
                initCustomFollowType();
                mCustomDetailFollowPW.setCustomInfo(mCustomInfo);
                mCustomDetailFollowPW.setFollowDetailInfo(mFollowDetailInfo);
                mCustomDetailFollowPW.setBuyType(1);
                mCustomDetailFollowPW.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                break;
            case R.id.imageview_custom_detail_type:
                if (mCustomDetailTypePW == null) {
                    mCustomDetailTypePW = new CustomDetailTypePW(this);
                    mCustomDetailTypePW.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            reflashType = mCustomInfo.reflashType;
                            if (reflashType != 0) {
                                HttpBusiness.getCustomDetail(customId, new CustomDetailHttpBusiness());
                            }
                        }
                    });
                }

                mCustomDetailTypePW.setBuyType(mCustomInfo);
                mCustomDetailTypePW.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                break;
        }
    }

    public int myPageIndex = 1;

    public void initCustomFollowType() {
        if (mCustomDetailFollowPW == null) {
            mCustomDetailFollowPW = new CustomDetailFollowPW(this);
            mCustomDetailFollowPW.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    reflashType = mCustomInfo.reflashType;
                    if (reflashType != 0) {
                        HttpBusiness.getCusomFollowList(myPageIndex + "", mCustomInfo.CustomerID, new CustomDetailHttpBusiness());
                    }
                }
            });
        }
    }

    /**
     * 1 显示客户基本信息
     * 2 显示客户跟进信息
     *
     * @param type
     */
    public void initPageView(int type) {
        if (type == 1) {
            textview_table_volume.setTextColor(getResources().getColor(R.color.global_text_red));
            view_table_volume.setVisibility(View.VISIBLE);
            textview_table_rate.setTextColor(getResources().getColor(R.color.global_black));
            view_table_rate.setVisibility(View.GONE);
            relativelayout_custom_detail_base_sub.setVisibility(View.VISIBLE);
            linearLayout_custom_detail_follow_sub.setVisibility(View.GONE);
        } else {
            textview_table_volume.setTextColor(getResources().getColor(R.color.global_black));
            view_table_volume.setVisibility(View.GONE);
            textview_table_rate.setTextColor(getResources().getColor(R.color.global_text_red));
            view_table_rate.setVisibility(View.VISIBLE);
            relativelayout_custom_detail_base_sub.setVisibility(View.GONE);
            linearLayout_custom_detail_follow_sub.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initData() {
        super.initData();
        initPageView(1);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        textViewTitle.setText("客户详情");

        initListView();
        onrefresh();
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
                myPageIndex = GlobalVarible.getPageIndex(listViewMailList);
                new FinishRefresh().execute();

            }
        });

        mListView.setAdapter(adapterMailList);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                if ((position - 1) >= 0) position--;
                String followInfoId = listViewMailList.get(position).ID;
                HttpBusiness.getCusomFollowDetail(mCustomInfo.CustomerID, followInfoId, new CustomDetailHttpBusiness());

            }
        });
        ListView mmListView = mListView.getRefreshableView();
        mmListView.setDivider(new ColorDrawable(getResources().getColor(R.color.custom_follow_divider)));
        mmListView.setDividerHeight((int) getResources().getDimension(R.dimen.custom_follow_divider));

    }

    public int subHeight = 0;
    public int subMaxListViewHeight = 0;

    Timer timer;

    @Override
    protected void onResume() {
        super.onResume();

        final Handler myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    if (relativelayout_custom_detail_base_sub.getHeight() != 0) {
                        //取消定时器
                        timer.cancel();
                        subHeight = relativelayout_custom_detail_base_sub.getHeight();
                        subMaxListViewHeight = (int) (subHeight - getResources().getDimension(R.dimen.custom_follow_mini));
                        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, subMaxListViewHeight);
                        mListView.setLayoutParams(mLayoutParams);


                    }
                }
            }
        };

        timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                Message message = new Message();
                message.what = 1;
                myHandler.sendMessage(message);
            }
        };
        //延迟每次延迟10 毫秒 隔1秒执行一次
        timer.schedule(task, 10, 1000);
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
            HttpBusiness.getCusomFollowList(myPageIndex + "", mCustomInfo.CustomerID, new CustomDetailHttpBusiness());
        }
    }


}
