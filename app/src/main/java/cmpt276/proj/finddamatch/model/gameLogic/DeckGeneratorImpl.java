package cmpt276.proj.finddamatch.model.gameLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.DeckGenerator;
import cmpt276.proj.finddamatch.model.CardGenerator;
import cmpt276.proj.finddamatch.model.MutableImage;

import static cmpt276.proj.finddamatch.model.gameLogic.VALID_GAME_MODE.GAME5;

/**
 * Class for generating a randomized stack of Cards
 */
public class DeckGeneratorImpl implements DeckGenerator {

    public int playerOrderSelection;
    public int order;
    public int size;

    private CardGenerator cardGenerator;
    Stack<Card> arrayOfCardsInit = new Stack<>();
    Stack<Card> arrayOfCardsFinal = new Stack<>();

    public DeckGeneratorImpl(CardGenerator cardGenerator) {
        this.playerOrderSelection = GAME5.getOrder();
        this.order = GAME5.getOrder();
        this.size = GAME5.getSize();
        this.cardGenerator = cardGenerator;
        fixPlayerChoice();
    }

    private void fixPlayerChoice() {
        double cardX = (int) Math.sqrt(playerOrderSelection);
        for (int i = 2; i < 1 + cardX; i++) {
            if (playerOrderSelection % i == 0) {
                playerOrderSelection = i;
                break;
            }
        }
    }

    @Override
    public Stack<Card> generate() {
        if (!arrayOfCardsInit.isEmpty()) {
            Collections.shuffle(arrayOfCardsInit);
            appendDeck();
            return arrayOfCardsFinal;
        }
        generateFirstCard();
        generateCardsMinusFirstLast();
        generateLastCard();
        Collections.shuffle(arrayOfCardsInit);
        appendDeck();
        return arrayOfCardsFinal;
    }

    private void generateFirstCard() {
        List<MutableImage> images = new ArrayList<>();
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                ImageImpl image = new ImageImpl(i * order + j);
                images.add(image);
            }
            ImageImpl image = new ImageImpl(order * order);
            images.add(image);
            arrayOfCardsInit.push(cardGenerator.generate(images));
            images.clear();
        }
    }

    private void generateCardsMinusFirstLast() {
        List<MutableImage> images = new ArrayList<>();
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < order; j++) {
                for (int k = 0; k < order; k++) {
                    MutableImage image = new ImageImpl(k * order + ((j + i * k) % order));
                    images.add(image);
                }
                MutableImage image = new ImageImpl(order * order + i + 1);
                images.add(image);
                arrayOfCardsInit.push(cardGenerator.generate(images));
                images.clear();
            }
        }
    }

    private void generateLastCard() {
        List<MutableImage> images = new ArrayList<>();
        for (int i = 0; i < order + 1; i++) {
            MutableImage image = new ImageImpl(order * order + i);
            images.add(image);
        }
        arrayOfCardsInit.push(cardGenerator.generate(images));
    }

    private void appendDeck() {
        arrayOfCardsFinal.clear();
        int i = 0;
        for (Card card : arrayOfCardsInit) {
            arrayOfCardsFinal.push(card);
            ++i;
            if (i == size) {
                break;
            }
        }
    }
}

