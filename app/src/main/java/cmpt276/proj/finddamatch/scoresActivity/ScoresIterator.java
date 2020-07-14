package cmpt276.proj.finddamatch.scoresActivity;

/* Iterable class of Scores to create and edit list of scores. Supports Singleton method*/

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class ScoresIterator implements Iterable<Score>{
    private List<Score> scores = new ArrayList<>();


    /*
    Singleton Support
     */
    private static ScoresIterator instance;
    private ScoresIterator(){}
    public static ScoresIterator getInstance(){
        if (instance == null){
            instance = new ScoresIterator();
        }
        return instance;
    }

    public void add(Score score){
        scores.add(score);
    }

    public List<Score> getScores(){
        return scores;
    }


    @NonNull
    @Override
    public Iterator<Score> iterator() {
        return scores.iterator();
    }


}
