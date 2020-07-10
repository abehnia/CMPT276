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
     * precondition: check() must be called before draw(),
     * unless its the first card drawn.
     * Draw a card from the draw deck
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
     * Pause the game
     * Side Effects: pauses the game timer
     */
    void pause();

    /**
     * Resumes the game
     * Side Effects: re-activates the game timer
     */
    void resume();


}
