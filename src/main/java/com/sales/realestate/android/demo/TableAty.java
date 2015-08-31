package com.sales.realestate.android.demo;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sales.realestate.android.CommomKey;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.TableNumberJson;
import com.sales.realestate.android.bean.TableRateInfo;
import com.sales.realestate.android.bean.TableVolumeInfo;
import com.sales.realestate.android.demo.custom.CustomAty;
import com.sales.realestate.android.demo.more.MoreAty;
import com.sales.realestate.android.view.RoundProgressBar;
import com.sales.realestate.android.view.calendaer.CalendarCard;
import com.sales.realestate.android.view.calendaer.CardGridItem;
import com.sales.realestate.android.view.calendaer.OnCellItemClick;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.DateTimeUtil;
import org.kymjs.kjframe.utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by chenlin on 2015/7/20.
 */
public class TableAty extends KJActivity {
    @BindView(id = R.id.title_image_left)
    private ImageView titleImageLeft;
    @BindView(id = R.id.title_name)
    public TextView textViewTitle;

    @BindView(id = R.id.textview_table_time_start, click = true)
    public TextView textview_table_time_start;
    @BindView(id = R.id.textview_table_time_end, click = true)
    public TextView textview_table_time_end;


    @BindView(id = R.id.relativelayout_table_volume, click = true)
    public RelativeLayout relativelayout_table_volume;
    @BindView(id = R.id.textview_table_volume)
    public TextView textview_table_volume;
    @BindView(id = R.id.view_table_volume)
    public View view_table_volume;
    @BindView(id = R.id.relativelayout_table_volume_sub)
    public RelativeLayout relativelayout_table_volume_sub;
    @BindView(id = R.id.textview_table_volume_number)
    public TextView textview_table_volume_number;
    @BindView(id = R.id.textview_table_volume_number_all)
    public TextView textview_table_volume_number_all;


    @BindView(id = R.id.relativelayout_table_rate, click = true)
    public RelativeLayout relativelayout_table_rate;
    @BindView(id = R.id.textview_table_rate)
    public TextView textview_table_rate;
    @BindView(id = R.id.view_table_rate)
    public View view_table_rate;
    @BindView(id = R.id.common_pro)
    public RoundProgressBar common_pro;


    @BindView(id = R.id.linearlayout_table_no1)
    private RelativeLayout linearlayout_table_no1;
    @BindView(id = R.id.linearlayout_table_no2)
    private RelativeLayout linearlayout_table_no2;
    @BindView(id = R.id.linearlayout_table_no3)
    private RelativeLayout linearlayout_table_no3;
    @BindView(id = R.id.linearlayout_table_no4)
    private RelativeLayout linearlayout_table_no4;
    @BindView(id = R.id.linearlayout_table_no5)
    private RelativeLayout linearlayout_table_no5;



    @BindView(id = R.id.textview_table_no1_no)
    public TextView textview_table_no1_no;
    @BindView(id = R.id.textview_table_no2_no)
    public TextView textview_table_no2_no;
    @BindView(id = R.id.textview_table_no3_no)
    public TextView textview_table_no3_no;
    @BindView(id = R.id.textview_table_no4_no)
    public TextView textview_table_no4_no;
    @BindView(id = R.id.textview_table_no5_no)
    public TextView textview_table_no5_no;

    @BindView(id = R.id.textview_table_no1_name)
    public TextView textview_table_no1_name;
    @BindView(id = R.id.textview_table_no2_name)
    public TextView textview_table_no2_name;
    @BindView(id = R.id.textview_table_no3_name)
    public TextView textview_table_no3_name;
    @BindView(id = R.id.textview_table_no4_name)
    public TextView textview_table_no4_name;
    @BindView(id = R.id.textview_table_no5_name)
    public TextView textview_table_no5_name;

    @BindView(id = R.id.textview_table_no1_volume)
    public TextView textview_table_no1_volume;
    @BindView(id = R.id.textview_table_no2_volume)
    public TextView textview_table_no2_volume;
    @BindView(id = R.id.textview_table_no3_volume)
    public TextView textview_table_no3_volume;
    @BindView(id = R.id.textview_table_no4_volume)
    public TextView textview_table_no4_volume;
    @BindView(id = R.id.textview_table_no5_volume)
    public TextView textview_table_no5_volume;

