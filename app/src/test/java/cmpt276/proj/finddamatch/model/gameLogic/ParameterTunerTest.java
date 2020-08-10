package cmpt276.proj.finddamatch.model.gameLogic;

import org.junit.jupiter.api.Test;

import cmpt276.proj.finddamatch.model.Game;
import cmpt276.proj.finddamatch.model.GameMode;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for ParameterTuner
 */

class ParameterTunerTest {

    @Test
    void getLowerRadiusBound() {
        GameMode gameMode = new GameModeImpl(2, 7, true);
        ParameterTuner parameterTuner = new ParameterTuner(gameMode);
//        parameterTuner.setLowerRadiusBound();
        assertEquals(0.45f, parameterTuner.getLowerRadiusBound());
    }

    @Test
    void getUpperRadiusBound() {
        GameMode gameMode = new GameModeImpl(2, 7, true);
        ParameterTuner parameterTuner = new ParameterTuner(gameMode);
//        parameterTuner.setUpperRadiusBound();
        assertEquals(0.70f, parameterTuner.getUpperRadiusBound());
    }

    @Test
    void setLowerRadiusBound() {
        GameMode gameMode = new GameModeImpl(3, 13, true);
        ParameterTuner parameterTuner = new ParameterTuner(gameMode);
//        parameterTuner.setLowerRadiusBound();
        assertEquals(0.375f, parameterTuner.getLowerRadiusBound());

        GameMode gameModeNext = new GameModeImpl(5, 31, true);
        ParameterTuner parameterTunerNext = new ParameterTuner(gameModeNext);
//        parameterTunerNext.setLowerRadiusBound();
        assertEquals(0.275f, parameterTunerNext.getLowerRadiusBound());
    }

    @Test
    void setUpperRadiusBound() {
        GameMode gameMode = new GameModeImpl(3, 13, true);
        ParameterTuner parameterTuner = new ParameterTuner(gameMode);
//        parameterTuner.setUpperRadiusBound();
        assertEquals(0.625f, parameterTuner.getUpperRadiusBound());

        GameMode gameModeNext = new GameModeImpl(5, 31, true);
        ParameterTuner parameterTunerNext = new ParameterTuner(gameModeNext);
//        parameterTunerNext.setUpperRadiusBound();
        assertEquals(0.525f, parameterTunerNext.getUpperRadiusBound());
    }
}