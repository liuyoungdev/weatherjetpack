package com.liuyoungdev.mvvm.weatherjetpack.ui.weather

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.liuyoungdev.mvvm.weatherjetpack.R
import com.liuyoungdev.mvvm.weatherjetpack.databinding.WeatherActivityBinding
import com.liuyoungdev.mvvm.weatherjetpack.util.InjectorUtil
import kotlinx.android.synthetic.main.title.*
import kotlinx.android.synthetic.main.weather_activity.*

class WeatherActivity : AppCompatActivity() {
    val viewModel by lazy {
        ViewModelProviders.of(this, InjectorUtil.getWeatherModelFactory())
            .get(WeatherActivityModel::class.java)
    }
    private val binding by lazy {
        DataBindingUtil.setContentView<WeatherActivityBinding>(this, R.layout.weather_activity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val decorView = window.decorView
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.statusBarColor = Color.TRANSPARENT
        }
        binding.viewModel = viewModel
//        为什么要显示的说明这一行？？？？？
//        调用了这个方法，我们使用的LiveData就能够感应到相关组件的生命周期，在对应的时候更新UI。
        binding.lifecycleOwner = this

//        只要数据改变，相应的UI就会改变，不需要手动添加观察数据改变，手动赋值。
//        viewModel.weather.observe(this, Observer { weatherData ->
//            weathertitle.text = weatherData.basic.cityName
//            timedate.text = weatherData.basic.update.time()
//        })
        viewModel.weatherID = if (viewModel.isWeatherCached()) {
            viewModel.getCachedWeather().basic.weatherId
        } else {
            intent.getStringExtra("weatherID")
        }
        viewModel.getWeatherInfo()
        navButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

}


