package cmpt276.proj.finddamatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        setupStartGameBtn();
        setupHelpBtn();
        setupSettingsBtn();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.default_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.backButton) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
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