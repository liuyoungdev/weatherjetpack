package com.liuyoungdev.mvvm.weatherjetpack.data.model.weather

import com.google.gson.annotations.SerializedName
import com.liuyoungdev.mvvm.weatherjetpack.data.model.weather.*

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