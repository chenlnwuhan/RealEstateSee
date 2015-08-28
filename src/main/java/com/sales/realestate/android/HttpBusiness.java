package com.sales.realestate.android;

import com.google.gson.Gson;
import com.sales.realestate.android.bean.JsonInfo;
import com.sales.realestate.android.demo.LoginAty;
import com.sales.realestate.android.utils.AppInit;

import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpConfig;
import org.kymjs.kjframe.http.HttpParams;
import org.kymjs.kjframe.utils.FileUtils;
import org.kymjs.kjframe.utils.KJLoger;
import org.kymjs.kjframe.utils.StringUtils;

import java.util.Map;

/**
 * Created by chenlin on 2015/8/3.
 */
public class HttpBusiness {
    public final static String RETURN_MESSAGE_OK = "1000";
    public final static String RETURN_MESSAGE_ERROR = "1001";
    public final static String RETURN_MESSAGE_NODATA = "1004";
    public final static String RETURN_MESSAGE_TOKEN = "1006";
    public final static String RETURN_MESSAGE_DOAGAIN = "1007";
    public final static String RETURN_MESSAGE_LASTVERSION = "1009";

    public final static int HTTP_KEY_LOGIN = 110;
    public final static int HTTP_KEY_BUILDING = 111;
    public final static int HTTP_KEY_HOME = 112;
    public final static int HTTP_KEY_PROPERTYCONSULTANT = 133;
    public final static int HTTP_KEY_TABLE_NUMBERRATE = 114;
    public final static int HTTP_KEY_TABLE_NUMBER = 115;
    public final static int HTTP_KEY_TABLE_RATE = 116;
    public final static int HTTP_KEY_TABLE_VOLUME = 117;
    public final static int HTTP_KEY_BASEINFO_FOLLOW = 162;
    public final static int HTTP_KEY_BASEINFO_LEVEL = 118;
    public final static int HTTP_KEY_BASEINFO_SOURCE = 119;
    public final static int HTTP_KEY_APPLYLIST = 120;
    public final static int HTTP_KEY_CHANGEPASSWORD = 121;
    public final static int HTTP_KEY_ERWEIMA = 122;
    public final static int HTTP_KEY_NOTICEDETAIL = 123;
    public final static int HTTP_KEY_NOTICE = 124;
    public final static int HTTP_KEY_SALESLIST = 125;
    public final static int HTTP_KEY_TODOLIST = 126;
    public final static int HTTP_KEY_CUSTOMDEFINE = 127;
    public final static int HTTP_KEY_CUSTOMANALYS = 128;
    public final static int HTTP_KEY_ADDRESS = 131;
    public final static int HTTP_KEY_MAILLIST = 130;
    public final static int HTTP_KEY_MAILLDETAIL = 129;
    public final static int HTTP_KEY_CUSTOMDETAIL = 132;
    public final static int HTTP_KEY_TABLE_CUSTOMLIST = 133;
    public final static int HTTP_KEY_TABLE_CUSTOMSEARCH = 134;
    public final static int HTTP_KEY_TABLE_CUSTOMADD = 134;
    public final static int HTTP_KEY_TABLE_CUSTOMTYPE = 135;
    public final static int HTTP_KEY_TABLE_PAYTYPE = 136;
    public final static int HTTP_KEY_TIME_VOLUME = 137;
    public final static int HTTP_KEY_TIME_CUSTOM = 138;
    public final static int HTTP_KEY_CUSTOM_DISTRIBUTE = 139;
    public final static int HTTP_KEY_CUSTOM_FORM_APPLY = 140;
    public final static int HTTP_KEY_CUSTOM_FORM_CONFIRM = 141;
    public final static int HTTP_KEY_CUSTOM_BUY_APPLY = 142;
    public final static int HTTP_KEY_CUSTOM_BUY_CONFIRM = 143;
    public final static int HTTP_KEY_CUSTOM_DONE_APPLY = 144;
    public final static int HTTP_KEY_CUSTOM_DONE_CONFIRM = 145;

