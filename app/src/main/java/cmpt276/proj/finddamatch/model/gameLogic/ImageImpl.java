package cmpt276.proj.finddamatch.model.gameLogic;

import cmpt276.proj.finddamatch.model.Image;
import cmpt276.proj.finddamatch.model.MutableImage;

/**
 * Typical image implementation of the mutable image interface
 * For more information, refer to the mutable image interface
 */
public class ImageImpl implements MutableImage {
    private boolean imageMode = true;
    int ID;
    private float x, y, radius, orientation;
    private boolean isText;

    public ImageImpl(float x, float y, float radius, float orientation,
                     int ID) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.orientation = orientation;
        this.ID = ID;
    }

    public ImageImpl(int ID) {
        this(0, 0, BASE_IMAGE_RADIUS, 0, ID);
    }

    @Override
    public float getOrientation() {
        return this.orientation;
    }

    @Override
    public void setOrientation(float orientation) {
        this.orientation = orientation;
    }

    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return this.y;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public float getRadius() {
        return this.radius;
    }

    @Override
    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public int getID() {
        return this.ID;
    }

    @Override
    public boolean isEquivalent(Image image) {
        return this.getID() == image.getID();
    }

    @Override
    public boolean getHasText() {
        return isText;
    }

    @Override
    public void setHasText(boolean gameMode) {
        isText = gameMode;
    }
}
