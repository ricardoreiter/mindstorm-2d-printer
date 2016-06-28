import java.awt.geom.Point2D.Float;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Printer printer = new Printer(Motor.A, Motor.B, Motor.C, new TouchSensor(SensorPort.S1), new TouchSensor(SensorPort.S2));
        printer.drawLine(new Float(10, 10), new Float(50, 10));
        printer.drawLine(new Float(50, 10), new Float(50, 15));
        printer.drawLine(new Float(50, 15), new Float(10, 15));
        printer.drawLine(new Float(10, 15), new Float(10, 10));
        printer.moveTo(new Float(0, 0), false);
        printer.stop();
    }

}
