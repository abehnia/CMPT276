package cmpt276.proj.finddamatch.UI.scoresActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cmpt276.proj.finddamatch.BuildConfig;
import cmpt276.proj.finddamatch.model.gameLogic.VALID_GAME_MODE;


public class JsonParser {

    public static Score parseScore(JSONObject object) throws JSONException {
        String name = object.getString("name");
        int time = object.getInt("time");
        String date = object.getString("date");
        return new Score(name, date, time);
    }

    public static ScoreTable parseScoreTable(JSONObject object)
            throws JSONException {
        ArrayList<Score> scores = new ArrayList<>();
        VALID_GAME_MODE gameMode = parseGameMode(object);
        JSONArray array = object.getJSONArray("values");
        for (int i = 0; i < array.length(); ++i) {
            scores.add(parseScore(array.getJSONObject(i)));
        }
        return new ScoreTable(scores, gameMode);
    }

    public static VALID_GAME_MODE parseGameMode(JSONObject object)
            throws JSONException {
        JSONObject gameMode = object.getJSONObject("mode");
        int order = gameMode.getInt("order");
        int size = gameMode.getInt("size");
        for (VALID_GAME_MODE validGameMode : VALID_GAME_MODE.values()) {
            if (validGameMode.getOrder() == order &&
                    validGameMode.getSize() == size) {
                return validGameMode;
            }
        }
        if (BuildConfig.DEBUG) {
            throw new AssertionError(
                    "Error in JSON file for default scores");
        }
        return null;
    }

    public static List<ScoreTable> parseDefaultScores
            (JSONArray array) throws JSONException {
        List<ScoreTable> scoreTables = new ArrayList<>();
        for (int i = 0; i < array.length(); ++i) {
            ScoreTable scoreTable = parseScoreTable(array.getJSONObject(i));
            scoreTables.add(scoreTable);
        }
        return scoreTables;
    }
}
