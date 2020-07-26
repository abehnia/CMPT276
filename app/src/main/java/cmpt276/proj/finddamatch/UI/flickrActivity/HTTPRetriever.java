package cmpt276.proj.finddamatch.UI.flickrActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPRetriever {
    public static byte[] getRequest(String request, int timeout)
            throws IOException, IllegalArgumentException {
        URL url = new URL(request);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(timeout);
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " + request);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public static <T, U extends ByteMapper<T>>
    T getRequest(String request, U mapper, int timeout) throws IOException,
            IllegalArgumentException {
        return mapper.map(getRequest(request, timeout));
    }
}
