
public class Axis {

	public final static int MAX_VELOCITY = 100;
	
	private final int rotationSize;
	private final int rotationPerPos;
	private final int size;
	private final byte rotationDirection;
	private final Motor motor;
	private int actualPos = 0;
	
	public Axis(Motor motor, int rotationSize, int size, int rotationPerPos, byte rotationDirection, TouchSensor endCourseSensor) {
		this.motor = motor;
		this.rotationSize = rotationSize;
		this.rotationPerPos = rotationPerPos;
		this.size = size;
		this.rotationDirection = rotationDirection;
		
		motor.setSpeed(MAX_VELOCITY);
		if (rotationDirection > 0) {
			motor.forward();
		} else {
			motor.backward();
		}
		
		while (!endCourseSensor.isPressed()) {
		}
		motor.stop();
	}
	
	public void gotoPos(int pos, int velocity, boolean ignoreComplete) {
		motor.setSpeed(velocity);
		
		int rotateQuantity = (pos - actualPos) * rotationPerPos;
		rotateQuantity *= rotationDirection;
		
		motor.rotate(rotateQuantity, ignoreComplete);
	}
	
}
