package cmpt276.proj.finddamatch.gameLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.ImageMockImpl;

import static cmpt276.proj.finddamatch.gameLogic.CardImpl.CARD1;
import static cmpt276.proj.finddamatch.gameLogic.CardImpl.PRE_GENERATED_CARDS;
import static cmpt276.proj.finddamatch.model.Card.NUMBER_OF_CARDS;

public class CardsGenerator {
    private List<Card> arrOfCards;
    private List<Card> tempList; // to store the card has been removed


    private CardsGenerator() {
        tempList = new ArrayList<>(NUMBER_OF_CARDS);
        arrOfCards = new ArrayList<>(NUMBER_OF_CARDS);

//        for(int i=0; i < NUMBER_OF_CARDS; i++){
//            arrOfCards.add(PRE_GENERATED_CARDS.get(i));
//        }

        //Refactored version of above
        for(Card card : PRE_GENERATED_CARDS){
            arrOfCards.add(card);
        }

        //Final Refactor Saiyin level 9000
        arrOfCards.addAll(PRE_GENERATED_CARDS);

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

