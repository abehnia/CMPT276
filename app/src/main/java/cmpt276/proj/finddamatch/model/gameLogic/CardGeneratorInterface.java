package cmpt276.proj.finddamatch.model.gameLogic;

import java.util.Stack;

import cmpt276.proj.finddamatch.model.Card;


/**
 * Interface for CardGenerator
 * This interface will be used so that we can use the
 * CardGenerator class in our game logic
 */

public interface CardGeneratorInterface {

    /**
     * get a newly generated deck of cards
     * @return
     */
    Stack<Card> getInstance();

    /**
     * returns the size of the card deck
     */
    int size();

    /**
     * checks if card deck is empty and returns True or False
     */
    boolean isEmpty();

    /**
     * adds a card to the an existing deck of cards
     */
    void push(Card card);

    /**
     * pops the top card out of the deck stack
     */
    Card pop();

    /**
     * returns the current card on deck stack
     */
    Card peek();
}
