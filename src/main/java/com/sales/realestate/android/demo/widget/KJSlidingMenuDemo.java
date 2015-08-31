package com.sales.realestate.android.demo.widget;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.ViewInject;
import org.kymjs.kjframe.widget.KJSlidingMenu;

import com.sales.realestate.android.R;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class KJSlidingMenuDemo extends KJActivity {

    @BindView(id = R.id.textview, click = true)
    private TextView mText;
    @BindView(id = R.id.anim_switch, click = true)
    private Button mSwitch;
    @BindView(id = R.id.main_group)
    private KJSlidingMenu mSliding;

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_main);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        mSwitch.setText("是否打开伪3D效果" + mSliding.isShowAnim());
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
        case R.id.textview:
            ViewInject.toast("hello");
            break;
        case R.id.anim_switch:
            mSliding.setShowAnim(true);
            mSwitch.setText("伪3D效果已打开");
            break;
        default:
            break;
        }
    }
}
