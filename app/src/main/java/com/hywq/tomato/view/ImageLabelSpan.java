/*
    The Android Not Open Source Project
    Copyright (c) 2014-9-6 wangzheng <iswangzheng@gmail.com>

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    @author wangzheng  DateTime 2014-9-6
 */

package com.hywq.tomato.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.text.style.ReplacementSpan;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;


public class ImageLabelSpan extends ReplacementSpan {
    private float scale;
    private float offsetY;

    private int leftMargin, rightMargin;
    private float ratio  = 1.0f;
    private float radius = 0f;

    private Drawable mDrawable;
    private Paint    mPaint;

    public static ImageLabelSpan from(Context context, int resourceId) {
        return new ImageLabelSpan(context, resourceId);
    }

    public ImageLabelSpan(Context context, int resourceId) {
        Drawable drawable = context.getResources()
                .getDrawable(resourceId);
        applyBounds(drawable);
    }


    private void applyBounds(Drawable drawable) {
        if (drawable == null) return;
        this.mDrawable = drawable;
        mDrawable.setBounds(0, 0,
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
    }

    @Override
    public int getSize(Paint paint, CharSequence text,
                       int start, int end, Paint.FontMetricsInt fm) {
        if (mDrawable == null) return 0;
        final int dwidth = mDrawable.getIntrinsicWidth();
        final int dheight = mDrawable.getIntrinsicHeight();

        Rect rect = new Rect();
        paint.getTextBounds(text.toString(), start, end, rect);

        offsetY = rect.top * (0.5f + ratio / 2);
        scale = rect.height() * 1f / dheight * ratio;

        return leftMargin + rightMargin
                + (int) (dwidth * scale);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end,
                     float x, int top, int y, int bottom, Paint paint) {
        if (mDrawable == null) return;
        int saveCount = canvas.save();
        canvas.translate(x + leftMargin, y + offsetY);
        canvas.scale(scale, scale);
        if (radius == 0) {
            mDrawable.draw(canvas);
        } else {
            updateBitmapShader();
            drawRoundDrawable(canvas);
        }
        canvas.restoreToCount(saveCount);
    }

    private void drawRoundDrawable(Canvas canvas) {
        if (mPaint == null) return;
        float r = radius / scale;
        RectF rect = new RectF(
                mDrawable.getBounds());
        canvas.drawRoundRect(rect, r, r, mPaint);
    }

    private void updateBitmapShader() {
        if (mPaint != null) return;
        Rect bounds = mDrawable.getBounds();
        Bitmap bitmap = drawableToBitmap(mDrawable,
                bounds.width(), bounds.height());
        Shader shader = new BitmapShader(bitmap,
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(shader);
    }

    //Converts a drawable to a bitmap of specified width and height.
    private Bitmap drawableToBitmap(Drawable drawable,
                                    int width,
                                    int height) {
        Bitmap bitmap = Bitmap.createBitmap(
                width, height,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }

    public ImageLabelSpan radius(float radius) {
        this.radius = SizeUtils.dp2px(radius);
        return this;
    }

    public ImageLabelSpan ratio(float ratio) {
        this.ratio = ratio;
        return this;
    }

    public ImageLabelSpan margin(int leftMargin,
                                 int rightMargin) {
        this.leftMargin = SizeUtils.dp2px(leftMargin);
        this.rightMargin = SizeUtils.dp2px(rightMargin);
        return this;
    }
}
