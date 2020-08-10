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

/**
 * Implements the Custom ImageSet
 */

public class CustomImageImpl implements ImageSet {
    List<Drawable> drawables;
    Drawable backLogo;

    public CustomImageImpl(Collection<Bitmap> bitmaps, Resources resources) {
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
        int minSide = Math.min(bitmap.getWidth(), bitmap.getHeight());
        Bitmap output = Bitmap.createBitmap(minSide, minSide, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        final Rect dest = new Rect(0, 0, minSide, minSide);
        float delta = Math.max(bitmap.getWidth() - minSide, bitmap.getHeight() - minSide);
        Rect src = new Rect(0, 0, minSide, minSide);
        if (bitmap.getWidth() > bitmap.getHeight()) {
            src.offset((int)(delta / 2.0f), 0);
        } else {
            src.offset(0, (int)(delta / 2.0f));
        }
        canvas.drawCircle(minSide / 2, minSide / 2,
                minSide/2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dest, paint);
        return output;
    }
}
