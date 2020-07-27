package cmpt276.proj.finddamatch.UI.flickrActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FlickrBitmapList implements Iterable<Bitmap>{
    private static FlickrBitmapList imageSet;
    private LinkedList<Bitmap> bitmapsList;

    public FlickrBitmapList(){
        this.bitmapsList = new LinkedList<>();
    }

    public static FlickrBitmapList getImageSet(){
        if (imageSet == null){
            imageSet = new FlickrBitmapList();
        }
        return imageSet;
    }

    public void add(Collection<Bitmap> b){
        bitmapsList.addAll(b);
    }

    public LinkedList<Bitmap> getImageSetList(){
        return bitmapsList;
    }

    public void remove(Collection<Bitmap> b){
        bitmapsList.removeAll(b);

    }

    @NonNull
    @Override
    public Iterator<Bitmap> iterator() {
        return bitmapsList.iterator();
    }
}
