package cmpt276.proj.finddamatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        setupBackButton();
    }

    private void setupBackButton() {
        ImageButton btn = findViewById(R.id.imgBtnBackArrowHelp);
        btn.setOnClickListener(v -> finish());
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, HelpActivity.class);
    }
}