package com.liuyoungdev.mvvm.weatherjetpack.data.model.weather

import com.google.gson.annotations.SerializedName

/**
 * author： yang
 * date  ： 2020-06-15
 */
class Suggestion {
    @SerializedName("comf")
    lateinit var comfort: Comfort
    @SerializedName("cw")
    lateinit var carWash: CarWash
    lateinit var sport: Sport

    inner class Comfort {
        @SerializedName("txt")
        var info = ""
    }

    inner class CarWash {
        @SerializedName("txt")
        var info = ""
    }

    inner class Sport {
        @SerializedName("txt")
        var info = ""
    }
}