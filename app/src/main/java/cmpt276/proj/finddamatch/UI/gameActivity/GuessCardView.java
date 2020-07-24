package cmpt276.proj.finddamatch.UI.gameActivity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.model.ImageSet;

/**
 * View for the card that the player clicks
 */
public class GuessCardView extends CardView {
    private static final float BORDER_RATIO = 1.05f;
    public Paint foregroundPaint;
    public Paint borderPaint;

    public GuessCardView(float x, float y, float radius,
                         Paint backgroundPaint, Resources resources, ImageSet imageSet) {
        super(x, y, radius, backgroundPaint, imageSet);
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
