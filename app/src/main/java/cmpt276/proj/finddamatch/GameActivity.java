package cmpt276.proj.finddamatch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        showDialogBox();
    }

    private void showDialogBox() {
        Button btn = findViewById(R.id.btnSave);
        btn.setOnClickListener(v -> {
            Context context;
            AlertDialog.Builder dBuilder = new AlertDialog.Builder(GameActivity.this);
            View dView = getLayoutInflater().inflate(R.layout.dialog_game_end, null);
            EditText nickName = dView.findViewById(R.id.editTextNickName);

        });
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, GameActivity.class);
    }
}