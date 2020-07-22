package cmpt276.proj.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.UI.gameActivity.GameCanvas;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreState;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoresManager;
import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.CardGenerator;
import cmpt276.proj.finddamatch.model.DeckGenerator;
import cmpt276.proj.finddamatch.model.Game;
import cmpt276.proj.finddamatch.model.Image;
import cmpt276.proj.finddamatch.model.gameLogic.CardGeneratorImpl;
import cmpt276.proj.finddamatch.model.gameLogic.DeckGeneratorImpl;
import cmpt276.proj.finddamatch.model.gameLogic.GameImpl;
import cmpt276.proj.finddamatch.UI.settingsActivity.Settings;

/**
 * Class for Game Activity
 * Sets up game play, handler. timer and shuffle button
 */

public class GameActivity extends AppCompatActivity {
    private GameCanvas gameCanvas;
    private Game game;
    private Card discard, draw;
    private Handler handler;
    private Handler revealHandler;
    private TextView timer;
    private boolean isTouchable, isInDelay;
    private static final int DELAY = 100;
    private static final int REVEAL_DELAY = 1500;
    ScoresManager scoresManager;
    static private final int SIXTH_SCORE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        scoresManager = ScoreState.get().getScoreManager();
        this.isTouchable = true;
        setupGame();
        setupCanvas();
        setupTouch();
        setupTimer();
        setupHandler();
        setupButton();
        setupBackButton();
    }

    @Override
    protected void onPause() {
        super.onPause();
        removeHandler();
        game.pause(SystemClock.elapsedRealtime());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isInDelay) {
            game.resume(SystemClock.elapsedRealtime());
        }
        updateTime();
    }

    private void displayDialogBox(long longTime) {
        int time = (int) (longTime / 1000);
        scoresManager.getCurrentScore().setTime(time);
        FragmentManager manager = getSupportFragmentManager();
        DialogBoxFragment dialog = new DialogBoxFragment();
        dialog.setCancelable(false);
        dialog.show(manager, "Best Scores Dialog");
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, GameActivity.class);
    }

    private void setupGame() {
        CardGenerator cardGenerator = new CardGeneratorImpl();
        DeckGenerator deckGenerator = new DeckGeneratorImpl(cardGenerator);
        game = new GameImpl(deckGenerator, SystemClock.elapsedRealtime());
        long time = SystemClock.elapsedRealtime();
        game.reset(time);
        game.pause(time);
        discard = game.peekDiscard();
        draw = game.peekDraw();
        this.isInDelay = true;
    }

    private void setupCanvas() {
        gameCanvas = findViewById(R.id.game_activity_game_canvas);
        gameCanvas.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right,
                                       int bottom, int oldLeft, int oldTop,
                                       int oldRight, int oldBottom) {
                gameCanvas.setCards(discard, draw,
                        Settings.get().getImageSetValue());
                gameCanvas.hide();;
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
        this.revealHandler = new Handler();
    }

    private void setupTimer() {
        this.timer = findViewById(R.id.game_activity_time_value);
        this.timer.setText(formatTime(game.queryTime(SystemClock.elapsedRealtime())));
    }

    private void setupButton() {
        Button resetButton = findViewById(R.id.game_activity_reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeHandler();
                long time = SystemClock.elapsedRealtime();
                game.reset(time);
                game.pause(time);
                isInDelay = true;
                updateTime();
                discard = game.peekDiscard();
                draw = game.peekDraw();
                gameCanvas.hide();
                gameCanvas.setCards(discard, draw,
                        Settings.get().getImageSetValue());
                isTouchable = true;
            }
        });
    }

    private void setupBackButton() {
        ImageButton backButton = findViewById(R.id.gameActivityBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void updateTime() {
        this.timer.setText(formatTime(game.queryTime(
                SystemClock.elapsedRealtime())));
        this.handler.postDelayed(this::updateTime, DELAY);
        long elapsedTime = game.queryTime(SystemClock.elapsedRealtime());
        if (elapsedTime < REVEAL_DELAY) {
            this.revealHandler.postDelayed(this::revealCards,
                    REVEAL_DELAY - elapsedTime);
        } else {
            revealCards();
        }
    }

    private void removeHandler() {
        this.handler.removeCallbacksAndMessages(null);
        this.revealHandler.removeCallbacksAndMessages(null);
    }

    private void actionDown(MotionEvent event) {
        if (game.isPaused() ||
                !gameCanvas.contains(event.getX(), event.getY())) {
            return;
        }
        Image intersectedImage = gameCanvas.getIntersection(
                event.getX(), event.getY());
        if (!game.check(intersectedImage)) {
            return;
        }
        game.update(intersectedImage);
        discard = game.draw();
        if (game.isGameDone()) {
            removeHandler();
            onGameDone();
            return;
        }
        draw = game.peekDraw();
        gameCanvas.setCards(discard, draw, Settings.get().getImageSetValue());
    }

    private void actionUp() {
        this.isTouchable = !game.isGameDone();
    }

    private void onGameDone() {
        long time = game.queryTime(SystemClock.elapsedRealtime());
        game.pause(time);
        displayDialogBox(time);
    }

    private void revealCards() {
        game.resume(SystemClock.elapsedRealtime());
        gameCanvas.reveal();
        this.isInDelay = false;
    }

    private String formatTime(long time) {
        long timeInSeconds = time / 1000;
        return String.format(Locale.getDefault(), " %02d:%02d",
                timeInSeconds / 60, timeInSeconds % 60);
    }
}