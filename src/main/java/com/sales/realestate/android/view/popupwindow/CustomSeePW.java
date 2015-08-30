package com.sales.realestate.android.view.popupwindow;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sales.realestate.android.AppContext;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.CustomInfo;
import com.sales.realestate.android.bean.PropertyConsultant;
import com.sales.realestate.android.bean.SpinnerItemInfo;
import com.sales.realestate.android.demo.custom.CustomAty;
import com.sales.realestate.android.view.adapter.ListOtherSpinnerAdapter;
import com.sales.realestate.android.view.calendaer.CalendarCard;
import com.sales.realestate.android.view.calendaer.CardGridItem;
import com.sales.realestate.android.view.calendaer.OnCellItemClick;

import org.kymjs.kjframe.utils.DateTimeUtil;
import org.kymjs.kjframe.utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by cc on 2015/7/23.
 */
public class CustomSeePW extends PopupWindow {
    private View conentView;

    private TextView textview_title_custom_popup;
    private TextView textview_custom_distribution_cancel;
    private TextView textview_custom_distribution_ok;
    private RelativeLayout relativelayout_title_custom_propertyconsultant;
    private LinearLayout linearlayout_popup_time;
    private TextView textview_popup_time;

    public TextView textview_custom_phone;
    public TextView textview_custom_name;
    public TextView textview_propertyconsultant_phone;
    public TextView textview_propertyconsultant_name;


    public EditText edittext_remarks_add;


    public  ArrayList<String> selectCustomIds = new ArrayList<String>();
    public PropertyConsultant mPropertyConsultant;
    public ArrayList<CustomInfo> selectCustomList = new ArrayList<CustomInfo>();

    /**
     * 1 认购申请
     * 2 认购确定
     */
    public int buyType = 1;

    public void setBuyType(int typeIndex) {
        buyType = typeIndex;
        if (buyType == 1) {
            relativelayout_title_custom_propertyconsultant.setVisibility(View.GONE);
            textview_title_custom_popup.setText("确认带看");
        } else {
            relativelayout_title_custom_propertyconsultant.setVisibility(View.VISIBLE);
            textview_title_custom_popup.setText("确认带看");
        }
    }

    public CustomSeePW(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popup_custom_see, null);
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);

        initPopUpWindows();
        initView();
        // 刷新状态
        this.update();
    }

    public void initPopUpWindows() {
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);

        //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
        this.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    public class CustomSeedHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_KEY_CUSTOM_SEED:
                    if (isError) {
                        AppContext.getCurrentActivity().toast("确认带看失败！");
                    } else {
                        try {
                            AppContext.getCurrentActivity().toast("带看成功！");
                            textview_custom_distribution_cancel.setClickable(false);
                            textview_custom_distribution_ok.setClickable(false);
                            mHandler.postDelayed(mRunnable, GlobalVarible.GLOBALDELAY);
                        } catch (Exception e) {
                            AppContext.getCurrentActivity().toast("确认带看结果解析错误！");
                        }
                    }
                    break;

            }
        }
    }

    public void refresh() {
        selectCustomList = new ArrayList<CustomInfo>();
        edittext_remarks_add.setText("");
        textview_popup_time.setText(DateTimeUtil.getCurrDateStr());
        for (int i = 0; i < selectCustomIds.size(); i++) {
            CustomInfo mCustomInfo = GlobalVarible.findCustomByCustomIdInCustomAnalyseList(selectCustomIds.get(i));
            if (mCustomInfo != null) {
                selectCustomList.add(mCustomInfo);
            }
        }
        if (selectCustomList.size() == 0) {
            AppContext.getCurrentActivity().toast("没有选择客户！");
        }
        mPropertyConsultant = GlobalVarible.findPropertyConsultantByNameInPropertyConsultant(selectCustomList.get(0).UserName);

        if (mPropertyConsultant != null) {
            textview_propertyconsultant_phone.setText(mPropertyConsultant.Mobile);
            textview_propertyconsultant_name.setText(selectCustomList.get(0).UserName);
        }
        textview_custom_phone.setText(selectCustomList.get(0).Mobile);
        textview_custom_name.setText(selectCustomList.get(0).CustomerName);
        textview_custom_distribution_cancel.setClickable(true);
        textview_custom_distribution_ok.setClickable(true);
    }

    public Handler mHandler = new Handler();

    public Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            CustomSeePW.this.dismiss();
        }
    };

    public void initView() {
        textview_custom_phone = (TextView) conentView.findViewById(R.id.textview_custom_phone);
        textview_custom_name = (TextView) conentView.findViewById(R.id.textview_custom_name);
        ;
        textview_propertyconsultant_phone = (TextView) conentView.findViewById(R.id.textview_propertyconsultant_phone);
        ;
        textview_propertyconsultant_name = (TextView) conentView.findViewById(R.id.textview_propertyconsultant_name);
        ;
        ;
        edittext_remarks_add = (EditText) conentView.findViewById(R.id.edittext_remarks_add);
        ;

        relativelayout_title_custom_propertyconsultant = (RelativeLayout) conentView.findViewById(R.id.relativelayout_title_custom_propertyconsultant);
        textview_title_custom_popup = (TextView) conentView.findViewById(R.id.textview_title_custom_popup);
        textview_custom_distribution_cancel = (TextView) conentView.findViewById(R.id.textview_custom_distribution_cancel);
        textview_custom_distribution_ok = (TextView) conentView.findViewById(R.id.textview_custom_distribution_ok);
        textview_custom_distribution_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomSeePW.this.dismiss();
            }
        });
        textview_custom_distribution_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpBusiness.goCustomSeed(selectCustomList.get(0).CustomerID,textview_popup_time.getText().toString(),edittext_remarks_add.getText().toString(),CustomAty.BuildingID, new CustomSeedHttpBusiness());
            }
        });
        textview_popup_time = (TextView) conentView.findViewById(R.id.textview_popup_time);
        linearlayout_popup_time = (LinearLayout) conentView.findViewById(R.id.linearlayout_popup_time);
        linearlayout_popup_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendarDialog();
            }
        });
    }

    public int timeType = 0;
    public String time;

    AlertDialog.Builder builder;
    AlertDialog mAlertDialog;
    CalendarCard card;

    public void showCalendarDialog() {
        if (builder == null) {
            builder = new AlertDialog.Builder(AppContext.getCurrentActivity());
            Calendar cal = Calendar.getInstance();
            card = new CalendarCard(AppContext.getCurrentActivity());
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
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (timeType == 1) {
                                if (!StringUtils.isEmpty(time)) {
                                    textview_popup_time.setText(time);
                                }

                            } else {
                                if (!StringUtils.isEmpty(time)) {
                                    textview_popup_time.setText(time);
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
