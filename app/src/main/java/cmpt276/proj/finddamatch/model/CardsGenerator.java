package cmpt276.proj.finddamatch.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardsGenerator {
    private static int NUMBER_OF_CARDS = 7;
    private List<Card> arrOfCards;

    private CardsGenerator() {
        arrOfCards = new ArrayList<>(NUMBER_OF_CARDS);
        for (int i = 0; i < arrOfCards.size(); ++i){
            arrOfCards.get(i).randomize();
        }
    }

    private static CardsGenerator instance;

    public static CardsGenerator getInstance() {
        if (instance == null) instance = new CardsGenerator();
        return instance;
    }

    public boolean isEmpty() {
        if (arrOfCards.isEmpty()) return true;
        else return false;
    }

    public void push(Card card) {
        arrOfCards.add(card);
    }

    public Card pop() {
        Card popValue = arrOfCards.get(arrOfCards.size() - 1);
        arrOfCards.remove(arrOfCards.size() - 1);
        arrOfCards.add(popValue);
        return popValue;
    }

    public Card peek() {
        return arrOfCards.get(arrOfCards.size() - 1);
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

