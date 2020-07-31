package com.liuyoungdev.mvvm.weatherjetpack.ui.area

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.liuyoungdev.mvvm.weatherjetpack.databinding.SimpleItemBinding

class ChooseAreaAdapter(context: Context, private val id: Int, private val dataList: List<String>) :
    ArrayAdapter<String>(context, id, dataList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val bind: SimpleItemBinding?
        val view = if (convertView == null) {
            val convertView = LayoutInflater.from(context).inflate(id, parent, false)
            bind = DataBindingUtil.bind(convertView)
            convertView.tag = bind
            convertView
        } else {
            bind = convertView.tag as SimpleItemBinding
            convertView
        }
        bind?.data = dataList[position]
        return view
    }

}
