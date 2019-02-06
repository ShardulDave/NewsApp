package com.example.android.homework;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {
    
    NewsDataRepository newsDataRepository;

    public NewsItemViewModel(Application application) {
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
