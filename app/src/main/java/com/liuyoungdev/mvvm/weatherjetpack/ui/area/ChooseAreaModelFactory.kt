package com.liuyoungdev.mvvm.weatherjetpack.ui.area

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.liuyoungdev.mvvm.weatherjetpack.data.PlaceRepository

class ChooseAreaModelFactory(private val repository: PlaceRepository) : ViewModelProvider.NewInstanceFactory(){


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChooseAreaFragmentModel(repository) as T
    }
}
