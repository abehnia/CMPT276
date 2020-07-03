package cmpt276.proj.finddamatch.model;

public class ImageMockImpl implements Image {
    private float x, y, scale, orientation;
    int value;

    public ImageMockImpl(float x, float y, float scale, float orientation,
                         int value) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.orientation = orientation;
        this.value = value;
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
    public float getScale() {
        return this.scale;
    }

    @Override
    public int getValue() {
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
    public void setScale(float scale) {
        this.scale = scale;
    }
}
