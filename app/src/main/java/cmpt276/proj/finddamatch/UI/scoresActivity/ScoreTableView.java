package cmpt276.proj.finddamatch.UI.scoresActivity;

import java.util.Iterator;
import java.util.List;

/**
 * View for a high score table
 */
public class ScoreTableView {
    List<ScoreView> scoreViews;

    public ScoreTableView(List<ScoreView> scoreViews) {
        this.scoreViews = scoreViews;
    }

    public void setScores(ScoreTable scoreTable) {
        Iterator<Score> scoreIterator = scoreTable.iterator();
        Iterator<ScoreView> scoreViewIterator = scoreViews.iterator();
        while (scoreIterator.hasNext()) {
            ScoreView scoreView = scoreViewIterator.next();
            Score score = scoreIterator.next();
            scoreView.setScore(score);
        }
    }

    public void setColor(int color) {
        for (ScoreView scoreView : scoreViews) {
            scoreView.setColor(color);
        }
    }
}
