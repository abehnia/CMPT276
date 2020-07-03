package cmpt276.proj.finddamatch;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.Image;

public class CardView implements Iterable<ImageView>{
    private static int NUMBER_OF_IMAGES_PER_DECK = 7;
    private double x, y, radius;
    private Paint paint;
    ArrayList<ImageView> images;
    TypedArray logos;

    public CardView(double x, double y, double radius, TypedArray logos, Paint paint) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.logos = logos;
        this.images = new ArrayList<>();
        this.paint = paint;
    }

    public void setImages(Card card, int imageSet) {
        images.clear();
        for (Image image : card) {
            Drawable imageToDraw = logos.getDrawable(image.getValue()
                    + imageSet * NUMBER_OF_IMAGES_PER_DECK);
            images.add(new ImageView(image, imageToDraw, this));
        }
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getRadius() {
        return this.radius;
    }

    public boolean isPointInside(double x, double y) {
        for (ImageView imageView : images) {
            if (imageView.isPointInside(x, y)) {
                return true;
            }
        }
        return false;
    }

    public int getIntersectedImage(double x, double y) {
        int index = 0;
        for (ImageView imageView : images) {
            if (imageView.isPointInside(x, y)) {
                return index;
            }
            index += 1;
        }
        throw new NoSuchElementException();
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float)x, (float)y, (float)radius, paint);
        for (ImageView imageView : images) {
            imageView.draw(canvas);
        }
    }

    public void applyFilter(int index) {
        images.get(index).applyFilter();
    }

    public void clearFilter() {
        for (ImageView imageView : images) {
            imageView.clearFilter();
        }
    }

    @NonNull
    @Override
    public Iterator<ImageView> iterator() {
        return images.iterator();
    }
}
