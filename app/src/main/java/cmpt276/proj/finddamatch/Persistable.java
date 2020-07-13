package cmpt276.proj.finddamatch;

import android.content.Context;

import java.io.IOException;

/**
 * Classes that persist through a restart of the app
 * must implement this interface
 */
public interface Persistable {
    /**
     * Loads the state of the class
     */
    void load(Context context) throws IOException, ClassNotFoundException;

    /**
     * Saves the state of the class
     */
    void save(Context context) throws IOException;
}