    @BindView(id = R.id.textview_table_no1_rate)
    public TextView textview_table_no1_rate;
    @BindView(id = R.id.textview_table_no2_rate)
    public TextView textview_table_no2_rate;
    @BindView(id = R.id.textview_table_no3_rate)
    public TextView textview_table_no3_rate;
    @BindView(id = R.id.textview_table_no4_rate)
    public TextView textview_table_no4_rate;
    @BindView(id = R.id.textview_table_no5_rate)
    public TextView textview_table_no5_rate;

    @BindView(id = R.id.view_no1_percent_base)
    public View view_no1_percent_base;
    @BindView(id = R.id.view_no2_percent_base)
    public View view_no2_percent_base;
    @BindView(id = R.id.view_no3_percent_base)
    public View view_no3_percent_base;
    @BindView(id = R.id.view_no4_percent_base)
    public View view_no4_percent_base;
    @BindView(id = R.id.view_no5_percent_base)
    public View view_no5_percent_base;

    @BindView(id = R.id.view_no1_percent_rate)
    public View view_no1_percent_rate;
    @BindView(id = R.id.view_no2_percent_rate)
    public View view_no2_percent_rate;
    @BindView(id = R.id.view_no3_percent_rate)
    public View view_no3_percent_rate;
    @BindView(id = R.id.view_no4_percent_rate)
    public View view_no4_percent_rate;
    @BindView(id = R.id.view_no5_percent_rate)
    public View view_no5_percent_rate;

    /**
     * 底部导航栏
     */
    @BindView(id = R.id.bottom_menu_layout_home, click = true)
    public LinearLayout navigationHome;
    @BindView(id = R.id.bottom_menu_layout_custom, click = true)
    public LinearLayout navigationCustom;
    @BindView(id = R.id.bottom_menu_layout_sale, click = true)
    public LinearLayout navigationSale;
    @BindView(id = R.id.bottom_menu_layout_table, click = true)
    public LinearLayout navigationTable;
    @BindView(id = R.id.bottom_menu_layout_more, click = true)
    public LinearLayout navigationMore;

    public int tableType = 1;
    public int timeType = 0;
    public String time;

    Builder builder;
    AlertDialog mAlertDialog;
    CalendarCard card;
    /**
     * 导航栏需要高亮的元素
     */
    @BindView(id = R.id.bottom_menu_img_table)
    public ImageView bottom_menu_img_more;
    @BindView(id = R.id.bottom_menu_text_table)
    public TextView bottom_menu_text_more;

