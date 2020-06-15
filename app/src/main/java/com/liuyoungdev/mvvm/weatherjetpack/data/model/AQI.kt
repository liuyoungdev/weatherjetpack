package com.liuyoungdev.mvvm.weatherjetpack.data.model

/**
 * author： yang
 * date  ： 2020-06-15
 */
class AQI {

    lateinit var city: AQICity

    inner class AQICity {
        var aqi = ""
        var pm25  = ""
    }
}