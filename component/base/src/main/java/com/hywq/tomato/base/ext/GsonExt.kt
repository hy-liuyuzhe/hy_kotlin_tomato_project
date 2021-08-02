package com.hywq.tomato.base.ext

import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils

fun Any?.toJson():String{
    try {
        return GsonUtils.toJson(this)
    } catch (e: Exception) {
        LogUtils.d(e.message)
        return "";
    }
}