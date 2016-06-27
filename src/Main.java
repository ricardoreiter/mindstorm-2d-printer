import java.awt.geom.Point2D.Float;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.RegulatedMotorListener;
import lejos.robotics.Touch;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //        Printer printer = new Printer(Motor.A, Motor.B, Motor.C, new TouchSensor(SensorPort.S1), new TouchSensor(SensorPort.S2));
        Printer printer = new Printer(new MotorMock("X"), new MotorMock("Y"), new MotorMock("Pen"), new TouchMock(), new TouchMock());
        printer.drawLine(new Float(10, 10), new Float(20, 20));
        printer.drawLine(new Float(20, 20), new Float(25, 20));
        printer.drawLine(new Float(10, 10), new Float(25, 20));
        printer.drawLine(new Float(25, 20), new Float(26, 30));
        printer.stop();
    }

    public static class TouchMock implements Touch {

        @Override
        public boolean isPressed() {
            return true;
        }

    }

    public static class MotorMock implements RegulatedMotor {

        private String name;

        public MotorMock(String name) {
            this.name = name;
        }

        @Override
        public void forward() {
            System.out.println(name + " forward");
        }

        @Override
        public void backward() {
            System.out.println(name + " backward");
        }

        /* (non-Javadoc)
         * @see lejos.robotics.BaseMotor#stop()
         */
        @Override
        public void stop() {
            System.out.println(name + " stop");
        }

        /* (non-Javadoc)
         * @see lejos.robotics.BaseMotor#flt()
         */
        @Override
        public void flt() {
            // TODO Auto-generated method stub

        }

        /* (non-Javadoc)
         * @see lejos.robotics.BaseMotor#isMoving()
         */
        @Override
        public boolean isMoving() {
            // TODO Auto-generated method stub
            return false;
        }

        /* (non-Javadoc)
         * @see lejos.robotics.Tachometer#getRotationSpeed()
         */
        @Override
        public int getRotationSpeed() {
            // TODO Auto-generated method stub
            return 0;
        }

        /* (non-Javadoc)
         * @see lejos.robotics.Encoder#getTachoCount()
         */
        @Override
        public int getTachoCount() {
            // TODO Auto-generated method stub
            return 0;
        }

        /* (non-Javadoc)
         * @see lejos.robotics.Encoder#resetTachoCount()
         */
        @Override
        public void resetTachoCount() {
            // TODO Auto-generated method stub

        }

        /* (non-Javadoc)
         * @see lejos.robotics.RegulatedMotor#addListener(lejos.robotics.RegulatedMotorListener)
         */
        @Override
        public void addListener(RegulatedMotorListener listener) {
            // TODO Auto-generated method stub

        }

        /* (non-Javadoc)
         * @see lejos.robotics.RegulatedMotor#removeListener()
         */
        @Override
        public RegulatedMotorListener removeListener() {
            // TODO Auto-generated method stub
            return null;
        }

        /* (non-Javadoc)
         * @see lejos.robotics.RegulatedMotor#stop(boolean)
         */
        @Override
        public void stop(boolean immediateReturn) {
            // TODO Auto-generated method stub

        }

        /* (non-Javadoc)
         * @see lejos.robotics.RegulatedMotor#flt(boolean)
         */
        @Override
        public void flt(boolean immediateReturn) {
            // TODO Auto-generated method stub

        }

        /* (non-Javadoc)
         * @see lejos.robotics.RegulatedMotor#waitComplete()
         */
        @Override
        public void waitComplete() {
            // TODO Auto-generated method stub

        }

        /* (non-Javadoc)
         * @see lejos.robotics.RegulatedMotor#rotate(int, boolean)
         */
        @Override
        public void rotate(int angle, boolean immediateReturn) {
            System.out.println(name + " rotate " + angle);
        }

        /* (non-Javadoc)
         * @see lejos.robotics.RegulatedMotor#rotate(int)
         */
        @Override
        public void rotate(int angle) {
            System.out.println(name + " rotate " + angle);
        }

        /* (non-Javadoc)
         * @see lejos.robotics.RegulatedMotor#rotateTo(int)
         */
        @Override
        public void rotateTo(int limitAngle) {
            // TODO Auto-generated method stub

        }

        /* (non-Javadoc)
         * @see lejos.robotics.RegulatedMotor#rotateTo(int, boolean)
         */
        @Override
        public void rotateTo(int limitAngle, boolean immediateReturn) {
            // TODO Auto-generated method stub

        }

        /* (non-Javadoc)
         * @see lejos.robotics.RegulatedMotor#getLimitAngle()
         */
        @Override
        public int getLimitAngle() {
            // TODO Auto-generated method stub
            return 0;
        }

        /* (non-Javadoc)
         * @see lejos.robotics.RegulatedMotor#setSpeed(int)
         */
        @Override
        public void setSpeed(int speed) {
            System.out.println(name + " setSpeed " + speed);
        }

        /* (non-Javadoc)
         * @see lejos.robotics.RegulatedMotor#getSpeed()
         */
        @Override
        public int getSpeed() {
            // TODO Auto-generated method stub
            return 0;
        }

        /* (non-Javadoc)
         * @see lejos.robotics.RegulatedMotor#getMaxSpeed()
         */
        @Override
        public float getMaxSpeed() {
            // TODO Auto-generated method stub
            return 0;
        }

        /* (non-Javadoc)
         * @see lejos.robotics.RegulatedMotor#isStalled()
         */
        @Override
        public boolean isStalled() {
            // TODO Auto-generated method stub
            return false;
        }

        /* (non-Javadoc)
         * @see lejos.robotics.RegulatedMotor#setStallThreshold(int, int)
         */
        @Override
        public void setStallThreshold(int error, int time) {
            // TODO Auto-generated method stub

        }

        /* (non-Javadoc)
         * @see lejos.robotics.RegulatedMotor#setAcceleration(int)
         */
        @Override
        public void setAcceleration(int acceleration) {
            // TODO Auto-generated method stub

        }

    }

}
