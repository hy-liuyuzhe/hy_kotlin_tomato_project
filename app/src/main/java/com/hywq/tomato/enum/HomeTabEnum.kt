package com.hywq.tomato.enum

import androidx.fragment.app.Fragment
import com.hywq.tomato.R
import com.hywq.tomato.fragment.HomeFragment
import com.hywq.tomato.fragment.SettingFragment

enum class HomeTabEnum(val id: Int, val clazz: Class<out Fragment>) {
    home(R.id.icon_home, HomeFragment::class.java),
    collection(R.id.icon_collection, HomeFragment::class.java),
    analytice(R.id.icon_analytics, HomeFragment::class.java),
    setting(R.id.icon_settings, SettingFragment::class.java),
}