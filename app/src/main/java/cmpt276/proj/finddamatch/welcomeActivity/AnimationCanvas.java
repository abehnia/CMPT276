package cmpt276.proj.finddamatch.welcomeActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


import cmpt276.proj.finddamatch.R;

/**
 * Canvas for the welcome screen animation
 * Draws the animation
 */
public class AnimationCanvas extends View {
    private Paint backgroundPaint;
    private Bitmap bitmap;
    private AnimationView animatedView;
    private AnimationEngine engine;
    private long time;

    public AnimationCanvas(Context context) {
        super(context);
        init();
    }

    public AnimationCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnimationCanvas(Context context, @Nullable AttributeSet attrs,
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
        float startX = width / 2f;
        float startY = height / 4f;
        float startVY = 0;
        PositionState state = new PositionState(startY, startVY);
        this.animatedView = new AnimationView(getResources().getDrawable(
                R.drawable.ic_toronto_raptors_logo, null),
                startX, startY, 100);
        GravityWithDragForce force = new GravityWithDragForce(
                height / 4f, 0.0001f);
        this.engine = new AnimationEngine(force, 0,
                height, state, 0.75f);
        this.time = SystemClock.uptimeMillis();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        long previousTime = time;
        time = SystemClock.uptimeMillis();
        canvas.drawBitmap(bitmap, 0, 0, backgroundPaint);
        animatedView.draw(canvas);
        PositionState state = engine.update(time - previousTime);
        animatedView.setY(state.getPosition());
        invalidate();
    }

    private void init() {
        int backgroundColor = this.getResources().getColor(
                R.color.colorGameBackground, null);
        this.backgroundPaint = new Paint(backgroundColor);
    }
}
