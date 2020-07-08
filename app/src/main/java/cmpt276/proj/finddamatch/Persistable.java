package cmpt276.proj.finddamatch;

import android.content.Context;

public interface Persistable {
    void load(Context context);
    void save(Context context);
}
