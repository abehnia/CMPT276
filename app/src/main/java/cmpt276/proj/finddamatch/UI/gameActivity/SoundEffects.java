package cmpt276.proj.finddamatch.UI.gameActivity;

import android.content.Context;
import android.media.SoundPool;

import cmpt276.proj.finddamatch.R;

/**
 * Loads and plays sound
 */
public class SoundEffects {
    private SoundPool soundPool;
    private int endGameSound;
    private int startGameSound;
    private int wrongClickSound;
    private int correctClickSound;

    public SoundEffects(Context context) {
        this.soundPool = new SoundPool.Builder().build();
        this.endGameSound = soundPool.load(context, R.raw.sound_game_end,1);
        this.startGameSound = soundPool.load(context, R.raw.sound_game_start,1);
        this.wrongClickSound = soundPool.load(context, R.raw.sound_wrong_click,1);
        this.correctClickSound = soundPool.load(context, R.raw.sound_correct_click,1);
    }

    public void playStartGameSound(){
        soundPool.play(startGameSound,1,1,0,0,1);
    }

    public void playEndGameSound(){
        soundPool.play(endGameSound,1,1,0,0,1);
    }

    public void playWrongClickSound(){
        soundPool.play(wrongClickSound,1,1,0,0,1);
    }

    public void playCorrectClickSound(){
        soundPool.play(correctClickSound,1,1,0,0,1);
    }



}
