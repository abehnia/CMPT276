package cmpt276.proj.finddamatch.UI.scoresActivity;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cmpt276.proj.finddamatch.UI.StringFormatting;
import cmpt276.proj.finddamatch.model.gameLogic.VALID_GAME_MODE;

public class ScoreViewGenerator {
    private static List<ScoreView> populate(View v, List<TypedArray> arrays,
                                           ScoreTable scoreTable) {
        ArrayList<ScoreView> scoreViews = new ArrayList<>();
        TextView txtName;
        TextView txtDate;
        TextView txtTime;

        TypedArray typedNameIds = arrays.get(0);
        TypedArray typedDateIds = arrays.get(1);
        TypedArray typedTimeIds = arrays.get(2);

        int index = 0;
        for (Score ignored : scoreTable) {
            txtName = v.findViewById(typedNameIds.
                    getResourceId(index, 0));
            txtDate = v.findViewById(typedDateIds.
                    getResourceId(index, 0));
            txtTime = v.findViewById(typedTimeIds.
                    getResourceId(index, 0));
            scoreViews.add(new ScoreView(txtName, txtDate,txtTime));
            ++index;
        }
        typedNameIds.recycle();
        typedDateIds.recycle();
        typedTimeIds.recycle();
        return scoreViews;
    }

    public static ScoreTableView generate(View v, List<TypedArray> arrays,
                                          ScoreTable scoreTable) {
        return new ScoreTableView(populate(v, arrays, scoreTable));
    }
}
