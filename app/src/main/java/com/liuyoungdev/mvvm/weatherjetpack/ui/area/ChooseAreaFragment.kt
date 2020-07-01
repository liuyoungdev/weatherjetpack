package com.liuyoungdev.mvvm.weatherjetpack.ui.area

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.liuyoungdev.mvvm.weatherjetpack.R
import com.liuyoungdev.mvvm.weatherjetpack.databinding.FragmentChooseareaBinding
import com.liuyoungdev.mvvm.weatherjetpack.util.InjectorUtil

/**
 * author： yang
 * date  ： 2020-06-12
 */
class ChooseAreaFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(this,InjectorUtil.getChooseAreaModelFactory()).get(ChooseAreaFragmentModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_choosearea, container, false)

        val binding = DataBindingUtil.bind<FragmentChooseareaBinding>(view)

        binding?.viewModel = viewModel

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        observe()

    }

    private fun observe() {

        if (viewModel.dataList.isEmpty()) {
            viewModel.getProvinces()
        }

    }

    companion object {
        const val LEVEL_PROVINCES = 0
        const val LEVEL_CITY = 1
        const val LEVEL_COUNTY = 2
    }
}