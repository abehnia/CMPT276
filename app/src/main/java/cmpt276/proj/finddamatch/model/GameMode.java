package cmpt276.proj.finddamatch.model;

import java.io.Serializable;

/**
 * Interface to represent a particular game mode
 */
public interface GameMode extends Serializable {

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

    /**
     * Are the game modes the same?
     */
    boolean isEquivalent(GameMode gameMode);
}
