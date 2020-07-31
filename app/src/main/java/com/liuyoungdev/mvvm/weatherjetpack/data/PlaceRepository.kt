package com.liuyoungdev.mvvm.weatherjetpack.data

import com.liuyoungdev.mvvm.weatherjetpack.data.db.PlaceDao
import com.liuyoungdev.mvvm.weatherjetpack.data.network.WeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlaceRepository private constructor(
    private val placeDao: PlaceDao,
    private val network: WeatherNetwork
) {

    //    调用withContext()函数之后，会立即执行代码块中的代码，同时将当前协程阻塞住，
    suspend fun getProvinceList() = withContext(Dispatchers.IO) {
        var list = placeDao.getProvinceList()
        println("list的size" + list.size)
        if (list.isEmpty()) {
            println("list的size" + list.size)
            list = network.fetchProvinceList()
            placeDao.saveProvinceList(list)
        }
        //当代码块中的代码执行完以后，会将最后一行的执行结果作为withContext()函数的返回值返回。
        list

    }

    suspend fun getCitiesList(provinceCode: Int) = withContext(Dispatchers.IO) {
        var list = placeDao.getCityData(provinceCode)
        if (list.isEmpty()) {
            list = network.fetchCityList(provinceCode)
            placeDao.saveCities(list)

        }
        list

    }

    suspend fun getCounties(provinceCode: Int, cityCode: Int) = withContext(Dispatchers.IO) {
        var list = placeDao.getCounties(cityCode)
        if (list.isEmpty()) {
            list = network.getCounties(provinceCode, cityCode)
            placeDao.saveCounties(list)
        }
        list


    }

    companion object {
        private var instance: PlaceRepository? = null
        fun getInstance(placeDao: PlaceDao, network: WeatherNetwork): PlaceRepository {
            if (instance == null) {
                synchronized(PlaceRepository::class.java) {
                    if (instance == null) {
                        instance = PlaceRepository(placeDao, network)
                    }
                }
            }
            return instance!!
        }
    }
}
