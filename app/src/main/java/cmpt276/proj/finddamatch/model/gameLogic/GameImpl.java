package cmpt276.proj.finddamatch.model.gameLogic;

import android.os.SystemClock;

import java.util.Stack;

import cmpt276.proj.finddamatch.BuildConfig;
import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.CardGenerator;
import cmpt276.proj.finddamatch.model.Game;
import cmpt276.proj.finddamatch.model.Image;


public class GameImpl implements Game {
    long referenceTime;
    long elapsedTime;
    CardGenerator dealer;
    Stack<Card> drawPile;
    Stack<Card> discardPile;

    public GameImpl(CardGenerator dealer) {
        this.referenceTime = SystemClock.elapsedRealtime();
        this.dealer = dealer;
        this.drawPile = dealer.generate();
        this.discardPile = new Stack<>();
        this.elapsedTime = 0;
    }

    @Override
    public boolean check(Image image) {
        return drawPile.peek().exists(image) && discardPile.peek().exists(image);
    }

    @Override
    public Card draw() {
        if (BuildConfig.DEBUG && drawPile.isEmpty()) {
            throw new AssertionError("Empty Stack");
        }
        Card card = drawPile.pop();
        discardPile.push(card);
        return card;
    }

    @Override
    public void update(Image image) {
    }

    @Override
    public void reset() {
        discardPile.clear();
        drawPile = dealer.generate();
        referenceTime = SystemClock.elapsedRealtime();
        elapsedTime = 0;
    }

    @Override
    public void pause() {
        updateTime();
    }

    @Override
    public void resume() {
        referenceTime = SystemClock.elapsedRealtime();
    }

    @Override
    public boolean isGameDone() {
        return drawPile.empty();
    }

    @Override
    public long queryTime() {
        updateTime();
        return elapsedTime;
    }

    private void updateTime() {
        long previousTime = referenceTime;
        referenceTime = SystemClock.elapsedRealtime();
        elapsedTime += referenceTime - previousTime;
    }
}
