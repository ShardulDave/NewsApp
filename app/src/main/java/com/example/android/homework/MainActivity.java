package com.example.android.homework;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.homework.Utility.InternetUtils;
import com.example.android.homework.adapter.NewsAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    NewsAdapter newsItemAdapter;
    NewsViewModel newsViewModel;
    RecyclerView newsItemRecyclerView;
    int mTIme = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_main);

        defineViewsandsetAdapter();
        getNewsDataandObserveWhenLoad();

    }

    private void defineViewsandsetAdapter() {
        newsItemRecyclerView = findViewById(R.id.recycler_view);

        newsItemAdapter = new NewsAdapter(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        newsItemRecyclerView.setLayoutManager(mLayoutManager);
        newsItemRecyclerView.setAdapter(newsItemAdapter);
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_refresh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.refreshData) {
            if (!InternetUtils.checkInternetConnectedOrNot(MainActivity.this)) {
                Toast.makeText(this, "Please connect to your wifi or mobile internet connection...", Toast.LENGTH_SHORT).show();
            } else {
                getNewsDataandObserveWhenLoad();
            }
        }

        return true;
    }

    public void getNewsDataandObserveWhenLoad() {
        newsViewModel.getDataFromServer();
        newsViewModel.getNewsSories().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable List<NewsItem> newsItems) {
                newsItemAdapter.setNewsDataFromObserver(newsItems);
            }
        });
    }

}
