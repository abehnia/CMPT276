package cmpt276.proj.finddamatch.UI.settingsActivity;

import android.content.res.Resources;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.UI.ImageSetOption;
import cmpt276.proj.finddamatch.UI.ValidImageSet;
import cmpt276.proj.finddamatch.model.GameMode;
import cmpt276.proj.finddamatch.model.gameLogic.GameDifficulty;
import cmpt276.proj.finddamatch.model.gameLogic.ValidGameMode;

import static cmpt276.proj.finddamatch.UI.ValidImageSet.Custom;
import static cmpt276.proj.finddamatch.UI.ValidImageSet.NBA;
import static cmpt276.proj.finddamatch.model.gameLogic.ValidGameMode.GAME1;

/**
 * Contains the settings of the app
 */
public class Settings implements Serializable {
    private static Settings appSettings;
    private ValidGameMode gameMode;
    private ImageSetOption imageSetOption;
    private GameDifficulty gameDifficulty;
    private transient GameMode candidateGameMode;
    private transient ImageSetOption candidateImageSetOption;
    private transient GameDifficulty candidateGameDifficulty;
    private List<Integer> buttonIDs;

    private Settings() {
        this.gameMode = GAME1;
        this.imageSetOption = NBA;
        this.candidateGameMode = gameMode;
        this.candidateImageSetOption = imageSetOption;
        this.candidateGameDifficulty = GameDifficulty.EASY;
        this.buttonIDs = new ArrayList<>();
        buttonIDs.add(R.id.imageSetWesternChoice);
        buttonIDs.add(R.id.gameOrderChoice0);
        buttonIDs.add(R.id.gameSizeChoice0);
        buttonIDs.add(R.id.textChoice1);
        buttonIDs.add(R.id.difficultyChoice0);
    }

    public ValidGameMode getGameMode() {
        return gameMode;
    }

    public ImageSetOption getImageSet() {
        return imageSetOption;
    }

    public GameDifficulty getDifficulty() {
        return gameDifficulty;
    }

    public void setGameMode(GameMode gameMode) {
        this.candidateGameMode = gameMode;
    }

    public void setImageSetOption(ImageSetOption imageSetOption) {
        this.candidateImageSetOption = imageSetOption;
    }

    public void setDifficulty(GameDifficulty difficulty) {
        this.candidateGameDifficulty = difficulty;
    }

    public String apply(int flickrImageSetSize, Resources resources) {
        if (candidateImageSetOption.isEquivalent(Custom) &&
                candidateGameMode.hasText()) {
            return resources.getString(R.string.flickr_ImageSet_can_not_have_Text);
        }
        if (candidateImageSetOption.isEquivalent(Custom) &&
                !checkFlickrImageSetSize(candidateGameMode, flickrImageSetSize)) {
            return resources.getString(R.string.not_enough_images);
        }
        if (checkGameMode() && checkImageSetOption()) {
            update();
            return resources.getString(R.string.true_value);
        }
        return resources.getString(R.string.invalid_game_mode_selected);
    }

    public static boolean checkFlickrImageSetSize(GameMode gameMode, int flickrImageSetSize) {
        final int ORDER2_NUMOFIMAGES = 7;
        final int ORDER3_NUMOFIMAGES = 13;
        final int ORDER5_NUMOFIMAGES = 31;
        int reqNumOfImages;
        switch (gameMode.getOrder()) {
            case 2:
                reqNumOfImages = ORDER2_NUMOFIMAGES;
                break;
            case 3:
                reqNumOfImages = ORDER3_NUMOFIMAGES;
                break;
            case 5:
                reqNumOfImages = ORDER5_NUMOFIMAGES;
                break;
            default:
                Log.e("Setting Activity", "Invalid Game Order");
                return false;
        }
        if (flickrImageSetSize >= reqNumOfImages) {
            return true;
        }
        return false;
    }

    /**
     * ImageSet, Order, Size
     */
    public List<Integer> getButtonIDs() {
        return buttonIDs;
    }

    public void setButtonIDs(List<Integer> buttonIDs) {
        this.buttonIDs = buttonIDs;
    }

    private void update() {
        this.gameMode = (ValidGameMode) candidateGameMode;
        this.imageSetOption = candidateImageSetOption;
        this.gameDifficulty = candidateGameDifficulty;
    }

    private boolean checkGameMode() {
        for (ValidGameMode gameMode : ValidGameMode.values()) {
            if (candidateGameMode.isEquivalent(gameMode)) {
                this.candidateGameMode = gameMode;
                return true;
            }
        }
        return false;
    }

    private boolean checkImageSetOption() {
        for (ImageSetOption imageSetOption : ValidImageSet.values()) {
            if (candidateImageSetOption.isEquivalent(imageSetOption)) {
                this.candidateImageSetOption = imageSetOption;
                return true;
            }
        }
        return false;
    }

    public static Settings get() {
        if (appSettings == null) {
            appSettings = new Settings();
            return appSettings;
        }
        return appSettings;
    }

    public static void set(Settings settings) {
        Settings.appSettings = settings;
    }

}
