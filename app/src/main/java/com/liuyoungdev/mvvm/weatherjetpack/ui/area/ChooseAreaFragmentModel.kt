package com.liuyoungdev.mvvm.weatherjetpack.ui.area

import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liuyoungdev.mvvm.weatherjetpack.WeatherJetpackApplication
import com.liuyoungdev.mvvm.weatherjetpack.data.PlaceRepository
import com.liuyoungdev.mvvm.weatherjetpack.data.model.area.City
import com.liuyoungdev.mvvm.weatherjetpack.data.model.area.County
import com.liuyoungdev.mvvm.weatherjetpack.data.model.area.Province
import com.liuyoungdev.mvvm.weatherjetpack.ui.area.ChooseAreaFragment.Companion.LEVEL_CITY
import com.liuyoungdev.mvvm.weatherjetpack.ui.area.ChooseAreaFragment.Companion.LEVEL_COUNTY
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
    var areaSelected = MutableLiveData<Boolean>()
    var selectProvince: Province? = null
    var selectCity: City? = null
    var selectCounty: County? = null

    lateinit var provinces: MutableList<Province>
    lateinit var cities: MutableList<City>
    lateinit var counties: MutableList<County>
    val dataList = ArrayList<String>()

    fun getProvinces() {
        currentLevel.value = LEVEL_PROVINCES
        launch {
            provinces = repository.getProvinceList()
            dataList.addAll(provinces.map { it.provinceName })
        }


    }

    private fun getCities() = selectProvince?.let {
        currentLevel.value = LEVEL_CITY
        launch {
            cities = repository.getCitiesList(it.provinceCode)
            dataList.addAll(cities.map { it.cityName })
        }

    }

    private fun getCounty() = selectCity?.let {
        currentLevel.value = LEVEL_COUNTY
        launch {
            counties = repository.getCounties(it.provinceId, it.cityCode)
            dataList.addAll(counties.map { it.countyName })
        }


    }

    fun onBack() {
        if (currentLevel.value == LEVEL_CITY) {
            getProvinces()
        } else if (currentLevel.value == LEVEL_COUNTY)
            getCities()
    }

    fun onItemClickListener(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        when {
            currentLevel.value == LEVEL_PROVINCES -> {
                selectProvince = provinces.get(position)
                getCities()
            }
            currentLevel.value == LEVEL_CITY -> {
                selectCity = cities.get(position)
                getCounty()
            }
            currentLevel.value == LEVEL_COUNTY -> {
                selectCounty = counties[position]
                areaSelected.value = true
            }

        }

    }


    private fun launch(block: suspend () -> Unit) = viewModelScope.launch {
        try {
            isLoading.value = true
            dataList.clear()
            block()
            dataChanged.value = dataChanged.value?.plus(1)
            isLoading.value = false
        } catch (t: Throwable) {
            t.printStackTrace()
            Toast.makeText(WeatherJetpackApplication.context, t.message, Toast.LENGTH_SHORT).show()
            dataChanged.value = dataChanged.value?.plus(1)
            isLoading.value = false
        }
    }


}
