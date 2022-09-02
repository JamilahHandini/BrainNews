package com.example.brainnews.rests;

import com.example.brainnews.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {



    @GET("everything")
    Call<ResponseModel> getNewsSearch(@Query("q")String keyword,
                                      @Query("language") String language,
                                      @Query("sortBy")String sortBy,
                                      @Query("apiKey")String apiKey);


    @GET("top-headlines")
    Call<ResponseModel> getLatestNews(@Query("category") String source, @Query("country")String country, @Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<ResponseModel> searchQueryCountry(@Query("q")String search,@Query("country")String country,@Query("apiKey")String apiKey);

    @GET("top-headlines")
    Call<ResponseModel> searchQuery(@Query("q")String search,@Query("apiKey")String apiKey);



}