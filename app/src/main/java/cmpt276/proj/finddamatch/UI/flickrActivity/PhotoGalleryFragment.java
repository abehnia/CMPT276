package cmpt276.proj.finddamatch.UI.flickrActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.model.flickrModel.FlickerAPI;
import cmpt276.proj.finddamatch.model.flickrModel.FlickrJSONParser;
import cmpt276.proj.finddamatch.model.flickrModel.FlickrPhoto;

/**
 * Fragment for the photo gallery
 */
public class PhotoGalleryFragment extends Fragment {
    public static final int NUMBER_OF_COLUMNS = 3;
    public static final String FLICKER_PHOTO_DOWNLOADER = "Flicker Photo Downloader";
    private RecyclerView recyclerView;
    private HeaderFetcher headerFetcher;
    private PhotoDownloader<PhotoViewHolder> downloader;
    private List<FlickrPhoto> galleryItems = new ArrayList<>();
    private Map<Integer, Bitmap> bitmapMap;

    public static PhotoGalleryFragment newInstance() {
        return new PhotoGalleryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bitmapMap = new HashMap<>();
        setRetainInstance(true);
        setupHeaderFetcher();
        headerFetcher.execute(FlickerAPI.searchPhotos("racoon",
                100, 1));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photo_gallery,
                container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.photoRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),
                NUMBER_OF_COLUMNS));
        setupDownloader();
        return v;
    }

    void setupDownloader() {
        Handler responseHandler= new Handler();
        this.downloader = new PhotoDownloader<>(FLICKER_PHOTO_DOWNLOADER,
                responseHandler);
        downloader.setListener((PhotoViewHolder holder, Bitmap bitmap) -> {
            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
            holder.bindDrawable(drawable);
            holder.processFilter();
            holder.setReady();
            holder.addBitmap(bitmap);
        });
        downloader.start();
        downloader.getLooper();
    }

    void setupHeaderFetcher() {
        FlickrJSONParser flickrJSONParser = new FlickrJSONParser();
        this.headerFetcher = new HeaderFetcher(flickrJSONParser,
                (List<FlickrPhoto> items) -> {
                    galleryItems = items;
                    setupAdapter();
                });
    }

    private void setupAdapter() {
        if (isAdded()) {
            recyclerView.setAdapter(new PhotoAdapter(galleryItems,
                    getResources().getDrawable(R.drawable.
                            ic_national_basketball_association_logo, null),
                    getContext(), downloader, bitmapMap));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        downloader.quit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        downloader.clearQueue();
    }
}
