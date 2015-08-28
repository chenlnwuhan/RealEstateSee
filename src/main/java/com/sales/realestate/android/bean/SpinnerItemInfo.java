package com.sales.realestate.android.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chenlin on 2015/7/12.
 */
public class SpinnerItemInfo {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCurrent = false;

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    @SerializedName("ID")
    public String id;
    @SerializedName("Name")
    public String name;

    public String RoomName ;

    public String parentId = "";
}
