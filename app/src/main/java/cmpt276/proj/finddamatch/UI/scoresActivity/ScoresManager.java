package cmpt276.proj.finddamatch.UI.scoresActivity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cmpt276.proj.finddamatch.model.GameMode;
import cmpt276.proj.finddamatch.model.gameLogic.VALID_GAME_MODE;

public class ScoresManager implements Serializable {
    private Map<GameMode, ScoreTable> scoreTables;
    private Map<GameMode, ScoreTable> defaultValues;
    private Score currentScore;

    public ScoresManager(List<ScoreTable> scoreTables,
                         List<ScoreTable> defaultValues) {
        this.scoreTables = new HashMap<>();
        this.defaultValues = new HashMap<>();
        for (ScoreTable scoreTable : scoreTables) {
            this.scoreTables.put(scoreTable.getGameMode(), scoreTable);
        }
        for (ScoreTable scoreTable : defaultValues) {
            this.defaultValues.put(scoreTable.getGameMode(), scoreTable);
        }
        this.currentScore = new Score("initial name",
                "initial date", 0);
    }

    public void addScore(VALID_GAME_MODE gameMode, Score score) {
        ScoreTable scoreTable = scoreTables.get(gameMode);
        if (scoreTable != null) {
            scoreTable.add(score);
        }
    }

    public ScoreTable getScoreTable(VALID_GAME_MODE gameMode) {
        return scoreTables.get(gameMode);
    }

    public void resetScoreTable(VALID_GAME_MODE gameMode) {
        if (scoreTables.containsKey(gameMode)) {
            scoreTables.put(gameMode, new
                    ScoreTable(defaultValues.get(gameMode)));
        }
    }

    public Score getCurrentScore() {
        return currentScore;
    }

    public void resetDefaultScores(List<ScoreTable> resetValues) {
        for (ScoreTable scoreTable : resetValues) {
            this.defaultValues.put(scoreTable.getGameMode(), scoreTable);
        }
    }
}
