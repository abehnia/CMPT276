package cmpt276.proj.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.Button;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.UI.welcomeActivity.AnimationCanvas;

/**
 * Class for Splash Screen Activity
 * Sets up welcome animation and skip button
 */

public class WelcomeActivity extends AppCompatActivity {
    private static int SCREEN_TIME = 7500;
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
        handler.removeCallbacksAndMessages(null);
        canvas.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        canvas.resume();
        this.referenceTime = SystemClock.elapsedRealtime();
        handler.postDelayed(this::nextActivity, SCREEN_TIME -
                elapsedTime);
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
        handler.removeCallbacksAndMessages(null);
        nextActivity();
    }

}