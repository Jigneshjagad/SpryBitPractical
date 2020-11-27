package com.sprybit.practical.retrofit;

import com.sprybit.practical.model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    //verification code http://api.openweathermap.org/data/2.5/kerala&APPID=f7ae896d963f6d47ee09e3a70ee4ceb5
    @GET("weather")
    Call<Weather> search(@Query("q") String q, @Query("APPID") String appId);
//    void search(@Query(value="q", encoded=true) String q);

}
