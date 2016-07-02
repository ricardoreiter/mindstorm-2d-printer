import lejos.robotics.RegulatedMotor;

public class Axis {

    public final static int MAX_VELOCITY = 300;

    private final int rotationPerPos;
    private final int size;
    private final byte rotationDirection;
    private final RegulatedMotor motor;
    private int actualPos = 0;

    public Axis(RegulatedMotor motor, int size, int rotationPerPos, int rotationDirection) {
        this.motor = motor;
        this.rotationPerPos = rotationPerPos;
        this.size = size;
        this.rotationDirection = (byte) rotationDirection;
    }

    public void waitCompleteMove() {
        motor.waitComplete();
    }

    public void gotoPos(int pos, boolean ignoreComplete) {
        if (pos > size || pos < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        motor.setSpeed(MAX_VELOCITY);

        int rotateQuantity = (pos - actualPos) * rotationPerPos;
        rotateQuantity *= rotationDirection;

        motor.rotate(rotateQuantity, ignoreComplete);
        actualPos = pos;
    }

}
