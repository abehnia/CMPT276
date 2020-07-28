package cmpt276.proj.finddamatch.model.gameLogic;

import java.util.Stack;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.CardGenerator;
import cmpt276.proj.finddamatch.model.DeckGenerator;
import cmpt276.proj.finddamatch.model.Game;
import cmpt276.proj.finddamatch.model.GameMode;
import cmpt276.proj.finddamatch.model.Image;

import static org.junit.jupiter.api.Assertions.*;

/**
 * GameImpl class test, used to verify each method
 */
class GameImplTest {

    @org.junit.jupiter.api.Test
    void check() {
        GameMode gameMode = new GameModeImpl(2, 7, true);
        ParameterTuner parameterTuner = new ParameterTuner(gameMode);
        CardGenerator c = new CardGeneratorImpl(parameterTuner, true);
        DeckGenerator dealer = new DeckGeneratorImpl(c, gameMode);
        GameImpl g = new GameImpl(dealer, 0);
        Card refDrawCard = g.peekDraw();
        Image drawImg = refDrawCard.get(2);
        assertTrue(g.check(drawImg));
    }

    @org.junit.jupiter.api.Test
    void draw() {
        GameMode gameMode = new GameModeImpl(2, 7, true);
        ParameterTuner parameterTuner = new ParameterTuner(gameMode);
        CardGenerator c = new CardGeneratorImpl(parameterTuner, true);
        DeckGenerator dealer = new DeckGeneratorImpl(c, gameMode);
        Game g = new GameImpl(dealer, 0);
        Stack<Card> cards = dealer.generate();
        assertEquals(cards.peek(), g.draw());
//        while (!g.drawPile.isEmpty() && !g.discardPile.isEmpty()) {
//            g.drawPile.pop();
//            g.discardPile.pop();
//        }
//        assertTrue(g.drawPile.isEmpty());
//        assertTrue(g.discardPile.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void update() {
        GameMode gameMode = new GameModeImpl(2, 7, true);
        ParameterTuner parameterTuner = new ParameterTuner(gameMode);
        CardGenerator c = new CardGeneratorImpl(parameterTuner, true);
        DeckGenerator dealer = new DeckGeneratorImpl(c, gameMode);
        Game g = new GameImpl(dealer, 0);
        Image image = new ImageImpl(1);
        g.update(image);
    }

    @org.junit.jupiter.api.Test
    void reset() {
        GameMode gameMode = new GameModeImpl(2, 7, true);
        ParameterTuner parameterTuner = new ParameterTuner(gameMode);
        CardGenerator c = new CardGeneratorImpl(parameterTuner, true);
        DeckGenerator dealer = new DeckGeneratorImpl(c, gameMode);
        long input = 0;
        GameImpl g = new GameImpl(dealer, input);
        g.reset(0);
        assertFalse(g.isPaused());

//        g.discardPile.pop();
//        assertTrue(g.discardPile.isEmpty());
//        g.reset(0);
//        for (int i = 0; i <= 0; i++) {
//        g.drawPile.pop();
//    }
//    assertTrue(g.drawPile.isEmpty());
//    assertEquals(input, g.referenceTime);
}

    @org.junit.jupiter.api.Test
    void pause() {
        GameMode gameMode = new GameModeImpl(2, 7, true);
        ParameterTuner parameterTuner = new ParameterTuner(gameMode);
        CardGenerator c = new CardGeneratorImpl(parameterTuner, true);
        DeckGenerator dealer = new DeckGeneratorImpl(c, gameMode);
        long input1 = 2;
        long input2 = 3;
        GameImpl g = new GameImpl(dealer, input1);
//        assertEquals(2, g.referenceTime);
////
////        g.pause(input2);
////        assertEquals(3, g.referenceTime);
////        assertEquals(1, g.elapsedTime);
        g.pause(input2);
        assertTrue(g.isPaused());
    }

    @org.junit.jupiter.api.Test
    void resume() {
        GameMode gameMode = new GameModeImpl(2, 7, true);
        ParameterTuner parameterTuner = new ParameterTuner(gameMode);
        CardGenerator c = new CardGeneratorImpl(parameterTuner, true);
        DeckGenerator dealer = new DeckGeneratorImpl(c, gameMode);
        long input1 = 11;
        long input2 = 500;
        GameImpl g = new GameImpl(dealer, input1);

        g.resume(input2);
    //    assertEquals(500, g.referenceTime);
        assertFalse(g.isPaused());
    }

    @org.junit.jupiter.api.Test
    void peekDiscard() {
        DeckGenerator dealer = new TestDeckGeneratorImpl();
        long input1 = 11;
        GameImpl g = new GameImpl(dealer, input1);

        Card refDiscard = g.peekDiscard();
        Image discardImg = refDiscard.get(2);

        assertEquals(4, discardImg.getID());
    }

    @org.junit.jupiter.api.Test
    void peekDraw() {
        DeckGenerator dealer = new TestDeckGeneratorImpl();
        long input1 = 11;
        GameImpl g = new GameImpl(dealer, input1);

        Card refDraw = g.peekDraw();
        Image drawImg = refDraw.get(2);

        assertEquals(2, drawImg.getID());
    }

    @org.junit.jupiter.api.Test
    void isGameDone() {
        DeckGenerator dealer = new TestDeckGeneratorImpl();
        long input1 = 11;
        GameImpl g = new GameImpl(dealer, input1);

        g.draw();
        assertTrue(g.isGameDone());
    }

    @org.junit.jupiter.api.Test
    void queryTime() {
        DeckGenerator dealer = new TestDeckGeneratorImpl();
        long input1 = 2;
        long input2 = 3;
        GameImpl g = new GameImpl(dealer, input1);
//        assertEquals(2, g.referenceTime);

//        g.queryTime(input2);
//        assertEquals(3, g.referenceTime);
        assertEquals(1, g.queryTime(input2));
    }
}
