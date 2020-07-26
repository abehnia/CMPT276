package cmpt276.proj.finddamatch.UI.flickrActivity;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

import cmpt276.proj.finddamatch.BuildConfig;
import cmpt276.proj.finddamatch.model.flickrModel.FlickrJSONParser;
import cmpt276.proj.finddamatch.model.flickrModel.FlickrPhoto;

public class HeaderFetcher extends AsyncTask<String, Void, List<FlickrPhoto>> {
    private static final String TAG = "PhotoGalleryFragment";
    private FlickrJSONParser jsonParser;
    Consumer<List<FlickrPhoto>> callBack;


    public HeaderFetcher(FlickrJSONParser jsonParser,
                         Consumer<List<FlickrPhoto>> callBack) {
        this.jsonParser = jsonParser;
        this.callBack = callBack;
    }

    @Override
    protected List<FlickrPhoto> doInBackground(String... requests) {
        if (BuildConfig.DEBUG && requests.length != 1) {
            throw new AssertionError("request length > 1" +
                    " in FetchHeaders");
        }
        String request = requests[0];
        try {
            return jsonParser.parse(HTTPRetriever.getRequest(
                    request, String::new, 60000));
        } catch (IOException e) {
            Log.e(TAG, "Failed to fetch URL: ", e);
        } finally {
            Log.i(TAG, request);
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<FlickrPhoto> items) {
        callBack.accept(items);
    }
}
