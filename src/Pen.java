import lejos.robotics.RegulatedMotor;

public class Pen {

    private static final int ROTATE_SIZE = 10;
    private static final int ROTATE_SPEED = 10;

    private final RegulatedMotor motor;
    private boolean isActive = false;

    public Pen(RegulatedMotor motor) {
        this.motor = motor;
        this.motor.setSpeed(ROTATE_SPEED);
    }

    public void setActive(boolean active) {
        if (isActive != active) {
            if (isActive) {
                motor.rotate(-ROTATE_SIZE);
            } else {
                motor.rotate(ROTATE_SIZE);
            }
        }
        isActive = active;
    }

}
