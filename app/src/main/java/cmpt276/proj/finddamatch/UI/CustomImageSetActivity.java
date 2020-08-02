package cmpt276.proj.finddamatch.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.UI.flickrActivity.BitmapStorer;
import cmpt276.proj.finddamatch.UI.customImageSetActivity.CustomImageSetAdapter;

/**
 * Activity that Displays currently chosen image set
 */

public class CustomImageSetActivity extends AppCompatActivity {
    private static final int NUMBER_OF_COLUMNS = 3;
    private CustomImageSetAdapter photoAdapter;
    private List<Bitmap> galleryItems;
    private Map<Integer, Bitmap> bitmapMap;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_image_set);
        init();
        setupButtons();
        setupAdapter();
        updateImageCount();
    }

    private void updateImageCount() {
        TextView count = findViewById(R.id.imageCount);
        String currentSize = String.valueOf(BitmapStorer.get().getBitmaps().size());
        count.setText(currentSize);
    }

    private void init() {
        this.galleryItems = BitmapStorer.get().getBitmaps();
        this.bitmapMap = new HashMap<>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateAdapter();
        updateImageCount();
    }

    private void setupButtons() {
        ImageButton backBtn = findViewById(R.id.btnBack);
        backBtn.setOnClickListener(v -> finish());

        Button addCustomImagesBtn = findViewById(R.id.btnAddCustomImages);
        addCustomImagesBtn.setOnClickListener(v -> {
        });

        Button addFlickrImagesBtn = findViewById(R.id.btnAddFlickrImages);
        addFlickrImagesBtn.setOnClickListener(v -> {
            Intent intent = PhotoGalleryActivity.makeIntent(CustomImageSetActivity.this);
            startActivity(intent);
        });

        ImageButton removeBtn = findViewById(R.id.btnRemove);
        removeBtn.setOnClickListener(v -> {
            BitmapStorer.get().remove(bitmapMap.values());
            bitmapMap.clear();
            updateAdapter();
            updateImageCount();
        });

        ImageButton clearBtn = findViewById(R.id.btnClear);
        clearBtn.setOnClickListener(v -> {
            BitmapStorer.get().clear();
            bitmapMap.clear();
            updateAdapter();
            updateImageCount();
        });
    }

    private void setupAdapter() {
            recyclerView = findViewById(R.id.imageSetRecyclerView);
            photoAdapter = new CustomImageSetAdapter(galleryItems,
                    this, bitmapMap);
            recyclerView.setAdapter(photoAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(this,
                NUMBER_OF_COLUMNS));
    }

    private void updateAdapter() {
        this.galleryItems = BitmapStorer.get().getBitmaps();
        photoAdapter = new CustomImageSetAdapter(galleryItems,
                this, bitmapMap);
        recyclerView.setAdapter(photoAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BitmapStorer bitmapStorer = BitmapStorer.get();
        bitmapStorer.save(this);
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, CustomImageSetActivity.class);
    }
}