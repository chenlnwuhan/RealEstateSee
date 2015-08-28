package com.sales.realestate.android;

import com.sales.realestate.android.demo.MainActivity;

import org.kymjs.kjframe.http.HttpParams;
import org.kymjs.kjframe.utils.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Url API 拼接相关方法
 *
 * @author chenlin
 */
public class UrlApiConfig {

    public final static String URL_START = "http://120.24.237.227:8086/api/";
    public final static String URL_END = "";
    public final static String TerminalType = "2";

    //访问量客户列表
    public final static String URL_SET_HOUSE_STATUS = URL_START + "Home/SetNewsHouseStatus" + URL_END;
    //客户到访量
    public final static String URL_GET_HOUSE_DETAIL = URL_START + "Home/GetNewsHouseDetails" + URL_END;
    public final static String URL_CUSTOM_TO_SEED = URL_START + "Home/Look" + URL_END;



    //用户登录Url
    public final static String URL_USER_LOGIN = URL_START + "UserLogin/Login" + URL_END;

    public final static String URL_BUILDING = URL_START + "Home/GetBuildingInfoList" + URL_END;

    public final static String URL_HOME_INDEX = URL_START + "Home/Index" + URL_END;

    public final static String URL_CUSTOM_ANALYSE = URL_START + "CustomerInfo/CustomerAnalysis" + URL_END;

    public final static String URL_CUSTOM_DEFINE = URL_START + "CustomerInfo/GetCustomerDefineList" + URL_END;

    public final static String URL_TODOLIST = URL_START + "CustomerInfo/AgencyMatters" + URL_END;

    public final static String URL_USER_ERWEIMA = URL_START + "Home/ErWeiMaSaoMa" + URL_END;
    //用户修改密码
    public final static String URL_USER_CHANGEPASSWORD = URL_START + "UserLogin/UpdatePwd" + URL_END;

    public final static String URL_USER_APPLYLIST = URL_START + "CustomerInfo/GetApplyInfoList" + URL_END;

    public final static String URL_USER_BASEINFO = URL_START + "CustomerInfo/GetBaseInfo" + URL_END;

    public final static String URL_USER_TABLEVOLUME = URL_START + "CustomerInfo/DealConsultantRpt" + URL_END;

    public final static String URL_USER_TABLERATE = URL_START + "CustomerInfo/DealPercentage" + URL_END;

    public final static String URL_USER_TABLENUMBER = URL_START + "CustomerInfo/DealConsultantList" + URL_END;

    public final static String URL_USER_TABLENUMBERRATE = URL_START + "CustomerInfo/DealPercentageList" + URL_END;

    public final static String URL_PROPERTYCONSULTANT = URL_START + "Home/GetBuildingUserInfoList" + URL_END;
    //申请的客户列表
    public final static String URL_CLIENTS_LIST = URL_START + "CustomerInfo/GetCustomerList" + URL_END;
    //客户详细
    public final static String URL_CLIENTS_DETAIL = URL_START + "CustomerInfo/CustomerInfo" + URL_END;
    //客户搜索
    public final static String URL_CLIENTS_SERACH = URL_START + "Home/GetCustomerInfoList" + URL_END;
    //客户录入
    public final static String URL_CLIENTS_ADD = URL_START + "Home/AddCustomer" + URL_END;
    //客户类型
    public final static String URL_CLIENTS_TYPE = URL_START + "Home/CustomerFlowTypeInfo" + URL_END;
    //付款类型
    public final static String URL_PAY_TYPE = URL_START + "Home/GetPayTypeInfo" + URL_END;
    //主页成交量
    public final static String URL_TIME_VOLUME = URL_START + "CustomerInfo/DealCustomerRpt" + URL_END;
    //成交量客户列表
    public final static String URL_VOLUME_CUSTOM = URL_START + "CustomerInfo/DealCustomerList" + URL_END;
    //分配客户
    public final static String URL_CUSTOM_DISTRIBUTE = URL_START + "Home/DistriCustomer" + URL_END;
    //申请认筹
    public final static String URL_FORM_APPLY = URL_START + "Home/ApplySigning" + URL_END;
    //申请认购
    public final static String URL_BUY_APPLY = URL_START + "Home/ApplyMake" + URL_END;
    //申请成交
    public final static String URL_DONE_APPLY = URL_START + "Home/ApplyTrading" + URL_END;
    //确认认筹
    public final static String URL_FORM_CONFIRM = URL_START + "Home/Signing" + URL_END;
    //确认认购
    public final static String URL_BUY_CONFIRM = URL_START + "Home/Make" + URL_END;
    //确认成交
    public final static String URL_DONE_CONFIRM = URL_START + "Home/Trading" + URL_END;
    //更新用户等级
    public final static String URL_UPDATE_CUSETOMLEVEL = URL_START + "CustomerInfo/UpdateCustomerLeve" + URL_END;

