package cmpt276.proj.finddamatch.model;

import java.util.Stack;

/**
 * Interface for a deck generator
 * Should generate a stack of cards
 */
public interface DeckGenerator {
    Stack<Card> generate();
}
