package com.hywq.tomato.view;

import android.content.Context;
import android.text.Html;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.hywq.tomato.R;


public class TExpandTextView extends AppCompatTextView {

    private String TEXT_EXPAND = "展开";
    private String TEXT_CLOSE = " ";
    private int mMaxLines = 3;

    public TExpandTextView(Context context) {
        super(context);
        init();
    }

    public TExpandTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TExpandTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }


    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(getNewConfigText(text), type);
    }

    private CharSequence getNewConfigText(CharSequence text) {

        return text;
    }
}