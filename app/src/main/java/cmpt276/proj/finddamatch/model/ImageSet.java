package cmpt276.proj.finddamatch.model;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * Interface for an Image Set
 * Creates image set for current game mode
 */

public interface ImageSet {

    /**
     Returns a drawable of the given ID
     */
    Drawable getImage(int id, boolean textImage);
}
