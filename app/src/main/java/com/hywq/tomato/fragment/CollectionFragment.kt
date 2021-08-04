package com.hywq.tomato.fragment

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.SizeUtils
import com.hywq.tomato.R
import com.hywq.tomato.view.ExpandTextView
import com.hywq.tomato.view.ImageLabelSpan


class CollectionFragment : Fragment() {

    lateinit var ftvContent: TextView
    var showContent = "图标头部显示引导/打开登陆APP楼层，支持点击关闭；2）去除底部“参与话题”浮层，" +
            "改为“参与”btn，点击，引导登陆/打开APP，在App登陆后跳转至该页面基于“买家主页”逻辑，" +
            "新增“达人主页”，涉及1）头部信息样式改造，去掉“加入天数”字段；2）加“达人”标；" +
            "3）优化“个人简介”字段位置布局；4）列表标题为“全部推荐"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_collection, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ftvContent = view.findViewById<ExpandTextView>(R.id.tv_activity_desc)
//        showContent = "移除它登陆APP楼层打开登陆APP楼层打开登陆APP楼层打开登陆APP楼层打开登陆APP楼层打开登陆APP楼层打开登陆APP楼层打开登陆APP楼层打开登陆APP楼层打开登陆APP楼层打开登陆APP楼层打开登陆123"
//        showContent = "83838386838383838383838683838383838383838383838383838383838383838383838388383838383838383838383838383838838338888888888888888888888888888888888855555555555555555555555555555555595838383838383838383882828283838303828285828282020205858605858285850505858"
        ftvContent.maxLines = 3
        ftvContent.text = showContent

        val ellipsizeStr =
            TextUtils.ellipsize(ftvContent.text, ftvContent.paint,
                ScreenUtils.getAppScreenWidth().toFloat()*3,
                TextUtils.TruncateAt.END
            )
        LogUtils.d("ellipsizeStr: "+ellipsizeStr)

//        appendPrefixIconContentText()
//        val c = ftvContent.text.toString()
//        LogUtils.d("content: "+c)
//        showContent.substring(2,f)
    }

    private fun appendPrefixIconContentText() {
        if(!ftvContent.text.contains("图标"))return
        val spanBuilder = SpannableStringBuilder(ftvContent.text)
        val prefix = ImageLabelSpan(context, R.drawable.hot_card_talent_icon)
            .margin(0, 5)
            .ratio(1.7f)
        spanBuilder.setSpan(prefix,0,2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ftvContent.text = spanBuilder
    }
}