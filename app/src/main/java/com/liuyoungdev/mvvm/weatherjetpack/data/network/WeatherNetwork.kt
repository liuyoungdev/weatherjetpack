package com.liuyoungdev.mvvm.weatherjetpack.data.network

import com.liuyoungdev.mvvm.weatherjetpack.data.network.api.PlaceService
import retrofit2.Call

/**
 * author： yang
 * date  ： 2020-06-24
 */
class WeatherNetwork {

    private val placeService = ServerCreator.create(PlaceService::class.java)
    suspend fun getProvinceList() = placeService.getProvince()



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
