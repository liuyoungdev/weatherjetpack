package com.liuyoungdev.mvvm.weatherjetpack.ui.weather

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.liuyoungdev.mvvm.weatherjetpack.R

class WeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_weather)
    }

}
