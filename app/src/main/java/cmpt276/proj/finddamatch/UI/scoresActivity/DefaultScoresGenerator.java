package cmpt276.proj.finddamatch.UI.scoresActivity;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import cmpt276.proj.finddamatch.R;

/**
 * Generates default scores from an internal JSON file
 */
public class DefaultScoresGenerator {
    private static String generateJsonString(Context context) {
        String json = null;
        try {
            InputStream is = context.getResources().
                    openRawResource(R.raw.default_values);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    public static List<ScoreTable> generateDefaultScores(Context context) {
        String jsonString = generateJsonString(context);
        try {
            JSONArray array = new JSONArray(jsonString);
            return JsonParser.parseDefaultScores(array);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
