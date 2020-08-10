package cmpt276.proj.finddamatch;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


import java.util.ArrayList;
import java.util.Stack;

import cmpt276.proj.finddamatch.UI.flickrActivity.BitmapStorer;
import cmpt276.proj.finddamatch.UI.gameActivity.CardView;
import cmpt276.proj.finddamatch.UI.gameActivity.FlickrSetImpl;
import cmpt276.proj.finddamatch.UI.gameActivity.GuessCardView;
import cmpt276.proj.finddamatch.UI.gameActivity.ImageSetImpl;
import cmpt276.proj.finddamatch.UI.settingsActivity.Settings;
import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.DeckGenerator;
import cmpt276.proj.finddamatch.model.ImageSet;

import static cmpt276.proj.finddamatch.UI.VALID_IMAGE_SET.Custom;

/**
 * Class to facilitate the export of deck of cards, created by
 * DeckGenerator class and exports to android filesystem.
 */

public class ExportCanvas {
    private DeckGenerator deckGenerator;
    private CardView cardView;
    private Resources resources;
    private ImageSet imageSet;
    private Canvas canvas;
    private static final float RADIUS = 400;
    private static final float DIAMETER = 2 * RADIUS * 1.05f;


    public ExportCanvas(Resources resources, DeckGenerator deckGenerator, ImageSet imageSet) {
        this.resources = resources;
        this.deckGenerator = deckGenerator;
        this.canvas = new Canvas();
        this.imageSet = imageSet;
        init();
    }

    private void init() {
        int backgroundColor = resources.getColor(
                R.color.colorGameBackground, null);
        Paint backgroundPaint = new Paint(backgroundColor);

        cardView = new GuessCardView(DIAMETER / 2f, DIAMETER / 2f, RADIUS,
                backgroundPaint, resources, imageSet);
    }

    public ArrayList<Bitmap> export() {
        ArrayList<Bitmap> bitmaps = new ArrayList<>();
        Stack<Card> cards = deckGenerator.generate();
        final int color = 0x00FFFFFF;
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        for (Card card : cards) {
            Bitmap bitmap = Bitmap.createBitmap((int) DIAMETER, (int) DIAMETER,
                    Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            canvas.drawARGB(0, 0, 0, 0);
            cardView.setImages(card);
            cardView.draw(canvas);
            Rect rect = new Rect(0, 0, (int) DIAMETER, (int) DIAMETER);
            canvas.drawBitmap(bitmap, rect, rect, paint);
            bitmaps.add(bitmap);
        }
        return bitmaps;
    }
}
