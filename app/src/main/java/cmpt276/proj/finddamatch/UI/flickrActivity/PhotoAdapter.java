package cmpt276.proj.finddamatch.UI.flickrActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.model.flickrModel.FlickrPhoto;

/**
 * Adapter for the recycler view in photo gallery
 */
public class PhotoAdapter extends
        RecyclerView.Adapter<PhotoViewHolder> {
    private List<FlickrPhoto> flickrPhotoList;
    private Drawable background;
    private Context context;
    private PhotoDownloader<PhotoViewHolder> downloader;
    private List<RecyclerViewCell> recyclerViewCells;
    private Map<Integer, Bitmap> bitmapMap;

    public PhotoAdapter(List<FlickrPhoto> flickrPhotoList,
                        Drawable background, Context context,
                        PhotoDownloader<PhotoViewHolder> downloader,
                        Map<Integer, Bitmap> bitmapMap) {
        this.flickrPhotoList = flickrPhotoList;
        this.background = background;
        this.context = context;
        this.downloader = downloader;
        this.bitmapMap = bitmapMap;
        this.recyclerViewCells = new ArrayList<>(Arrays.asList(new
                RecyclerViewCell[flickrPhotoList.size()]));
        recyclerViewCells.replaceAll((RecyclerViewCell recyclerViewCell) -> new RecyclerViewCell());
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                              int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_gallery,
                parent, false);
        return new PhotoViewHolder(view, recyclerViewCells, bitmapMap);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder,
                                 int position) {
        FlickrPhoto flickrPhoto = flickrPhotoList.get(position);
        this.downloader.queueDownload(holder, flickrPhoto.getUrl());
        holder.bindDrawable(background);
    }

    @Override
    public int getItemCount() {
        return flickrPhotoList.size();
    }

    @Override
    public void onViewRecycled(@NonNull PhotoViewHolder holder) {
        holder.clearFilter();
        holder.clearReady();
    }

    public void clearSelections() {
        for (RecyclerViewCell recyclerViewCell : recyclerViewCells) {
            recyclerViewCell.setSelected(false);
        }
    }

}
