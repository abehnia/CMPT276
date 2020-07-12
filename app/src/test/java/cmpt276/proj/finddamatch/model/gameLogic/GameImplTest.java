package cmpt276.proj.finddamatch.model.gameLogic;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.Image;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameImplTest {


    @org.junit.jupiter.api.Test
    void check() {
        TestCardGeneratorImpl dealer = new TestCardGeneratorImpl();
        GameImpl g = new GameImpl(dealer, 0);

        Card refDrawCard = g.peekDraw();
        Image drawImg = refDrawCard.get(2);
        assertTrue(g.check(drawImg));
    }

    @org.junit.jupiter.api.Test
    void draw() {
        TestCardGeneratorImpl dealer = new TestCardGeneratorImpl();
        GameImpl g = new GameImpl(dealer, 0);

        while (!g.drawPile.isEmpty() && !g.discardPile.isEmpty()) {
            g.drawPile.pop();
            g.discardPile.pop();
        }
        assertTrue(g.drawPile.isEmpty());
        assertTrue(g.discardPile.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void update() {
    }

    @org.junit.jupiter.api.Test
    void reset() {
        TestCardGeneratorImpl dealer = new TestCardGeneratorImpl();
        long input = 0;
        GameImpl g = new GameImpl(dealer, input);

        g.discardPile.pop();
        assertTrue(g.discardPile.isEmpty());
        g.reset(0);
        for (int i = 0; i <= 0; i++) {
            g.drawPile.pop();
        }
        assertTrue(g.drawPile.isEmpty());
        assertEquals(input, g.referenceTime);
    }

    @org.junit.jupiter.api.Test
    void pause() {
        TestCardGeneratorImpl dealer = new TestCardGeneratorImpl();
        long input1 = 2;
        long input2 = 3;
        GameImpl g = new GameImpl(dealer, input1);
        assertEquals(2, g.referenceTime);

        g.pause(input2);
        assertEquals(3, g.referenceTime);
        assertEquals(1, g.elapsedTime);
    }

    @org.junit.jupiter.api.Test
    void resume() {
        TestCardGeneratorImpl dealer = new TestCardGeneratorImpl();
        long input1 = 11;
        long input2 = 500;
        GameImpl g = new GameImpl(dealer, input1);

        g.resume(input2);
        assertEquals(500, g.referenceTime);
    }

    @org.junit.jupiter.api.Test
    void peekDiscard() {
        TestCardGeneratorImpl dealer = new TestCardGeneratorImpl();
        long input1 = 11;
        GameImpl g = new GameImpl(dealer, input1);

        Card refDiscard = g.peekDiscard();
        Image discardImg = refDiscard.get(2);

        assertEquals(4, discardImg.getID());
    }

    @org.junit.jupiter.api.Test
    void peekDraw() {
        TestCardGeneratorImpl dealer = new TestCardGeneratorImpl();
        long input1 = 11;
        GameImpl g = new GameImpl(dealer, input1);

        Card refDraw = g.peekDraw();
        Image drawImg = refDraw.get(2);

        assertEquals(2, drawImg.getID());
    }

    @org.junit.jupiter.api.Test
    void isGameDone() {
        TestCardGeneratorImpl dealer = new TestCardGeneratorImpl();
        long input1 = 11;
        GameImpl g = new GameImpl(dealer, input1);

        g.drawPile.pop();
        assertTrue(g.drawPile.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void queryTime() {
        TestCardGeneratorImpl dealer = new TestCardGeneratorImpl();
        long input1 = 2;
        long input2 = 3;
        GameImpl g = new GameImpl(dealer, input1);
        assertEquals(2, g.referenceTime);

        g.queryTime(input2);
        assertEquals(3, g.referenceTime);
        assertEquals(1, g.elapsedTime);


    }
}