    @Override
    public void initBottomNavagation() {
        bottom_menu_img_more.setImageResource(R.drawable.pic_bottom_table_foucs);
        bottom_menu_text_more.setTextColor(this.getResources().getColor(R.color.bottom_navigation_text_color_foucs));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivityActionMode(ActionBarMode.CUSTOMTITILE);
        setTitileResId(R.layout.view_title_simple);
        setRootViewResId(R.layout.aty_table);
        setmBottomNavigation(BottomNavigation.JUSTNOSCROLL);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.bottom_menu_layout_custom:
                showActivity(this, CustomAty.class);
                break;
            case R.id.bottom_menu_layout_more:
                showActivity(this, MoreAty.class);
                break;
            case R.id.bottom_menu_layout_home:
                showActivity(this, MainActivity.class);
                break;
            case R.id.bottom_menu_layout_sale:
                showActivity(this, SaleAty.class);
                break;
            case R.id.relativelayout_table_volume:
                if (tableType == 1) return;
                initPageView(1);
                break;
            case R.id.relativelayout_table_rate:
                if (tableType == 2) return;
                initPageView(2);
                break;
            case R.id.textview_table_time_start:
                if (timeType != 0) return;
                timeType = 1;
                showCalendarDialog();
                break;
            case R.id.textview_table_time_end:
                if (timeType != 0) return;
                timeType = 2;
                showCalendarDialog();
                break;
        }
    }

    public class TableHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_KEY_TABLE_VOLUME:
                    if (isError) {
                        toast("成交量未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            TableVolumeInfo mTableVolumeInfo = gson.fromJson(returnStr, TableVolumeInfo.class);
                            textview_table_volume_number.setText(mTableVolumeInfo.TotalHouseCount);
                            if(mTableVolumeInfo.TotalTurnover==null){
                                mTableVolumeInfo.TotalTurnover = "0";
                            }
                            textview_table_volume_number_all.setText(mTableVolumeInfo.TotalTurnover + "元");
                            HttpBusiness.getTableRate(textview_table_time_start.getText().toString(), textview_table_time_end.getText().toString(), new TableHttpBusiness());
                        } catch (Exception e) {
                            toast("成交量解析错误！");
                        }
                    }
                    break;
                case HttpBusiness.HTTP_KEY_TABLE_RATE:
                    if (isError) {
                        toast("成交率未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            TableRateInfo mTableRateInfo = gson.fromJson(returnStr, TableRateInfo.class);
                            String tmp = mTableRateInfo.Percentage.replace("%", "");
                            int i = 0;
                            if (!StringUtils.isEmpty(tmp)) {
                                String[] precents = tmp.split("\\.");
                                i = Integer.valueOf(precents[0]);
                            }
                            common_pro.setProgress(i);
                            HttpBusiness.getTableNumber("1", textview_table_time_start.getText().toString(), textview_table_time_end.getText().toString(), new TableHttpBusiness());
                        } catch (Exception e) {
                            e.printStackTrace();
                            toast("成交率解析错误！");
                        }
                    }
                    break;
                case HttpBusiness.HTTP_KEY_TABLE_NUMBER:
                    if (isError) {
                        toast("销售排名未读取！");
                    } else {
                        if (errorMessage.equals("NoData")) {
                            toast("无销售信息！");
                            return;
                        }
                        Gson gson = new Gson();
                        try {
                            TableNumberJson mTableNumberJson = gson.fromJson(returnStr, TableNumberJson.class);
                            if (mTableNumberJson.deallist == null && mTableNumberJson.deallist.size() > 0) {
                                toast("销售数据为空");
                                return;
                            }
                            textview_table_no1_no.setVisibility(View.GONE);
                            textview_table_no2_no.setVisibility(View.GONE);
                            textview_table_no3_no.setVisibility(View.GONE);
                            textview_table_no4_no.setVisibility(View.GONE);
                            textview_table_no5_no.setVisibility(View.GONE);
                            textview_table_no1_name.setText("");
                            textview_table_no1_volume.setText("");
                            textview_table_no2_name.setText("");
                            textview_table_no2_volume.setText("");
                            textview_table_no3_name.setText("");
                            textview_table_no3_volume.setText("");
                            textview_table_no4_name.setText("");
                            textview_table_no4_volume.setText("");
                            textview_table_no5_name.setText("");
                            textview_table_no5_volume.setText("");
                            for (int i = 0; i < mTableNumberJson.deallist.size(); i++) {
                                if (i == 0) {
                                    textview_table_no1_no.setVisibility(View.VISIBLE);
                                    linearlayout_table_no1.setVisibility(View.VISIBLE);
                                    textview_table_no1_name.setText(mTableNumberJson.deallist.get(i).UserName);
                                    textview_table_no1_volume.setText(mTableNumberJson.deallist.get(i).Total + "套");
                                } else if (i == 1) {
                                    linearlayout_table_no2.setVisibility(View.VISIBLE);
                                    textview_table_no2_no.setVisibility(View.VISIBLE);
                                    textview_table_no2_name.setText(mTableNumberJson.deallist.get(i).UserName);
                                    textview_table_no2_volume.setText(mTableNumberJson.deallist.get(i).Total + "套");
                                } else if (i == 2) {
                                    linearlayout_table_no3.setVisibility(View.VISIBLE);
                                    textview_table_no3_no.setVisibility(View.VISIBLE);
                                    textview_table_no3_name.setText(mTableNumberJson.deallist.get(i).UserName);
                                    textview_table_no3_volume.setText(mTableNumberJson.deallist.get(i).Total + "套");
                                } else if (i == 3) {
                                    linearlayout_table_no4.setVisibility(View.VISIBLE);
                                    textview_table_no4_no.setVisibility(View.VISIBLE);
                                    textview_table_no4_name.setText(mTableNumberJson.deallist.get(i).UserName);
                                    textview_table_no4_volume.setText(mTableNumberJson.deallist.get(i).Total + "套");
                                } else if (i == 4) {
                                    linearlayout_table_no5.setVisibility(View.VISIBLE);
                                    textview_table_no5_no.setVisibility(View.VISIBLE);
                                    textview_table_no5_name.setText(mTableNumberJson.deallist.get(i).UserName);
                                    textview_table_no5_volume.setText(mTableNumberJson.deallist.get(i).Total + "套");
                                }
                            }

                            HttpBusiness.getTableNumberRate("1", textview_table_time_start.getText().toString(), textview_table_time_end.getText().toString(), new TableHttpBusiness());

                        } catch (Exception e) {
                            toast("销售排名解析错误！");
                        }
                    }
                    break;
                case HttpBusiness.HTTP_KEY_TABLE_NUMBERRATE:
                    if (isError) {
                        toast("销售排名未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            TableNumberJson mTableNumberJson = gson.fromJson(returnStr, TableNumberJson.class);
                            if (mTableNumberJson.PercentageList == null && mTableNumberJson.PercentageList.size() > 0) {
                                toast("销售数据为空");
                            }
                            for (int i = 0; i < mTableNumberJson.PercentageList.size(); i++) {

                                String perge = mTableNumberJson.PercentageList.get(i).Percentage.replace("%", "");
                                if (StringUtils.isEmpty(perge)) {
                                    perge = "0";
                                }
                                int height = (int) getResources().getDimension(R.dimen.table_rate_height);
                                int width = (int) ((getResources().getDimension(R.dimen.table_rate_base) * Float.parseFloat(perge) / 100));
                                RelativeLayout.LayoutParams mLayoutParams = new RelativeLayout.LayoutParams(width, height);
                                if (i == 0) {
                                    textview_table_no1_rate.setText(mTableNumberJson.PercentageList.get(i).Percentage );
                                    view_no1_percent_base.setLayoutParams(mLayoutParams);
                                } else if (i == 1) {
                                    textview_table_no2_rate.setText(mTableNumberJson.PercentageList.get(i).Percentage);
                                    view_no2_percent_base.setLayoutParams(mLayoutParams);
                                } else if (i == 2) {
                                    textview_table_no3_rate.setText(mTableNumberJson.PercentageList.get(i).Percentage );
                                    view_no3_percent_base.setLayoutParams(mLayoutParams);
                                } else if (i == 3) {
                                    textview_table_no4_rate.setText(mTableNumberJson.PercentageList.get(i).Percentage );
                                    view_no4_percent_base.setLayoutParams(mLayoutParams);
                                } else if (i == 4) {
                                    textview_table_no5_rate.setText(mTableNumberJson.PercentageList.get(i).Percentage);
                                    view_no5_percent_base.setLayoutParams(mLayoutParams);
                                }
                            }

                        } catch (Exception e) {
                            toast("销售排名解析错误！");
                        }
                    }
                    break;
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        HttpBusiness.getTableVolume(textview_table_time_start.getText().toString(), textview_table_time_end.getText().toString(), new TableHttpBusiness());
        if(GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_PROPERTY)){
            navigationSale.setVisibility(View.GONE);
        }
    }

    /**
     * 1 显示成交量
     * 2 显示成交率
     *
     * @param type
     */
    public void initPageView(int type) {
        tableType = type;
        if (type == 1) {
            textview_table_volume.setTextColor(getResources().getColor(R.color.global_text_red));
            view_table_volume.setVisibility(View.VISIBLE);
            textview_table_rate.setTextColor(getResources().getColor(R.color.global_black));
            view_table_rate.setVisibility(View.GONE);
            relativelayout_table_volume_sub.setVisibility(View.VISIBLE);
            common_pro.setVisibility(View.GONE);

            textview_table_no1_volume.setVisibility(View.VISIBLE);
            textview_table_no2_volume.setVisibility(View.VISIBLE);
            textview_table_no3_volume.setVisibility(View.VISIBLE);
            textview_table_no4_volume.setVisibility(View.VISIBLE);
            textview_table_no5_volume.setVisibility(View.VISIBLE);

            textview_table_no1_rate.setVisibility(View.GONE);
            textview_table_no2_rate.setVisibility(View.GONE);
            textview_table_no3_rate.setVisibility(View.GONE);
            textview_table_no4_rate.setVisibility(View.GONE);
            textview_table_no5_rate.setVisibility(View.GONE);

            view_no1_percent_base.setVisibility(View.GONE);
            view_no2_percent_base.setVisibility(View.GONE);
            view_no3_percent_base.setVisibility(View.GONE);
            view_no4_percent_base.setVisibility(View.GONE);
            view_no5_percent_base.setVisibility(View.GONE);
            view_no1_percent_rate.setVisibility(View.GONE);
            view_no2_percent_rate.setVisibility(View.GONE);
            view_no3_percent_rate.setVisibility(View.GONE);
            view_no4_percent_rate.setVisibility(View.GONE);
            view_no5_percent_rate.setVisibility(View.GONE);


        } else {
            textview_table_volume.setTextColor(getResources().getColor(R.color.global_black));
            view_table_volume.setVisibility(View.GONE);
            textview_table_rate.setTextColor(getResources().getColor(R.color.global_text_red));
            view_table_rate.setVisibility(View.VISIBLE);
            relativelayout_table_volume_sub.setVisibility(View.GONE);
            common_pro.setVisibility(View.VISIBLE);

            textview_table_no1_volume.setVisibility(View.GONE);
            textview_table_no2_volume.setVisibility(View.GONE);
            textview_table_no3_volume.setVisibility(View.GONE);
            textview_table_no4_volume.setVisibility(View.GONE);
            textview_table_no5_volume.setVisibility(View.GONE);

            textview_table_no1_rate.setVisibility(View.VISIBLE);
            textview_table_no2_rate.setVisibility(View.VISIBLE);
            textview_table_no3_rate.setVisibility(View.VISIBLE);
            textview_table_no4_rate.setVisibility(View.VISIBLE);
            textview_table_no5_rate.setVisibility(View.VISIBLE);

            view_no1_percent_base.setVisibility(View.VISIBLE);
            view_no2_percent_base.setVisibility(View.VISIBLE);
            view_no3_percent_base.setVisibility(View.VISIBLE);
            view_no4_percent_base.setVisibility(View.VISIBLE);
            view_no5_percent_base.setVisibility(View.VISIBLE);
            view_no1_percent_rate.setVisibility(View.VISIBLE);
            view_no2_percent_rate.setVisibility(View.VISIBLE);
            view_no3_percent_rate.setVisibility(View.VISIBLE);
            view_no4_percent_rate.setVisibility(View.VISIBLE);
            view_no5_percent_rate.setVisibility(View.VISIBLE);


        }
    }

    @Override
    public void initData() {
        super.initData();
        textview_table_no1_no.setVisibility(View.GONE);
        textview_table_no2_no.setVisibility(View.GONE);
        textview_table_no3_no.setVisibility(View.GONE);
        textview_table_no4_no.setVisibility(View.GONE);
        textview_table_no5_no.setVisibility(View.GONE);
        textview_table_no1_name.setText("");
        textview_table_no1_volume.setText("");
        textview_table_no2_name.setText("");
        textview_table_no2_volume.setText("");
        textview_table_no3_name.setText("");
        textview_table_no3_volume.setText("");
        textview_table_no4_name.setText("");
        textview_table_no4_volume.setText("");
        textview_table_no5_name.setText("");
        textview_table_no5_volume.setText("");
        initPageView(1);

    }

    @Override
    public void initWidget() {
        super.initWidget();
        textViewTitle.setText(MainActivity.BuildingName);
        titleImageLeft.setVisibility(View.GONE);
        textview_table_time_start.setText(DateTimeUtil.getDateBeforeMonth());
        textview_table_time_end.setText(DateTimeUtil.getCurrDateStr());

    }

    public void showCalendarDialog() {
        if (builder == null) {
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
                            if (timeType == 1) {
                                if (!StringUtils.isEmpty(time)) {
                                    if (textview_table_time_start.getText().equals(time)) {

                                    } else {
                                        String beginTime = time;
                                        String endTime = textview_table_time_end.getText().toString();
                                        if ((DateTimeUtil.compare_date(beginTime, endTime) <= 0)) {
                                            textview_table_time_start.setText(time);
                                            HttpBusiness.getTableVolume(textview_table_time_start.getText().toString(), textview_table_time_end.getText().toString(), new TableHttpBusiness());
                                        } else {
                                            toast("开始日期不能在结束日期之后！");
                                        }
                                    }


                                }

                            } else {
                                if (textview_table_time_end.getText().equals(time)) {

                                } else {
                                    String beginTime = textview_table_time_start.getText().toString();
                                    String endTime = time;
                                    if ((DateTimeUtil.compare_date(beginTime, endTime) <= 0)) {
                                        textview_table_time_end.setText(time);
                                        HttpBusiness.getTableVolume(textview_table_time_start.getText().toString(), textview_table_time_end.getText().toString(), new TableHttpBusiness());
                                    } else {
                                        toast("开始日期不能在结束日期之后！");
                                    }
                                }

                            }
                            dialog.cancel();
                        }
                    }

            );
            mAlertDialog = builder.create();
        } else {
        }

        mAlertDialog.show();
    }
}
