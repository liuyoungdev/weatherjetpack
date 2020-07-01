package com.liuyoungdev.mvvm.weatherjetpack.data

import com.liuyoungdev.mvvm.weatherjetpack.data.db.WeatherDao

class WeatherRepository private constructor(private val weatherDao: WeatherDao) {

    fun isWeatherCached() = weatherDao.getWeatherCacheInfo() != null

    companion object {
        private lateinit var instance: WeatherRepository
        fun getInstance(weatherDao: WeatherDao): WeatherRepository {
            if (!::instance.isInitialized) {
                synchronized(WeatherRepository::class.java) {
                    if (!::instance.isInitialized) {
                        instance =
                            WeatherRepository(weatherDao)
                    }
                }
            }
            return instance
        }
    }
}
