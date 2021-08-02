package com.hywq.tomato.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blankj.utilcode.util.ThreadUtils
import com.hywq.tomato.R

class SplashActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        ThreadUtils.runOnUiThreadDelayed({
            MainActivity.start()
        },800)
    }
}