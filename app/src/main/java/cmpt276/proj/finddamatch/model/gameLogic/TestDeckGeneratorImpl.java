package cmpt276.proj.finddamatch.model.gameLogic;

import java.util.Stack;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.DeckGenerator;

/**
 * Test Class for generating a randomized stack of Cards
 */
public class TestDeckGeneratorImpl implements DeckGenerator {
    Stack<Card> cards;

    public TestDeckGeneratorImpl() {
        this.cards = new Stack<>();
    }

    @Override
    public Stack<Card> generate() {
        cards.clear();
        cards.push(new CardImpl(new ImageImpl(0), new ImageImpl(1),
                new ImageImpl(2)));
        cards.push(new CardImpl(new ImageImpl(0), new ImageImpl(3),
                new ImageImpl(4)));
        return cards;
    }
}
