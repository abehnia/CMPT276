package cmpt276.proj.finddamatch.model;

/**
 * Interface for the main logic of the game
 * This is the interface that should be used in the game UI
 */
public interface Game {
    /**
     * Checks if image is the same in both drawn cards
     * Side effects : none
     */
    boolean check(Image image);

    /**
     * Updates the game state when image is chosen
     * Side Effects: updates the counter and puts back
     *               drawn cards back to the deck
     */
    void update(Image image);

    /**
     * Poll a card from the deck
     * note that this is usually done twice each round
     * Side Effects: a card is "drawn" from the deck
     */
    Card poll();

    /**
     * Reset the game state
     * Can be used to start the game
     * Side Effects: resets the state of the game
     */
    void reset();

    /**
     * Query the time in the game
     * Side Effects: none
     */
    long queryTime();

    /**
     * Is the game finished yet? (i.e. is time equal to 0)
     * Side Effects: none
     */
    boolean isGameDone();

    /**
     * Return the number of correct images chosen by the player
     * Side Effects: none
     */
    int getScore();
}
