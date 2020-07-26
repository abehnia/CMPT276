package cmpt276.proj.finddamatch.model.flickrModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cmpt276.proj.finddamatch.BuildConfig;
import cmpt276.proj.finddamatch.model.JSONParser;

/**
 * Parses a JSON returned by Flickr and extracts a list of Flickr Photos
 */
public class FlickrJSONParser implements JSONParser<List<FlickrPhoto>> {

    public static final String ERROR_MESSAGE = "Error in parsing Flickr JSON";

    @Override
    public List<FlickrPhoto> parse(String JSONUrl) {
        return parseArray(JSONUrl);
    }

    private List<FlickrPhoto> parseArray(String JSONUrl) {
        List<FlickrPhoto> photos = new ArrayList<>();
        try {
            JSONObject jsonObject = (new JSONObject(JSONUrl)).
                    getJSONObject("photos");
            JSONArray jsonArray = jsonObject.getJSONArray("photo");
            for (int i = 0; i < jsonArray.length(); ++i) {
                FlickrPhoto photo = parsePhoto(jsonArray.getJSONObject(i));
                photos.add(photo);
            }
            return photos;
        } catch (JSONException e) {
            e.printStackTrace();
            if (BuildConfig.DEBUG) {
                throw new AssertionError(ERROR_MESSAGE);
            }
        }
        return photos;
    }

    private FlickrPhoto parsePhoto(JSONObject jsonObject) throws JSONException {
        String farmID = jsonObject.getString("farm");
        String ID = jsonObject.getString("id");
        String serverID = jsonObject.getString("server");
        String secret = jsonObject.getString("secret");
        return new FlickrPhotoImpl(ID, secret, serverID, farmID);
    }
}
