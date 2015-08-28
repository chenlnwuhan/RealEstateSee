package com.sales.realestate.android.bean;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cc on 2015/8/4.
 */
public class HomeInfo {
    public String EmailTotal;
    public String HouseTotal;
    public String IsLook;
    public String ComeTelTotal;
    public String WeiTotal;
    public String ReportTotal;
    public String OkDefinitionTotal;
    public String NoDefinitionTotal;
    public String OkSchedule;
    public String NoSchedule;
    @SerializedName("bList")
    public ArrayList<BuildingInfo> buildlist = new ArrayList<BuildingInfo>();
}
