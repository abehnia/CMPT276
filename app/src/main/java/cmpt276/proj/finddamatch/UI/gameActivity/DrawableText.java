package cmpt276.proj.finddamatch.UI.gameActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Class that creates custom drawable for text provided
 */

public class DrawableText extends Drawable {
    private final String text;
    private final Paint paint;
    private int cachedRectWidth;
    private final float MAX_TEXT_SIZE = 50;
    private final float MIN_TEXT_SIZE = 10;

    public DrawableText(String text) {
        this.text = text;
        this.paint = new Paint();
        this.cachedRectWidth = -1;
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(MAX_TEXT_SIZE);
        paint.setColor(Color.WHITE);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        final Rect r = getBounds();
        int rectWidth = r.width();
        float textSize = findSize(rectWidth);
        paint.setTextSize(textSize);
        canvas.drawText(text, r.centerX(), r.centerY(), paint);
    }

    public void setTextColor(int color) {
        paint.setColor(color);
    }

    private float findSize(int rectWidth) {
        if (cachedRectWidth == rectWidth) {
            return paint.getTextSize();
        }
        cachedRectWidth = rectWidth;
        float upper = MAX_TEXT_SIZE;
        float lower = MIN_TEXT_SIZE;
        int middle = (int) ((upper + lower) / 2);
        while (true) {
            paint.setTextSize(middle);
            float textWidth = paint.measureText(text);
            boolean smaller = textWidth < rectWidth;
            upper = smaller ? upper : middle;
            lower = smaller ? middle : lower;
            int nextMiddle = (int) ((upper + lower) / 2);
            if (nextMiddle == middle) {
                return middle;
            }
            middle = nextMiddle;
        }
    }

    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
