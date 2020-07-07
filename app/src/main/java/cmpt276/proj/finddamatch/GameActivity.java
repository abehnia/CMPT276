package cmpt276.proj.finddamatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cmpt276.proj.finddamatch.model.ScoreManger;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        setupDialogBoxBtn();
    }

    private void displayDialogBox(long longTime) {

        int time = (int)(longTime/1000);
        ScoreManger.setScoreTime(6,time,GameActivity.this);
        FragmentManager manager = getSupportFragmentManager();
        DialogBoxFragment dialog = new DialogBoxFragment();
        dialog.show(manager, "Best Scores Dialog");
    }

    private void setupDialogBoxBtn() {
        Button btn = findViewById(R.id.btnTestDialogBox);
        btn.setOnClickListener(v -> displayDialogBox(9000));


    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, GameActivity.class);
    }
}