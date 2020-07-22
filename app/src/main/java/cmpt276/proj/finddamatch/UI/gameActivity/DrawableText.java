package cmpt276.proj.finddamatch.UI.gameActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DrawableText extends Drawable {
    private final String text;
    private final Paint paint;

    public DrawableText(String text) {
        this.text = text;
        this.paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(22f);
        paint.setFakeBoldText(true);


    }


    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawText(text, 0 ,0, paint);

    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }
}
