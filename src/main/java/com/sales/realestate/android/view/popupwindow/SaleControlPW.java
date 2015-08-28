package com.sales.realestate.android.view.popupwindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.HouseInfo;
import com.sales.realestate.android.bean.SailHouseDetail;

import org.kymjs.kjframe.utils.KJLoger;
import org.kymjs.kjframe.utils.StringUtils;

/**
 * Created by cc on 2015/7/23.
 */
public class SaleControlPW extends PopupWindow {
    private View conentView;

    private RadioGroup radiogroup_lock_type;
    private RelativeLayout relativelayout_sale_moneytype;

    private RadioGroup radiogroup_money_taype;

    private RelativeLayout relativelayout_sale_dong_money;

    private RelativeLayout relativelayout_sale_lock_company;

    private RelativeLayout relativelayout_sale_lock_property;

    private RelativeLayout relativelayout_sale_lock_date;

    private RelativeLayout relativelayout_sale_lock_sale_info;

    private RelativeLayout relativelayout_sale_lock_moneybuy;

    private TextView textview_sale_lock_date_title;
    private RadioButton radio_lock;
    private RadioButton radio_done;
    private TextView textview_sale_lock_moneybuy_title;
    private TextView textview_sale_lock_sale_info_company;
    private TextView textview_sale_lock_property;
    private TextView textview_sale_lock_date;
    private TextView textview_sale_lock_sale_info;
    private TextView textview_sale_lock_moneybuy;
    private TextView textview_sale_lock_huxin;
    private TextView textview_sale_lock_mianji;
    private EditText textview_sale_dong_money;
    private EditText edit_sale_lock_company;
    private Button  button_sale_lock;
    private Button  button_sale_cancel;
    private TextView textview_title_custom_popup;

    private String PayName = "全款";
    private HouseInfo info;
    private Context mContext;


    public Boolean isNeedReflash = false ;


    /**
     * 0是未售出，1是已售,2是认购锁定 ,3是预留,4已购,5是已售
     * 1  未售(0) -可以做锁定和成交按钮选择
     * 2  未售(0) -做了成交按钮操作
     * 3  认购锁定(2)，已购(4)
     * 4  已售(1)
     * 5  已售信息(5)
     * 6  预留(3) 做解锁
     */
    public int buyType = 1;

    public void setSailHouseDetail(SailHouseDetail info) {
        if(info.Housemj==null){
            info.Housemj = "";
        }
        if(info.Househx==null){
            info.Househx = "";
        }
        if(buyType != 5){
            textview_sale_lock_property.setText(info.UserName);
            textview_sale_lock_huxin.setText(info.Househx);
            textview_sale_lock_mianji.setText(info.Housemj+ "m²");
        }else {
            textview_sale_lock_property.setText("");
            textview_sale_lock_date.setText("");
        }
        if (buyType==4) {
            textview_sale_lock_sale_info.setText("我售");
            textview_sale_lock_sale_info_company.setVisibility(View.GONE);
        } else {
            textview_sale_lock_sale_info.setText("已售");
        }
        if (buyType == 3) {
            textview_sale_lock_date.setText(info.SetTime);
            textview_sale_lock_moneybuy.setText(info.SetMoney);
        }else if(buyType ==5){
            textview_sale_lock_sale_info_company.setText(info.Remark);
            textview_sale_lock_moneybuy.setText(info.OkMoney);
        } else {
            String time = "";
            if(!StringUtils.isEmpty(info.OkTime)){
                time = info.OkTime.split(" ")[0];
            }
            textview_sale_lock_date.setText(time);
            textview_sale_lock_moneybuy.setText(info.OkMoney);
        }
    }


