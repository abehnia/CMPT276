package cmpt276.proj.finddamatch.model;

import java.io.Serializable;

/**
 * Interface for the main logic of the game
 * This is the interface that should be used in the game UI
 */
public interface Game extends Serializable {
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
    void reset(long time);

    /**
     * Query the time in the game
     * Side Effects: none
     */
    long queryTime(long time);

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
    void pause(long time);

    /**
     * Resumes the game
     * Side Effects: re-activates the game timer
     */
    void resume(long time);

    /**
     * Returns the first card in the draw pile
     * Side Effects: none
     */
    Card peekDiscard();


    /**
     * Returns the first card in the discard pile
     * Side Effects: none
     */
    Card peekDraw();
}
