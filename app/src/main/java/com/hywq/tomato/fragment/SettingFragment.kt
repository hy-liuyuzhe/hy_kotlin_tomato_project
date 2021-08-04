package com.hywq.tomato.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hywq.tomato.R
import com.hywq.tomato.view.ExpandTextView
import com.hywq.tomato.view.ExpandableTextView

class SettingFragment : Fragment() {

    var showContent = "图标头部显示引导/打开登陆APP楼层，支持点击关闭；2）去除底部“参与话题”浮层，" +
            "改为“参与”btn，点击，引导登陆/打开APP，在App登陆后跳转至该页面基于“买家主页”逻辑，" +
            "新增“达人主页”，涉及1）头部信息样式改造，去掉“加入天数”字段；2）加“达人”标；" +
            "3）优化“个人简介”字段位置布局；4）列表标题为“全部推荐"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val expandText = view.findViewById<ExpandableTextView>(R.id.expandText)
//        showContent =
//            "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
        expandText.text = showContent
    }
}