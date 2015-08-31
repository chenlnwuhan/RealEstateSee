package com.sales.realestate.android.demo.custom;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
import com.sales.realestate.android.bean.MailInfo;
import com.sales.realestate.android.bean.SpinnerItemInfo;
import com.sales.realestate.android.view.adapter.ListOtherSpinnerAdapter;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;

import java.util.ArrayList;

/**
 * Created by chenlin on 2015/7/20.
 */
public class CustomSearchAty extends KJActivity {

    @BindView(id = R.id.title_image_left, click = true)
    private ImageView titleImageLeft;

    @BindView(id = R.id.textview_mail_detail_info)
    public TextView textview_mail_detail_info;
    @BindView(id = R.id.textview_mail_detail_date)
    public TextView textview_mail_detail_date;
    @BindView(id = R.id.title_name)
    public TextView textViewTitle;

    @BindView(id = R.id.linnearlayout_custom_search_root)
    public LinearLayout linnearlayout_custom_search_root;

    public MailInfo mMailInfo;

    @BindView(id = R.id.linearlayout_custom_property,click = true)
    public RelativeLayout linearlayout_custom_property;
    @BindView(id = R.id.linearlayout_custom_property_line)
    public RelativeLayout linearlayout_custom_property_line;
    @BindView(id = R.id.linearlayout_custom_way,click = true)
    public RelativeLayout linearlayout_custom_type;
    @BindView(id = R.id.linearlayout_custom_level,click = true)
    public RelativeLayout linearlayout_custom_level;
    @BindView(id = R.id.textview_custom_property)
    public TextView textview_custom_property;
    @BindView(id = R.id.textview_custom_way)
    public TextView textview_custom_type;
    @BindView(id = R.id.textview_custom_level)
    public TextView textview_custom_level;
    @BindView(id = R.id.image_custom_property)
    public ImageView image_custom_property;
    @BindView(id = R.id.image_custom_way)
    public ImageView image_custom_type;
    @BindView(id = R.id.image_custom_level)
    public ImageView image_custom_level;
    @BindView(id = R.id.edittext_custom_name)
    public EditText edittext_custom_name;
    @BindView(id = R.id.edittext_custom_phone)
    public EditText edittext_custom_phone;

    public  String customType;
    @BindView(id = R.id.button_submit,click = true)
    public Button button_submit;

    public String sexId ;
    public String dicdId ;
    public String levelId ;

    private ArrayList<SpinnerItemInfo> spinnerObjectArrayList1 = GlobalVarible.getSPINNER_CUSTOM_PROPERTY();
    private ArrayList<SpinnerItemInfo> spinnerObjectArrayList2 = GlobalVarible.getSpinnerCustomWay();
    private ArrayList<SpinnerItemInfo> spinnerObjectArrayList3 = GlobalVarible.getSpinnerCustomLevel();
    private PopupWindow dropDownWindown1 = null;
    private PopupWindow dropDownWindown2 = null;
    private PopupWindow dropDownWindown3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivityActionMode(ActionBarMode.CUSTOMTITILE);
        setTitileResId(R.layout.view_title_simple);
        setRootViewResId(R.layout.aty_custom_search);
        setmBottomNavigation(BottomNavigation.NOBOTTOMSCROLL);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        int[] viewLocation = new int[2];
        int viewY = 0;
        int viewX = 0 ;
        switch (v.getId()) {
            case R.id.title_image_left:
                onBackPressed();
                break;
            case R.id.linearlayout_custom_property:

                v.getLocationInWindow(viewLocation);
                viewX = viewLocation[0]; // x 坐标
                viewY = viewLocation[1]; // y 坐标
                dropDownWindown1 = showDropDown(viewX,viewY+v.getHeight()+1,textview_custom_property,image_custom_property,dropDownWindown1,v.getWidth(),spinnerObjectArrayList1,1);
                break;
            case R.id.linearlayout_custom_way:
                v.getLocationInWindow(viewLocation);
                viewX = viewLocation[0]; // x 坐标
                viewY = viewLocation[1]; // y 坐标
                dropDownWindown2 = showDropDown(viewX,viewY+v.getHeight()+1,textview_custom_type,image_custom_type,dropDownWindown2,v.getWidth(),spinnerObjectArrayList2,2);
                break;
            case R.id.linearlayout_custom_level:
                v.getLocationInWindow(viewLocation);
                viewX = viewLocation[0]; // x 坐标
                viewY = viewLocation[1]; // y 坐标
                dropDownWindown3 = showDropDown(viewX,viewY+v.getHeight()+1,textview_custom_level,image_custom_level,dropDownWindown3,v.getWidth(),spinnerObjectArrayList3,3);
                break;
            case R.id.button_submit:
                Bundle mBundle = new Bundle();
                mBundle.putString("customName",edittext_custom_name.getText().toString());
                mBundle.putString("customPhone",edittext_custom_phone.getText().toString());
                mBundle.putString("buildId",CustomAty.BuildingID);
                mBundle.putString("dicdid",dicdId);
                mBundle.putString("customType",customType);
                mBundle.putString("levelId",textview_custom_level.getText().toString());
                mBundle.putString("propertyId",sexId);
                showActivity(this,CustomAty2.class,mBundle);
                break ;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            customType = bundle.getString("customType");
        }
    }

    public void reFresh(){
        sexId = "0";
        dicdId = "";
        levelId = "";
        edittext_custom_phone.setText("");
        edittext_custom_name.setText("");
        textview_custom_property.setText("");
        textview_custom_type.setText("");
        textview_custom_level.setText("");
        if(GlobalVarible.ROLE_ID.equals("3")){
            linearlayout_custom_property_line.setVisibility(View.GONE);
        }
    }
    @Override
    public void initData() {
        super.initData();

        GlobalVarible.initTitleListFocus(spinnerObjectArrayList1, -1, 0);
        GlobalVarible.initTitleListFocus(spinnerObjectArrayList2, -1, 0);
        GlobalVarible.initTitleListFocus(spinnerObjectArrayList3, -1, 0);
    }

    public void initTitle(int pozition) {
        textViewTitle.setText("高级搜索");
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle(0);
        ((View) linnearlayout_custom_search_root.getParent()).setBackgroundColor(getResources().getColor(R.color.custom_backgroud_color));
        reFresh();
    }

    public PopupWindow showDropDown(int pointX,int ponintY,final TextView targetTextView, final ImageView targetImageView , PopupWindow targetPopup,int popupWidth, final ArrayList<SpinnerItemInfo> targetList,final int typeid){
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
                    if(typeid ==1){
                        sexId = targetList.get(position).getId();
                    }else if(typeid ==2){
                        dicdId = targetList.get(position).getId();
                    }else if(typeid ==3){
                        levelId = targetList.get(position).getName();
                    }
                    targetTextView.setText(targetList.get(position).getName());
                    GlobalVarible.initTitleListFocus(targetList, -1, position);
                    hadapter.notifyDataSetChanged();
                    finalTargetPopup.dismiss();

                }
            });
            finalTargetPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
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
