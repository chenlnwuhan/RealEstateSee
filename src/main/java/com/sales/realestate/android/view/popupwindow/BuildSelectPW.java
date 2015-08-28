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
public class BuildSelectPW extends PopupWindow {
    private View conentView;
    ListView list1;
    ListView list2;
    ListView list3;
    ListView list4;

    ListBuildInfoAdapter mListBuildInfoAdapter1;
    ListBuildInfoAdapter mListBuildInfoAdapter2;
    ListBuildInfoAdapter mListBuildInfoAdapter3;
    ListBuildInfoAdapter mListBuildInfoAdapter4;

    ArrayList<SpinnerItemInfo> spinnerObjectArrayList1;
    ArrayList<SpinnerItemInfo> spinnerObjectArrayList2;
    ArrayList<SpinnerItemInfo> spinnerObjectArrayList3;
    ArrayList<SpinnerItemInfo> spinnerObjectArrayList4;


    public List<BuildingUnitInfo> mBuildUnitList = GlobalVarible.getUnitList();
    public List<HouseInfo> mHouseList = GlobalVarible.getHouseList();
    public String buildName = "";
    public String buildNo = "";
    /**
     * 1 认购申请
     * 2 认购确定
     */
    public int buyType = 1;

    public void setBuyType(int typeIndex) {
        buyType = typeIndex;
        buildName = "";
    }
    public void reflashSelect(){
        initFirstLayerListView(1);
        initSecondLayerListView(2);
        initThirdLayerListView(2);
        initFourthLayerListView(2);
    }

