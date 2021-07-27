package com.hywq.tomato

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hywq.tomato.umsdk.UmManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        UmManager.init(application)
    }
}