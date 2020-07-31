package com.liuyoungdev.mvvm.weatherjetpack.data.model.weather

import com.google.gson.annotations.SerializedName

/**
 * author： yang
 * date  ： 2020-06-15
 */
class Basic {
    @SerializedName("city")
    var cityName = ""
    @SerializedName("id")
    var weatherId = ""
    lateinit var update: Update

    inner class Update {
        @SerializedName("loc")
        var updateTime = ""

        fun time() = updateTime.split(" ")[1]
    }
}