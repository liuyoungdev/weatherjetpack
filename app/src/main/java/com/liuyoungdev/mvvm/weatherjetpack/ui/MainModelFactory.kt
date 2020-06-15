package com.liuyoungdev.mvvm.weatherjetpack.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.liuyoungdev.mvvm.weatherjetpack.data.WeatherRespository

class MainModelFactory(private val respository: WeatherRespository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(respository) as T

    }
}
