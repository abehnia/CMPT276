package cmpt276.proj.finddamatch.UI.scoresActivity;

import android.content.Context;

import org.json.JSONException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import cmpt276.proj.finddamatch.UI.Persistable;

import static cmpt276.proj.finddamatch.UI.scoresActivity.DefaultScoresGenerator.generateDefaultScores;

/**
 * Handles the persistence of the Score Manager
 */
public class ScoreState implements Persistable {
    private ScoresManager scoreManager;
    private static ScoreState scoreState;

    private ScoreState() {}

    @Override
    public void load(Context context) throws JSONException {
        try {
            FileInputStream scoreStateFileInputStream =
                    context.openFileInput("score-state");
            ObjectInput scoreStateReader = new
                    ObjectInputStream(scoreStateFileInputStream);
            this.scoreManager = (ScoresManager) scoreStateReader.readObject();
            scoreStateReader.close();
            scoreStateFileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            List<ScoreTable> currentValues = DefaultScoresGenerator.
                    generateDefaultScores(context);
            List<ScoreTable> resetValues = DefaultScoresGenerator.
                    generateDefaultScores(context);
            scoreManager = new ScoresManager(currentValues, resetValues);
        }
    }

    @Override
    public void save(Context context) throws IOException {
        FileOutputStream scoreStateFileOutputStream =
                context.openFileOutput("score-state",
                        Context.MODE_PRIVATE);
        ObjectOutputStream scoreStateWriter = new
                ObjectOutputStream(scoreStateFileOutputStream);
        scoreStateWriter.writeObject(scoreManager);
        scoreStateWriter.close();
        scoreStateFileOutputStream.close();
    }

    public void setScoreManager(ScoresManager scoreManager) {
        this.scoreManager = scoreManager;
    }

    public ScoresManager getScoreManager() {
        return this.scoreManager;
    }

    public static ScoreState get() {
        if (scoreState == null) {
            scoreState = new ScoreState();
        }
        return scoreState;
    }
}
