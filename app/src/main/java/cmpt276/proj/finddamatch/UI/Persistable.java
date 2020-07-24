package cmpt276.proj.finddamatch.UI;

import android.content.Context;

import org.json.JSONException;

import java.io.IOException;

/**
 * Classes that persist through a restart of the app
 * must implement this interface
 */
public interface Persistable {
    /**
     * Loads the state of the class
     */
    void load(Context context);

    /**
     * Saves the state of the class
     */
    void save(Context context);
}
