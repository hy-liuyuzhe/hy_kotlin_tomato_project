package com.hywq.tomato.fragment

import android.os.Build
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.SizeUtils
import com.hywq.tomato.R
import com.hywq.tomato.view.*

class SettingFragment : Fragment() {

    var showContent = "图标头部显示引导/打开登陆APP楼层，支持点击关闭；2）去除底部“参与话题”浮层，" +
            "改为“参与”btn，点击，引导登陆/打开APP，在App登陆后跳转至该页面基于“买家主页”逻辑，" +
            "新增“达人主页”，涉及1）头部信息样式改造，去掉“加入天数”字段；2）加“达人”标；" +
            "3）优化“个人简介”字段位置布局；4）列表标题为“全部推荐"

    lateinit var finalTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting, null, false)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val expandText = view.findViewById<ExpandableTextView>(R.id.expandText)
        val staticLayout = view.findViewById<StaticLayoutView>(R.id.staticLayout)
        finalTextView = view.findViewById<TextView>(R.id.finalTextView)

        showContent =
            "图标12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"

        val spanBuilder = SpannableStringBuilder(showContent)

        val prefix = CenterImageSpan(context, R.drawable.hot_card_talent_icon)
        spanBuilder.setSpan(prefix, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        expandText.text = SpannableStringBuilder(spanBuilder)

        val textPaint = TextPaint().apply {
            textSize = SizeUtils.sp2px(14f).toFloat()
        }
        val builder = StaticLayout.Builder.obtain(
            spanBuilder, 0, spanBuilder.length,
            textPaint, ScreenUtils.getScreenWidth()
        ).setMaxLines(3).setEllipsize(TextUtils.TruncateAt.END)
            .setBreakStrategy(Layout.BREAK_STRATEGY_BALANCED)
        val layout = builder
//            .setEllipsizedWidth(ScreenUtils.getScreenWidth())
            .build()
        staticLayout.setLayout(layout)
        LogUtils.d("text: ${layout.text}")

        val spannableStringBuilder = SpannableStringBuilder(layout.text);
        val start = spannableStringBuilder.length
        spannableStringBuilder.append("展开")
        spannableStringBuilder.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                LogUtils.d("click")
                handleClickable(staticLayout, spanBuilder, textPaint)
            }
        }, start, spannableStringBuilder.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        finalTextView.text = spannableStringBuilder
        finalTextView.setMovementMethod(LinkMovementMethod.getInstance())
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun handleClickable(
        staticLayout: StaticLayoutView,
        spanBuilder: SpannableStringBuilder,
        textPaint: TextPaint
    ) {
        val build = StaticLayout.Builder.obtain(
            spanBuilder, 0, spanBuilder.length,
            textPaint, ScreenUtils.getScreenWidth()
        ).setEllipsize(TextUtils.TruncateAt.END)
            .setBreakStrategy(Layout.BREAK_STRATEGY_BALANCED)
            .setMaxLines(Int.MAX_VALUE).build()

        finalTextView.text = build.text
    }
}