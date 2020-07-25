package cmpt276.proj.finddamatch.model.flickrModel;

import java.util.Locale;

/**
 * Simple implementation of FlickrPhoto
 */
public class FlickrPhotoImpl implements FlickrPhoto {
    private static final String URL_FORMAT = "https" +
            "://farm%s.staticflicker.com/%s/%s_%s.jpg";
    private String ID;
    private String secret;
    private String serverID;
    private String farmID;

    public FlickrPhotoImpl(String ID, String secret,
                           String serverID, String farmID) {
        this.ID = ID;
        this.secret = secret;
        this.serverID = serverID;
        this.farmID = farmID;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public String getSecret() {
        return secret;
    }

    @Override
    public String getServerID() {
        return serverID;
    }

    @Override
    public String getUrl(FlickrPhotoSize size) {
        return String.format(Locale.getDefault(), URL_FORMAT, farmID,
                serverID, ID, secret);
    }

    @Override
    public String getFarmID() {
        return farmID;
    }
}
