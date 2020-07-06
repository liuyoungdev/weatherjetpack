package com.liuyoungdev.mvvm.weatherjetpack.data.network

import com.liuyoungdev.mvvm.weatherjetpack.data.network.api.PlaceService
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
    suspend fun getProvinceList() = placeService.getProvince().await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body!=null)continuation.resume(body)
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
