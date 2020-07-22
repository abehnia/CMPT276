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

public class ImageSetImpl implements ImageSet{


    @Override
    public Drawable getDrawables(int id, boolean textDrawable, Resources resources){
        Drawable imageToDraw;
        int numOfImages = Card.NUMBER_OF_IMAGES_PER_DECK;
//        final int numOfImages = 31;

        int imageSet = Settings.get().getImageSetValue();
        TypedArray logos = resources.obtainTypedArray(R.array.logos);
        String[] logos_string = resources.getStringArray(R.array.logos_string);


        if (textDrawable){
            imageToDraw = new DrawableText(logos_string[id + imageSet * numOfImages]);
        }else{
            imageToDraw = logos.getDrawable(id + imageSet * numOfImages);
        }

        logos.recycle();


        return imageToDraw;
    }




}
