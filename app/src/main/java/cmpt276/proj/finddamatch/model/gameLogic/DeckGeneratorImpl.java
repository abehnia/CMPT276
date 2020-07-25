package cmpt276.proj.finddamatch.model.gameLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.DeckGenerator;
import cmpt276.proj.finddamatch.model.CardGenerator;
import cmpt276.proj.finddamatch.model.MutableImage;

import static cmpt276.proj.finddamatch.model.gameLogic.VALID_GAME_MODE.GAME4;

/**
 * Class for generating a randomized stack of Cards
 */
public class DeckGeneratorImpl implements DeckGenerator {

    public int PLAYER_SELECTION = GAME4.getOrder();
    public final int ORDER = GAME4.getOrder();
    public final int SIZE = GAME4.getSize();

    CardGenerator cardGenerator;

    Stack<Card> arrayOfCardsInit = new Stack<>();
    ArrayList<MutableImage> images = new ArrayList<>();
    Stack<Card> arrayOfCardsFinal = new Stack<>();

    public DeckGeneratorImpl(CardGenerator cardGenerator) {
        this.cardGenerator = cardGenerator;
        for (int i = 2; i < 1 + (int) Math.sqrt(PLAYER_SELECTION); i++) {
            if (PLAYER_SELECTION % i == 0) {
                PLAYER_SELECTION = i;
                break;
            }
        }
    }

    @Override
    public Stack<Card> generate() {
        //crispy clean decks
        arrayOfCardsInit.clear();
        arrayOfCardsFinal.clear();

        // Generate the first card, then adds it to the arrayOfCards stack.
        generateFirstCard();

        // Generate the all cards except the first and last card(ALL - 2), then adds is to the arrayOfCards stack.
        generateCardsMinusFirstLast();

        // Generate the last card, then adds it to the arrayOfCards stack.
        generateLastCard();

        // Add desired unit of cards to the arrayOfCardsFinal deck.
        appendDeck();

        // One last shuffle before we return.
        Collections.shuffle(arrayOfCardsFinal);

        return arrayOfCardsFinal; //end of DeckGeneratorImpl logic
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

    private void generateCardsMinusFirstLast() {
        List<MutableImage> images = new ArrayList<>();
        for (int i = 0; i < ORDER; i++) {
            for (int j = 0; j < ORDER; j++) {
                for (int k = 0; k < ORDER; k++) {
                    MutableImage image = new ImageImpl(k * ORDER + ((j + i * k) % ORDER));
                    images.add(image);
                }
                MutableImage image = new ImageImpl(ORDER * ORDER + i + 1);
                images.add(image);
                arrayOfCardsInit.push(cardGenerator.generate(images));
                images.clear();
            }
        }
    }

    private void generateLastCard() {
        List<MutableImage> images = new ArrayList<>();
        for (int i = 0; i < ORDER + 1; i++) {
            MutableImage image = new ImageImpl(ORDER * ORDER + i);
            images.add(image);
        }
        arrayOfCardsInit.push(cardGenerator.generate(images));
    }

    private void appendDeck() {
        for (int i = 0; i < SIZE; i++) {
            arrayOfCardsFinal.push(arrayOfCardsInit.pop());
        }
    }
}

