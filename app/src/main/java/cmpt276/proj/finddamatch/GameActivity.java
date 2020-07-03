package cmpt276.proj.finddamatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.CardMockImpl;
import cmpt276.proj.finddamatch.model.ImageMockImpl;

public class GameActivity extends AppCompatActivity {
    private GameCanvas gameCanvas;
    private int counter = 0;
    private Button button;
    Card lead, guess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameCanvas = findViewById(R.id.game_activity_game_canvas);
        gameCanvas.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameActivity.this.counter += 1;
                GameActivity.this.counter %= 7;
            }
        });
        lead = new CardMockImpl(new ImageMockImpl(0.5f, 0.5f, 1.0f, (float) (Math.PI/2.f), 0),
                                new ImageMockImpl(0.5f, -0.5f, 1.0f, (float) (Math.PI/2.f), 1),
                                new ImageMockImpl(0, 0.5f, 1.0f, (float) (Math.PI/2.0), 2));
        guess = new CardMockImpl(new ImageMockImpl(0.5f, 0.5f, 1.0f, (float) (Math.PI/2.f), 0),
                new ImageMockImpl(0.5f, -0.5f, 1.0f, (float) (Math.PI/2.f), 1),
                new ImageMockImpl(0, 0.5f, 1.0f, (float) (Math.PI/2.0), 2));
        gameCanvas.setInitialCards(lead, guess);
        gameCanvas.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (gameCanvas.intersects(event.getX(), event.getY())) {
                    Toast.makeText(GameActivity.this, "yep", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, GameActivity.class);
    }
}