package cmpt276.proj.finddamatch.model.gameLogic;

import android.os.SystemClock;

import java.util.Stack;

import cmpt276.proj.finddamatch.BuildConfig;
import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.CardGenerator;
import cmpt276.proj.finddamatch.model.Game;
import cmpt276.proj.finddamatch.model.Image;

import static java.lang.Boolean.TRUE;


public class GameImplementation implements Game {
    long referenceTime;
    long elapseTime;
    CardGenerator dealer;
    Stack<Card> drawPile;
    Stack<Card> discardPile;

    public GameImplementation(CardGenerator dealer) {
        this.referenceTime = SystemClock.elapsedRealtime();
        this.dealer = dealer;
        this.drawPile = dealer.generate();
        this.discardPile = new Stack<>();
        this.elapseTime = 0;
    }

    public boolean check(Image image) {
        return drawPile.peek().exists(image) && discardPile.peek().exists(image);
    }

    public Card draw() {
        if (BuildConfig.DEBUG && drawPile.isEmpty() != TRUE) {
            throw new AssertionError("Empty Stack");
        }
        Card card = drawPile.pop();
        discardPile.push(card);
        return card;
    }

    //TODO verify if update is an unnecessary method. Delete if yes, complete if no.
    public void update(Image image) {}

    public void reset() {
        discardPile.clear();
        drawPile = dealer.generate();
        this.referenceTime = SystemClock.elapsedRealtime();
        this.elapseTime = 0;
    }

    public void pause() {
        long previousTime = referenceTime;
        referenceTime = SystemClock.elapsedRealtime();
        elapseTime += referenceTime - previousTime;
    }

    public void resume() {
        referenceTime = SystemClock.elapsedRealtime();
    }

    public boolean isGameDone() {
        return drawPile.empty();
    }

    public long queryTime() {
        return this.elapseTime;
    }

    public long getScore() {
        return this.elapseTime;
    }


}
