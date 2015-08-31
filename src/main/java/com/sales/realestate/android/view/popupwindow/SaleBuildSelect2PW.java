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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by cc on 2015/7/23.
 */
public class SaleBuildSelect2PW extends PopupWindow {
    private View conentView;
    ListView list1 ;
    ListView list2 ;

    ListBuildInfoAdapter mListBuildInfoAdapter1 ;
    ListBuildInfoAdapter mListBuildInfoAdapter2 ;


    ArrayList<SpinnerItemInfo> spinnerObjectArrayList1;
    ArrayList<SpinnerItemInfo> spinnerObjectArrayList2;


    /**
     * 1 认购申请
     * 2 认购确定
     */
    public int buyType = 1;
    
    public  Map<String,List<BuildingUnitInfo>>  unitList;
    
   public BuildingUnitInfo info = new BuildingUnitInfo();

    public void setBuyType(int typeIndex){
        buyType = typeIndex ;
        if(buyType==1){

        }else{

        }
    }
    public SaleBuildSelect2PW(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popup_sale_select2, null);
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
        list2 = (ListView)conentView.findViewById(R.id.list2);

    }


    public void initListData(){

        initFirstLayerListView(1);
        initSecondLayerListView(1,null);


        GlobalVarible.initTitleListFocus(spinnerObjectArrayList1, -1, 0);
        GlobalVarible.initTitleListFocus(spinnerObjectArrayList2, -1, 0);


        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView mT = (TextView) view.findViewById(R.id.textview2);
                if (((Integer)mT.getTag()) == 1) {
                    return;
                } else {
                    GlobalVarible.initTitleListFocus(spinnerObjectArrayList1, -1, position);
                    mListBuildInfoAdapter1.notifyDataSetChanged();
                    info.Dong = spinnerObjectArrayList1.get(position).getId();
                    initSecondLayerListView(1,unitList.get(info.Dong));
                }
//                initSecondLayerListView(1);

            }
        });
        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView mT = (TextView) view.findViewById(R.id.textview2);
                if(((Integer)mT.getTag()) == 1){
                    return ;
                }else{
                    GlobalVarible.initTitleListFocus(spinnerObjectArrayList2,-1,position);
                    mListBuildInfoAdapter2.notifyDataSetChanged();
                    info.Dan = spinnerObjectArrayList2.get(position).getId();
                    info.DanName = spinnerObjectArrayList2.get(position).getName();
                    SaleBuildSelect2PW.this.dismiss();
                }

            }
        });
    }
    public void initFirstLayerListView(int initType){
        if(initType==1){
            spinnerObjectArrayList1 = new  ArrayList<SpinnerItemInfo>();
            mListBuildInfoAdapter1 = new ListBuildInfoAdapter(spinnerObjectArrayList1);
            list1.setAdapter(mListBuildInfoAdapter1);
        }else{
            spinnerObjectArrayList1.clear();
            mListBuildInfoAdapter1.notifyDataSetChanged();
        }
    }
    public void setspinnerList(Map<String,List<BuildingUnitInfo>> list) {
    	spinnerObjectArrayList1 = new  ArrayList<SpinnerItemInfo>();
    	if(list == null || list.size() == 0) return;
    	unitList = list;
    	Iterator<Entry<String, List<BuildingUnitInfo>>> it = list.entrySet().iterator();
    	while(it.hasNext()) {
    		Entry<String, List<BuildingUnitInfo>> key = it.next();
    		String dong = key.getKey();
    		SpinnerItemInfo mDong = new SpinnerItemInfo();
    		mDong.id = dong;
    		mDong.name = dong+"栋";
    		spinnerObjectArrayList1.add(mDong);    		
    	}   
    	if(spinnerObjectArrayList1.size() >0) {
    		info.Dong = spinnerObjectArrayList1.get(0).id;
    	}
    	 mListBuildInfoAdapter1 = new ListBuildInfoAdapter(spinnerObjectArrayList1);
         list1.setAdapter(mListBuildInfoAdapter1);       
    }
    /**
     * 1 显示刷新
     * 2 空
     * @param initType
     */
    public void initSecondLayerListView(int initType,List<BuildingUnitInfo> list){
    	spinnerObjectArrayList2 = new  ArrayList<SpinnerItemInfo>();
    	if(list != null && list.size() >= 0) {
    		for (int i = 0; i < list.size(); i++) {
    			BuildingUnitInfo info = list.get(i);
    			 SpinnerItemInfo mUnit = new SpinnerItemInfo();
                 mUnit.id = info.Dan;
                 mUnit.name =  info.DanName;
                 spinnerObjectArrayList2.add(mUnit);
			}
    	}
        if(initType==1){           
            mListBuildInfoAdapter2 = new ListBuildInfoAdapter(spinnerObjectArrayList2);
            list2.setAdapter(mListBuildInfoAdapter2);
        }else{
        	mListBuildInfoAdapter2.setSpinnerObjectArrayList(spinnerObjectArrayList2);
//            spinnerObjectArrayList2.clear();
            mListBuildInfoAdapter2.notifyDataSetChanged();
        }
    }
}
