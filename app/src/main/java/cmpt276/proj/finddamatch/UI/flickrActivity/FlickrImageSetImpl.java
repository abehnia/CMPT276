package cmpt276.proj.finddamatch.UI.flickrActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.model.ImageSet;

public class FlickrImageSetImpl implements ImageSet {
    private Drawable[] imagesList;
    private FlickrBitmapList bitmapList;
    private Drawable backLogo;


    public FlickrImageSetImpl(Resources resources) {
        this.bitmapList = FlickrBitmapList.getImageSet();
        final int NUM_OF_IMAGES = bitmapList.getImageSetList().size();
        this.imagesList = new Drawable[NUM_OF_IMAGES];
        for (int i = 0; i < NUM_OF_IMAGES; i++) {
            Bitmap bitmap = bitmapList.getImageSetList().get(i);
            Drawable drawable = new BitmapDrawable(resources, bitmap);
            imagesList[i] = drawable;
        }
        this.backLogo = resources.getDrawable(
                R.drawable.ic_national_basketball_association_logo,
                null);
    }

    @Override
    public void setColor(int color) {
        return;
    }

    @Override
    public Drawable getImage(int id, boolean textDrawable) {
        Drawable imageToDraw;
        imageToDraw = imagesList[id];
        return imageToDraw;
    }

    @Override
    public Drawable getBackLogo() {
        return this.backLogo;
    }
}

