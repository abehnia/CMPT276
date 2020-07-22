package cmpt276.proj.finddamatch.UI.scoresActivity;

import android.widget.TextView;

import cmpt276.proj.finddamatch.UI.StringFormatting;

public class ScoreView {
    private TextView name;
    private TextView date;
    private TextView time;

    public ScoreView(TextView name, TextView date, TextView time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public void setScore(Score score) {
        name.setText(score.getName());
        date.setText(score.getDate());
        String time = StringFormatting.getTimeString(score.getTime());
        this.time.setText(time);
    }

    public void setColor(int color) {
        name.setTextColor(color);
        date.setTextColor(color);
        time.setTextColor(color);
    }
}
