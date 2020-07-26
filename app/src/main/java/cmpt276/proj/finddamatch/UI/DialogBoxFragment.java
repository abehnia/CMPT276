package cmpt276.proj.finddamatch.UI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.UI.scoresActivity.Score;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreState;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreManager;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreTable;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreTableView;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreViewGenerator;
import cmpt276.proj.finddamatch.UI.settingsActivity.Settings;
import cmpt276.proj.finddamatch.model.gameLogic.VALID_GAME_MODE;

/**
 * Class to show dialog box. Sets up table and takes input to save high score
 */

public class DialogBoxFragment extends AppCompatDialogFragment {
    private ScoreManager scoreManager;
    private ScoreTable scoreTable;
    private ScoreTableView scoreTableView;
    private Settings settings;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).
                inflate(R.layout.dialog_game_end, null);
        this.settings = Settings.get();
        setupScores();
        populateTable(v);
        showScore();
        setupTextView(v);
        DialogInterface.OnClickListener listener = setupOnClick(v);
        return setupDialog(v, listener);
    }

    private void setupScores() {
        scoreManager = ScoreState.get().getScoreManager();
        scoreTable = scoreManager.getScoreTable(settings.getGameMode());
    }

    private Dialog setupDialog(View v,
                               DialogInterface.OnClickListener listener) {
        return new AlertDialog.Builder(getActivity())
                .setTitle("Best Scores")
                .setView(v).setCancelable(false)
                .setNeutralButton("Save", listener)
                .setNegativeButton(android.R.string.cancel, listener)
                .create();
    }

    private void setupTextView(View v) {
        int time = scoreManager.getCurrentTime();
        String dTime = StringFormatting.getTimeString(time);
        TextView txtYourScore = v.findViewById(R.id.txtYourScore);
        txtYourScore.setText(dTime);
    }

    private DialogInterface.OnClickListener setupOnClick(View v) {
        return (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_NEGATIVE:
                    Objects.requireNonNull(getActivity()).finish();
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    EditText txt = v.findViewById(R.id.editTextNickName);
                    String nickName = txt.getText().toString();
                    if (!nickName.isEmpty()) {
                        Score currentScore = makeNewScore(nickName);
                        scoreManager.addScore(settings.getGameMode(),
                                currentScore);
                    } else {
                        txt.setError("Enter Nickname");
                        Toast.makeText(getContext(),
                                "No Nickname: Score not Saved",
                                Toast.LENGTH_SHORT).show();
                    }
                    Objects.requireNonNull(getActivity()).finish();

            }
        };
    }


    private void populateTable(View v) {
        TypedArray typedNameIds = getResources().
                obtainTypedArray(R.array.name2_ids);
        TypedArray typedDateIds = getResources().
                obtainTypedArray(R.array.date2_ids);
        TypedArray typedTimeIds = getResources().
                obtainTypedArray(R.array.time2_ids);
        ScoreTable scoreTable = scoreManager.getScoreTable(settings.
                getGameMode());
        this.scoreTableView = ScoreViewGenerator.generate(v,
                Arrays.asList(typedNameIds, typedDateIds, typedTimeIds),
                scoreTable);
    }

    private void showScore() {
        scoreTableView.setScores(scoreTable);
    }

    private Score makeNewScore(String nickName) {
        int currentTime = scoreManager.getCurrentTime();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new
                SimpleDateFormat("EEE, MMM d",
                Locale.getDefault());
        String date = dateFormat.format(calendar.getTime());
        Score score = new Score(nickName, date, currentTime);
        return score;
    }
}
