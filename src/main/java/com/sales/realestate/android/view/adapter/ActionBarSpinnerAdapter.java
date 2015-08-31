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
public class ActionBarSpinnerAdapter extends BaseAdapter {



    class Holder {
        TextView nameTextView ;
        TextView idTextView ;
    }
    private ArrayList<SpinnerItemInfo>  spinnerObjectArrayList = new ArrayList<SpinnerItemInfo>();

    public ActionBarSpinnerAdapter( ArrayList<SpinnerItemInfo> pList) {
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
            convertView=_LayoutInflater.inflate(R.layout.view_item_title, null);
            ListView.LayoutParams mLayoutParams= new ListView.LayoutParams(ListView.LayoutParams.FILL_PARENT, (int) AppContext.getCurrentActivity().getResources().getDimension(R.dimen.listview_item_mail_height));
            convertView.setLayoutParams(mLayoutParams);
            holder.idTextView = (TextView) convertView.findViewById(R.id.building1);
            holder.nameTextView = (TextView) convertView.findViewById(R.id.building2);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }
        holder.idTextView.setText(spinnerObjectArrayList.get(position).getId());
        holder.idTextView.setVisibility(View.GONE);

        holder.nameTextView.setText(spinnerObjectArrayList.get(position).getName());
        if(spinnerObjectArrayList.get(position).isCurrent()){
            holder.nameTextView.setTextColor(AppContext.getCurrentActivity().getResources().getColor(R.color.title_item_text_isselted_color));
        }else{
            holder.nameTextView.setTextColor(AppContext.getCurrentActivity().getResources().getColor(R.color.titile_name_color));
        }
        return convertView;
    }
}
