package cmpt276.proj.finddamatch.model;

/**
 * Interface for an image
 * Holds a position, an orientation,
 * a radius and the image value
 */
public interface Image {
    public static final float BASE_IMAGE_RADIUS = 0.2f;

    /**
     * @return orientation of the image in radians
     */
    float getOrientation();

    /**
     * Sets the orientation of the image
     */
    void setOrientation(float orientation);

    /**
     * @return the X position of the image
     */
    float getX();

    /**
     * Sets the X position of the image
     */
    void setX(float x);

    /**
     * @return the Y position of the image
     */
    float getY();

    /**
     * Sets the Y position of the image
     */
    void setY(float y);

    /**
     * @return radius of the image
     */
    float getRadius();

    /**
     * Sets the radius of the image
     */
    void setRadius(float radius);

    /**
     * @return the ID of the image
     */
    int getID();

    /**
     * Returns true if the Id of the images are the same
     */
     boolean isEquivalent(Image image);
}
