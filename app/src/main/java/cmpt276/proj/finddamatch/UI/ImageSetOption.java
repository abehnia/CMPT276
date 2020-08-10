package cmpt276.proj.finddamatch.UI;

import java.io.Serializable;

/**
 * Interface for ImageSetOption
 */

public interface ImageSetOption extends Serializable {
    int getID();

    boolean isEquivalent(ImageSetOption imageSetOption);
}
