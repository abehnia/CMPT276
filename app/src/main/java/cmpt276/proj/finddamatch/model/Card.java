package cmpt276.proj.finddamatch.model;

/**
 * Interface for a card
 * Holds n images
 */
public interface Card extends Iterable<Image> {
    public static final int NUMBER_OF_IMAGES_PER_DECK = 7;
    public static final int NUMBER_OF_IMAGES_PER_CARD = 3;
    public static final int NUMBER_OF_CARDS = 7;
    public static final float CARD_BASE_RADIUS = 1.0f;

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
