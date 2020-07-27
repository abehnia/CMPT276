package cmpt276.proj.finddamatch.model;

public interface JSONParser<T> {
    T parse(String JSONUrl);
}
