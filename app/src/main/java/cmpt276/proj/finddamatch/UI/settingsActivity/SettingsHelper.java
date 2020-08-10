package cmpt276.proj.finddamatch.UI.settingsActivity;

import cmpt276.proj.finddamatch.model.GameMode;
import cmpt276.proj.finddamatch.model.gameLogic.ValidGameMode;

/**
 * Helper functions for settings
 */
public class SettingsHelper {

    public static int getMaxSize(int order) {
        int maxSize = 0;
        for (GameMode gameMode : ValidGameMode.values()) {
            if (gameMode.getOrder() == order) {
                maxSize = Math.max(maxSize, gameMode.getSize());
            }
        }
        return maxSize;
    }

}
