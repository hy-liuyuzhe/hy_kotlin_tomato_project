package com.hywq.tomato

import android.app.Activity
import android.app.Application
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.Utils
import com.example.base.BaseApplication
import com.hywq.tomato.umsdk.UmManager

class App : BaseApplication(){

    override fun onCreate() {
        super.onCreate()
        UmManager.preInit(this)
    }
}