package cmpt276.proj.finddamatch.model;

/**
 * Interface for an image
 * Holds a position, an orientation,
 * a scale and the image value
 */
public interface Image {
    public static final float BASE_IMAGE_RADIUS = 0.2f;

    /**
     * @return orientation of the image in radians
     */
    float getOrientation();

    /**
     * @return the X position of the image
     */
    float getX();

    /**
     * @return the Y position of the image
     */
    float getY();

    /**
     * @return radius of the image
     */
    float getRadius();

    /**
     * @return the ID of the image
     */
    int getID();

    /**
     * Sets the orientation of the image
     */
    void setOrientation(float orientation);

    /**
     * Sets the X position of the image
     */
    void setX(float x);

    /**
     * Sets the Y position of the image
     */
    void setY(float y);

    /**
     * Sets the radius of the image
     */
    void setRadius(float radius);

    /**
     * Compares the 2 images' IDs
     */
     boolean isEquivalent(Image image);
}
