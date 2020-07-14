package cmpt276.proj.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.UI.settingsActivity.Settings;

/**
 * Class for Settings Activity from Main Menu
 * Sets up sets of logos
 */

public class SettingsActivity extends AppCompatActivity {
    private int imageSetKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setupImageSet();
        setupApplyButton();
        setupToolbar();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Settings.get().save(SettingsActivity.this);
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, SettingsActivity.class);
    }

    private void setupToolbar() {
        TextView title = findViewById(R.id.toolbarTitle);
        title.setText(R.string.settings_activity_title);
        ImageButton button = findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupImageSet() {
        RadioGroup radioGroup = findViewById(R.id.ImageSetChoice);
        this.imageSetKey = Settings.get().getImageSetKey();
        radioGroup.check(imageSetKey);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> imageSetKey = checkedId);
    }

    private void setupApplyButton() {
        Button applyButton = findViewById(R.id.activitySettingsApply);
        applyButton.setOnClickListener(v -> {
            Settings.get().setImageSet(imageSetKey);
            finish();
        });
    }
}
