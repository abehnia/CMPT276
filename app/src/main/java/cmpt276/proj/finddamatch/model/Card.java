package cmpt276.proj.finddamatch.model;

/**
 * Interface for a card
 * Holds n images
 */
public interface Card extends Iterable<Image> {
    public static final float CARD_BASE_RADIUS = 1.0f;
    public static int MAX_NUMBER_OF_IMAGES = 31;

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


}
