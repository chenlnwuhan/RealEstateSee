package com.sales.realestate.android.demo.todo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sales.realestate.android.CommomKey;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.HttpBusiness;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.ToDoListJson;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;

/**
 * Created by cc on 2015/7/24.
 */
public class ToDoListAty extends KJActivity {
    @BindView(id = R.id.title_image_left, click = true)
    private ImageView titleImageLeft;
    @BindView(id = R.id.title_name)
    public TextView textViewTitle;


    /**
     * 代办跳转控件表
     */
    @BindView(id = R.id.img_todolist_customdistribution_next, click = true)
    private RelativeLayout img_todolist_customdistribution_next;
    @BindView(id = R.id.img_todolist_customseeapply_next, click = true)
    private RelativeLayout img_todolist_customseeapply_next;
    @BindView(id = R.id.img_todolist_customform_next, click = true)
    private RelativeLayout img_todolist_customform_next;
    @BindView(id = R.id.img_todolist_custombuy_next, click = true)
    private RelativeLayout img_todolist_custombuy_next;
    @BindView(id = R.id.img_todolist_customdone_next, click = true)
    private RelativeLayout img_todolist_customdone_next;
    @BindView(id = R.id.img_todolist_saleform_next, click = true)
    private RelativeLayout img_todolist_saleform_next;
    @BindView(id = R.id.img_todolist_salebuy_next, click = true)
    private RelativeLayout img_todolist_salebuy_next;
    @BindView(id = R.id.img_todolist_saledone_next, click = true)
    private RelativeLayout img_todolist_saledone_next;

