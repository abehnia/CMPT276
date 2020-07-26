package cmpt276.proj.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.UI.flickrActivity.PhotoGalleryActivity;

public class ImageSetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_set);

        setupAddImagesBtn();

    }

    private void setupAddImagesBtn() {
        Button bestScoresBtn = findViewById(R.id.btnAddImages);
        bestScoresBtn.setOnClickListener(v -> {
            Intent settings_intent = PhotoGalleryActivity.makeIntent(ImageSetActivity.this);
            startActivity(settings_intent);
        });
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, ImageSetActivity.class);
    }

}