    public final static int HTTP_KEY_TIME_VISIT_CUSTOM = 146;
    public final static int HTTP_KEY_TIME_VISIT_VOLUME = 147;
    public final static int HTTP_KEY_UPDATE_CUSTOM_TYPE = 148;
    public final static int HTTP_KEY_CUSTOM_FOLLOWINFO_LIST = 149;
    public final static int HTTP_KEY_CUSTOM_FOLLOWINFO_DETAIL = 150;
    public final static int HTTP_KEY_CUSTOM_FOLLOWINFO_ADD = 151;

    public final static int HTTP_KEY_SET_HOUSE_STATUS = 152;
    public final static int HTTP_KEY_GET_HOUSE_DETAIL = 153;

    public final static int HTTP_KEY_GET_CUSTOM_SEE = 161;
    public final static int HTTP_KEY_UPDATE_CUSTOM_SEE = 154;
    public final static int HTTP_KEY_GET_CUSTOM_FORM = 155;
    public final static int HTTP_KEY_UPDATE_CUSTOM_FORM = 156;
    public final static int HTTP_KEY_GET_CUSTOM_BUY = 157;
    public final static int HTTP_KEY_UPDATE_CUSTOM_BUY = 158;
    public final static int HTTP_KEY_GET_CUSTOM_DONE = 159;
    public final static int HTTP_KEY_UPDATE_CUSTOM_DONE = 160;
    public final static int HTTP_KEY_BAIDU_CLOUD = 163;
    public final static int HTTP_KEY_DEFINETION_LIST = 164;
    public final static int HTTP_KEY_DEFINETION_DETAIL = 165;
    public final static int HTTP_KEY_DEFINETION_UPDATE = 166;
    public final static int HTTP_KEY_HOME_CLEAR = 167;
    public final static int HTTP_KEY_GET_HOUSE_OTHER_DETAIL = 168;
    public final static int HTTP_KEY_CUSTOM_SEED = 169;
    public final static int HTTP_CHECKVERSION = 170;
    public final static int HTTP_SENDMESSAGE = 171;
    public final static int HTTP_DELMAIL = 172;
    public final static int HTTP_MESSAGELOGIN = 173;


