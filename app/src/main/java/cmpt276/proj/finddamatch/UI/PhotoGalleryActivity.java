package cmpt276.proj.finddamatch.UI;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import cmpt276.proj.finddamatch.UI.flickrActivity.PhotoGalleryFragment;
import cmpt276.proj.finddamatch.UI.flickrActivity.SingleFragmentActivity;


public class PhotoGalleryActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return PhotoGalleryFragment.newInstance();
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, PhotoGalleryActivity.class);
    }
}