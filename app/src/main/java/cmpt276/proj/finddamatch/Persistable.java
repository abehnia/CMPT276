package cmpt276.proj.finddamatch;

import android.content.Context;

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