    public void setBuyType(int typeIndex) {
        buyType = typeIndex;
        isNeedReflash = false ;
        button_sale_lock.setText("确定");
        if (buyType == 1) {
            radiogroup_lock_type.setVisibility(View.VISIBLE);
            relativelayout_sale_moneytype.setVisibility(View.GONE);
            relativelayout_sale_dong_money.setVisibility(View.GONE);
            relativelayout_sale_lock_company.setVisibility(View.GONE);
            relativelayout_sale_lock_property.setVisibility(View.GONE);
            relativelayout_sale_lock_date.setVisibility(View.GONE);
            relativelayout_sale_lock_sale_info.setVisibility(View.GONE);
            relativelayout_sale_lock_moneybuy.setVisibility(View.GONE);
            button_sale_lock.setVisibility(View.VISIBLE);
            button_sale_cancel.setVisibility(View.VISIBLE);
        }else if (buyType == 7) {
            radiogroup_lock_type.setVisibility(View.GONE);
            relativelayout_sale_moneytype.setVisibility(View.GONE);
            relativelayout_sale_dong_money.setVisibility(View.GONE);
            relativelayout_sale_lock_company.setVisibility(View.GONE);
            relativelayout_sale_lock_property.setVisibility(View.GONE);
            relativelayout_sale_lock_date.setVisibility(View.GONE);
            relativelayout_sale_lock_sale_info.setVisibility(View.GONE);
            relativelayout_sale_lock_moneybuy.setVisibility(View.GONE);
            button_sale_cancel.setVisibility(View.GONE);
            button_sale_lock.setVisibility(View.VISIBLE);
        } else if (buyType == 2) {
            radiogroup_lock_type.setVisibility(View.VISIBLE);
            relativelayout_sale_moneytype.setVisibility(View.VISIBLE);
            relativelayout_sale_dong_money.setVisibility(View.VISIBLE);
            relativelayout_sale_lock_company.setVisibility(View.VISIBLE);
            relativelayout_sale_lock_property.setVisibility(View.GONE);
            relativelayout_sale_lock_date.setVisibility(View.GONE);
            relativelayout_sale_lock_sale_info.setVisibility(View.GONE);
            relativelayout_sale_lock_moneybuy.setVisibility(View.GONE);
            button_sale_lock.setVisibility(View.VISIBLE);
            button_sale_cancel.setVisibility(View.VISIBLE);
        } else if (buyType == 3) {
            radiogroup_lock_type.setVisibility(View.GONE);
            relativelayout_sale_moneytype.setVisibility(View.GONE);
            ;
            relativelayout_sale_dong_money.setVisibility(View.GONE);
            relativelayout_sale_lock_company.setVisibility(View.GONE);
            relativelayout_sale_lock_property.setVisibility(View.VISIBLE);
            relativelayout_sale_lock_date.setVisibility(View.VISIBLE);
            relativelayout_sale_lock_sale_info.setVisibility(View.GONE);
            relativelayout_sale_lock_moneybuy.setVisibility(View.VISIBLE);
            button_sale_lock.setVisibility(View.VISIBLE);
            textview_sale_lock_date_title.setText("认购日期");
            textview_sale_lock_moneybuy_title.setText("认购金额");
            button_sale_cancel.setVisibility(View.GONE);
        } else if (buyType == 4) {
            radiogroup_lock_type.setVisibility(View.GONE);
            relativelayout_sale_moneytype.setVisibility(View.GONE);
            ;
            relativelayout_sale_dong_money.setVisibility(View.GONE);
            relativelayout_sale_lock_company.setVisibility(View.GONE);
            relativelayout_sale_lock_property.setVisibility(View.VISIBLE);
            relativelayout_sale_lock_date.setVisibility(View.VISIBLE);
            relativelayout_sale_lock_sale_info.setVisibility(View.VISIBLE);
            relativelayout_sale_lock_moneybuy.setVisibility(View.VISIBLE);
            button_sale_lock.setVisibility(View.VISIBLE);
            textview_sale_lock_date_title.setText("成交日期");
            textview_sale_lock_moneybuy_title.setText("成交金额");
            textview_sale_lock_sale_info_company.setVisibility(View.GONE);
            button_sale_cancel.setVisibility(View.GONE);
        } else if (buyType == 5) {
            radiogroup_lock_type.setVisibility(View.GONE);
            relativelayout_sale_moneytype.setVisibility(View.GONE);
            relativelayout_sale_dong_money.setVisibility(View.GONE);
            relativelayout_sale_lock_company.setVisibility(View.GONE);
            relativelayout_sale_lock_property.setVisibility(View.VISIBLE);
            relativelayout_sale_lock_date.setVisibility(View.VISIBLE);
            relativelayout_sale_lock_sale_info.setVisibility(View.VISIBLE);
            relativelayout_sale_lock_moneybuy.setVisibility(View.VISIBLE);
            button_sale_lock.setVisibility(View.VISIBLE);
            textview_sale_lock_date_title.setText("成交日期");
            textview_sale_lock_moneybuy_title.setText("成交金额");
            textview_sale_lock_sale_info_company.setVisibility(View.VISIBLE);
            button_sale_cancel.setVisibility(View.GONE);
        } else if (buyType == 6) {
            radiogroup_lock_type.setVisibility(View.GONE);
            relativelayout_sale_moneytype.setVisibility(View.GONE);
            relativelayout_sale_dong_money.setVisibility(View.GONE);
            relativelayout_sale_lock_company.setVisibility(View.GONE);
            relativelayout_sale_lock_property.setVisibility(View.GONE);
            relativelayout_sale_lock_date.setVisibility(View.GONE);
            relativelayout_sale_lock_sale_info.setVisibility(View.GONE);
            relativelayout_sale_lock_moneybuy.setVisibility(View.GONE);
            button_sale_lock.setVisibility(View.VISIBLE);
            button_sale_cancel.setVisibility(View.VISIBLE);
            button_sale_lock.setText("解锁");
        }
        reflash();
    }

