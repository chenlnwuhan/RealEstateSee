package com.sales.realestate.android.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.sales.realestate.android.AppContext;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.CustomInfo;
import com.sales.realestate.android.ifs.GroupViewIFS;

import java.util.ArrayList;

/**
 * Created by chenlin on 2015/7/20.
 */
public class ListAdapterCustom extends BaseAdapter {

    /**
     * adapter 类型
     * 0 默认全部显示
     * 1 没有客户信息中的checkbox
     * 2 没有置业顾问一栏
     */
    public int adapterType;
    public GroupViewIFS mGroupViewIFS;
    public ArrayList<CustomInfo> mCustomInfoList = new ArrayList<CustomInfo>();

    public ListAdapterCustom(ArrayList<CustomInfo> customInfoList) {
        this.mCustomInfoList = customInfoList;
    }

    @Override
    public int getCount() {
        return mCustomInfoList.size();
    }

    @Override
    public Object getItem(int index) {
        return mCustomInfoList.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public View getView(final int index, View view, ViewGroup arg2) {
        Holder holder;
        if (true) {
            LayoutInflater inflater = LayoutInflater.from(AppContext.getCurrentActivity());
            if(adapterType==3||adapterType==4){
                view = inflater.inflate(R.layout.view_item_volume, null);
            }else{
                view = inflater.inflate(R.layout.view_item_custom, null);
            }

            ListView.LayoutParams mLayoutParams = new ListView.LayoutParams(ListView.LayoutParams.FILL_PARENT, (int) AppContext.getCurrentActivity().getResources().getDimension(R.dimen.listview_item_mail_height));
            view.setLayoutParams(mLayoutParams);
            holder = new Holder();

            holder.textview_info_custom_name = (TextView) view.findViewById(R.id.textview_info_custom_name);
            holder.textview_info_custom_phone = (TextView) view.findViewById(R.id.textview_info_custom_phone);
            holder.textview_info_custom_property_consultant = (TextView) view.findViewById(R.id.textview_info_custom_property_consultant);
            holder.textview_info_custom_type = (TextView) view.findViewById(R.id.textview_info_custom_type);
            holder.textview_info_custom_last_followup = (TextView) view.findViewById(R.id.textview_info_custom_last_followup);
            holder.imageview_check_custom = (CheckBox) view.findViewById(R.id.checkbox_check_custom);
            holder.imageview_check_custom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (mGroupViewIFS != null) {
                        if (isChecked) {
                            mCustomInfoList.get(index).isClick = true;
                            mGroupViewIFS.itemOnCheck(index);
                        } else {
                            mCustomInfoList.get(index).isClick = false;
                            mGroupViewIFS.itemUnCheck(index);
                        }
                    }
                }
            });
  //          view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        CustomInfo mCustomnfo = mCustomInfoList.get(index);
        if(!mCustomnfo.isClick){
            holder.imageview_check_custom.setChecked(false);
        }else{
            holder.imageview_check_custom.setChecked(true);
        }
        holder.textview_info_custom_name.setText(mCustomnfo.CustomerName);
        holder.textview_info_custom_phone.setText(mCustomnfo.Mobile);
        holder.textview_info_custom_property_consultant.setText(mCustomnfo.UserName);
        holder.textview_info_custom_type.setText(mCustomnfo.Customerlevel);
        holder.textview_info_custom_last_followup.setText(mCustomnfo.FollowTime);
        if(mCustomnfo.CustomerStatus.equals("5")){
            holder.imageview_check_custom.setEnabled(false);
            holder.imageview_check_custom.setVisibility(View.INVISIBLE);
        }
        if(adapterType==1){
            holder.imageview_check_custom.setVisibility(View.GONE);
        }
        if(adapterType==2){
            holder.textview_info_custom_property_consultant.setVisibility(View.GONE);
        }else if(adapterType==3){
            holder.imageview_check_custom.setVisibility(View.GONE);
            holder.textview_info_custom_property_consultant.setVisibility(View.GONE);
            holder.textview_info_custom_type.setText(mCustomnfo.RoomName);
            holder.textview_info_custom_last_followup.setText(mCustomnfo.OkTime);
        }else if(adapterType==4){
            holder.imageview_check_custom.setVisibility(View.GONE);
            holder.textview_info_custom_property_consultant.setVisibility(View.GONE);
            holder.textview_info_custom_type.setText(mCustomnfo.Customerlevel);
        }else if(adapterType==6){
            String[] phoneTmp = mCustomnfo.CustomerName.split(",");
            if(phoneTmp.length>1){
                holder.textview_info_custom_name.setText(phoneTmp[0]);
                holder.textview_info_custom_phone.setText(phoneTmp[1]);
            }
            holder.imageview_check_custom.setVisibility(View.GONE);
            holder.textview_info_custom_last_followup.setText(mCustomnfo.FollowLastTime);
        }else if(adapterType==9){
            holder.textview_info_custom_property_consultant.setVisibility(View.GONE);
        }

        return view;

    }

    public class Holder {
        public CheckBox imageview_check_custom;
        public TextView textview_info_custom_name;
        public TextView textview_info_custom_phone;
        public TextView textview_info_custom_property_consultant;
        public TextView textview_info_custom_type;
        public TextView textview_info_custom_last_followup;
    }
}
