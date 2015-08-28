package com.sales.realestate.android.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by chenlin on 2015/8/7.
 */
public class PropertyConsultantJson extends JsonInfo {
    @SerializedName("List")
    public ArrayList<PropertyConsultant> edList = new ArrayList<PropertyConsultant>();
}
