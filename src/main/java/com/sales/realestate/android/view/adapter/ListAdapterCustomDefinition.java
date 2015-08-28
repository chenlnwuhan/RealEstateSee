package com.sales.realestate.android.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sales.realestate.android.AppContext;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.CustomInfo;
import com.sales.realestate.android.bean.ToDoListInfo;

import java.util.ArrayList;

/**
 * Created by chenlin on 2015/7/20.
 */
public class ListAdapterCustomDefinition extends BaseAdapter {

    public ArrayList<CustomInfo> mTodoList = new ArrayList<CustomInfo>();
    public ListAdapterCustomDefinition(ArrayList<CustomInfo> mTodoList){
        this.mTodoList = mTodoList;
    }
    @Override
    public int getCount() {

        return mTodoList.size();
    }

    @Override
    public Object getItem(int index) {
        return mTodoList.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public View getView(int index, View view, ViewGroup arg2) {
        Holder holder ;
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(AppContext.getCurrentActivity());
            view = inflater.inflate(R.layout.view_item_custom_definition, null);
            ListView.LayoutParams mLayoutParams= new ListView.LayoutParams(ListView.LayoutParams.FILL_PARENT, (int) AppContext.getCurrentActivity().getResources().getDimension(R.dimen.listview_item_mail_height));
            view.setLayoutParams(mLayoutParams);
            holder = new Holder();

            holder.img_listview_isnew = (TextView) view.findViewById(R.id.img_listview_isnew);
            holder.listview_item_info = (TextView) view.findViewById(R.id.listview_item_info);
            holder.listview_item_people = (TextView) view.findViewById(R.id.listview_item_people);
            holder.listview_item_phone = (TextView) view.findViewById(R.id.listview_item_phone);
            holder.listview_item_hasdeal = (TextView) view.findViewById(R.id.listview_item_hasdeal);
            holder.listview_item_date = (TextView) view.findViewById(R.id.listview_item_date);

            view.setTag(holder);
        }
        else {
            holder = (Holder) view.getTag();
        }
        CustomInfo mMailInfo = mTodoList.get(index);
        String deal = "有效客户";
        int resId = R.color.list_item_definition_youxiao;
        if(!mMailInfo.isValidate){
            deal="无效客户";
            resId = R.color.list_item_definition_wuxiao;
        }
        holder.img_listview_isnew.setText(String.valueOf(index+1));
        holder.listview_item_info.setText(mMailInfo.CustomerName);
        holder.listview_item_people.setText("界定人 "+mMailInfo.UserName);
        holder.listview_item_phone.setText(mMailInfo.Mobile);
        holder.listview_item_hasdeal.setText(deal);
        holder.listview_item_hasdeal.setTextColor(AppContext.getCurrentActivity().getResources().getColor(resId));
        holder.listview_item_date.setText(mMailInfo.AddTime);
        return view;
    }

    public class  Holder {
        public TextView img_listview_isnew ;
        public TextView listview_item_info;
        public TextView listview_item_people;
        public TextView listview_item_phone;
        public TextView listview_item_hasdeal;
        public TextView listview_item_date;
    }
}
