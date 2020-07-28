package cmpt276.proj.finddamatch.UI.settingsActivity;

import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.UI.ImageSetOption;
import cmpt276.proj.finddamatch.UI.VALID_IMAGE_SET;
import cmpt276.proj.finddamatch.UI.flickrActivity.BitmapStorer;
import cmpt276.proj.finddamatch.model.GameMode;
import cmpt276.proj.finddamatch.model.gameLogic.VALID_GAME_MODE;

import static cmpt276.proj.finddamatch.UI.VALID_IMAGE_SET.FLICKR;
import static cmpt276.proj.finddamatch.UI.VALID_IMAGE_SET.WESTERN;
import static cmpt276.proj.finddamatch.model.gameLogic.VALID_GAME_MODE.GAME1;

/**
 * Contains the settings of the app
 */
public class Settings implements Serializable {
    private static Settings appSettings;
    private VALID_GAME_MODE gameMode;
    private ImageSetOption imageSetOption;
    private transient GameMode candidateGameMode;
    private transient ImageSetOption candidateImageSetOption;
    private List<Integer> buttonIDs;

    private Settings() {
        this.gameMode = GAME1;
        this.imageSetOption = WESTERN;
        this.candidateGameMode = gameMode;
        this.candidateImageSetOption = imageSetOption;
        this.buttonIDs = new ArrayList<>();
        buttonIDs.add(R.id.imageSetWesternChoice);
        buttonIDs.add(R.id.gameOrderChoice0);
        buttonIDs.add(R.id.gameSizeChoice0);
        buttonIDs.add(R.id.textChoice1);
    }

    public VALID_GAME_MODE getGameMode() {
        return gameMode;
    }

    public ImageSetOption getImageSet() {
        return imageSetOption;
    }

    public void setGameMode(GameMode gameMode) {
        this.candidateGameMode = gameMode;
    }

    public void setImageSetOption(ImageSetOption imageSetOption) {
        this.candidateImageSetOption = imageSetOption;
    }

    public boolean apply(int flickrImageSetSize) {
        if (candidateImageSetOption.isEquivalent(FLICKR) &&
                candidateGameMode.hasText()) {
            return false;
        }
        if (candidateImageSetOption.isEquivalent(FLICKR) &&
                !checkFlickrImageSetSize(candidateGameMode, flickrImageSetSize)) {
            return false;
        }
        if (checkGameMode() && checkImageSetOption()) {
            update();
            return true;
        }
        return false;
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
        return flickrImageSetSize >= reqNumOfImages;
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
        this.gameMode = (VALID_GAME_MODE) candidateGameMode;
        this.imageSetOption = candidateImageSetOption;
    }

    private boolean checkGameMode() {
        for (VALID_GAME_MODE gameMode : VALID_GAME_MODE.values()) {
            if (candidateGameMode.isEquivalent(gameMode)) {
                this.candidateGameMode = gameMode;
                return true;
            }
        }
        return false;
    }

    private boolean checkImageSetOption() {
        for (ImageSetOption imageSetOption : VALID_IMAGE_SET.values()) {
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
