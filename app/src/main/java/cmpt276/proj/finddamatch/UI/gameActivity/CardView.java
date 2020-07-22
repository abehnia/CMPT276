package cmpt276.proj.finddamatch.UI.gameActivity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;

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
    protected TypedArray logos;
    protected boolean isHidden;

    public CardView(float x, float y, float radius, TypedArray logos,
                    Paint backgroundPaint) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.logos = logos;
        this.images = new ArrayList<>();
        this.backgroundPaint = backgroundPaint;
        this.isHidden = false;
    }

    public void setImages(Card card, Resources resources) {
        images.clear();
        ImageSet imageset = new ImageSetImpl();
        for (Image image : card) {
            Drawable imageToDraw = imageset.getDrawables(image.getID(), true, resources);
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
