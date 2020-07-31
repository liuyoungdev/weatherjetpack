package com.liuyoungdev.mvvm.weatherjetpack.data.db

import com.liuyoungdev.mvvm.weatherjetpack.data.model.area.City
import com.liuyoungdev.mvvm.weatherjetpack.data.model.area.County
import com.liuyoungdev.mvvm.weatherjetpack.data.model.area.Province
import org.litepal.LitePal

class PlaceDao {

    fun getProvinceList(): MutableList<Province> = LitePal.findAll(Province::class.java)
    fun saveProvinceList(list: MutableList<Province>) {
        LitePal.saveAll(list)
    }

    fun getCityData(provinceCode:Int) :MutableList<City> = LitePal.where("provinceId = ?",provinceCode.toString()).find(City::class.java)
    fun saveCities(list: MutableList<City>) {
        LitePal.saveAll(list)
    }

    fun getCounties(cityCode: Int): MutableList<County> = LitePal.where("cityId = ?",cityCode.toString()).find(County::class.java)
    fun saveCounties(list: MutableList<County>) {

        LitePal.saveAll(list)
    }


}
