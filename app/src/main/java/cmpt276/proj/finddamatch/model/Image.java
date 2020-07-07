package cmpt276.proj.finddamatch.model;

/**
 * Interface for an image
 * Holds a position, an orientation,
 * a scale and the image value
 */
public interface Image {

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
     * @return the value of the image
     */
    int getID();

    float getScale();

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
     * Sets the scale of the image
     */
    void setScale(float scale);
}
