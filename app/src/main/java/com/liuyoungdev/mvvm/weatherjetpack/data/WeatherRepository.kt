package com.liuyoungdev.mvvm.weatherjetpack.data

import com.liuyoungdev.mvvm.weatherjetpack.data.db.WeatherDao
import com.liuyoungdev.mvvm.weatherjetpack.data.model.weather.Weather
import com.liuyoungdev.mvvm.weatherjetpack.data.network.WeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository private constructor(
    private val weatherDao: WeatherDao,
    private val netWork: WeatherNetwork
) {

    fun isWeatherCached() = weatherDao.getWeatherCacheInfo() != null

    suspend fun getWeather(weatherID: String) = withContext(Dispatchers.IO) {
        var weather = weatherDao.getWeatherCacheInfo()
        if (weather == null) weather = refreshWeather(weatherID)
        weather
    }

    suspend fun refreshWeather(weatherID: String) = withContext(Dispatchers.IO) {
        val weatherInfo = netWork.requestWeatherInfo(weatherID)
        val weather = weatherInfo.weather!![0]
        weatherDao.saveWeatherInfo(weather)
        weather

    }

    fun getWeatherCachInfo() = weatherDao.getWeatherCacheInfo()!!
    suspend fun refreshBindPic() = requestBindPic()

    private suspend fun requestBindPic() = withContext(Dispatchers.IO) {
        val url = netWork.fetchBindPic()
        weatherDao.cachPicUrl(url)
        url
    }

    suspend fun getBindPic(): String? {

        var bindPic = weatherDao.getBindPic()
        if (bindPic == null) bindPic = requestBindPic()
        return bindPic
    }

    companion object {
        private lateinit var instance: WeatherRepository
        fun getInstance(weatherDao: WeatherDao, netWork: WeatherNetwork): WeatherRepository {
            if (!::instance.isInitialized) {
                synchronized(WeatherRepository::class.java) {
                    if (!::instance.isInitialized) {
                        instance =
                            WeatherRepository(weatherDao, netWork)
                    }
                }
            }
            return instance
        }
    }
}
