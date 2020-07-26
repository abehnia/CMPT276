package cmpt276.proj.finddamatch.UI.flickrActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;

public class PhotoDownloader<T> extends HandlerThread {
    private static final int MESSAGE_DOWNLOAD = 0;
    private boolean hasQuit;
    private Handler requestHandler;
    private Handler responseHandler;
    private ConcurrentMap<T, String> requestMap;
    private BiConsumer<T, Bitmap> listener;

    public PhotoDownloader(String name, Handler responseHandler) {
        super(name);
        this.hasQuit = false;
        this.requestMap = new ConcurrentHashMap<>();
        this.responseHandler = responseHandler;
    }

    public void setListener(BiConsumer<T, Bitmap> listener) {
        this.listener = listener;
    }

    public void clearQueue() {
        requestHandler.removeMessages(MESSAGE_DOWNLOAD);
        requestMap.clear();
    }

    @Override
    public boolean quit() {
        hasQuit = true;
        return super.quit();
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void onLooperPrepared() {
        requestHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MESSAGE_DOWNLOAD) {
                    T target = (T) msg.obj;
                    handleRequest(target);
                }
            }
        };
    }

    public void queueDownload(T target, String url) {
        if (url == null) {
            requestMap.remove(target, null);
        } else {
            requestMap.put(target, url);
            requestHandler.obtainMessage(MESSAGE_DOWNLOAD,
                    target).sendToTarget();
        }
    }

    private void handleRequest(final T target) {
        try {
            final String url = requestMap.get(target);
            if (url == null) {
                return;
            }
            Bitmap bitmap = HTTPRetriever.getRequest(url, (byte[] bytes) ->
                    BitmapFactory.decodeByteArray(bytes, 0, bytes.length),
                    1000);
            responseHandler.post(() -> {
                if (!(requestMap.get(target) == url) ||
                        hasQuit) {
                    return;
                }
                requestMap.remove(target);
                listener.accept(target, bitmap);
            });
        } catch (IOException e) {
            Log.e("Downloader", "Error Download image" + e);
        } catch (IllegalArgumentException e) {
            Log.e("Downloader", "Connection Timed out" + e);
        }
    }
}
