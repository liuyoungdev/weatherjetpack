package com.liuyoungdev.mvvm.weatherjetpack.util

import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.liuyoungdev.mvvm.weatherjetpack.R
import com.liuyoungdev.mvvm.weatherjetpack.data.model.weather.Weather
import com.liuyoungdev.mvvm.weatherjetpack.databinding.ForecastItemBinding

/**
 * author： yang
 * date  ： 2020-07-28
 */
//kotlin中使用注解BindingAdapter需要build.gradle中添加kapt支持
@BindingAdapter("bind:loadPic")
fun ImageView.loadBingPic(url: String?) {
    if (url != null) Glide.with(context).load(url).into(this)

}

@BindingAdapter("bind:showForecast")
fun LinearLayout.showForecast(weather: Weather?) = weather?.let {
    removeAllViews()
    for (forecast in it.forecastList) {
        val view = LayoutInflater.from(context).inflate(R.layout.forecast_item, this, false)
        DataBindingUtil.bind<ForecastItemBinding>(view)?.forecast = forecast
        addView(view)
    }
}