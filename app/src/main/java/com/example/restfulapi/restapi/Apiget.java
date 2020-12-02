package com.example.restfulapi.restapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface Apiget
{
    String Baseurl="https://simplifiedcoding.net/demos/";
    String feed="marvel";

    Retrofit retrofit = new Retrofit.Builder().baseUrl(Baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

   @GET(feed)

    Call<List<modelclass>>getusuer();

}