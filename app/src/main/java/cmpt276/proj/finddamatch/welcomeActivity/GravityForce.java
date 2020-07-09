package cmpt276.proj.finddamatch.welcomeActivity;

public class GravityForce {
    private float g;
    private static final float G = 9.816f;

    GravityForce(float virtualToRealFactor) {
        this.g = G * virtualToRealFactor;
    }

    PositionState updateState(PositionState state, long deltaT) {
        float previousPosition = state.getPosition();
        float previousVelocity = state.getVelocity();
        float velocity = previousVelocity + g * deltaT / 1000.0f;
        float position = previousPosition + g * deltaT * deltaT /
                (1000.0f * 1000.0f) + previousVelocity * deltaT / (1000.0f);
        state.setVelocity(velocity);
        state.setPosition(position);
        return state;
    }
}
