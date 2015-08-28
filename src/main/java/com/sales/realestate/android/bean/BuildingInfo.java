package com.sales.realestate.android.bean;

import org.kymjs.kjframe.database.annotate.Id;

/**
 * Created by chenlin on 2015/7/12.
 */
public class BuildingInfo {
public String CommissionCycleEntTime;
public String RuleDetails;
public String IsDel;
public String AddUser;
public String AddUserName;
public String AddTime;
public String Sort;
public String BuildingTypeName;
public String CityID;


public String ProtectionValue1;
public String ReChouReceivables;
public String SetReceivables;
public String OkReceivables;
public String CollectionVoucher;
public String CustomerExpireCount;
public String CommissionCycle;
public String Commission;
public String QuYuName;
public String IsAgentLook;
public String QuYuID;
public String ShangQuanName;
public String ShangQuanID;
@Id()
private String id;
private String name;
private String others;
public String BuildingID;
public String BuildingName;
public String BuildingAddress;
public int BuildingType;
public String StartTime;
public String EndTime;
public String BuildingOpenTime;
public String ImageUrl;
public String BuildingDetails;
public String BuildingMean;
public String XBaiDu;
public String YBaidu;

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {

        return id;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }


}
