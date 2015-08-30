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
public class CustomDonePW extends PopupWindow {
    private View conentView;

    private TextView textview_title_custom_popup;
    private TextView textview_custom_distribution_cancel;
    private TextView textview_custom_distribution_ok;
    private RelativeLayout relativelayout_title_custom_propertyconsultant;

    private LinearLayout linearlayout_popup_time;
    private TextView textview_popup_time;

    public LinearLayout linearlayout_pupup_buildno;
    private TextView textview_popup_buildno;
    private ImageView img_popup_buildno;

    public LinearLayout linearlayout_popup_paytype;
    private TextView textview_popup_paytype;
    private ImageView image_popup_paytype;

    public TextView textview_custom_phone;
    public TextView textview_custom_name;
    public TextView textview_propertyconsultant_phone;
    public TextView textview_propertyconsultant_name;
    public TextView textview_linearlayout_pay;
    public TextView edittext_remarks_pay;

    public EditText edittext_pupup_money;
    public EditText edittext_remarks_add;

    public String roomID;
    public String roomName;
    private BuildSelectPW mBuildSelectPW = null;
    public String payType;
    public  ArrayList<String> selectCustomIds = new ArrayList<String>();
    public PropertyConsultant mPropertyConsultant;
    public ArrayList<CustomInfo> selectCustomList = new ArrayList<CustomInfo>();

    private ArrayList<SpinnerItemInfo> spinnerObjectArrayList1 = GlobalVarible.getSPINNER_BUILDNO_LIST();
    private PopupWindow dropDownWindown1 = null;
    private ArrayList<SpinnerItemInfo> spinnerObjectArrayList2 = GlobalVarible.getSpinnerMoneyType();
    private PopupWindow dropDownWindown2 = null;
    /**
     * 1 认购申请
     * 2 认购确定
     */
    public int buyType = 1;

    public void setBuyType(int typeIndex) {
        buyType = typeIndex;
        if (buyType == 1) {
            relativelayout_title_custom_propertyconsultant.setVisibility(View.GONE);
            textview_title_custom_popup.setText("申请成交");
            textview_linearlayout_pay.setVisibility(View.GONE);
            edittext_remarks_pay.setVisibility(View.GONE);
        } else {
            relativelayout_title_custom_propertyconsultant.setVisibility(View.VISIBLE);
            textview_title_custom_popup.setText("确认成交");
            textview_linearlayout_pay.setVisibility(View.VISIBLE);
            edittext_remarks_pay.setVisibility(View.VISIBLE);
        }
    }

