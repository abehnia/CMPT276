package cmpt276.proj.finddamatch.gameActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.NoSuchElementException;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.Image;

/**
 * Main canvas for the game
 * Draws the game
 */
public class GameCanvas extends View {
    private Paint backgroundPaint;
    private Bitmap bitmap;
    private CardView guessCard, leadCard;

    public GameCanvas(Context context) {
        super(context);
        init();
    }

    public GameCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameCanvas(Context context, @Nullable AttributeSet attrs,
                      int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public boolean contains(float x, float y) {
        return guessCard.contains(x, y);
    }

    public Image getIntersection(float x, float y) {
        if (guessCard.contains(x, y)) {
            return guessCard.getIntersection(x, y);
        }
        throw new NoSuchElementException();
    }

    public void setCards(Card guess, Card lead, int imageSet) {
        guessCard.setImages(guess, imageSet);
        leadCard.setImages(lead, imageSet);
        invalidate();
    }

    public void reveal() {
        leadCard.setHidden(false);
        invalidate();
    }

    public void hide() {
        leadCard.setHidden(true);
        invalidate();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                actionDown(x, y);
                break;
            case MotionEvent.ACTION_UP:
                actionUp();
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onSizeChanged(int width, int height,
                                 int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        this.bitmap = Bitmap.createBitmap(
                width, height, Bitmap.Config.ARGB_8888);
        setupCards(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, backgroundPaint);
        leadCard.draw(canvas);
        guessCard.draw(canvas);
    }

    private void init() {
        int backgroundColor = this.getResources().getColor(
                R.color.colorGameBackground, null);
        this.backgroundPaint = new Paint(backgroundColor);
    }

    private void actionDown(float x, float y) {
        if (contains(x, y)) {
            guessCard.applyFilter(getIntersection(x, y));
            invalidate();
        }
    }

    private void actionUp() {
        guessCard.clearFilter();
        invalidate();
    }

    private void setupCards(int width, int height) {
        TypedArray logos = getResources().obtainTypedArray(R.array.logos);
        float guessCardX = width / 2.0f;
        float guessCardY = 7 * height / 10.0f;
        float guessCardRadius = Math.min(width, height) / 3.0f;
        guessCard = new GuessCardView(guessCardX, guessCardY, guessCardRadius,
                logos, backgroundPaint, this.getResources());
        float leadCardX = width / 2.0f;
        float leadCardY = 3 * height / 10.0f;
        float leadCardRadius = Math.min(width, height) / 4.0f;
        leadCard = new LeadCardView(leadCardX, leadCardY, leadCardRadius,
                logos, backgroundPaint, this.getResources());
    }
}
