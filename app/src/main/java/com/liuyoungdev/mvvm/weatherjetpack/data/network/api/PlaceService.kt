package com.liuyoungdev.mvvm.weatherjetpack.data.network.api

import com.liuyoungdev.mvvm.weatherjetpack.data.model.area.Province
import retrofit2.Call
import retrofit2.http.GET

/**
 * author： yang
 * date  ： 2020-07-01
 */
interface PlaceService {
    @GET("api/china")
    fun getProvince(): Call<MutableList<Province>>
}