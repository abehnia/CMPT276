package cmpt276.proj.finddamatch.model;

import java.util.List;

/**
 * Interface for a card generator
 * Creates a card bases on a list of images
 */
public interface CardGenerator {
    Card generate(List<MutableImage> images);
}
