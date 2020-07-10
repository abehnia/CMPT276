package cmpt276.proj.finddamatch.model.gameLogic;

import android.os.SystemClock;

import java.util.Stack;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.CardGenerator;
import cmpt276.proj.finddamatch.model.Game;
import cmpt276.proj.finddamatch.model.Image;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class GameImplementation implements Game {
    long start_real_time = SystemClock.elapsedRealtime();
    long end_real_time;
    long ref_pause_time;
    long ref_resume_time;
    long inactive_time;
    long elapsed_game_time;

    Card card = new CardImpl();
    CardGenerator dealer = new CardGeneratorImpl();
    Stack<Card> draw_pile;
    Stack<Card> discard_pile;
    int top;
    int counter = 0;

    public GameImplementation(){
        this.draw_pile = dealer.generate();
    }

    public boolean check(Image image){
        //assume the parameter image is coming from discard_pile
        if(draw_pile.peek().exists(image) == TRUE && discard_pile.peek().exists(image)){
            counter += 1;
            return TRUE;
        }else{
            return FALSE;
        }
    }

    public Card draw(){
        assert draw_pile.isEmpty() == TRUE: " Empty Stack";
        Card card = draw_pile.peek();

        discard_pile.push(draw_pile.pop());

        return card;
    }

    //TODO verify if update is an unnecessary method. Delete if yes, complete if no.
    public void update(Image image){}

    public void reset(){
        assert discard_pile.isEmpty() == FALSE: "Game is reset";

        while (discard_pile.isEmpty() == FALSE) {
            discard_pile.pop();
        }

        this.draw_pile = dealer.generate();
    }

    public void pause(){
        ref_pause_time = SystemClock.elapsedRealtime();
    }

    public long resume(){
        ref_resume_time = SystemClock.elapsedRealtime();
        inactive_time = ref_pause_time - ref_resume_time;
        return inactive_time;
    }

    public boolean isGameDone(){
        assert draw_pile.isEmpty() == TRUE: "Cards left to play";

        long end_real_time = SystemClock.elapsedRealtime();
        elapsed_game_time =  (end_real_time - start_real_time) + inactive_time;

        return TRUE;
    }

    public long queryTime(){return this.elapsed_game_time;}

    public int getScore(){return this.counter;}




}
