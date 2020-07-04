package cmpt276.proj.finddamatch.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import cmpt276.proj.finddamatch.GameActivity;
import cmpt276.proj.finddamatch.R;

public abstract class ScoreManger extends Context {
    private static final String KEY_NAME = "Name";
    private static final String KEY_DATE = "Date";
    private static final String KEY_TIME = "Time";


    public static void saveHighScore(String name, int time, Context context){

//        Getting Date
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d");
        String date = dateFormat.format(calendar.getTime());

//        Setting new score as 6th best score
        String key = "BestScore" + 6;
        SharedPreferences prefs = context.getSharedPreferences(key, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_NAME, name);
        editor.putInt(KEY_TIME, time);
        editor.putString(KEY_DATE, date);
        editor.apply();

//        Sorting Scores
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6 - i; j++) {
                int x = getScoreTime(j, context);
                int y = getScoreTime(j+1, context);
                if (x > y) {
                    // swap arr[j+1] and arr[i]
                    int tmp_time = getScoreTime(j, context);
                    String tmp_name = getScoreName(j, context);
                    String tmp_date = getScoreDate(j, context);

                    setScoreTime(j, getScoreTime(j + 1, context), context);
                    setScoreDate(j, getScoreDate(j + 1, context), context);
                    setScoreName(j, getScoreName(j + 1, context), context);

                    setScoreTime(j + 1, tmp_time, context);
                    setScoreDate(j + 1, tmp_date, context);
                    setScoreName(j + 1, tmp_name, context);
                    }
            }
        }

    }

    public static String getScoreName(int i, Context context){
        String key = "BestScore" + i;
        SharedPreferences prefs = context.getSharedPreferences(key, MODE_PRIVATE);


        String filename = "BestScore" + i + "Name";
        int id = context.getResources().getIdentifier(filename, "string", context.getPackageName());
        String defVal = context.getString(id);

        return prefs.getString(KEY_NAME,defVal);
    }

    public static String getScoreDate(int i, Context context){
        String key = "BestScore" + i;
        SharedPreferences prefs = context.getSharedPreferences(key, MODE_PRIVATE);

        String filename = "BestScore" + i + "Date";
        int id = context.getResources().getIdentifier(filename, "string", context.getPackageName());
        String defVal = context.getString(id);
        
        return prefs.getString(KEY_DATE,defVal);
    }

    public static int getScoreTime(int i, Context context){
        int[] times= context.getResources().getIntArray(R.array.def_times);
        String key = "BestScore" + i;
        SharedPreferences prefs = context.getSharedPreferences(key, MODE_PRIVATE);

        return prefs.getInt(KEY_TIME,times[i-1]);
    }

    public static void setScoreName(int i, String name, Context context){
        String key = "BestScore" + i;
        SharedPreferences prefs = context.getSharedPreferences(key, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_NAME, name);
        editor.apply();
    }

    public static void setScoreDate(int i, String date, Context context){
        String key = "BestScore" + i;
        SharedPreferences prefs = context.getSharedPreferences(key, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_DATE, date);
        editor.apply();
    }

    public static void setScoreTime(int i, int time, Context context){
        String key = "BestScore" + i;
        SharedPreferences prefs = context.getSharedPreferences(key, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_TIME, time);
        editor.apply();
    }

    public static void resetScores(Context context){
        for (int i = 6; i >= 1; i--) {
            String key = "BestScore" + i;
            SharedPreferences prefs = context.getSharedPreferences(key, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear().apply();
        }
    }


}




