    public final static String URL_CUSETOM_FOLLOWLIST = URL_START + "CustomerInfo/FollowMessage" + URL_END;
    public final static String URL_CUSETOM_FOLLOWDETAIL = URL_START + "CustomerInfo/FollowInfo" + URL_END;
    public final static String URL_CUSETOM_FOLLOWADD = URL_START + "CustomerInfo/AddCustomerFollow" + URL_END;

    public final static String URL_BAIDU_CLOUD = URL_START + "Home/UserMessageInfo" + URL_END;

    public final static String URL_DEFINITION_LIST = URL_START + "CustomerInfo/GetApplyDefineList" + URL_END;

    public final static String URL_DEFINITION_DETAIL = URL_START + "CustomerInfo/GetCustomerDefineInfo" + URL_END;

    public final static String URL_DEFINITION_UPDATE = URL_START + "CustomerInfo/UpdateCustomerDefine" + URL_END;

    public final static String URL_HOME_CLEAR = URL_START + "Home/ClearHouse" + URL_END;

    public final static String URL_HOUSE_DETAIL_OTHERS = URL_START + "Home/GetNewsHouseHeDetails" + URL_END;

    public final static String URL_CHECK_VESRION = URL_START + "CustomerInfo/CheckAppVersion" + URL_END;

    //¿Í»§´ø¿´
    public final static String URL_GET_CUSTOM_SEE = URL_START + "CustomerInfo/GetCustomerSeeInfo" + URL_END;
    //¸üÐÂ¿Í»§´ø¿´
    public final static String URL_UPDATE_CUSTOM_SEE = URL_START + "CustomerInfo/UpdateCustomerLook" + URL_END;
    //¿Í»§ÈÏ³ï
    public final static String URL_GET_CUSTOM_FROM = URL_START + "CustomerInfo/GetCustomerRewardInfo" + URL_END;
    //¸üÐÂ¿Í»§ÈÏ³ï
    public final static String URL_UPDATE_CUSTOM_FROM = URL_START + "CustomerInfo/CustomerRewardConfirm" + URL_END;
    //¿Í»§ÈÏ¹º
    public final static String URL_GET_CUSTOM_BUY = URL_START + "CustomerInfo/GetCustomerSubscribeInfo" + URL_END;
    //¸üÐÂ¿Í»§ÈÏ¹º
    public final static String URL_UPDATE_CUSTOM_BUY = URL_START + "CustomerInfo/SubscribeCustomerConfirm" + URL_END;

    //¿Í»§³É½»
    public final static String URL_GET_CUSTOM_DONE = URL_START + "CustomerInfo/GetCustomerDealInfo" + URL_END;
    //¸üÐÂ¿Í»§³É½»
    public final static String URL_UPDATE_CUSTOM_DONE = URL_START + "CustomerInfo/UpdateCustomerDeal" + URL_END;



    //楼盘销控表
    public final static String URL_SALES_LIST = URL_START + "Home/GetHouseInfoList" + URL_END;
    //ANDROID最新版本
    public final static String URL_ANDROID_VERSION = URL_START + "/android/version" + URL_END;
    //ANDROID最新版本下载地址
    public final static String URL_ANDROID_FILEURL = URL_START + "/android/" + URL_END;
    //新闻公告列表
    public final static String URL_NOTICES_LIST = URL_START + "News/GetNewsORactivityList" + URL_END;
    //新闻公告列表
    public final static String URL_NOTICES_DETAIL = URL_START + "News/GetNewsInfo" + URL_END;
    //用户的邮件列表
    public final static String URL_MAIL_DETAIL = URL_START + "Mail/ReadMail" + URL_END;
    //用户的邮件列表
    public final static String URL_MESSAGES_LIST = URL_START + "Mail/GetMail" + URL_END;
    //用户的通讯录
    public final static String URL_CONTACTS_LIST = URL_START + "CustomerInfo/GetAddressBook" + URL_END;

