package cmpt276.proj.finddamatch.scoresActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;

import cmpt276.proj.finddamatch.R;

/**
 * Class to Manage high scores. Allows to save and load all scores, and a few other needed methods.
 */

public abstract class ScoreManager extends Context {
    private static final String KEY_NAME = "Name";
    private static final String KEY_DATE = "Date";
    private static final String KEY_TIME = "Time";
    private static final int sixthScore = 5;


    public static void saveHighScore(String name, Context context) {
        ScoresIterator scores = ScoresIterator.getInstance();
        /*Getting Date*/
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d");
        String date = dateFormat.format(calendar.getTime());

        /*Set as 6th best score*/
        scores.getScores().get(sixthScore).setName(name);
        scores.getScores().get(sixthScore).setDate(date);


        /*Sorting Scores*/
        Collections.sort(scores.getScores());
    }

    public static void resetScores(Context context) {
        ScoresIterator scores = ScoresIterator.getInstance();
        int[] times = context.getResources().getIntArray(R.array.def_times);
        String[] names = context.getResources().getStringArray(R.array.def_names);
        String[] dates = context.getResources().getStringArray(R.array.def_dates);
        for (int i = 0; i < scores.getScores().size(); i++) {

            scores.getScores().get(i).setName(names[i]);
            scores.getScores().get(i).setDate(dates[i]);
            scores.getScores().get(i).setTime(times[i]);
        }
    }

    public static String getTimeString(int time, Context context) {
        String time_str;
        if (time > 120) {
            int min = (time / 60);
            int sec = (time % 60);
            time_str = context.getString(R.string.TimeMins, min, sec);

        } else if (time == 60) {
            int min = (time / 60);
            time_str = context.getString(R.string.TimeExact1Min, min);

        } else if ((time % 60) == 0) {
            int min = (time / 60);
            time_str = context.getString(R.string.TimeExactMins, min);

        } else if (time > 60) {
            int min = (time / 60);
            int sec = (time % 60);
            time_str = context.getString(R.string.Time1Min, min, sec);

        } else {
            time_str = context.getString(R.string.TimeSecs, time);
        }
        return time_str;
    }

    public static void loadAllScores(Context context) {
        ScoresIterator scores = ScoresIterator.getInstance();
        scores.getScores().clear();
        int[] times = context.getResources().getIntArray(R.array.def_times);
        String[] names = context.getResources().getStringArray(R.array.def_names);
        String[] dates = context.getResources().getStringArray(R.array.def_dates);
        for (int i = 0; i < 6; i++) {
            String key = "BestScore" + i;
            SharedPreferences prefs = context.getSharedPreferences(key, MODE_PRIVATE);

            String name = prefs.getString(KEY_NAME, names[i]);
            String date = prefs.getString(KEY_DATE, dates[i]);
            int time = prefs.getInt(KEY_TIME, times[i]);

            scores.add(new Score(name, date, time));
        }
    }

    public static void saveAllScores(Context context) {
        ScoresIterator scores = ScoresIterator.getInstance();
        for (int i = 0; i < scores.getScores().size(); i++) {
            String key = "BestScore" + i;
            SharedPreferences prefs = context.getSharedPreferences(key, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            String name = scores.getScores().get(i).getName();
            String date = scores.getScores().get(i).getDate();
            int time = scores.getScores().get(i).getTime();

            editor.putString(KEY_NAME, name);
            editor.putString(KEY_DATE, date);
            editor.putInt(KEY_TIME, time);

            editor.apply();
        }
    }


}




















