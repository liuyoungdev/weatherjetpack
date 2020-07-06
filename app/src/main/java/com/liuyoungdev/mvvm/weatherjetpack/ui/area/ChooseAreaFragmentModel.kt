package com.liuyoungdev.mvvm.weatherjetpack.ui.area

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liuyoungdev.mvvm.weatherjetpack.WeatherJetpackApplication
import com.liuyoungdev.mvvm.weatherjetpack.data.PlaceRepository
import com.liuyoungdev.mvvm.weatherjetpack.data.model.area.Province
import com.liuyoungdev.mvvm.weatherjetpack.ui.area.ChooseAreaFragment.Companion.LEVEL_PROVINCES
import kotlinx.coroutines.launch

/**
 * author： yang
 * date  ： 2020-06-29
 */
class ChooseAreaFragmentModel(private val repository: PlaceRepository) : ViewModel() {
    var dataChanged = MutableLiveData<Int>()
    var currentLevel = MutableLiveData<Int>()
    var isLoading = MutableLiveData<Boolean>()

    lateinit var provinces: MutableList<Province>
    val dataList = ArrayList<String>()

    fun getProvinces() {
        currentLevel.value = LEVEL_PROVINCES
        launch {
            provinces = repository.getProvinceList()
            dataList.addAll(provinces.map { it.provinceName })
        }


    }

    private fun launch(block: suspend () -> Unit) = viewModelScope.launch {
        try {
            isLoading.value = true
            dataList.clear()
            block
            dataChanged.value = dataChanged.value?.plus(1)
            isLoading.value = false
        } catch (t: Throwable) {
            t.printStackTrace()
            Toast.makeText(WeatherJetpackApplication.context, t.message, Toast.LENGTH_SHORT).show()
            isLoading.value = false
            dataChanged.value = dataChanged.value?.plus(1)
        }
    }
}
