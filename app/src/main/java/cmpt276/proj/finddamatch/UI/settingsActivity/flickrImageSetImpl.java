package cmpt276.proj.finddamatch.UI.settingsActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.UI.flickrActivity.FlickrBitmapList;
import cmpt276.proj.finddamatch.model.ImageSet;

public class flickrImageSetImpl implements ImageSet {
    private Drawable[] imagesList;
    private Drawable backLogo;
    private FlickrBitmapList rawImagesList;
    private static int NUM_OF_IMAGES;


    public flickrImageSetImpl(Resources resources) {
        this.rawImagesList = FlickrBitmapList.getImageSet();
        NUM_OF_IMAGES = rawImagesList.getImageSetList().size();
        this.imagesList = new Drawable[NUM_OF_IMAGES];
        for (int i = 0; i < NUM_OF_IMAGES; i++) {
            imagesList[i] = (Drawable) rawImagesList.getImageSetList().get(i);
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

