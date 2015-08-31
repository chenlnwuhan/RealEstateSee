package com.sales.realestate.android.view.popupwindow;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sales.realestate.android.AppContext;
import com.sales.realestate.android.GlobalVarible;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.BuildJson;
import com.sales.realestate.android.bean.VesrionJson;

import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.utils.FileUtils;
import org.kymjs.kjframe.utils.KJLoger;
import org.kymjs.kjframe.utils.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by cc on 2015/7/23.
 */
public class MoreCheckupPW extends PopupWindow {
    private View conentView;

    private TextView textview_more_checkup;
    private TextView textview_more_checkup_info;
    private TextView textview_more_checkup_progress;
    private boolean isContinueDownload = true;

    private Button button_more_checkup;
    private Button button_more_checkup_cancel;
    private String fileName = "";


    public VesrionJson getmBuildListJson() {
        return mBuildListJson;
    }

    public void setmBuildListJson(final VesrionJson mBuildListJson) {
        this.mBuildListJson = mBuildListJson;
        textview_more_checkup_progress.setText("");
        isContinueDownload = true;
        button_more_checkup_cancel.setVisibility(View.VISIBLE);
        if (mBuildListJson != null) {
            textview_more_checkup.setText("发现新版本：" + mBuildListJson.APPversion);
            textview_more_checkup_info.setText(mBuildListJson.UpdateRemark);
            if(mBuildListJson.APPFORCED.equals("1")){
                button_more_checkup_cancel.setVisibility(View.GONE);
            }
            button_more_checkup.setText("更新");
            button_more_checkup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(StringUtils.isEmpty(fileName)){
                        fileName = "";
                        isContinueDownload = true ;
                        showProgress2();
                        new FinishRefresh().execute(mBuildListJson.APPPilst);
                    }else{
                        installApk(fileName);
                    }

                }
            });
        }
    }

    public VesrionJson mBuildListJson;
    /**
     * 1 认购申请
     * 2 认购确定
     */
    public int buyType = 1;

    public void setBuyType(int typeIndex) {
        buyType = typeIndex;
        if (buyType == 1) {
            textview_more_checkup.setText("已是最新版本.");
            button_more_checkup.setText("确定");
        } else {
            textview_more_checkup.setText("发现新版本.");
            button_more_checkup.setText("更新");
        }
    }

    public MoreCheckupPW(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popup_more_checkup, null);
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);

        initPopUpWindows();
        initView();
        // 刷新状态
        this.update();
    }

    public void initPopUpWindows() {
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        isContinueDownload = true ;

        //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
        this.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    public void initView() {
        textview_more_checkup = (TextView) conentView.findViewById(R.id.textview_more_checkup);
        textview_more_checkup_info = (TextView) conentView.findViewById(R.id.textview_more_checkup_info);
        textview_more_checkup_progress = (TextView) conentView.findViewById(R.id.textview_more_checkup_progress);
        button_more_checkup = (Button) conentView.findViewById(R.id.button_more_checkup);
        button_more_checkup_cancel = (Button) conentView.findViewById(R.id.button_more_checkup_cancel);
        button_more_checkup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoreCheckupPW.this.dismiss();
            }
        });
        button_more_checkup_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoreCheckupPW.this.dismiss();
            }
        });
        button_more_checkup_cancel.setVisibility(View.GONE);

    }

    private void installApk(String filePath) {
        File apkfile = new File(filePath);
        if (!apkfile.exists()) {
            AppContext.getCurrentActivity().toast("apk文件下载有问题，请重试！");
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        AppContext.getCurrentActivity().startActivity(i);
    }

    private class FinishRefresh extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            URL url;
            String fileName = "tmp-"+System.currentTimeMillis()+".apk";
            fileName =  FileUtils.getSDCardPath() + File.separator + fileName;
            try {
                url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                int length = conn.getContentLength();
                InputStream ins = conn.getInputStream();
                File ApkFile = new File(fileName);
                FileOutputStream outStream = new FileOutputStream(ApkFile);
                int count = 0;
                byte buf[] = new byte[1024];
                do {
                    int numread = ins.read(buf);
                    count += numread;
                    publishProgress((int) ((count / (float) length) * 100) + "");
                    if (numread <= 0) {
                        // 下载完成通知安装
                        break;
                    }
                    outStream.write(buf, 0, numread);
                } while (isContinueDownload);// 点击取消停止下载
                outStream.close();
                ins.close();
            } catch (Exception e) {
                isContinueDownload = false;
                e.printStackTrace();
            }
            if(isContinueDownload==true){
                return fileName;
            }else{
                return null ;
            }

        }

        @Override
        protected void onPostExecute(String result) {
            AppContext.getCurrentActivity().realCancelProgress();
            AppContext.getCurrentActivity().mHandler.removeCallbacks(runnable);
            if(result==null){
                fileName = "";
                textview_more_checkup_progress.setText("下载失败: 请重试！");
            }else{
                fileName = result;
                textview_more_checkup_progress.setText("下载成功！");
                installApk(result);
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            textview_more_checkup_progress.setText("下载进度:  "+values[0]+"%");
        }
    }

    public void showProgress2() {
        if( null != AppContext.getCurrentActivity().mProgress  &&!AppContext.getCurrentActivity().mProgress.isShowing()) {
            try {
                AppContext.getCurrentActivity().mProgress.show();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            AppContext.getCurrentActivity().mProgress.setContentView(R.layout.dialog_wait);
            AppContext.getCurrentActivity().mHandler.postDelayed(runnable,180000);

        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            AppContext.getCurrentActivity().realCancelProgress();
            AppContext.getCurrentActivity().mHandler.removeCallbacks(runnable);
            isContinueDownload = false;
        }
    };

}
