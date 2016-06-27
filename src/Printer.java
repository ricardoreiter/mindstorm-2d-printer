import com.sun.javafx.geom.Point2D;

public class Printer {

	private static final int X_SIZE = 10;
	private static final int Y_SIZE = 10;
	private static final int X_ROTATION_SIZE = 100;
	private static final int Y_ROTATION_SIZE = 100;
	private static final int X_ROTATION_PER_POS = X_ROTATION_SIZE / X_SIZE;
	private static final int Y_ROTATION_PER_POS = Y_ROTATION_SIZE / Y_SIZE;
	
	private Axis xAxis;
	private Axis yAxis;
	private Pen pen;
	private Point2D currentPos;
	
	public Printer() {
		xAxis = new Axis(Motor.A, X_ROTATION_SIZE, X_SIZE, X_ROTATION_PER_POS, 1, new TouchSensor(SensorPort.S1));
		yAxis = new Axis(Motor.B, Y_ROTATION_SIZE, Y_SIZE, Y_ROTATION_PER_POS, 1, new TouchSensor(SensorPort.S2));
		pen = new Pen(Motor.C);
		currentPos = new Point2D(0, 0);
	}
	
	public void drawLine(Point2D from, Point2D to) {
		if (!from.equals(currentPos)) {
			pen.setActive(false);
			gotoPos(from);
		}
		pen.setActive(true);
		gotoPos(to);
	}
	
	public void stop() {
		pen.setActive(false);
	}
	
	private void gotoPos(Point2D pos) {
		int xDiff = (int) (pos.x - currentPos.x);
		int yDiff = (int) (pos.y - currentPos.y);
		xDiff = xDiff * X_ROTATION_PER_POS;
		yDiff = yDiff * Y_ROTATION_PER_POS;
		
		int xSpeed = Axis.MAX_VELOCITY;
		int ySpeed = Axis.MAX_VELOCITY;
		
		if (xDiff > yDiff) {
			ySpeed = (yDiff * xSpeed) / xDiff;
		} else {
			xSpeed = (xDiff * ySpeed) / yDiff;
		}
		
		xAxis.gotoPos(pos.x, xSpeed, true);
		yAxis.gotoPos(pos.y, ySpeed, false);
	}
	
}
