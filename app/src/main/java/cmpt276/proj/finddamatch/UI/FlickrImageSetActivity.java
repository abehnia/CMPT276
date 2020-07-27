package cmpt276.proj.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;

import cmpt276.proj.finddamatch.R;

public class FlickrImageSetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickr_image_set);

        setupToolbar();
    }

    private void setupToolbar() {
        ImageButton backBtn = findViewById(R.id.btnBack);
        backBtn.setOnClickListener(v -> finish());

        ImageButton addImagesBtn = findViewById(R.id.btnAddImages);
        addImagesBtn.setOnClickListener(v -> {
            Intent intent = PhotoGalleryActivity.makeIntent(FlickrImageSetActivity.this);
            startActivity(intent);
        });

        ImageButton removeBtn = findViewById(R.id.btnRemove);
        removeBtn.setOnClickListener(v -> Log.i("App", "Remove Images Button Pressed"));

        ImageButton clearBtn = findViewById(R.id.btnClear);
        clearBtn.setOnClickListener(v -> Log.i("App", "Clear All Images Button Pressed"));
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, FlickrImageSetActivity.class);
    }
}