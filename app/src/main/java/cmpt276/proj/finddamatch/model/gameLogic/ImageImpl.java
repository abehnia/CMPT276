package cmpt276.proj.finddamatch.model.gameLogic;

import cmpt276.proj.finddamatch.model.Image;

public class ImageImpl implements Image {
    private float x, y, radius, orientation;
    int value;

    public ImageImpl(float x, float y, float radius, float orientation,
                     int value) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.orientation = orientation;
        this.value = value;
    }

    public ImageImpl(int ID) {
        this(0, 0, BASE_IMAGE_RADIUS, 0, ID);
    }

    @Override
    public float getOrientation() {
        return this.orientation;
    }

    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public float getY() {
        return this.y;
    }

    @Override
    public float getRadius() {
        return this.radius;
    }

    @Override
    public int getID() {
        return this.value;
    }

    @Override
    public void setOrientation(float orientation) {
        this.orientation = orientation;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public boolean isEquivalent(Image image) {
        return this.getID() == image.getID();
    }
}
