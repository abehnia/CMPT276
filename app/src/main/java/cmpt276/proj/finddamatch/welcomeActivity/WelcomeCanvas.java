package cmpt276.proj.finddamatch.welcomeActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


import cmpt276.proj.finddamatch.R;

/**
 * Welcome canvas for the animation
 */
public class WelcomeCanvas extends View {
    private Bitmap bitmap;
    private Paint backgroundPaint;
    private Drawable imageToDraw;
    private float x, y, velocityX, velocityY;
    private long time;
    private float width, height;
    private static final float DRAG_COEFFICIENT = 0.0001f;
    private static final float G = 1000f;

    public WelcomeCanvas(Context context) {
        super(context);
        init();
    }

    public WelcomeCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WelcomeCanvas(Context context, @Nullable AttributeSet attrs,
                         int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int width, int height,
                                 int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        this.bitmap = Bitmap.createBitmap(
                width, height, Bitmap.Config.ARGB_8888);
        this.width = width;
        this.height = height;
        x = width / 2.0f;
        y = height / 4.0f;
        time = SystemClock.uptimeMillis();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        long prevTime = time;
        time = SystemClock.uptimeMillis();
        updateState(time - prevTime);
        // initImage(imageToDraw);
        canvas.drawBitmap(bitmap, 0, 0, backgroundPaint);
        this.imageToDraw.setBounds((int) x, (int) y, (int) x + 100, (int) y + 100);
        imageToDraw.draw(canvas);
        invalidate();
    }

    private void init() {
        int backgroundColor = this.getResources().getColor(
                R.color.colorGameBackground, null);
        this.backgroundPaint = new Paint(backgroundColor);
        this.imageToDraw = getResources().getDrawable(
                R.drawable.ic_toronto_raptors_logo, null).mutate();
        velocityX = 0;
        velocityY = 0;
    }

    private void updateState(long deltaT) {
        float previousVelocity = velocityY;
        float previousPosition = y;
        if (Math.abs(velocityY) <= 5 && Math.abs(y - (this.height - 200)) <= 20) {
            return;
        }
        if (y <= this.height - 200) {
            velocityY = (float) (previousVelocity * Math.exp(-DRAG_COEFFICIENT * deltaT / 1000.0f) +
                    G * (1.0f - Math.exp(-DRAG_COEFFICIENT * deltaT / 1000.0f)) / DRAG_COEFFICIENT);
            y = -velocityY / DRAG_COEFFICIENT + previousVelocity / DRAG_COEFFICIENT +
                    previousPosition + G * deltaT / (DRAG_COEFFICIENT * 1000.0f);
        } else {
            y = this.height - 200;
            velocityY = -0.8f * velocityY;
        }
    }

    private void initImage(Drawable imageToDraw) {
        int width = this.imageToDraw.getIntrinsicWidth();
        int height = this.imageToDraw.getIntrinsicHeight();
        float maxSide = Math.max(width, height);
        float NewWidth = (width / maxSide) * 300;
        float newHeight = (height / maxSide) * 300;
        float left = this.x - NewWidth / 2.0f;
        float right = left + NewWidth;
        float top = this.y - newHeight / 2.0f;
        float bottom = top + newHeight;
        this.imageToDraw.setBounds((int) left, (int) top,
                (int) right, (int) bottom);
    }

}
