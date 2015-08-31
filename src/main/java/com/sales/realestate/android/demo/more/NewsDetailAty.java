package com.sales.realestate.android.demo.more;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.NewsInfo;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.StringUtils;

/**
 * Created by chenlin on 2015/7/20.
 */
public class NewsDetailAty extends KJActivity {

    @BindView(id = R.id.title_image_left, click = true)
    private ImageView titleImageLeft;

    @BindView(id = R.id.textview_news_detail_title)
    public TextView textview_news_detail_title;
    @BindView(id = R.id.textview_news_detail_info)
    public TextView textview_news_detail_info;
    @BindView(id = R.id.textview_news_detail_date)
    public TextView textview_news_detail_date;
    @BindView(id = R.id.textview_more_news_end)
    public TextView textview_more_news_end;
    @BindView(id = R.id.textview_more_news_start)
    public TextView textview_more_news_start;
    @BindView(id = R.id.title_name)
    public TextView textViewTitle;

    public NewsInfo mMailInfo;
    @BindView(id = R.id.linearlayout_more_news_title)
    public LinearLayout linearlayout_more_news_title;

    public class NewsIndexHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_KEY_NOTICEDETAIL:
                    if (isError) {
                        toast(textViewTitle.getText().toString() + "未读取！");
                    } else {
                        try {
                            Gson gson = new Gson();
                            NewsInfo mUserInfo = gson.fromJson(returnStr, NewsInfo.class);
                            textview_news_detail_title.setText(mUserInfo.NewsTitle);
                            textview_news_detail_info.setText(Html.fromHtml(mUserInfo.NewsContens));
                            textview_news_detail_date.setText(mUserInfo.AddTime);
                            textview_more_news_start.setText(mUserInfo.StarTime);
                            textview_more_news_end.setText(mUserInfo.EndTime);
                        } catch (Exception e) {
                            toast(textViewTitle.getText().toString() + "解析错误！");
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
        setRootViewResId(R.layout.aty_more_news_detail);
        setmBottomNavigation(BottomNavigation.JUSTNOBOTTOM);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String data = bundle.getString("newsId");
            String dataType = bundle.getString("newsType");
            if (!StringUtils.isEmpty(data)) {
                if (dataType.equals("1")) {
                    textViewTitle.setText("新闻公告");
                    linearlayout_more_news_title.setVisibility(View.GONE);
                } else {
                    textViewTitle.setText("优惠活动");
                }
                HttpBusiness.getNoticesListHTTP(data, new NewsIndexHttpBusiness());
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
