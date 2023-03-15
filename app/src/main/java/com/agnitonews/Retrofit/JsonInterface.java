package com.agnitonews.Retrofit;

import com.agnitonews.Model.CategoryModel;
import com.agnitonews.Model.HomeModel;
import com.agnitonews.Model.NewsDetailsModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface JsonInterface {

    @GET(API.category)
    Call<CategoryModel> showCategory();


    @POST(API.show_New)
    Call<HomeModel> showNews();


     @FormUrlEncoded
    @POST(API.show_NewDetails)
    Call<NewsDetailsModel> showNewsDetails(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(API.show_New_by_Cat)
    Call<HomeModel> showNewsByCat(@FieldMap Map<String, String> params);
}
