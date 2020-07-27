package cmpt276.proj.finddamatch.UI.flickrImageSetActivity;

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
import cmpt276.proj.finddamatch.UI.flickrActivity.FlickrCell;
import cmpt276.proj.finddamatch.UI.flickrActivity.PhotoViewHolder;

public class FlickrImageSetAdapter extends
        RecyclerView.Adapter<PhotoViewHolder> {
    private List<Bitmap> bitmaps;
    private Context context;
    private List<FlickrCell> flickrCells;
    private Map<Integer, Bitmap> bitmapMap;

    public FlickrImageSetAdapter(List<Bitmap> bitmaps, Context context,
                        Map<Integer, Bitmap> bitmapMap) {
        this.bitmaps = bitmaps;
        this.context = context;
        this.bitmapMap = bitmapMap;
        this.flickrCells = new ArrayList<>(Arrays.asList(new
                FlickrCell[bitmaps.size()]));
        flickrCells.replaceAll((FlickrCell flickrCell) -> new FlickrCell());
        for (int i = 0; i < flickrCells.size(); ++i) {
            flickrCells.get(i).setBitmap(bitmaps.get(i));
        }
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                              int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_gallery,
                parent, false);
        return new PhotoViewHolder(view, flickrCells, bitmapMap);
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
        for (FlickrCell flickrCell : flickrCells) {
            flickrCell.setSelected(false);
        }
    }

}
