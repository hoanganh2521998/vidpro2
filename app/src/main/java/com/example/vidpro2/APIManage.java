package com.example.vidpro2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIManage {
    @GET("getVideoHot")
    Call<List<Video>> getHot();

    @GET("GetCategory")
    Call<List<Categories>> getCate();

    @GET("GetItemCategoryOne")
    Call<List<Video>> getCateOne();

    @GET("GetItemThree")
    Call<List<Video>> getCateThree();

    @GET("GetItemCategoryTwo")
    Call<List<Video>> getCateTwo();




}
