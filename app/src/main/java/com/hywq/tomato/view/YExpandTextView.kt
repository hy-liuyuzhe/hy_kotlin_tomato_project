package com.hywq.tomato.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View
import android.view.animation.Interpolator
import androidx.appcompat.widget.AppCompatTextView
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.LogUtils
import com.hywq.tomato.R
import kotlin.properties.Delegates

class YExpandTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {
    private val IDLE = 0
    private val EXPANDING = 1
    private val COLLAPSING = 2

    private var animator: ValueAnimator? = null
    private val interpolator: Interpolator = FastOutSlowInInterpolator()
    private var state = IDLE


    var viewHeight by Delegates.notNull<Int>()
    var maxLineHeight: Int = 0
    var expand = false

    private var originText = ""
    private var expandText: CharSequence = ""
    private var initializer = false
    private var expansion: Float = 0f


    var duration = 500L
    private val TEXT_EXPAND = "展开"
    private val TEXT_CLOSE = "收起"
    private val mMaxLines = 3


    companion object {
        private const val ELLIPSIS_NORMAL = "\u2026" // HORIZONTAL ELLIPSIS (…)
    }

    init {
        movementMethod = LinkMovementMethod.getInstance()
    }

    override fun scrollTo(x: Int, y: Int) {

    }

    fun setNewText(text: CharSequence?){
        this.originText = text.toString()
        initializer = false
        setText(text)
    }

    override fun setText(text: CharSequence?, type: BufferType?) {
        super.setText(text, type)
        if (!text.isNullOrEmpty() && originText.isEmpty()) {
            this.originText = text.toString()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (!initializer) {
            setExpandText(layout, mMaxLines)
        } else {
            val height = measuredHeight


            var expansionDelta = height - Math.round(height * expansion)
            if (expansion == 0f || (state == COLLAPSING && height - expansionDelta < maxLineHeight)) {
                expansionDelta = maxLineHeight
            }else if(state == EXPANDING && height - expansionDelta < maxLineHeight){
                expansionDelta = maxLineHeight
            }

            setMeasuredDimension(measuredWidth, height - expansionDelta)
        }
    }

    private fun setExpandText(layout: Layout, maxLines: Int) {
        viewHeight = measuredHeight;
        maxLineHeight = layout.getLineTop(maxLines)

        val lastLineEnd = layout.getLineEnd(maxLines - 1)
        val ellipsisText = ELLIPSIS_NORMAL + TEXT_EXPAND
        val ansIndex = lastLineEnd - ellipsisText.length

        val sp = SpannableStringBuilder(originText)
        sp.replace(ansIndex, lastLineEnd, ellipsisText)
        sp.setSpan(TDefaultClickSpan(), ansIndex, lastLineEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        expandText = sp
        initializer = true
        text = sp
        setMeasuredDimension(measuredWidth, maxLineHeight)
    }

    inner class TDefaultClickSpan : ClickableSpan() {
        override fun onClick(widget: View) {
            if (expand) {
                expand = false
                LogUtils.d("去折叠1")
                text = expandText
                setExpand(0f, true)
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
                setExpand(1f, true)
            }
        }

        override fun updateDrawState(ds: TextPaint) {
            ds.color = ColorUtils.getColor(R.color.red)
        }
    }

    private fun setExpand(expansion: Float, animate: Boolean) {
        if (animate) {
            animateSize(expansion)
        } else {
            setExpansion(expansion)
        }
    }

    private fun animateSize(targetExpansion: Float) {
        animator?.cancel()
        animator = ValueAnimator.ofFloat(expansion, targetExpansion)
            ?.also {
                it.interpolator = interpolator
                it.duration = duration
                it.addUpdateListener { setExpansion(it.animatedValue as Float) }
                it.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationStart(animation: Animator?) {
                        state = if (targetExpansion == 0f) COLLAPSING else EXPANDING
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                        state = IDLE
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        state = IDLE
                    }
                })
                it.start()
            }
    }

    private fun setExpansion(expansion: Float) {
        if (this.expansion == expansion) {
            return
        }
        this.expansion = expansion
        LogUtils.d("expansion= " + expansion)
        requestLayout()
    }

}