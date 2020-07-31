package com.liuyoungdev.mvvm.weatherjetpack.data.model.weather

import com.google.gson.annotations.SerializedName

/**
 * author： yang
 * date  ： 2020-06-15
 */
class Now {
    @SerializedName("tmp")
    var temperature = ""
    @SerializedName("cond")
    lateinit var more: More

    fun degree() = "$temperature℃"

    inner class More {
        @SerializedName("txt")
        var info = ""
    }
}