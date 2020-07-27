package cmpt276.proj.finddamatch.UI.gameActivity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.Image;
import cmpt276.proj.finddamatch.model.ImageSet;

/**
 * Abstract class to represent a view for a card
 */
public abstract class CardView implements Iterable<ImageView> {
    protected float x, y, radius;
    protected Paint backgroundPaint;
    protected ArrayList<ImageView> images;
    protected boolean isHidden;
    protected ImageSet imageSet;

    public CardView(float x, float y, float radius,
                    Paint backgroundPaint, ImageSet imageSet) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.images = new ArrayList<>();
        this.backgroundPaint = backgroundPaint;
        this.isHidden = false;
        this.imageSet = imageSet;
    }

    public void setImages(Card card) {
        images.clear();
        for (Image image : card) {
            Drawable imageToDraw = imageSet.getImage(image.getID(),
                    image.getHasText());
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

    public boolean getHidden() {
        return this.isHidden;
    }

    public void setHidden(boolean isHidden) {
        this.isHidden = isHidden;
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
        if (!isHidden) {
            for (ImageView imageView : images) {
                imageView.draw(canvas);
            }
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
