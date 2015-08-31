package com.sales.realestate.android.demo.more;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sales.realestate.android.CommomKey;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.R;
import com.sales.realestate.android.demo.MainActivity;
import com.sales.realestate.android.demo.SaleAty;
import com.sales.realestate.android.demo.TableAty;
import com.sales.realestate.android.demo.custom.CustomAty;
import com.sales.realestate.android.view.popupwindow.MoreLoginoutPW;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.StringUtils;

/**
 * Created by chenlin on 2015/7/20.
 */
public class MoreAty extends KJActivity {
    @BindView(id = R.id.title_image_left)
    private ImageView titleImageLeft;
    @BindView(id = R.id.title_name)
    public TextView textViewTitle;

    @BindView(id = R.id.imageview_more_name)
    private ImageView imageview_more_name;

    @BindView(id = R.id.relativelayout_more_promotion, click = true)
    public RelativeLayout relativelayout_more_promotion;
    @BindView(id = R.id.relativelayout_more_news, click = true)
    public RelativeLayout relativelayout_more_news;
    @BindView(id = R.id.relativelayout_more_changepassword, click = true)
    public RelativeLayout relativelayout_more_changepassword;
    @BindView(id = R.id.relativelayout_more_about, click = true)
    public RelativeLayout relativelayout_more_about;
    @BindView(id = R.id.button_more_loginout, click = true)
    public Button button_more_loginout;

    @BindView(id = R.id.textview_more_version)
    public TextView textview_more_version;



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

    @BindView(id = R.id.textview_more_name)
    public TextView textview_more_name;

    @BindView(id = R.id.textview_more_phone)
    public TextView textview_more_phone;


    /**
     * 导航栏需要高亮的元素
     */
    @BindView(id = R.id.bottom_menu_img_more)
    public ImageView bottom_menu_img_more;
    @BindView(id = R.id.bottom_menu_text_more)
    public TextView bottom_menu_text_more;

    public MoreLoginoutPW mMoreLoginoutPW;

    @Override
    public void initBottomNavagation() {
        bottom_menu_img_more.setImageResource(R.drawable.pic_bottom_more_foucs);
        bottom_menu_text_more.setTextColor(this.getResources().getColor(R.color.bottom_navigation_text_color_foucs));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivityActionMode(ActionBarMode.CUSTOMTITILE);
        setTitileResId(R.layout.view_title_simple);
        setRootViewResId(R.layout.aty_more);
        setmBottomNavigation(BottomNavigation.JUSTNOSCROLL);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        textViewTitle.setText("更多");
        titleImageLeft.setVisibility(View.GONE);
        textview_more_name.setText(GlobalVarible.USER_NAME + "(" + GlobalVarible.ROLE_NAME + ")");
        textview_more_phone.setText(GlobalVarible.USER_PHONE);
        textview_more_version.setText(GlobalVarible.VERSION_NAME);

        if(!StringUtils.isEmpty(GlobalVarible.USER_HEAD)){
            KJBitmap kjb = new KJBitmap();
            kjb.display(imageview_more_name,
                    GlobalVarible.USER_HEAD);
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
            case R.id.bottom_menu_layout_sale:
                showActivity(this, SaleAty.class);
                break;
            case R.id.relativelayout_more_promotion:
                bundle.putString("newType", "活动");
                showActivity(this, PromotionsNewAty.class, bundle);
                break;
            case R.id.relativelayout_more_news:
                bundle.putString("newType", "新闻");
                showActivity(this, PromotionsNewAty.class, bundle);
                break;
            case R.id.relativelayout_more_changepassword:
                showActivity(this, MoreChangePasswordAty.class);
                break;
            case R.id.relativelayout_more_about:
                showActivity(this, MoreAboutAty.class);
                break;
            case R.id.button_more_loginout:
                if (mMoreLoginoutPW == null) {
                    mMoreLoginoutPW = new MoreLoginoutPW(this);
                    mMoreLoginoutPW.setBuyType(1);
                    mMoreLoginoutPW.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {

                        }
                    });
                }
                mMoreLoginoutPW.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                break;


        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_PROPERTY)){
            navigationSale.setVisibility(View.GONE);
        }
    }
}
