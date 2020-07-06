package com.liuyoungdev.mvvm.weatherjetpack.ui.area

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

class ChooseAreaAdapter(context:Context,private val id:Int,private val dataList:List<String>) : ArrayAdapter<String>(context,id,dataList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return super.getView(position, convertView, parent)
    }

}
