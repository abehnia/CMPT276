package cmpt276.proj.finddamatch.UI.settingsActivity;

import android.content.res.TypedArray;
import android.widget.RadioGroup;

import java.util.HashMap;
import java.util.Map;

import cmpt276.proj.finddamatch.BuildConfig;

public class OptionsViewImpl<T> implements OptionView<T> {
    private Map<Integer, String> buttonToValue;
    private StringMapper<T> mapper;
    private RadioGroup radioGroup;
    private int currentButtonID;

    public <U extends StringMapper<T>>
    OptionsViewImpl(U mapper, RadioGroup radioGroup, int currentButtonID,
                    TypedArray buttonIDs, String[] values) {
        this.mapper = mapper;
        this.radioGroup = radioGroup;
        this.radioGroup.check(currentButtonID);
        this.currentButtonID = currentButtonID;
        this.buttonToValue = new HashMap<>();
        init(buttonIDs, values);
    }

    private void setupListener() {
        radioGroup.setOnCheckedChangeListener((group, checkedId) ->
                currentButtonID = checkedId);
    }

    @Override
    public int getCurrentButtonID() {
        return this.currentButtonID;
    }

    @Override
    public T getValue() {
        return mapper.map(buttonToValue.get(currentButtonID));
    }

    private void init(TypedArray buttonIDs, String[] values) {
        int[] keys = new int[buttonIDs.length()];
        for (int i = 0; i < keys.length; ++i) {
            keys[i] = buttonIDs.getResourceId(i, 0);
        }
        buttonIDs.recycle();
        if (BuildConfig.DEBUG && !(keys.length == values.length)) {
            throw new AssertionError("Length of keys and values" +
                    " not equal in settings.");
        }
        for (int i = 0; i < keys.length; ++i) {
            buttonToValue.put(keys[i], values[i]);
        }
        setupListener();
    }
}
