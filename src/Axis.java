import lejos.robotics.RegulatedMotor;
import lejos.robotics.Touch;

public class Axis {

    public final static int MAX_VELOCITY = 100;

    private final int rotationPerPos;
    private final int size;
    private final byte rotationDirection;
    private final RegulatedMotor motor;
    private int actualPos = 0;

    public Axis(RegulatedMotor motor, int size, int rotationPerPos, int rotationDirection, Touch endCourseSensor) {
        this.motor = motor;
        this.rotationPerPos = rotationPerPos;
        this.size = size;
        this.rotationDirection = (byte) rotationDirection;

        motor.setSpeed(MAX_VELOCITY);
        if (rotationDirection < 0) {
            motor.forward();
        } else {
            motor.backward();
        }

        while (!endCourseSensor.isPressed()) {}
        motor.stop();
    }

    public void waitCompleteMove() {
        motor.waitComplete();
    }

    public void gotoPos(int pos, int velocity, boolean ignoreComplete) {
        if (velocity > 0) {
            if (pos > size || pos < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
            motor.setSpeed(velocity);

            int rotateQuantity = (pos - actualPos) * rotationPerPos;
            rotateQuantity *= rotationDirection;

            motor.rotate(rotateQuantity, ignoreComplete);
            actualPos = pos;
        }
    }

}
