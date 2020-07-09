package cmpt276.proj.finddamatch.model.gameLogic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import cmpt276.proj.finddamatch.model.Card;

import static cmpt276.proj.finddamatch.model.gameLogic.CardImpl.PRE_GENERATED_CARDS;
import static cmpt276.proj.finddamatch.model.Card.NUMBER_OF_CARDS;

/**
 * Class for generating a stack of Cards
 */

public class CardsGenerator {
    private Stack<Card> ARRAY_OF_CARDS;
    private Stack<Card> BACK_UP_ARRAY; // to store the card has been removed


    public CardsGenerator() {
        ARRAY_OF_CARDS = new Stack<>();
        BACK_UP_ARRAY = new Stack<>();
        for (Card card : PRE_GENERATED_CARDS) {
            ARRAY_OF_CARDS.push(card);
            card.randomize();
        }
        Collections.shuffle(ARRAY_OF_CARDS);
    }

    public int size() {
        return ARRAY_OF_CARDS.size();
    }

    public boolean isEmpty() {
        return ARRAY_OF_CARDS.isEmpty();
    }

    public void push(Card card) {
        ARRAY_OF_CARDS.push(card);
    }

    public Card pop() {
        Card popValue = ARRAY_OF_CARDS.get(0);
        ARRAY_OF_CARDS.remove(0);
        BACK_UP_ARRAY.add(popValue);
        return popValue;
    }

    public Card peek() {
        return ARRAY_OF_CARDS.get(0);
    }
}

