package cmpt276.proj.finddamatch.UI;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.UI.customImageSetActivity.CustomImageSetAdapter;
import cmpt276.proj.finddamatch.UI.flickrActivity.BitmapStorer;

/**
 * Activity that Displays currently chosen image set
 */

public class CustomImageSetActivity extends AppCompatActivity {
    private static final int WANTED_SIZE = 240;
    private static final int REQUEST_CODE = 100;
    private static final int ACTIVITY_REQUEST_CODE = 1;
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
        addCustomImagesBtn.setOnClickListener(view -> {
            if (ActivityCompat.checkSelfPermission(CustomImageSetActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(CustomImageSetActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
                if (ActivityCompat.checkSelfPermission(CustomImageSetActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                    return;
            }
            newIntent();
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

    private void newIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setType("image/*");
        startActivityForResult(intent, ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            checkClipData(data);
            updateAdapter();
            updateImageCount();
        }
    }

    private void checkClipData(@Nullable Intent data) {
        assert data != null;
        ClipData clipData = data.getClipData();
        if (clipData != null) {
            for (int i = 0; i < clipData.getItemCount(); ++i) {
                Uri imageUri = clipData.getItemAt(i).getUri();
                try {
                    addBitmap(imageUri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Uri imageUri = data.getData();
            try {
                assert imageUri != null;
                addBitmap(imageUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void addBitmap(Uri imageUri) throws FileNotFoundException {
        InputStream inputStream = getContentResolver().openInputStream(imageUri);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        double tempHeight = bitmap.getHeight();
        double tempWidth = bitmap.getWidth();
        boolean isHeightTheLongestSide = tempHeight > tempWidth;
        double longestSide = Math.max(tempHeight, tempWidth);
        double scalingFactor = WANTED_SIZE / longestSide;
        if (isHeightTheLongestSide) {
            tempWidth *= scalingFactor;
            tempHeight = WANTED_SIZE;
        } else {
            tempHeight *= scalingFactor;
            tempWidth = WANTED_SIZE;
        }
        int height = (int) tempHeight;
        int width = (int) tempWidth;
        bitmap = CustomImageSetActivity.scaleBitmap(bitmap, width, height);
        BitmapStorer.get().add(bitmap);
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

    // Inspired by StackOverflow
    private static Bitmap scaleBitmap(Bitmap bitmap, int width, int height) {
        Bitmap outputBitMap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(outputBitMap);
        Matrix matrix = new Matrix();
        matrix.setScale((float) width / bitmap.getWidth(),
                (float) WANTED_SIZE / bitmap.getHeight());
        canvas.drawBitmap(bitmap, matrix, new Paint());
        return outputBitMap;
    }
}