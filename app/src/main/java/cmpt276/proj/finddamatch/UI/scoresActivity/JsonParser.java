package cmpt276.proj.finddamatch.UI.scoresActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cmpt276.proj.finddamatch.BuildConfig;
import cmpt276.proj.finddamatch.model.gameLogic.VALID_GAME_MODE;


public class JsonParser {

    private static final String NAME_KEY = "name";
    private static final String TIME_KEY = "time";
    private static final String DATE_KEY = "date";
    private static final String SCORE_TABLE_KEY = "values";
    private static final String GAME_MODE_KEY = "mode";
    private static final String ORDER_KEY = "order";
    private static final String SIZE_KEY = "size";
    private static final String ERROR_MESSAGE =
            "Error in JSON file for default scores";

    public static Score parseScore(JSONObject object) throws JSONException {
        String name = object.getString(NAME_KEY);
        int time = object.getInt(TIME_KEY);
        String date = object.getString(DATE_KEY);
        return new Score(name, date, time);
    }

    public static ScoreTable parseScoreTable(JSONObject object)
            throws JSONException {
        ArrayList<Score> scores = new ArrayList<>();
        VALID_GAME_MODE gameMode = parseGameMode(object);
        JSONArray array = object.getJSONArray(SCORE_TABLE_KEY);
        for (int i = 0; i < array.length(); ++i) {
            scores.add(parseScore(array.getJSONObject(i)));
        }
        return new ScoreTable(scores, gameMode);
    }

    public static VALID_GAME_MODE parseGameMode(JSONObject object)
            throws JSONException {
        JSONObject gameMode = object.getJSONObject(GAME_MODE_KEY);
        int order = gameMode.getInt(ORDER_KEY);
        int size = gameMode.getInt(SIZE_KEY);
        for (VALID_GAME_MODE validGameMode : VALID_GAME_MODE.values()) {
            if (validGameMode.getOrder() == order &&
                    validGameMode.getSize() == size) {
                return validGameMode;
            }
        }
        if (BuildConfig.DEBUG) {
            throw new AssertionError(ERROR_MESSAGE);
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
