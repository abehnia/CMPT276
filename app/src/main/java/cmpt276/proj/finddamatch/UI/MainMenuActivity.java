package cmpt276.proj.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.UI.flickrActivity.BitmapStorer;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreState;

import cmpt276.proj.finddamatch.UI.settingsActivity.Settings;
import cmpt276.proj.finddamatch.UI.settingsActivity.SettingsSaver;
import cmpt276.proj.finddamatch.model.GameMode;

import static cmpt276.proj.finddamatch.UI.VALID_IMAGE_SET.Custom;

/**
 * Class for the Main Menu. Sets up various buttons
 */

public class MainMenuActivity extends AppCompatActivity {

    public static final String LOADING_TEXT = "Loading bitmaps, please wait a moment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        setupStartGameBtn();
        setupHelpBtn();
        setupSettings();
        setupBestScoresBtn();
        setupFlickrBtn();
        setupFlickrStorage();
        ScoreState.get().load(MainMenuActivity.this);
    }

    private void setupBestScoresBtn() {
        Button bestScoresBtn = findViewById(R.id.btnBestScores);
        bestScoresBtn.setOnClickListener(v -> {
            Intent settings_intent = ScoresActivity.makeIntent(MainMenuActivity.this);
            startActivity(settings_intent);
        });
    }

    private void setupSettings() {
        SettingsSaver.load(MainMenuActivity.this);
        Button settingsBtn = findViewById(R.id.btnSettings);
        settingsBtn.setOnClickListener(v -> {
            Intent settings_intent = SettingsActivity.makeIntent(MainMenuActivity.this);
            startActivity(settings_intent);
        });
    }

    private void setupHelpBtn() {
        Button helpBtn = findViewById(R.id.btnHelp);
        helpBtn.setOnClickListener(v -> {
            Intent intent = HelpActivity.makeIntent(MainMenuActivity.this);
            startActivity(intent);
        });
    }

    private void setupStartGameBtn() {
        Button startBtn = findViewById(R.id.btnStartGame);
        startBtn.setOnClickListener(v -> {
            Intent intent = GameActivity.makeIntent(MainMenuActivity.this);
            if (!BitmapStorer.get().isReady()) {
                Toast.makeText(this,
                        LOADING_TEXT, Toast.LENGTH_SHORT).show();
                return;
            }
            int flickrImageSetSize = BitmapStorer.get().getBitmaps().size();
            GameMode gameMode = Settings.get().getGameMode();
            if (Settings.get().getImageSet().isEquivalent(Custom) &&
                    !Settings.checkFlickrImageSetSize(gameMode, flickrImageSetSize)) {
                Toast.makeText(this, R.string.not_enough_images,
                        Toast.LENGTH_SHORT).show();
                return;
            }
            startActivity(intent);
        });
    }

    private void setupFlickrBtn() {
        Button flickrBtn = findViewById(R.id.btnFlickr);
        flickrBtn.setOnClickListener(v -> {
            Intent intent = CustomImageSetActivity.makeIntent(MainMenuActivity.this);
            if (!BitmapStorer.get().isReady()) {
                Toast.makeText(this,
                        LOADING_TEXT, Toast.LENGTH_SHORT).show();
                return;
            }
            startActivity(intent);
        });
    }

    private void setupFlickrStorage() {
        BitmapStorer bitmapStorer = BitmapStorer.get();
        while (!bitmapStorer.isReady()) {
        }
        bitmapStorer.load(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ScoreState.get().save(MainMenuActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BitmapStorer bitmapStorer = BitmapStorer.get();
        bitmapStorer.clearQueue();
        bitmapStorer.quitSafely();
    }

}