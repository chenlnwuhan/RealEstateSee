package com.sales.realestate.android.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.StringUtils;

import android.app.ActionBar;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sales.realestate.android.CommomKey;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.BuildJson;
import com.sales.realestate.android.bean.BuildingUnitInfo;
import com.sales.realestate.android.bean.House2Json;
import com.sales.realestate.android.bean.HouseInfo;
import com.sales.realestate.android.bean.SailHouseDetail;
import com.sales.realestate.android.demo.custom.CustomAty;
import com.sales.realestate.android.demo.more.MoreAty;
import com.sales.realestate.android.utils.FileUtils;
import com.sales.realestate.android.view.popupwindow.SaleBuildSelect1PW;
import com.sales.realestate.android.view.popupwindow.SaleBuildSelect2PW;
import com.sales.realestate.android.view.popupwindow.SaleControlPW;

/**
 * Created by chenlin on 2015/7/20.
 */
public class SaleAty extends KJActivity {

    @BindView(id = R.id.title_image_left)
    private ImageView titleImageLeft;
    @BindView(id = R.id.title_name)
    public TextView textViewTitle;

    public  int layoutTitleWidth = GlobalVarible.SCRREN_WIDTH / 29 * 5;
    public  int layoutWidth = (GlobalVarible.SCRREN_WIDTH - layoutTitleWidth) / 4;
    public  int layoutHigh = (int) (layoutWidth * 0.7);
    public  int divWidth = 0;

    private SaleBuildSelect2PW mSaleBuildSelect2PW = null;
    private SaleBuildSelect1PW mSaleBuildSelect1PW = null;
    private SaleControlPW mSaleControlPW = null ;


    @BindView(id = R.id.relativelayout_sale_build_title,click = true)
    public RelativeLayout relativelayout_sale_build_title;
    @BindView(id = R.id.textview_sale_build_title)
    public TextView textview_sale_build_title;
    @BindView(id = R.id.image_sale_build_title)
    public ImageView image_sale_build_title;

    @BindView(id = R.id.relativelayout_sale_unit_title,click = true)
    public RelativeLayout relativelayout_sale_unit_title;
    @BindView(id = R.id.textview_sale_unit_title)
    public TextView textview_sale_unit_title;
    @BindView(id = R.id.image_sale_unit_title)
    public ImageView image_sale_unit_title;

    @BindView(id = R.id.first_LinearLayout)
    public LinearLayout first_LinearLayout;
    /**
     * 底部导航栏
     */
    @BindView(id = R.id.bottom_menu_layout_home, click = true)
    public LinearLayout navigationHome;
    @BindView(id = R.id.bottom_menu_layout_custom, click = true)
    public LinearLayout navigationCustom;
    @BindView(id = R.id.bottom_menu_layout_sale, click = true)
    public LinearLayout navigationSale;
    @BindView(id = R.id.bottom_menu_layout_table, click = true)
    public LinearLayout navigationTable;
    @BindView(id = R.id.bottom_menu_layout_more, click = true)
    public LinearLayout navigationMore;
    @BindView(id = R.id.list_ld_num, click = false)
    public LinearLayout lcList;
    @BindView(id = R.id.list_item_ld, click = false)
    public RelativeLayout ldList;
    /**
     * 导航栏需要高亮的元素
     */
    @BindView(id = R.id.bottom_menu_img_sale)
    public ImageView bottom_menu_img_more;
    @BindView(id = R.id.bottom_menu_text_sale)
    public TextView bottom_menu_text_more;

    public BuildJson mBuildJson ;
    private SailHouseDetail mBuildJson2;
    
