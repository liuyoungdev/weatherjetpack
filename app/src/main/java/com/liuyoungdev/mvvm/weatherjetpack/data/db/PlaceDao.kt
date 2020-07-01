package com.liuyoungdev.mvvm.weatherjetpack.data.db

import com.liuyoungdev.mvvm.weatherjetpack.data.model.area.Province
import org.litepal.LitePal

class PlaceDao {

    fun getProvinceList(): MutableList<Province> = LitePal.findAll(Province::class.java)


}
