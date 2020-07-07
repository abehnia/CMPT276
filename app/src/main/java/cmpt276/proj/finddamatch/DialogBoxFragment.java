package cmpt276.proj.finddamatch;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

//Class to show dialog box. Sets up table and takes input to save high score

public class DialogBoxFragment extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_game_end, null);
        populateTable(v);
        int time = ScoreManger.getScoreTime(6, Objects.requireNonNull(getContext()));
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
                        ScoreManger.saveHighScore(nickName, time, getContext());
                    }else{
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
        for (int i = 5; i>=1;i--){
            int time = ScoreManger.getScoreTime(i, Objects.requireNonNull(getContext()));
            String name = ScoreManger.getScoreName(i, getContext());
            String date = ScoreManger.getScoreDate(i, getContext());


            if (time >= 120){
                int min = (time/60);
                int sec = (time%60);
                time_str = getString(R.string.TimeMins, min, sec);

            }else if(time == 60){
                int min = (time/60);
                time_str = getString(R.string.TimeExact1Min, min);

            }else if((time % 60)==0){
                int min = (time/60);
                time_str = getString(R.string.TimeExactMins, min);

            }else if(time >= 60){
                int min = (time/60);
                int sec = (time%60);
                time_str = getString(R.string.Time1Min, min, sec);

            }else{
                time_str = getString(R.string.TimeSecs, time);
            }


            switch (i){
                case 1:
                    txtName = v.findViewById(R.id.Score1Name2);
                    txtDate = v.findViewById(R.id.Score1Date2);
                    txtTime = v.findViewById(R.id.Score1Time2);

                    txtName.setText(name);
                    txtDate.setText(date);
                    txtTime.setText(time_str);
                    break;
                case 2:
                    txtName = v.findViewById(R.id.Score2Name2);
                    txtDate = v.findViewById(R.id.Score2Date2);
                    txtTime = v.findViewById(R.id.Score2Time2);

                    txtName.setText(name);
                    txtDate.setText(date);
                    txtTime.setText(time_str);
                    break;
                case 3:
                    txtName = v.findViewById(R.id.Score3Name2);
                    txtDate = v.findViewById(R.id.Score3Date2);
                    txtTime = v.findViewById(R.id.Score3Time2);

                    txtName.setText(name);
                    txtDate.setText(date);
                    txtTime.setText(time_str);
                    break;
                case 4:
                    txtName = v.findViewById(R.id.Score4Name2);
                    txtDate = v.findViewById(R.id.Score4Date2);
                    txtTime = v.findViewById(R.id.Score4Time2);

                    txtName.setText(name);
                    txtDate.setText(date);
                    txtTime.setText(time_str);
                    break;
                case 5:
                    txtName = v.findViewById(R.id.Score5Name2);
                    txtDate = v.findViewById(R.id.Score5Date2);
                    txtTime = v.findViewById(R.id.Score5Time2);

                    txtName.setText(name);
                    txtDate.setText(date);
                    txtTime.setText(time_str);
                    break;
            }
        }
    }
}
