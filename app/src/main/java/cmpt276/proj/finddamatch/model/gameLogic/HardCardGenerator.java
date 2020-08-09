package cmpt276.proj.finddamatch.model.gameLogic;

import java.util.Random;

import cmpt276.proj.finddamatch.model.MutableImage;

/**
 * Card Generator for the Hard mode
 */
public class HardCardGenerator extends AbstractCardGenerator {
    public HardCardGenerator(ParameterTuner parameterTuner, boolean hasText) {
        super(parameterTuner, hasText);
    }

    @Override
    protected void randomizeOrientation(MutableImage image, Random random) {
        image.setOrientation((float) (random.nextFloat() * 2 * Math.PI));
    }

    @Override
    protected void randomizeRadius(MutableImage image, Random random) {
        float lower = parameterTuner.getLowerRadiusBound();
        float upper = parameterTuner.getUpperRadiusBound();
        float randomLowerBound = 1 * lower / 2f +
                (lower * random.nextFloat() * 1 / 2f);
        image.setRadius(random.nextFloat() * (upper -
                randomLowerBound) + randomLowerBound);
    }
}
