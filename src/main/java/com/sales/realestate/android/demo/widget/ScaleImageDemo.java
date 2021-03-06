package com.sales.realestate.android.demo.widget;

import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.widget.ScaleImageView;

import com.sales.realestate.android.R;

import android.view.View;
import android.widget.Button;

public class ScaleImageDemo extends KJActivity {
    @BindView(id = R.id.button1, click = true)
    Button mBtn1;
    @BindView(id = R.id.button2, click = true)
    Button mBtn2;
    @BindView(id = R.id.images)
    ScaleImageView image;

    @Override
    public void setRootView() {
        setContentView(R.layout.scale_img);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        mBtn1.setText("否可双击缩放：" + image.canDoubleClick());
        mBtn2.setText("是否可旋转" + image.canRotate());
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
        case R.id.button1:
            image.setCanDoubleClick(!image.canDoubleClick());
            mBtn1.setText("否可双击缩放：" + image.canDoubleClick());
            break;
        case R.id.button2:
            image.setCanRotate(!image.canRotate());
            mBtn2.setText("是否可旋转" + image.canRotate());
            break;
        default:
            break;
        }
    }

}
