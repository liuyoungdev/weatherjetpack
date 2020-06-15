package com.liuyoungdev.mvvm.weatherjetpack.util

import com.liuyoungdev.mvvm.weatherjetpack.data.WeatherRespository
import com.liuyoungdev.mvvm.weatherjetpack.data.db.WeatherDao
import com.liuyoungdev.mvvm.weatherjetpack.ui.MainModelFactory

/**
 * author： yang
 * date  ： 2020-06-12
 */
object InjectorUtil {
    fun getWeatherRespository() = WeatherRespository.getInstance(WeatherDao())

    fun getMainModelFactory() =
        MainModelFactory(getWeatherRespository())
}