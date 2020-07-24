package cmpt276.proj.finddamatch.model;

/**
 * Interface to represent a particular game mode
 */
public interface GameMode {

    /**
     * Returns the order of the game
     */
    int getOrder();

    /**
     * Returns the size of the game
     */
    int getSize();

    /**
     * Does the game contain text?
     */
    boolean hasText();

//    /**
//     * Sets the size of the game
//     */
//    void setSize(int userSelection);
//
//    /**
//     * Sets the order(difficulty) of the game
//     */
//    void setOrder(int userSelection);

}
