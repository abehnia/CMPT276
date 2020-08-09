package cmpt276.proj.finddamatch.model.gameLogic;

import java.util.Random;

import cmpt276.proj.finddamatch.model.MutableImage;

/**
 * Card Generator for the easy mode
 */
public class EasyCardGenerator extends AbstractCardGenerator {
    public EasyCardGenerator(ParameterTuner parameterTuner, boolean hasText) {
        super(parameterTuner, hasText);
    }

    @Override
    protected void randomizeOrientation(MutableImage image, Random random) {
        image.setOrientation(0);
    }

    @Override
    protected void randomizeRadius(MutableImage image, Random random) {
        float lower = parameterTuner.getLowerRadiusBound();
        float upper = parameterTuner.getUpperRadiusBound();
        float radius = 3 / 4f * lower + 1 / 4f * upper;
        image.setRadius(radius);
    }
}
