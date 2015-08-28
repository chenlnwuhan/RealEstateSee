package com.sales.realestate.android.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cc on 2015/8/5.
 */
public class AddressJson extends JsonInfo {
    @SerializedName("BookList")
    public ArrayList<AddressInfo> BookList ;
}
