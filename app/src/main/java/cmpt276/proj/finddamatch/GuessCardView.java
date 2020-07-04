package cmpt276.proj.finddamatch;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class GuessCardView extends CardView {
    public Paint foregroundPaint;

    public GuessCardView(float x, float y, float radius, TypedArray logos,
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
