package cmpt276.proj.finddamatch.model.flickrModel;

import static cmpt276.proj.finddamatch.model.flickrModel.FlickerAPI.FlickerAPIArg.*;
import static cmpt276.proj.finddamatch.model.flickrModel.FlickerAPI.FlickerAPIMethod.*;

/**
 * Returns the http addresses used to access the Flickr API
 */
public class FlickerAPI {
    private final static String GLOBAL_API_KEY = "07b7536411f39141" +
            "e5b970e798cdd798";
    private final static String API_ADDRESS = "https" +
            "://api.flickr.com/services/rest";
    private static final String DEFAULT_FORMAT = "json";
    private static final String DEFAULT_CALLBACK = "1";
    private static final String QUERY = "?";
    private static final String METHOD = "method";
    private static final String AND = "&";
    private static final String IS = "=";

    public static String getRecentPhotos(int photosPerPage, int page) {
        return API_ADDRESS +
                APIChainer(GET_RECENT, Pair.create(API_KEY, GLOBAL_API_KEY),
                        Pair.create(FORMAT, DEFAULT_FORMAT),
                        Pair.create(PER_PAGE, Integer.toString(photosPerPage)),
                        Pair.create(PAGE, Integer.toString(page)),
                        Pair.create(CALLBACK, DEFAULT_CALLBACK),
                        Pair.create(EXTRAS, URL_S.toString()));
    }

    public static String searchPhotos(String text, int photosPerPage,
                                      int page) {
        return API_ADDRESS +
                APIChainer(SEARCH, Pair.create(API_KEY, GLOBAL_API_KEY),
                        Pair.create(FORMAT, DEFAULT_FORMAT),
                        Pair.create(TEXT, text),
                        Pair.create(PER_PAGE, Integer.toString(photosPerPage)),
                        Pair.create(PAGE, Integer.toString(page)),
                        Pair.create(CALLBACK, DEFAULT_CALLBACK),
                        Pair.create(EXTRAS, URL_S.toString()));
    }

    @SafeVarargs
    private static String APIChainer(FlickerAPIMethod method,
                                     Pair<FlickerAPIArg, String>... args) {
        StringBuilder result = new StringBuilder(QUERY);
        result.append(METHOD).append(IS).append(method);
        for (Pair<FlickerAPIArg, String> arg: args) {
            result.append(AND).append(arg.first).append(IS).append(arg.second);
        }
        return result.toString();
    }

    enum FlickerAPIMethod {
        GET_RECENT("flickr.photos.getRecent"),
        SEARCH("flickr.photos.search");

        private String string;

        private FlickerAPIMethod(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return string;
        }
    }

    enum FlickerAPIArg {
        PER_PAGE("per_page"),
        PAGE("page"),
        FORMAT("format"),
        CALLBACK("nojsoncallback"),
        API_KEY("api_key"),
        TEXT("text"),
        URL_S("url_s"),
        EXTRAS("extras");

        private String string;

        private FlickerAPIArg(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return string;
        }
    }

    private static class Pair<T, U> {
        public T first;
        public U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        public static <T, U> Pair<T, U> create(T first, U second) {
            return new Pair<>(first, second);
        }
    }
}
