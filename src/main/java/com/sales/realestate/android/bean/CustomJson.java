package com.sales.realestate.android.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by chenlin on 2015/8/7.
 */
public class CustomJson extends JsonInfo {

    public ArrayList<CustomInfo> Deallist = new ArrayList<CustomInfo>();
    @SerializedName("List")
    public ArrayList<CustomInfo> customList = new ArrayList<CustomInfo>();
    public ArrayList<CustomInfo> customerListSub = new ArrayList<CustomInfo>();
    public String totalCount;
}
