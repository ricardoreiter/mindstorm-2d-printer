import java.awt.geom.Point2D;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.Touch;

public class Printer {

    private static final int X_SIZE = 500;
    private static final int Y_SIZE = 500;
    private static final int X_ROTATION_SIZE = 1000;
    private static final int Y_ROTATION_SIZE = 1000;
    private static final int X_ROTATION_PER_POS = X_ROTATION_SIZE / X_SIZE;
    private static final int Y_ROTATION_PER_POS = Y_ROTATION_SIZE / Y_SIZE;

    private Axis xAxis;
    private Axis yAxis;
    private Pen pen;
    private Point2D currentPos;

    public Printer(RegulatedMotor motorX, RegulatedMotor motorY, RegulatedMotor motorPen, Touch s1, Touch s2) {
        xAxis = new Axis(motorX, X_SIZE, X_ROTATION_PER_POS, 1, s1);
        yAxis = new Axis(motorY, Y_SIZE, Y_ROTATION_PER_POS, 1, s2);
        pen = new Pen(motorPen);
        currentPos = new Point2D.Float(0, 0);
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
        int xDiff = (int) (pos.getX() - currentPos.getX());
        int yDiff = (int) (pos.getY() - currentPos.getY());
        xDiff = xDiff * X_ROTATION_PER_POS;
        yDiff = yDiff * Y_ROTATION_PER_POS;

        int xSpeed = Axis.MAX_VELOCITY;
        int ySpeed = Axis.MAX_VELOCITY;

        if (Math.abs(xDiff) > Math.abs(yDiff)) {
            ySpeed = (Math.abs(yDiff) * xSpeed) / Math.abs(xDiff);
        } else {
            xSpeed = (Math.abs(xDiff) * ySpeed) / Math.abs(yDiff);
        }

        xAxis.gotoPos((int) pos.getX(), xSpeed, true);
        yAxis.gotoPos((int) pos.getY(), ySpeed, false);
        currentPos = pos;
        xAxis.waitCompleteMove();
        yAxis.waitCompleteMove();
    }

    public void moveTo(Point2D pos, boolean penOn) {
        pen.setActive(penOn);
        gotoPos(pos);
    }

}
