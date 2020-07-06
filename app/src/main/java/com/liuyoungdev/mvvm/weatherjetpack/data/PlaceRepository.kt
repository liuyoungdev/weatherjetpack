package com.liuyoungdev.mvvm.weatherjetpack.data

import com.liuyoungdev.mvvm.weatherjetpack.data.db.PlaceDao
import com.liuyoungdev.mvvm.weatherjetpack.data.network.WeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlaceRepository private constructor(private val placeDao: PlaceDao,private val network:WeatherNetwork) {

    suspend fun getProvinceList() = withContext(Dispatchers.IO) {
        var list = placeDao.getProvinceList()
        if (list == null) {
            list = network.getProvinceList()
            placeDao.saveProvinceList(list)
        }
        list

    }

    companion object {
        private var instance: PlaceRepository? = null
        fun getInstance(placeDao: PlaceDao,network:WeatherNetwork): PlaceRepository {
            if (instance == null) {
                synchronized(PlaceRepository::class.java) {
                    if (instance == null) {
                        instance = PlaceRepository(placeDao,network)
                    }
                }
            }
            return instance!!
        }
    }
}
