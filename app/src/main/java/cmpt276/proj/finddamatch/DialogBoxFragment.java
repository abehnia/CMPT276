package cmpt276.proj.finddamatch;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Objects;

import cmpt276.proj.finddamatch.model.Game;
import cmpt276.proj.finddamatch.model.ScoreManger;
import cmpt276.proj.finddamatch.model.ScoresIterator;

//Class to show dialog box. Sets up table and takes input to save high score

public class DialogBoxFragment extends AppCompatDialogFragment {
    private ScoresIterator scores;
    private final int sixthScore = 5;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_game_end, null);
        populateTable(v);
        scores = ScoresIterator.getInstance();
        int time = scores.getScores().get(sixthScore).getTime();
        String dTime = ScoreManger.getTimeString(time,getContext());
        TextView txtYourScore = v.findViewById(R.id.txtYourScore);
        txtYourScore.setText(dTime);

        DialogInterface.OnClickListener listener = (dialog, which) -> {
            switch (which){
                case DialogInterface.BUTTON_NEGATIVE:
                    Objects.requireNonNull(getActivity()).finish();
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    EditText txt = v.findViewById(R.id.editTextNickName);
                    String nickName = txt.getText().toString();
                    if (!nickName.equals("")){
                        ScoreManger.saveHighScore(nickName, getContext());
                    }else{
                        int[] times= getResources().getIntArray(R.array.def_times);
                        scores.getScores().get(sixthScore).setTime(times[sixthScore]);
                        txt.setError("Enter Nickname");
                        Toast.makeText(getContext(), "No Nickname: Score not Saved", Toast.LENGTH_SHORT).show();
                    }
                    Objects.requireNonNull(getActivity()).finish();

            }
        };

        return new AlertDialog.Builder(getActivity())
                .setTitle("Best Scores")
                .setView(v)
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

        for (int i = 0; i < 5;i++){
            int time = scores.getScores().get(i).getTime();
            String name = scores.getScores().get(i).getName();
            String date = scores.getScores().get(i).getDate();
            time_str = ScoreManger.getTimeString(time,getContext());

            txtName = v.findViewById(typedName2Ids.getResourceId(i,0));
            txtDate = v.findViewById(typedDate2Ids.getResourceId(i,0));
            txtTime = v.findViewById(typedTime2Ids.getResourceId(i,0));

            txtName.setText(name);
            txtDate.setText(date);
            txtTime.setText(time_str);
        }

        typedName2Ids.recycle();
        typedDate2Ids.recycle();
        typedTime2Ids.recycle();
    }
}
