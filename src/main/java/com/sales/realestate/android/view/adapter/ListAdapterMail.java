package com.sales.realestate.android.view.adapter;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sales.realestate.android.AppContext;
import com.sales.realestate.android.R;
import com.sales.realestate.android.bean.MailInfo;

import java.util.ArrayList;

/**
 * Created by chenlin on 2015/7/20.
 */
public class ListAdapterMail extends BaseAdapter {

    public int mRightWidth = 0;
    public ArrayList<MailInfo> mMailInfoList = new ArrayList<MailInfo>();
    public ListAdapterMail(ArrayList<MailInfo> mailInfoList ){
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
    public View getView(final int index, View view, ViewGroup arg2) {
        Holder holder ;
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(AppContext.getCurrentActivity());
            view = inflater.inflate(R.layout.view_item_maillist, null);
            ListView.LayoutParams mLayoutParams= new ListView.LayoutParams(ListView.LayoutParams.FILL_PARENT, (int) AppContext.getCurrentActivity().getResources().getDimension(R.dimen.listview_item_mail_height));
            view.setLayoutParams(mLayoutParams);
            holder = new Holder();
            holder.item_left = (RelativeLayout)view.findViewById(R.id.item_left);
            holder.item_right = (RelativeLayout)view.findViewById(R.id.item_right);


            holder.mImageView = (ImageView) view.findViewById(R.id.img_listview_isnew);
            holder.mTextViewTiltle = (TextView) view.findViewById(R.id.listview_item_info);
            holder.mTextViewDate = (TextView) view.findViewById(R.id.listview_item_date);
            holder.item_right_txt = (TextView)view.findViewById(R.id.item_right_txt);
            view.setTag(holder);
        }
        else {
            holder = (Holder) view.getTag();
        }
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        holder.item_left.setLayoutParams(lp1);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(mRightWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        holder.item_right.setLayoutParams(lp2);
        MailInfo mMailInfo = mMailInfoList.get(index);
        if(mMailInfo.IsRead.equals("已读")){
            holder.mImageView.setVisibility(View.INVISIBLE);
        }else{
            holder.mImageView.setVisibility(View.VISIBLE);
        }
        holder.mTextViewTiltle.setText(mMailInfo.Contents);
        holder.mTextViewDate.setText(mMailInfo.SendTime);
        holder.item_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onRightItemClick(v, index);
                }
            }
        });
        return view;
    }

    public class  Holder {

        RelativeLayout item_left;
        RelativeLayout item_right;

        public ImageView mImageView ;
        public TextView mTextViewTiltle;
        public TextView mTextViewDate;
        TextView item_right_txt;


    }
    /**
     * 单击事件监听器
     */
    private onRightItemClickListener mListener = null;

    public void setOnRightItemClickListener(onRightItemClickListener listener){
        mListener = listener;
    }

    public interface onRightItemClickListener {
        void onRightItemClick(View v, int position);
    }

}
