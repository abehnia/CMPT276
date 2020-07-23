package cmpt276.proj.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreState;
import cmpt276.proj.finddamatch.UI.settingsActivity.Settings;


/**
 * Class for the Main Menu. Sets up various buttons
 */

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        setupStartGameBtn();
        setupHelpBtn();
        setupSettings();
        setupBestScoresBtn();
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
        Settings.get().init(getResources());
        Settings.get().load(MainMenuActivity.this);
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
            startActivity(intent);
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        ScoreState.get().save(MainMenuActivity.this);
    }

}