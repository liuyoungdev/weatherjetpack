package com.liuyoungdev.mvvm.weatherjetpack.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.liuyoungdev.mvvm.weatherjetpack.util.InjectorUtil
import com.liuyoungdev.mvvm.weatherjetpack.R
import com.liuyoungdev.mvvm.weatherjetpack.ui.weather.WeatherActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProviders.of(
            this,
            InjectorUtil.getMainModelFactory()
        )
            .get(MainViewModel::class.java)
        if (viewModel.isWeatherCached()) {
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
