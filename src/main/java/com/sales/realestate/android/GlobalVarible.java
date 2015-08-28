package com.sales.realestate.android;

import com.sales.realestate.android.bean.AddressInfo;
import com.sales.realestate.android.bean.BuildingInfo;
import com.sales.realestate.android.bean.BuildingUnitInfo;
import com.sales.realestate.android.bean.CustomInfo;
import com.sales.realestate.android.bean.FollowDetailInfo;
import com.sales.realestate.android.bean.HouseInfo;
import com.sales.realestate.android.bean.MailInfo;
import com.sales.realestate.android.bean.NewsInfo;
import com.sales.realestate.android.bean.PropertyConsultant;
import com.sales.realestate.android.bean.SpinnerItemInfo;
import com.sales.realestate.android.bean.ToDoListInfo;
import com.sales.realestate.android.bean.VesrionJson;

import org.kymjs.kjframe.utils.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenlin on 2015/7/8.
 */
public class GlobalVarible {
    public static String BAIDULOGCACHE = "";
    public static String IP = "";
    public static int VERSION_ID;
    public static String VERSION_NAME;
    public static String IMEI = "";
    public static int SCRREN_WIDTH;
    public static int SCRRENT_HEIGHT;


    public static String USER_ID = "";
    public static String USER_NAME = "";
    public static String USER_PHONE = "";
    public static String USER_PASSWORD = "";
    public static String USER_TOKEN = "";
    public static String USER_HEAD = "";
    public static String IS_BIND = "";
    public static String LOGIN_ID =  "";

    public static void cleanData(){

    }
    /**
     * 1 销售总监
     * 2 项目经理
     * 3 置业顾问
     */
    public static String ROLE_ID = "";
    public static String ROLE_NAME = "";

    public static boolean IS_LOGIN = false;


    public static int GLOBALDELAY = 1000;

    public static VesrionJson getVSERION() {
        return VSERION;
    }

    public static void setVSERION(VesrionJson VSERION) {
        GlobalVarible.VSERION = VSERION;
    }

    public static VesrionJson VSERION ;
    public static List<BuildingInfo> getBuildList() {
        return BUILD_LIST;
    }

    public static void setBuildList(List<BuildingInfo> buildList) {
        BUILD_LIST = buildList;
    }

    private static List<BuildingInfo> BUILD_LIST = new ArrayList<BuildingInfo>();

    public static ArrayList<MailInfo> getMailList() {
        return MAIL_LIST;
    }

    public static void setMailList(ArrayList<MailInfo> mailList) {
        MAIL_LIST = mailList;
    }

    public static ArrayList<NewsInfo> getNewsList() {
        return NEWS_LIST;
    }

    public static void setNewsList(ArrayList<NewsInfo> newsList) {
        NEWS_LIST = newsList;
    }

    private static ArrayList<NewsInfo> NEWS_LIST = new ArrayList<NewsInfo>();
    private static ArrayList<MailInfo> MAIL_LIST = new ArrayList<MailInfo>();

    public static ArrayList<CustomInfo> getCustomAnalyseList() {
        return CUSTOM_ANALYSE_LIST;
    }

    public static void setCustomAnalyseList(ArrayList<CustomInfo> customAnalyseList) {
        CUSTOM_ANALYSE_LIST = customAnalyseList;
    }

