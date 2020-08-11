package cmpt276.proj.finddamatch.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import cmpt276.proj.finddamatch.UI.gameActivity.ExportCanvas;
import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.UI.flickrActivity.BitmapStorer;
import cmpt276.proj.finddamatch.UI.gameActivity.CustomImageImpl;
import cmpt276.proj.finddamatch.UI.gameActivity.GameCanvas;
import cmpt276.proj.finddamatch.UI.gameActivity.ImageSetImpl;
import cmpt276.proj.finddamatch.UI.gameActivity.SoundEffects;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreState;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreManager;
import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.DeckGenerator;
import cmpt276.proj.finddamatch.model.Game;
import cmpt276.proj.finddamatch.model.GameGenerator;
import cmpt276.proj.finddamatch.model.Image;
import cmpt276.proj.finddamatch.model.ImageSet;
import cmpt276.proj.finddamatch.model.gameLogic.GameGeneratorImpl;
import cmpt276.proj.finddamatch.UI.settingsActivity.Settings;
import static cmpt276.proj.finddamatch.UI.ValidImageSet.Custom;

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
    private ExportCanvas exportCanvas;
    private ImageSet imageSet;
    private int STORAGE_PERMISSION_CODE = 1;
    private SoundEffects soundEffects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.soundEffects = new SoundEffects(GameActivity.this);
        this.isPlayed = false;
        this.isTouchable = true;
        setupImageSet();
        setupGame();
        setupCanvas();
        setupTouch();
        setupTimer();
        setupHandler();
        setupButton();
        setupBackButton();
        setupExportButton();
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
        GameGenerator gameGenerator = new GameGeneratorImpl(settings.getGameMode(),
                settings.getDifficulty());
        game = gameGenerator.generate(SystemClock.elapsedRealtime());
        long time = SystemClock.elapsedRealtime();
        game.reset(time);
        game.pause(time);
        discard = game.peekDiscard();
        draw = game.peekDraw();
        this.isInDelay = true;
        exportCanvas = new ExportCanvas(getResources(), gameGenerator.generateDeck()
                , imageSet);
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

    /**
     * Request permission WRITE_EXTERNAL_STORAGE permission prior to export
     */
    private void setupExportButton() {
        Button exportButton = findViewById(R.id.game_activity_export_button);
        exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(GameActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(GameActivity.this, R.string.previous_permission_check,
                            Toast.LENGTH_SHORT).show();
                    bitmapExport();
                } else {
                    ActivityCompat.requestPermissions(GameActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            STORAGE_PERMISSION_CODE);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[]
            permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, R.string.request_permission_granted,
                        Toast.LENGTH_SHORT).show();
                bitmapExport();
            } else {
                Toast.makeText(this, R.string.request_permission_denied,
                        Toast.LENGTH_SHORT).show();
            }
            return;
        }
    }

    private void bitmapExport() {
        List<Bitmap> exportBitmaps = exportCanvas.export();
        BitmapStorer.get().addExport(exportBitmaps);
        BitmapStorer.get().export(GameActivity.this);
    }

    private void setupButton() {
        Button resetButton = findViewById(R.id.game_activity_shuffle_button);
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

    private void playStartSound() {
        if (!isPlayed) {
            soundEffects.playStartGameSound();
            isPlayed = true;
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
            soundEffects.playWrongClickSound();
            return;
        }
        game.update(intersectedImage);
        discard = game.draw();
        if (game.isGameDone()) {
            removeHandler();
            onGameDone();
            return;
        }
        soundEffects.playCorrectClickSound();
        draw = game.peekDraw();
        gameCanvas.setCards(draw, discard);
    }

    private void actionUp() {
        this.isTouchable = !game.isGameDone();
    }

    private void onGameDone() {
        long time = game.queryTime(SystemClock.elapsedRealtime());
        game.pause(time);
        soundEffects.playEndGameSound();
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

    private void setupImageSet() {
        if (Settings.get().getImageSet().isEquivalent(Custom)) {
            imageSet = new CustomImageImpl(BitmapStorer.get().getBitmaps(),
                    getResources());
        } else {
            imageSet = new ImageSetImpl(getResources());
        }
    }
}