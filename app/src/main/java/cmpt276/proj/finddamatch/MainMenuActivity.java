package cmpt276.proj.finddamatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//Class for the Main Menu. Sets up various buttons

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        setupStartGameBtn();
        setupHelpBtn();
        setupSettingsBtn();
        setupBestScoresBtn();
    }

    private void setupBestScoresBtn() {
        Button bestScoresBtn = findViewById(R.id.btnBestScores);
        bestScoresBtn.setOnClickListener(v -> {
            Intent settings_intent = ScoresActivity.makeIntent(MainMenuActivity.this);
            startActivity(settings_intent);
        });
    }

    private void setupSettingsBtn() {
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
}