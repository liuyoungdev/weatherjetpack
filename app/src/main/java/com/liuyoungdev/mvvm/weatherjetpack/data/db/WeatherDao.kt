package com.liuyoungdev.mvvm.weatherjetpack.data.db

import android.annotation.SuppressLint
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.liuyoungdev.mvvm.weatherjetpack.WeatherJetpackApplication
import com.liuyoungdev.mvvm.weatherjetpack.data.model.weather.Weather

/**
 * author： yang
 * date  ： 2020-06-15
 */
class WeatherDao {

    fun getWeatherCacheInfo(): Weather? {
        val weatherInfo =
            PreferenceManager.getDefaultSharedPreferences(WeatherJetpackApplication.context)
                .getString("weather", null)
        if (weatherInfo != null) {
            return Gson().fromJson(weatherInfo, Weather::class.java)
        }
        return null


    }

    fun saveWeatherInfo(weather: Weather) {
        val weathers = Gson().toJson(weather)
        val defaultSharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(WeatherJetpackApplication.context)
        val editor = defaultSharedPreferences.edit()
        editor.putString("weather", weathers)
        editor.apply()
    }

    fun cachPicUrl(url: String) {
        val defaultSharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(WeatherJetpackApplication.context)
        val editor = defaultSharedPreferences.edit()
        editor.putString("bindpic", url)
        editor.apply()

    }

    fun getBindPic(): String? {
        val url = PreferenceManager.getDefaultSharedPreferences(WeatherJetpackApplication.context)
            .getString("bindpic", null)

        return url
    }
}