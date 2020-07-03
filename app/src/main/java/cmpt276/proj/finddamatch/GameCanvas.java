package cmpt276.proj.finddamatch;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import java.util.NoSuchElementException;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.Image;

public class GameCanvas extends View {
    private int backgroundColor, cardColor;
    private Paint backgroundPaint, cardPaint;
    private Canvas extraCanvas;
    private Bitmap extraBitmap;
    private CardView guessCard, leadCard;
    private Card lead, guess;

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

    public boolean intersects(double x, double y) {
        return guessCard.isPointInside(x, y);
    }

    public int getIntersection(double x, double y) {
        if (guessCard.isPointInside(x, y)) {
            return guessCard.getIntersectedImage(x, y);
        }
        throw new NoSuchElementException();
    }

    public void click(double x, double y) {
        if (guessCard.isPointInside(x, y)) {
            guessCard.applyFilter(guessCard.getIntersectedImage(x, y));
            invalidate();
        }
    }

    public void clearClick() {
        guessCard.clearFilter();
        invalidate();
    }

    public void setInitialCards(Card lead, Card guess) {
        this.lead = lead;
        this.guess = guess;
    }
    public void setCards(Card guess, Card lead) {
        guessCard.setImages(guess, 0);
        leadCard.setImages(lead, 0);
    }

    private void init() {
        backgroundColor = ResourcesCompat.getColor(getResources(),
                R.color.colorGameBackground, null);
        backgroundPaint = new Paint();
        backgroundPaint.setColor(backgroundColor);
        cardColor = ResourcesCompat.getColor(getResources(),
                R.color.colorCard, null);
        cardPaint = new Paint();
        cardPaint.setColor(cardColor);
    }

    @Override
    protected void onSizeChanged(int width, int height,
                                 int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        extraBitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        extraCanvas = new Canvas(extraBitmap);
        extraCanvas.drawColor(backgroundColor);
        TypedArray logos = getResources().obtainTypedArray(R.array.logos);
        guessCard = new CardView(width / 2.0, 3 * height / 4.0, Math.min(width, height) / 4.0, logos, cardPaint);
        leadCard = new CardView(guessCard.getX(), height / 4.0, guessCard.getRadius(), logos, cardPaint);
        guessCard.setImages(guess, 0);
        leadCard.setImages(lead, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(extraBitmap, 0, 0, null);
        leadCard.draw(canvas);
        guessCard.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (intersects(x, y)) {
                    click(x, y);
                }
                break;
            case MotionEvent.ACTION_UP:
                clearClick();
                break;
            default:
        }
        return true;
    }
}
