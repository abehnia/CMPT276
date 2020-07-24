package cmpt276.proj.finddamatch.UI.settingsActivity;

import cmpt276.proj.finddamatch.model.GameMode;
import cmpt276.proj.finddamatch.model.gameLogic.VALID_GAME_MODE;

/**
 * Helper functions for settings
 */
public class SettingsHelper {

    public static int getMaxSize(int order) {
        int max_size = 0;
        for (GameMode gameMode : VALID_GAME_MODE.values()) {
            if (gameMode.getOrder() == order) {
                max_size = Math.max(max_size, gameMode.getSize());
            }
        }
        return max_size;
    }

}
