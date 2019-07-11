package com.example.requestapi.retrofit;


import com.example.requestapi.model.Weather;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherServerClient {

    String BASE_URL = "http://140.116.247.161:7777";

    @GET("/weather/{cityName}/")
    Call<ArrayList<Weather>> getWeatherInfo(@Path("cityName") String cityName);
}