package cmpt276.proj.finddamatch.UI.flickrActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.UI.ImageSetActivity;

public class PhotoGalleryActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return PhotoGalleryFragment.newInstance();
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, PhotoGalleryActivity.class);
    }
}