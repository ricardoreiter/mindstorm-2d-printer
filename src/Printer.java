import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.LinkedList;
import java.util.List;

import lejos.robotics.RegulatedMotor;

public class Printer {

    private static final int X_SIZE = 75;
    private static final int Y_SIZE = 18;
    private static final int X_ROTATION_SIZE = 750;
    private static final int Y_ROTATION_SIZE = 180;
    private static final int X_ROTATION_PER_POS = X_ROTATION_SIZE / X_SIZE;
    private static final int Y_ROTATION_PER_POS = Y_ROTATION_SIZE / Y_SIZE;

    private Axis xAxis;
    private Axis yAxis;
    private Pen pen;
    private Point2D currentPos;

    public Printer(RegulatedMotor motorX, RegulatedMotor motorY, RegulatedMotor motorPen) {
        xAxis = new Axis(motorX, X_SIZE, X_ROTATION_PER_POS, 1);
        yAxis = new Axis(motorY, Y_SIZE, Y_ROTATION_PER_POS, -1);
        pen = new Pen(motorPen);
        currentPos = new Point2D.Float(0, 0);
    }

    private static class Line {

        private Point2D from;
        private Point2D to;

        public Line(Point2D from, Point2D to) {
            super();
            this.from = from;
            this.to = to;
        }

        public Point2D getFrom() {
            return from;
        }

        public Point2D getTo() {
            return to;
        }

    }

    public void drawMatrix(int[][] matrix) {
        List<Line> lines = new LinkedList<Line>();

        for (int y = 0; y < matrix.length; y++) {
            Point2D lineFrom = null;
            for (int x = 0; x < matrix[y].length; x++) {
                if (matrix[y][x] == 1) {
                    if (lineFrom == null) {
                        if (x == matrix[y].length - 1) {
                            lines.add(new Line(new Float(x, y), new Float(x, y)));
                        } else {
                            lineFrom = new Float(x, y);
                        }
                    } else if (x == matrix[y].length - 1) {
                        lines.add(new Line(lineFrom, new Float(x, y)));
                    }
                } else {
                    if (lineFrom != null) {
                        lines.add(new Line(lineFrom, new Float(x - 1, y)));
                        lineFrom = null;
                    }
                }
            }
        }

        for (Line line : lines) {
            drawLine(line.getFrom(), line.getTo());
        }
        //        stop();
    }

    public void drawLine(Point2D from, Point2D to) {
        System.out.println(String.format("From %s -> To %s", from, to));
        //        if (!from.equals(currentPos)) {
        //            gotoPos(from);
        //            pen.setActive(false);
        //        }
        //        pen.setActive(true);
        //        gotoPos(to);
    }

    public void stop() {
        moveTo(new Float(0, 0), false);
    }

    private void gotoPos(Point2D pos) {
        int xDiff = (int) (pos.getX() - currentPos.getX());
        int yDiff = (int) (pos.getY() - currentPos.getY());

        if (xDiff != yDiff && xDiff != 0 && yDiff != 0) {
            throw new RuntimeException("Desenho na vertical com ângulo diferente de 45º");
        }

        xAxis.gotoPos((int) pos.getX(), true);
        yAxis.gotoPos((int) pos.getY(), false);
        currentPos = pos;
        xAxis.waitCompleteMove();
        yAxis.waitCompleteMove();
    }

    public void moveTo(Point2D pos, boolean penOn) {
        pen.setActive(penOn);
        gotoPos(pos);
    }

}
