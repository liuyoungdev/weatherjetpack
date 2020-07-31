package com.liuyoungdev.mvvm.weatherjetpack.data.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.liuyoungdev.mvvm.weatherjetpack.data.model.weather.HeWeather
import com.liuyoungdev.mvvm.weatherjetpack.data.network.api.PlaceService
import com.liuyoungdev.mvvm.weatherjetpack.data.network.api.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * author： yang
 * date  ： 2020-06-24
 */
class WeatherNetwork {

    private val placeService = ServerCreator.create(PlaceService::class.java)

    private val weatherService = ServerCreator.create(WeatherService::class.java)

    suspend fun fetchProvinceList() = placeService.getProvince().await()
    suspend fun fetchCityList(provinceCode: Int) = placeService.getCities(provinceCode).await()
    suspend fun fetchBindPic() = weatherService.getBindPic().await()

    suspend fun getCounties(provinceCode: Int, cityCode: Int) =
        placeService.getCounties(provinceCode, cityCode).await()

    suspend fun requestWeatherInfo(weatherID: String) =
        weatherService.getWeather(weatherID).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    println("返回值" + body.toString())
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

            })
        }
    }


    companion object {

        private var network: WeatherNetwork? = null
        fun getInstance(): WeatherNetwork {
            if (network == null) {
                synchronized(WeatherNetwork::class.java) {
                    if (network == null) {
                        network = WeatherNetwork()
                    }
                }
            }
            return network!!
        }
    }

}