    //访问量客户列表
    public final static String URL_VOLUME_VISIT_CUSTOM = URL_START + "CustomerInfo/VisitCustomerList" + URL_END;
    //客户到访量
    public final static String URL_TIME_VISIT_VOLUME = URL_START + "CustomerInfo/VisitCustomerRpt" + URL_END;

    public final static String URL_DEL_MAIL =  URL_START + "Mail/DelMail" + URL_END;

    public final static String URL_SEND_MESSAGE =  URL_START + "UserLogin/SendMessage" + URL_END;

    public final static String UR_MESSAGE_LOGIN =  URL_START + "UserLogin/VerificationLogin" + URL_END;


    public   static HttpParams setSendMessageParams(String loginId) {
        HttpParams params = new HttpParams();
        params.put("UserName", loginId);
        params.put("SendMsgTypes", "2");
        return params;
    }

    public   static HttpParams setUrlHouseStatusParams(String NewsHouseID,String IsSell,String PayName,String Remark,String OkMoney) {
        HttpParams params = getNewHttpParams();
        params.put("NewsHouseID", NewsHouseID);
        params.put("IsSell", IsSell);
        params.put("PayName", PayName);
        params.put("Remark", Remark);
        params.put("OkMoney", OkMoney);
        return params;
    }

    public  static HttpParams updateCustomDoneParams(String BuildingID,String CustomerID,String ApplyID,String IsLook,String Details,String CommissionValue,String NewsHouseID,String BuildingName,String RoomName,String DealTime,String DealMoney,String CustomerName) {
        HttpParams params = getNewHttpParams();
        params.put("BuildingID", BuildingID);
        params.put("CustomerID", CustomerID);
        params.put("ApplyID", ApplyID);
        params.put("IsOk",IsLook);
        params.put("Details",Details);
        params.put("CommissionValue",CommissionValue);
        params.put("NewsHouseID",NewsHouseID);
        params.put("BuildingName",BuildingName);
        params.put("RoomName",RoomName);
        params.put("DealTime",DealTime);
        params.put("DealMoney",DealMoney);
        params.put("CustomerName",CustomerName);

        return params;
    }

    public  static HttpParams getHomeClearParams(String NewsHouseID) {
        HttpParams params = getNewHttpParams();
        params.put("NewsHouseID", NewsHouseID);
        params.put("IsSell", "3");
        return params;
    }

    public  static HttpParams getBaiduParams(String Channel_ID) {
        HttpParams params = getNewHttpParams();
        params.put("Channel_ID", Channel_ID);
        params.put("AppType", "1");
        params.put("UserType", "2");
        return params;
    }




    public  static HttpParams updateCustomBuyParams(String BuildingID,String CustomerID,String ApplyID,String IsLook,String Details,String NewsHouseID,String CustomerName,String IsSetTime,String IsSetMoney,String RoomName,String BuildingName) {
        HttpParams params = getNewHttpParams();
        params.put("BuildingID", BuildingID);
        params.put("CustomerID", CustomerID);
        params.put("ApplyID", ApplyID);
        params.put("IsSet",IsLook);
        params.put("Details",Details);
        params.put("NewsHouseID",NewsHouseID);
        params.put("CustomerName",CustomerName);
        params.put("IsSetTime",IsSetTime);
        params.put("IsSetMoney",IsSetMoney);
        params.put("RoomName",RoomName);
        params.put("BuildingName",BuildingName);
        return params;
    }

