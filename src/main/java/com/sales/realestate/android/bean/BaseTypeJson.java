package com.sales.realestate.android.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by chenlin on 2015/8/7.
 */
public class BaseTypeJson {
    @SerializedName("baseClassSub")
    public ArrayList<SpinnerItemInfo> edList = new ArrayList<SpinnerItemInfo>();
}
