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
import com.sales.realestate.android.bean.FollowDetailInfo;

import java.util.ArrayList;

/**
 * Created by chenlin on 2015/7/20.
 */
public class ListAdapterFollow extends BaseAdapter {

    public ArrayList<FollowDetailInfo> mMailInfoList = new ArrayList<FollowDetailInfo>();
    public ListAdapterFollow(ArrayList<FollowDetailInfo> mailInfoList){
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
            LayoutInflater inflater = LayoutInflater.from(AppContext.getCurrentActivity());
            view = inflater.inflate(R.layout.view_item_custom_follow, null);
            ListView.LayoutParams mLayoutParams= new ListView.LayoutParams(ListView.LayoutParams.FILL_PARENT, (int) AppContext.getCurrentActivity().getResources().getDimension(R.dimen.listview_item_mail_height));
            view.setLayoutParams(mLayoutParams);
            holder = new Holder();
            holder.mImageView = (ImageView) view.findViewById(R.id.img_listview_isnew);
            holder.mTextViewTiltle = (TextView) view.findViewById(R.id.listview_item_info);
            holder.mTextViewDate = (TextView) view.findViewById(R.id.listview_item_date);
            view.setTag(holder);
        }
        else {
            holder = (Holder) view.getTag();
        }
        FollowDetailInfo mMailInfo = mMailInfoList.get(index);
        if(mMailInfo.FollowTypeName.equals("电话跟进")){
            holder.mImageView.setImageResource(R.drawable.pic_phone_in);
        }else if(mMailInfo.FollowTypeName.equals("短信跟进")){
            holder.mImageView.setImageResource(R.drawable.pic_message_in);
        }else if(mMailInfo.FollowTypeName.equals("人员随访")){
            holder.mImageView.setImageResource(R.drawable.pic_people_in);
        }else if(mMailInfo.FollowTypeName.equals("微信跟进")){
            holder.mImageView.setImageResource(R.drawable.pic_wei_in);
        }else{
            holder.mImageView.setImageResource(R.drawable.pic_phone_go);
        }
        holder.mTextViewTiltle.setText(mMailInfo.Details);
        holder.mTextViewDate.setText(mMailInfo.AddTime);
        return view;
    }

    public class  Holder {
        public ImageView mImageView ;
        public TextView mTextViewTiltle;
        public TextView mTextViewDate;

    }
}
