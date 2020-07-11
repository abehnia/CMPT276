package cmpt276.proj.finddamatch.model.gameLogic;

import java.util.Collections;
import java.util.Stack;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.CardGenerator;

import static cmpt276.proj.finddamatch.model.gameLogic.CardImpl.CARD1;
import static cmpt276.proj.finddamatch.model.gameLogic.CardImpl.CARD2;
import static cmpt276.proj.finddamatch.model.gameLogic.CardImpl.PRE_GENERATED_CARDS;

/**
 * Test Class for generating a randomized stack of Cards
 */
public class TestCardGeneratorImpl implements CardGenerator {

    @Override
    public Stack<Card> generate() {
        Stack<Card> arrayOfCards = new Stack<>();
        arrayOfCards.push(CARD1);
        arrayOfCards.push(CARD2);

        return arrayOfCards;
    }
}