    public void setHouseMessage(HouseInfo info) {
        this.info = info;
        if(info.HuXingName==null){
            info.HuXingName = "";
        }
        if(info.Acreage==null){
            info.Acreage = "";
        }
        radiogroup_lock_type.clearCheck();
        textview_title_custom_popup.setText(info.RoomName);
        textview_sale_lock_huxin.setText(info.HuXingName);
        textview_sale_lock_mianji.setText(info.Acreage + "m²");
    }

    public SaleControlPW(Context context) {
        this.mContext = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popup_sale_lock, null);
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);

        initPopUpWindows();
        initView();
        // 刷新状态
        this.update();
    }

    public void reflash() {
        edit_sale_lock_company.setText("");
        textview_sale_dong_money.setText("");
    }


    public void initPopUpWindows() {
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
        //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
        this.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    public void initView() {
        radiogroup_lock_type = (RadioGroup) conentView.findViewById(R.id.radiogroup_lock_type);
        relativelayout_sale_moneytype = (RelativeLayout) conentView.findViewById(R.id.relativelayout_sale_moneytype);
        radiogroup_money_taype = (RadioGroup) conentView.findViewById(R.id.radiogroup_money_taype);
        radio_lock = (RadioButton) conentView.findViewById(R.id.radio_lock);
        radio_done = (RadioButton) conentView.findViewById(R.id.radio_done);
        relativelayout_sale_dong_money = (RelativeLayout) conentView.findViewById(R.id.relativelayout_sale_dong_money);
        relativelayout_sale_lock_company = (RelativeLayout) conentView.findViewById(R.id.relativelayout_sale_lock_company);
        relativelayout_sale_lock_property = (RelativeLayout) conentView.findViewById(R.id.relativelayout_sale_lock_property);
        relativelayout_sale_lock_date = (RelativeLayout) conentView.findViewById(R.id.relativelayout_sale_lock_date);
        relativelayout_sale_lock_sale_info = (RelativeLayout) conentView.findViewById(R.id.relativelayout_sale_lock_sale_info);
        relativelayout_sale_lock_moneybuy = (RelativeLayout) conentView.findViewById(R.id.relativelayout_sale_lock_moneybuy);

        button_sale_lock = (Button) conentView.findViewById(R.id.button_sale_lock);
        button_sale_cancel = (Button) conentView.findViewById(R.id.button_sale_cancel);
        textview_sale_lock_date_title = (TextView) conentView.findViewById(R.id.textview_sale_lock_date_title);
        textview_sale_lock_moneybuy_title = (TextView) conentView.findViewById(R.id.textview_sale_lock_moneybuy_title);

        textview_sale_lock_sale_info_company = (TextView) conentView.findViewById(R.id.textview_sale_lock_sale_info_company);

        textview_sale_lock_property = (TextView) conentView.findViewById(R.id.textview_sale_lock_property);
        textview_sale_lock_date = (TextView) conentView.findViewById(R.id.textview_sale_lock_date);
        textview_sale_lock_sale_info = (TextView) conentView.findViewById(R.id.textview_sale_lock_sale_info);
        textview_sale_lock_moneybuy = (TextView) conentView.findViewById(R.id.textview_sale_lock_moneybuy);
        textview_sale_lock_huxin = (TextView) conentView.findViewById(R.id.textview_sale_lock_huxin);
        textview_sale_lock_mianji = (TextView) conentView.findViewById(R.id.textview_sale_lock_mianji);
        textview_sale_dong_money = (EditText) conentView.findViewById(R.id.textview_sale_dong_money);
        edit_sale_lock_company = (EditText) conentView.findViewById(R.id.edit_sale_lock_company);

        textview_title_custom_popup = (TextView) conentView.findViewById(R.id.textview_title_custom_popup);
        button_sale_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buyType == 1) {
                    if (!radio_lock.isChecked()) {
                        SaleControlPW.this.dismiss();
                    }else{
                        HttpBusiness.setHouseStatus(info.NewsHouseID, "3", "", "", "", new SaleHttpBusiness());
                    }

                } else if (buyType == 2) {
                    if (!StringUtils.isNumber(textview_sale_dong_money.getText().toString())) {
                        toast("请填入正确的成交金额！");
                        return;
                    }
                    if (StringUtils.isEmpty(edit_sale_lock_company.getText().toString())) {
                        toast("请填入成交公司！");
                        return;
                    }

                    HttpBusiness.setHouseStatus(info.NewsHouseID, "5", PayName, edit_sale_lock_company.getText().toString(), textview_sale_dong_money.getText().toString(), new SaleHttpBusiness());
                }else if(buyType == 6){
                    HttpBusiness.getHomeClearHttp(info.NewsHouseID,new SaleHttpBusiness());
                }else{
                    SaleControlPW.this.dismiss();
                }
            }
        });
        button_sale_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNeedReflash = false;
                dismiss();
            }
        });
        radiogroup_lock_type.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                switch (checkedId) {
                    case R.id.radio_lock:
                        setBuyType(1);
                        break;
                    case R.id.radio_done:
                        setBuyType(2);
                        break;
                }
            }
        });

        radiogroup_money_taype.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_money_all:
                        PayName = "全款";
                        break;
                    case R.id.radio_money_loans:
                        PayName = "贷款";
                }
            }
        });
        reflash();
    }

    public void toast(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_LONG).show();
        KJLoger.debug(text);
    }

    public class SaleHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_KEY_SET_HOUSE_STATUS:
                    if (isError) {
                        if (buyType == 1) {
                            toast("锁定失败！");
                        } else {
                            toast("成交失败！");
                        }
                    } else {
                        if (buyType == 1) {
                            toast("锁定成功！");
                        } else {
                            toast("成交成功！");
                        }
                        button_sale_lock.setClickable(false);
                        radiogroup_lock_type.setClickable(false);

                        mHandler.postDelayed(mRunnable, GlobalVarible.GLOBALDELAY);
                    }
                    break;
                case HttpBusiness.HTTP_KEY_HOME_CLEAR:
                    if (isError) {
                        toast("房屋解锁失败！");
                    } else {
                        toast("房屋解锁成功！");
                        button_sale_lock.setClickable(false);
                        radiogroup_lock_type.setClickable(false);
                        mHandler.postDelayed(mRunnable, GlobalVarible.GLOBALDELAY);
                    }
                    break;
            }
        }
    }

    public Handler mHandler = new Handler();

    public Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            button_sale_lock.setClickable(true);
            radiogroup_lock_type.setClickable(true);
            isNeedReflash = true ;
            SaleControlPW.this.dismiss();
        }
    };

    public interface ReturnListener {
        public void lockReturn();

        public void successReturn(String PayName, String Remark, String OkMoney);
    }
}
