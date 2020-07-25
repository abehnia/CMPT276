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
import static cmpt276.proj.finddamatch.model.gameLogic.VALID_GAME_MODE.GAME5;

/**
 * Class for generating a randomized stack of Cards
 */
public class DeckGeneratorImpl implements DeckGenerator {

    public int PLAYER_ORDER_SELECTION = GAME5.getOrder();
    public final int ORDER = GAME5.getOrder();
    public final int SIZE = GAME5.getSize();

    CardGenerator cardGenerator;

    Stack<Card> arrayOfCardsInit = new Stack<>();
    ArrayList<MutableImage> images = new ArrayList<>();
    Stack<Card> arrayOfCardsFinal = new Stack<>();

    public DeckGeneratorImpl(CardGenerator cardGenerator) {
        this.cardGenerator = cardGenerator;
        double cardx = (int) Math.sqrt(PLAYER_ORDER_SELECTION);
        for (int i = 2; i < 1 + cardx; i++) {
            if (PLAYER_ORDER_SELECTION % i == 0) {
                PLAYER_ORDER_SELECTION = i;
                break;
            }
        }
    }

    @Override
    public Stack<Card> generate() {
        //TODO If logic for when a brand new game is initiated run
        if(arrayOfCardsInit.empty() && arrayOfCardsFinal.empty()) {
            arrayOfCardsInit.clear();
            arrayOfCardsFinal.clear();

            generateFirstCard();
            generateCardsMinusFirstLast();
            generateLastCard();
            appendDeck();
            Collections.shuffle(arrayOfCardsFinal);

            return arrayOfCardsFinal;
        }
        //TODO If logic for when a player initiates a new game from previous play
        else
        {
             Collections.shuffle(arrayOfCardsInit);
             arrayOfCardsFinal.clear();
             appendDeck();

             return arrayOfCardsFinal;
        }
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

