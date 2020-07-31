package com.liuyoungdev.mvvm.weatherjetpack.data.network.api

import com.liuyoungdev.mvvm.weatherjetpack.data.model.area.City
import com.liuyoungdev.mvvm.weatherjetpack.data.model.area.County
import com.liuyoungdev.mvvm.weatherjetpack.data.model.area.Province
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * author： yang
 * date  ： 2020-07-01
 */
interface PlaceService {
    @GET("api/china")
    fun getProvince(): Call<MutableList<Province>>
    @GET("api/china/{provinceCode}")
    fun getCities(@Path("provinceCode") provinceCode:Int): Call<MutableList<City>>
    @GET("api/china/{provinceCode}/{cityCode}")
    fun getCounties(@Path("provinceCode") provinceCode:Int, @Path("cityCode") cityCode:Int): Call<MutableList<County>>
}