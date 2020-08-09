package cmpt276.proj.finddamatch.UI.customImageSetActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
import cmpt276.proj.finddamatch.UI.flickrActivity.RecyclerViewCell;
import cmpt276.proj.finddamatch.UI.flickrActivity.PhotoViewHolder;

/**
 * Adapter for the recycler view in Image Set Activity
 */

public class CustomImageSetAdapter extends
        RecyclerView.Adapter<PhotoViewHolder> {
    private List<Bitmap> bitmaps;
    private Context context;
    private List<RecyclerViewCell> recyclerViewCells;
    private Map<Integer, Bitmap> bitmapMap;

    public CustomImageSetAdapter(List<Bitmap> bitmaps, Context context,
                                 Map<Integer, Bitmap> bitmapMap) {
        this.bitmaps = bitmaps;
        this.context = context;
        this.bitmapMap = bitmapMap;
        this.recyclerViewCells = new ArrayList<>(Arrays.asList(new
                RecyclerViewCell[bitmaps.size()]));
        recyclerViewCells.replaceAll((RecyclerViewCell recyclerViewCell) -> new RecyclerViewCell());
        for (int i = 0; i < recyclerViewCells.size(); ++i) {
            recyclerViewCells.get(i).setBitmap(bitmaps.get(i));
        }
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
        Bitmap bitmap = bitmaps.get(position);
        holder.bindDrawable(new BitmapDrawable(context.getResources(), bitmap));
        holder.setReady();
    }

    @Override
    public int getItemCount() {
        return bitmaps.size();
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
