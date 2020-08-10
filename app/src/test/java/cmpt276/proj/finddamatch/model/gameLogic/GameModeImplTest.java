package cmpt276.proj.finddamatch.model.gameLogic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cmpt276.proj.finddamatch.model.GameMode;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Test for GameModeImpl
 * */

class GameModeImplTest {

    @Test
    void getOrder() {
        // Test parametrized constructor
        GameMode gameMode = new GameModeImpl(2, 7, true);
        assertEquals(2, gameMode.getOrder());

        // Test VALID_GAME_MODE enum
        List<ValidGameMode> validGameModes = new ArrayList<>(Arrays.asList(ValidGameMode.values()));
        assertEquals(2, validGameModes.get(0).getOrder());
    }

    @Test
    void getSize() {
        // Test parametrized constructor
        GameMode gameMode = new GameModeImpl(2, 7, true);
        assertEquals(7, gameMode.getSize());

        // Test VALID_GAME_MODE enum
        List<ValidGameMode> validGameModes = new ArrayList<>(Arrays.asList(ValidGameMode.values()));
        assertEquals(7, validGameModes.get(0).getSize());
    }

    @Test
    void hasText() {
        // Test parametrized constructor
        GameMode gameMode = new GameModeImpl(2, 7, true);
        assertTrue(gameMode.hasText());

        // Test VALID_GAME_MODE enum
        List<ValidGameMode> validGameModes = new ArrayList<>(Arrays.asList(ValidGameMode.values()));
        assertFalse(validGameModes.get(0).hasText());
    }

    @Test
    void isEquivalent() {
        // Test parametrized constructor
        GameMode gameMode = new GameModeImpl(2, 7, true);

        // Test VALID_GAME_MODE enum
        List<ValidGameMode> validGameModes = new ArrayList<>(Arrays.asList(ValidGameMode.values()));

        // Test true
        assertTrue(gameMode.isEquivalent(validGameModes.get(1)));
        assertTrue(validGameModes.get(1).isEquivalent(gameMode));

        // Test false
        assertFalse(gameMode.isEquivalent(validGameModes.get(0)));
        assertFalse(validGameModes.get(0).isEquivalent(gameMode));
    }
}