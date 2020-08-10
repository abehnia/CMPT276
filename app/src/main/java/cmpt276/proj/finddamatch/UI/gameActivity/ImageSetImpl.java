package cmpt276.proj.finddamatch.UI.gameActivity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.UI.settingsActivity.Settings;
import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.ImageSet;

/**
 * Basic implementation for Image Set
 * Not be used for Flickr
 * Refer to Image Set interface for more info
 */

public class ImageSetImpl implements ImageSet {
    private Drawable[] imagesList;
    private DrawableText[] imagesTextList;
    private Drawable backLogo;
    private static final int NUM_OF_IMAGES = Card.MAX_NUMBER_OF_IMAGES;


    public ImageSetImpl(Resources resources) {
        int imageSetNum = Settings.get().getImageSet().getID();
        TypedArray logos = resources.obtainTypedArray(R.array.logos);
        String[] logosString = resources.getStringArray(R.array.logos_string);
        this.imagesList = new Drawable[NUM_OF_IMAGES];
        this.imagesTextList = new DrawableText[NUM_OF_IMAGES];
        for (int i = 0; i < NUM_OF_IMAGES; i++) {
            imagesList[i] = logos.getDrawable(i + imageSetNum * NUM_OF_IMAGES);
            imagesTextList[i] = new DrawableText(logosString[i + imageSetNum * NUM_OF_IMAGES]);
        }
        logos.recycle();
        this.backLogo = resources.getDrawable(
                R.drawable.ic_national_basketball_association_logo,
                null);
    }

    @Override
    public void setColor(int color) {
        for (DrawableText drawableText : imagesTextList) {
            drawableText.setTextColor(color);
        }
    }

    @Override
    public Drawable getImage(int id, boolean textDrawable) {
        Drawable imageToDraw;

        if (textDrawable) {
            imageToDraw = imagesTextList[id];
        } else {
            imageToDraw = imagesList[id];
        }
        return imageToDraw;
    }

    @Override
    public Drawable getBackLogo() {
        return this.backLogo;
    }
}
