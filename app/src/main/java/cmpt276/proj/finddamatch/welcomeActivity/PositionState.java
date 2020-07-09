package cmpt276.proj.finddamatch.welcomeActivity;

public class PositionState {
    private float position;
    private float velocity;

    public PositionState(float position, float velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public float getPosition() {
        return position;
    }

    public void setPosition(float position) {
        this.position = position;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }
}
