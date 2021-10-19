package com.hywq.tomato.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.RoundedCornerTreatment;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.hywq.tomato.R;

/**
 * @author ymatou
 */
public class CarbonLayout extends LinearLayout implements ShadowView{

    private final int shadowColor;
    public static final boolean IS_PIE_OR_HIGHER = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P;
    private ColorStateList shadowColorStateList;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private MaterialShapeDrawable shadowDrawable;

    public CarbonLayout(Context context) {
        this(context, null);
    }

    public CarbonLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarbonLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        shadowColor = ColorUtils.getColor(R.color.red);
        shadowColorStateList = ColorStateList.valueOf(shadowColor);
        paint.setColor(ColorUtils.getColor(R.color.grade_blue));

        ShapeAppearanceModel.Builder builder = ShapeAppearanceModel.builder().setAllCornerSizes(SizeUtils.dp2px(17));
        shadowDrawable = new MaterialShapeDrawable(builder.build());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("liuyuzhe", "onMeasure: ");

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("liuyuzhe", "onLayout: ");
        if (getWidth() == 0 || !changed) return;
        setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                Log.d("liuyuzhe", "getOutline: ");
                shadowDrawable.setBounds(new Rect(0, 0, getWidth(), getHeight()));
                shadowDrawable.setShadowCompatibilityMode(MaterialShapeDrawable.SHADOW_COMPAT_MODE_NEVER);
                shadowDrawable.getOutline(outline);
            }
        });
        setClipToOutline(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("liuyuzhe", "onDraw: ");
        drawLayout(canvas, "onDraw: 高");
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        if (child instanceof ShadowView && !IS_PIE_OR_HIGHER && shadowColorStateList != null) {
            ((ShadowView) child).drawShadow(canvas);
        }

        return super.drawChild(canvas, child, drawingTime);
    }

    @Override
    public int getOutlineAmbientShadowColor() {
        return shadowColorStateList.getDefaultColor();
    }

    @Override
    public void setOutlineSpotShadowColor(int color) {
        super.setOutlineSpotShadowColor(color);
    }

    private void drawLayout(Canvas canvas, String s) {
        if (IS_PIE_OR_HIGHER) {
            Log.d("liuyuzhe", s);
            super.setOutlineSpotShadowColor(shadowColorStateList.getColorForState(getDrawableState(), shadowColorStateList.getDefaultColor()));
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.d("liuyuzhe", "dispatchDraw: ");
        drawLayout(canvas, "dispatchDraw: 高");
    }

    @Override
    public void setElevation(float elevation) {
        if (IS_PIE_OR_HIGHER) {
            super.setElevation(elevation);
        }else{
            super.setElevation(0);
            super.setTranslationZ(0);
        }
        Log.d("liuyuzhe", "setElevation: " + elevation);
    }

    @Override
    public void drawShadow(Canvas canvas) {
//        int count = canvas.save();
        float z = getElevation() + getTranslationZ();
        Log.d("liuyuzhe", "drawShadow: "+z);
        shadowDrawable.setFillColor(shadowColorStateList);
        shadowDrawable.setShadowColor(shadowColorStateList.getColorForState(getDrawableState(),shadowColorStateList.getDefaultColor()));
        shadowDrawable.setBounds(getLeft(), (int) (getTop() + z / 4), getRight(), (int) (getBottom() + z / 4));
        shadowDrawable.setShadowCompatibilityMode(MaterialShapeDrawable.SHADOW_COMPAT_MODE_ALWAYS);
        shadowDrawable.setElevation(z);
        shadowDrawable.draw(canvas);
//        canvas.translate(this.getLeft(), this.getTop());
//        canvas.restoreToCount(count);
    }
}