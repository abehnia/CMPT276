package cmpt276.proj.finddamatch.UI.flickrActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.model.flickrModel.FlickrPhoto;
import cmpt276.proj.finddamatch.model.flickrModel.FlickrPhotoSize;

public class PhotoAdapter extends
        RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    private List<FlickrPhoto> flickrPhotoList;
    private Drawable background;
    private Context context;
    private PhotoDownloader<PhotoViewHolder> downloader;

    public PhotoAdapter(List<FlickrPhoto> flickrPhotoList,
                        Drawable background, Context context,
                        PhotoDownloader<PhotoViewHolder> downloader) {
        this.flickrPhotoList = flickrPhotoList;
        this.background = background;
        this.context = context;
        this.downloader = downloader;
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
        this.downloader.queueDownload(holder,
                flickrPhoto.getUrl(FlickrPhotoSize.SMALL));
        holder.bindDrawable(background);
    }

    @Override
    public int getItemCount() {
        return flickrPhotoList.size();
    }

    static class PhotoViewHolder extends RecyclerView.ViewHolder implements
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
        }
    }
}
