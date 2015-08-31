package com.sales.realestate.android.demo.analyse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.AnalyseInfo;
import com.sales.realestate.android.bean.CustomAnalyseJson;
import com.sales.realestate.android.bean.MailInfo;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.AnalyseChartView;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.StringUtils;

/**
 * Created by chenlin on 2015/7/21.
 */
public class CustomAnalyseAty extends KJActivity {

    @BindView(id = R.id.title_image_left, click = true)
    private ImageView titleImageLeft;
    @BindView(id = R.id.title_name)
    public TextView textViewTitle;

    public String BuildingID;

    @BindView(id = R.id.textview_custom_analysic_youxiao)
    public TextView textview_custom_analysic_youxiao;
    @BindView(id = R.id.textview_custom_analysic_wuxiao)
    public TextView textview_custom_analysic_wuxiao;
    @BindView(id = R.id.textview_custom_analysic_daofang)
    public TextView textview_custom_analysic_daofang;
    @BindView(id = R.id.textview_custom_analysic_liushi)
    public TextView textview_custom_analysic_liushi;

    @BindView(id = R.id.img_custom_analysic_youxiao_next, click = true)
    public RelativeLayout img_custom_analysic_youxiao_next;
    @BindView(id = R.id.img_custom_analysic_wuxiao_next, click = true)
    public RelativeLayout img_custom_analysic_wuxiao_next;
    @BindView(id = R.id.img_custom_analysic_daofang_next, click = true)
    public RelativeLayout img_custom_analysic_daofang_next;
    @BindView(id = R.id.img_custom_analysic_liushi_next, click = true)
    public RelativeLayout img_custom_analysic_liushi_next;
    
    @BindView(id = R.id.view_trend_char)
    private AnalyseChartView mAnalyseChartView;
    private String numberYouXiao;
    private String numberWuXiao;
    private String numberDaoFang;
    private String numberLiuSHI;

    public class CustomAnalyseHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_KEY_CUSTOMANALYS:
                    if (isError) {
                        toast("客户分析未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            CustomAnalyseJson mCustomAnalyseJson = gson.fromJson(returnStr, CustomAnalyseJson.class);
                            initView(mCustomAnalyseJson);
                        } catch (Exception e) {
                            toast("客户分析解析错误！");
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
        setRootViewResId(R.layout.aty_custom_analysis);
        setmBottomNavigation(BottomNavigation.NOBOTTOMSCROLL);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            String data = bundle.getString("BuildingID");
            BuildingID = data;
            if (!StringUtils.isEmpty(data)) {
                if(firstPoint==0) {
                    firstPoint++;
                    HttpBusiness.getCustomAnalyse(data, new CustomAnalyseHttpBusiness());
                }
            }
        }
    }
    public int firstPoint = 0 ;
    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.img_custom_analysic_youxiao_next:
                bundle.putString("customType", "1");
                this.showActivity(this, CustomAnalyseDetailAty.class, bundle);
                break;
            case R.id.img_custom_analysic_wuxiao_next:
                bundle.putString("customType", "0");
                this.showActivity(this, CustomAnalyseDetailAty.class, bundle);
                break;
            case R.id.img_custom_analysic_daofang_next:
                bundle.putString("customType", "2");
                this.showActivity(this, CustomAnalyseDetailAty.class, bundle);
                break;
            case R.id.img_custom_analysic_liushi_next:
                bundle.putString("customType", "3");
                this.showActivity(this, CustomAnalyseDetailAty.class, bundle);
                break;
            case R.id.title_image_left:
                onBackPressed();
                break;
        }
    }

    @Override
    public void initData() {
        super.initData();



        
    }

    public void initView(CustomAnalyseJson mCustomAnalyseJson){
        numberYouXiao = mCustomAnalyseJson.CustomerValidNumber+"/"+mCustomAnalyseJson.CustomerTotal;
        numberWuXiao = mCustomAnalyseJson.CustomerInvalidNumber+"/"+mCustomAnalyseJson.CustomerTotal;
        numberDaoFang = mCustomAnalyseJson.CustomerVisitNumber+"/"+mCustomAnalyseJson.CustomerTotal;
        numberLiuSHI = mCustomAnalyseJson.CustomerLossNumber+"/"+mCustomAnalyseJson.CustomerTotal;
        textview_custom_analysic_youxiao.setText(numberWuXiao);
        textview_custom_analysic_wuxiao.setText(numberYouXiao);
        textview_custom_analysic_daofang.setText(numberDaoFang);
        textview_custom_analysic_liushi.setText(numberLiuSHI);
        try{
            setChartValue(mCustomAnalyseJson.analyseList);
        }catch (Exception e){
            e.printStackTrace();
            toast("走势图初始化错误！");
        }

    }
    private void setChartValue(ArrayList<AnalyseInfo> analyseList) {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        HashMap<Double, Double> map = new HashMap<Double, Double>();
        int max = 0 ;
        for(int i=0;i<analyseList.size() ;i++){
            if(Integer.valueOf(analyseList.get(i).CustomerNumber)>max){
                max = Integer.valueOf(analyseList.get(i).CustomerNumber);
            }
            map.put((double) Double.parseDouble(analyseList.get(i).Month+"."+i), Double.parseDouble(analyseList.get(i).CustomerNumber));
        }

        int pj = max/5 ;

        String sTemp = String.valueOf(pj);
        String ch = sTemp.substring(0, 1);
        int xx = Integer.valueOf(ch)+1;
        String abc = String.valueOf(xx);
        for(int i = 1;i<sTemp.length();i++){
            abc = abc +"0";
        }
        pj = Integer.valueOf(abc);
        if(pj==0){
            pj = 10 ;
        }
        mAnalyseChartView.SetTuView(map, pj*5, pj, "月", "", false);
    	mAnalyseChartView.invalidate();
    }
    @Override
    public void initWidget() {
        super.initWidget();
        textViewTitle.setText("客户分析");

    }
}
