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

import java.io.IOException;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.UI.scoresActivity.Score;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreState;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreTable;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoresManager;
import cmpt276.proj.finddamatch.model.gameLogic.VALID_GAME_MODE;

/**
 * Activity to show user the top 5 high scores and reset high scores
 */

public class ScoresActivity extends AppCompatActivity {
    private ScoresManager scoresManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        scoresManager = ScoreState.get().getScoreManager();
        populateTable();
        setupResetBtn();
        setupToolbar();
    }

    private void setupResetBtn() {
        Button btn = findViewById(R.id.btnReset);
        btn.setOnClickListener(v -> {
            scoresManager.resetScoreTable(VALID_GAME_MODE.GAME1);
            populateTable();
        });
    }

    private void populateTable() {
        TextView txtName;
        TextView txtDate;
        TextView txtTime;
        String time_str;

        TypedArray typedNameIds = getResources().obtainTypedArray(R.array.name_ids);
        TypedArray typedDateIds = getResources().obtainTypedArray(R.array.date_ids);
        TypedArray typedTimeIds = getResources().obtainTypedArray(R.array.time_ids);

        ScoreTable scoreTable = scoresManager.
                getScoreTable(VALID_GAME_MODE.GAME1);
        int index = 0;
        for (Score score : scoreTable) {
            int time = score.getTime();
            String name = score.getName();
            String date = score.getDate();
            time_str = StringFormatting.getTimeString(time, ScoresActivity.this);

            txtName = findViewById(typedNameIds.getResourceId(index, 0));
            txtDate = findViewById(typedDateIds.getResourceId(index, 0));
            txtTime = findViewById(typedTimeIds.getResourceId(index, 0));

            txtName.setText(name);
            txtDate.setText(date);
            txtTime.setText(time_str);

            txtName.setTextColor(ContextCompat.getColor(ScoresActivity.this, R.color.colorText));
            txtDate.setTextColor(ContextCompat.getColor(ScoresActivity.this, R.color.colorText));
            txtTime.setTextColor(ContextCompat.getColor(ScoresActivity.this, R.color.colorText));

            index += 1;
        }

        typedNameIds.recycle();
        typedDateIds.recycle();
        typedTimeIds.recycle();
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

    public static Intent makeIntent(Context context) {
        return new Intent(context, ScoresActivity.class);
    }
}