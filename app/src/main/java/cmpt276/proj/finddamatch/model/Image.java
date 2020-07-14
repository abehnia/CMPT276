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
     * Returns true if the Id of the images are the same
     */
    boolean isEquivalent(Image image);
}