    public String buildId = "";
    public BuildingUnitInfo buildinfo = new BuildingUnitInfo();
    public Map<String,List<BuildingUnitInfo>>  unitList = new HashMap<String, List<BuildingUnitInfo>>();
    public Map<BuildingUnitInfo,List<HouseInfo>>  houseList = new HashMap<BuildingUnitInfo,List<HouseInfo>>();
    public Handler handler = new  Handler();
    @Override
    public void initBottomNavagation() {
        bottom_menu_img_more.setImageResource(R.drawable.pic_bottom_sale_foucs);
        bottom_menu_text_more.setTextColor(this.getResources().getColor(R.color.bottom_navigation_text_color_foucs));
    }
    public class SaleHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_KEY_SALESLIST:
                    if (isError) {
                        toast("销控列表未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            FileUtils.writeFile(org.kymjs.kjframe.utils.FileUtils.getSDCardPath() + File.separator + "1.txt", returnStr);
                            mBuildJson = gson.fromJson(returnStr, BuildJson.class);
                            unitList = GlobalVarible.findUnitList(mBuildJson.edList);
                            houseList = GlobalVarible.getUnitHouseList(mBuildJson.enList);
                            if(!StringUtils.isEmpty(buildinfo.Dan) && !StringUtils.isEmpty(buildinfo.Dong)) {
                            	dealHouseList();
                            }
                        } catch (Exception e) {
                            toast("销控列表解析错误！");
                        }
                    }
                    break;
                case HttpBusiness.HTTP_KEY_GET_HOUSE_OTHER_DETAIL:
                    if (isError) {
                        toast("房屋详情未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            List<SailHouseDetail> ps = gson.fromJson(returnStr, new TypeToken<List<SailHouseDetail>>(){}.getType());
                            if(ps==null||ps.size()==0){
                                toast("房屋详情为空！");
                            }
                            mBuildJson2 = ps.get(0);
                            mSaleControlPW.setSailHouseDetail(mBuildJson2);
                        } catch (Exception e) {
                            toast("房屋详情解析错误！");
                        }
                    }
                    break;
                case HttpBusiness.HTTP_KEY_GET_HOUSE_DETAIL:
                    if (isError) {
                        toast("房屋详情未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            House2Json mHouse2Json = gson.fromJson(returnStr, House2Json.class);
                            if(mHouse2Json.nhdList==null||mHouse2Json.nhdList.size()==0){
                                toast("房屋详情为空！");
                            }
                            mBuildJson2 = mHouse2Json.nhdList.get(0);
                            mSaleControlPW.setSailHouseDetail(mBuildJson2);
                        } catch (Exception e) {
                            toast("房屋详情解析错误！");
                        }
                    }
                    break;
            }
        }
    }   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivityActionMode(ActionBarMode.CUSTOMTITILE);
        setTitileResId(R.layout.view_title_simple);
        setRootViewResId(R.layout.aty_sale);
        setmBottomNavigation(BottomNavigation.JUSTNOSCROLL);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        textViewTitle.setText("销控表");
        titleImageLeft.setVisibility(View.GONE);

//        showldView(GlobalVarible.getHouseTest());
    }
    @Override
    protected void onStart() {
        super.onStart();
        buildId = MainActivity.BuildingID;
            if (!StringUtils.isEmpty(buildId)) {
                HttpBusiness.getSalesListHttp(buildId, new SaleHttpBusiness());
                textview_sale_build_title.setText(MainActivity.BuildingName);
        }
        if(GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_PROPERTY)){
            navigationSale.setVisibility(View.GONE);
        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.bottom_menu_layout_custom:
                showActivity(this, CustomAty.class);
                break;
            case R.id.bottom_menu_layout_table:
                showActivity(this, TableAty.class);
                break;
            case R.id.bottom_menu_layout_home:
                showActivity(this, MainActivity.class);
                break;
            case R.id.bottom_menu_layout_more:
                showActivity(this, MoreAty.class);
                break;
            case R.id.relativelayout_sale_build_title:
                if (mSaleBuildSelect1PW == null) {
                    mSaleBuildSelect1PW = new SaleBuildSelect1PW(this);
                    mSaleBuildSelect1PW.setBackgroundDrawable(new BitmapDrawable());

                    mSaleBuildSelect1PW.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
                    mSaleBuildSelect1PW.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                        	if (!mSaleBuildSelect1PW.buildId.equals(buildId)||true) {
                        		buildId = mSaleBuildSelect1PW.buildId;
                        		textview_sale_build_title.setText(mSaleBuildSelect1PW.buildName);
                        		HttpBusiness.getSalesListHttp(buildId, new SaleHttpBusiness());
                        	}
                        }
                    });
                }
                mSaleBuildSelect1PW.setBuyType(buildId);
                mSaleBuildSelect1PW.showAsDropDown(first_LinearLayout, 0, 1);
                break;
            case R.id.relativelayout_sale_unit_title:
