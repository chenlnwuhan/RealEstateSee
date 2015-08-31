package com.sales.realestate.android.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cc on 2015/8/1.
 */
public class FollowMessageInfo extends JsonInfo {
    public ArrayList<FollowStateInfo> customerState = new ArrayList<FollowStateInfo>();
    public ArrayList<FollowDetailInfo> follws = new ArrayList<FollowDetailInfo>();

}
