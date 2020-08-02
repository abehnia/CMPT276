package cmpt276.proj.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.media.SoundPool.Builder;
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
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreManager;
import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.CardGenerator;
import cmpt276.proj.finddamatch.model.DeckGenerator;
import cmpt276.proj.finddamatch.model.Game;
import cmpt276.proj.finddamatch.model.Image;
import cmpt276.proj.finddamatch.model.gameLogic.DeckGeneratorImpl;
import cmpt276.proj.finddamatch.model.gameLogic.GameImpl;
import cmpt276.proj.finddamatch.UI.settingsActivity.Settings;
import cmpt276.proj.finddamatch.model.gameLogic.HardCardGenerator;
import cmpt276.proj.finddamatch.model.gameLogic.ParameterTuner;

import static cmpt276.proj.finddamatch.model.gameLogic.VALID_GAME_MODE.GAME1;

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
    private boolean isPlayed;
    private static final int DELAY = 100;
    private static final int REVEAL_DELAY = 1500;
    private ScoreManager scoreManager;
    private SoundPool soundPool;
    private int endGameSound;
    private int startGameSound;
    private int wrongClickSound;
    private int correctClickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.isTouchable = true;
        setupSound();
        setupGame();
        setupCanvas();
        setupTouch();
        setupTimer();
        setupHandler();
        setupButton();
        setupBackButton();
    }

    private void setupSound() {
        this.soundPool = new SoundPool.Builder().build();
        this.endGameSound = soundPool.load(this, R.raw.sound_game_end,1);
        this.startGameSound = soundPool.load(this, R.raw.sound_game_start,1);
        this.wrongClickSound = soundPool.load(this, R.raw.sound_wrong_click,1);
        this.correctClickSound = soundPool.load(this, R.raw.sound_correct_click,1);
        this.isPlayed = false;
    }

    private void playStartSound(){
        if(!isPlayed){
            soundPool.play(startGameSound,1,1,0,0,1);
            isPlayed = true;
        }
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
        scoreManager.setTime(time);
        FragmentManager manager = getSupportFragmentManager();
        DialogBoxFragment dialog = new DialogBoxFragment();
        dialog.setCancelable(false);
        dialog.show(manager, "Best Scores Dialog");
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, GameActivity.class);
    }

    private void setupGame() {
        this.scoreManager = ScoreState.get().getScoreManager();
        Settings settings = Settings.get();
        ParameterTuner parameterTuner = new ParameterTuner(settings.getGameMode());
        CardGenerator cardGenerator = new HardCardGenerator(parameterTuner,
                settings.getGameMode().hasText());
        DeckGenerator deckGenerator = new DeckGeneratorImpl(cardGenerator,
                settings.getGameMode());
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
                gameCanvas.setCards(draw, discard);
                gameCanvas.hide();
                ;
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
        resetButton.setOnClickListener(v -> {
            removeHandler();
            long time = SystemClock.elapsedRealtime();
            game.reset(time);
            game.pause(time);
            isInDelay = true;
            updateTime();
            discard = game.peekDiscard();
            draw = game.peekDraw();
            gameCanvas.hide();
            gameCanvas.setCards(draw, discard);
            isTouchable = true;
        });
    }

    private void setupBackButton() {
        ImageButton backButton = findViewById(R.id.gameActivityBackButton);
        backButton.setOnClickListener(v -> finish());
    }

    private void updateTime() {
        this.timer.setText(formatTime(game.queryTime(
                SystemClock.elapsedRealtime())));
        this.handler.postDelayed(this::updateTime, DELAY);
        long elapsedTime = game.queryTime(SystemClock.elapsedRealtime());
        if (elapsedTime < REVEAL_DELAY) {
            this.revealHandler.postDelayed(this::revealCards,
                    REVEAL_DELAY - elapsedTime);
            this.revealHandler.postDelayed(this::playStartSound,
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
            soundPool.play(wrongClickSound,1,1,0,0,1);
            return;
        }
        game.update(intersectedImage);
        discard = game.draw();
        if (game.isGameDone()) {
            removeHandler();
            onGameDone();
            return;
        }
        soundPool.play(correctClickSound,1,1,0,0,1);
        draw = game.peekDraw();
        gameCanvas.setCards(draw, discard);
    }

    private void actionUp() {
        this.isTouchable = !game.isGameDone();
    }

    private void onGameDone() {
        long time = game.queryTime(SystemClock.elapsedRealtime());
        game.pause(time);
        soundPool.play(endGameSound,1,1,0,0,1);
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