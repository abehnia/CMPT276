package cmpt276.proj.finddamatch.UI.scoresActivity;

import android.content.Context;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cmpt276.proj.finddamatch.UI.GameMode;
import cmpt276.proj.finddamatch.UI.Persistable;

public class ScoresManager implements Persistable, Serializable {
    private Map<GameMode, ScoreTable> scoreTables;
    private Map<GameMode, ScoreTable> defaultValues;

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
    }

    public void addScore(GameMode gameMode, Score score) {
        ScoreTable scoreTable = scoreTables.get(gameMode);
        if (scoreTable != null) {
            scoreTable.add(score);
        }
    }

    public ScoreTable getScoreTable(GameMode gameMode) {
        return scoreTables.get(gameMode);
    }

    public void resetScoreTable(GameMode gameMode) {
        if (scoreTables.containsKey(gameMode)) {
            scoreTables.put(gameMode, new
                    ScoreTable(defaultValues.get(gameMode)));
        }
    }

    @Override
    public void load(Context context) {
    }

    @Override
    public void save(Context context) {

    }
}
