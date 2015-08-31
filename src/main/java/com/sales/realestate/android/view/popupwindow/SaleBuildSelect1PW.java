package com.sales.realestate.android.view.popupwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.BuildingUnitInfo;
import com.sales.realestate.android.bean.HouseInfo;
import com.sales.realestate.android.bean.SpinnerItemInfo;
import com.sales.realestate.android.view.adapter.ListBuildInfoAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cc on 2015/7/23.
 */
public class SaleBuildSelect1PW extends PopupWindow {
    private View conentView;
    ListView list1 ;

    ListBuildInfoAdapter mListBuildInfoAdapter1 ;


    ArrayList<SpinnerItemInfo> spinnerObjectArrayList1;

    /**
     * 1 认购申请
     * 2 认购确定
     */
    public String buyType = "1";
    public String buildId = "";
    public String buildName = "";
    public void setBuyType(String typeIndex){
        buyType = typeIndex ;
        GlobalVarible.initTitleListFocus(spinnerObjectArrayList1, -1, buyType);
        mListBuildInfoAdapter1.notifyDataSetChanged();

    }
    public SaleBuildSelect1PW(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popup_sale_select1, null);
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        initPopUpWindows();
        initView();
        // 刷新状态
        this.update();

    }

    public void initPopUpWindows(){
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);

        //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
        this.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        initView();
        initListData();

    }

    public void initView(){
        list1 = (ListView)conentView.findViewById(R.id.list1);
    }


    public void initListData(){

        initFirstLayerListView(1);


        GlobalVarible.initTitleListFocus(spinnerObjectArrayList1, -1, 0);
        if(spinnerObjectArrayList1.size() > 0) {        	
        	buildId = spinnerObjectArrayList1.get(0).id;
        	 buildName = spinnerObjectArrayList1.get(0).getName();
        }
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView mT = (TextView) view.findViewById(R.id.textview2);
                if (((Integer)mT.getTag()) == 1&&false) {
                    return;
                } else {
                    GlobalVarible.initTitleListFocus(spinnerObjectArrayList1, -1, position);
                    mListBuildInfoAdapter1.notifyDataSetChanged();
                    buildId = spinnerObjectArrayList1.get(position).getId();
                    buildName = spinnerObjectArrayList1.get(position).getName();
                    SaleBuildSelect1PW.this.dismiss();
                }
            }
        });
    }
    public void initFirstLayerListView(int initType){
        if(initType==1){
            spinnerObjectArrayList1 = new  ArrayList<SpinnerItemInfo>();
           /* for(int i=1;i<10;i++){
                SpinnerItemInfo mDong = new SpinnerItemInfo();
                mDong.id = String.valueOf(i);
                mDong.name =  "H"+String.valueOf(i)+"小区";
                spinnerObjectArrayList1.add(mDong);
            }*/
            for(int i =0;i<GlobalVarible.getBuildList().size(); i++) {
            	 SpinnerItemInfo mDong = new SpinnerItemInfo();
                 mDong.id = GlobalVarible.getBuildList().get(i).BuildingID;
                 mDong.name = GlobalVarible.getBuildList().get(i).BuildingName;
                 spinnerObjectArrayList1.add(mDong);
            }
            mListBuildInfoAdapter1 = new ListBuildInfoAdapter(spinnerObjectArrayList1);
            list1.setAdapter(mListBuildInfoAdapter1);
        }else{
            spinnerObjectArrayList1.clear();
            mListBuildInfoAdapter1.notifyDataSetChanged();
        }
    }
}