    public static void getMessageLogin(String userLogin, String password, String messageCode,RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_LOGIN, UrlApiConfig.URL_USER_LOGIN, UrlApiConfig.getUrlUserMessageLoginParams(userLogin, password,messageCode), mMyCallBack);
    }
    public static void sendMessage(String loginID, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_SENDMESSAGE, UrlApiConfig.URL_SEND_MESSAGE, UrlApiConfig.setSendMessageParams(loginID), mMyCallBack);
    }

    public static void delMessage(String mailId, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_DELMAIL, UrlApiConfig.URL_DEL_MAIL, UrlApiConfig.getUrlMailDetailParams(mailId), mMyCallBack);
    }
    public static void checkVersion(String APPversion, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_CHECKVERSION, UrlApiConfig.URL_CHECK_VESRION, UrlApiConfig.checkVersion( APPversion), mMyCallBack);
    }

    public static void goCustomSeed(String CustomerID, String LookTime,String Details, String BuildingID, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_CUSTOM_SEED, UrlApiConfig.URL_CUSTOM_TO_SEED, UrlApiConfig.getCustomSeedParams( CustomerID,  LookTime, Details,  BuildingID), mMyCallBack);
    }
    public static void updateCustomDone(String BuildingID, String CustomerID, String ApplyID, String IsLook, String Details, String CommissionValue, String NewsHouseID,String BuildingName,String RoomName,String DealTime,String DealMoney,String CustomerName,RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_UPDATE_CUSTOM_DONE, UrlApiConfig.URL_UPDATE_CUSTOM_DONE, UrlApiConfig.updateCustomDoneParams(BuildingID, CustomerID, ApplyID, IsLook, Details, CommissionValue,NewsHouseID, BuildingName, RoomName, DealTime, DealMoney, CustomerName), mMyCallBack);
    }

    public static void getCustomDone(String BuildingID, String CustomerID, String ApplyID, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_GET_CUSTOM_DONE, UrlApiConfig.URL_GET_CUSTOM_DONE, UrlApiConfig.setCustomSeeParams(BuildingID, CustomerID, ApplyID), mMyCallBack);
    }

    public static void updateCustomBuy(String BuildingID, String CustomerID, String ApplyID, String IsLook, String Details,String NewsHouseID,String CustomerName,String IsSetTime,String IsSetMoney,String RoomName,String BuildingName, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_UPDATE_CUSTOM_BUY, UrlApiConfig.URL_UPDATE_CUSTOM_BUY, UrlApiConfig.updateCustomBuyParams(BuildingID, CustomerID, ApplyID, IsLook, Details,NewsHouseID, CustomerName, IsSetTime, IsSetMoney, RoomName, BuildingName), mMyCallBack);
    }

    public static void getCustomBuy(String BuildingID, String CustomerID, String ApplyID, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_GET_CUSTOM_BUY, UrlApiConfig.URL_GET_CUSTOM_BUY, UrlApiConfig.setCustomSeeParams(BuildingID, CustomerID, ApplyID), mMyCallBack);
    }

    public static void updateCustomForm(String BuildingID, String CustomerID, String ApplyID, String IsLook, String Details,String LookTime,String BuildingName,String setTime,String CustomerName, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_UPDATE_CUSTOM_FORM, UrlApiConfig.URL_UPDATE_CUSTOM_FROM, UrlApiConfig.updateCustomFromParams(BuildingID, CustomerID, ApplyID, IsLook, Details,LookTime, BuildingName, setTime, CustomerName), mMyCallBack);
    }

    public static void getCustomForm(String BuildingID, String CustomerID, String ApplyID, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_GET_CUSTOM_FORM, UrlApiConfig.URL_GET_CUSTOM_FROM, UrlApiConfig.setCustomSeeParams(BuildingID, CustomerID, ApplyID), mMyCallBack);
    }

    public static void getBaiDuCloud(String ChannelId, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_BAIDU_CLOUD, UrlApiConfig.URL_BAIDU_CLOUD, UrlApiConfig.getBaiduParams(ChannelId), mMyCallBack);
    }

    public static void updateCustomSee(String BuildingID, String CustomerID, String ApplyID, String IsLook, String Details,String LookTime, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_UPDATE_CUSTOM_SEE, UrlApiConfig.URL_UPDATE_CUSTOM_SEE, UrlApiConfig.updateCustomSeeParams(BuildingID, CustomerID, ApplyID, IsLook, Details, LookTime), mMyCallBack);
    }

    public static void getCustomSee(String BuildingID, String CustomerID, String ApplyID, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_GET_CUSTOM_SEE, UrlApiConfig.URL_GET_CUSTOM_SEE, UrlApiConfig.setCustomSeeParams(BuildingID, CustomerID, ApplyID), mMyCallBack);
    }

    public static void getHouseDetails(String NewsHouseID, String IsSell, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_GET_HOUSE_DETAIL, UrlApiConfig.URL_GET_HOUSE_DETAIL, UrlApiConfig.getUrlHouseDetailParams(NewsHouseID, IsSell), mMyCallBack);
    }

    public static void getHouseOthersDetails(String NewsHouseID, String IsSell, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_GET_HOUSE_OTHER_DETAIL, UrlApiConfig.URL_HOUSE_DETAIL_OTHERS, UrlApiConfig.getUrlHouseDetailParams(NewsHouseID, IsSell), mMyCallBack);
    }

    public static void setHouseStatus(String NewsHouseID, String IsSell, String PayName, String Remark, String OkMoney, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_SET_HOUSE_STATUS, UrlApiConfig.URL_SET_HOUSE_STATUS, UrlApiConfig.setUrlHouseStatusParams(NewsHouseID, IsSell, PayName, Remark, OkMoney), mMyCallBack);
    }


    public static void getUpdateCustomType(String CustomerID, String Leve, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_UPDATE_CUSTOM_TYPE, UrlApiConfig.URL_UPDATE_CUSETOMLEVEL, UrlApiConfig.getUpdateCustomLevelParams(CustomerID, Leve), mMyCallBack);
    }

    public static void getCusomFollowList(String PageIndex, String CustomerID, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_CUSTOM_FOLLOWINFO_LIST, UrlApiConfig.URL_CUSETOM_FOLLOWLIST, UrlApiConfig.getUrlFollowListParams(PageIndex, CustomerID), mMyCallBack);
    }

    public static void getCusomFollowDetail(String CustomerID, String ID, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_CUSTOM_FOLLOWINFO_DETAIL, UrlApiConfig.URL_CUSETOM_FOLLOWDETAIL, UrlApiConfig.getCustomFollowTypeDetailParams(CustomerID, ID), mMyCallBack);
    }

    public static void getAddCusomFollowDetail(String CustomerID, String From, String Details, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_CUSTOM_FOLLOWINFO_ADD, UrlApiConfig.URL_CUSETOM_FOLLOWADD, UrlApiConfig.getAddCustomFollowInfoParams(CustomerID, From, Details), mMyCallBack);
    }

    public static void getVisitVolume(String BeginTime, String EndTime, RequestCallBack mMyCallBack, String DictID) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_TIME_VISIT_VOLUME, UrlApiConfig.URL_TIME_VISIT_VOLUME, UrlApiConfig.getUrlTableVolumeParams(BeginTime, EndTime, DictID), mMyCallBack);
    }

    public static void getVolumeVisitCustom(String pageIndex, String BeginTime, String EndTime, RequestCallBack mMyCallBack, String DictID) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_TIME_VISIT_CUSTOM, UrlApiConfig.URL_VOLUME_VISIT_CUSTOM, UrlApiConfig.getUrlTableOrderParams(pageIndex, BeginTime, EndTime, DictID), mMyCallBack);
    }


    public static void getCustomDoneConfirm(String buildingid, String Remark, String OkTime, String customerID, String RoomID, String RoomName, String OkMoney, String PayType, String CommissionValue,String SetMoney, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_CUSTOM_DONE_CONFIRM, UrlApiConfig.URL_DONE_CONFIRM, UrlApiConfig.getUrlCustomDoneParams(buildingid, Remark, OkTime, customerID, RoomID, RoomName, OkMoney, PayType, CommissionValue,SetMoney), mMyCallBack);
    }

    public static void getCustomDoneApply(String buildingid, String Remark, String OkTime, String customerID, String RoomID, String RoomName, String OkMoney, String PayType, String CommissionValue,String SetMoney, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_CUSTOM_DONE_APPLY, UrlApiConfig.URL_DONE_APPLY, UrlApiConfig.getUrlCustomDoneParams(buildingid, Remark, OkTime, customerID, RoomID, RoomName, OkMoney, PayType, CommissionValue,SetMoney), mMyCallBack);
    }

    public static void getCustomBuyConfirm(String buildingid, String Remark, String setTime, String customerID, String roomID, String roomName,String SetMoney, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_CUSTOM_BUY_CONFIRM, UrlApiConfig.URL_BUY_CONFIRM, UrlApiConfig.getUrlCustomBuyParams(buildingid, Remark, setTime, customerID, roomID, roomName,SetMoney), mMyCallBack);
    }

    public static void getCustomBuyApply(String buildingid, String Remark, String setTime, String customerID, String roomID, String roomName, String SetMoney, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_CUSTOM_BUY_APPLY, UrlApiConfig.URL_BUY_APPLY, UrlApiConfig.getUrlCustomBuyParams(buildingid, Remark, setTime, customerID, roomID, roomName,SetMoney), mMyCallBack);
    }

    public static void getCustomFormConfirm(String buildingid, String details, String renChouTime, String CustomerID, String renChouMoney, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_CUSTOM_FORM_CONFIRM, UrlApiConfig.URL_FORM_CONFIRM, UrlApiConfig.getUrlCustomFormConfirmParams(buildingid, details, renChouTime, CustomerID, renChouMoney), mMyCallBack);
    }

    public static void getCustomFormApply(String buildingid, String details, String renChouTime, String CustomerID, String renChouMoney, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_CUSTOM_FORM_APPLY, UrlApiConfig.URL_FORM_APPLY, UrlApiConfig.getUrlCustomFormApplyParams(buildingid, details, renChouTime, CustomerID, renChouMoney), mMyCallBack);
    }

    public static void getCustomDistribute(String UserID, String BuildingID, String CustomerIDList, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_CUSTOM_DISTRIBUTE, UrlApiConfig.URL_CUSTOM_DISTRIBUTE, UrlApiConfig.getUrlCustomDistributeParams(UserID, BuildingID, CustomerIDList), mMyCallBack);
    }

    public static void getVolumeCustom(String pageIndex, String BeginTime, String EndTime, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_TIME_CUSTOM, UrlApiConfig.URL_VOLUME_CUSTOM, UrlApiConfig.getUrlTableOrderParams(pageIndex, BeginTime, EndTime), mMyCallBack);
    }

    public static void getHomeVolume(String BeginTime, String EndTime, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_TIME_VOLUME, UrlApiConfig.URL_TIME_VOLUME, UrlApiConfig.getUrlTableVolumeParams(BeginTime, EndTime), mMyCallBack);
    }

    public static void getPropertyConsultant(String buildid, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_PROPERTYCONSULTANT, UrlApiConfig.URL_PROPERTYCONSULTANT, UrlApiConfig.getUrlPropertyConsultantParams(buildid), mMyCallBack);
    }

    public static void getPayType(RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.getHttp(HTTP_KEY_TABLE_PAYTYPE, UrlApiConfig.URL_PAY_TYPE, UrlApiConfig.getNewHttpParams(), mMyCallBack);
    }

    public static void getCustomType(String buildID,RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.getHttp(HTTP_KEY_TABLE_CUSTOMTYPE, UrlApiConfig.URL_CLIENTS_TYPE, UrlApiConfig.getUrlPropertyConsultantParams(buildID), mMyCallBack);
    }

    public static void getCustomAdd(String Sex, String IsLook, String Remark, String BuildingName, String BuildingID, String Mobile, String CustomerName, String CustomerLevel, String DictID, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_TABLE_CUSTOMADD, UrlApiConfig.URL_CLIENTS_ADD, UrlApiConfig.getUrlClientsAddParams(Sex, IsLook, Remark, BuildingName, BuildingID, Mobile, CustomerName, CustomerLevel, DictID), mMyCallBack);
    }

    public static void getTableNumber(String pageIndex, String BeginTime, String EndTime, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_TABLE_NUMBER, UrlApiConfig.URL_USER_TABLENUMBER, UrlApiConfig.getUrlTableOrderParams(pageIndex, BeginTime, EndTime), mMyCallBack);
    }

    public static void getCustomSearch(String PageIndex, String PropertyConsultantID, String CustomerFlowType, String BuildingID, String Mobile, String CustomerName, String CustomerLevel, String DictID, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_TABLE_CUSTOMSEARCH, UrlApiConfig.URL_CLIENTS_SERACH, UrlApiConfig.getUrlClientsSearchParams(PageIndex, PropertyConsultantID, CustomerFlowType, BuildingID, Mobile, CustomerName, CustomerLevel, DictID), mMyCallBack);
    }

    public static void getTableNumberRate(String pageIndex, String BeginTime, String EndTime, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_TABLE_NUMBERRATE, UrlApiConfig.URL_USER_TABLENUMBERRATE, UrlApiConfig.getUrlTableOrderParams(pageIndex, BeginTime, EndTime), mMyCallBack);
    }

    public static void getCustomList(String indexpage, String CustomerValid, String BuildingID, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_TABLE_CUSTOMLIST, UrlApiConfig.URL_CLIENTS_LIST, UrlApiConfig.getUrlClientsListParams(indexpage, CustomerValid, BuildingID), mMyCallBack);
    }

    public static void getTableRate(String BeginTime, String EndTime, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_TABLE_RATE, UrlApiConfig.URL_USER_TABLERATE, UrlApiConfig.getUrlTableVolumeParams(BeginTime, EndTime), mMyCallBack);
    }

    public static void getTableVolume(String BeginTime, String EndTime, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_TABLE_VOLUME, UrlApiConfig.URL_USER_TABLEVOLUME, UrlApiConfig.getUrlTableVolumeParams(BeginTime, EndTime), mMyCallBack);
    }

    public static void getCustomLevel(String baseType, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_BASEINFO_LEVEL, UrlApiConfig.URL_USER_BASEINFO, UrlApiConfig.getUrlBaseTypeParams(baseType), mMyCallBack);
    }

    public static void getCustomSource(String baseType, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_BASEINFO_SOURCE, UrlApiConfig.URL_USER_BASEINFO, UrlApiConfig.getUrlBaseTypeParams(baseType), mMyCallBack);
    }

    public static void getCustomFollowType(String baseType, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_BASEINFO_FOLLOW, UrlApiConfig.URL_USER_BASEINFO, UrlApiConfig.getUrlBaseTypeParams(baseType), mMyCallBack);
    }

    public static void getApplyHttp(String PageIndex, String ApplyTypeID, String BrandID, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_APPLYLIST, UrlApiConfig.URL_USER_APPLYLIST, UrlApiConfig.getUrlApplyListParams(PageIndex, ApplyTypeID, BrandID), mMyCallBack);
    }

    public static void getHomeClearHttp(String NewsHouseID, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_HOME_CLEAR, UrlApiConfig.URL_HOME_CLEAR, UrlApiConfig.getHomeClearParams(NewsHouseID), mMyCallBack);
    }

    public static void getSalesListHttp(String buildId, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_SALESLIST, UrlApiConfig.URL_SALES_LIST, UrlApiConfig.getUrlSalesListParams(buildId), mMyCallBack);
    }

    public static void getErMaHttp(String CustomerID, String BuildingID, String AgentCode, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_ERWEIMA, UrlApiConfig.URL_USER_ERWEIMA, UrlApiConfig.getUrlErWeiMaParams(CustomerID, BuildingID, AgentCode), mMyCallBack);
    }

    public static void getChangePasswordHTTP(String newPassword, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_CHANGEPASSWORD, UrlApiConfig.URL_USER_CHANGEPASSWORD, UrlApiConfig.getUrlUserChangepasswordParams(newPassword), mMyCallBack);
    }


    public static void getNoticesListHTTP(String NewsID, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_NOTICEDETAIL, UrlApiConfig.URL_NOTICES_DETAIL, UrlApiConfig.getUrlNoticesDetailParams(NewsID), mMyCallBack);
    }

    public static void getNoticesListHTTP(String newsType, String index, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_NOTICE, UrlApiConfig.URL_NOTICES_LIST, UrlApiConfig.getUrlNoticesListParams(newsType, index), mMyCallBack);
    }

    public static void showHttpWaitProgress() {
        AppContext.getCurrentActivity().showProgress();
    }

    public static void getToDoListHTTP(RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_TODOLIST, UrlApiConfig.URL_TODOLIST, UrlApiConfig.getUrlContactsListParams(), mMyCallBack);
    }

    public static void getBuildingHTTP(RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.getHttp(HTTP_KEY_BUILDING, UrlApiConfig.URL_BUILDING, UrlApiConfig.getNewHttpParams(), mMyCallBack);
    }

    public static void getCustomDefineHTTP(String PageIndex, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_CUSTOMDEFINE, UrlApiConfig.URL_CUSTOM_DEFINE, UrlApiConfig.getUrlCustomDefineParams(PageIndex), mMyCallBack);
    }

    public static void getCustomDefineListHTTP(String PageIndex, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_DEFINETION_LIST, UrlApiConfig.URL_DEFINITION_LIST, UrlApiConfig.getUrlCustomDefineParams(PageIndex), mMyCallBack);
    }

    public static void getCustomDefineListHTTP(String BuildingID, String CustomerID, String ApplyID, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_DEFINETION_DETAIL, UrlApiConfig.URL_DEFINITION_DETAIL, UrlApiConfig.setCustomSeeParams(BuildingID, CustomerID, ApplyID), mMyCallBack);
    }

    public static void updateCustomDefinitionParamsHTTP(String BuildingID, String CustomerID, String ApplyID, String CustomerValid, String Details,String DefineTime, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_DEFINETION_UPDATE, UrlApiConfig.URL_DEFINITION_UPDATE, UrlApiConfig.updateCustomDefinitionParams(BuildingID, CustomerID, ApplyID, CustomerValid, Details,DefineTime), mMyCallBack);
    }


    public static void getHomeHttp(String buildId, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_HOME, UrlApiConfig.URL_HOME_INDEX, UrlApiConfig.getUrlHomeIndexParams(buildId), mMyCallBack);
    }

    public static void getCustomDetail(String customId, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_CUSTOMDETAIL, UrlApiConfig.URL_CLIENTS_DETAIL, UrlApiConfig.getUrlClientsDetailParams(customId), mMyCallBack);
    }

    public static void getLogin(String userLogin, String password, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_LOGIN, UrlApiConfig.URL_USER_LOGIN, UrlApiConfig.getUrlUserLoginParams(userLogin, password), mMyCallBack);
    }

    public static void getAddress(RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_ADDRESS, UrlApiConfig.URL_CONTACTS_LIST, UrlApiConfig.getUrlContactsListParams(), mMyCallBack);
    }

    public static void getMails(String mailType, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_MAILLIST, UrlApiConfig.URL_MESSAGES_LIST, UrlApiConfig.getUrlMessageListParams(mailType), mMyCallBack);
    }

    public static void getMailDetail(String mailId, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_MAILLDETAIL, UrlApiConfig.URL_MAIL_DETAIL, UrlApiConfig.getUrlMailDetailParams(mailId), mMyCallBack);
    }

    public static void getCustomAnalyse(String BuildingId, RequestCallBack mMyCallBack) {
        showHttpWaitProgress();
        HttpBusiness.postHttp(HTTP_KEY_CUSTOMANALYS, UrlApiConfig.URL_CUSTOM_ANALYSE, UrlApiConfig.getUrlCustomAnalyseParams(BuildingId), mMyCallBack);
    }

    public static interface RequestCallBack {
        public void onFailure(int uid, int errorNo, String strMsg);

        public void onSuccess(int uid, String returnStr);
    }

    public static class MyCallBack implements RequestCallBack {

        public String errorMessage = "";
        public String errorNo = "";
        public boolean isError = false;

        @Override
        public void onFailure(int uid, int errorNo, String strMsg) {
            if(errorNo==-1){
                AppContext.getCurrentActivity().toast("网络连接不正确，请检查网络连接！");
            }else if(errorNo==408){
                AppContext.getCurrentActivity().toast("网络连接超时，请重试！");
            }else{
                errorMessage = "请求错误!错误码:" + errorNo + "!错误信息:" + strMsg;
                AppContext.getCurrentActivity().toast(errorMessage);
            }
            AppContext.getCurrentActivity().cancelProgress();

        }

        @Override
        public void onSuccess(int uid, String returnStr) {
            try {
                KJLoger.debug(System.currentTimeMillis() + "onSuccess");
                AppContext.getCurrentActivity().cancelProgress();
                Gson gson = new Gson();
                errorMessage = "";
                errorNo ="";
                isError = false;
                if (uid != HTTP_KEY_TABLE_PAYTYPE&&uid != HTTP_KEY_GET_HOUSE_OTHER_DETAIL) {
                    JsonInfo mJsonInfo = gson.fromJson(returnStr, JsonInfo.class);
                    if (RETURN_MESSAGE_TOKEN.equals(mJsonInfo.returnCode)) {
                        errorNo = RETURN_MESSAGE_TOKEN;
                        isError = true;
                        errorMessage = mJsonInfo.Msg;
                        AppContext.getCurrentActivity().toast("服务器令牌失效,请重新登录");
                        AppInit.loginOutUserInfo();
                        AppContext.getCurrentActivity().skipActivity(AppContext.getCurrentActivity(), LoginAty.class);
                        return;
                    }
                    if (StringUtils.isEmpty(mJsonInfo.MessageCode)) {
                        isError = true;
                        errorMessage = mJsonInfo.Message;
                        if (StringUtils.isEmpty(errorMessage)) {
                            errorMessage = mJsonInfo.Msg;
                            if (!StringUtils.isEmpty(mJsonInfo.returnCode)) {
                                HttpConfig.sCookie = ";_token=" + GlobalVarible.USER_TOKEN + ";_userid=" + GlobalVarible.USER_ID;
                            }
                        }
                        AppContext.getCurrentActivity().toast(errorMessage);
                    } else if (RETURN_MESSAGE_NODATA.equals(mJsonInfo.MessageCode)) {
                        errorMessage = mJsonInfo.MessageName;
                    }else if (RETURN_MESSAGE_LASTVERSION.equals(mJsonInfo.MessageCode)) {
                        errorMessage = mJsonInfo.MessageName;
                    } else if (RETURN_MESSAGE_DOAGAIN.equals(mJsonInfo.MessageCode)) {
                        isError = true;
                        errorMessage = mJsonInfo.MessageName;
                        AppContext.getCurrentActivity().toast("申请已经通过，请不要重复操作！");
                    }else if ("2001".equals(mJsonInfo.MessageCode)) {
                        isError = true;
                        errorNo = mJsonInfo.MessageCode;
                        errorMessage = mJsonInfo.MessageName;
                    }  else if (!RETURN_MESSAGE_OK.equals(mJsonInfo.MessageCode)) {
                        isError = true;
                        errorMessage = mJsonInfo.MessageName;
                        AppContext.getCurrentActivity().toast(errorMessage);
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
                AppContext.getCurrentActivity().toast("服务器返回信息解析出错，请重试！");
            }

        }
    }

    private static KJHttp getKJHttp() {
        return new KJHttp();
    }

    public static void getHttp(int uid, String url, HttpParams params, RequestCallBack mMyCallBack) {
        KJLoger.debug(url + params.getUrlParams() + "|" + HttpConfig.sCookie);
        goHttp(uid, url, params, 2, mMyCallBack);
    }

    public static void postHttp(int uid, String url, HttpParams params, RequestCallBack mMyCallBack) {
        KJLoger.debug(url + params.getUrlParams() + "|" + HttpConfig.sCookie);
        goHttp(uid, url, params, 1, mMyCallBack);
    }

    public static void goHttp(final int uid, String url, HttpParams params, int httpType, final RequestCallBack mMyCallBack) {
        KJHttp tmpKJHttp = getKJHttp();
        if (httpType == 1) {
            tmpKJHttp.post(url.trim(), params, false, new HttpCallBack() {
                @Override
                public void onFailure(int errorNo, String strMsg) {
                    super.onFailure(errorNo, strMsg);
                    if (mMyCallBack != null) {
                        mMyCallBack.onFailure(uid, errorNo, strMsg);
                    }
                }

                @Override
                public void onSuccess(Map<String, String> headers, byte[] t) {
                    super.onSuccess(headers, t);
                    KJLoger.debug(new String(t));
                    String returnS = new String(t);
                    if (mMyCallBack != null) {
                        mMyCallBack.onSuccess(uid, returnS);
                    }
                }
            });
        } else {
            tmpKJHttp.get(url.trim(), params, false, new HttpCallBack() {
                @Override
                public void onFailure(int errorNo, String strMsg) {
                    super.onFailure(errorNo, strMsg);
                    if (mMyCallBack != null) {
                        mMyCallBack.onFailure(uid, errorNo, strMsg);
                    }
                }

                @Override
                public void onSuccess(Map<String, String> headers, byte[] t) {
                    super.onSuccess(headers, t);
                    KJLoger.debug(new String(t));
                    String returnS = new String(t);
                    if (mMyCallBack != null) {
                        mMyCallBack.onSuccess(uid, returnS);
                    }
                }
            });
        }

    }

}
