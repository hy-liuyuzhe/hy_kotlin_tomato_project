package com.hywq.tomato.view;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;

/**
 * Create by wangzheng on 2016/3/18
 */
public class SpanBuilder extends SpannableStringBuilder {

    public static SpanBuilder apply(CharSequence text, Object... spans) {
        return new SpanBuilder().append(text, spans);
    }

    @Override
    public SpanBuilder append(CharSequence text) {
        if (!TextUtils.isEmpty(text)) {
            super.append(text);
        }
        return this;
    }

    public SpanBuilder append(CharSequence text, Object... spans) {
        if (!TextUtils.isEmpty(text)) {
//            this.append(text);
            for (Object span : spans) {
                setSpan(span, length() - text.length(), length());
            }
        }
        return this;
    }

    private void setSpan(Object span, int start, int end) {
        if (span == null) return;
        setSpan(span, start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public boolean isEmpty() {
        return length() <= 0;
    }
}