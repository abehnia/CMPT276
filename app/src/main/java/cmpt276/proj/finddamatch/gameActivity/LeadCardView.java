package cmpt276.proj.finddamatch.gameActivity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;

import cmpt276.proj.finddamatch.R;

/**
 * View for the card that the player matches
 * (i.e. the player doesn't click on this card)
 */
public class LeadCardView extends CardView {
    public Paint foregroundPaint;

    public LeadCardView(float x, float y, float radius, TypedArray logos,
                        Paint backgroundPaint, Resources resources) {
        super(x, y, radius, logos, backgroundPaint);
        this.foregroundPaint = new Paint(resources.getColor(
                R.color.colorPrimary, null));
    }

    @Override
    protected void drawSelf(Canvas canvas) {
        canvas.drawCircle(x, y, radius, foregroundPaint);
    }
}
