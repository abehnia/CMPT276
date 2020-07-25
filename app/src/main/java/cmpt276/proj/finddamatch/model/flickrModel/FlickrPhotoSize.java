package cmpt276.proj.finddamatch.model.flickrModel;

/**
 * Different size of photos for Flickr
 */
public enum FlickrPhotoSize {
    SMALL_SQUARE("s"),
    SMALL("m"),
    THUMBNAIL("t"),
    MEDIUM("z"),
    LARGE("b");

    private String size;

    private FlickrPhotoSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return this.size;
    }
}
