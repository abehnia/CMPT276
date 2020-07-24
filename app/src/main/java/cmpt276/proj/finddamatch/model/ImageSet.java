package cmpt276.proj.finddamatch.model;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * Interface for an Image Set
 * Creates image set for current game mode
 */

public interface ImageSet {

    /**
     * @param color to be set as TextDrawables's color
     */
    void setColor(int color);

    /**
     * Returns a drawable of the given ID
     */
    Drawable getImage(int id, boolean textImage);

    /**
     * @return the logo set to the back of the card
     */
    Drawable getBackLogo();
}
