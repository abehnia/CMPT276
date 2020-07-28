package cmpt276.proj.finddamatch.UI.flickrActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

import cmpt276.proj.finddamatch.R;

/**
 * Holds a view to a photo gallery element
 */
public class PhotoViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener {
    private android.widget.ImageView imageView;
    private android.widget.ImageView overlay;
    private final List<FlickrCell> flickrCells;
    private final Map<Integer, Bitmap> bitmapMap;

    public PhotoViewHolder(View itemView, List<FlickrCell> flickerCells,
                           Map<Integer, Bitmap> bitmapMap) {
        super(itemView);
        this.imageView = (android.widget.ImageView)
                itemView.findViewById(R.id.item_image_view);
        this.overlay = (android.widget.ImageView)
                itemView.findViewById(R.id.checkbox);
        this.bitmapMap = bitmapMap;
        this.flickrCells = flickerCells;
        itemView.setOnClickListener(this);
    }

    public void bindDrawable(Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }

    @Override
    public void onClick(View v) {
        if (!hasValidPosition() || !isReady()) {
            return;
        }
        FlickrCell flickrCell = flickrCells.get(getLayoutPosition());
        flickrCell.setSelected(!flickrCell.isSelected());
        processFilter();
        AppendToSet();
    }

    private void AppendToSet() {
        int position = getLayoutPosition();
        FlickrCell flickrCell = flickrCells.get(position);
        boolean select = flickrCell.isSelected();
        if (select) {
            bitmapMap.put(position, flickrCell.getBitmap());
        } else {
            bitmapMap.remove(position);
        }
    }

    public void setReady() {
        int position = getLayoutPosition();
        if (!hasValidPosition()) {
            return;
        }
        FlickrCell flickrCell = flickrCells.get(position);
        flickrCell.setReady(true);
    }

    public void clearReady() {
        if (!hasValidPosition()) {
            return;
        }
        int position = getLayoutPosition();
        FlickrCell flickrCell = flickrCells.get(position);
        flickrCell.setReady(false);
    }

    public void applyFilter() {
        overlay.setVisibility(View.VISIBLE);
    }

    public void clearFilter() {
        overlay.setVisibility(View.INVISIBLE);
    }

    public void processFilter() {
        if (!hasValidPosition()) {
            return;
        }
        int position = getLayoutPosition();
        FlickrCell flickrCell = flickrCells.get(position);
        if (flickrCell.isSelected()) {
            applyFilter();
        } else {
            clearFilter();
        }
    }

    public void addBitmap(Bitmap bitmap) {
        if (!hasValidPosition()) {
            return;
        }
        FlickrCell flickrCell = flickrCells.get(getLayoutPosition());
        flickrCell.setBitmap(bitmap);
    }

    private boolean hasValidPosition() {
        return getLayoutPosition() != -1;
    }

    private boolean isReady() {
        return flickrCells.get(getLayoutPosition()).isReady();
    }
}
