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
import android.widget.TextView;

import com.sales.realestate.android.AppContext;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.CustomInfo;
import com.sales.realestate.android.bean.FollowDetailInfo;
import com.sales.realestate.android.bean.SpinnerItemInfo;
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
public class CustomDetailFollowPW extends PopupWindow {
    private View conentView;

    private LinearLayout linearlayout_popup_time;
    private TextView textview_popup_time;

    private EditText edittext_remarks_add;
    public LinearLayout linnearlayout_follow_type;
    private TextView textview_follow_type;
    private ImageView image_phone_type;

    public String seletctFollowType ="0";

    private TextView textview_custom_distribution_cancel;
    private TextView textview_custom_distribution_ok;

    private ArrayList<SpinnerItemInfo> spinnerObjectArrayList = GlobalVarible.getSpinnerCustomFollowtype();

    private PopupWindow dropDownWindown1 = null;
    public FollowDetailInfo mFollowDetailInfo = new FollowDetailInfo();
    public CustomInfo mcustomInfo = new CustomInfo();

    /**
     * 1 详细模式
     * 2 修改模式
     */
    public int buyType = 1;

    public void setCustomInfo(CustomInfo customInfo) {
        mcustomInfo = customInfo;
    }

    public void setFollowDetailInfo(FollowDetailInfo mFollowDetailInfoT) {
        mFollowDetailInfo = mFollowDetailInfoT;
    }

    public void setBuyType(int typeIndex) {
        buyType = typeIndex;
        if (buyType == 1) {
            mcustomInfo.reflashType = 0;
            image_phone_type.setVisibility(View.VISIBLE);
            linnearlayout_follow_type.setClickable(true);
            edittext_remarks_add.setEnabled(true);
            linearlayout_popup_time.setClickable(true);
            textview_popup_time.setText(DateTimeUtil.getCurrDateStr());
            GlobalVarible.initTitleListFocus(spinnerObjectArrayList, -1, 0);
            textview_follow_type.setText(spinnerObjectArrayList.get(0).getName());
            edittext_remarks_add.setText("");
            textview_custom_distribution_ok.setVisibility(View.VISIBLE);
            seletctFollowType = spinnerObjectArrayList.get(0).getId();
        } else {
            mcustomInfo.reflashType = 0;
            image_phone_type.setVisibility(View.GONE);
            linnearlayout_follow_type.setClickable(false);
            edittext_remarks_add.setEnabled(false);
            linearlayout_popup_time.setClickable(false);
            textview_popup_time.setText(mFollowDetailInfo.AddTime);
            GlobalVarible.initTitleListFocus(spinnerObjectArrayList, -1, mFollowDetailInfo.FollowTypeName);
            textview_custom_distribution_ok.setVisibility(View.GONE);
            textview_follow_type.setText(mFollowDetailInfo.FollowTypeName);
            edittext_remarks_add.setText(mFollowDetailInfo.Details);
        }
    }

