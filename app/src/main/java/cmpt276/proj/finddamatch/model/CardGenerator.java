package cmpt276.proj.finddamatch.model;

import java.io.Serializable;
import java.util.List;

/**
 * Interface for a card generator
 * Creates a card bases on a list of images
 */
public interface CardGenerator extends Serializable {
    Card generate(List<MutableImage> images);
}
