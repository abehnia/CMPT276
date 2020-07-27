package cmpt276.proj.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreState;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreTable;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreManager;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreTableView;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreViewGenerator;
import cmpt276.proj.finddamatch.UI.settingsActivity.Settings;
import cmpt276.proj.finddamatch.model.Game;
import cmpt276.proj.finddamatch.model.GameMode;
import cmpt276.proj.finddamatch.model.gameLogic.VALID_GAME_MODE;

/**
 * Activity to show user the top 5 high scores and reset high scores
 */

public class ScoresActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ScoreManager scoreManager;
    ScoreTableView scoreTableView;
    ScoreTable scoreTable;
    Settings settings;
    List<GameMode> gameModeList;
    VALID_GAME_MODE gameMode;
    Map<Integer, GameMode> integerGameModeMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        integerGameModeMap = new HashMap<>();
        gameModeList = new ArrayList<>();
        gameModeList.addAll(Arrays.asList(VALID_GAME_MODE.values()));
        for (int i = 0; i < gameModeList.size(); ++i) {
            integerGameModeMap.put(i, gameModeList.get(i));
        }

        Spinner spinnerGameModes = findViewById(R.id.gameMode_spinner);
        ArrayAdapter<GameMode> spinnerAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_item, gameModeList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGameModes.setAdapter(spinnerAdapter);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
//                (this, R.array.gameModes_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerGameModes.setAdapter(adapter);

        spinnerGameModes.setOnItemSelectedListener(this);
        scoreManager = ScoreState.get().getScoreManager();
        this.settings = Settings.get();
        scoreTable = scoreManager.getScoreTable(gameMode);
        populateTable();
        showScore();
        setupResetBtn();
        setupToolbar();
    }

    private void setupResetBtn() {
        Button btn = findViewById(R.id.btnReset);
        btn.setOnClickListener(v -> {
            scoreManager.resetScoreTable(gameMode);
            scoreTable = scoreManager.getScoreTable(gameMode);
            showScore();
        });
    }

    private void populateTable() {
        TypedArray typedNameIds = getResources().
                obtainTypedArray(R.array.name_ids);
        TypedArray typedDateIds = getResources().
                obtainTypedArray(R.array.date_ids);
        TypedArray typedTimeIds = getResources().
                obtainTypedArray(R.array.time_ids);
        ScoreTable scoreTable = scoreManager.
                getScoreTable(gameMode);
        this.scoreTableView = ScoreViewGenerator.generate(
                findViewById(android.R.id.content).getRootView(),
                Arrays.asList(typedNameIds, typedDateIds, typedTimeIds),
                scoreTable);
        int color = ContextCompat.getColor(ScoresActivity.this,
                R.color.colorText);
        scoreTableView.setColor(color);
    }

    private void setupToolbar() {
        TextView title = findViewById(R.id.toolbarTitle);
        title.setText(R.string.scoreboard_menu_title);
        ImageButton button = findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showScore() {
        scoreTableView.setScores(scoreTable);
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, ScoresActivity.class);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        VALID_GAME_MODE gameMode = (VALID_GAME_MODE) parent.getItemAtPosition(position);
        int spinner_pos = parent.getSelectedItemPosition();
        Toast.makeText(parent.getContext(), "Game " +
                        Integer.toString(spinner_pos + 1) +
                        " is selected", Toast.LENGTH_SHORT).show();
        this.gameMode = gameMode;
//        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), text + " is selected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}