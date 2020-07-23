package cmpt276.proj.finddamatch.UI.gameActivity;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.UI.settingsActivity.Settings;
import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.ImageSet;

public class ImageSetImpl implements ImageSet{
    private Drawable[] imagesList;
    private Drawable[] imagesTextList;


    public ImageSetImpl(Resources resources){
//        final int NUM_OF_IMAGES = 31;
        int NUM_OF_IMAGES = Card.NUMBER_OF_IMAGES_PER_DECK;

        int IMAGE_SET_NUM = Settings.get().getImageSetValue();

        TypedArray logos = resources.obtainTypedArray(R.array.logos);
        String[] logos_string = resources.getStringArray(R.array.logos_string);

        this.imagesList = new Drawable[NUM_OF_IMAGES];
        this.imagesTextList = new Drawable[NUM_OF_IMAGES];

        for (int i = 0; i < NUM_OF_IMAGES; i++){
            imagesList[i] = logos.getDrawable(i + IMAGE_SET_NUM * NUM_OF_IMAGES);
            imagesTextList[i] = new DrawableText(logos_string[i + IMAGE_SET_NUM * NUM_OF_IMAGES]);
        }
        logos.recycle();
    }



    @Override
    public Drawable getImage(int id, boolean textDrawable){
        Drawable imageToDraw;

        if (textDrawable){
            imageToDraw = imagesTextList[id];
        }else{
            imageToDraw = imagesList[id];
        }
        return imageToDraw;
    }




}
