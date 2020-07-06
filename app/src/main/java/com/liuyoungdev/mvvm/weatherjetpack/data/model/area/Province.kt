package com.liuyoungdev.mvvm.weatherjetpack.data.model.area

import com.google.gson.annotations.SerializedName
import org.litepal.LitePal
import org.litepal.crud.LitePalSupport

class Province(@SerializedName("name") val provinceName:String,@SerializedName("id") val provinceCode: Int) : LitePalSupport() {
}
