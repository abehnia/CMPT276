package cmpt276.proj.finddamatch.UI.flickrActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.model.flickrModel.FlickrPhoto;

public class PhotoAdapter extends
        RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    private List<FlickrPhoto> flickrPhotoList;
    private Drawable background;
    private Context context;
    private PhotoDownloader<PhotoViewHolder> downloader;
    private List<Boolean> selected;
    private Set<Bitmap> bitmapSet;

    public PhotoAdapter(List<FlickrPhoto> flickrPhotoList,
                        Drawable background, Context context,
                        PhotoDownloader<PhotoViewHolder> downloader,
                        Set<Bitmap> bitmapSet) {
        this.flickrPhotoList = flickrPhotoList;
        this.background = background;
        this.context = context;
        this.downloader = downloader;
        this.bitmapSet = bitmapSet;
        this.selected = new ArrayList<>(Arrays.asList(new
                Boolean[flickrPhotoList.size()]));
        selected.replaceAll((Boolean condition) -> false);
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                              int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_gallery,
                parent, false);
        return new PhotoViewHolder(view, this);
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
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {
        private android.widget.ImageView imageView;
        private final PhotoAdapter adapter;

        public PhotoViewHolder(View itemView, PhotoAdapter adapter) {
            super(itemView);
            this.imageView = (android.widget.ImageView)
                    itemView.findViewById(R.id.item_image_view);
            this.adapter = adapter;
            itemView.setOnClickListener(this);
        }

        public void bindDrawable(Drawable drawable) {
            imageView.setImageDrawable(drawable);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            selected.set(position, !selected.get(position));
            processFilter();
        }

        public void applyFilter() {
            imageView.setColorFilter(new LightingColorFilter(Color.GREEN,
                    Color.TRANSPARENT));
        }

        public void clearFilter() {
            this.imageView.setColorFilter(null);
        }

        public void processFilter() {
            int position = getLayoutPosition();
            if (position == -1) {
                return;
            }
            if (selected.get(position)) {
                applyFilter();
            } else {
                clearFilter();
            }
        }
    }
}
