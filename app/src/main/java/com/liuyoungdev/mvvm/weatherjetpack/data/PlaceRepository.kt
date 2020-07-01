package com.liuyoungdev.mvvm.weatherjetpack.data

import com.liuyoungdev.mvvm.weatherjetpack.data.db.PlaceDao
import com.liuyoungdev.mvvm.weatherjetpack.data.network.WeatherNetwork

class PlaceRepository private constructor(private val placeDao: PlaceDao,private val network:WeatherNetwork) {
    suspend fun getProvinceList() {
        val list = placeDao.getProvinceList()
        if (list == null) {
            network.getProvinceList()
        }

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
