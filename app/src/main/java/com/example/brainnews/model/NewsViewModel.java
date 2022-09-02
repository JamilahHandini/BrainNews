package com.example.brainnews.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import com.example.brainnews.activity.MainActivity;

public class NewsViewModel extends AndroidViewModel {

    private static final String API_KEY = "d7f16611b7d7415f81773f2ec68a2800";

    private NewsRepository newsRepository;
    private LiveData<ResponseModel> responseModelLiveData;


    public NewsViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        newsRepository = new NewsRepository();
        responseModelLiveData = newsRepository.getResponseModel();
    }

    public void searchNews(String keyword,String country){
        newsRepository.searchNews(keyword,country, API_KEY);
    }

    public void topHeadlines(String categories, String country){
        newsRepository.topHeadlines(categories, country,  API_KEY);
    }

    public void getNewsSearch(String keyword){
        newsRepository.getNewsSearch(keyword,"id","publishedAt",API_KEY);
    }

    public LiveData<ResponseModel> getResponseModelLiveData(){
        return responseModelLiveData;
    }

}
