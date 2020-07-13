package cmpt276.proj.finddamatch.gameActivity;

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

/**
 * Abstract class to represent a view for a card
 */
public abstract class CardView implements Iterable<ImageView> {
    protected float x, y, radius;
    protected Paint backgroundPaint;
    protected ArrayList<ImageView> images;
    protected TypedArray logos;

    public CardView(float x, float y, float radius, TypedArray logos,
                    Paint backgroundPaint) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.logos = logos;
        this.images = new ArrayList<>();
        this.backgroundPaint = backgroundPaint;
    }

    public void setImages(Card card, int imageSet) {
        images.clear();
        for (Image image : card) {
            Drawable imageToDraw = logos.getDrawable(image.getID()
                    + imageSet * Card.NUMBER_OF_IMAGES_PER_DECK);
            images.add(new ImageView(image, imageToDraw, this));
        }
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getRadius() {
        return this.radius;
    }

    public boolean contains(float x, float y) {
        for (ImageView imageView : images) {
            if (imageView.contains(x, y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Pre-condition: Must be called if the point is already inside
     * (i.e. check with contains first)
     */
    public Image getIntersection(float x, float y) {
        for (ImageView imageView : images) {
            if (imageView.contains(x, y)) {
                return imageView.getImage();
            }
        }
        throw new NoSuchElementException();
    }

    public void draw(Canvas canvas) {
        this.drawSelf(canvas);
        for (ImageView imageView : images) {
            imageView.draw(canvas);
        }
    }

    public void applyFilter(Image image) {
        for (ImageView imageView : images) {
            if (imageView.getImage() == image) {
                imageView.applyFilter(backgroundPaint);
                return;
            }
        }
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

    abstract protected void drawSelf(Canvas canvas);
}
