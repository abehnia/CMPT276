package cmpt276.proj.finddamatch.model.gameLogic;

import cmpt276.proj.finddamatch.model.GameMode;

public class ParameterTuner {
    private GameMode gameMode;
    private float lowerRadiusBound;
    private float upperRadiusBound;

    public ParameterTuner(GameMode gameMode) {
        this.gameMode = gameMode;
        lowerRadiusBound = 0f;
        upperRadiusBound = 0f;
    }

    public float getLowerRadiusBound() {
        return lowerRadiusBound;
    }

    public float getUpperRadiusBound() {
        return upperRadiusBound;
    }

    public void setLowerRadiusBound() {
        int order = gameMode.getOrder();
        switch (order) {
            case 2:
                lowerRadiusBound = 0.5f;
                break;
            case 3:
                lowerRadiusBound = 0.3f;
                break;
            case 5:
                lowerRadiusBound = 0.2f;
                break;
        }
    }

    public void setUpperRadiusBound() {
        int order = gameMode.getOrder();
        switch (order) {
            case 2:
                upperRadiusBound = 0.7f;
                break;
            case 3:
                upperRadiusBound = 0.5f;
                break;
            case 5:
                upperRadiusBound = 0.4f;
                break;
        }
    }
}
