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
    public int PLAYER_ORDER_SELECTION = GAME5.getOrder();
    public final int ORDER = GAME5.getOrder();
    public final int SIZE = 2;

    CardGenerator cardGenerator;

    Stack<Card> arrayOfCardsInit = new Stack<>();
    ArrayList<MutableImage> images = new ArrayList<>();
    Stack<Card> arrayOfCardsFinal = new Stack<>();

    @Override
    public Stack<Card> generate() {
        arrayOfCardsInit.clear();
        arrayOfCardsFinal.clear();

        generateFirstCard();
        generateFirstCard();
        appendDeck();

        return arrayOfCardsFinal;
    }

    private void generateFirstCard() {
        List<MutableImage> images = new ArrayList<>();
        for (int i = 0; i < ORDER; i++) {
            for (int j = 0; j < ORDER; j++) {
                ImageImpl image = new ImageImpl(i * ORDER + j);
                images.add(image);
            }
            ImageImpl image = new ImageImpl(ORDER * ORDER);
            images.add(image);
            arrayOfCardsInit.push(cardGenerator.generate(images));
            images.clear();
        }
    }

    private void appendDeck() {
        int i = SIZE;
        for (Card card: arrayOfCardsInit) {
            if(i != 0){
                arrayOfCardsFinal.push(card);
                i--;
            }
            else{
                break;
            }
        }
    }
}
