package com.sales.realestate.android.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenlin on 2015/8/5.
 */
public class CustomAnalyseJson extends JsonInfo {
    @SerializedName("list")
    public ArrayList<AnalyseInfo> analyseList ;
    public String CustomerTotal;
    public String CustomerValidNumber;
    public String CustomerInvalidNumber;
    public String CustomerVisitNumber;
    public String CustomerLossNumber;
}
