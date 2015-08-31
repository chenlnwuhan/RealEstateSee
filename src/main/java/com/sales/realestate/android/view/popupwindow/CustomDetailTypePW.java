package com.sales.realestate.android.view.popupwindow;

import android.app.ActionBar;
import android.content.Context;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sales.realestate.android.AppContext;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.CustomInfo;
import com.sales.realestate.android.bean.SpinnerItemInfo;
import com.sales.realestate.android.view.adapter.ListOtherSpinnerAdapter;

import java.util.ArrayList;

/**
 * Created by cc on 2015/7/23.
 */
public class CustomDetailTypePW extends PopupWindow {
    private View conentView;

    private RelativeLayout linearlayout_popup_time;
    private TextView textview_popup_time;
    private ImageView image_phone_type;

    public ListOtherSpinnerAdapter Mhadapter;
    public CustomInfo customInfo;

    private TextView textview_custom_distribution_cancel;
    private TextView textview_custom_distribution_ok;

    private ArrayList<SpinnerItemInfo> spinnerObjectArrayList = GlobalVarible.getSpinnerCustomLevel();

    private PopupWindow dropDownWindown1 = null;

    /**
     * 1 详细模式
     * 2 修改模式
     */

    public void setBuyType(CustomInfo typeIndex) {
        customInfo = typeIndex;
        customInfo.reflashType = 0;
        int levelindex = 0;
        ArrayList<SpinnerItemInfo> m = GlobalVarible.getSpinnerCustomLevel();
        for (int i = 0; i < m.size(); i++) {
            if (customInfo.Customerlevel.equals(m.get(i).getName())) {
                levelindex = i;
            }
        }
        textview_popup_time.setText(customInfo.Customerlevel);
        GlobalVarible.initTitleListFocus(spinnerObjectArrayList, -1, levelindex);
//        Mhadapter.notifyDataSetChanged();

    }

    public CustomDetailTypePW(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popup_custom_detail_type, null);
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

        linearlayout_popup_time = (RelativeLayout) conentView.findViewById(R.id.linearlayout_popup_time);
        textview_popup_time = (TextView) conentView.findViewById(R.id.textview_popup_time);
        image_phone_type = (ImageView) conentView.findViewById(R.id.image_phone_type);
        textview_custom_distribution_cancel = (TextView) conentView.findViewById(R.id.textview_custom_distribution_cancel);
        textview_custom_distribution_ok = (TextView) conentView.findViewById(R.id.textview_custom_distribution_ok);
        linearlayout_popup_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] viewLocation = new int[2];
                v.getLocationInWindow(viewLocation);
                int viewX = viewLocation[0]; // x 坐标
                int viewY = viewLocation[1]; // y 坐标

                dropDownWindown1 = showDropDown(viewX, viewY + v.getHeight() * 2, textview_popup_time, image_phone_type, dropDownWindown1, v.getWidth(), spinnerObjectArrayList);
            }
        });
        textview_custom_distribution_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customInfo.reflashType = 0;
                CustomDetailTypePW.this.dismiss();
            }
        });
        textview_custom_distribution_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!customInfo.Customerlevel.equals(textview_popup_time.getText().toString())) {
                    HttpBusiness.getUpdateCustomType(customInfo.CustomerID, textview_popup_time.getText().toString(), new CustomUpdateLHttpBusiness());
                } else {
                    AppContext.getCurrentActivity().toast("选择的等级和原有一致！");
                }
            }
        });
    }
    public class CustomUpdateLHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_KEY_UPDATE_CUSTOM_TYPE:
                    if (isError) {
                        AppContext.getCurrentActivity().toast("客户等级修改失败！");
                    } else {
                        try {
                            AppContext.getCurrentActivity().toast("客户等级修改成功！");
                            textview_custom_distribution_cancel.setClickable(false);
                            textview_custom_distribution_ok.setClickable(false);
                            customInfo.reflashType = 1;
                            mHandler.postDelayed(mRunnable, GlobalVarible.GLOBALDELAY);
                        } catch (Exception e) {
                            AppContext.getCurrentActivity().toast("客户等级修改结果解析错误！");
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
            CustomDetailTypePW.this.dismiss();
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
            Mhadapter = hadapter;
            listView.setAdapter(hadapter);

            targetPopup = new PopupWindow(listView, popupWidth, ActionBar.LayoutParams.WRAP_CONTENT, true);
            targetPopup.setBackgroundDrawable(new BitmapDrawable());
            final PopupWindow finalTargetPopup = targetPopup;
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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

