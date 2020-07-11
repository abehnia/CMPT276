package cmpt276.proj.finddamatch.model.gameLogic;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.CardGenerator;
import cmpt276.proj.finddamatch.model.Game;
import cmpt276.proj.finddamatch.model.Image;

import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.*;

class GameImplTest {
    TestCardGeneratorImpl dealer = new TestCardGeneratorImpl();
    GameImpl g = new GameImpl(dealer, 0);
    CardImpl c = new CardImpl();

    @org.junit.jupiter.api.Test
    void check() {
        Card refDrawCard = g.peekDraw();
        Image drawImg = refDrawCard.get(2);
        assertTrue(g.check(drawImg));
    }

    @org.junit.jupiter.api.Test
    void draw() {

    }

    @org.junit.jupiter.api.Test
    void update() {
    }

    @org.junit.jupiter.api.Test
    void reset() {
    }

    @org.junit.jupiter.api.Test
    void pause() {
    }

    @org.junit.jupiter.api.Test
    void resume() {
    }

    @org.junit.jupiter.api.Test
    void peekDiscard() {
    }

    @org.junit.jupiter.api.Test
    void peekDraw() {
    }

    @org.junit.jupiter.api.Test
    void isGameDone() {
    }

    @org.junit.jupiter.api.Test
    void queryTime() {
    }
}