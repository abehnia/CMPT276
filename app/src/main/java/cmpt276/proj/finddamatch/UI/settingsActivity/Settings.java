package cmpt276.proj.finddamatch.UI.settingsActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.HashMap;

import cmpt276.proj.finddamatch.BuildConfig;
import cmpt276.proj.finddamatch.UI.Persistable;
import cmpt276.proj.finddamatch.R;

/**
 * Contains the settings of the app
 */
public class Settings implements Persistable {
    private int imageSetValue;
    private int imageSetKey;
    private boolean initialized;
    private static HashMap<Integer, Integer> map;
    public static Settings appSettings;
    private static final String sharedFile = "cmpt276.proj.finddamatch.settings";
    private static final String IMAGE_SET_KEY_KEY = "image-set-key-key";
    private static final String IMAGE_SET_VALUE_KEY = "image-set-value-key";
    private static final int IMAGE_SET_DEFAULT_VALUE = 0;
    private static final int IMAGE_SET_DEFAULT_KEY = R.id.imageSetWesternChoice;

    private Settings() {
        this.imageSetValue = IMAGE_SET_DEFAULT_VALUE;
        this.imageSetKey = IMAGE_SET_DEFAULT_KEY;
        Settings.map = new HashMap<>();
    }

    /**
     * This function must be called before calling any of the other ones
     */
    public void init(Resources resources) {
        TypedArray typedKeys = resources.obtainTypedArray(R.array.imageset_keys);
        int[] keys = new int[typedKeys.length()];
        for (int i = 0; i < keys.length; ++i) {
            keys[i] = typedKeys.getResourceId(i, 0);
        }
        typedKeys.recycle();
        int[] values = resources.getIntArray(R.array.imageset_values);
        if (BuildConfig.DEBUG && !(keys.length == values.length)) {
            throw new AssertionError("Length of keys and values" +
                    " not equal in settings.");
        }
        for (int i = 0; i < keys.length; ++i) {
            Settings.map.put(keys[i], values[i]);
        }
        this.initialized = true;
    }

    public int getImageSetKey() {
        checkInitialized();
        return this.imageSetKey;
    }

    public int getImageSetValue() {
        checkInitialized();
        return this.imageSetValue;
    }

    public void setImageSet(int imageSetKey) {
        checkInitialized();
        Integer value = Settings.map.get(imageSetKey);
        if (value != null) {
            this.imageSetKey = imageSetKey;
            this.imageSetValue = value;
        } else {
            this.imageSetKey = IMAGE_SET_DEFAULT_KEY;
            this.imageSetValue = IMAGE_SET_DEFAULT_VALUE;
        }
    }

    public static Settings get() {
        if (appSettings == null) {
            appSettings = new Settings();
            return appSettings;
        }
        return appSettings;
    }

    @Override
    public void load(Context context) {
        checkInitialized();
        SharedPreferences settings = context.getSharedPreferences(sharedFile,
                Context.MODE_PRIVATE);
        this.imageSetKey = settings.getInt(IMAGE_SET_KEY_KEY,
                IMAGE_SET_DEFAULT_KEY);
        this.imageSetValue = settings.getInt(IMAGE_SET_VALUE_KEY,
                IMAGE_SET_DEFAULT_VALUE);

    }

    @Override
    public void save(Context context) {
        checkInitialized();
        SharedPreferences settings = context.getSharedPreferences(sharedFile,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor settingsEditor = settings.edit();
        settingsEditor.putInt(IMAGE_SET_KEY_KEY, this.imageSetKey);
        settingsEditor.putInt(IMAGE_SET_VALUE_KEY, this.imageSetValue);
        settingsEditor.apply();
    }

    private void checkInitialized() {
        if (BuildConfig.DEBUG && !this.initialized) {
            throw new AssertionError("Assertion failed");
        }
    }
}
