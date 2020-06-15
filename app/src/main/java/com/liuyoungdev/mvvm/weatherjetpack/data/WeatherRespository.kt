package com.liuyoungdev.mvvm.weatherjetpack.data

import com.liuyoungdev.mvvm.weatherjetpack.data.db.WeatherDao

class WeatherRespository private constructor(private val weatherDao: WeatherDao) {
    fun isWeatherCached() = weatherDao.getWeatherCacheInfo() != null

    companion object {
        private lateinit var instance: WeatherRespository
        fun getInstance(weatherDao: WeatherDao): WeatherRespository {
            if (!Companion::instance.isInitialized) {
                synchronized(WeatherRespository::class.java) {
                    if (Companion::instance.isInitialized) {
                        instance =
                            WeatherRespository(weatherDao)
                    }
                }
            }
            return instance
        }
    }
}
