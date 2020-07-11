package cmpt276.proj.finddamatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import cmpt276.proj.finddamatch.settingsActivity.Settings;

public class SettingsActivity extends AppCompatActivity {
    private int imageSetKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setupImageSet();
        setupApplyButton();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Settings.get().save(SettingsActivity.this);
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, SettingsActivity.class);
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
