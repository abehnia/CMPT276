package cmpt276.proj.finddamatch.model;

public class ImageMockImpl implements Image {
    private float x, y, radius, orientation;
    int value;

    public ImageMockImpl(float x, float y, float scale, float orientation,
                         int value) {
        this.x = x;
        this.y = y;
        this.radius = radius;
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
    public float getRadius() {
        return 0.4f;
    }

    @Override
    public int getID() {
        return this.value;
    }

   public boolean isEquivalent(Image image){return false;}
}
