package cmpt276.proj.finddamatch.UI.settingsActivity;

/**
 * Option View for use inside the settings screen
 */
public interface OptionView<T> {
    int getCurrentButtonID();

    T getValue();
}
