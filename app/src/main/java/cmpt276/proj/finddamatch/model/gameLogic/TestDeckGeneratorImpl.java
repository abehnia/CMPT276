package cmpt276.proj.finddamatch.model.gameLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.CardGenerator;
import cmpt276.proj.finddamatch.model.DeckGenerator;
import cmpt276.proj.finddamatch.model.DeckGenerator;
import cmpt276.proj.finddamatch.model.MutableImage;

import static cmpt276.proj.finddamatch.model.gameLogic.VALID_GAME_MODE.GAME5;

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
