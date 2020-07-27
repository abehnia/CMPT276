package cmpt276.proj.finddamatch.UI.gameActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cmpt276.proj.finddamatch.R;
import cmpt276.proj.finddamatch.model.ImageSet;

public class FlickrSetImpl implements ImageSet {
    List<Drawable> drawables;
    Drawable backLogo;

    public FlickrSetImpl(Collection<Bitmap> bitmaps, Resources resources) {
        this.drawables = new ArrayList<>();
        for (Bitmap bitmap: bitmaps) {
            this.backLogo = resources.getDrawable(R.drawable.
                    ic_national_basketball_association_logo, null);
            drawables.add(new BitmapDrawable(resources,
                    getCroppedBitmap(bitmap)));
        }
    }

    @Override
    public void setColor(int color) {
    }

    @Override
    public Drawable getImage(int id, boolean textImage) {
        return drawables.get(id);
    }

    @Override
    public Drawable getBackLogo() {
        return backLogo;
    }

    /**
     * Stack overflow helped quite a bit with this one
     */
    private Bitmap getCroppedBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        int minSide = Math.min(bitmap.getWidth(), bitmap.getHeight());
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                minSide/2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
}
