package com.example.android.homework.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.android.homework.NewsItem;
import com.example.android.homework.R;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder> {

    Context context;
    List<NewsItem> newsItems;

    public NewsAdapter(Context context) {
        this.context = context;
    }

    public void setNewsDataFromObserver(List<NewsItem> newsItems) {
        this.newsItems = newsItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsAdapter.NewsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);
        return new NewsItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsItemViewHolder holder, int position) {

        final NewsItem newsItem = newsItems.get(position);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "" + newsItem.getWebUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });
        holder.newsTitle.setText("Title : " + newsItem.getTitle());
        holder.newsDate.setText("Date  : " + newsItem.getPublishedAt());
        holder.newsDescription.setText("Description : " + newsItem.getDescription());



    }

    @Override
    public int getItemCount() {
        if (newsItems != null) return newsItems.size();
        else return 0;
    }


    public class NewsItemViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        TextView newsTitle, newsDescription, newsDate;

        public NewsItemViewHolder(View view) {
            super(view);
            relativeLayout = view.findViewById(R.id.relativeLayout);
            newsTitle = view.findViewById(R.id.newstitle);
            newsDescription = view.findViewById(R.id.newsdesc);
            newsDate = view.findViewById(R.id.newsdate);

        }
    }
}
