package cmpt276.proj.finddamatch.UI.gameActivity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.model.ImageSet;
import cmpt276.proj.finddamatch.model.MutableImage;
import cmpt276.proj.finddamatch.model.gameLogic.ImageImpl;

/**
 * View for the card that the player matches
 * (i.e. the player doesn't click on this card)
 */
public class LeadCardView extends CardView {
    private static final float BORDER_RATIO = 1.05f;
    private Paint foregroundPaint;
    private Paint borderPaint;
    private ImageView view;


    public LeadCardView(float x, float y, float radius,
                        Paint backgroundPaint, Resources resources, ImageSet imageSet) {
        super(x, y, radius, backgroundPaint, imageSet);
        this.foregroundPaint = new Paint();
        this.borderPaint = new Paint();
        foregroundPaint.setColor(Color.BLACK);
        borderPaint.setColor(resources.getColor(R.color.orange, null));
        Drawable backLogo = imageSet.getBackLogo();
        MutableImage image = new ImageImpl(0);
        image.setRadius(0.5f);
        this.view = new ImageView(image, backLogo, this);
    }

    @Override
    protected void drawSelf(Canvas canvas) {
        canvas.drawCircle(x, y, radius * BORDER_RATIO, borderPaint);
        canvas.drawCircle(x, y, radius, foregroundPaint);
        if (isHidden) {
            view.draw(canvas);
        }
    }
}
