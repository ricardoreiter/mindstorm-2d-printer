
public class Pen {

	private static final int ROTATE_SIZE = 10;
	private static final int ROTATE_SPEED = 10;
	
	private final Motor motor;
	private boolean isActive = false;
	
	public Pen(Motor motor) {
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
