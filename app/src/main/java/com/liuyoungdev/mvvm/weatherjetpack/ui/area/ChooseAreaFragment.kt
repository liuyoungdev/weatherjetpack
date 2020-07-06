package com.liuyoungdev.mvvm.weatherjetpack.ui.area

import android.app.ProgressDialog
import android.os.Bundle
import android.transition.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.liuyoungdev.mvvm.weatherjetpack.R
import com.liuyoungdev.mvvm.weatherjetpack.databinding.FragmentChooseareaBinding
import com.liuyoungdev.mvvm.weatherjetpack.util.InjectorUtil
import kotlinx.android.synthetic.main.fragment_choosearea.*

/**
 * author： yang
 * date  ： 2020-06-12
 */
class ChooseAreaFragment : Fragment() {

    private lateinit var adapter: ArrayAdapter<String>
    private  var progressDialog: ProgressDialog? = null
    private val viewModel by lazy {
        ViewModelProviders.of(this,InjectorUtil.getChooseAreaModelFactory()).get(ChooseAreaFragmentModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_choosearea, container, false)

        val binding = DataBindingUtil.bind<FragmentChooseareaBinding>(view)

        binding?.viewModel = viewModel

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = ChooseAreaAdapter(context!!,R.layout.simple_item,viewModel.dataList)
        listview.adapter = adapter
        observe()

    }

    private fun observe() {

        viewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading)showProgressDialog()
            else closeProgressDialog()

        })

        viewModel.currentLevel.observe(this, Observer { level->
            when(level){
                LEVEL_PROVINCES -> {
                    titletext.setText("中国")
                    backButton.visibility = View.GONE
                }
                LEVEL_CITY -> {

                }
                LEVEL_COUNTY -> {

                }


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
        if (progressDialog == null){
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