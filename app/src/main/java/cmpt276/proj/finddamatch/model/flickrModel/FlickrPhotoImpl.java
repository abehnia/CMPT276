package cmpt276.proj.finddamatch.model.flickrModel;


/**
 * Simple implementation of FlickrPhoto
 */
public class FlickrPhotoImpl implements FlickrPhoto {
    private String ID;
    private String secret;
    private String serverID;
    private String farmID;
    private String url;

    public FlickrPhotoImpl(String ID, String secret,
                           String serverID, String farmID, String url) {
        this.ID = ID;
        this.secret = secret;
        this.serverID = serverID;
        this.farmID = farmID;
        this.url = url;
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
    public String getFarmID() {
        return farmID;
    }

    @Override
    public String getUrl() {
        return url;
    }
}
