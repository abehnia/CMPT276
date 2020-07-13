package cmpt276.proj.finddamatch.model.gameLogic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.DeckGenerator;
import cmpt276.proj.finddamatch.model.CardGenerator;
import cmpt276.proj.finddamatch.model.Image;
import cmpt276.proj.finddamatch.model.MutableImage;

/**
 * Class for generating a randomized stack of Cards
 */
public class DeckGeneratorImpl implements DeckGenerator {
    CardGenerator cardGenerator;

    public DeckGeneratorImpl(CardGenerator cardGenerator) {
        this.cardGenerator = cardGenerator;
    }

    @Override
    public Stack<Card> generate() {
        Stack<Card> arrayOfCards = new Stack<>();
        ArrayList<MutableImage> images = new ArrayList<>();
        for (Card card : PRE_GENERATED_CARDS.values()) {
            for (Image image : card) {
                images.add(new ImageImpl(image.getID()));
            }
            arrayOfCards.push(cardGenerator.generate(images));
            images.clear();
        }
        Collections.shuffle(arrayOfCards);
        return arrayOfCards;
    }
}

