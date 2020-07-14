package cmpt276.proj.finddamatch.UI.gameActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import cmpt276.proj.finddamatch.model.Image;

/**
 * View for a single image
 */
public class ImageView {
    private float x, y;
    private float radius;
    private float orientation;
    private Image image;
    private RectF bounds;
    private Drawable imageToDraw;
    private static final float EPSILON = 1e-3f;
    private static final double PI_TO_DEGREE = 180 / Math.PI;

    public ImageView(Image image, Drawable imageToDraw, CardView card) {
        this.x = card.getX() + image.getX() * card.getRadius();
        this.y = card.getY() + image.getY() * card.getRadius();
        this.radius = image.getRadius() * card.getRadius();
        this.orientation = image.getOrientation();
        this.image = image;
        initImage(imageToDraw);
    }

    public Image getImage() {
        return this.image;
    }

    public void draw(Canvas canvas) {
        canvas.save();
        canvas.rotate((float) (orientation * PI_TO_DEGREE), this.x, this.y);
        imageToDraw.draw(canvas);
        canvas.restore();
    }

    public boolean contains(float x, float y) {
        float deltaX = x - this.x;
        float deltaY = y - this.y;
        float xPrime = (float) (Math.cos(orientation) * deltaX +
                Math.sin(orientation) * deltaY);
        float yPrime = (float) (-Math.sin(orientation) * deltaX +
                Math.cos(orientation) * deltaY);
        return inBound(xPrime, yPrime);
    }

    public void applyFilter(Paint backgroundPaint) {
        imageToDraw.setColorFilter(new LightingColorFilter(Color.LTGRAY,
                backgroundPaint.getColor()));
    }

    public void clearFilter() {
        imageToDraw.setColorFilter(null);
    }

    private void initImage(Drawable imageToDraw) {
        this.imageToDraw = imageToDraw.mutate();
        int width = this.imageToDraw.getIntrinsicWidth();
        int height = this.imageToDraw.getIntrinsicHeight();
        float maxSide = Math.max(width, height);
        float NewWidth = (width / maxSide) * this.radius;
        float newHeight = (height / maxSide) * this.radius;
        float left = this.x - NewWidth / 2.0f;
        float right = left + NewWidth;
        float top = this.y - newHeight / 2.0f;
        float bottom = top + newHeight;
        this.bounds = new RectF(left, top, right, bottom);
        this.imageToDraw.setBounds((int) left, (int) top,
                (int) right, (int) bottom);
    }

    private boolean inBound(float x, float y) {
        float halfWidth = bounds.width() / 2.0f;
        float halfHeight = bounds.height() / 2.0f;
        return x <= halfWidth + EPSILON && x >= -halfWidth - EPSILON &&
                y <= halfHeight + EPSILON && y >= -halfHeight - EPSILON;
    }

}
