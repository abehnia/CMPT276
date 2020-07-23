package cmpt276.proj.finddamatch.UI.gameActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DrawableText extends Drawable {
    private final String text;
    private final Paint paint;
    private final Rect rect = new Rect();

    public DrawableText(String text) {

        this.text = text;
        this.paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(50);
        paint.setFakeBoldText(true);
    }


    @Override
    public void draw(@NonNull Canvas canvas) {

        final Rect r = getBounds();
        int width = r.width();
        float textWidth = paint.measureText(text);
        float textSize = findSize(textWidth, width);
//        textSize = Math.floor(textSize * (width/textWidth));
        paint.setTextSize(textSize);
        canvas.drawText(text, r.centerX() ,r.centerY(), paint);

    }

    private float findSize(float textWidth, int width) {
        float textSize = paint.getTextSize();
        while(textWidth > width){
            textSize = textSize-3;
            paint.setTextSize(textSize);
            textWidth = paint.measureText(text);
        }
        return textSize;
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
