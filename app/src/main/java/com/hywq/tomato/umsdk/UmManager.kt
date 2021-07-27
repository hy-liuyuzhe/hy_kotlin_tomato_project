package com.hywq.tomato.umsdk

import android.app.Activity
import android.content.Context
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.Utils
import com.hywq.tomato.BuildConfig
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure

//

/**
 *
统一对各个业务进行初始化接口
//1.隐私合规中加入友盟+SDK合规声明https://developer.umeng.com/docs/147377/detail/213789

//2.在Application.oncreate()中调用预初始化函数
//preInit预初始化函数耗时极少，不会影响App首次冷启动用户体验，不会采集设备信息，也不会向友盟后台上报数据。
public static void preInit(Context context, String appkey, String channel)

//3.客户端用户同意隐私政策后，正式初始化友盟+SDK
UMConfigure.init(Context context, String appkey, String channel, int deviceType, String pushSecret);
2.统计SDK基础统计指标自动采集
//选择AUTO页面采集模式，统计SDK基础指标无需手动埋点可自动采集。
//建议在宿主App的Application.onCreate函数中调用此函数。

 */

object UmManager {
    private const val appKey = "60ff4bf7ff4d74541c845b0e";

    fun onResume(c: Context) {
        MobclickAgent.onResume(c)
    }

    fun onPause(c: Context) {
        MobclickAgent.onPause(c)
    }

    fun preInit(c: Context) {
        UMConfigure.preInit(c, appKey, "")
        UMConfigure.setLogEnabled(BuildConfig.DEBUG)
        ActivityUtils.addActivityLifecycleCallbacks(object : Utils.ActivityLifecycleCallbacks() {
            override fun onActivityResumed(activity: Activity) {
                onResume(activity)
            }

            override fun onActivityPaused(activity: Activity) {
                onPause(activity)
            }
        })
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
    }

    fun init(c: Context) {
        UMConfigure.init(c, appKey, "", 0, "")
    }

}