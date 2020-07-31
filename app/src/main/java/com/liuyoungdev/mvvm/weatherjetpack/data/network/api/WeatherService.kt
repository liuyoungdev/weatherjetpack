package com.liuyoungdev.mvvm.weatherjetpack.data.network.api

import com.liuyoungdev.mvvm.weatherjetpack.data.model.weather.HeWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * author： yang
 * date  ： 2020-07-24
 */
interface WeatherService {
    @GET("api/weather")
    fun getWeather(@Query("cityid") cityid: String): Call<HeWeather>

    @GET("api/bing_pic")
    fun getBindPic(): Call<String>
}