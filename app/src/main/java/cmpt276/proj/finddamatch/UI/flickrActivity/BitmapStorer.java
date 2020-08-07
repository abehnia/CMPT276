package cmpt276.proj.finddamatch.UI.flickrActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.lang.UCharacter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import cmpt276.proj.finddamatch.UI.scoresActivity.DefaultScoresGenerator;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreManager;
import cmpt276.proj.finddamatch.UI.scoresActivity.ScoreTable;

/**
 * Class to store Bitmaps added to Image Set by User
 * Also has methods to save and load Image set when user starts or closes the app.
 */

public class BitmapStorer extends HandlerThread {
    private static final String TAG = "BitmapStorer";
    private static final int MESSAGE_LOAD = 0;
    private static final int MESSAGE_SAVE = 1;
    private static final String FILE_NAME = "bitmap-storage-";
    private static final String BITMAP_SIZE = "bitmap-size";
    public static final String BITMAP_SIZE_KEY = "BITMAP-SIZE";

    private List<Bitmap> bitmaps;
    private static BitmapStorer bitmapStorer;
    private Handler requestHandler;
    private boolean isReady;

    public static BitmapStorer get() {
        if (bitmapStorer == null) {
            bitmapStorer = new BitmapStorer();
            bitmapStorer.start();
        } else if (!bitmapStorer.isAlive()) {
            BitmapStorer previousBitmap = bitmapStorer;
            bitmapStorer = new BitmapStorer();
            previousBitmap.quitSafely();
            bitmapStorer.start();
        }
        return bitmapStorer;
    }

    public BitmapStorer() {
        super(TAG);
        this.bitmaps = new ArrayList<>();
        this.isReady = false;
    }

    public BitmapStorer(List<Bitmap> bitmaps) {
        super(TAG);
        this.bitmaps = bitmaps;
        this.isReady = false;
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void onLooperPrepared() {
        requestHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MESSAGE_LOAD) {
                    Context target = (Context) msg.obj;
                    internalLoad(target);
                } else if (msg.what == MESSAGE_SAVE) {
                    Context target = (Context) msg.obj;
                    internalSave(target);
                }
            }
        };
        this.isReady = true;
    }

    public void load(Context context) {
        this.isReady = false;
        requestHandler.obtainMessage(MESSAGE_LOAD, context).sendToTarget();
    }

    public void save(Context context) {
        this.isReady = true;
        requestHandler.obtainMessage(MESSAGE_SAVE, context).sendToTarget();
    }

    public boolean isReady() {
        return isReady;
    }

    private void internalLoad(Context context) {
        try {
            SharedPreferences sharedPreferences =
                    context.getSharedPreferences(BITMAP_SIZE, Context.MODE_PRIVATE);
            int size = sharedPreferences.getInt(BITMAP_SIZE_KEY, 0);
            for (int i = 0; i < size; ++i) {
                String fileName = FILE_NAME + i;
                FileInputStream bitmapStateFileInputStream =
                        context.openFileInput(fileName);
                Bitmap bitmap = BitmapFactory.decodeStream(
                        bitmapStateFileInputStream);
                bitmaps.add(bitmap);
                bitmapStateFileInputStream.close();
            }
            this.isReady = true;
        } catch (IOException e) {
            Log.e(TAG, "Error in Internal Load");
        }
    }

    private void internalSave(Context context) {
        try {
            SharedPreferences sharedPreferences =
                    context.getSharedPreferences(BITMAP_SIZE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(BITMAP_SIZE_KEY, bitmaps.size());
            editor.apply();
            for (int i = 0; i < bitmaps.size(); ++i) {
                String fileName = FILE_NAME + i;
                FileOutputStream bitmapStateFileOutputStream =
                        context.openFileOutput(fileName, Context.MODE_PRIVATE);
                Bitmap bitmap = bitmaps.get(i);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100,
                        bitmapStateFileOutputStream);
                bitmapStateFileOutputStream.close();
            }
        } catch (IOException e) {
            Log.e(TAG, "Error in Internal Save");
        } finally {
            this.isReady = true;
        }
    }

    public void add(Collection<Bitmap> bitmapCollection) {
        bitmaps.addAll(bitmapCollection);
    }

    public void add(Bitmap bitmap) {
        bitmaps.add(bitmap);
    }

    public void remove(Collection<Bitmap> bitmapCollection) {
        bitmaps.removeAll(bitmapCollection);
    }

    public void clear() {
        bitmaps.clear();
    }

    public List<Bitmap> getBitmaps() {
        return bitmaps;
    }

    public void clearQueue() {
        requestHandler.removeMessages(MESSAGE_LOAD);
        requestHandler.removeMessages(MESSAGE_SAVE);
    }
}
