package cmpt276.proj.finddamatch.welcomeActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
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
    private Drawable background;
    private AnimationView animatedView;
    private AnimationEngine engine;
    private Handler handler;
    private PositionState state;
    private long referenceTime;
    private long elapsedTime;
    private boolean firstTime;

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
        float startX = (float) (0.20 * width);
        float startY = (float) (height * 0.10);
        float startVY = 0;
        int backgroundWidth = background.getIntrinsicWidth();
        int backgroundHeight = background.getIntrinsicHeight();
        double ratio = (double) backgroundHeight / backgroundWidth;
        int newBackgroundWidth = (int) (0.9 * width);
        int newBackgroundHeight = (int) (ratio * newBackgroundWidth);
        float radius = newBackgroundHeight / 2.2f;
        this.state = new PositionState(startY, startVY);
        this.animatedView = new AnimationView(getResources().getDrawable(
                R.drawable.ic_bball, null),
                startX, startY, radius);
        GravityForce force = new GravityForce(height / 4f);
        this.engine = new AnimationEngine(force, 0,
                height + 10 , state, 0.78f);
        this.background.setBounds((int) (0.05 * width),
                (int) (0.10 * height - newBackgroundHeight / 2),
                (int) (0.95 * width),
                (int) (0.10 * height + newBackgroundHeight / 2));
        this.handler.postDelayed(this::drawHelper, 1000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        background.draw(canvas);
        animatedView.draw(canvas);
        long previousTime = referenceTime;
        referenceTime = SystemClock.elapsedRealtime();
        elapsedTime = referenceTime - previousTime;
        PositionState state = engine.update(elapsedTime);
        animatedView.setY(state.getPosition());
    }

    private void init() {
        this.firstTime = true;
        this.elapsedTime = 0;
        this.referenceTime = SystemClock.elapsedRealtime();
        this.background = getResources().getDrawable(
                R.drawable.ic_logo_nobball, null);
        handler = new Handler();
    }

    private void drawHelper() {
        if (firstTime) {
            firstTime = false;
            referenceTime = SystemClock.elapsedRealtime();
        }
        this.handler.postDelayed(this::drawHelper, 5);
        invalidate();
    }
}
