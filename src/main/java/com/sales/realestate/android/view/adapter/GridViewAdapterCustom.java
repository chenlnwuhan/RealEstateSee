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
import com.sales.realestate.android.bean.PropertyConsultant;
import com.sales.realestate.android.ifs.GroupViewIFS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenlin on 2015/7/20.
 */
public class GridViewAdapterCustom extends BaseAdapter {

    /**
     * adapter 类型
     * 0 默认全部显示
     * 1 没有客户信息中的checkbox
     * 2 没有置业顾问一栏
     */
    public int adapterType;
    private int lastPosition = -1;
    private List<Boolean> mImage_bs = new ArrayList<Boolean>();
    public GroupViewIFS mGroupViewIFS;
    public ArrayList<PropertyConsultant> mPropertyConsultant = new ArrayList<PropertyConsultant>();
    public void setAllClear(){
        for(int i = 0 ;i<mImage_bs.size();i++){
            mImage_bs.set(i, false);
        }
    }
    public GridViewAdapterCustom(ArrayList<PropertyConsultant> mPropertyConsultant) {
        this.mPropertyConsultant = mPropertyConsultant;
        for (int i = 0; i < mPropertyConsultant.size(); i++)
            mImage_bs.add(false);
    }

    @Override
    public int getCount() {
        return mPropertyConsultant.size();
    }

    @Override
    public Object getItem(int index) {
        return mPropertyConsultant.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public View getView(final int index, View view, ViewGroup arg2) {
        Holder holder;
        if (view == null || view.getTag() == null) {
            LayoutInflater inflater = LayoutInflater.from(AppContext.getCurrentActivity());
            view = inflater.inflate(R.layout.view_item_custom_distribution, null);
            ListView.LayoutParams mLayoutParams = new ListView.LayoutParams((int) AppContext.getCurrentActivity().getResources().getDimension(R.dimen.gridview_custom_item_width), (int) AppContext.getCurrentActivity().getResources().getDimension(R.dimen.gridview_custom_item_heigth));
            view.setLayoutParams(mLayoutParams);
            holder = new Holder();

            holder.textview_gridview_name = (TextView) view.findViewById(R.id.textview_gridview_name);
            holder.textview_gridview_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean selected = mImage_bs.get(index);
                    for(int i = 0 ;i<mImage_bs.size();i++){
                        mImage_bs.set(i, false);
                    }
                    mImage_bs.set(index, !selected);
                    if(mGroupViewIFS!=null&&(!selected)){
                        mGroupViewIFS.itemOnCheck(index);
                    }
                    if(mGroupViewIFS!=null&&(selected)){
                        mGroupViewIFS.itemUnCheck(index);
                    }
                    notifyDataSetChanged();
                }
            });
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        int resId = mImage_bs.get(index) ? R.color.gridview_item_custom_check : R.color.gridview_item_custom_uncheck;
        holder.textview_gridview_name.setBackgroundColor(AppContext.getCurrentActivity().getResources().getColor(resId));
        holder.textview_gridview_name.setText(mPropertyConsultant.get(index).UserName);
        return view;
    }

    public class Holder {
        public TextView textview_gridview_name;

    }
}
