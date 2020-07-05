package cmpt276.proj.finddamatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import cmpt276.proj.finddamatch.model.ScoreManger;

public class ScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        populateTable();
        setupResetBtn();
    }

    private void setupResetBtn() {
        Button btn = findViewById(R.id.btnReset);
        btn.setOnClickListener(v -> {
            ScoreManger.resetScores(ScoresActivity.this);
            populateTable();
        });
    }

    private void populateTable() {
        TextView txtName;
        TextView txtDate;
        TextView txtTime;
        String time_str;
        for (int i = 5; i>=1;i--){
            int time = ScoreManger.getScoreTime(i, ScoresActivity.this);
            String name = ScoreManger.getScoreName(i, ScoresActivity.this);
            String date = ScoreManger.getScoreDate(i, ScoresActivity.this);
            if (time > 120){
                int min = (time/60);
                int sec = (time%60);
                time_str = getString(R.string.TimeMins, min, sec);

            }else if(time == 60){
                int min = (time/60);
                time_str = getString(R.string.TimeExact1Min, min);

            }else if((time % 60)==0){
                int min = (time/60);
                time_str = getString(R.string.TimeExactMins, min);

            }else if(time > 60){
                int min = (time/60);
                int sec = (time%60);
                time_str = getString(R.string.Time1Min, min, sec);

            }else{
                time_str = getString(R.string.TimeSecs, time);
            }


            switch (i){
                case 1:
                    txtName = findViewById(R.id.Score1Name);
                    txtDate = findViewById(R.id.Score1Date);
                    txtTime = findViewById(R.id.Score1Time);

                    txtName.setText(name);
                    txtDate.setText(date);
                    txtTime.setText(time_str);
                    break;
                case 2:
                    txtName = findViewById(R.id.Score2Name);
                    txtDate = findViewById(R.id.Score2Date);
                    txtTime = findViewById(R.id.Score2Time);

                    txtName.setText(name);
                    txtDate.setText(date);
                    txtTime.setText(time_str);
                    break;
                case 3:
                    txtName = findViewById(R.id.Score3Name);
                    txtDate = findViewById(R.id.Score3Date);
                    txtTime = findViewById(R.id.Score3Time);

                    txtName.setText(name);
                    txtDate.setText(date);
                    txtTime.setText(time_str);
                    break;
                case 4:
                    txtName = findViewById(R.id.Score4Name);
                    txtDate = findViewById(R.id.Score4Date);
                    txtTime = findViewById(R.id.Score4Time);

                    txtName.setText(name);
                    txtDate.setText(date);
                    txtTime.setText(time_str);
                    break;
                case 5:
                    txtName = findViewById(R.id.Score5Name);
                    txtDate = findViewById(R.id.Score5Date);
                    txtTime = findViewById(R.id.Score5Time);

                    txtName.setText(name);
                    txtDate.setText(date);
                    txtTime.setText(time_str);
                    break;
            }
        }
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, ScoresActivity.class);
    }
}