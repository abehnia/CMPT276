package cmpt276.proj.finddamatch.UI.settingsActivity;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.HashMap;
import java.util.Map;

import cmpt276.proj.finddamatch.BuildConfig;
import cmpt276.proj.finddamatch.R;

public class SettingsMapper {
    private Map<String, Integer> orderMap;
    private Map<String, Integer> sizeMap;
    private Map<String, Boolean> hasTextMap;
    private Map<String, Integer> imageSetIDMap;

    public SettingsMapper(Resources resources) {
        this.orderMap = new HashMap<>();
        this.sizeMap = new HashMap<>();
        this.hasTextMap = new HashMap<>();
        this.imageSetIDMap = new HashMap<>();
        init(resources);
    }

    private void init(Resources resources) {
        String[] values = resources.getStringArray(R.array.gameorder_values);
        parseMap(values, orderMap, Integer::parseInt);
        values = resources.getStringArray(R.array.imageset_values);
        parseMap(values, imageSetIDMap, Integer::parseInt);
        values = resources.getStringArray(R.array.gamesize_values);
        parseMap(values, sizeMap, Integer::parseInt);
    }

//    typedKeys = resources.obtainTypedArray(R.array.imageset_keys);
//    String[] values = resources.getStringArray(R.array.imageset_values);
//    map.put(keys[i], values[i]);
//    int[] keys = new int[typedKeys.length()];
//        for (int i = 0; i < keys.length; ++i) {
//        keys[i] = typedKeys.getResourceId(i, 0);
//    }
//        typedKeys.recycle();

    private <T, U extends StringMapper<T>>
    void parseMap(String[] values, Map<String, T> map, U mapper) {
        for (String value : values) {
            map.put(value, mapper.map(value));
        }
    }

}
