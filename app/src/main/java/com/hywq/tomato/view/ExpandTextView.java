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


public class ExpandTextView extends AppCompatTextView {

    private String originText;// 原始内容文本
    private int initWidth = 0;// TextView可展示宽度
    private int mMaxLines = 6;// TextView最大行数
    private SpannableString SPAN_CLOSE = null;// 收起的文案(颜色处理)
    private SpannableString SPAN_EXPAND = null;// 展开的文案(颜色处理)
    private String TEXT_EXPAND = "展开";
    private String TEXT_CLOSE = " ";
    public boolean isExpand = false;
    private boolean appendShowAll = false;// true 不需要展开收起功能， false 需要展开收起功能
    public boolean haveFull = true;

    public ExpandTextView(Context context) {
        super(context);
        initCloseEnd();
    }

    public ExpandTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCloseEnd();
    }

    public ExpandTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCloseEnd();
    }


    @Override
    public void setMaxLines(int maxLines) {
        this.mMaxLines = maxLines;
        super.setMaxLines(maxLines);
    }

    /**
     * 初始化TextView的可展示宽度
     */
    public void initWidth(int width) {
        initWidth = width;
    }

    /**
     * 收起的文案(颜色处理)初始化
     */
    private void initCloseEnd() {
        String content = TEXT_EXPAND;
        SPAN_CLOSE = new SpannableString(content);
        SPAN_CLOSE.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), content.length() - 2, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体
        ButtonSpan span = new ButtonSpan(getContext(), new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        }, R.color.red);
        SPAN_CLOSE.setSpan(span, 0, content.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    /**
     * 展开的文案(颜色处理)初始化
     */
    private void initExpandEnd() {
        String content = TEXT_CLOSE;
        SPAN_EXPAND = new SpannableString(content);
        ButtonSpan span = new ButtonSpan(getContext(), new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        }, R.color.color_999999);
        SPAN_EXPAND.setSpan(span, 0, content.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }

    public void setCloseText(CharSequence text) {
        if (SPAN_CLOSE == null) {
            initCloseEnd();
        }
        appendShowAll = false;
        originText = text.toString();
        // SDK >= 16 可以直接从xml属性获取最大行数
        int maxLines = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            maxLines = getMaxLines();
        } else {
            maxLines = mMaxLines;
        }
        String workingText = new StringBuilder(originText).toString();
        if (maxLines != -1) {
            Layout layout = createWorkingLayout(workingText);
            if (layout.getLineCount() > maxLines) {
                haveFull = true;
                //获取一行显示字符个数，然后截取字符串数
                int offset = layout.getLineEnd(maxLines - 1);
                workingText = originText.substring(0, offset).trim();// 收起状态原始文本截取展示的部分
                String showText = originText.substring(0, offset).trim() + "..." + SPAN_CLOSE;
                Layout layout2 = createWorkingLayout(showText);
                // 对workingText进行-1截取，直到展示行数==最大行数，并且添加 SPAN_CLOSE 后刚好占满最后一行
                int tmp = 0;
                while (layout2.getLineCount() > maxLines) {
                    int lastSpace = workingText.length() - 1;
                    if (lastSpace == -1) {
                        break;
                    }
                    tmp = lastSpace;
                    workingText = workingText.substring(0, lastSpace);
                    layout2 = createWorkingLayout(workingText + "..." + SPAN_CLOSE);
                }
                appendShowAll = true;
                workingText = workingText.substring(0, tmp - 22);
                workingText = workingText + "...";
            } else {
                haveFull = false;
            }
        }
//        setText(Html.fromHtml(parseContent(workingText)));
        setText(workingText);
        if (appendShowAll) {
            // 必须使用append，不能在上面使用+连接，否则spannable会无效
            append(SPAN_CLOSE);
            setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    private String parseContent(String content) {
        if (!TextUtils.isEmpty(content)) {
            content = content.replace("\n", "<br>");
        }
        return content;
    }


    public void setExpandText(String text) {
        /*if (SPAN_EXPAND == null) {
            initExpandEnd();
        }*/
        originText = text;
        Layout layout1 = createWorkingLayout(text);
        Layout layout2 = createWorkingLayout(text + TEXT_CLOSE);
        // 展示全部原始内容时 如果 TEXT_CLOSE 需要换行才能显示完整，则直接将TEXT_CLOSE展示在下一行
        if (layout2.getLineCount() > layout1.getLineCount()) {
            setText(Html.fromHtml(parseContent(originText)));
        } else {
            setText(Html.fromHtml(parseContent(originText)));
        }
        // append(SPAN_EXPAND);
        setMovementMethod(LinkMovementMethod.getInstance());
    }

    //返回textview的显示区域的layout，该textview的layout并不会显示出来，只是用其宽度来比较要显示的文字是否过长
    private Layout createWorkingLayout(String workingText) {
        return new StaticLayout(workingText, getPaint(), initWidth - getPaddingLeft() - getPaddingRight(),
                Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return new StaticLayout(workingText, getPaint(), initWidth - getPaddingLeft() - getPaddingRight(),
                    Layout.Alignment.ALIGN_NORMAL, getLineSpacingMultiplier(), getLineSpacingExtra(), false);
        } else {
            return new StaticLayout(workingText, getPaint(), initWidth - getPaddingLeft() - getPaddingRight(),
                    Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        }*/
    }
}