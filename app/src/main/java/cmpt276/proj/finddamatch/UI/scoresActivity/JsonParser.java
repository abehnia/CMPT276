package cmpt276.proj.finddamatch.UI.scoresActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cmpt276.proj.finddamatch.UI.GameMode;
import cmpt276.proj.finddamatch.UI.GameModeImpl;

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
        GameMode gameMode = parseGameMode(object);
        JSONArray array = object.getJSONArray("values");
        for (int i = 0; i < array.length(); ++i) {
            scores.add(parseScore(array.getJSONObject(i)));
        }
        return new ScoreTable(scores, gameMode);
    }

    public static GameMode parseGameMode(JSONObject object)
            throws JSONException {
        JSONObject gameMode = object.getJSONObject("mode");
        int order = gameMode.getInt("order");
        int size = gameMode.getInt("size");
        return new GameModeImpl(order, size);
    }

    public static Map<GameMode, ScoreTable> parseDefaultScores
            (JSONArray array) throws JSONException {
        HashMap<GameMode, ScoreTable> map = new HashMap<>();
        for (int i = 0; i < array.length(); ++i) {
            ScoreTable scoreTable = parseScoreTable(array.getJSONObject(i));
            map.put(scoreTable.getGameMode(), scoreTable);
        }
        return map;
    }
}
