package cmpt276.proj.finddamatch.model;

/**
 * Interface for a card
 * Holds n images
 */
public interface Card extends Iterable<Image> {
    /**
     * Get the index image in the card
     * Side Effects: none
     */
    Image get(int index);

    /**
     * Does image exist in the card?
     * Side Effects: none
     */
    boolean exists(Image image);

    /**
     * Returns the number of images in the card
     * Side Effects: none
     */
    int size();

    /**
     * Randomize the orientation, scale and position of images
     * Side Effects: changes the orientation, scale and position of images
     */
    void randomize();
}