    public  static HttpParams updateCustomDefinitionParams(String BuildingID,String CustomerID,String ApplyID,String CustomerValid,String Details,String DefineTime) {
        HttpParams params = getNewHttpParams();
        params.put("BuildingID", BuildingID);
        params.put("CustomerID", CustomerID);
        params.put("ApplyID", ApplyID);
        params.put("CustomerValid",CustomerValid);
        params.put("Details",Details);
        params.put("DefineTime",DefineTime);

        return params;
    }
    public  static HttpParams updateCustomFromParams(String BuildingID,String CustomerID,String ApplyID,String IsLook,String Details,String LookTime,String BuildingName,String setTime,String CustomerName) {
        HttpParams params = getNewHttpParams();
        params.put("BuildingID", BuildingID);
        params.put("CustomerID", CustomerID);
        params.put("ApplyID", ApplyID);
        params.put("IsRenChou",IsLook);
        params.put("Details",Details);
        params.put("CheckRenChouTime",LookTime);
        params.put("BuildingName",BuildingName);
        params.put("RenChouMoney",setTime);
        params.put("CustomerName",CustomerName);
        return params;
    }
    public  static HttpParams updateCustomSeeParams(String BuildingID,String CustomerID,String ApplyID,String IsLook,String Details,String LookTime) {
        HttpParams params = getNewHttpParams();
        params.put("BuildingID", BuildingID);
        params.put("CustomerID", CustomerID);
        params.put("ApplyID", ApplyID);
        params.put("IsLook",IsLook);
        params.put("Details",Details);
        params.put("LookTime",LookTime);
        return params;
    }
    public  static HttpParams setCustomSeeParams(String BuildingID,String CustomerID,String ApplyID) {
        HttpParams params = getNewHttpParams();
        params.put("BuildingID", BuildingID);
        params.put("CustomerID", CustomerID);
        params.put("ApplyID", ApplyID);
        params.put("UserName",GlobalVarible.USER_NAME );
        return params;
    }

    public  static HttpParams getUrlHouseDetailParams(String NewsHouseID,String IsSell) {
        HttpParams params = getNewHttpParams();
        params.put("NewsHouseID", NewsHouseID);
        params.put("IsSell", IsSell);
        return params;
    }

    public  static HttpParams checkVersion(String APPversion) {
        HttpParams params = getNewHttpParams();
        params.put("APPversion", APPversion);
        params.put("APPType", "0");
        return params;
    }


    public  static HttpParams getUrlPropertyConsultantParams(String Buildingid) {
        HttpParams params = getNewHttpParams();
        params.put("Buildingid", Buildingid);
        return params;
    }

    public  static HttpParams getUpdateCustomLevelParams(String CustomerID,String Leve) {
        HttpParams params = getNewHttpParams();
        params.put("CustomerID", CustomerID);
        params.put("Leve", Leve);
        return params;
    }
    public  static HttpParams getAddCustomFollowInfoParams(String CustomerID,String From,String Details) {
        HttpParams params = getNewHttpParams();
        params.put("CustomerID", CustomerID);
        params.put("From", From);
        params.put("Details", Details);
        return params;
    }

    public  static HttpParams getCustomFollowTypeDetailParams(String CustomerID,String ID) {
        HttpParams params = getNewHttpParams();
        params.put("CustomerID", CustomerID);
        params.put("ID", ID);
        return params;
    }

    public  static HttpParams getUrlTableVolumeParams(String BeginTime,String EndTime,String DictID) {
        HttpParams params = getNewHttpParams();
        params.put("BeginTime", BeginTime);
        params.put("EndTime", EndTime);
        params.put("DictID", DictID);
        params.put("BuildingID", MainActivity.BuildingID);
        return params;
    }

    public  static HttpParams getUrlTableOrderParams(String PageIndex,String BeginTime,String EndTime,String DictID) {
        HttpParams params = getNewHttpParams();
        params.put("BeginTime", BeginTime);
        params.put("EndTime", EndTime);
        params.put("PageIndex", PageIndex);
        params.put("DictID", DictID);
        return params;
    }


    public  static HttpParams getUrlTableVolumeParams(String BeginTime,String EndTime) {
        HttpParams params = getNewHttpParams();
        params.put("BeginTime", BeginTime);
        params.put("EndTime", EndTime);
        params.put("BuildingID", MainActivity.BuildingID);
        return params;
    }

    public  static HttpParams getUrlCustomDistributeParams(String UserID,String BuildingID,String CustomerIDList) {
        HttpParams params = new HttpParams();
        params.put("UserID", UserID);
        params.put("BuildingID", BuildingID);
        params.put("CustomerIDList", CustomerIDList);
        return params;
    }

