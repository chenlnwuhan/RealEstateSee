package com.sales.realestate.android.demo;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.ViewInject;

import com.sales.realestate.android.R;
import com.sales.realestate.android.R.id;
import com.sales.realestate.android.R.layout;
import com.sales.realestate.android.demo.widget.KJSlidingMenuDemo;
import com.sales.realestate.android.demo.widget.ScaleImageDemo;

import android.view.View;
import android.widget.Button;

public class WidgetActivity extends KJActivity {
    @BindView(id = R.id.button1, click = true)
    private Button mBtn1;
    @BindView(id = R.id.button2, click = true)
    private Button mBtn2;
    @BindView(id = R.id.button3, click = true)
    private Button mBtn3;
    @BindView(id = R.id.button4, click = true)
    private Button mBtn4;
    @BindView(id = R.id.button5, click = true)
    private Button mBtn5;

    @Override
    public void setRootView() {
        setContentView(R.layout.widget);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        mBtn1.setText("侧滑SlidingMenu");
        mBtn2.setText("上下拉ListView，圆形imageView，万能适配器");
        mBtn3.setText("KJScrollView请访问：https://github.com/KJFrame/KJBlog");
        mBtn4.setText("KJViewPager请访问：https://github.com/kymjs/KJController");
        mBtn5.setText("缩放旋转ImageView");
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
        case R.id.button1:
            showActivity(aty, KJSlidingMenuDemo.class);
            break;
        case R.id.button2:
            break;
        case R.id.button3:
            ViewInject.toast("请查看KJBlog项目");
            break;
        case R.id.button4:
            ViewInject.toast("请查看KJController项目");
            break;
        case R.id.button5:
            showActivity(aty, ScaleImageDemo.class);
            break;
        }
    }
}
