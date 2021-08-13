package com.hywq.tomato.activity

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.JsonUtils
import com.blankj.utilcode.util.LogUtils
import com.hywq.tomato.R
import com.hywq.tomato.adapter.CommonFragmentAdapter
import com.hywq.tomato.databinding.ActivityMainBinding
import com.hywq.tomato.enum.HomeTabEnum
import com.hywq.tomato.umsdk.UmManager

class MainActivity : AppCompatActivity() {

    companion object {
        fun start() {
            ActivityUtils.startActivity(MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        LogUtils.d("main")
        UmManager.init(application)

        val fragments = ArrayList<Fragment>()

        HomeTabEnum.values().forEach {
            fragments.add(it.clazz.newInstance())
        }
        binding.viewPager.adapter = CommonFragmentAdapter(this, fragments)

        binding.expandableBottomBar.onItemSelectedListener = { v, menuItem, byUser ->
            HomeTabEnum.values().find { menuItem.id == it.id }?.ordinal?.also { index ->
                binding.viewPager.setCurrentItem(index, false)
            }
        }
    }

    override fun onBackPressed() {
        LogUtils.d("onBackPressed")
        super.onBackPressed()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_BACK){
//            LogUtils.d("onKeyDown")
//            return false
//        }
        return super.onKeyDown(keyCode, event)
    }


}