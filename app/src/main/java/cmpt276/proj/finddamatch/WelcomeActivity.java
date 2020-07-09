package cmpt276.proj.finddamatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    private static int SCREEN_TIME = 20000;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        handler = new Handler();
        handler.postDelayed(() -> nextActivity(), SCREEN_TIME);
        setupSkipBtn();
    }

    private void nextActivity() {
        Intent intent = new Intent(WelcomeActivity.this, MainMenuActivity.class);
        startActivity(intent);
        finish();
    }

    private void setupSkipBtn() {
        Button skipBtn = findViewById(R.id.btnSkip);
        skipBtn.setOnClickListener(v -> skipSplash());
    }

    private void skipSplash() {
        if (handler != null){
            handler.removeCallbacksAndMessages(null);
        }
        nextActivity();
    }

}