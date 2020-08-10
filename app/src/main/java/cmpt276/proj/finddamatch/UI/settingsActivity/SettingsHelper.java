package cmpt276.proj.finddamatch.UI.settingsActivity;

import cmpt276.proj.finddamatch.model.GameMode;
import cmpt276.proj.finddamatch.model.gameLogic.ValidGameMode;

/**
 * Helper functions for settings
 */
public class SettingsHelper {

    public static int getMaxSize(int order) {
        int max_size = 0;
        for (GameMode gameMode : ValidGameMode.values()) {
            if (gameMode.getOrder() == order) {
                max_size = Math.max(max_size, gameMode.getSize());
            }
        }
        return max_size;
    }

}
