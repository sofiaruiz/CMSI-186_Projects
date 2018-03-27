import java.text.DecimalFormat;

public class Ball {

    private final static double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;
    public double xLocation;
    public double yLocation;
    public double xVel;
    public double yVel;
    private double timeSlice;


    public Ball(double t) {

        timeSlice = t;
    }

    //methods start here!
    public double[] getLocation() {
        double[] location = new double[2];
        location[0] = xLocation;
        location[1] = yLocation;
        return location;
    }

    public double[] getVelocity () {
        double[] velocity = new double[2];
        velocity[0] = xVel;
        velocity[1] = yVel;
        return velocity;
    }

    public void friction() {
        this.xVel *= Math.pow(0.99, timeSlice);
        this.yVel *= Math.pow(0.99, timeSlice);
    }

    public double [] move() {
        double[] moving = new double [2];
        xLocation = xLocation + xVel;
        yLocation = yLocation + yVel;
        moving[0] = xLocation;
        moving[1] = yLocation;
        friction();
        return moving;
    }

    public boolean isMoving() {
        if ( !(xVel < (1 / 12)) || !(yVel < ( 1 / 12 ))) {
          return true;
        }
        return false;
    }

    public boolean isOnField(double fieldX, double fieldY) {
        if((Math.abs(xLocation) <= (fieldX/2)) && (Math.abs(yLocation) <= (fieldY/2))){
            return true;
        }
        return false;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("#0.000");
        String ball= "<" + df.format(xLocation) + "," + df.format(yLocation) + ">" + "<" + df.format(xVel) + "," + df.format(yVel) + ">";
        return ball;
    }

}
