package cmpt276.proj.finddamatch.model;

import java.io.Serializable;
import java.util.Stack;

/**
 * Interface for a deck generator
 * Should generate a stack of cards
 */
public interface DeckGenerator extends Serializable {
    Stack<Card> generate();
}
