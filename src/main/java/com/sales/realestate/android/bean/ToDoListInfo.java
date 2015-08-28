package com.sales.realestate.android.bean;

import java.security.PublicKey;

/**
 * Created by chenlin on 2015/7/24.
 */
public class ToDoListInfo {
    public String id;
    /**
     * 0 客户界定
     * 1 带看申请
     * 2 认筹申请
     * 3 认购申请
     * 4 成交申请
     * 5 销控认筹
     * 6 销控认购
     * 7 销控成交
     */
    public String ApplyID = "";
    public String ApplyType = "0";
    public String BuildingName = "";
    public String IsExamine = "";
    public String ApplyTime = "";
    public String CustomerID = "";
    public String CustomerValid = "";
    public String CustomerName = "";
}
