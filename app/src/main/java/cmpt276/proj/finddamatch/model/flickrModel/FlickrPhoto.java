package cmpt276.proj.finddamatch.model.flickrModel;

/**
 * Interface for a flickr photo
 * Returns its most important parameters such as ID, Farm, etc...
 */
public interface FlickrPhoto {

    /**
     * @return The farm of the photo on Flickr
     */
    String getFarmID();

    /**
     * @return The ID of the photo on Flickr
     */
    String getID();

    /**
     * @return The secret of the photo on Flickr
     */
    String getSecret();

    /**
     * @return The server of the photo on Flickr
     */
    String getServerID();

    /**
     * @return Return the url used to download the photo
     */
    String getUrl(FlickrPhotoSize size);
}
