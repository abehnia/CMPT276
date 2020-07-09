package cmpt276.proj.finddamatch.welcomeActivity;

public class AnimationEngine {
    private float lowerLimit;
    private float upperLimit;
    private GravityWithDragForce force;
    private PositionState state;
    private float impactCoefficient;

    AnimationEngine(GravityWithDragForce force, float lowerLimit,
                    float upperLimit, PositionState state,
                    float impactCoefficient) {
        this.force = force;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.state = state;
        this.impactCoefficient = impactCoefficient;
    }

    AnimationEngine(GravityWithDragForce force, PositionState state,
                    float impactCoefficient) {
        this(force, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY,
                state, impactCoefficient);
    }

    PositionState update(long deltaT) {
        state = force.updateState(state, deltaT);
        if (state.getPosition() < lowerLimit) {
            state.setPosition(lowerLimit);
            state.setVelocity(-impactCoefficient * state.getVelocity());
        }
        if (state.getPosition() > upperLimit) {
            state.setPosition(upperLimit);
            state.setVelocity(-impactCoefficient * state.getVelocity());
        }
        return state;
    }
}
