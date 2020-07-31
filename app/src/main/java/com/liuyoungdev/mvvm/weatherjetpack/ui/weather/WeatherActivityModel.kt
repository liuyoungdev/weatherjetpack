package com.liuyoungdev.mvvm.weatherjetpack.ui.weather

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liuyoungdev.mvvm.weatherjetpack.WeatherJetpackApplication
import com.liuyoungdev.mvvm.weatherjetpack.data.PlaceRepository
import com.liuyoungdev.mvvm.weatherjetpack.data.WeatherRepository
import com.liuyoungdev.mvvm.weatherjetpack.data.model.weather.Weather
import kotlinx.coroutines.launch

/**
 * author： yang
 * date  ： 2020-07-23
 */
class WeatherActivityModel(private val repository: WeatherRepository) : ViewModel() {

    var weatherID: String = ""
    var weather = MutableLiveData<Weather>()
    var bindPicUrl = MutableLiveData<String>()
    var refreshing = MutableLiveData<Boolean>()
    var weatherInitialized = MutableLiveData<Boolean>()


    fun getWeatherInfo() {

        launch({
            weather.value = repository.getWeather(weatherID)
            weatherInitialized.value = true

        }, {
            Toast.makeText(WeatherJetpackApplication.context, it.message, Toast.LENGTH_SHORT).show()
        })

        getBindPicUrl(false)
    }

    private fun getBindPicUrl(refresh: Boolean) {
        launch({
            bindPicUrl.value = if (refresh) repository.refreshBindPic() else repository.getBindPic()
        }, {
            Toast.makeText(WeatherJetpackApplication.context, it.message, Toast.LENGTH_SHORT).show()
        })

    }

    fun refresh() {
        refreshWeather()
    }

    fun refreshWeather() {
        refreshing.value = true
        launch({
            weather.value = repository.refreshWeather(weatherID)
            refreshing.value = false
            weatherInitialized.value = true

        }, {
            Toast.makeText(WeatherJetpackApplication.context, it.message, Toast.LENGTH_SHORT).show()
            refreshing.value = false

        })
        getBindPicUrl(true)
    }

    private fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) =
        viewModelScope.launch {
            try {

                block()
            } catch (e: Throwable) {
                error(e)
            }
        }

    fun isWeatherCached() = repository.isWeatherCached()
    fun getCachedWeather() = repository.getWeatherCachInfo()

}