package com.liuyoungdev.mvvm.weatherjetpack.ui.area

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.transition.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.liuyoungdev.mvvm.weatherjetpack.R
import com.liuyoungdev.mvvm.weatherjetpack.databinding.ChooseAreaBinding
import com.liuyoungdev.mvvm.weatherjetpack.ui.MainActivity
import com.liuyoungdev.mvvm.weatherjetpack.ui.weather.WeatherActivity
import com.liuyoungdev.mvvm.weatherjetpack.util.InjectorUtil
import kotlinx.android.synthetic.main.choose_area.*
import kotlinx.android.synthetic.main.weather_activity.*

/**
 * author： yang
 * date  ： 2020-06-12
 */
class ChooseAreaFragment : Fragment() {

    private lateinit var adapter: ArrayAdapter<String>
    private var progressDialog: ProgressDialog? = null
    private val viewModel by lazy {
        ViewModelProviders.of(this, InjectorUtil.getChooseAreaModelFactory())
            .get(ChooseAreaFragmentModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.choose_area, container, false)
        val binding = DataBindingUtil.bind<ChooseAreaBinding>(view)

        binding?.viewModel = viewModel

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = ChooseAreaAdapter(context!!, R.layout.simple_item, viewModel.dataList)
        listview.adapter = adapter
        observe()

    }

    private fun observe() {

        viewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading) showProgressDialog()
            else closeProgressDialog()

        })

        viewModel.currentLevel.observe(this, Observer { level ->
            when (level) {
                LEVEL_PROVINCES -> {
                    titletext.text = "中国"
                    backButton.visibility = View.GONE
                }
                LEVEL_CITY -> {
                    titletext.text = viewModel.selectProvince?.provinceName
                    backButton.visibility = View.VISIBLE
                }
                LEVEL_COUNTY -> {
                    titletext.text = viewModel.selectCity?.cityName
                    backButton.visibility = View.VISIBLE
                }


            }

        })
        viewModel.dataChanged.observe(this, Observer {
            adapter.notifyDataSetChanged()
            listview.setSelection(0)

        })
        viewModel.areaSelected.observe(this, Observer { selected ->
            if (selected && viewModel.selectCounty != null) {
                if (activity is MainActivity) {
                    val intent = Intent(activity, WeatherActivity::class.java)
                    intent.putExtra("weatherID", viewModel.selectCounty?.weatherId)
                    startActivity(intent)
                    activity?.finish()
                } else if (activity is WeatherActivity) {
                    val weatherActivity = activity as WeatherActivity
                    weatherActivity.drawerLayout.closeDrawers()
                    weatherActivity.viewModel.weatherID = viewModel.selectCounty!!.weatherId
                    weatherActivity.viewModel.refreshWeather()
                }
                viewModel.areaSelected.value = false
            }
        })

        if (viewModel.dataList.isEmpty()) {
            viewModel.getProvinces()
        }

    }

    private fun closeProgressDialog() {
        progressDialog?.dismiss()
    }


    private fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(activity)
            progressDialog?.setMessage("正在加载...")
            progressDialog?.setCanceledOnTouchOutside(false)
        }
        progressDialog?.show()

    }

    companion object {
        const val LEVEL_PROVINCES = 0
        const val LEVEL_CITY = 1
        const val LEVEL_COUNTY = 2
    }

}