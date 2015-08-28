package com.sales.realestate.android.bean;

import org.kymjs.kjframe.utils.Cn2Spell;

import java.util.Comparator;

/**
 * Created by cc on 2015/7/26.
 */
public class AddressInfo {
    public String a ;
    public String UserName;
    public String Mobile;
    public String RoleName;

    public static class ToSort implements Comparator<AddressInfo>{

        @Override
        public int compare(AddressInfo lhs, AddressInfo rhs) {
            String s1= Cn2Spell.converterToFirstSpell(lhs.UserName);
            lhs.a = s1.substring(0,1) ;
            String s2= Cn2Spell.converterToFirstSpell(rhs.UserName);
            rhs.a = s2.substring(0,1) ;
            if(s1.compareTo(s2)>0) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