    @BindView(id = R.id.textview_todolist_customdistribution)
    public TextView textview_todolist_customdistribution;
    @BindView(id = R.id.textview_todolist_customseeapply)
    public TextView textview_todolist_customseeapply;
    @BindView(id = R.id.textview_todolist_customform)
    public TextView textview_todolist_customform;
    @BindView(id = R.id.textview_todolist_custombuy)
    public TextView textview_todolist_custombuy;
    @BindView(id = R.id.textview_todolist_customdone)
    public TextView textview_todolist_customdone;
    @BindView(id = R.id.textview_todolist_saleform)
    public TextView textview_todolist_saleform;
    @BindView(id = R.id.textview_todolist_salebuy)
    public TextView textview_todolist_salebuy;
    @BindView(id = R.id.textview_todolist_saledone)
    public TextView textview_todolist_saledone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivityActionMode(ActionBarMode.CUSTOMTITILE);
        setTitileResId(R.layout.view_title_simple);
        setRootViewResId(R.layout.aty_todolist);
        setmBottomNavigation(BottomNavigation.NOBOTTOMSCROLL);
        super.onCreate(savedInstanceState);
    }

    public class ToDoListHttpBusiness extends HttpBusiness.MyCallBack {
        @Override
        public void onSuccess(int uid, String returnStr) {
            super.onSuccess(uid, returnStr);
            switch (uid) {
                case HttpBusiness.HTTP_KEY_TODOLIST:
                    if (isError) {
                        toast("待办事项列表未读取！");
                    } else {
                        Gson gson = new Gson();
                        try {
                            ToDoListJson mToDoListJson = gson.fromJson(returnStr, ToDoListJson.class);
                            textview_todolist_customdistribution.setText(mToDoListJson.AgentDefinedCount+"/"+mToDoListJson.AgentTotalApply);
                            textview_todolist_customseeapply.setText(mToDoListJson.AgentSeeCount+"/"+mToDoListJson.AgentTotalApply);
                            textview_todolist_customform.setText(mToDoListJson.AgentFromCount+"/"+mToDoListJson.AgentTotalApply);
                            textview_todolist_custombuy.setText(mToDoListJson.AgentTrancheCount+"/"+mToDoListJson.AgentTotalApply);
                            textview_todolist_customdone.setText(mToDoListJson.AgentDealCount+"/"+mToDoListJson.AgentTotalApply);
                            textview_todolist_saleform.setText(mToDoListJson.BuildingFromCount+"/"+mToDoListJson.BuildingTotalApply);
                            textview_todolist_salebuy.setText(mToDoListJson.BuildingTrancheCount+"/"+mToDoListJson.BuildingTotalApply);
                            textview_todolist_saledone.setText(mToDoListJson.BuildingDealCount+"/"+mToDoListJson.BuildingTotalApply);
                        } catch (Exception e) {
                            toast("待办事项解析错误！");
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void initWidget() {
        super.initWidget();
        textViewTitle.setText("待办事项");
        if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_DIRECTOR)) {
            img_todolist_customdistribution_next.setVisibility(View.GONE);
            img_todolist_customseeapply_next.setVisibility(View.GONE);
            img_todolist_customform_next.setVisibility(View.VISIBLE);;
            img_todolist_custombuy_next.setVisibility(View.VISIBLE);
            img_todolist_customdone_next.setVisibility(View.VISIBLE);
            img_todolist_saleform_next.setVisibility(View.VISIBLE);
            img_todolist_salebuy_next.setVisibility(View.VISIBLE);
            img_todolist_saledone_next.setVisibility(View.VISIBLE);
        } else if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_MANAGER)) {
            img_todolist_customdistribution_next.setVisibility(View.GONE);
            img_todolist_customseeapply_next.setVisibility(View.GONE);
            img_todolist_customform_next.setVisibility(View.VISIBLE);;
            img_todolist_custombuy_next.setVisibility(View.GONE);
            img_todolist_customdone_next.setVisibility(View.GONE);
            img_todolist_saleform_next.setVisibility(View.VISIBLE);
            img_todolist_salebuy_next.setVisibility(View.GONE);
            img_todolist_saledone_next.setVisibility(View.GONE);
        } else if (GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_PROPERTY)) {
            img_todolist_customdistribution_next.setVisibility(View.VISIBLE);
            img_todolist_customseeapply_next.setVisibility(View.VISIBLE);
            img_todolist_customform_next.setVisibility(View.GONE);;
            img_todolist_custombuy_next.setVisibility(View.GONE);
            img_todolist_customdone_next.setVisibility(View.GONE);
            img_todolist_saleform_next.setVisibility(View.GONE);
            img_todolist_salebuy_next.setVisibility(View.GONE);
            img_todolist_saledone_next.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        HttpBusiness.getToDoListHTTP(new ToDoListHttpBusiness());

    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.img_todolist_customdistribution_next:
                bundle.putString("ApplyType", "0");
                this.showActivity(this, ToDoListDetailAty.class, bundle);
                break;
            case R.id.img_todolist_customseeapply_next:
                bundle.putString("ApplyType", "1");
                this.showActivity(this, ToDoListDetailAty.class, bundle);
                break;
            case R.id.img_todolist_customform_next:
                bundle.putString("ApplyType", "2");
                this.showActivity(this, ToDoListDetailAty.class, bundle);
                break;
            case R.id.img_todolist_custombuy_next:
                bundle.putString("ApplyType", "3");
                this.showActivity(this, ToDoListDetailAty.class, bundle);
                break;
            case R.id.img_todolist_customdone_next:
                bundle.putString("ApplyType", "4");
                this.showActivity(this, ToDoListDetailAty.class, bundle);
                break;
            case R.id.img_todolist_saleform_next:
                bundle.putString("ApplyType", "5");
                this.showActivity(this, ToDoListDetailAty.class, bundle);
                break;
            case R.id.img_todolist_salebuy_next:
                bundle.putString("ApplyType", "6");
                this.showActivity(this, ToDoListDetailAty.class, bundle);
                break;
            case R.id.img_todolist_saledone_next:
                bundle.putString("ApplyType", "7");
                this.showActivity(this, ToDoListDetailAty.class, bundle);
                break;
            case R.id.title_image_left:
                onBackPressed();
                break;

        }
    }
}
