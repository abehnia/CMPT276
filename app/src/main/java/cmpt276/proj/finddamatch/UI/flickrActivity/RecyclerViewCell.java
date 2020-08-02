package cmpt276.proj.finddamatch.UI.flickrActivity;

import android.graphics.Bitmap;

import cmpt276.proj.finddamatch.model.flickrModel.FlickrPhoto;

/**
 * Class for every Image shown on Recycler View
 */

public class RecyclerViewCell {
    private boolean ready;
    private boolean selected;
    private Bitmap bitmap;

    public RecyclerViewCell() {
        this.ready = false;
        this.selected = false;
        this.bitmap = null;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
