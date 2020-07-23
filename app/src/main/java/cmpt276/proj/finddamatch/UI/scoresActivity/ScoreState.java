package cmpt276.proj.finddamatch.UI.scoresActivity;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import cmpt276.proj.finddamatch.UI.Persistable;

/**
 * Handles the persistence of the Score Manager
 */
public class ScoreState implements Persistable {
    private static final String SCORE_STATE_FILE_NAME = "score-state";
    private ScoreManager scoreManager;
    private static ScoreState scoreState;

    private ScoreState() {}

    @Override
    public void load(Context context) {
        try {
            FileInputStream scoreStateFileInputStream =
                    context.openFileInput(SCORE_STATE_FILE_NAME);
            ObjectInput scoreStateReader = new
                    ObjectInputStream(scoreStateFileInputStream);
            this.scoreManager = (ScoreManager) scoreStateReader.readObject();
            List<ScoreTable> resetValues = DefaultScoresGenerator.
                    generateDefaultScores(context);
            assert resetValues != null;
            this.scoreManager.resetDefaultScores(resetValues);
            scoreStateReader.close();
            scoreStateFileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            List<ScoreTable> currentValues = DefaultScoresGenerator.
                    generateDefaultScores(context);
            assert currentValues != null;
            List<ScoreTable> resetValues = DefaultScoresGenerator.
                    generateDefaultScores(context);
            scoreManager = new ScoreManager(currentValues, resetValues);
        }
    }

    @Override
    public void save(Context context) {
        try {
            FileOutputStream scoreStateFileOutputStream = context.
                    openFileOutput(SCORE_STATE_FILE_NAME, Context.MODE_PRIVATE);
            ObjectOutputStream scoreStateWriter = new
                    ObjectOutputStream(scoreStateFileOutputStream);
            scoreStateWriter.writeObject(scoreManager);
            scoreStateWriter.close();
            scoreStateFileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ScoreManager getScoreManager() {
        return this.scoreManager;
    }

    public static ScoreState get() {
        if (scoreState == null) {
            scoreState = new ScoreState();
        }
        return scoreState;
    }
}
