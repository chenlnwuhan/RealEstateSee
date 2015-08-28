package com.sales.realestate.android;

import android.app.Application;

import org.kymjs.kjframe.KJActivity;

public class AppContext extends Application {

    private static KJActivity currentActivity;
    public static Application mApplication;

    public static void setCurrentActivity(KJActivity mKJActivity) {
        currentActivity = mKJActivity;
    }

    public static KJActivity getCurrentActivity() {
        return currentActivity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        mApplication = this;



        // CrashHandler.create(this);
    }
}
