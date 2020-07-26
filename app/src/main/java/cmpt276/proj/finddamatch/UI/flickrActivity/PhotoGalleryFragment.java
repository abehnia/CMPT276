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
import java.util.List;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.model.flickrModel.FlickerAPI;
import cmpt276.proj.finddamatch.model.flickrModel.FlickrJSONParser;
import cmpt276.proj.finddamatch.model.flickrModel.FlickrPhoto;

public class PhotoGalleryFragment extends Fragment {
    public static final int NUMBER_OF_COLUMNS = 3;
    public static final String FLICKER_PHOTO_DOWNLOADER = "Flicker Photo Downloader";
    private RecyclerView recyclerView;
    private HeaderFetcher headerFetcher;
    private PhotoDownloader<PhotoAdapter.PhotoViewHolder> downloader;
    private List<FlickrPhoto> galleryItems = new ArrayList<>();

    public static PhotoGalleryFragment newInstance() {
        return new PhotoGalleryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setupHeaderFetcher();
        headerFetcher.execute(FlickerAPI.getRecentPhotos(200, 1));
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
        downloader.setListener((PhotoAdapter.PhotoViewHolder holder, Bitmap bitmap) -> {
            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
            holder.bindDrawable(drawable);
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
                    getContext(), downloader));
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
