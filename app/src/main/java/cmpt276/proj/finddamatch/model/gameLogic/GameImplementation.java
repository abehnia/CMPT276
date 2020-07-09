package cmpt276.proj.finddamatch.model.gameLogic;

import android.os.SystemClock;

import java.util.Stack;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.Game;
import cmpt276.proj.finddamatch.model.Image;

import static java.lang.Boolean.TRUE;


public class GameImplementation implements Game {
    long time = SystemClock.elapsedRealtime();

    Card card = new CardImpl();
    CardsGenerator dealer = new CardsGenerator();
    Stack<Card> deck;
    int counter = 0;

    public GameImplementation(){
        this.deck = dealer.getInstance();

    }
    public boolean check(Image image){
      boolean outcome = image.isEquivalent(image);

      return outcome;
    }

    public void update(Image image){

    }

    public Card poll(){
        Card card1 = deck.peek(0);


    }


}