    public  static HttpParams getUrlCustomFormApplyParams(String buildingid,String details,String renChouTime,String CustomerID,String renChouMoney) {
        HttpParams params = getNewHttpParams();
        params.put("buildingid", buildingid);
        try {
            params.put("details", URLDecoder.decode(details, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        params.put("renChouTime", renChouTime);
        params.put("CustomerID", CustomerID);
        params.put("renChouMoney", renChouMoney);
        return params;
    }

    public  static HttpParams getUrlCustomFormConfirmParams(String buildingid,String details,String renChouTime,String customerID,String renChouMoney) {
        HttpParams params = getNewHttpParams();
        params.put("buildingid", buildingid);
        try {
            params.put("details", URLDecoder.decode(details, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        params.put("renChouTime", renChouTime);
        params.put("customerID", customerID);
        params.put("renChouMoney", renChouMoney);
        return params;
    }

    public  static HttpParams getUrlCustomBuyParams(String buildingid,String Remark,String setTime,String customerID,String roomID,String roomName,String SetMoney) {
        HttpParams params = getNewHttpParams();
        params.put("buildingid", buildingid);
        try {
            params.put("Remark", URLDecoder.decode(Remark, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        params.put("setTime", setTime);
        params.put("roomID", roomID);
        params.put("customerID", customerID);
        params.put("roomName", roomName);
        params.put("SetMoney", SetMoney);

        return params;
    }

    public  static HttpParams getUrlCustomDoneParams(String buildingid,String Remark,String OkTime,String customerID,String RoomID,String RoomName,String OkMoney,String PayType,String CommissionValue,String SetMoney) {
        HttpParams params = getNewHttpParams();
        params.put("buildingid", buildingid);
        try {
            params.put("Remark", URLDecoder.decode(Remark, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        params.put("OkTime", OkTime);
        params.put("RoomID", RoomID);
        params.put("customerID", customerID);
        params.put("RoomName", RoomName);
        params.put("OkMoney", OkMoney);
        params.put("PayType", PayType);
        params.put("SetMoney", SetMoney);
        params.put("CommissionValue", CommissionValue);
        return params;
    }







    public  static HttpParams getUrlTableOrderParams(String PageIndex,String BeginTime,String EndTime) {
        HttpParams params = getNewHttpParams();
        params.put("BeginTime", BeginTime);
        params.put("EndTime", EndTime);
        params.put("PageIndex", PageIndex);
        params.put("BuildingID", MainActivity.BuildingID);
        return params;
    }

    public  static HttpParams getUrlBaseTypeParams(String Type) {
        HttpParams params = getNewHttpParams();
        params.put("Type", Type);
        return params;
    }
    
    public  static HttpParams getUrlCustomAnalyseParams(String BuildingID) {
        HttpParams params = getNewHttpParams();
        params.put("BuildingID", BuildingID);
        return params;
    }

    public  static HttpParams getUrlApplyListParams(String PageIndex,String ApplyTypeID,String BrandID) {
        HttpParams params = getNewHttpParams();
        params.put("ApplyTypeID", ApplyTypeID);
        params.put("PageIndex", PageIndex);
        params.put("BrandID", BrandID);
        params.put("BuildingID", MainActivity.BuildingID);
        return params;
    }


    public  static HttpParams getUrlHomeIndexParams(String BuildingID) {
        HttpParams params = getNewHttpParams();
        params.put("BuildingID", BuildingID);
        return params;
    }


    public  static HttpParams getUrlMailDetailParams(String MailID) {
        HttpParams params = getNewHttpParams();
        params.put("MailID", MailID);
        return params;
    }

    public static HttpParams getUrlUserLoginParams(String loginId, String password) {
        HttpParams params = new HttpParams();
        params.put("UserName", loginId);
        params.put("PassWord", password);
        params.put("TerminalType", TerminalType);
        params.put("IPORMachine", GlobalVarible.IMEI);
        return params;
    }

    public static HttpParams getUrlUserMessageLoginParams(String loginId, String password,String MessageCode) {
        HttpParams params = new HttpParams();
        params.put("UserName", loginId);
        params.put("PassWord", password);
        params.put("TerminalType", TerminalType);
        params.put("IPORMachine", GlobalVarible.IMEI);
        params.put("MessageCode", MessageCode);
        return params;
    }






    public static HttpParams getCustomSeedParams(String CustomerID, String LookTime,String Details, String BuildingID) {
        HttpParams params = getNewHttpParams();
        params.put("CustomerID", CustomerID);
        params.put("LookTime", LookTime);
        params.put("Details", Details);
        params.put("BuildingID", BuildingID);
        return params;
    }

    public  static HttpParams getUrlUserChangepasswordParams(String newpass) {
        HttpParams params = getNewHttpParams();
        params.put("OldPwd", GlobalVarible.USER_PASSWORD);
        params.put("NewPwd", newpass);
        return params;
    }

    public  static HttpParams getUrlClientsAddParams(String Sex,String IsLook,String Remark,String BuildingID,String BuildingName,String Mobile,String CustomerName,String CustomerLevel,String DictID) {
        HttpParams params = getNewHttpParams();
        params.put("BuildingName", BuildingName);
        params.put("BuildingID", BuildingID);
        params.put("Mobile", Mobile);
        params.put("Sex", Sex);
        params.put("IsLook", IsLook);
        params.put("CustomerName", CustomerName);
        params.put("CustomerLevel", CustomerLevel);
        params.put("CustomerType", "1");
        params.put("DictID", DictID);
        params.put("Remark", Remark);
        return params;
    }

    public  static HttpParams getUrlClientsSearchParams(String PageIndex,String PropertyConsultantID,String CustomerFlowType,String BuildingID,String Mobile,String CustomerName,String CustomerLevel,String DictID) {
        HttpParams params = getNewHttpParams();
        params.put("PropertyConsultantID", PropertyConsultantID);
        params.put("CustomerFlowType", CustomerFlowType);
        params.put("BuildingID", BuildingID);
        params.put("PageIndex", PageIndex);
        params.put("Mobile", Mobile);
        params.put("CustomerName", CustomerName);
        params.put("CustomerLevel", CustomerLevel);
        params.put("DictID", DictID);
        return params;
    }

    public  static HttpParams getUrlClientsListParams(String PageIndex,String CustomerValid,String BuildingID) {
        HttpParams params = getNewHttpParams();
        params.put("PageIndex", PageIndex);
        params.put("CustomerValid", CustomerValid);
        params.put("BuildingID", BuildingID);
        return params;
    }

    public  static HttpParams getUrlCustomDefineParams(String PageIndex) {
        HttpParams params = getNewHttpParams();
        params.put("PageIndex", PageIndex);
        params.put("BuildingID", MainActivity.BuildingID);
        return params;
    }

    public  static HttpParams getUrlClientsDetailParams(String CustomerID) {
        HttpParams params = getNewHttpParams();
        params.put("CustomerID", CustomerID);
        return params;
    }

    public  static HttpParams getUrlFollowListParams(String PageIndex,String CustomerID) {
        HttpParams params = getNewHttpParams();
        params.put("CustomerID", CustomerID);
        params.put("PageIndex", PageIndex);
        params.put("BuildingID", MainActivity.BuildingID);
        return params;
    }

    public  static HttpParams getUrlErWeiMaParams(String CustomerID,String BuildingID,String AgentCode) {
        HttpParams params = getNewHttpParams();
        params.put("CustomerID", CustomerID);
        params.put("BuildingID", BuildingID);
        params.put("AgentCode", AgentCode);
        return params;
    }

    public  static HttpParams getUrlSalesListParams(String buildingid) {
        HttpParams params = getNewHttpParams();
        params.put("buildingid", buildingid);
        return params;
    }

    public  static HttpParams getUrlNoticesListParams(String NewsType, String PageIndex) {
        HttpParams params = getNewHttpParams();
        params.put("NewsType", NewsType);
        params.put("PageIndex", PageIndex);
        return params;
    }

    public  static HttpParams getUrlNoticesDetailParams(String noticeid) {
        HttpParams params = getNewHttpParams();
        params.put("NewsID", noticeid);
        return params;
    }

    /**
     * 0 全部 1本周
     */
    public  static HttpParams getUrlMessageListParams(String MailTimeSpan) {
        HttpParams params = getNewHttpParams();
        params.put("MailTimeSpan", MailTimeSpan);
        return params;
    }

    public  static HttpParams getUrlContactsListParams() {
        HttpParams params = getNewHttpParams();
        params.put("BuildingID", MainActivity.BuildingID);
        return params;
    }


    public static synchronized HttpParams getNewHttpParams() {
        HttpParams params = new HttpParams();
        params.put("UserID", GlobalVarible.USER_ID);
        params.put("RoleID", GlobalVarible.ROLE_ID);
        return params;
    }
}
