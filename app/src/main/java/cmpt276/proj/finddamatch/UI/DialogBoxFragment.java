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

import java.util.Iterator;
import java.util.Objects;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.UI.scoresActivity.Score;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreState;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreTable;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoresManager;
import cmpt276.proj.finddamatch.model.gameLogic.VALID_GAME_MODE;

/**
 * Class to show dialog box. Sets up table and takes input to save high score
 */

public class DialogBoxFragment extends AppCompatDialogFragment {
    private ScoresManager scoresManager;
    private final int sixthScore = 5;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_game_end, null);
        scoresManager = ScoreState.get().getScoreManager();
        populateTable(v);
        int time = scoresManager.getCurrentScore().getTime();
        String dTime = StringFormatting.getTimeString(time, getContext());
        TextView txtYourScore = v.findViewById(R.id.txtYourScore);
        txtYourScore.setText(dTime);


        DialogInterface.OnClickListener listener = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_NEGATIVE:
                    Objects.requireNonNull(getActivity()).finish();
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    EditText txt = v.findViewById(R.id.editTextNickName);
                    String nickName = txt.getText().toString();
                    if (!nickName.equals("")) {
                        Score currentScore = scoresManager.getCurrentScore();
                        currentScore.setName(nickName);
                        scoresManager.addScore(VALID_GAME_MODE.GAME1,
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


        return new AlertDialog.Builder(getActivity())
                .setTitle("Best Scores")
                .setView(v).setCancelable(false)
                .setNeutralButton("Save", listener)
                .setNegativeButton(android.R.string.cancel, listener)
                .create();
    }

    private void populateTable(View v) {
        TextView txtName;
        TextView txtDate;
        TextView txtTime;
        String time_str;

        TypedArray typedName2Ids = getResources().obtainTypedArray(R.array.name2_ids);
        TypedArray typedDate2Ids = getResources().obtainTypedArray(R.array.date2_ids);
        TypedArray typedTime2Ids = getResources().obtainTypedArray(R.array.time2_ids);

        ScoreTable scoreTable = scoresManager.getScoreTable(VALID_GAME_MODE
                .GAME1);
        int index = 0;
        for (Score score : scoreTable) {
            int time = score.getTime();
            String name = score.getName();
            String date = score.getDate();
            time_str = StringFormatting.getTimeString(time, getContext());

            txtName = v.findViewById(typedName2Ids.getResourceId(index, 0));
            txtDate = v.findViewById(typedDate2Ids.getResourceId(index, 0));
            txtTime = v.findViewById(typedTime2Ids.getResourceId(index, 0));

            txtName.setText(name);
            txtDate.setText(date);
            txtTime.setText(time_str);
            ++index;
        }

        typedName2Ids.recycle();
        typedDate2Ids.recycle();
        typedTime2Ids.recycle();
    }
}
