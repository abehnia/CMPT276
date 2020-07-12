package cmpt276.proj.finddamatch.model.gameLogic;

import java.util.Stack;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.DeckGenerator;

import static cmpt276.proj.finddamatch.model.gameLogic.PRE_GENERATED_CARDS.CARD1;
import static cmpt276.proj.finddamatch.model.gameLogic.PRE_GENERATED_CARDS.CARD2;

/**
 * Test Class for generating a randomized stack of Cards
 */
public class TestDeckGeneratorImpl implements DeckGenerator {

    @Override
    public Stack<Card> generate() {
        Stack<Card> arrayOfCards = new Stack<>();
        arrayOfCards.push(CARD1);
        arrayOfCards.push(CARD2);

        return arrayOfCards;
    }
}
