package cmpt276.proj.finddamatch;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import cmpt276.proj.finddamatch.gameActivity.GameCanvas;
import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.Game;
import cmpt276.proj.finddamatch.model.GameMockImpl;
import cmpt276.proj.finddamatch.model.Image;

public class GameActivity extends AppCompatActivity {
    private GameCanvas gameCanvas;
    private Game game;
    private Card lead, guess;
    private Handler handler;
    private TextView timer;
    private boolean isTouchable;
    private static final int DELAY = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.isTouchable = true;
        setupGame();
        setupCanvas();
        setupTouch();
        setupTimer();
        setupHandler();
        setupButton();
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, GameActivity.class);
    }

    private void setupGame() {
        game = new GameMockImpl();
        game.reset();
        lead = game.poll();
        guess = game.poll();
    }

    private void setupCanvas() {
        gameCanvas = findViewById(R.id.game_activity_game_canvas);
        gameCanvas.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right,
                                       int bottom, int oldLeft, int oldTop,
                                       int oldRight, int oldBottom) {
                gameCanvas.setCards(guess, lead, 0);
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupTouch() {
        gameCanvas.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!isTouchable) {
                    return true;
                }
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        actionDown(event);
                        return false;
                    case MotionEvent.ACTION_UP:
                        actionUp();
                        return false;
                    default:
                        return false;
                }
            }
        });
    }

    private void setupHandler() {
        this.handler = new Handler();
        this.handler.postDelayed(this::updateTime, DELAY);
    }

    private void setupTimer() {
        this.timer = findViewById(R.id.game_activity_time_value);
        this.timer.setText(formatTime(game.queryTime()));
    }

    private void setupButton() {
        Button resetButton = findViewById(R.id.game_activity_reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.reset();
                lead = game.poll();
                guess = game.poll();
                gameCanvas.setCards(guess, lead, 0);
                setupHandler();
                isTouchable = true;
            }
        });
    }

    private void updateTime() {
        this.timer.setText(formatTime(game.queryTime()));
        handler.postDelayed(this::updateTime, DELAY);
    }

    private void removeHandler() {
        this.handler.removeCallbacksAndMessages(null);
    }

    private void actionDown(MotionEvent event) {
        if (!gameCanvas.contains(event.getX(), event.getY())) {
            return;
        }
        Image intersectedImage = gameCanvas.getIntersection(
                event.getX(), event.getY());
        if (!game.check(intersectedImage)) {
            return;
        }
        game.update(intersectedImage);
        if (game.isGameDone()) {
            removeHandler();
            return;
        }
        lead = game.poll();
        guess = game.poll();
        gameCanvas.setCards(guess, lead, 0);
    }

    private void actionUp() {
        this.isTouchable = !game.isGameDone();
    }

    private String formatTime(long time) {
        long timeInSeconds = time / 1000;
        return String.format(Locale.getDefault(), " %02d:%02d",
                timeInSeconds / 60, timeInSeconds % 60);
    }
}