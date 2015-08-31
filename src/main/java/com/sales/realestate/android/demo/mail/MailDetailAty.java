package com.sales.realestate.android.demo.mail;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.MailInfo;
import com.sales.realestate.android.bean.MailJson;
import com.sales.realestate.android.view.adapter.ListAdapterMail;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.StringUtils;

/**
 * Created by chenlin on 2015/7/20.
 */
public class MailDetailAty extends KJActivity {

    @BindView(id = R.id.title_image_left, click = true)
    private ImageView titleImageLeft;

    @BindView(id = R.id.textview_mail_detail_info)
    public TextView textview_mail_detail_info;
    @BindView(id = R.id.textview_mail_detail_date)
    public TextView textview_mail_detail_date;
    @BindView(id = R.id.title_name)
    public TextView textViewTitle;

    public MailInfo mMailInfo;

    public class MailDetailHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_KEY_MAILLDETAIL:
                    if (isError) {
                        toast("邮件未读取！");
                    } else {
                        if (errorMessage.equals("NoData")) {
                            toast("服务器返回邮件信息为空！");
                            return;
                        }
                        Gson gson = new Gson();
                        try {
                            mMailInfo = gson.fromJson(returnStr, MailInfo.class);
                            textview_mail_detail_info.setText(mMailInfo.Contents);
                            textview_mail_detail_date.setText(mMailInfo.SendTime);
                        } catch (Exception e) {
                            toast("邮件解析错误！");
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
        setRootViewResId(R.layout.aty_mail_detail);
        setmBottomNavigation(BottomNavigation.JUSTNOBOTTOM);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            String data = bundle.getString("mailId");
            if (!StringUtils.isEmpty(data)) {
                HttpBusiness.getMailDetail(data,new MailDetailHttpBusiness());
            }
        }
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.title_image_left:
                onBackPressed();
                break;
        }
    }

    @Override
    public void initData() {
        super.initData();
    }

    public void initTitle(int pozition) {
        textViewTitle.setText("邮件详情");
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle(0);
    }


}
