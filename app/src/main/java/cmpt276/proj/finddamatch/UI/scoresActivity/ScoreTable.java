package cmpt276.proj.finddamatch.UI.scoresActivity;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import cmpt276.proj.finddamatch.model.GameMode;
import cmpt276.proj.finddamatch.model.gameLogic.VALID_GAME_MODE;

public class ScoreTable implements Iterable<Score>, Serializable {
    private List<Score> scores;
    private GameMode gameMode;

    public ScoreTable(VALID_GAME_MODE gameMode) {
        this.scores = new ArrayList<>();
        this.gameMode = gameMode;
    }

    public ScoreTable(ScoreTable scoreTable) {
        this.gameMode = scoreTable.gameMode;
        this.scores = new ArrayList<>();
        for (Score score : scoreTable.scores) {
            this.scores.add(new Score(score));
        }
        sort();
    }

    public ScoreTable(List<Score> scores, VALID_GAME_MODE gameMode) {
        this.scores = scores;
        this.gameMode = gameMode;
    }

    public void add(Score score) {
        Score last = scores.get(scores.size() - 1);
        if (score.compareTo(last) < 0) {
            scores.remove(last);
            scores.add(score);
            sort();
        }
    }

    public void sort() {
        Collections.sort(scores);
    }

    public GameMode getGameMode() {
        return this.gameMode;
    }

    public void clear() {
        scores.clear();
    }

                      @NonNull
                      @Override
    public Iterator<Score> iterator() {
        return scores.iterator();
    }
}