    public CustomDetailFollowPW(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popup_custom_detail_follow, null);
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);

        initPopUpWindows();
        initView();
        // 刷新状态
        this.update();
        GlobalVarible.initTitleListFocus(spinnerObjectArrayList, -1, 0);
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

    public void initView() {

        linearlayout_popup_time = (LinearLayout) conentView.findViewById(R.id.linearlayout_popup_time);
        textview_popup_time = (TextView) conentView.findViewById(R.id.textview_popup_time);
        edittext_remarks_add = (EditText) conentView.findViewById(R.id.edittext_remarks_add);
        linnearlayout_follow_type = (LinearLayout) conentView.findViewById(R.id.linnearlayout_follow_type);
        textview_follow_type = (TextView) conentView.findViewById(R.id.textview_follow_type);
        image_phone_type = (ImageView) conentView.findViewById(R.id.image_phone_type);
        textview_custom_distribution_cancel = (TextView) conentView.findViewById(R.id.textview_custom_distribution_cancel);
        textview_custom_distribution_ok = (TextView) conentView.findViewById(R.id.textview_custom_distribution_ok);
        linnearlayout_follow_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] viewLocation = new int[2];
                v.getLocationInWindow(viewLocation);
                int viewX = viewLocation[0]; // x 坐标
                int viewY = viewLocation[1]; // y 坐标
                int tt = linnearlayout_follow_type.getHeight();
                dropDownWindown1 = showDropDown(viewX, viewY + 100, textview_follow_type, image_phone_type, dropDownWindown1, linnearlayout_follow_type.getWidth(), spinnerObjectArrayList);
            }
        });
        linearlayout_popup_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendarDialog();
            }
        });
        textview_custom_distribution_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDetailFollowPW.this.dismiss();


            }
        });
        textview_custom_distribution_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buyType==2){
                    CustomDetailFollowPW.this.dismiss();
                }
                else{
                    if(StringUtils.isEmpty(edittext_remarks_add.getText().toString())){
                        AppContext.getCurrentActivity().toast("请输入跟进详情");
                        return;
                    }
                    HttpBusiness.getAddCusomFollowDetail(mcustomInfo.CustomerID,seletctFollowType,edittext_remarks_add.getText().toString(), new CustomAddFollowInfoHttpBusiness());
                }
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

    public class CustomAddFollowInfoHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_KEY_CUSTOM_FOLLOWINFO_ADD:
                    if (isError) {
                        AppContext.getCurrentActivity().toast("客户跟进信息添加失败！");
                    } else {
                        try {
                            AppContext.getCurrentActivity().toast("客户跟进信息添加成功！");
                            textview_custom_distribution_cancel.setClickable(false);
                            textview_custom_distribution_ok.setClickable(false);
                            mcustomInfo.reflashType = 1;
                            mHandler.postDelayed(mRunnable, GlobalVarible.GLOBALDELAY);
                        } catch (Exception e) {
                            AppContext.getCurrentActivity().toast("客户跟进信息添加结果解析错误！");
                        }
                    }
                    break;

            }
        }
    }

    public Handler mHandler = new Handler();

    public Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            textview_custom_distribution_cancel.setClickable(true);
            textview_custom_distribution_ok.setClickable(true);
            CustomDetailFollowPW.this.dismiss();
        }
    };

    public PopupWindow showDropDown(int pointX, int ponintY, final TextView targetTextView, final ImageView targetImageView, PopupWindow targetPopup, int popupWidth, final ArrayList<SpinnerItemInfo> targetList) {
        targetImageView.setImageResource(R.drawable.pic_drop_up);
        if (targetPopup == null) {
            ListView listView = new ListView(AppContext.getCurrentActivity());
            listView.setDivider(null);
            listView.setSelector(new ColorDrawable(Color.TRANSPARENT));
            listView.setCacheColorHint(Color.TRANSPARENT);
            listView.setDividerHeight(1);
            listView.setVerticalScrollBarEnabled(false);
            final ListOtherSpinnerAdapter hadapter = new ListOtherSpinnerAdapter(targetList);
            listView.setAdapter(hadapter);

            targetPopup = new PopupWindow(listView, popupWidth, ActionBar.LayoutParams.WRAP_CONTENT, true);
            targetPopup.setBackgroundDrawable(new BitmapDrawable());
            final PopupWindow finalTargetPopup = targetPopup;
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    targetTextView.setText(targetList.get(position).getName());
                    GlobalVarible.initTitleListFocus(targetList, -1, position);
                    seletctFollowType = spinnerObjectArrayList.get(position).getId();
                    hadapter.notifyDataSetChanged();
                    finalTargetPopup.dismiss();
                }
            });
            finalTargetPopup.setOnDismissListener(new OnDismissListener() {
                @Override
                public void onDismiss() {
                    targetImageView.setImageResource(R.drawable.pic_popup_custom_dropdown);
                }
            });
            finalTargetPopup.setOutsideTouchable(true);
        }
        targetPopup.showAtLocation(AppContext.getCurrentActivity().getWindow().getDecorView(), Gravity.NO_GRAVITY, pointX, ponintY);
        return targetPopup;
    }
}

