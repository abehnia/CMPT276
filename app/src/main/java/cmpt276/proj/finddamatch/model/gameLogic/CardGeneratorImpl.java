package cmpt276.proj.finddamatch.model.gameLogic;

import java.util.Collections;
import java.util.Stack;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.CardGenerator;

import static cmpt276.proj.finddamatch.model.gameLogic.CardImpl.PRE_GENERATED_CARDS;

/**
 * Class for generating a randomized stack of Cards
 */
public class CardGeneratorImpl implements CardGenerator {

    @Override
    public Stack<Card> generate() {
        Stack<Card> arrayOfCards = new Stack<>();
        arrayOfCards.addAll(PRE_GENERATED_CARDS);
        for (Card card : arrayOfCards) {
            card.randomize();
        }
        Collections.shuffle(arrayOfCards);
        return arrayOfCards;
    }
}

