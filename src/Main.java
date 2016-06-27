import com.sun.javafx.geom.Point2D;

public class Main {

	
	public static void main(String[] args) throws InterruptedException {
//		Thread.sleep(2000);
//		UltrasonicSensor ultrasonicSensor = new UltrasonicSensor(SensorPort.S3);
//		ColorSensor colorSensor = new ColorSensor(SensorPort.S1);
//		TouchSensor touchSensor = new TouchSensor(SensorPort.S4);
//		Behavior[] behaviorList = { //
//									new AndarRastrearBloco(ultrasonicSensor, colorSensor, touchSensor), //
//									new DoPath(),
//									new Girar(), //
//								  };
		
		Point2D currentPos = new Point2D(5, 0);
		Point2D pos = new Point2D(10, 5);
		
		int xDiff = (int) (pos.x - currentPos.x);
		int yDiff = (int) (pos.y - currentPos.y);
		xDiff = xDiff * 10;
		yDiff = yDiff * 10;
		
		int xSpeed = Axis.MAX_VELOCITY;
		int ySpeed = Axis.MAX_VELOCITY;
		
		if (xDiff > yDiff) {
			ySpeed = (yDiff * xSpeed) / xDiff;
		} else {
			xSpeed = (xDiff * ySpeed) / yDiff;
		}
		
		System.out.println(xDiff);
		System.out.println(xSpeed);
		System.out.println(yDiff);
		System.out.println(ySpeed);
	}
	
}
