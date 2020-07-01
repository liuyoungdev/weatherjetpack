package com.liuyoungdev.mvvm.weatherjetpack.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * author： yang
 * date  ： 2020-06-24
 */
object ServerCreator {

    private val BASE_URL = "http://guolin.tech/"
    private val okHttpClient = OkHttpClient.Builder()

    private val builder = Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient.build())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

    private val retrofit = builder.build()

    fun <T> create(serviceApi: Class<T>): T = create(serviceApi)


}