    public CustomDonePW(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popup_custom_done, null);
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);

        initPopUpWindows();
        initView();
        // 刷新状态
        this.update();
        GlobalVarible.initTitleListFocus(spinnerObjectArrayList1, -1, 0);
        GlobalVarible.initTitleListFocus(spinnerObjectArrayList2, -1, 0);
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

    public class CustomDoneHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_KEY_CUSTOM_DONE_APPLY:
                    if (isError) {
                        AppContext.getCurrentActivity().toast("成交申请失败！");
                    } else {
                        try {
                            AppContext.getCurrentActivity().toast("成交申请成功！");
                            textview_custom_distribution_cancel.setClickable(false);
                            textview_custom_distribution_ok.setClickable(false);
                            mHandler.postDelayed(mRunnable, GlobalVarible.GLOBALDELAY);
                        } catch (Exception e) {
                            AppContext.getCurrentActivity().toast("成交申请结果解析错误！");
                        }
                    }
                    break;
                case HttpBusiness.HTTP_KEY_CUSTOM_DONE_CONFIRM:
                    if (isError) {
                        AppContext.getCurrentActivity().toast("成交确认失败！");
                    } else {
                        try {
                            AppContext.getCurrentActivity().toast("成交成功！");
                            textview_custom_distribution_cancel.setClickable(false);
                            textview_custom_distribution_ok.setClickable(false);
                            mHandler.postDelayed(mRunnable, GlobalVarible.GLOBALDELAY);
                        } catch (Exception e) {
                            AppContext.getCurrentActivity().toast("成交确认结果解析错误！");
                        }
                    }
                    break;

            }
        }
    }

    public void refresh() {
        payType = spinnerObjectArrayList2.get(0).getId();
        edittext_remarks_pay.setText("");
        textview_popup_buildno.setText("请选择房号");
        textview_popup_paytype.setText(spinnerObjectArrayList2.get(0).getName());
        selectCustomList = new ArrayList<CustomInfo>();
        edittext_pupup_money.setText("");
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
        roomID = selectCustomList.get(0).NewsHouseID;
        roomName = selectCustomList.get(0).RoomName;
        textview_popup_buildno.setText(selectCustomList.get(0).RoomName);
    }

    public Handler mHandler = new Handler();

    public Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            CustomDonePW.this.dismiss();
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
        edittext_pupup_money = (EditText) conentView.findViewById(R.id.edittext_pupup_money);
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
                CustomDonePW.this.dismiss();
            }
        });
        textview_custom_distribution_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(edittext_pupup_money.getText().toString())) {
                    AppContext.getCurrentActivity().toast("请填入成交金额！");
                } else if (!StringUtils.isNumber(edittext_pupup_money.getText().toString())) {
                    AppContext.getCurrentActivity().toast("成交金额格式不正确！");
                } else if ("请选择房号".equals(textview_popup_buildno.getText().toString())) {
                    AppContext.getCurrentActivity().toast("请选择房号！");
                } else {
                    if (buyType == 1) {
                        HttpBusiness.getCustomDoneApply(CustomAty.BuildingID, edittext_remarks_add.getText().toString(), textview_popup_time.getText().toString(), selectCustomList.get(0).CustomerID, roomID, textview_popup_buildno.getText().toString(), edittext_pupup_money.getText().toString(), payType, edittext_remarks_pay.getText().toString(),edittext_pupup_money.getText().toString(),new CustomDoneHttpBusiness());
                    } else {
                        if (StringUtils.isEmpty(edittext_remarks_pay.getText().toString())) {
                            AppContext.getCurrentActivity().toast("请填入佣金金额！");
                        } else if (!StringUtils.isNumber(edittext_remarks_pay.getText().toString())) {
                            AppContext.getCurrentActivity().toast("佣金金额格式不正确！");
                        } else {
                            HttpBusiness.getCustomDoneConfirm(CustomAty.BuildingID, edittext_remarks_add.getText().toString(), textview_popup_time.getText().toString(), selectCustomList.get(0).CustomerID, roomID, textview_popup_buildno.getText().toString(), edittext_pupup_money.getText().toString(), payType, edittext_remarks_pay.getText().toString(),edittext_pupup_money.getText().toString(), new CustomDoneHttpBusiness());

                        }
                    }
                }
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

        linearlayout_pupup_buildno = (LinearLayout) conentView.findViewById(R.id.linearlayout_pupup_buildno);
        textview_popup_buildno = (TextView) conentView.findViewById(R.id.textview_popup_buildno);
        img_popup_buildno = (ImageView) conentView.findViewById(R.id.img_popup_buildno);
        linearlayout_pupup_buildno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mBuildSelectPW == null) {
                    mBuildSelectPW = new BuildSelectPW(AppContext.getCurrentActivity());
                    mBuildSelectPW.setBackgroundDrawable(new BitmapDrawable());
                    mBuildSelectPW.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
                    mBuildSelectPW.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            if(StringUtils.isEmpty(mBuildSelectPW.buildName))return ;;

                            roomID  = mBuildSelectPW.houseid;
                            roomName = mBuildSelectPW.housename;
                            textview_popup_buildno.setText(mBuildSelectPW.buildName);
                        }
                    });
                }
                mBuildSelectPW.setBuyType(1);
                mBuildSelectPW.reflashSelect();


                int[] viewLocation = new int[2];
                v.getLocationInWindow(viewLocation);
                int viewX = viewLocation[0]; // x 坐标
                int viewY = viewLocation[1]; // y 坐标
                int tt = v.getHeight();
                mBuildSelectPW.showAtLocation(AppContext.getCurrentActivity().getWindow().getDecorView(), Gravity.NO_GRAVITY, 0, viewY + v.getHeight() * 2);
            }
        });

        linearlayout_popup_paytype = (LinearLayout) conentView.findViewById(R.id.linearlayout_popup_paytype);
        textview_popup_paytype = (TextView) conentView.findViewById(R.id.textview_popup_paytype);
        image_popup_paytype = (ImageView) conentView.findViewById(R.id.image_popup_paytype);
        linearlayout_popup_paytype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] viewLocation = new int[2];
                v.getLocationInWindow(viewLocation);
                int viewX = viewLocation[0]; // x 坐标
                int viewY = viewLocation[1]; // y 坐标
                int tt = v.getHeight();
                dropDownWindown2 = showDropDown(viewX, viewY + v.getHeight() * 2, textview_popup_paytype, image_popup_paytype, dropDownWindown2, v.getWidth(), spinnerObjectArrayList2);
            }
        });
        textview_linearlayout_pay = (TextView) conentView.findViewById(R.id.textview_linearlayout_pay);
        edittext_remarks_pay = (TextView) conentView.findViewById(R.id.edittext_remarks_pay);

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
                    if (StringUtils.isEmpty(targetList.get(position).RoomName)) {
                        payType = targetList.get(position).getId();
                    } else {
                        roomID = targetList.get(position).getId();
                        roomName = targetList.get(position).RoomName;
                    }

                    targetTextView.setText(targetList.get(position).getName());
                    GlobalVarible.initTitleListFocus(targetList, -1, position);
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
