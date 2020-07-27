package cmpt276.proj.finddamatch.UI.flickrActivity;

/**
 * Interface for our lambda to map bytes to objects
 */
public interface ByteMapper <T>{
    T map(byte[] bytes);
}
