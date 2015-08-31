package com.sales.realestate.android.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenlin on 2015/8/7.
 */
public class CustomTypeJson {
    @SerializedName("List")
    public ArrayList<SpinnerItemInfo> edList = new ArrayList<SpinnerItemInfo>();
}
