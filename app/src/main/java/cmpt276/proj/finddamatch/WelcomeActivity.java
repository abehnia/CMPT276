package cmpt276.proj.finddamatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

import cmpt276.proj.finddamatch.welcomeActivity.AnimationCanvas;

public class WelcomeActivity extends AppCompatActivity {
    private static int SCREEN_TIME = 20000;
    private Handler handler;
    private long referenceTime;
    private long elapsedTime;
    private AnimationCanvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        handler = new Handler();
        this.elapsedTime = 0;
        this.canvas = findViewById(R.id.textView4);
        setupSkipBtn();
    }

    @Override
    protected void onPause() {
        super.onPause();
        long previousTime = this.referenceTime;
        this.referenceTime = SystemClock.elapsedRealtime();
        this.elapsedTime += referenceTime - previousTime;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        canvas.resume();
        this.referenceTime = SystemClock.elapsedRealtime();
        if (handler != null) {
            handler.postDelayed(this::nextActivity, SCREEN_TIME -
                    elapsedTime);
        }
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