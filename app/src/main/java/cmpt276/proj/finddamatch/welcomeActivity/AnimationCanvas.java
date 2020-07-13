package cmpt276.proj.finddamatch.welcomeActivity;

import android.content.Context;
import android.graphics.Canvas;
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
    private static final double BALL_START_X_POS_PERCENTAGE = 0.20;
    private static final double BALL_START_Y_POS_PERCENTAGE = 0.1;
    private static final float IMAGE_FICTIONAL_SIZE = 4f;
    private static final float IMPACT_COEFFICIENT = 0.78f;
    private static final int DELAY = 10;
    private static final int INITIAL_DELAY = 1000;
    private static final double BACKGROUND_WIDTH_PERCENTAGE = 0.9;
    private static final int NAVIGATION_BAR_HEIGHT = 100;
    private static final double BACKGROUND_START_X_POS_PERCENTAGE = 0.05;
    private static final double BACKGROUND_MIDDLE_Y_POS_PERCENTAGE = 0.10;
    private static final float BACKGROUND_HEIGHT_TO_RADIUS = 1 / 2.2f;

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
        long delay = firstTime ? INITIAL_DELAY : DELAY;
        this.handler.postDelayed(this::drawHelper, delay);
    }

    public void pause() {
        this.handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onSizeChanged(int width, int height,
                                 int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        float radius = setupBackground(width, height) *
                BACKGROUND_HEIGHT_TO_RADIUS;
        float startX = (float) (BALL_START_X_POS_PERCENTAGE * width);
        float startY = (float) (BALL_START_Y_POS_PERCENTAGE * height);
        float startVY = 0;
        this.state = new PositionState(startY, startVY);
        this.animatedView = new AnimationView(getResources().getDrawable(
                R.drawable.ic_bball, null),
                startX, startY, radius);
        GravityForce force = new GravityForce(height /
                IMAGE_FICTIONAL_SIZE);
        this.engine = new AnimationEngine(force, 0,
                height - NAVIGATION_BAR_HEIGHT,
                state, IMPACT_COEFFICIENT);
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
        this.handler.postDelayed(this::drawHelper, DELAY);
        invalidate();
    }

    private float setupBackground(int width, int height) {
        int backgroundWidth = background.getIntrinsicWidth();
        int backgroundHeight = background.getIntrinsicHeight();
        double ratio = (double) backgroundHeight / backgroundWidth;
        int newBackgroundWidth = (int) (BACKGROUND_WIDTH_PERCENTAGE * width);
        int newBackgroundHeight = (int) (ratio * newBackgroundWidth);
        this.background.setBounds(
                (int) (BACKGROUND_START_X_POS_PERCENTAGE * width),
                (int) (BACKGROUND_MIDDLE_Y_POS_PERCENTAGE *
                        height - newBackgroundHeight / 2),
                (int) (BACKGROUND_START_X_POS_PERCENTAGE * width +
                        BACKGROUND_WIDTH_PERCENTAGE * width),
                (int) (BACKGROUND_MIDDLE_Y_POS_PERCENTAGE * height +
                        newBackgroundHeight / 2));
        return newBackgroundHeight;
    }
}
