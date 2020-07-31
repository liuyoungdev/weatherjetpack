package com.liuyoungdev.mvvm.weatherjetpack.util

import com.liuyoungdev.mvvm.weatherjetpack.data.PlaceRepository
import com.liuyoungdev.mvvm.weatherjetpack.data.WeatherRepository
import com.liuyoungdev.mvvm.weatherjetpack.data.db.PlaceDao
import com.liuyoungdev.mvvm.weatherjetpack.data.db.WeatherDao
import com.liuyoungdev.mvvm.weatherjetpack.data.network.WeatherNetwork
import com.liuyoungdev.mvvm.weatherjetpack.ui.MainModelFactory
import com.liuyoungdev.mvvm.weatherjetpack.ui.area.ChooseAreaModelFactory
import com.liuyoungdev.mvvm.weatherjetpack.ui.weather.WeatherModelFactory

/**
 * author： yang
 * date  ： 2020-06-12
 */
object InjectorUtil {
    fun getWeatherRepository() = WeatherRepository.getInstance(WeatherDao(),WeatherNetwork())
    fun getPlaceRepository()= PlaceRepository.getInstance(PlaceDao(), WeatherNetwork())

    fun getMainModelFactory() = MainModelFactory(getWeatherRepository())
    fun getChooseAreaModelFactory() = ChooseAreaModelFactory(getPlaceRepository())
    fun getWeatherModelFactory() = WeatherModelFactory(getWeatherRepository())
}