//                if (mSaleBuildSelect2PW == null) {
                if(unitList == null ||unitList.size() == 0) {
                    toast("请先选择楼盘！");
                    return;
                }
                    mSaleBuildSelect2PW = new SaleBuildSelect2PW(this);
                    mSaleBuildSelect2PW.setBackgroundDrawable(new BitmapDrawable());
                    mSaleBuildSelect2PW.setBuyType(1);
                    mSaleBuildSelect2PW.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
                    mSaleBuildSelect2PW.setspinnerList(unitList);
                    mSaleBuildSelect2PW.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {                        	
                        	buildinfo = mSaleBuildSelect2PW.info;
                        	if(!StringUtils.isEmpty(buildinfo.DanName) && !StringUtils.isEmpty(buildinfo.Dong)) {
                        		textview_sale_unit_title.setText(buildinfo.Dong+"栋"+buildinfo.DanName);
                        		dealHouseList();
                        	}
                        }
                    });
//                }
                mSaleBuildSelect2PW.showAsDropDown(first_LinearLayout, 0, 1);
                break;
        }
    }

    private void showldView(List<HouseInfo> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        lcList.removeAllViews();
        ldList.removeAllViews();
        String lc = "";
        List<HouseInfo> curList = null;
        for (int i = list.size()-1; i >= 0; i--) {
            HouseInfo info = list.get(i);
            if (!lc.equals(info.Ceng)) {
                lc = info.Ceng;
                List<HouseInfo> lastList = curList;
                if (lastList != null) {
                    clList.add(lastList);
                }
                curList = new ArrayList<HouseInfo>();
            }else{
                if(i==(list.size()-1)){
                    List<HouseInfo> lastList = curList;
                    clList.add(lastList);
                }
            }
            if ("1".equals(info.HouseType)) {
                String[] dels = info.HouseTypeName.split("-");
                if ("1".equals(dels[1])) {
                    int total = Integer.valueOf(dels[2]);
                    for (int j = 1; j < total; j++) {
                        HouseInfo nullinfo = new HouseInfo();
                        nullinfo.Ceng = info.Ceng;
                        curList.add(nullinfo);
                    }
                }
            }else if("1".equals(info.HouseType)){

            }
            curList.add(info);

        }

        for (int i = 0; i < clList.size(); i++) {
            List<HouseInfo> lastList = clList.get(i);
            for (int j = 0; j < lastList.size(); j++) {
                HouseInfo info = lastList.get(j);
                if (j == 0) {
                    TextView tv = new TextView(this);
                    LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(layoutTitleWidth, layoutHigh);
                    tvParams.setMargins(1, 0, 0, 0);
                    tvParams.gravity = Gravity.CENTER;
                    tv.setLayoutParams(tvParams);
                    tv.setText(info.Ceng);
                    tv.setGravity(Gravity.CENTER);
                    if (i % 2 == 0) {
                        tv.setBackgroundResource(R.drawable.shape_sale_normol_background1);
                    } else {
                        tv.setBackgroundResource(R.drawable.shape_sale_normol_background2);
                    }
                    lcList.addView(tv);
                }
                if (info.NewsHouseID != null) {
                    ldList.addView(drawTextView(info, i, j));
                }
            }


        }

    }

    private List<List<HouseInfo>> clList = new ArrayList<List<HouseInfo>>();
    private TextView drawTextView(final HouseInfo info, int lc, int dan) {

        TextView tv = new TextView(this);
        int high = layoutHigh;
        int width = layoutWidth;
        RelativeLayout.LayoutParams tvParams;
        int top = (lc) * (layoutHigh + divWidth);
        int left = (dan) * (layoutWidth + divWidth);
        if ("1".equals(info.HouseType)) {
            String[] dels = info.HouseTypeName.split("-");
            if ("1".equals(dels[1])) {
                width = layoutWidth * (Integer.valueOf(dels[2])) + (Integer.valueOf(dels[2]) - 1) * divWidth;
//				tv.setBackgroundResource(R.drawable.shape_edittext_border);

                //			tv.setBackground(getResources().getDrawable(R.drawable.shape_edittext_border));
            } else {
                high = layoutHigh * (Integer.valueOf(dels[2])) + (Integer.valueOf(dels[2]) - 1) * divWidth;
                if (lc < clList.size() - 1) {
                    for (int i = 1; i < Integer.valueOf(dels[2]); i++) {
                        if (lc + i < clList.size() - 1) {
                            HouseInfo nullinfo = new HouseInfo();
                            nullinfo.Ceng = clList.get(lc + i).get(0).Ceng;
                            clList.get(lc + i).add(dan, nullinfo);
                        }
                    }
                }
            }
        } else {
//    		tv.setBackgroundColor(Color.RED);
        }
        tvParams = new RelativeLayout.LayoutParams(width, high);
        tvParams.setMargins(left, top, 0, 0);
        if (info.IsSell.equals("1") || info.IsSell.equals("5")) {
            tv.setBackgroundResource(R.drawable.shape_sale_normol_background3);
        } else if (info.IsSell.equals("2") || info.IsSell.equals("4")) {
            tv.setBackgroundResource(R.drawable.shape_sale_normol_background5);
        } else if (info.IsSell.equals("3")) {
            tv.setBackgroundResource(R.drawable.shape_sale_normol_background4);
        } else {
            if (lc % 2 == 0) {
                tv.setBackgroundResource(R.drawable.shape_sale_normol_background1);
            } else {
                tv.setBackgroundResource(R.drawable.shape_sale_normol_background2);
            }

        }
        /**
         * 0是未售出，1是已售,2是认购锁定 ,3是预留,4已购,5是已售
         * 1  未售(0) -可以做锁定和成交按钮选择
         * 2  未售(0) -做了成交按钮操作
         * 3  认购锁定(2)，已购(4)
         * 4  已售(1)
         * 5  已售信息(5)
         * 6  预留(3) 做解锁
         */
        int type = 0 ;
        if(info.IsSell.equals("0")) type = 1 ;
        if(info.IsSell.equals("1")) type = 4 ;
        if(info.IsSell.equals("2")||info.IsSell.equals("4")) type = 3 ;
        if(info.IsSell.equals("3")) type = 6 ;
        if(info.IsSell.equals("5")) type = 5 ;
        final int finalType = type;
        tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSaleControlPW == null){
                    mSaleControlPW = new SaleControlPW(SaleAty.this);
                    mSaleControlPW.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            if (mSaleControlPW.isNeedReflash) {
                                HttpBusiness.getSalesListHttp(buildId, new SaleHttpBusiness());
                            }
                        }
                    });
                  
                }
                mSaleControlPW.setHouseMessage(info);
                if(info.IsSell.equals("1")){
                    HttpBusiness.getHouseDetails(info.NewsHouseID, "1", new SaleHttpBusiness());
                }else if(info.IsSell.equals("2")){
                    HttpBusiness.getHouseDetails(info.NewsHouseID, "2", new SaleHttpBusiness());
                }else if(info.IsSell.equals("3")){
   //                 HttpBusiness.getHouseOthersDetails(info.NewsHouseID, "3", new SaleHttpBusiness());
                }else if(info.IsSell.equals("5")){
                    HttpBusiness.getHouseOthersDetails(info.NewsHouseID, "5", new SaleHttpBusiness());
                }
                int type2 = finalType;
                if(GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_PROPERTY)||GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_MANAGER)){
                    if(type2 ==1||type2 ==6){
                        type2 = 7;
                    }
                }
                mSaleControlPW.setBuyType(type2);
                mSaleControlPW.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
            }
        });
        tv.setText(info.RoomName);
        tv.setGravity(Gravity.CENTER);
        tv.setLayoutParams(tvParams);
        return tv;
    }
    
    private void dealHouseList() {
    	if(!houseList.isEmpty()) {
    		clList.clear();
    		Iterator<Entry<BuildingUnitInfo,  List<HouseInfo>>> it = houseList.entrySet().iterator();
    		while(it.hasNext()) {
    			Entry<BuildingUnitInfo, List<HouseInfo>> entry = it.next();
    			BuildingUnitInfo key = entry.getKey();
    			if(buildinfo.Dan.equals(key.Dan) && (buildinfo.Dong).equals(key.Dong)) {
    				 List<HouseInfo> value = entry.getValue();    				
    				 showldView(value);
    				 break;
    			}
    		}
     	}
    }
    

    
}
