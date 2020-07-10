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
     * drawn cards back to the deck
     * update will save the game time state,
     */
    void update(Image image);

    /**
     * precondition: check() must be called before draw(), unless its the first card drawn.
     * Draw a card from the draw deck and move it to discard stack
     * essentially pop and move to discard.
     * Side Effects: a card is "drawn" from the deck
     */
    Card draw();

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
     * Is the game finished yet? (i.e. are there cards left to play)
     * Also ends real_time and computes the total elapsed game time.
     * Side Effects: none
     */
    boolean isGameDone();

    /**
     * Return the number of correct images chosen by the player
     * Side Effects: none
     */
    int getScore();

    /**
     * Creates a reference time variable to track when game is paused
     */
    void pause();

    /**
     * This will be used to track continued gametime
     * elapsed time - time at resume() = resumed gameplay time
     * total gametime = resumed gameplay + pausedGametime
     * @return
     */
    long resume();


}
