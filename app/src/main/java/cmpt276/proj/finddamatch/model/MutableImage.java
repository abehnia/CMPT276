package cmpt276.proj.finddamatch.model;

/**
 * Interface for an image
 * Holds a position, an orientation,
 * a radius and the image value
 */
public interface MutableImage extends Image {

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
}
