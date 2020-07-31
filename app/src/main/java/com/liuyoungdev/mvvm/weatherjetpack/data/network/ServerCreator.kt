package com.liuyoungdev.mvvm.weatherjetpack.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * author： yang
 * date  ： 2020-06-24
 */
object ServerCreator {

    private val BASE_URL = "http://guolin.tech/"

    private val builder = Retrofit.Builder().baseUrl(BASE_URL).client(initOkhttp())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
    val retrofit = builder.build()

    fun <T> create(serviceApi: Class<T>): T = retrofit.create(serviceApi)


    private fun initOkhttp(): OkHttpClient {
        val loggIntercepter = HttpLoggingInterceptor()
        loggIntercepter.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(loggIntercepter).build()

        return client
    }
}