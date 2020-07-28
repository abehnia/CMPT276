package cmpt276.proj.finddamatch.UI.flickrActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
    private static final int NUMBER_OF_COLUMNS = 3;
    private static final String FLICKER_PHOTO_DOWNLOADER =
            "Flicker Photo Downloader";
    private static final String RACOON_SEARCH = "racoon";
    private static final int PHOTOS_PER_PAGE = 100;
    private static final int PAGE_NUMBER = 1;
    private RecyclerView recyclerView;
    private HeaderFetcher headerFetcher;
    private PhotoDownloader<PhotoViewHolder> downloader;
    private PhotoAdapter photoAdapter;
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
        headerFetcher.execute(FlickerAPI.searchPhotos(RACOON_SEARCH,
                PHOTOS_PER_PAGE, PAGE_NUMBER));
    }

    private void setupToolbar(View view) {
        View toolbar = view.findViewById(R.id.searchToolbar);
        ImageButton backBtn = toolbar.findViewById(R.id.btnBack);
        backBtn.setOnClickListener(v -> getActivity().finish());

        FloatingActionButton addImage = view.findViewById(R.id.btnAddImages);
        addImage.setOnClickListener(v -> {
            BitmapStorer.get().add(bitmapMap.values());
            bitmapMap.clear();
            photoAdapter.clearSelections();
            photoAdapter.notifyDataSetChanged();
        });

        ImageButton clearSearchBtn = toolbar.findViewById(R.id.btnClearSearch);
        clearSearchBtn.setOnClickListener(v -> {
            setupHeaderFetcher();
            headerFetcher.execute(FlickerAPI.searchPhotos(RACOON_SEARCH,
                    PHOTOS_PER_PAGE, PAGE_NUMBER));
        });

        final SearchView searchView = toolbar.findViewById(R.id.menu_item_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                setupHeaderFetcher();
                headerFetcher.execute(FlickerAPI.searchPhotos(s,
                        PHOTOS_PER_PAGE, PAGE_NUMBER));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return true;
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photo_gallery,
                container, false);
        recyclerView = v.findViewById(R.id.photoRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),
                NUMBER_OF_COLUMNS));
        setupDownloader();
        setupToolbar(v);
        return v;
    }

    void setupDownloader() {
        Handler responseHandler = new Handler();
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
            photoAdapter = new PhotoAdapter(galleryItems,
                    getResources().getDrawable(R.drawable.
                            ic_national_basketball_association_logo,
                            null), getContext(), downloader, bitmapMap);
            recyclerView.setAdapter(photoAdapter);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        downloader.clearQueue();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        downloader.quit();
    }

}
