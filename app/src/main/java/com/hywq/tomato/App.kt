package com.hywq.tomato

import com.hywq.tomato.base.BaseApplication
import com.hywq.tomato.umsdk.UmManager

class App : BaseApplication(){

    override fun onCreate() {
        super.onCreate()
        UmManager.preInit(this)
    }
}