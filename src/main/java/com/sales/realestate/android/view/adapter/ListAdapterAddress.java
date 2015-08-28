package com.sales.realestate.android.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sales.realestate.android.AppContext;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.AddressInfo;

import java.util.ArrayList;

/**
 * Created by chenlin on 2015/7/20.
 */
public class ListAdapterAddress extends BaseAdapter {

    public ArrayList<AddressInfo> mMailInfoList = new ArrayList<AddressInfo>();
    public ListAdapterAddress(ArrayList<AddressInfo> mailInfoList){
        this.mMailInfoList = mailInfoList;
    }
    @Override
    public int getCount() {

        return mMailInfoList.size();
    }

    @Override
    public Object getItem(int index) {
        return mMailInfoList.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public View getView(int index, View view, ViewGroup arg2) {
        Holder holder ;


        if(view == null){

            int height  = (int) AppContext.getCurrentActivity().getResources().getDimension(R.dimen.listview_item_mail_height);
            int colorId = AppContext.getCurrentActivity().getResources().getColor(R.color.titile_name_color);
            int firstTextViewVisable    = View.GONE;
            int otherTextViewVisable    = View.VISIBLE;
            AddressInfo mMailInfo = mMailInfoList.get(index);
            if(mMailInfo.RoleName.equals("9999")){
                height = (int) AppContext.getCurrentActivity().getResources().getDimension(R.dimen.listview_item_first_height);
                colorId = AppContext.getCurrentActivity().getResources().getColor(R.color.confirm_backgroud_color);
                firstTextViewVisable    = View.VISIBLE;
                otherTextViewVisable    = View.GONE;
            }


            LayoutInflater inflater = LayoutInflater.from(AppContext.getCurrentActivity());
            view = inflater.inflate(R.layout.view_item_address, null);
            ListView.LayoutParams mLayoutParams= null;
            mLayoutParams = new ListView.LayoutParams(ListView.LayoutParams.FILL_PARENT, height);
            view.setLayoutParams(mLayoutParams);
            view.setBackgroundColor(colorId);
            holder = new Holder();
            holder.listview_item_a = (TextView) view.findViewById(R.id.listview_item_a);
            holder.listview_item_a.setVisibility(firstTextViewVisable);
            holder.listview_item_info = (TextView) view.findViewById(R.id.listview_item_info);
            holder.listview_item_info.setVisibility(otherTextViewVisable);
            holder.listview_item_phone = (TextView) view.findViewById(R.id.listview_item_phone);
            holder.listview_item_phone.setVisibility(otherTextViewVisable);
            holder.listview_item_role = (TextView) view.findViewById(R.id.listview_item_role);
            holder.listview_item_role.setVisibility(otherTextViewVisable);
            holder.line_divider = (View) view.findViewById(R.id.line_divider);
            holder.line_divider.setVisibility(otherTextViewVisable);
            view.setTag(holder);
        }
        else {
            holder = (Holder) view.getTag();
        }
        AddressInfo mMailInfo = mMailInfoList.get(index);

        holder.listview_item_a.setText(mMailInfo.UserName);
        holder.listview_item_info.setText(mMailInfo.UserName);
        holder.listview_item_phone.setText(mMailInfo.Mobile);
        holder.listview_item_role.setText(mMailInfo.RoleName);
        return view;
    }

    public class  Holder {
        public TextView listview_item_a;
        public TextView listview_item_info;
        public TextView listview_item_phone;
        public TextView listview_item_role;
        public  View  line_divider ;

    }
}
