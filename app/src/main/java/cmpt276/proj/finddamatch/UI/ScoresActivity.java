package cmpt276.proj.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreState;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreTable;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreManager;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreTableView;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreViewGenerator;
import cmpt276.proj.finddamatch.UI.settingsActivity.Settings;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        setUpSpinner();

        scoreManager = ScoreState.get().getScoreManager();
        this.settings = Settings.get();
        gameMode = settings.getGameMode();
        populateTable(gameMode);
        showScore(gameMode);
        setupResetBtn(gameMode);
        setupToolbar();
        updateGameModeTxt(gameMode);
    }

    private void updateGameModeTxt(VALID_GAME_MODE gameMode) {
        int numOfImgs = gameMode.getOrder()+1;
        int numOfCards = gameMode.getSize();
        boolean hasText = gameMode.hasText();
        String txt;
        if(hasText){
            txt = "true";
        }else{
            txt = "false";
        }
        TextView gameModeTxt = findViewById(R.id.txt_gameModeStr);
        gameModeTxt.setText(getString(R.string.selected_game_mode, numOfImgs, numOfCards, txt));
    }

    private void setUpSpinner() {
        gameModeList = new ArrayList<>();
        gameModeList.addAll(Arrays.asList(VALID_GAME_MODE.values()));
        Spinner spinnerGameModes = findViewById(R.id.gameMode_spinner);
        ArrayAdapter<GameMode> spinnerAdapter = new ArrayAdapter<GameMode>
                (this, android.R.layout.simple_spinner_item, gameModeList){
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                int color = ContextCompat.getColor(ScoresActivity.this,
                        R.color.colorAccent);
//                ((TextView) v).setTextSize(18);
                ((TextView) v).setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ((TextView) v).setTextColor(color);
                return v;
            }

        };
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGameModes.setAdapter(spinnerAdapter);
        spinnerGameModes.setPrompt("Select Game Mode:");
        spinnerGameModes.setOnItemSelectedListener(this);
    }

    private void setupResetBtn(VALID_GAME_MODE gameMode) {
        Button btn = findViewById(R.id.btnReset);
        btn.setOnClickListener(v -> {
            scoreManager.resetScoreTable(gameMode);
            scoreTable = scoreManager.getScoreTable(gameMode);
            showScore(gameMode);
        });
    }

    private void populateTable(VALID_GAME_MODE gameMode) {
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

    private void showScore(VALID_GAME_MODE gameMode) {
        scoreTable = scoreManager.getScoreTable(gameMode);
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
                (spinner_pos + 1) +
                " is selected", Toast.LENGTH_SHORT).show();
        this.gameMode = gameMode;
        populateTable(gameMode);
        showScore(gameMode);
        setupResetBtn(gameMode);
        updateGameModeTxt(gameMode);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}