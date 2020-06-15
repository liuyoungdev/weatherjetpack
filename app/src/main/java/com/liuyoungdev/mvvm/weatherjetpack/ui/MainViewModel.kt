package com.liuyoungdev.mvvm.weatherjetpack.ui

import androidx.lifecycle.ViewModel
import com.liuyoungdev.mvvm.weatherjetpack.data.WeatherRespository

/**
 * author： yang
 * date  ： 2020-06-12
 */
class MainViewModel(private val respository : WeatherRespository) : ViewModel() {
    fun isWeatherCached()= respository.isWeatherCached()

}