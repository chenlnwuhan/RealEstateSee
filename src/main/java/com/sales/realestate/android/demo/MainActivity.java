package com.sales.realestate.android.demo;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sales.realestate.android.AppContext;
import com.sales.realestate.android.CommomKey;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.BaseTypeJson;
import com.sales.realestate.android.bean.BuildJson;
import com.sales.realestate.android.bean.BuildListJson;
import com.sales.realestate.android.bean.BuildingInfo;
import com.sales.realestate.android.bean.CustomTypeJson;
import com.sales.realestate.android.bean.ErWeiMaInfo;
import com.sales.realestate.android.bean.HomeJson;
import com.sales.realestate.android.bean.PropertyConsultantJson;
import com.sales.realestate.android.bean.SpinnerItemInfo;
import com.sales.realestate.android.bean.VesrionJson;
import com.sales.realestate.android.demo.analyse.CustomAnalyseAty;
import com.sales.realestate.android.demo.analyse.StatisticsCustomAty;
import com.sales.realestate.android.demo.analyse.StatisticsVolumeAty;
import com.sales.realestate.android.demo.more.AddresListAty;
import com.sales.realestate.android.demo.todo.CustomDefinitionAty;
import com.sales.realestate.android.demo.todo.ToDoListAty;
import com.sales.realestate.android.demo.custom.CustomAty;
import com.sales.realestate.android.demo.mail.MailAty;
import com.sales.realestate.android.demo.more.MoreAty;
import com.sales.realestate.android.utils.AppInit;
import com.sales.realestate.android.utils.FileUtils;
import com.sales.realestate.android.view.QRCodeScanActivity;
import com.sales.realestate.android.view.adapter.ActionBarSpinnerAdapter;
import com.sales.realestate.android.view.popupwindow.MoreCheckupPW;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.KJLoger;
import org.kymjs.kjframe.utils.SPUtils;
import org.kymjs.kjframe.utils.StringUtils;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends KJActivity {


    /**
     * 顶部菜单布局文件初始化
     */
    @BindView(id = R.id.title_image_left, click = true)
    private RelativeLayout titleImageLeft;
    @BindView(id = R.id.title_name)
    public TextView textViewTitle;
    @BindView(id = R.id.title_linerlayout, click = true)
    LinearLayout linearLayoutTitle;
    @BindView(id = R.id.title_center_location)
    LinearLayout linearLayoutTitleCenterLocation;
    @BindView(id = R.id.tilte_image_right, click = true)
    ImageButton qrCodeBtn;

    @BindView(id = R.id.imageview_volume, click = true)
    private ImageView imageview_volume;

    @BindView(id = R.id.title_tip_text)
    public TextView title_tip_text;
    @BindView(id = R.id.see_comming_text)
    public TextView see_comming_text;
    @BindView(id = R.id.see_phone_text)
    public TextView see_phone_text;
    @BindView(id = R.id.see_wei_text)
    public TextView see_wei_text;
    @BindView(id = R.id.see_baobei_text)
    public TextView see_baobei_text;

    @BindView(id = R.id.custom_difinition_text)
    public TextView custom_difinition_text;
    @BindView(id = R.id.see_jieding_tip_text)
    public TextView see_jieding_tip_text;
    @BindView(id = R.id.see_todo_text)
    public TextView see_todo_text;
    @BindView(id = R.id.see_toto_tip_text)
    public TextView see_toto_tip_text;

    @BindView(id = R.id.home_banner_pic)
    public ImageView home_banner_pic;



    @Override
    public Context createConfigurationContext(Configuration overrideConfiguration) {
        return super.createConfigurationContext(overrideConfiguration);
    }

    /**
     * 顶部下拉菜单初始化数据
     */
    private PopupWindow popupTitleDropDown = null;
    private ListView listViewPopupTitle;
    public ArrayList<SpinnerItemInfo> listViewTitleList = new ArrayList<SpinnerItemInfo>();
    public ActionBarSpinnerAdapter adapterTitleList;

    @BindView(id = R.id.home_banner_volume)
    private TextView homeBannerTextView;


    public static String BuildingName ="" ;
    public static String BuildingID ="" ;
    /**
     * 通讯录跳转
     */
    @BindView(id = R.id.linnearlayout_home_addreslist, click = true)
    public LinearLayout linnearlayout_home_addreslist;
    public VesrionJson mBuildListJson ;
    public MoreCheckupPW mMoreCheckupPW;

    /**
     * 3个图片跳转
     * 1 客户界定
     * 2 客户分析
     * 3 待办事项
     */
    @BindView(id = R.id.see_analyse_pic, click = true)
    private ImageView imageViewSeeCustomAnalyse;
    @BindView(id = R.id.see_todo_pic, click = true)
    private ImageView see_todo_pic;
    @BindView(id = R.id.see_jieding_pic, click = true)
    private ImageView see_jieding_pic;

    public HomeJson mHomeJson ;

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
    /**
     * 导航栏需要高亮的元素
     */
    @BindView(id = R.id.bottom_menu_img_home)
    public ImageView navagationImgHome;
    @BindView(id = R.id.bottom_menu_text_home)
    public TextView navagationTextHome;
    private class FinishRefresh extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            KJLoger.debug(System.currentTimeMillis() + "FinishRefresh");
            Gson gson = new Gson();
            BuildJson mBuildJson = gson.fromJson(params[0], BuildJson.class);
            GlobalVarible.setHouseList(mBuildJson.enList);
            GlobalVarible.setUnitList(mBuildJson.edList);
            KJLoger.debug(System.currentTimeMillis() + "FinishRefresh");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
        }
    }

    public class HomeHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_CHECKVERSION:
                    if (isError) {
                        toast("检查版本错误！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            if (errorMessage.equals("NoData")||errorMessage.equals("LatestVersion")) {
                                    return ;
                            }else{
                                mBuildListJson = gson.fromJson(returnStr, VesrionJson.class);
                                GlobalVarible.setVSERION(mBuildListJson);
                            }
                            if (mMoreCheckupPW == null) {
                                mMoreCheckupPW = new MoreCheckupPW(MainActivity.this);
                                mMoreCheckupPW.setBuyType(1);
                                mMoreCheckupPW.setOnDismissListener(new PopupWindow.OnDismissListener() {
                                    @Override
                                    public void onDismiss() {
                                    }
                                });
                            }
                            mMoreCheckupPW.setmBuildListJson(mBuildListJson);
                            if(mBuildListJson.APPFORCED.equals("1")){
                                mMoreCheckupPW.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                            }
                        } catch (Exception e) {
                            toast("检查版本解析错误！");
                        }
                    }
                    break;
                case HttpBusiness.HTTP_KEY_BAIDU_CLOUD:

                    if (isError) {
                        AppContext.getCurrentActivity().toast("消息推送绑定失败！");
                    } else {
                        if(errorNo.equals(HttpBusiness.RETURN_MESSAGE_TOKEN)){
                            return ;
                        }
                        try {
                            //               AppContext.getCurrentActivity().toast("消息推送绑定成功！");
                            AppInit.baidubindstore("1");
                        } catch (Exception e) {
                            AppContext.getCurrentActivity().toast("消息推送绑定结果解析错误！");
                        }
                    }
                    break;
                case HttpBusiness.HTTP_KEY_SALESLIST:
                    if (isError) {
                        toast("房源列表未读取！");
                    } else {
                        try {
                            KJLoger.debug(System.currentTimeMillis() + "onSuccess房源列表");
                            new FinishRefresh().execute(returnStr);
 //                           FileUtils.writeFile(org.kymjs.kjframe.utils.FileUtils.getSDCardPath()+File.separator+"1.txt",returnStr);

                        } catch (Exception e) {
                            toast("房源列表解析错误！");
                        }
                    }
                    break;

                case HttpBusiness.HTTP_KEY_PROPERTYCONSULTANT:
                    if (isError) {
                        toast("置业顾问列表读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            PropertyConsultantJson t = gson.fromJson(returnStr,PropertyConsultantJson.class);
                            GlobalVarible.setPropertyConsultantList(t.edList);
                            HttpBusiness.getSalesListHttp(BuildingID, new HomeHttpBusiness());
                        } catch (Exception e) {
                            toast("置业顾问列表解析错误！");
                        }
                    }
                    break;
                case HttpBusiness.HTTP_KEY_TABLE_CUSTOMTYPE:
                    if (isError) {
                        toast("客户类型未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            CustomTypeJson t = gson.fromJson(returnStr,CustomTypeJson.class);
                            GlobalVarible.setTitleCustomList(t.edList);
                            HttpBusiness.getPropertyConsultant(BuildingID, new HomeHttpBusiness());
                        } catch (Exception e) {
                            toast("客户类型解析错误！");
                        }
                    }
                    break;
                case HttpBusiness.HTTP_KEY_BASEINFO_LEVEL:
                    if (isError) {
                        toast("客户等级未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            BaseTypeJson t = gson.fromJson(returnStr,BaseTypeJson.class);
                            GlobalVarible.setSpinnerCustomLevel(t.edList);
                            HttpBusiness.getCustomSource("3", new HomeHttpBusiness());
                        } catch (Exception e) {
                            toast("客户等级解析错误！");
                        }
                    }
                    break;
                case HttpBusiness.HTTP_KEY_BASEINFO_SOURCE:
                    if (isError) {
                        toast("客户来源未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            BaseTypeJson t = gson.fromJson(returnStr,BaseTypeJson.class);
                            GlobalVarible.setSpinnerCustomWay(t.edList);
                            HttpBusiness.getCustomFollowType("4", new HomeHttpBusiness());
                        } catch (Exception e) {
                            toast("客户来源解析错误！");
                        }
                    }
                    break;
                case HttpBusiness.HTTP_KEY_BASEINFO_FOLLOW:
                    if (isError) {
                        toast("客户跟进类型未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            BaseTypeJson t = gson.fromJson(returnStr,BaseTypeJson.class);
                            GlobalVarible.setSpinnerCustomFollowtype(t.edList);

                        } catch (Exception e) {
                            toast("客户跟进类型解析错误！");
                        }
                    }
                    break;


                case HttpBusiness.HTTP_KEY_TABLE_PAYTYPE:
                    if (isError) {
                        toast("付款方式未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            ArrayList<SpinnerItemInfo> t = gson.fromJson(returnStr,new TypeToken<ArrayList<SpinnerItemInfo>>(){}.getType());
                            GlobalVarible.setSpinnerMoneyType(t);
                            HttpBusiness.getCustomLevel("5", new HomeHttpBusiness());
                        } catch (Exception e) {
                            toast("付款方式解析错误！");
                        }
                    }
                    break;
                case HttpBusiness.HTTP_KEY_ERWEIMA:
                    if (isError) {
                        toast("服务器返回二维码扫描无效！");
                    } else {
                        toast("二维码扫描完成并确认！");
                    }
                    break;
                case HttpBusiness.HTTP_KEY_BUILDING:
                    if (isError) {
                        toast("楼盘信息未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            BuildListJson mBuildListJson = gson.fromJson(returnStr, BuildListJson.class);
                            GlobalVarible.setBuildList(mBuildListJson.buildlist);
                            initTitle();
                            BuildingID =listViewTitleList.get(0).id;
                            BuildingName = listViewTitleList.get(0).name;
                            HttpBusiness.getHomeHttp(BuildingID,new HomeHttpBusiness());
                            mHomeJson = null;
                        } catch (Exception e) {
                            toast("楼盘信息解析错误！");
                        }
                    }
                    break;

                case HttpBusiness.HTTP_KEY_HOME:
                    if (isError) {
                        toast("主页面信息未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            mHomeJson = gson.fromJson(returnStr, HomeJson.class);
                            if(Integer.valueOf(mHomeJson.mHomeInfo.EmailTotal)==0){
                                title_tip_text.setVisibility(View.GONE);
                            }else{
                                title_tip_text.setVisibility(View.VISIBLE);
                                title_tip_text.setText(mHomeJson.mHomeInfo.EmailTotal);
                            }
                            String w = "总成交量   "+mHomeJson.mHomeInfo.HouseTotal+"  套";
                            int start = w.indexOf(mHomeJson.mHomeInfo.HouseTotal);
                            int end = w.indexOf(mHomeJson.mHomeInfo.HouseTotal) + mHomeJson.mHomeInfo.HouseTotal.length();
                            Spannable word = new SpannableString(w);
                            word.setSpan(new AbsoluteSizeSpan(50), start, end,
                                    Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                            word.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                            homeBannerTextView.setText(word);

                            see_comming_text.setText("到访量\n"+mHomeJson.mHomeInfo.IsLook);
                            see_phone_text.setText("来电量\n"+mHomeJson.mHomeInfo.ComeTelTotal);
                            see_wei_text.setText("微客户\n"+mHomeJson.mHomeInfo.WeiTotal);
                            see_baobei_text.setText("报备量\n"+mHomeJson.mHomeInfo.ReportTotal);
                            custom_difinition_text.setText("总数"+mHomeJson.mHomeInfo.OkDefinitionTotal);
                            if(Integer.valueOf(mHomeJson.mHomeInfo.NoDefinitionTotal)==0){
                                see_jieding_tip_text.setVisibility(View.GONE);
                            }else{
                                see_jieding_tip_text.setVisibility(View.VISIBLE);
                                see_jieding_tip_text.setText(mHomeJson.mHomeInfo.NoDefinitionTotal);
                            }
                            see_todo_text.setText("已处理"+mHomeJson.mHomeInfo.OkSchedule);
                            if(Integer.valueOf(mHomeJson.mHomeInfo.NoSchedule)==0){
                                see_toto_tip_text.setVisibility(View.GONE);
                            }else{
                                see_toto_tip_text.setVisibility(View.VISIBLE);
                                see_toto_tip_text.setText(mHomeJson.mHomeInfo.NoSchedule);
                            }
                            ArrayList<BuildingInfo> tt =mHomeJson.mHomeInfo.buildlist;
                            String img = "";
                            for(int i =0 ;i<tt.size();i++){
                                if(tt.get(i).BuildingID.equals(BuildingID)){
                                    img = tt.get(i).ImageUrl;
                                    break;
                                }
                            }
                            if(!StringUtils.isEmpty(img)){
                                KJBitmap kjb = new KJBitmap();
                                kjb.display(home_banner_pic,
                                        img);
                            }
                            HttpBusiness.getCustomType(BuildingID, new HomeHttpBusiness());
                        } catch (Exception e) {
                            toast("主页面信息解析错误！");
                        }
                    }
                    break;
            }
        }
    }


    /**
     * 4个跳转到客户的layout
     * 1 到访客户
     * 2 来电客户
     * 3 微客户
     * 3 报备客户
     */
    @BindView(id = R.id.see_comming_relativeLayout, click = true)
    public RelativeLayout see_comming_relativeLayout;
    @BindView(id = R.id.see_phone_relativeLayout, click = true)
    public RelativeLayout see_phone_relativeLayout;
    @BindView(id = R.id.see_wei_relativeLayout, click = true)
    public RelativeLayout see_wei_relativeLayout;
    @BindView(id = R.id.see_baobei_relativeLayout, click = true)
    public RelativeLayout see_baobei_relativeLayout;

    @Override
    public void setRootView() {
        super.setRootView();

//        if (!"org.kymjs.kjframe.demo".equals(getApplication().getPackageName())) {
//            KJActivityStack.create().AppExit(aty);
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivityActionMode(ActionBarMode.CUSTOMTITILE);
        setTitileResId(R.layout.view_title_home);
        setRootViewResId(R.layout.aty_home);
        setmBottomNavigation(BottomNavigation.HOME);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshData2();
        if(GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_PROPERTY)){
            navigationSale.setVisibility(View.GONE);
        }
    }

    public boolean refreshData2(){
        boolean isInit = true ;
        HttpBusiness.checkVersion(GlobalVarible.VERSION_NAME, new HomeHttpBusiness());
        HttpBusiness.getBuildingHTTP(new HomeHttpBusiness());
        if(GlobalVarible.getSpinnerMoneyType()==null||GlobalVarible.getSpinnerMoneyType().size()==0) {
            HttpBusiness.getPayType(new HomeHttpBusiness());
            isInit = false;
        } else if(GlobalVarible.getSpinnerCustomLevel()==null||GlobalVarible.getSpinnerCustomLevel().size()==0) {
            HttpBusiness.getCustomLevel("5",new HomeHttpBusiness());
            isInit = true;
        } else if (GlobalVarible.getSpinnerCustomWay()==null||GlobalVarible.getSpinnerCustomWay().size() == 0) {
            HttpBusiness.getCustomSource("3", new HomeHttpBusiness());
            isInit = true;
        }
        if(StringUtils.isEmpty(GlobalVarible.IS_BIND)){
            String channelId = (String) SPUtils.get2(this, "BAIDU_CHANNELID", "");
            if (!StringUtils.isEmpty(channelId)) {
                HttpBusiness.getBaiDuCloud(channelId, new HomeHttpBusiness());
            }
        }
        return isInit;
    }
    public boolean refreshData(){
        boolean isInit = true ;
        if(StringUtils.isEmpty(BuildingID)){
            HttpBusiness.getBuildingHTTP(new HomeHttpBusiness());
            isInit = false;

        }else if( mHomeJson == null){
            HttpBusiness.getHomeHttp(BuildingID,new HomeHttpBusiness());
            isInit = true;
        }else if(GlobalVarible.getHouseList()==null||GlobalVarible.getHouseList().size()==0) {
            HttpBusiness.getSalesListHttp(BuildingID, new HomeHttpBusiness());
            isInit = true;
        }
        if(GlobalVarible.getSpinnerMoneyType()==null||GlobalVarible.getSpinnerMoneyType().size()==0) {
            HttpBusiness.getPayType(new HomeHttpBusiness());
            isInit = true;
        } else if(GlobalVarible.getSpinnerCustomLevel()==null||GlobalVarible.getSpinnerCustomLevel().size()==0) {
            HttpBusiness.getCustomLevel("5", new HomeHttpBusiness());
            isInit = true;
        } else if (GlobalVarible.getSpinnerCustomWay()==null||GlobalVarible.getSpinnerCustomWay().size() == 0) {
            HttpBusiness.getCustomSource("3", new HomeHttpBusiness());
            isInit = true;
        }
        return isInit;
    }


    @Override
    public void initBottomNavagation() {
        navagationImgHome.setImageResource(R.drawable.pic_bottom_home_foucs);
        navagationTextHome.setTextColor(this.getResources().getColor(R.color.bottom_navigation_text_color_foucs));
    }

    @Override
    public void initWidget() {

        super.initWidget();
    }

    @Override
    public void initData() {
        super.initData();
    }

    public void initTitle() {
        listViewTitleList = GlobalVarible.getTITLE_HOME_LIST();
        adapterTitleList = new ActionBarSpinnerAdapter(listViewTitleList);
        GlobalVarible.initTitleListFocus(listViewTitleList, -1, 0);
        initTitle(0);
        initTitlePopup();
        if(GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_PROPERTY)){
            qrCodeBtn.setVisibility(View.VISIBLE);
        }else{
            qrCodeBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        if(!refreshData()){
            toast("有信息未同步，开始同步信息！");
            return ;
        }
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.title_linerlayout:
                adapterTitleList.notifyDataSetChanged();
                popupTitleDropDown.showAsDropDown(linearLayoutTitleCenterLocation, 0, -5);
                break;
            case R.id.title_image_left:
                showActivity(this, MailAty.class);
                break;
            case R.id.see_analyse_pic:
                if(StringUtils.isEmpty(BuildingID)){
                    toast("请先选择楼盘！");
                }
                bundle.putString("BuildingID",BuildingID);
                showActivity(this, CustomAnalyseAty.class,bundle);
                break;
            case R.id.linnearlayout_home_addreslist:
                showActivity(this, AddresListAty.class);
                break;
            case R.id.see_comming_relativeLayout:
                bundle.putString("customType", "0");
                this.showActivity(this, StatisticsCustomAty.class, bundle);
                break;
            case R.id.see_phone_relativeLayout:
                bundle.putString("customType", "1");
                this.showActivity(this, StatisticsCustomAty.class, bundle);
                break;
            case R.id.see_wei_relativeLayout:
                bundle.putString("customType", "2");
                this.showActivity(this, StatisticsCustomAty.class, bundle);
                break;
            case R.id.see_baobei_relativeLayout:
                bundle.putString("customType", "3");
                this.showActivity(this, StatisticsCustomAty.class, bundle);
                break;
            case R.id.see_todo_pic:
                this.showActivity(this, ToDoListAty.class);
                break;
            case R.id.see_jieding_pic:
                this.showActivity(this, CustomDefinitionAty.class);
                break;

            case R.id.tilte_image_right:
                Intent intent = new Intent();
                intent.setClass(this, QRCodeScanActivity.class);
                this.startActivityForResult(intent, 1);
                break;
            case R.id.bottom_menu_layout_custom:
                if(StringUtils.isEmpty(BuildingID)){
                    toast("请先选择楼盘！");
                }
                bundle.putString("BuildingID",BuildingID);
                showActivity(this, CustomAty.class,bundle);
                break;
            case R.id.bottom_menu_layout_more:
                showActivity(this, MoreAty.class);
                break;
            case R.id.bottom_menu_layout_table:
                showActivity(this, TableAty.class);
                break;
            case R.id.bottom_menu_layout_sale:
                if(StringUtils.isEmpty(BuildingID)){
                    toast("请先选择楼盘！");
                }
                bundle.putString("BuildingID",BuildingID);
                showActivity(this, SaleAty.class,bundle);
                break;
            case R.id.imageview_volume:
                showActivity(this, StatisticsVolumeAty.class);
                break;


        }
    }

    public void initTitle(int pozition) {
        textViewTitle.setText(listViewTitleList.get(pozition).getName());
    }

    public void initTitlePopup() {
        listViewPopupTitle = new ListView(this);
        listViewPopupTitle.setDivider(null);
        listViewPopupTitle.setSelector(new ColorDrawable(Color.TRANSPARENT));
        listViewPopupTitle.setCacheColorHint(Color.TRANSPARENT);
        listViewPopupTitle.setDividerHeight(1);
        listViewPopupTitle.setVerticalScrollBarEnabled(false);
        listViewPopupTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!listViewTitleList.get(position).isCurrent){
                    initTitle(position);
                    GlobalVarible.initTitleListFocus(listViewTitleList, -1, position);
                    BuildingID =listViewTitleList.get(position).id;
                    BuildingName = listViewTitleList.get(position).name;
                    HttpBusiness.getHomeHttp(BuildingID, new HomeHttpBusiness());
                }
                popupTitleDropDown.dismiss();
            }
        });
        listViewPopupTitle.setAdapter(adapterTitleList);
        popupTitleDropDown = new PopupWindow(listViewPopupTitle, (int) getResources().getDimension(R.dimen.title_spinner_item_width), ActionBar.LayoutParams.WRAP_CONTENT, true);
        popupTitleDropDown.setBackgroundDrawable(new BitmapDrawable());
        popupTitleDropDown.setOutsideTouchable(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (resultCode) {
            case QRCodeScanActivity.RESULT_OK:
                String returnStr = data.getStringExtra("qrCodeResult");
                Gson mGson = new Gson();
                try{
                    toast("二维码格式扫描成功，正在向服务器提交信息");
                    ErWeiMaInfo mErWeiMaInfo = mGson.fromJson(returnStr,ErWeiMaInfo.class);
                    HttpBusiness.getErMaHttp(mErWeiMaInfo.CustomerID,MainActivity.BuildingID,mErWeiMaInfo.AgentID,new HomeHttpBusiness());
                }catch (Exception e){
                    toast("二维码格式解析失败");
                    toast("二维码数据为:"+returnStr);
                }

                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

}
