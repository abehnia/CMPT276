package cmpt276.proj.finddamatch.welcomeActivity;

public class GravityWithDragForce {
    private float g;
    private float frictionCoefficient;
    private static final float G = 9.816f;

    GravityWithDragForce(float virtualToRealFactor, float frictionCoefficient) {
        this.g = G * virtualToRealFactor;
        this.frictionCoefficient = frictionCoefficient;
    }

    PositionState updateState(PositionState state, long deltaT) {
        float previousPosition = state.getPosition();
        float previousVelocity = state.getVelocity();
        float velocity = (float) (previousVelocity *
                Math.exp(-frictionCoefficient * deltaT / 1000f) +
                g * (1 - Math.exp(-frictionCoefficient * deltaT / 1000f)) /
                        frictionCoefficient);
        float position = - velocity / frictionCoefficient +
                previousVelocity / frictionCoefficient +
                previousPosition + g * deltaT / (frictionCoefficient * 1000f);
        state.setVelocity(velocity);
        state.setPosition(position);
        return state;
    }
}
