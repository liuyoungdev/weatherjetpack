package com.liuyoungdev.mvvm.weatherjetpack.ui.area

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.liuyoungdev.mvvm.weatherjetpack.data.PlaceRepository
import com.liuyoungdev.mvvm.weatherjetpack.ui.area.ChooseAreaFragment.Companion.LEVEL_PROVINCES

/**
 * author： yang
 * date  ： 2020-06-29
 */
class ChooseAreaFragmentModel(private val repository: PlaceRepository) : ViewModel() {
    var currentLevel = MutableLiveData<Int>()
    var isLoading = MutableLiveData<Boolean>()

    val dataList = ArrayList<String>()

    fun getProvinces() {
        currentLevel.value = LEVEL_PROVINCES
        isLoading.value = true
        repository.getProvinceList()


    }
}
