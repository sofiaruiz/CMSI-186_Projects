import java.text.DecimalFormat;

public class Timer {

  private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;
  private static double totalSeconds = 0;
  private static double timeSlice = 0;
  private double hours = 0;
  private double minutes = 0;
  private double seconds = 0;

  public Timer() {

  }
  public Timer(double t) {
    timeSlice = t;
  }

  public double tick() {
    totalSeconds += timeSlice;
    return totalSeconds;
  }

  /**
   * Method to validate the optional time slice arg from the command line
   * @param argValue String from the main programs args[7] input
   * @return double-precision value of the argument or -1.0 if invalid
   * @throws NumberFormatException
   */
  public double validateTimeSliceArg(String argValue) throws NumberFormatException,
                                                             IllegalArgumentException {
      double returnValue = 0.0;
      try {
         returnValue = Double.parseDouble( argValue );
      }
      catch( NumberFormatException nfe ) {
         throw new NumberFormatException( " [converting time slice value failed]");
      }
      if( (0.0 >= returnValue) || (1800.0 < returnValue) ) {
         throw new IllegalArgumentException( " [time slice value out of range]" );
      }
      return returnValue;
   }



  public double getTotalSeconds() {
    return totalSeconds;
  }

  public String toString() {
    DecimalFormat df = new DecimalFormat("#.000");
    DecimalFormat dfi = new DecimalFormat("#0");
    hours = Math.floor( totalSeconds / 3600);
    minutes = Math.floor((totalSeconds % 3600) / 60);
    seconds = totalSeconds - ((hours * 3600) + (minutes * 60));
    return dfi.format(hours) + ":" + dfi.format(minutes) + ":" + df.format(seconds);
  }

}
