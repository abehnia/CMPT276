package cmpt276.proj.finddamatch.UI.scoresActivity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cmpt276.proj.finddamatch.model.GameMode;
import cmpt276.proj.finddamatch.model.gameLogic.ValidGameMode;

/**
 * Manages all the high score tables
 */
public class ScoreManager implements Serializable {
    private Map<GameMode, ScoreTable> scoreTables;
    private Map<GameMode, ScoreTable> defaultValues;
    private int currentTime;

    public ScoreManager(List<ScoreTable> scoreTables,
                        List<ScoreTable> defaultValues) {
        this.scoreTables = new HashMap<>();
        this.defaultValues = new HashMap<>();
        for (ScoreTable scoreTable : scoreTables) {
            this.scoreTables.put(scoreTable.getGameMode(), scoreTable);
        }
        for (ScoreTable scoreTable : defaultValues) {
            this.defaultValues.put(scoreTable.getGameMode(), scoreTable);
        }
        this.currentTime = 0;
    }

    public void addScore(ValidGameMode gameMode, Score score) {
        ScoreTable scoreTable = scoreTables.get(gameMode);
        if (scoreTable != null) {
            scoreTable.add(score);
        }
    }

    public ScoreTable getScoreTable(ValidGameMode gameMode) {
        return scoreTables.get(gameMode);
    }

    public void resetScoreTable(ValidGameMode gameMode) {
        if (scoreTables.containsKey(gameMode)) {
            scoreTables.put(gameMode, new
                    ScoreTable(defaultValues.get(gameMode)));
        }
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void resetDefaultScores(List<ScoreTable> resetValues) {
        this.defaultValues.clear();
        for (ScoreTable scoreTable : resetValues) {
            this.defaultValues.put(scoreTable.getGameMode(), scoreTable);
        }
    }

    public void setTime(int time) {
        this.currentTime = time;
    }

    public int size() {
        return scoreTables.size();
    }
}
