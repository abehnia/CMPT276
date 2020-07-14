package cmpt276.proj.finddamatch.UI.welcomeActivity;


/**
 * The main engine for the animation
 */
public class AnimationEngine {
    private float lowerLimit;
    private float upperLimit;
    private GravityForce force;
    private PositionState state;
    private float impactCoefficient;
    private boolean animationDone;
    private final float THRESHOLD = 1000f / 16f;

    AnimationEngine(GravityForce force, float lowerLimit,
                    float upperLimit, PositionState state,
                    float impactCoefficient) {
        this.force = force;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.state = state;
        this.impactCoefficient = impactCoefficient;
        this.animationDone = false;
    }

    AnimationEngine(GravityForce force, PositionState state,
                    float impactCoefficient) {
        this(force, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY,
                state, impactCoefficient);
    }

    PositionState update(long deltaT) {
        if (animationDone) {
            return state;
        }
        state = force.updateState(state, deltaT);
        if (state.getPosition() < lowerLimit) {
            state.setPosition(lowerLimit);
            state.setVelocity(-impactCoefficient * state.getVelocity());
        } else if (state.getPosition() > upperLimit) {
            state.setPosition(upperLimit);
            if (Math.abs(state.getVelocity()) < THRESHOLD) {
                state.setVelocity(0);
                animationDone = true;
            } else {
                state.setVelocity(-impactCoefficient * state.getVelocity());
            }
        }
        return state;
    }
}
