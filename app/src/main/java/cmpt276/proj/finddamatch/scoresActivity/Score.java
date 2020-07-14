package cmpt276.proj.finddamatch.scoresActivity;

/**
 * Comparable Class to Save Scores
 */

public class Score implements Comparable<Score> {
    private String name;
    private String date;
    private int time;

    public Score(String name, String date, int time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public int compareTo(Score o) {
        return this.getTime() - o.time;
    }
}
