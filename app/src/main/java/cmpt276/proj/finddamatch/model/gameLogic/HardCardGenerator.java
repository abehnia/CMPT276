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
        image.setRadius(random.nextFloat() * (parameterTuner.getUpperRadiusBound() -
                parameterTuner.getLowerRadiusBound()) + parameterTuner.getLowerRadiusBound());
    }
}
