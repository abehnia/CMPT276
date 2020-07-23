package cmpt276.proj.finddamatch.UI.settingsActivity;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SettingsSaver {
    private static final String SETTINGS_STATE_FILE_NAME = "settings-state";

    public static void load(Context context) {
        try {
            FileInputStream settingsFileInputStream =
                    context.openFileInput(SETTINGS_STATE_FILE_NAME);
            ObjectInput settingsReader = new
                    ObjectInputStream(settingsFileInputStream);
            Settings settings = (Settings) settingsReader.readObject();
            Settings.set(settings);
            settingsReader.close();
            settingsFileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            Settings.get();
        }
    }

    public static void save(Context context) {
        try {
            FileOutputStream SettingsFileOutputStream = context.
                    openFileOutput(SETTINGS_STATE_FILE_NAME, Context.MODE_PRIVATE);
            ObjectOutputStream settingsStateWriter = new
                    ObjectOutputStream(SettingsFileOutputStream);
            settingsStateWriter.writeObject(Settings.get());
            settingsStateWriter.close();
            SettingsFileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
