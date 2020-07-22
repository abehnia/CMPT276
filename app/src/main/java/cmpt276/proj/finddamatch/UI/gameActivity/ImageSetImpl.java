package cmpt276.proj.finddamatch.UI.gameActivity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import java.util.HashMap;

import cmpt276.proj.finddamatch.BuildConfig;
import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.UI.settingsActivity.Settings;
import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.ImageSet;

public abstract class ImageSetImpl implements ImageSet {


    public Drawable getDrawable(int id, boolean textDrawable, Resources resources){
        Drawable imageToDraw;
        int numOfImages = Card.NUMBER_OF_IMAGES_PER_DECK;
//        final int numOfImages = 31;

        int imageSet = Settings.get().getImageSetValue();
        TypedArray logos = resources.obtainTypedArray(R.array.logos);
        String[] logos_string = resources.getStringArray(R.array.logos_string);

        imageToDraw = logos.getDrawable(id + imageSet * numOfImages);

//        if (textDrawable){
//            Drawable d = resources.getDrawable(R.drawable.background_shape);
//            imageToDraw = d;
//        }else{
//            imageToDraw = logos.getDrawable(id + imageSet * numOfImages);
//        }

        return imageToDraw;
    }




}
