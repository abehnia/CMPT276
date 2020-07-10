package cmpt276.proj.finddamatch.model;

import java.util.Stack;

/**
 * Interface for a card generator
 * Should generate a stack of cards
 */
public interface CardGenerator {
    Stack<Card> generate();

}
