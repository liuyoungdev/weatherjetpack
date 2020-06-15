package com.liuyoungdev.mvvm.weatherjetpack

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import org.litepal.LitePal

/**
 * author： yang
 * date  ： 2020-06-15
 */
class WeatherJetpackApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LitePal.initialize(this)
        context = this
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}