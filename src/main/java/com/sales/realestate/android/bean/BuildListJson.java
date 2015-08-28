package com.sales.realestate.android.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by chenlin on 2015/8/4.
 */
public class BuildListJson extends JsonInfo {
    @SerializedName("List")
    public List<BuildingInfo> buildlist ;
}
