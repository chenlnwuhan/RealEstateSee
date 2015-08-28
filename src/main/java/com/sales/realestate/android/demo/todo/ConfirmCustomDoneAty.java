package com.sales.realestate.android.demo.todo;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sales.realestate.android.AppContext;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.CustomerSeeInfo;
import com.sales.realestate.android.demo.MainActivity;
import com.sales.realestate.android.view.calendaer.CalendarCard;
import com.sales.realestate.android.view.calendaer.CardGridItem;
import com.sales.realestate.android.view.calendaer.OnCellItemClick;
import com.sales.realestate.android.view.popupwindow.BuildSelectPW;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by cc on 2015/7/25.
 */
public class ConfirmCustomDoneAty extends KJActivity {

    /**
     * 顶部菜单布局文件初始化
     */
    @BindView(id = R.id.title_image_left, click = true)
    private ImageView titleImageLeft;
    @BindView(id = R.id.radiogroup_confirm)
    RadioGroup radiogroup_confirm;
    @BindView(id = R.id.title_name)
    public TextView textViewTitle;

    @BindView(id = R.id.edittext_remarks_add)
    public EditText edittext_remarks_add;

    @BindView(id = R.id.linnearlayout_confirm_root)
    public LinearLayout linnearlayout_confirm_root;

    @BindView(id = R.id.textview_confirm_selected)
    public TextView textview_confirm_selected;

    @BindView(id = R.id.linerlayout_confirm_building, click = true)
    private LinearLayout linerlayout_confirm_building;
    @BindView(id = R.id.relativelayout_building_house)
    private RelativeLayout relativelayout_building_house;

    private BuildSelectPW mBuildSelectPW = null;



    @BindView(id = R.id.textview_confirm_phone)
    public TextView textview_confirm_phone;
    @BindView(id = R.id.textview_confirm_name)
    public TextView textview_confirm_name;
    @BindView(id = R.id.textview_confirm_building)
    public TextView textview_confirm_building;
    @BindView(id = R.id.linearlayout_popup_time, click = true)
    public LinearLayout linearlayout_popup_time;
    @BindView(id = R.id.textview_confirm_time)
    public TextView textview_confirm_time;

    @BindView(id = R.id.textview_confirm_buy_money)
    public EditText textview_confirm_buy_money;

