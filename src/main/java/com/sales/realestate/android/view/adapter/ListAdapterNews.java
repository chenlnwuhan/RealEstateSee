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
import com.sales.realestate.android.bean.MailInfo;
import com.sales.realestate.android.bean.NewsInfo;

import java.util.ArrayList;

/**
 * Created by chenlin on 2015/7/20.
 */
public class ListAdapterNews extends BaseAdapter {

    public ArrayList<NewsInfo> mMailInfoList = new ArrayList<NewsInfo>();
    public ListAdapterNews(ArrayList<NewsInfo> mailInfoList){
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
            view = inflater.inflate(R.layout.view_item_news, null);
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
        NewsInfo mMailInfo = mMailInfoList.get(index);
        if(mMailInfo.NewsType.equals("新闻")){
            holder.mImageView.setImageResource(R.drawable.pic_more_news_icon);
        }else{
            holder.mImageView.setImageResource(R.drawable.pic_more_promotion_icon);
        }
        holder.mTextViewTiltle.setText(mMailInfo.NewsTitle);
        holder.mTextViewDate.setText(mMailInfo.AddTime);
        return view;
    }

    public class  Holder {
        public ImageView mImageView ;
        public TextView mTextViewTiltle;
        public TextView mTextViewDate;

    }
}