    public static int getPageIndex(ArrayList mObjectList){
        int pageIndex = 1 ;
        if(mObjectList!=null){
            pageIndex = mObjectList.size()/10 + 1 ;
        }
        return pageIndex;
    }
    public static boolean initPageObject(ArrayList mObjectList,ArrayList newObjectList,int pageIndex){
        if(pageIndex<1){
            pageIndex = 1  ;
        }
        try{
            int startIndex = 10 * (pageIndex-1);
            int endIndex = startIndex+9;
            int mIndex = mObjectList.size();

            if(startIndex>=mIndex){
                for(int i=0;i<10;i++){
                    if(i>=newObjectList.size()){
                        break;
                    }
                    mObjectList.add(newObjectList.get(i));
                }
            }
            else if(mIndex<=endIndex){
                int beginIndex = mIndex%10  ;
                for(int i=beginIndex;i<(beginIndex+10);i++){
                    if(i>=newObjectList.size()){
                        break;
                    }
                    mObjectList.add(newObjectList.get(i));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }
    private static ArrayList<CustomInfo> CUSTOM_ANALYSE_LIST = new ArrayList<CustomInfo>();

    public static ArrayList<CustomInfo> getCustomDefineList() {
        for (int i = 0; i < CUSTOM_DEFINE_LIST.size(); i++) {
            if (CUSTOM_DEFINE_LIST.get(i).CustomerValid.equals("有效客户")) {
                CUSTOM_DEFINE_LIST.get(i).isValidate = true;
            } else {
                CUSTOM_DEFINE_LIST.get(i).isValidate = false;
            }
        }
        return CUSTOM_DEFINE_LIST;
    }

    public static void setCustomDefineList(ArrayList<CustomInfo> customDefineList) {
        CUSTOM_DEFINE_LIST = customDefineList;
    }

    private static ArrayList<CustomInfo> CUSTOM_DEFINE_LIST = new ArrayList<CustomInfo>();

    public static ArrayList<PropertyConsultant> getPropertyConsultantList() {
        return PROPERTY_CONSULTANT_LIST;
    }

    public static void setPropertyConsultantList(ArrayList<PropertyConsultant> propertyConsultantList) {
        PROPERTY_CONSULTANT_LIST = propertyConsultantList;
    }

    private static ArrayList<PropertyConsultant> PROPERTY_CONSULTANT_LIST = new ArrayList<PropertyConsultant>();
    private static ArrayList<ToDoListInfo> TODO_INFO_LIST = new ArrayList<ToDoListInfo>();

    public static ArrayList<AddressInfo> getAddressList() {
        return ADDRESS_LIST;
    }

    public static void setAddressList(ArrayList<AddressInfo> addressList) {
        ADDRESS_LIST = addressList;
    }
    public static CustomInfo findCustomByCustomIdInCustomAnalyseList(String id) {
        CustomInfo mCustomInfo = null ;
        for(int i=0;i<CUSTOM_ANALYSE_LIST.size();i++){
            if(CUSTOM_ANALYSE_LIST.get(i).CustomerID.equals(id)){
                mCustomInfo = CUSTOM_ANALYSE_LIST.get(i);
                break;
            }
        }
        return mCustomInfo;
    }
    public static PropertyConsultant findPropertyConsultantByNameInPropertyConsultant(String name) {
        PropertyConsultant mPropertyConsultant = null ;
        for(int i=0;i<PROPERTY_CONSULTANT_LIST.size();i++){
            if(PROPERTY_CONSULTANT_LIST.get(i).UserName.equals(name)){
                mPropertyConsultant = PROPERTY_CONSULTANT_LIST.get(i);
                break;
            }
        }
        return mPropertyConsultant;
    }
    private static ArrayList<AddressInfo> ADDRESS_LIST = new ArrayList<AddressInfo>();
    private static ArrayList<FollowDetailInfo> FOLLOWDETAILLIST = new ArrayList<FollowDetailInfo>();

    private static ArrayList<SpinnerItemInfo> TITLE_MAIL_LIST = new ArrayList<SpinnerItemInfo>();
    private static ArrayList<SpinnerItemInfo> TITLE_STATISTICS_CUSTOM = new ArrayList<SpinnerItemInfo>();
    private static ArrayList<SpinnerItemInfo> TITLE_HOME_LIST = new ArrayList<SpinnerItemInfo>();
    private static ArrayList<SpinnerItemInfo> TITLE_CUSTOM_ANALYSE_LIST = new ArrayList<SpinnerItemInfo>();

    public static ArrayList<SpinnerItemInfo> getTitleCustomList() {
        return TITLE_CUSTOM_LIST;
    }

    public static void setTitleCustomList(ArrayList<SpinnerItemInfo> titleCustomList) {
        TITLE_CUSTOM_LIST = titleCustomList;
    }

    private static ArrayList<SpinnerItemInfo> TITLE_CUSTOM_LIST = new ArrayList<SpinnerItemInfo>();
    private static ArrayList<SpinnerItemInfo> TITLE_TODO_LIST = new ArrayList<SpinnerItemInfo>();

    private static ArrayList<SpinnerItemInfo> SPINNER_PHONE_TYPE = new ArrayList<SpinnerItemInfo>();


    public static ArrayList<SpinnerItemInfo> getSpinnerCustomLevel() {
        return SPINNER_CUSTOM_LEVEL;
    }

    public static void setSpinnerCustomLevel(ArrayList<SpinnerItemInfo> spinnerCustomLevel) {
        SPINNER_CUSTOM_LEVEL = spinnerCustomLevel;
    }

    private static ArrayList<SpinnerItemInfo> SPINNER_CUSTOM_LEVEL = new ArrayList<SpinnerItemInfo>();

    public static ArrayList<SpinnerItemInfo> getSpinnerCustomWay() {
        return SPINNER_CUSTOM_WAY;
    }

    public static void setSpinnerCustomWay(ArrayList<SpinnerItemInfo> spinnerCustomWay) {
        SPINNER_CUSTOM_WAY = spinnerCustomWay;
    }

    public static ArrayList<SpinnerItemInfo> getSpinnerCustomFollowtype() {
        return SPINNER_CUSTOM_FOLLOWTYPE;
    }

    public static void setSpinnerCustomFollowtype(ArrayList<SpinnerItemInfo> spinnerCustomFollowtype) {
        SPINNER_CUSTOM_FOLLOWTYPE = spinnerCustomFollowtype;
    }

    private static ArrayList<SpinnerItemInfo> SPINNER_CUSTOM_FOLLOWTYPE = new ArrayList<SpinnerItemInfo>();
    private static ArrayList<SpinnerItemInfo> SPINNER_CUSTOM_WAY = new ArrayList<SpinnerItemInfo>();
    private static ArrayList<SpinnerItemInfo> SPINNER_CUSTOM_SEX = new ArrayList<SpinnerItemInfo>();
    private static ArrayList<SpinnerItemInfo> SPINNER_CUSTOM_PROPERTY = new ArrayList<SpinnerItemInfo>();

    private static ArrayList<SpinnerItemInfo> SPINNER_BUILDNO_LIST = new ArrayList<SpinnerItemInfo>();

    public static ArrayList<SpinnerItemInfo> getSpinnerMoneyType() {
        return SPINNER_MONEY_TYPE;
    }

    public static void setSpinnerMoneyType(ArrayList<SpinnerItemInfo> spinnerMoneyType) {
        SPINNER_MONEY_TYPE = spinnerMoneyType;
    }

    private static ArrayList<SpinnerItemInfo> SPINNER_MONEY_TYPE = new ArrayList<SpinnerItemInfo>();

    public static ArrayList<SpinnerItemInfo> getSPINNER_BUILDNO_LIST() {
        SPINNER_BUILDNO_LIST.clear();
        for (int i = 0; i < HOUSE_LIST.size(); i++) {
            SpinnerItemInfo mSpinnerItemInfo = new SpinnerItemInfo();
            mSpinnerItemInfo.setId(HOUSE_LIST.get(i).NewsHouseID);
            mSpinnerItemInfo.setName(HOUSE_LIST.get(i).Dong + "-" + HOUSE_LIST.get(i).Dan + "-" + HOUSE_LIST.get(i).RoomName);
            mSpinnerItemInfo.RoomName = HOUSE_LIST.get(i).RoomName;
            mSpinnerItemInfo.setIsCurrent(false);
            SPINNER_BUILDNO_LIST.add(mSpinnerItemInfo);
        }
        return SPINNER_BUILDNO_LIST;
    }

    public static ArrayList<SpinnerItemInfo> getSPINNER_CUSTOM_PROPERTY() {
        SPINNER_CUSTOM_PROPERTY.clear();
        ArrayList<PropertyConsultant> mT = getPropertyConsultantList();
        for (int i = 0; i < mT.size(); i++) {
            SpinnerItemInfo mSpinnerItemInfo = new SpinnerItemInfo();
            mSpinnerItemInfo.setId(mT.get(i).UserID);
            mSpinnerItemInfo.setName(mT.get(i).UserName);
            mSpinnerItemInfo.setIsCurrent(false);
            SPINNER_CUSTOM_PROPERTY.add(mSpinnerItemInfo);
        }

        return SPINNER_CUSTOM_PROPERTY;
    }

    public static ArrayList<SpinnerItemInfo> getSPINNER_CUSTOM_SEX() {
        if (SPINNER_CUSTOM_SEX.size() == 0) {
            SpinnerItemInfo mSpinnerItemInfo = new SpinnerItemInfo();
            mSpinnerItemInfo.setId("0");
            mSpinnerItemInfo.setName("女");
            mSpinnerItemInfo.setIsCurrent(false);
            SPINNER_CUSTOM_SEX.add(mSpinnerItemInfo);

            SpinnerItemInfo mSpinnerItemInfo1 = new SpinnerItemInfo();
            mSpinnerItemInfo1.setId("1");
            mSpinnerItemInfo1.setName("男");
            mSpinnerItemInfo1.setIsCurrent(false);
            SPINNER_CUSTOM_SEX.add(mSpinnerItemInfo1);
        }

        return SPINNER_CUSTOM_SEX;
    }

    public static ArrayList<String> CUSTOMPOPUPTITLE = new ArrayList<String>();

    static {
        CUSTOMPOPUPTITLE.add("123");
    }


    public static ArrayList<AddressInfo> getADDRESS_LIST() {
        ArrayList<AddressInfo> ADDRESS_LISTTMP = new ArrayList<AddressInfo>();

        Collections.sort(ADDRESS_LIST, new AddressInfo.ToSort());

        String a = "";

        for (int i = 0; i < ADDRESS_LIST.size(); i++) {
            if (!ADDRESS_LIST.get(i).a.toLowerCase().equals(a.toLowerCase())) {
                AddressInfo firstAddressInfo = new AddressInfo();
                a = ADDRESS_LIST.get(i).a;
                firstAddressInfo.UserName = a.toUpperCase();
                firstAddressInfo.RoleName = "9999";
                ADDRESS_LISTTMP.add(firstAddressInfo);
            }
            ADDRESS_LISTTMP.add(ADDRESS_LIST.get(i));
        }

        return ADDRESS_LISTTMP;
    }

    public static ArrayList<SpinnerItemInfo> getTITLE_STATISTICS_CUSTOM() {
        if (TITLE_STATISTICS_CUSTOM.size() == 0) {
            SpinnerItemInfo mSpinnerItemInfo = new SpinnerItemInfo();
            mSpinnerItemInfo.setId("1");
            mSpinnerItemInfo.setName("到访量");
            mSpinnerItemInfo.setIsCurrent(false);
            TITLE_STATISTICS_CUSTOM.add(mSpinnerItemInfo);

            SpinnerItemInfo mSpinnerItemInfo1 = new SpinnerItemInfo();
            mSpinnerItemInfo1.setId("3");
            mSpinnerItemInfo1.setName("来电量");
            mSpinnerItemInfo1.setIsCurrent(false);
            TITLE_STATISTICS_CUSTOM.add(mSpinnerItemInfo1);

            SpinnerItemInfo mSpinnerItemInfo2 = new SpinnerItemInfo();
            mSpinnerItemInfo2.setId("2");
            mSpinnerItemInfo2.setName("微客户");
            mSpinnerItemInfo2.setIsCurrent(false);
            TITLE_STATISTICS_CUSTOM.add(mSpinnerItemInfo2);

            SpinnerItemInfo mSpinnerItemInfo3 = new SpinnerItemInfo();
            mSpinnerItemInfo3.setId("0");
            mSpinnerItemInfo3.setName("报备量");
            mSpinnerItemInfo3.setIsCurrent(false);
            TITLE_STATISTICS_CUSTOM.add(mSpinnerItemInfo3);
        }

        return TITLE_STATISTICS_CUSTOM;
    }


    public static ArrayList<SpinnerItemInfo> getTITLE_TODO_LIST() {
        TITLE_TODO_LIST.clear();
        if (TITLE_TODO_LIST.size() == 0) {

            SpinnerItemInfo mSpinnerItemInfo = new SpinnerItemInfo();
            mSpinnerItemInfo.setId("0");
            mSpinnerItemInfo.setName("客户界定");
            mSpinnerItemInfo.setIsCurrent(false);
            if(GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_PROPERTY)){
                TITLE_TODO_LIST.add(mSpinnerItemInfo);
            }
            SpinnerItemInfo mSpinnerItemInfo1 = new SpinnerItemInfo();
            mSpinnerItemInfo1.setId("1");
            mSpinnerItemInfo1.setName("带看申请");
            mSpinnerItemInfo1.setIsCurrent(false);
            if(GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_PROPERTY)){
                TITLE_TODO_LIST.add(mSpinnerItemInfo1);
            }

            SpinnerItemInfo mSpinnerItemInfo2 = new SpinnerItemInfo();
            mSpinnerItemInfo2.setId("2");
            mSpinnerItemInfo2.setName("认筹申请");
            mSpinnerItemInfo2.setIsCurrent(false);
            if(GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_DIRECTOR)||GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_MANAGER)){
                TITLE_TODO_LIST.add(mSpinnerItemInfo2);
            }

            SpinnerItemInfo mSpinnerItemInfo3 = new SpinnerItemInfo();
            mSpinnerItemInfo3.setId("3");
            mSpinnerItemInfo3.setName("认购申请");
            mSpinnerItemInfo3.setIsCurrent(false);
            if(GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_DIRECTOR)){
                TITLE_TODO_LIST.add(mSpinnerItemInfo3);
            }

