package cmpt276.proj.finddamatch.model.gameLogic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cmpt276.proj.finddamatch.model.Game;
import cmpt276.proj.finddamatch.model.GameMode;

import static org.junit.jupiter.api.Assertions.*;

class GameModeImplTest {

    @Test
    void getOrder() {
        // Test parametrized constructor
        GameMode gameMode = new GameModeImpl(2, 7, true);
        assertEquals(2, gameMode.getOrder());

        // Test VALID_GAME_MODE enum
        List<VALID_GAME_MODE> valid_game_modes = new ArrayList<>(Arrays.asList(VALID_GAME_MODE.values()));
        assertEquals(2, valid_game_modes.get(0).getOrder());
    }

    @Test
    void getSize() {
        // Test parametrized constructor
        GameMode gameMode = new GameModeImpl(2, 7, true);
        assertEquals(7, gameMode.getSize());

        // Test VALID_GAME_MODE enum
        List<VALID_GAME_MODE> valid_game_modes = new ArrayList<>(Arrays.asList(VALID_GAME_MODE.values()));
        assertEquals(7, valid_game_modes.get(0).getSize());
    }

    @Test
    void hasText() {
        // Test parametrized constructor
        GameMode gameMode = new GameModeImpl(2, 7, true);
        assertTrue(gameMode.hasText());

        // Test VALID_GAME_MODE enum
        List<VALID_GAME_MODE> valid_game_modes = new ArrayList<>(Arrays.asList(VALID_GAME_MODE.values()));
        assertFalse(valid_game_modes.get(0).hasText());
    }

    @Test
    void isEquivalent() {
        // Test parametrized constructor
        GameMode gameMode = new GameModeImpl(2, 7, true);

        // Test VALID_GAME_MODE enum
        List<VALID_GAME_MODE> valid_game_modes = new ArrayList<>(Arrays.asList(VALID_GAME_MODE.values()));

        // Test true
        assertTrue(gameMode.isEquivalent(valid_game_modes.get(1)));
        assertTrue(valid_game_modes.get(1).isEquivalent(gameMode));

        // Test false
        assertFalse(gameMode.isEquivalent(valid_game_modes.get(0)));
        assertFalse(valid_game_modes.get(0).isEquivalent(gameMode));
    }
}