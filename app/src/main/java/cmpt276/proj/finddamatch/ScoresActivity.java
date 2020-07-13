package cmpt276.proj.finddamatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import cmpt276.proj.finddamatch.scoresActivity.ScoreManager;
import cmpt276.proj.finddamatch.scoresActivity.ScoresIterator;

/**Activity to show user the top 5 high scores and reset high scores*/

public class ScoresActivity extends AppCompatActivity {
    private ScoresIterator scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        scores = ScoresIterator.getInstance();
        populateTable();
        setupResetBtn();
        setupToolbar();
    }

    private void setupResetBtn() {
        Button btn = findViewById(R.id.btnReset);
        btn.setOnClickListener(v -> {
            ScoreManager.resetScores(ScoresActivity.this);
            populateTable();
        });
    }

    private void setupToolbar() {
        Toolbar myToolbar = findViewById(R.id.bestScoresToolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(R.string.best_scores_activity_title);
    }

    private void populateTable() {
        TextView txtName;
        TextView txtDate;
        TextView txtTime;
        String time_str;

        TypedArray typedNameIds = getResources().obtainTypedArray(R.array.name_ids);
        TypedArray typedDateIds = getResources().obtainTypedArray(R.array.date_ids);
        TypedArray typedTimeIds = getResources().obtainTypedArray(R.array.time_ids);

        for (int i = 0; i < 5;i++){
            int time = scores.getScores().get(i).getTime();
            String name = scores.getScores().get(i).getName();
            String date = scores.getScores().get(i).getDate();
            time_str = ScoreManager.getTimeString(time,ScoresActivity.this);

            txtName = findViewById(typedNameIds.getResourceId(i,0));
            txtDate = findViewById(typedDateIds.getResourceId(i,0));
            txtTime = findViewById(typedTimeIds.getResourceId(i,0));

            txtName.setText(name);
            txtDate.setText(date);
            txtTime.setText(time_str);

            txtName.setTextColor(ContextCompat.getColor(ScoresActivity.this, R.color.colorTableText));
            txtDate.setTextColor(ContextCompat.getColor(ScoresActivity.this, R.color.colorTableText));
            txtTime.setTextColor(ContextCompat.getColor(ScoresActivity.this, R.color.colorTableText));

        }

        typedNameIds.recycle();
        typedDateIds.recycle();
        typedTimeIds.recycle();
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, ScoresActivity.class);
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

    @Override
    protected void onPause() {
        super.onPause();
        ScoreManager.saveAllScores(ScoresActivity.this);
    }
}