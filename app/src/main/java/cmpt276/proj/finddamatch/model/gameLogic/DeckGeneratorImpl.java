package cmpt276.proj.finddamatch.model.gameLogic;

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

    public int NUM_OF_IMAGES_PER_CARD;
    public int ORDER = NUM_OF_IMAGES_PER_CARD - 1;
    public int ALL = ORDER * ORDER + ORDER + 1;
    CardGenerator cardGenerator;

    Stack<Card> arrayOfCards = new Stack<>();
    ArrayList<MutableImage> images = new ArrayList<>();

    public DeckGeneratorImpl(CardGenerator cardGenerator) {
        this.cardGenerator = cardGenerator;
    }

    @Override
    public Stack<Card> generate() {
        // Generate the first card, then returns the stack with first card in it.
        generateFirstCard(arrayOfCards);

        // Generate the all cards except the first and last card(ALL - 2), then returns stack with cards added.
        generateCardsMinusFirstLast(arrayOfCards);

        // Generate the last card, returns stack with last card added to it.
        generateLastCard(arrayOfCards);

        return arrayOfCards;
    }

    private Stack<Card> generateFirstCard(Stack<Card> arrayOfCards){
        for (int i= 0; i < ORDER; i++){
            Card card = new CardImpl();
            for (int j = 0; j < ORDER; j++){
                Image image = new ImageImpl(i * ORDER + j);
                card.add(image);
            }
            Image image = new ImageImpl(ORDER * ORDER);
            card.add(image);
            arrayOfCards.push(card);
        }
        return arrayOfCards;
    }

    private Stack <Card> generateCardsMinusFirstLast(Stack<Card> arrayOfCards){
        for (int i = 0; i < ORDER; i++){
            for (int j = 0; j < ORDER; j++){
                Card card = new CardImpl();
                for( int k = 0; k < ORDER; k++){
                    Image image = new ImageImpl(k * ORDER + ((j + i * k) % ORDER));
                    card.add(image);
                }
                Image image = new ImageImpl(ORDER * ORDER + i + 1);
                card.add(image);
                arrayOfCards.push(card);
            }
        }
        return arrayOfCards;
    }

    private Stack<Card> generateLastCard(Stack<Card> arrayOfCards){
        Card lastCard = new CardImpl();
        for (int i = 0; i < ORDER + 1; i++){
            Image image = new ImageImpl(ORDER * ORDER + i);
            lastCard.add(image);
        }
        arrayOfCards.push(lastCard);
        return arrayOfCards;
    }

    private Stack<Card> generateSevenPregeneratedCards(Stack<Card> arrayOfCards){
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

