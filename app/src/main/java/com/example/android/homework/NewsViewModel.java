package com.example.android.homework;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import com.example.android.homework.roomdatabase.NewsQueriesDao;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {
    
    NewsDataRepository newsDataRepository;

    public NewsViewModel(Application application) {
        super(application);
        this.newsDataRepository = new NewsDataRepository(application);
    }
    public void getDataFromServer(){
        newsDataRepository.getDataFromNewsApiAndSave();
    }

    public MutableLiveData<List<NewsItem>> getNewsSories(){
       return newsDataRepository.getNews();
    }

}
