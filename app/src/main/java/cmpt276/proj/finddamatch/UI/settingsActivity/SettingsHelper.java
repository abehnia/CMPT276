package cmpt276.proj.finddamatch.UI.settingsActivity;

import cmpt276.proj.finddamatch.model.GameMode;
import cmpt276.proj.finddamatch.model.gameLogic.GAME_MODES;

public class SettingsHelper {

    public static boolean isSettingsValid(GameMode tentativeGameMode) {
        for (GameMode gameMode : GAME_MODES.values()) {
            if (gameMode.isEquivalent(tentativeGameMode)) {
                return true;
            }
        }
        return false;
    }

    public static int getMaxSize(int order) {
        int max_size = 0;
        for (GAME_MODES gameMode : GAME_MODES.values()) {
            if (gameMode.getOrder() == order) {
                max_size = Math.max(max_size, gameMode.getSize());
            }
        }
        return max_size;
    }

}
