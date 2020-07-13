package cmpt276.proj.finddamatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        TextView textView = findViewById(R.id.helpView);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        setupToolbar();
    }

    private void setupToolbar() {
        TextView title = findViewById(R.id.toolbarTitle);
        title.setText(R.string.help_activity_title);
        ImageButton button = findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, HelpActivity.class);
    }
}