    public BuildSelectPW(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popup_build_select, null);
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
        initView();
        initListData();

    }

    public void initView() {
        list1 = (ListView) conentView.findViewById(R.id.list1);
        list2 = (ListView) conentView.findViewById(R.id.list2);
        list3 = (ListView) conentView.findViewById(R.id.list3);
        list4 = (ListView) conentView.findViewById(R.id.list4);
    }

    public String dongid="";
    public String unitid="";
    public String cengid="";
    public String houseid="";
    public String dongname="";
    public String unitname="";
    public String cengname="";
    public String housename="";


    public void initListData() {

        initFirstLayerListView(1);


        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView mT = (TextView) view.findViewById(R.id.textview2);
                if (spinnerObjectArrayList1.get(position).isCurrent) {
                    return;
                } else {
                    dongid =spinnerObjectArrayList1.get(position).getId();
                    dongname =spinnerObjectArrayList1.get(position).getName();
                    GlobalVarible.initTitleListFocus(spinnerObjectArrayList1, -1, position);
                    mListBuildInfoAdapter1.notifyDataSetChanged();
                }
                initSecondLayerListView(1);
                initThirdLayerListView(2);
                initFourthLayerListView(2);
            }
        });
        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView mT = (TextView) view.findViewById(R.id.textview2);
                if (spinnerObjectArrayList2.get(position).isCurrent) {
                    return;
                } else {
                    unitid = spinnerObjectArrayList2.get(position).getId();
                    unitname = spinnerObjectArrayList2.get(position).getName();
                    GlobalVarible.initTitleListFocus(spinnerObjectArrayList2, -1, position);
                    mListBuildInfoAdapter2.notifyDataSetChanged();
                }
                initThirdLayerListView(1);
                initFourthLayerListView(2);
            }
        });
        list3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView mT = (TextView) view.findViewById(R.id.textview2);
                if (spinnerObjectArrayList3.get(position).isCurrent) {
                    return;
                } else {
                    cengid = spinnerObjectArrayList3.get(position).getId();
                    cengname = spinnerObjectArrayList3.get(position).getName();
                    GlobalVarible.initTitleListFocus(spinnerObjectArrayList3, -1, position);
                    mListBuildInfoAdapter3.notifyDataSetChanged();
                }
                initFourthLayerListView(1);

            }
        });
        list4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView mT = (TextView) view.findViewById(R.id.textview2);
                if (spinnerObjectArrayList4.get(position).isCurrent) {
                    return;
                } else {
                    houseid = spinnerObjectArrayList4.get(position).getId();
                    housename = spinnerObjectArrayList4.get(position).getName();
                    GlobalVarible.initTitleListFocus(spinnerObjectArrayList4, -1, position);
                    mListBuildInfoAdapter4.notifyDataSetChanged();
                    buildName = dongname+unitname+cengname+housename;
                    BuildSelectPW.this.dismiss();
                }

            }
        });
    }

    public void initFirstLayerListView(int initType) {
        if (initType == 1) {
            spinnerObjectArrayList1 = getDoneList();
            mListBuildInfoAdapter1 = new ListBuildInfoAdapter(spinnerObjectArrayList1);
            list1.setAdapter(mListBuildInfoAdapter1);
        } else {
            spinnerObjectArrayList1.clear();
            mListBuildInfoAdapter1.notifyDataSetChanged();
        }
    }

    /**
     * 1 显示刷新
     * 2 空
     *
     * @param initType
     */
    public void initSecondLayerListView(int initType) {
        if (initType == 1) {
            spinnerObjectArrayList2 = getUnitList(dongid);
            mListBuildInfoAdapter2 = new ListBuildInfoAdapter(spinnerObjectArrayList2);
            list2.setAdapter(mListBuildInfoAdapter2);
        } else {
            if(spinnerObjectArrayList2!=null){
                spinnerObjectArrayList2.clear();
                mListBuildInfoAdapter2.notifyDataSetChanged();
            }

        }
    }

    public void initThirdLayerListView(int initType) {
        if (initType == 1) {
            spinnerObjectArrayList3 = getCengList(dongid,unitid);
            mListBuildInfoAdapter3 = new ListBuildInfoAdapter(spinnerObjectArrayList3);
            list3.setAdapter(mListBuildInfoAdapter3);
        } else {
            if(spinnerObjectArrayList3!=null){
                spinnerObjectArrayList3.clear();
                mListBuildInfoAdapter3.notifyDataSetChanged();
            }
        }
    }

    public void initFourthLayerListView(int initType) {
        if (initType == 1) {
            spinnerObjectArrayList4 = getHouseList(dongid,unitid,cengid);
            mListBuildInfoAdapter4 = new ListBuildInfoAdapter(spinnerObjectArrayList4);
            list4.setAdapter(mListBuildInfoAdapter4);
        } else {

            if(spinnerObjectArrayList4!=null){
                spinnerObjectArrayList4.clear();
                mListBuildInfoAdapter4.notifyDataSetChanged();
            }
        }
    }
    public ArrayList<SpinnerItemInfo> getDoneList(){
        ArrayList<SpinnerItemInfo> t = new ArrayList<SpinnerItemInfo>();
        String lastDone = "";
        for (int i = 0; i < mBuildUnitList.size(); i++) {
            if (lastDone.equals(mBuildUnitList.get(i).Dong)) {
                continue;
            } else {
                lastDone = mBuildUnitList.get(i).Dong;
                boolean isFind = false;
                for (int j = 0; j < t.size(); j++) {
                    if (lastDone.equals(t.get(j).getId())) {
                        isFind = true;
                        break;
                    }
                }
                if (!isFind) {
                    SpinnerItemInfo mDong = new SpinnerItemInfo();
                    mDong.id = mBuildUnitList.get(i).Dong;
                    mDong.name = mBuildUnitList.get(i).Dong + "栋";
                    t.add(mDong);
                }
            }

        }
        return t;
    }
    public ArrayList<SpinnerItemInfo> getUnitList(String dong){
        ArrayList<SpinnerItemInfo> t = new ArrayList<SpinnerItemInfo>();
        ArrayList<BuildingUnitInfo> t2 = new ArrayList<BuildingUnitInfo>();
        for(int i=0;i < mBuildUnitList.size(); i++){
            if(dong.equals(mBuildUnitList.get(i).Dong)){
                t2.add(mBuildUnitList.get(i));
            }
        }
        String lastDan = "";
        for (int i = 0; i < t2.size(); i++) {
            if (lastDan.equals(t2.get(i).Dan)) {
                continue;
            } else {
                lastDan = t2.get(i).Dan;
                boolean isFind = false;
                for (int j = 0; j < t.size(); j++) {
                    if (lastDan.equals(t.get(j).getId())) {
                        isFind = true;
                        break;
                    }
                }
                if (!isFind) {
                    SpinnerItemInfo mDong = new SpinnerItemInfo();
                    mDong.id = t2.get(i).Dan;
                    mDong.name = t2.get(i).DanName;
                    t.add(mDong);
                }
            }

        }
        return t;
    }

    public ArrayList<SpinnerItemInfo> getCengList(String dong,String dan){
        ArrayList<SpinnerItemInfo> t = new ArrayList<SpinnerItemInfo>();
        ArrayList<HouseInfo> t2 = new ArrayList<HouseInfo>();
        for(int i=0;i < mHouseList.size(); i++){
            if((dong.equals(mHouseList.get(i).Dong))&&(dan.equals(mHouseList.get(i).Dan))){
                t2.add(mHouseList.get(i));
            }
        }
        String lastCeng = "";
        for (int i = 0; i < t2.size(); i++) {
            if (lastCeng.equals(t2.get(i).Ceng)) {
                continue;
            } else {
                lastCeng = t2.get(i).Ceng;
                boolean isFind = false;
                for (int j = 0; j < t.size(); j++) {
                    if (lastCeng.equals(t.get(j).getId())) {
                        isFind = true;
                        break;
                    }
                }
                if (!isFind) {
                    SpinnerItemInfo mCeng = new SpinnerItemInfo();
                    mCeng.id = t2.get(i).Ceng;
                    mCeng.name = t2.get(i).Ceng+"层";
                    t.add(mCeng);
                }
            }

        }
        return t;
    }

    public ArrayList<SpinnerItemInfo> getHouseList(String dong,String dan,String Ceng){
        ArrayList<SpinnerItemInfo> t = new ArrayList<SpinnerItemInfo>();
        for(int i=0;i < mHouseList.size(); i++){
            if((dong.equals(mHouseList.get(i).Dong))&&(dan.equals(mHouseList.get(i).Dan))&&(Ceng.equals(mHouseList.get(i).Ceng))){
                if(mHouseList.get(i).IsSell.equals("0")){
                    SpinnerItemInfo mHouse = new SpinnerItemInfo();
                    mHouse.id = mHouseList.get(i).NewsHouseID;
                    mHouse.name = mHouseList.get(i).RoomName;
                    t.add(mHouse);
                }
            }
        }
        return t;
    }
}
