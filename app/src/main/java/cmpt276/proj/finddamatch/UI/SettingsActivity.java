package cmpt276.proj.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.UI.flickrActivity.BitmapStorer;
import cmpt276.proj.finddamatch.UI.settingsActivity.OptionView;
import cmpt276.proj.finddamatch.UI.settingsActivity.OptionsViewImpl;
import cmpt276.proj.finddamatch.UI.settingsActivity.Settings;
import cmpt276.proj.finddamatch.UI.settingsActivity.SettingsSaver;
import cmpt276.proj.finddamatch.UI.settingsActivity.StringMapper;
import cmpt276.proj.finddamatch.model.GameMode;
import cmpt276.proj.finddamatch.model.gameLogic.GameDifficulty;
import cmpt276.proj.finddamatch.model.gameLogic.GameModeImpl;

import static cmpt276.proj.finddamatch.UI.settingsActivity.SettingsHelper.getMaxSize;

/**
 * Class for Settings Activity from Main Menu
 * Sets up sets of logos
 */

public class SettingsActivity extends AppCompatActivity {
    private Settings settings;
    private OptionView<Integer> imageSetOption;
    private OptionView<Integer> gameOrderOption;
    private OptionView<Integer> gameSizeOption;
    private OptionView<Boolean> hasTextOption;
    private OptionView<Integer> gameDifficultyOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(R.layout.activity_settings);
        setupApplyButton();
        setupToolbar();
        setupOptionsView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SettingsSaver.save(SettingsActivity.this);
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, SettingsActivity.class);
    }

    private void setupToolbar() {
        TextView title = findViewById(R.id.toolbarTitle);
        title.setText(R.string.settings_activity_title);
        ImageButton button = findViewById(R.id.backButton);
        button.setOnClickListener(v -> finish());
    }

    private void setupApplyButton() {
        int flickrImageSetSize = BitmapStorer.get().getBitmaps().size();
        Button applyButton = findViewById(R.id.activitySettingsApply);
        applyButton.setOnClickListener(v -> {
            GameMode gameMode = new GameModeImpl(gameOrderOption.getValue(),
                    gameSizeOption.getValue(),
                    hasTextOption.getValue());
            ImageSetOption imageSet =
                    ValidImageSet.values()[imageSetOption.getValue()];
            GameDifficulty difficulty = GameDifficulty.values()
                    [gameDifficultyOption.getValue()];
            settings.setGameMode(gameMode);
            settings.setImageSetOption(imageSet);
            settings.setDifficulty(difficulty);
            String checkOptions = settings.apply(flickrImageSetSize, getResources());
            if (checkOptions.equals(getString(R.string.true_value))) {
                settings.setButtonIDs(Arrays.asList(
                        imageSetOption.getCurrentButtonID(),
                        gameOrderOption.getCurrentButtonID(),
                        gameSizeOption.getCurrentButtonID(),
                        hasTextOption.getCurrentButtonID(),
                        gameDifficultyOption.getCurrentButtonID()
                ));
                finish();
                return;
            }
            Toast.makeText(this, checkOptions,
                    Toast.LENGTH_SHORT).show();
        });
    }


    private void setupOptionsView() {
        List<Integer> buttonIDs = settings.getButtonIDs();
        imageSetOption = setupOptionView(R.array.imageset_keys,
                R.array.imageset_values, R.id.ImageSetChoice, buttonIDs.get(0),
                Integer::parseInt);
        gameOrderOption = setupOptionView(R.array.gameorder_keys,
                R.array.gameorder_values, R.id.GameOrderChoice, buttonIDs.get(1),
                Integer::parseInt);
        setupGameSize(buttonIDs.get(2));
        hasTextOption = setupOptionView(R.array.text_keys,
                R.array.text_values, R.id.textChoice, buttonIDs.get(3),
                Boolean::parseBoolean);
        gameDifficultyOption = setupOptionView(R.array.difficulty_keys,
                R.array.difficulty_values, R.id.difficultyChoice, buttonIDs.get(4),
                Integer::parseInt);
    }

    private <T, U extends StringMapper<T>>
    OptionView<T> setupOptionView(int buttonIDs, int buttonValues,
                                  int radioGroupID, int checkedButton,
                                  U mapper) {
        TypedArray typedArray = getResources().obtainTypedArray(buttonIDs);
        String[] values = getResources().getStringArray(buttonValues);
        RadioGroup radioGroup = findViewById(radioGroupID);
        return new OptionsViewImpl<>(mapper, radioGroup,
                checkedButton, typedArray, values);
    }

    private void init() {
        this.settings = Settings.get();
    }

    private void setupGameSize(int buttonID) {
        StringMapper<Integer> gameSizeMapper = (String string) ->
        {
            int value = Integer.parseInt(string);
            if (value != -1) {
                return value;
            }
            value = getMaxSize(gameOrderOption.getValue());
            return value;
        };
        gameSizeOption = setupOptionView(R.array.gamesize_keys,
                R.array.gamesize_values, R.id.GameSizeChoice, buttonID,
                gameSizeMapper);
    }
}
