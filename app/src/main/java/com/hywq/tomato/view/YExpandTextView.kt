package com.hywq.tomato.view

import android.content.Context
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.LogUtils
import com.hywq.tomato.R
import kotlin.properties.Delegates

class YExpandTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private val TEXT_EXPAND = "展开"
    private val TEXT_CLOSE = "收起"
    private val mMaxLines = 3

    var viewHeight by Delegates.notNull<Int>()
    var maxLineHeight: Int = 0
    var expand = false

    private var originText = ""
    private var expandText: CharSequence = ""

    companion object {
        private const val ELLIPSIS_NORMAL = "\u2026" // HORIZONTAL ELLIPSIS (…)
    }

    init {
        movementMethod = LinkMovementMethod.getInstance()
    }

    override fun setText(text: CharSequence?, type: BufferType?) {
        super.setText(text, type)
        if (!text.isNullOrEmpty() && originText.isEmpty()) {
            this.originText = text.toString()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (expand) {
            return
        }
        if (maxLineHeight == measuredHeight) {
            LogUtils.d("已经是最大高度了")
            return
        }
        setExpandText(layout, mMaxLines)
    }

    private fun setExpandText(layout: Layout, maxLines: Int) {
        viewHeight = measuredHeight;
        maxLineHeight = layout.getLineTop(maxLines)
        LogUtils.d("maxLineHeight" + maxLineHeight)

        val lastLineEnd = layout.getLineEnd(maxLines - 1)
        val ellipsisText = ELLIPSIS_NORMAL + TEXT_EXPAND
        val ansIndex = lastLineEnd - ellipsisText.length

        val sp = SpannableStringBuilder(originText)
        sp.replace(ansIndex, lastLineEnd, ellipsisText)
        sp.setSpan(TDefaultClickSpan(), ansIndex, lastLineEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        expandText = sp
        text = sp
        setMeasuredDimension(measuredWidth, maxLineHeight)
    }

    inner class TDefaultClickSpan : ClickableSpan() {
        override fun onClick(widget: View) {
            if (expand) {
                expand = false
                LogUtils.d("去折叠1")
                text = ""
                text = expandText
                setMeasuredDimension(measuredWidth, maxLineHeight)
            } else {
                LogUtils.d("去展开")
                expand = true
                val sp = SpannableStringBuilder(originText)
                val start = sp.length
                sp.append(TEXT_CLOSE)
                sp.setSpan(
                    TDefaultClickSpan(),
                    start,
                    sp.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                text = sp
                setMeasuredDimension(measuredWidth, viewHeight)
            }
        }

        override fun updateDrawState(ds: TextPaint) {
            ds.color = ColorUtils.getColor(R.color.red)
        }
    }

}