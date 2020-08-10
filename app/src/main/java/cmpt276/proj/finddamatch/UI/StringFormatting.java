package cmpt276.proj.finddamatch.UI;

import java.util.Locale;

/**
 * Formats the strings
 */
public class StringFormatting {
    private static final String SEC_FORMAT = "%1$d:0%2$d";
    private static final String MIN_FORMAT = "%1$d:%2$d";
    private static final int SEC_LIMIT = 10;
    private static final int MIN_TO_SEC = 60;

    public static String getTimeString(int time) {
        String time_str;
        int min = (time / MIN_TO_SEC);
        int sec = (time % MIN_TO_SEC);
        if (sec < SEC_LIMIT) {
            time_str = String.format(Locale.getDefault(),
                    SEC_FORMAT, min, sec);
        } else {
            time_str = String.format(Locale.getDefault(),
                    MIN_FORMAT, min, sec);
        }
        return time_str;
    }
}
