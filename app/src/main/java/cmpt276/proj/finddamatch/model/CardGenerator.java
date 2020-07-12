package cmpt276.proj.finddamatch.model;

import java.util.List;

public interface CardGenerator {
    Card generate(List<MutableImage> images);
}
