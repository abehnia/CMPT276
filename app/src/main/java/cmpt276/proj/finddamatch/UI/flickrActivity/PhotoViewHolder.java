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
    private final List<RecyclerViewCell> recyclerViewCells;
    private final Map<Integer, Bitmap> bitmapMap;

    public PhotoViewHolder(View itemView, List<RecyclerViewCell> flickerCells,
                           Map<Integer, Bitmap> bitmapMap) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.item_image_view);
        this.overlay = itemView.findViewById(R.id.checkbox);
        this.bitmapMap = bitmapMap;
        this.recyclerViewCells = flickerCells;
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
        RecyclerViewCell recyclerViewCell = recyclerViewCells.get(getLayoutPosition());
        recyclerViewCell.setSelected(!recyclerViewCell.isSelected());
        processFilter();
        AppendToSet();
    }

    private void AppendToSet() {
        int position = getLayoutPosition();
        RecyclerViewCell recyclerViewCell = recyclerViewCells.get(position);
        boolean select = recyclerViewCell.isSelected();
        if (select) {
            bitmapMap.put(position, recyclerViewCell.getBitmap());
        } else {
            bitmapMap.remove(position);
        }
    }

    public void setReady() {
        int position = getLayoutPosition();
        if (!hasValidPosition()) {
            return;
        }
        RecyclerViewCell recyclerViewCell = recyclerViewCells.get(position);
        recyclerViewCell.setReady(true);
    }

    public void clearReady() {
        if (!hasValidPosition()) {
            return;
        }
        int position = getLayoutPosition();
        RecyclerViewCell recyclerViewCell = recyclerViewCells.get(position);
        recyclerViewCell.setReady(false);
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
        RecyclerViewCell recyclerViewCell = recyclerViewCells.get(position);
        if (recyclerViewCell.isSelected()) {
            applyFilter();
        } else {
            clearFilter();
        }
    }

    public void addBitmap(Bitmap bitmap) {
        if (!hasValidPosition()) {
            return;
        }
        RecyclerViewCell recyclerViewCell = recyclerViewCells.get(getLayoutPosition());
        recyclerViewCell.setBitmap(bitmap);
    }

    private boolean hasValidPosition() {
        return getLayoutPosition() != -1;
    }

    private boolean isReady() {
        return recyclerViewCells.get(getLayoutPosition()).isReady();
    }
}
