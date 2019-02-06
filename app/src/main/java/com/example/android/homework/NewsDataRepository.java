package com.example.android.homework;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.example.android.homework.Utility.JsonUtils;
import com.example.android.homework.Utility.NetworkUtils;
import com.example.android.homework.roomdatabase.NewsDataBase;
import com.example.android.homework.roomdatabase.NewsItemDao;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class NewsDataRepository {

    private NewsItemDao newsItemDao;
    private NewsDataBase newsDataBase;
    Context ctx;

    public NewsDataRepository(Context ctx) {
        newsDataBase = NewsDataBase.getDatabase(ctx);
        newsItemDao = newsDataBase.newsQueriesDao();
        this.ctx = ctx;
    }

    public void getDataFromNewsApiAndSave() {
        new getNewsApiResponse(newsItemDao).execute();
    }

    public static class getNewsApiResponse extends AsyncTask<Void, Void, String> {
        String response;
        NewsItemDao newsItemDao;

        private getNewsApiResponse(NewsItemDao newsItemDao) {
            this.newsItemDao = newsItemDao;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {

                response = NetworkUtils.getResponseFromHttpUrl
                        (new URL(NetworkUtils.appendURL(NetworkUtils.BASEURL, NetworkUtils.APIKEY)));

                if (JsonUtils.parseData(response).size() != 0) {
                    newsItemDao.clearrAll();
                    newsItemDao.insert(JsonUtils.parseData(response));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }


    }


    public MutableLiveData<List<NewsItem>> getNews() {
        try {
            MutableLiveData<List<NewsItem>> data;
            data = new getDataFromRoomLocalDataBase().execute().get();
            return data;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


    public NewsItemDao getnewsItemDao() {
        return newsItemDao;
    }
    private class getDataFromRoomLocalDataBase extends AsyncTask<Void, Void, MutableLiveData<List<NewsItem>>> {

        @Override
        protected MutableLiveData<List<NewsItem>> doInBackground(Void... voids) {
            MutableLiveData<List<NewsItem>> listMutableLiveData = new MutableLiveData<>();
            listMutableLiveData.postValue(newsItemDao.loadAllNews());
            return listMutableLiveData;
        }
    }

}
