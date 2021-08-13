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
import com.hywq.tomato.view.ExpandableTextView.LinkTouchMovementMethod

class TExpandTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private val TEXT_EXPAND = "展开"
    private val TEXT_CLOSE = "收起"
    private val mMaxLines = 3
    private var originText = ""

    init {
    }

    override fun setText(text: CharSequence?, type: BufferType?) {
        super.setText(text, type)
        if (!text.isNullOrEmpty()) {
            this.originText = text.toString()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val layout = layout
        val lineCount = layout.lineCount
        val maxLines = maxLines
        val text = text
        if (maxLines in 2 until lineCount && !TextUtils.isEmpty(text)) {
            setExpandText(layout, maxLines, text)
        }
    }

    private fun setExpandText(layout: Layout, maxLines: Int, text: CharSequence) {
        var curtText = text
        val end = getLastLineEnd(curtText, layout, maxLines, ELLIPSIS_NORMAL + TEXT_EXPAND)
        curtText = curtText.subSequence(0, end)
        val ans = SpannableStringBuilder(curtText)
        ans.append(ELLIPSIS_NORMAL)
        val start = ans.length
        ans.append(TEXT_EXPAND)
        ans.setSpan(TDefaultClickSpan(), start, ans.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        setText(ans)
        movementMethod = LinkMovementMethod.getInstance()
    }

    private fun getLastLineEnd(
        curtText: CharSequence,
        layout: Layout,
        maxLines: Int,
        ellipsisText: String
    ): Int {
        val lastLineEnd = layout.getLineEnd(maxLines - 1)
        val ellipsisWidth = paint.measureText(ellipsisText)
        var ansIndex = lastLineEnd - ellipsisText.length
        var expandTextWidth = 0f
        LogUtils.d("ansIndex.= " + ansIndex)
        while (expandTextWidth < ellipsisWidth) {
            expandTextWidth = paint.measureText(curtText.substring(ansIndex, lastLineEnd))
            ansIndex--
            LogUtils.d("ansIndex= " + ansIndex)
        }
        return ansIndex
    }

    inner class TDefaultClickSpan : ClickableSpan() {
        override fun onClick(widget: View) {
            LogUtils.d("去展开.height: "+height)
            maxLines = Int.MAX_VALUE
            setText(originText)
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.color = ColorUtils.getColor(R.color.red)
        }
    }

    //    @Override
    //    public void setText(CharSequence text, BufferType type) {
    //        super.setText(getNewConfigText(text), type);
    //    }
    //
    //    private CharSequence getNewConfigText(CharSequence text) {
    //
    //        return text;
    //    }
    companion object {
        private const val ELLIPSIS_NORMAL = "\u2026" // HORIZONTAL ELLIPSIS (…)
    }
}