    @BindView(id = R.id.textview_confirm_propertyconsultant)
    public TextView textview_confirm_propertyconsultant;
    @BindView(id = R.id.textview_confirm_applicants_phone)
    public TextView textview_confirm_applicants_phone;
    @BindView(id = R.id.textview_confirm_applicants_name)
    public TextView textview_confirm_applicants_name;
    @BindView(id = R.id.textview_confirm_applicants_time)
    public TextView textview_confirm_applicants_time;
    @BindView(id = R.id.textview_confirm_remarks)
    public TextView textview_confirm_remarks;
    @BindView(id = R.id.textview_confirm_pay)
    public EditText textview_confirm_pay;
    @BindView(id = R.id.btn_confirm_send)
    public Button btn_confirm_send;
    private String BuildingID = "";
    private String CustomerID = "";
    private String ApplyID = "";
    private int isLook = 1;
    private CustomerSeeInfo mCustomerSeeInfo = new CustomerSeeInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivityActionMode(ActionBarMode.CUSTOMTITILE);
        setTitileResId(R.layout.view_title_simple);
        setRootViewResId(R.layout.aty_confirm_custom_done);
        setmBottomNavigation(BottomNavigation.JUSTNOBOTTOM);
        super.onCreate(savedInstanceState);
    }

    public void initView() {
        edittext_remarks_add.setVisibility(View.VISIBLE);
        textview_confirm_phone.setText(mCustomerSeeInfo.Mobile);
        textview_confirm_name.setText(mCustomerSeeInfo.CustomerName);
        textview_confirm_building.setText(mCustomerSeeInfo.RoomName);
        textview_confirm_time.setText(mCustomerSeeInfo.OkTime);
        textview_confirm_propertyconsultant.setText(mCustomerSeeInfo.ConsultantName);
        textview_confirm_pay.setText("0");
        String applyName = "";
        String applyPhone = "";
        if (!StringUtils.isEmpty(mCustomerSeeInfo.ApplyName)) {
            String[] tmp = mCustomerSeeInfo.ApplyName.split(" ");
            applyName = tmp[0];
            if (tmp.length > 1) {
                applyPhone = tmp[1];
            }
        }

        textview_confirm_applicants_phone.setText(applyPhone);
        textview_confirm_applicants_name.setText(applyName);
        textview_confirm_applicants_time.setText(mCustomerSeeInfo.ApplyTime);
        textview_confirm_remarks.setText(mCustomerSeeInfo.OkDetails);
        textview_confirm_buy_money.setText(mCustomerSeeInfo.OkMoney.replace("/元",""));
        btn_confirm_send.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String remork = edittext_remarks_add.getText().toString();
                boolean isDai = StringUtils.isNumber(textview_confirm_pay.getText().toString());
                String buyMoney = textview_confirm_buy_money.getText().toString();
                String roomName = textview_confirm_building.getText().toString();
                if (isLook == -1) {
                    toast("请选择审查结果！");
                }else if(!isDai){
                    toast("请输入正确的待结佣金！");
                }else if (!StringUtils.isNumber(buyMoney)) {
                    toast("请填写正确的成交金额！");
                }  else if (remork == null || StringUtils.isEmpty(remork.trim())) {
                    toast("请填写备注！");
                } else {
                    HttpBusiness.updateCustomDone(BuildingID, CustomerID, ApplyID, String.valueOf(isLook), remork.trim(),textview_confirm_pay.getText().toString(),mCustomerSeeInfo.NewsHouseID,MainActivity.BuildingName,roomName, textview_confirm_time.getText().toString(), buyMoney,mCustomerSeeInfo.CustomerName,new ToDoListDetailHttpBusiness());
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            BuildingID = bundle.getString("BuildingID");
            CustomerID = bundle.getString("CustomerID");
            ApplyID = bundle.getString("ApplyID");
            if (StringUtils.isEmpty(BuildingID)) {
                BuildingID = "";
            }
            if (StringUtils.isEmpty(CustomerID)) {
                CustomerID = "";
            }
            if (StringUtils.isEmpty(ApplyID)) {
                ApplyID = "";
            }
            HttpBusiness.getCustomDone(BuildingID, CustomerID, ApplyID, new ToDoListDetailHttpBusiness());
        }
    }

    public class ToDoListDetailHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_KEY_GET_CUSTOM_DONE:
                    if (isError) {
                        toast(textViewTitle.getText().toString() + "未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            mCustomerSeeInfo = gson.fromJson(returnStr, CustomerSeeInfo.class);
                            if (mCustomerSeeInfo != null) {
                                initView();
                            }
                        } catch (Exception e) {
                            toast(textViewTitle.getText().toString() + "解析错误！");
                        }
                    }
                    break;

                case HttpBusiness.HTTP_KEY_UPDATE_CUSTOM_DONE:
                    if (isError) {
                        toast("界定客户成交失败！");

                    } else {
                        toast("界定客户成交成功！");
                        mHandler.postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                onBackPressed();
                            }
                        }, GlobalVarible.GLOBALDELAY);

                    }
                    break;
            }
        }
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
                                    textview_confirm_time.setText(time);
                                }

                            } else {
                                if (!StringUtils.isEmpty(time)) {
                                    textview_confirm_time.setText(time);
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

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.title_image_left:
                onBackPressed();
                break;
            case R.id.linerlayout_confirm_building:
                if (mBuildSelectPW == null) {
                mBuildSelectPW = new BuildSelectPW(this);
                mBuildSelectPW.setBackgroundDrawable(new BitmapDrawable());
                mBuildSelectPW.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
                mBuildSelectPW.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        if(StringUtils.isEmpty(mBuildSelectPW.buildName))return ;
                        textview_confirm_building.setText(mBuildSelectPW.buildName);
                        mCustomerSeeInfo.NewsHouseID = mBuildSelectPW.houseid;
                    }
                });
            }
            mBuildSelectPW.setBuyType(1);
            mBuildSelectPW.showAsDropDown(relativelayout_building_house, 0, 1);
            break;

            case R.id.linearlayout_popup_time:
                showCalendarDialog();
                break;
        }
    }

    public void initTitle(int pozition) {
        textViewTitle.setText("成交确认");
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initConfirmModule();
        initTitle(0);
        ((View) linnearlayout_confirm_root.getParent()).setBackgroundColor(getResources().getColor(R.color.confirm_backgroud_color));

    }

    public void initConfirmModule() {
        radiogroup_confirm.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (R.id.radio_youxiao == checkedId) {
                    isLook = 1;
//                    edittext_remarks_add.setVisibility(View.GONE);
                } else {
                    isLook = 2;
//                    edittext_remarks_add.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}