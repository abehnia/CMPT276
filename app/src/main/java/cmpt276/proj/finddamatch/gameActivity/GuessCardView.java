package cmpt276.proj.finddamatch.gameActivity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import cmpt276.proj.finddamatch.R;

/**
 * View for the card that the player clicks
 */
public class GuessCardView extends CardView {
    private static final float BORDER_RATIO = 1.05f;
    public Paint foregroundPaint;
    public Paint borderPaint;

    public GuessCardView(float x, float y, float radius, TypedArray logos,
                         Paint backgroundPaint, Resources resources) {
        super(x, y, radius, logos, backgroundPaint);
        this.foregroundPaint = new Paint();
        this.borderPaint = new Paint();
        foregroundPaint.setColor(Color.BLACK);
        borderPaint.setColor(resources.getColor(R.color.orange, null));
    }

    @Override
    protected void drawSelf(Canvas canvas) {
        canvas.drawCircle(x, y, radius * BORDER_RATIO, borderPaint);
        canvas.drawCircle(x, y, radius, foregroundPaint);
    }
}
