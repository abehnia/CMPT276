package cmpt276.proj.finddamatch.model.gameLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.DeckGenerator;
import cmpt276.proj.finddamatch.model.CardGenerator;
import cmpt276.proj.finddamatch.model.Image;
import cmpt276.proj.finddamatch.model.MutableImage;

import static cmpt276.proj.finddamatch.model.gameLogic.VALID_GAME_MODE.GAME1;

/**
 * Class for generating a randomized stack of Cards
 */
public class DeckGeneratorImpl implements DeckGenerator {

    public int ORDER = GAME1.getOrder();
    public int NUM_OF_IMAGES_PER_CARD = ORDER - 1;
    public int ALL = ORDER * ORDER + ORDER + 1;
    CardGenerator cardGenerator;

    Stack<Card> arrayOfCards = new Stack<>();
    ArrayList<MutableImage> images = new ArrayList<>();

    public DeckGeneratorImpl(CardGenerator cardGenerator) {
        this.cardGenerator = cardGenerator;
    }

    @Override
    public Stack<Card> generate() {
        // Generate the first card, then adds it to the arrayOfCards stack.
        generateFirstCard();

        // Generate the all cards except the first and last card(ALL - 2), then adds is to the arrayOfCards stack.
        generateCardsMinusFirstLast();

        // Generate the last card, then adds it to the arrayOfCards stack.
        generateLastCard();

        // One last shuffle before we return.
        Collections.shuffle(arrayOfCards);

        return arrayOfCards; //end of DeckGeneratorImpl logic

//        //Generate a shuffled deck of cards, using PRE_GENERATED_CARDS CLASS
//        generateSevenPregeneratedCards(arrayOfCards);
    }

    private void generateFirstCard(){
        ArrayList images = new ArrayList<Card>();
        for (int i= 0; i < ORDER; i++){
            Card card = new CardImpl();
            for (int j = 0; j < ORDER; j++){
                Image image = new ImageImpl(i * ORDER + j);
                images.add(image);
            }
            Image image = new ImageImpl(ORDER * ORDER);
            images.add(image);
            arrayOfCards.push(new CardImpl(images));
        }
    }

    private void generateCardsMinusFirstLast(){
        ArrayList images = new ArrayList<Card>();
        for (int i = 0; i < ORDER; i++) {
            for (int j = 0; j < ORDER; j++) {
                Card card = new CardImpl();
                for (int k = 0; k < ORDER; k++) {
                    Image image = new ImageImpl(k * ORDER + ((j + i * k) % ORDER));
                    images.add(image);
                }
                Image image = new ImageImpl(ORDER * ORDER + i + 1);
                images.add(image);
                arrayOfCards.push(new CardImpl(images));
            }
        }
    }

    private void generateLastCard(){
        ArrayList images = new ArrayList<Card>();
        for (int i = 0; i < ORDER + 1; i++){
            Image image = new ImageImpl(ORDER * ORDER + i);
            images.add(image);
        }
        arrayOfCards.push(new CardImpl(images));
    }

//    private Stack<Card> generateSevenPregeneratedCards(Stack<Card> arrayOfCards){
//        for (Card card : PRE_GENERATED_CARDS.values()) {
//            for (Image image : card) {
//                images.add(new ImageImpl(image.getID()));
//            }
//            arrayOfCards.push(cardGenerator.generate(images));
//            images.clear();
//        }
//        Collections.shuffle(arrayOfCards);
//        return arrayOfCards;
//    }
}

