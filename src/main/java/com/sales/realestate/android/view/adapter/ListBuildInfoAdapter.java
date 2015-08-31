package com.sales.realestate.android.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sales.realestate.android.AppContext;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.SpinnerItemInfo;

import java.util.ArrayList;


/**
 * Created by chenlin on 2015/7/12.
 */
public class ListBuildInfoAdapter extends BaseAdapter {



    class Holder {
        TextView textview1 ;
        TextView textview2 ;
    }

    public ArrayList<SpinnerItemInfo> getSpinnerObjectArrayList() {
        return spinnerObjectArrayList;
    }

    public void setSpinnerObjectArrayList(ArrayList<SpinnerItemInfo> spinnerObjectArrayList) {
        this.spinnerObjectArrayList = spinnerObjectArrayList;
    }

    private ArrayList<SpinnerItemInfo>  spinnerObjectArrayList = new ArrayList<SpinnerItemInfo>();

    public ListBuildInfoAdapter(ArrayList<SpinnerItemInfo> pList) {
        this.spinnerObjectArrayList = pList;
    }

    @Override
    public int getCount() {
        return spinnerObjectArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return spinnerObjectArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder = new Holder();
        if(convertView==null){
            LayoutInflater _LayoutInflater= LayoutInflater.from(AppContext.getCurrentActivity());
            convertView=_LayoutInflater.inflate(R.layout.view_item_popup_spinner, null);
            ListView.LayoutParams mLayoutParams= new ListView.LayoutParams(ListView.LayoutParams.FILL_PARENT, (int) AppContext.getCurrentActivity().getResources().getDimension(R.dimen.build_listview_height));
            convertView.setLayoutParams(mLayoutParams);
            holder.textview1 = (TextView) convertView.findViewById(R.id.textview1);
            holder.textview2 = (TextView) convertView.findViewById(R.id.textview2);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }
        holder.textview1.setText(spinnerObjectArrayList.get(position).getId());
        holder.textview1.setVisibility(View.GONE);

        holder.textview2.setText(spinnerObjectArrayList.get(position).getName());
        if(spinnerObjectArrayList.get(position).isCurrent()){
            holder.textview2.setTextColor(AppContext.getCurrentActivity().getResources().getColor(R.color.global_text_red));
            holder.textview2.setTag(1);
        }else{
            holder.textview2.setTextColor(AppContext.getCurrentActivity().getResources().getColor(R.color.global_black));
            holder.textview2.setTag(2);
        }
        return convertView;
    }
}
