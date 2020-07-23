package cmpt276.proj.finddamatch.UI.settingsActivity;

import java.util.HashSet;
import java.util.Set;

import cmpt276.proj.finddamatch.model.GameMode;
import cmpt276.proj.finddamatch.model.gameLogic.GAME_MODES;

public class SettingsHelper {

    public static StringMapper<Integer> gameSizeMapper = (String string) ->
    {
        int value = Integer.parseInt(string);
        if (value != -1) {
            return value;
        }
        value = getMaxSize(Settings.get().getGameMode().getOrder());
        return value;
    };

    public static Set<Integer> getValidSizes(int order) {
        HashSet<Integer> result = new HashSet<>();
        for (GAME_MODES gameMode : GAME_MODES.values()) {
            if (gameMode.getOrder() == order) {
                result.add(gameMode.getSize());
            }
        }
        return result;
    }

    public static boolean isSettingsValid(int order, int size, int imageSet) {
        for (GameMode gameMode : GAME_MODES.values()) {
            if (gameMode.getOrder() == order &&
                    gameMode.getSize() == size) {
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
