package com.liuyoungdev.mvvm.weatherjetpack.data.model

import com.google.gson.annotations.SerializedName

/**
 * author： yang
 * date  ： 2020-06-15
 */
class Weather {
    lateinit var basic: Basic
    lateinit var aqi: AQI
    lateinit var now: Now
    lateinit var suggestion: Suggestion
    @SerializedName("daily_forecast")
    lateinit var forecastList: List<Forecast>
}