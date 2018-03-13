import java.text.DecimalFormat;

 public class Clock {
   /**
    *  Class field definintions go here
    */
    private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
    private static final double INVALID_ARGUMENT_VALUE = -1.0;
    private static final double MAXIMUM_DEGREE_VALUE = 360.0;
    private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
    private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;
    private double hours;
    private double minutes;
    private double seconds;
    private double timeslice = 0;
    private double angle = 0;
    private double totalSeconds = 0;
    private double degrees;
   /**
    *  Constructor goes here
    */
    public Clock() {
        timeslice = DEFAULT_TIME_SLICE_IN_SECONDS;
    }

    public Clock( double a, double t) {
        timeslice = t;
        angle = a % 360;
    }

   /**
    *  Methods go here
    *
    *  Method to calculate the next tick from the time increment
    *  @return double-precision value of the current clock tick
    */
    public double tick() {
        totalSeconds += timeslice;
        return totalSeconds;
    }

   /**
    *  Method to validate the angle argument
    *  @param   argValue  String from the main programs args[0] input
    *  @return  double-precision value of the argument
    *  @throws  NumberFormatException
    */
    public double validateAngleArg( String argValue ) throws NumberFormatException {
       double targetAngle = Double.parseDouble(argValue);
       if ( targetAngle < 0 || targetAngle > 360 ){
          throw new NumberFormatException("You entered invalid arguments");
       }
       return targetAngle;
    }

   /**
    *  Method to validate the optional time slice argument
    *  @param  argValue  String from the main programs args[1] input
    *  @return double-precision value of the argument or -1.0 if invalid
    *  note: if the main program determines there IS no optional argument supplied,
    *         I have elected to have it substitute the string "60.0" and call this
    *         method anyhow.  That makes the main program code more uniform, but
    *         this is a DESIGN DECISION, not a requirement!
    *  note: remember that the time slice, if it is small will cause the simulation
    *         to take a VERY LONG TIME to complete!
    */
    public double validateTimeSliceArg( String argValue ) throws NumberFormatException {
        double timeslice = Double.parseDouble(argValue);
        if (timeslice < 0 || timeslice > 1800) {
            throw new NumberFormatException("You entered invalid arguments");
        }
        return timeslice;
    }

   /**
    *  Method to calculate and return the current position of the hour hand
    *  @return double-precision value of the hour hand location
    */
    public double getHourHandAngle() {
       double hourHandAngle = ( totalSeconds * HOUR_HAND_DEGREES_PER_SECOND ) % 360;
       return hourHandAngle;
    }

   /**
    *  Method to calculate and return the current position of the minute hand
    *  @return double-precision value of the minute hand location
    */
    public double getMinuteHandAngle() {
       double minuteHandAngle = ( totalSeconds * MINUTE_HAND_DEGREES_PER_SECOND ) % 360;
       return minuteHandAngle;
    }

   /**
    *  Method to calculate and return the angle between the hands
    *  @return double-precision value of the angle between the two hands
    */
    public double getHandAngle() {
        double angleFinal = Math.abs(getHourHandAngle() - getMinuteHandAngle()) % 360;
        if (angleFinal > 180) {
            return 360 - angleFinal;
        }
        return angleFinal;
    }


   /**
    *  Method to fetch the total number of seconds
    *   we can use this to tell when 12 hours have elapsed
    *  @return double-precision value the total seconds private variable
    */
    public double getTotalSeconds() {
       return totalSeconds;
    }

   /**
    *  Method to return a String representation of this clock
    *  @return String value of the current clock
    */
    public String toString() {
        hours = Math.floor(totalSeconds / 3600);
        minutes = Math.floor((totalSeconds % 3600) /60);
        seconds = totalSeconds - ((minutes * 60) + (hours * 3600));
        DecimalFormat df = new DecimalFormat("#.00");
        return (int) hours + " : " + (int) minutes + " : " + df.format(seconds);
    }

   /**
    *  The main program starts here
    *  remember the constraints from the project description
    *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
    *  be sure to make LOTS of tests!!
    *  remember you are trying to BREAK your code, not just prove it works!
    */
    public static void main( String args[] ) {

        System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );
      Clock clock = new Clock();
      System.out.println( "    New clock created: " + clock.toString() );
      System.out.println( "    Testing validateAngleArg()....");
      System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }

      System.out.println( "\n    Testing validateAngleArg()....");
      System.out.println( "      sending '  0 degrees', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }

      System.out.print( "      sending ' 30 degrees', expecting double value  30.0" );
      try { System.out.println( (30.0 == clock.validateAngleArg( "30.0" )) ? " - got  30.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown:\n            " + e.toString() ); }

      System.out.print( "      sending ' 60 degrees', expecting double value  60.0" );
      try { System.out.println( (60.0 == clock.validateAngleArg( "60.0" )) ? " - got  60.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown:\n            " + e.toString() ); }

      System.out.print( "      sending ' 90 degrees', expecting double value  90.0" );
      try { System.out.println( (90.0 == clock.validateAngleArg( "90.0" )) ? " - got  90.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown:\n            " + e.toString() ); }

      System.out.print( "      sending '120 degrees', expecting double value 120.0" );
      try { System.out.println( (120.0 == clock.validateAngleArg( "120.0" )) ? " - got 120.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown:\n            " + e.toString() ); }

      System.out.print( "      sending '150 degrees', expecting double value 150.0" );
      try { System.out.println( (150.0 == clock.validateAngleArg( "150.0" )) ? " - got 150.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown:\n            " + e.toString() ); }

      System.out.println( "      sending '  355 degrees', expecting double value   355.0" );
      try { System.out.println( (355.0 == clock.validateAngleArg( "355.0" )) ? " - got 355.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }

      System.out.println( "      sending '  360 degrees', expecting double value   360.0" );
      try { System.out.println( (360.0 == clock.validateAngleArg( "360.0" )) ? " - got 360.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }

      System.out.println( "\n    Testing validateTimeSliceArg()....");
      System.out.println( "      sending '  no seconds', expecting double value   60.0" );
      try { System.out.println( (60.0 == clock.validateTimeSliceArg( "" )) ? " - got 60.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }

      System.out.print( "      sending '  10.0 seconds', expecting double value   10.0" );
      try { System.out.println( (10.0 == clock.validateTimeSliceArg( "10.0" )) ? " - got   10.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown:\n            " + e.toString() ); }

      System.out.print( "      sending '  30.0 seconds', expecting double value   30.0" );
      try { System.out.println( (30.0 == clock.validateTimeSliceArg( "30.0" )) ? " - got   30.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown:\n            " + e.toString() ); }

      System.out.println( "      sending '  0 seconds', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateTimeSliceArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }

      System.out.println( "      sending '  1799 seconds', expecting double value   1799.0" );
      try { System.out.println( (1799.0 == clock.validateTimeSliceArg( "1799.0" )) ? " - got 1799.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }

   }

 }
