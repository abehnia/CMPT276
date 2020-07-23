package cmpt276.proj.finddamatch.model;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public interface ImageSet {

    /**
     Returns a drawable of the given ID
     */
    Drawable getImage(int id, boolean textImage);
}
