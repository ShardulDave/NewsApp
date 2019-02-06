package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class NewsRecyclerViewAdapter  extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {


    ArrayList<NewsItem> newsdata;



    public class NewsViewHolder extends RecyclerView.ViewHolder {

        public final TextView titleView;
        public final TextView descriptionView;
        public final TextView dateView;

        public NewsViewHolder(View view){
            super(view);
            titleView=(TextView)view.findViewById(R.id.title_t);
            descriptionView=(TextView)view.findViewById(R.id.description_t);
            dateView=(TextView)view.findViewById(R.id.date_t);


        }


    }


    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.news_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new NewsViewHolder(view);
    }


    public void onBindViewHolder(NewsViewHolder newsAdapterViewHolder, int position) {
        String title = newsdata.get(position).getTitle();
        newsAdapterViewHolder.titleView.setText(title);
        String decription=newsdata.get(position).getDescription();
        newsAdapterViewHolder.descriptionView.setText(decription);
        String date=newsdata.get(position).getPublishedAt();
        newsAdapterViewHolder.dateView.setText(decription);
    }

    @Override
    public int getItemCount() {
        if (null == newsdata) return 0;
        return newsdata.size();
    }


}
