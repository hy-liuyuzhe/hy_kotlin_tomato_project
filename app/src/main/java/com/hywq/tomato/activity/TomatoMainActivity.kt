package com.hywq.tomato.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.LogUtils
import com.hywq.tomato.R
import com.hywq.tomato.adapter.CommonFragmentAdapter
import com.hywq.tomato.databinding.ActivityMainTomatoBinding
import com.hywq.tomato.enum.HomeTabEnum
import com.hywq.tomato.umsdk.UmManager

class TomatoMainActivity : AppCompatActivity() {

    companion object {
        fun start() {
            ActivityUtils.startActivity(TomatoMainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainTomatoBinding>(this, R.layout.activity_main_tomato)
        LogUtils.d("main")
        UmManager.init(application)

        val fragments = ArrayList<Fragment>()

        HomeTabEnum.values().forEach {
            fragments.add(it.clazz.newInstance())
        }
        binding.viewPager.adapter = CommonFragmentAdapter(this, fragments)
        binding.viewPager.setUserInputEnabled(false)
        binding.viewPager.offscreenPageLimit = fragments.size
        binding.expandableBottomBar.onItemSelectedListener = { v, menuItem, byUser ->
            HomeTabEnum.values().find { menuItem.id == it.id }?.ordinal?.also { index ->
                binding.viewPager.setCurrentItem(index, false)
            }
        }
    }
}