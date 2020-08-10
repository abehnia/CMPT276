package cmpt276.proj.finddamatch.model;

/**
 * Passes different JSON formats and creates corresponding classes
 * */
public interface JSONParser<T> {
    T parse(String JSONUrl);
}
