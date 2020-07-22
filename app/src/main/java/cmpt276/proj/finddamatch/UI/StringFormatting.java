package cmpt276.proj.finddamatch.UI;

import android.content.Context;

import cmpt276.proj.finddamatch.R;

public class StringFormatting {
    public static String getTimeString(int time, Context context) {
        String time_str;
        int min = (time/60);
        int sec = (time%60);
        if(sec < 10){
            time_str = context.getString(R.string.TimeSecs, min, sec);
        }else{
            time_str = context.getString(R.string.TimeMins, min, sec);
        }
        return time_str;
    }
}
