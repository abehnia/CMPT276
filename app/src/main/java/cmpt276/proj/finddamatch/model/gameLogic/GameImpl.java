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

    public GameImpl(CardGenerator dealer, long time) {
        this.referenceTime = time;
        this.dealer = dealer;
        this.drawPile = dealer.generate();
        this.discardPile = new Stack<>();
        this.elapsedTime = 0;
        draw();
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
    public void reset(long time) {
        discardPile.clear();
        drawPile = dealer.generate();
        referenceTime = time;
        elapsedTime = 0;
        draw();
    }

    @Override
    public void pause(long time) {
        updateTime(time);
    }

    @Override
    public void resume(long time) {
        referenceTime = time;
    }

    @Override
    public Card peekDiscard() {
        if (BuildConfig.DEBUG && discardPile.empty()) {
            throw new AssertionError("Stack Empty");
        }
        return discardPile.peek();
    }

    @Override
    public Card peekDraw() {
        if (BuildConfig.DEBUG && discardPile.empty()) {
            throw new AssertionError("Stack Empty");
        }
        return drawPile.peek();
    }

    @Override
    public boolean isGameDone() {
        return drawPile.empty();
    }

    @Override
    public long queryTime(long time) {
        updateTime(time);
        return elapsedTime;
    }

    private void updateTime(long time) {
        long previousTime = referenceTime;
        referenceTime = time;
        elapsedTime += referenceTime - previousTime;
    }
}
