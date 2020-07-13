package cmpt276.proj.finddamatch.welcomeActivity;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;


/**
 * View for a logo based animation object
 */
public class AnimationView {
    private static final float EPSILON = 1e-3f;
    private static final double PI_TO_DEGREE = 180 / Math.PI;
    private float x, y;
    private float radius;
    private Rect bounds;
    private Drawable imageToDraw;

    public AnimationView(Drawable imageToDraw, float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        initImage(imageToDraw);
    }

    public AnimationView(Drawable imageToDraw, float x, float y) {
        this(imageToDraw, x, y, 1.0f);
    }

    void setX(float x) {
        this.x = x;
        float width = bounds.width();
        bounds.left = (int) (this.x - width / 2f);
        bounds.right = (int) (this.x + width / 2f);
        this.imageToDraw.setBounds(this.bounds);
    }

    void setY(float y) {
        this.y = y;
        float height = bounds.height();
        bounds.top = (int) (this.y - height / 2f);
        bounds.bottom = (int) (this.y + height / 2f);
        this.imageToDraw.setBounds(this.bounds);
    }

    public void draw(Canvas canvas) {
        imageToDraw.draw(canvas);
    }

    private void initImage(Drawable imageToDraw) {
        this.imageToDraw = imageToDraw.mutate();
        int width = this.imageToDraw.getIntrinsicWidth();
        int height = this.imageToDraw.getIntrinsicHeight();
        float maxSide = Math.max(width, height);
        float NewWidth = (width / maxSide) * 2 * this.radius;
        float newHeight = (height / maxSide) * 2 * this.radius;
        float left = this.x - NewWidth / 2.0f;
        float right = left + NewWidth;
        float top = this.y - newHeight / 2.0f;
        float bottom = top + newHeight;
        this.bounds = new Rect((int)left, (int)top, (int)right, (int)bottom);
        this.imageToDraw.setBounds(this.bounds);
    }
}
