package cmpt276.proj.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Arrays;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreState;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreTable;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreManager;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreTableView;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreViewGenerator;
import cmpt276.proj.finddamatch.model.gameLogic.VALID_GAME_MODE;

/**
 * Activity to show user the top 5 high scores and reset high scores
 */

public class ScoresActivity extends AppCompatActivity {
    private ScoreManager scoreManager;
    ScoreTableView scoreTableView;
    ScoreTable scoreTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        scoreManager = ScoreState.get().getScoreManager();
        scoreTable = scoreManager.getScoreTable(VALID_GAME_MODE.GAME1);
        populateTable();
        showScore();
        setupResetBtn();
        setupToolbar();
    }

    private void setupResetBtn() {
        Button btn = findViewById(R.id.btnReset);
        btn.setOnClickListener(v -> {
            scoreManager.resetScoreTable(VALID_GAME_MODE.GAME1);
            scoreTable = scoreManager.getScoreTable(VALID_GAME_MODE.GAME1);
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
                getScoreTable(VALID_GAME_MODE.GAME1);
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
}