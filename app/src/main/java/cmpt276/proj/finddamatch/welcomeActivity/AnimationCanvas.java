package cmpt276.proj.finddamatch.welcomeActivity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;

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
    private Handler handler;
    private PositionState state;
    private long referenceTime;
    private long elapsedTime;

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

    public void resume() {
        this.referenceTime = SystemClock.elapsedRealtime();
        this.handler.postDelayed(this::invalidate, 10);
    }

    public void pause() {
        this.handler.removeCallbacksAndMessages(null);
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
        this.state = new PositionState(startY, startVY);
        this.animatedView = new AnimationView(getResources().getDrawable(
                R.drawable.ic_toronto_raptors_logo, null),
                startX, startY, 100);
        GravityForce force = new GravityForce(height / 4f);
        this.engine = new AnimationEngine(force, 0,
                height + 100, state, 0.78f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, backgroundPaint);
        animatedView.draw(canvas);
        long previousTime = referenceTime;
        referenceTime = SystemClock.elapsedRealtime();
        elapsedTime = referenceTime - previousTime;
        PositionState state = engine.update(elapsedTime);
        animatedView.setY(state.getPosition());
        this.handler.postDelayed(this::invalidate, 10);
    }

    private void init() {
        this.elapsedTime = 0;
        this.referenceTime = SystemClock.elapsedRealtime();
        int backgroundColor = this.getResources().getColor(
                R.color.colorGameBackground, null);
        this.backgroundPaint = new Paint(backgroundColor);
        handler = new Handler();
    }
}
