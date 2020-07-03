package cmpt276.proj.finddamatch;

import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;

import cmpt276.proj.finddamatch.model.Image;

public class ImageView {
    private static double EPSILON = 1e-1;
    private static double INHERENT_IMAGE_RADIUS = 0.4;
    private double x;
    private double y;
    private double scale;
    private double orientation;
    private double width, height;
    private double left, top, right, bottom;
    private Drawable imageToDraw;

    public ImageView(Image image, Drawable imageToDraw, CardView card) {
        this.x = (float) (card.getX() + image.getX() * card.getRadius());
        this.y = (float) (card.getY() + image.getY() * card.getRadius());
        this.scale = (float) (image.getScale() * card.getRadius());
        this.orientation = image.getOrientation();
        initImage(imageToDraw);
    }

    public void draw(Canvas canvas) {
        canvas.save();
        canvas.rotate((float) (orientation * 180 / Math.PI), (float)this.x, (float)this.y);
        imageToDraw.draw(canvas);
        canvas.restore();
    }

    public boolean isPointInside(double x, double y) {
        double deltaX = x - this.x;
        double deltaY = y - this.y;
        double xPrime = Math.cos(orientation) * deltaX -
                Math.sin(orientation) * deltaY;
        double yPrime = Math.sin(orientation) * deltaX +
                Math.cos(orientation) * deltaY;
        return xPrime - width / 2.0 <= EPSILON && xPrime + width / 2.0 >= -EPSILON &&
                yPrime + height / 2.0 >= -EPSILON && yPrime - height / 2.0 <= EPSILON;
    }

    public void applyFilter() {
        imageToDraw.setColorFilter(new LightingColorFilter(Color.LTGRAY, Color.BLACK));
    }

    public void clearFilter() {
        imageToDraw.setColorFilter(null);
    }

    private void initImage(Drawable imageToDraw) {
        this.imageToDraw = imageToDraw.mutate();
        int width = this.imageToDraw.getIntrinsicWidth();
        int height = this.imageToDraw.getIntrinsicHeight();
        double radius = Math.max(width, height);
        this.width = INHERENT_IMAGE_RADIUS *
                (width / radius) * this.scale;
        this.height = INHERENT_IMAGE_RADIUS *
                (height / radius) * this.scale;
        this.left = this.x - this.width / 2.0;
        this.right = this.x + this.width / 2.0;
        this.top = this.y - this.height / 2.0;
        this.bottom = this.y + this.height / 2.0;
        this.imageToDraw.setBounds((int)left, (int)top,
                (int)right, (int)bottom);
    }
}
