package com.example.requestapi.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {
    @Expose
    @SerializedName("city")
    private String city;

    @Expose
    @SerializedName("weathertime")
    private String weatherTime;

    @Expose
    @SerializedName("pop")
    private String pop;         // 降雨機率

    @Expose
    @SerializedName("temperature")
    private String temparature;

    @Expose
    @SerializedName("rh")
    private String rh;          // 相對溼度

    @Expose
    @SerializedName("minci")
    private String minci;       // 最低舒適度

    @Expose
    @SerializedName("windspeed")
    private String windSpeed;

    @Expose
    @SerializedName("maxat")
    private String maxat;       // 最高體感溫度

    @Expose
    @SerializedName("weather")
    private String weather;     // 天氣描述的編號

    @Expose
    @SerializedName("maxci")
    private String maxci;       // 最高舒適度

    @Expose
    @SerializedName("uvi")
    private String uvi;         // 紫外線

    @Expose
    @SerializedName("minat")
    private String minat;       // 最低體感溫度

    @Expose
    @SerializedName("pm")
    private String pm;          // pm2.5

    public Weather(String city, String weatherTime, String pop, String temparature, String rh, String minci,
                   String windSpeed, String maxat, String weather, String maxci, String uvi, String minat, String pm){
        this.city = city;
        this.weatherTime = weatherTime;
        this.pop = pop;
        this.temparature = temparature;
        this.rh = rh;
        this.minci = minci;
        this.windSpeed = windSpeed;
        this.maxat = maxat;
        this.weather = weather;
        this.maxci = maxci;
        this.uvi = uvi;
        this.minat = minat;
        this.pm = pm;
    }

    public String getCity() {
        return city;
    }

    public String getWeatherTime() {
        return weatherTime;
    }

    public String getPop() {
        return pop;
    }

    public String getTemparature() {
        return temparature;
    }

    public String getRh() {
        return rh;
    }

    public String getMinci() {
        return minci;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getMaxat() {
        return maxat;
    }

    public String getWeather() {
        return weather;
    }

    public String getMaxci() {
        return maxci;
    }

    public String getUvi() {
        return uvi;
    }

    public String getMinat() {
        return minat;
    }

    public String getPm() {
        return pm;
    }
}
