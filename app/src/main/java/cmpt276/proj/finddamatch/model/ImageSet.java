package cmpt276.proj.finddamatch.model;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public abstract interface ImageSet {

    /**
     * Returns a drawable of the given ID
     */
    Drawable getDrawables(int id, boolean textImage, Resources resources);
}
