package com.sales.realestate.android.bean;

/**
 * Created by cc on 2015/8/2.
 * {"NewsHouseID": 1,"RoomName": "101室","Dong": 1,"Dan": 1},{"NewsHouseID": 2,"RoomName": "102室","Dong": 1,"Dan": 1}
 * HouseType	整数	房源类型 1是合并 2是拆分
 * IsSell	整数	0是未售出，1是已售,2是认购锁定 ,3是预留,4已购,5是已售
 * HouseTypeName	字符	当 HouseType=1的时候请看这个值96ab3-1-2。
 第二个参数1代表列，2代表行
 第三个参数代表合并位数
 */
public class HouseInfo {
    public String NewsHouseID ;
    public String RoomName = "";
    public String Dong = "0";
    public String Dan ="0";
    public String HouseTypeName ;
    public String Ceng;
    public String HouseType ;
    public String IsSell ;
    public String HuXingName;
    public String Acreage;
    public int buyType;
    public int compareTo(HouseInfo arg0) {
        return this.RoomName.compareTo(arg0.RoomName);
    }
}
