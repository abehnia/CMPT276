package cmpt276.proj.finddamatch.gameLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.ImageMockImpl;

public class CardsGenerator {
    private static int NUMBER_OF_CARDS = 7;
    private List<Card> arrOfCards;
    private List<Card> tempList; // to store the card has been removed

    private CardsGenerator() {
        tempList = new ArrayList<>();
        arrOfCards = new ArrayList<>(NUMBER_OF_CARDS);
        arrOfCards.set(0, new CardImpl(new ImageImpl(0.5f, 0, 1, 0, 0),
                new ImageImpl(-0.5f, 0, 1, 0, 1),
                new ImageMockImpl(0, 0.5f, 1, 0, 2)));
        arrOfCards.set(0, new CardImpl(new ImageImpl(0.5f, 0, 1, 0, 2),
                new ImageImpl(-0.5f, 0, 1, 0, 3),
                new ImageMockImpl(0, 0.5f, 1, 0, 4)));
        arrOfCards.set(0, new CardImpl(new ImageImpl(0.5f, 0, 1, 0, 0),
                new ImageImpl(-0.5f, 0, 1, 0, 4),
                new ImageMockImpl(0, 0.5f, 1, 0, 5)));
        arrOfCards.set(0, new CardImpl(new ImageImpl(0.5f, 0, 1, 0, 0),
                new ImageImpl(-0.5f, 0, 1, 0, 3),
                new ImageMockImpl(0, 0.5f, 1, 0, 6)));
        arrOfCards.set(0, new CardImpl(new ImageImpl(0.5f, 0, 1, 0, 1),
                new ImageImpl(-0.5f, 0, 1, 0, 4),
                new ImageMockImpl(0, 0.5f, 1, 0, 6)));
        arrOfCards.set(0, new CardImpl(new ImageImpl(0.5f, 0, 1, 0, 1),
                new ImageImpl(-0.5f, 0, 1, 0, 3),
                new ImageMockImpl(0, 0.5f, 1, 0, 5)));
        arrOfCards.set(0, new CardImpl(new ImageImpl(0.5f, 0, 1, 0, 2),
                new ImageImpl(-0.5f, 0, 1, 0, 5),
                new ImageMockImpl(0, 0.5f, 1, 0, 6)));
        for (int i = 0; i < arrOfCards.size(); ++i) {
            arrOfCards.get(i).randomize();
        }

        this.cardsShuffle();
    }

    private static CardsGenerator instance;

    public static CardsGenerator getInstance() {
        if (instance == null) instance = new CardsGenerator();
        return instance;
    }

    public int size() {
        return arrOfCards.size();
    }

    public boolean isEmpty() {
        return arrOfCards.isEmpty();
    }

    public void push(Card card) {
        arrOfCards.add(card);
    }

    public Card pop() {
        Card popValue = arrOfCards.get(0);
        arrOfCards.remove(0);
        tempList.add(popValue);
        return popValue;
    }

    public Card peek() {
        return arrOfCards.get(0);
    }

    public void cardsShuffle() {
        Random random = new Random();
        for (int i = 0; i < arrOfCards.size(); ++i) {
            int randomIndexToSwap = random.nextInt(arrOfCards.size());
            Card temp = arrOfCards.get(randomIndexToSwap);
            arrOfCards.set(randomIndexToSwap, arrOfCards.get(i));
            arrOfCards.set(i, temp);
        }
    }

}

