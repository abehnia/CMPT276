package cmpt276.proj.finddamatch.model;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import cmpt276.proj.finddamatch.GameActivity;

public abstract class ScoreManger extends Context {
    private static final String KEY_NAME = "Name";
    private static final String KEY_DATE = "Date";
    private static final String KEY_TIME = "Time";


    private static Calendar calendar;
    private static SimpleDateFormat dateFormat;
    private static String date;

    public static void saveHighScore(int i, String name, String time, Context context){
        String key = "BestScore" + i;
        SharedPreferences prefs = context.getSharedPreferences(key, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_TIME, time);

//        Getting Date
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("EEE, MMM d");
        date = dateFormat.format(calendar.getTime());
        editor.putString(KEY_DATE, date);
        editor.apply();

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

    public static String getScoreTime(int i, Context context){
        String key = "BestScore" + i;
        SharedPreferences prefs = context.getSharedPreferences(key, MODE_PRIVATE);

        String filename = "BestScore" + i + "Time";
        int id = context.getResources().getIdentifier(filename, "string", context.getPackageName());
        String defVal = context.getString(id);
        
        return prefs.getString(KEY_TIME,defVal);
    }



}




