            SpinnerItemInfo mSpinnerItemInfo4 = new SpinnerItemInfo();
            mSpinnerItemInfo4.setId("4");
            mSpinnerItemInfo4.setName("成交申请");
            mSpinnerItemInfo4.setIsCurrent(false);
            if(GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_DIRECTOR)){
                TITLE_TODO_LIST.add(mSpinnerItemInfo4);
            }

            SpinnerItemInfo mSpinnerItemInfo5 = new SpinnerItemInfo();
            mSpinnerItemInfo5.setId("5");
            mSpinnerItemInfo5.setName("销控认筹");
            mSpinnerItemInfo5.setIsCurrent(false);
            if(GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_DIRECTOR)||GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_MANAGER)){
                TITLE_TODO_LIST.add(mSpinnerItemInfo5);
            }

            SpinnerItemInfo mSpinnerItemInfo6 = new SpinnerItemInfo();
            mSpinnerItemInfo6.setId("6");
            mSpinnerItemInfo6.setName("销控认购");
            mSpinnerItemInfo6.setIsCurrent(false);
            if(GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_DIRECTOR)){
                TITLE_TODO_LIST.add(mSpinnerItemInfo6);
            }

            SpinnerItemInfo mSpinnerItemInfo7 = new SpinnerItemInfo();
            mSpinnerItemInfo7.setId("7");
            mSpinnerItemInfo7.setName("销控成交");
            mSpinnerItemInfo7.setIsCurrent(false);
            if(GlobalVarible.ROLE_ID.equals(CommomKey.ROLE_TYPE_DIRECTOR)){
                TITLE_TODO_LIST.add(mSpinnerItemInfo7);
            }
        }

        return TITLE_TODO_LIST;
    }

    public static ArrayList<MailInfo> getMAIL_LIST() {
        for (int i = 0; i < MAIL_LIST.size(); i++) {
            String tmpStr = MAIL_LIST.get(i).IsRead;
            if (StringUtils.isEmpty(tmpStr) && tmpStr.equals("未读")) {
                MAIL_LIST.get(i).isReadGo = false;
            } else {
                MAIL_LIST.get(i).isReadGo = true;
            }
        }
        return MAIL_LIST;
    }

    public static ArrayList<SpinnerItemInfo> getTITLE_CUSTOM_ANALYSE_LIST() {
        if (TITLE_CUSTOM_ANALYSE_LIST.size() == 0) {
            SpinnerItemInfo mSpinnerItemInfo = new SpinnerItemInfo();
            mSpinnerItemInfo.setId("1");
            mSpinnerItemInfo.setName("有效客户");
            mSpinnerItemInfo.setIsCurrent(false);
            TITLE_CUSTOM_ANALYSE_LIST.add(mSpinnerItemInfo);

            SpinnerItemInfo mSpinnerItemInfo1 = new SpinnerItemInfo();
            mSpinnerItemInfo1.setId("0");
            mSpinnerItemInfo1.setName("无效客户");
            mSpinnerItemInfo1.setIsCurrent(false);
            TITLE_CUSTOM_ANALYSE_LIST.add(mSpinnerItemInfo1);

            SpinnerItemInfo mSpinnerItemInfo2 = new SpinnerItemInfo();
            mSpinnerItemInfo2.setId("2");
            mSpinnerItemInfo2.setName("到访客户");
            mSpinnerItemInfo2.setIsCurrent(false);
            TITLE_CUSTOM_ANALYSE_LIST.add(mSpinnerItemInfo2);

            SpinnerItemInfo mSpinnerItemInfo3 = new SpinnerItemInfo();
            mSpinnerItemInfo3.setId("3");
            mSpinnerItemInfo3.setName("流失客户");
            mSpinnerItemInfo3.setIsCurrent(false);
            TITLE_CUSTOM_ANALYSE_LIST.add(mSpinnerItemInfo3);
        }

        return TITLE_CUSTOM_ANALYSE_LIST;
    }



    public static ArrayList<SpinnerItemInfo> getTITLE_HOME_LIST() {
        TITLE_HOME_LIST.clear();
        if (TITLE_HOME_LIST.size() == 0) {
            for (int i = 0; i < BUILD_LIST.size(); i++) {
                SpinnerItemInfo mSpinnerItemInfo = new SpinnerItemInfo();
                mSpinnerItemInfo.setId(BUILD_LIST.get(i).BuildingID);
                mSpinnerItemInfo.setName(BUILD_LIST.get(i).BuildingName);
                mSpinnerItemInfo.setIsCurrent(false);
                TITLE_HOME_LIST.add(mSpinnerItemInfo);
            }
        }
        return TITLE_HOME_LIST;
    }

    public static ArrayList<SpinnerItemInfo> getTITLE_MAIL_LIST() {
        if (TITLE_MAIL_LIST.size() == 0) {
            SpinnerItemInfo mSpinnerItemInfo1 = new SpinnerItemInfo();
            mSpinnerItemInfo1.setId("0");
            mSpinnerItemInfo1.setName("全部邮件");
            mSpinnerItemInfo1.setIsCurrent(false);
            TITLE_MAIL_LIST.add(mSpinnerItemInfo1);

            SpinnerItemInfo mSpinnerItemInfo = new SpinnerItemInfo();
            mSpinnerItemInfo.setId("1");
            mSpinnerItemInfo.setName("本周邮件");
            mSpinnerItemInfo.setIsCurrent(false);
            TITLE_MAIL_LIST.add(mSpinnerItemInfo);


        }
        return TITLE_MAIL_LIST;
    }

    public static ArrayList<SpinnerItemInfo> initTitleListFocus(ArrayList<SpinnerItemInfo> mTitilList, int oldPosition, int newPosition) {
        if (oldPosition > -1) {
            mTitilList.get(oldPosition).setIsCurrent(false);
            mTitilList.get(newPosition).setIsCurrent(true);
        } else {
            for (int i = 0; i < mTitilList.size(); i++) {
                if (i == newPosition) {
                    mTitilList.get(i).setIsCurrent(true);
                } else {
                    mTitilList.get(i).setIsCurrent(false);
                }
            }
        }
        return mTitilList;
    }

    public static ArrayList<SpinnerItemInfo> initTitleListFocus(ArrayList<SpinnerItemInfo> mTitilList, int oldPosition, String newPosition) {

            for (int i = 0; i < mTitilList.size(); i++) {
                if (mTitilList.get(i).id.equals(newPosition)) {
                    mTitilList.get(i).setIsCurrent(true);
                } else {
                    mTitilList.get(i).setIsCurrent(false);
                }
            }

        return mTitilList;
    }

    public static ArrayList<SpinnerItemInfo> initTitleListFocus(ArrayList<SpinnerItemInfo> mTitilList,String add) {

            for (int i = 0; i < mTitilList.size(); i++) {
                if(mTitilList.get(i).isCurrent){
                    int t = mTitilList.get(i).name.indexOf("(");
                    if(t>0){
                        mTitilList.get(i).name = mTitilList.get(i).name.substring(0,t);
                    }
                       mTitilList.get(i).name += add;
                       break ;

                }
            }
        return mTitilList;
    }

    public static List<HouseInfo> getHouseList() {
        return HOUSE_LIST;
    }

    public static void setHouseList(List<HouseInfo> houseList) {
        HOUSE_LIST = houseList;
    }

    public static List<HouseInfo> HOUSE_LIST = new ArrayList<HouseInfo>();

    public static List<BuildingUnitInfo> getUnitList() {
        return UNIT_LIST;
    }

    public static void setUnitList(List<BuildingUnitInfo> unitList) {
        UNIT_LIST = unitList;
    }

    public static List<BuildingUnitInfo> UNIT_LIST = new ArrayList<BuildingUnitInfo>();



    public static int findSpinnerIndexById(String spinnerId, ArrayList<SpinnerItemInfo> spinnerObjectArrayList1) {
        for (int i = 0; i < spinnerObjectArrayList1.size(); i++) {
            if (spinnerObjectArrayList1.get(i).getId().equals(spinnerId)) {
               return i;
            }
        }
        return 0;
    }
    public static Map<String,List<BuildingUnitInfo>> findUnitList(List<BuildingUnitInfo> list) {
        Map<String,List<BuildingUnitInfo>> result = new HashMap<String, List<BuildingUnitInfo>>();
        if(list == null || list.size() == 0) {
            return result;

        }else {
            String dong = "";
            List<BuildingUnitInfo> dans = null;
            for (int i = 0; i < list.size(); i++) {
                BuildingUnitInfo info = list.get(i);
                if(dong.equals(info.Dong)) {
                    dans.add(info);
                    if(i == list.size() -1 ) {
                        result.put(dong, dans);
                    }
                } else {
                    if(dans != null) {
                        result.put(dong, dans);
                    }
                    dong = info.Dong;
                    dans = new ArrayList<BuildingUnitInfo>();
                    dans.add(info);
                    if(i == list.size() -1 ) {
                        result.put(dong, dans);
                    }
                }
            }
            return result;
        }
    }

    public static Map<BuildingUnitInfo,List<HouseInfo>> getUnitHouseList(List<HouseInfo> list) {
        Map<BuildingUnitInfo,List<HouseInfo>> result = new HashMap<BuildingUnitInfo, List<HouseInfo>>();
        if(list != null && list.size() > 0) {
            BuildingUnitInfo build = new BuildingUnitInfo();
            List<HouseInfo> houses = new ArrayList<HouseInfo>();
            for (int i = 0; i < list.size(); i++) {

                HouseInfo info = list.get(i);
                if(info.Dong.equals(build.Dong) && info.Dan.equals(build.Dan)) {
                    houses.add(info);
                    if(i == list.size() -1) {
                        result.put(build, houses);
                    }
                } else {
                    if(houses != null) {
                        result.put(build, houses);
                    }
                    build = new BuildingUnitInfo();
                    build.Dan = info.Dan;
                    build.Dong = info.Dong;
                    houses = new ArrayList<HouseInfo>();
                    houses.add(info);
                }
            }
        }
        return result;
    }
}
