public class ClockSolver {

    private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
    private final double DEFAULT_TIME_SLICE_SECONDS = 60.0;
    private final static double EPSILON_VALUE       = 0.1;      // small value for double-precision comparisons
    private static final double MAXIMUM_NUMBER_OF_SECONDS = 43200;
    private static double targetAngle = 0;
    private static double timeSlice = 0;

   /**
    *  Constructor
    *  This just calls the superclass constructor, which is "Object"
    */
    public ClockSolver() {
       super();
    }

   /**
    *  Method to handle all the input arguments from the command line
    *   this sets up the variables for the simulation
    */
    public void handleInitialArguments( String args[] ) {
      // args[0] specifies the angle for which you are looking
      //  your simulation will find all the angles in the 12-hour day at which those angles occur
      // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds
      // you may want to consider using args[2] for an "angle window"

       System.out.println( "\n   Hello world, from the ClockSolver program!!\n\n" ) ;
       if( 0 == args.length ) {
          System.out.println( "   Sorry you must enter at least one argument\n" +
                              "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                              "   Please try again..........." );
          System.exit( 1 );
       }

       if ( 1 == args.length ) {
           timeSlice = DEFAULT_TIME_SLICE_SECONDS;
           targetAngle = Double.parseDouble(args[0]);
       }
       if ( 2 == args.length ) {
            timeSlice = Double.parseDouble(args[1]);
            targetAngle = Double.parseDouble(args[0]);
       }
    }

   /**
    *  The main program starts here
    *  remember the constraints from the project description
    *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
    *  @param  args  String array of the arguments from the command line
    *                args[0] is the angle for which we are looking
    *                args[1] is the time slice; this is optional and defaults to 60 seconds
    */
    public static void main( String args[] ) {
       ClockSolver cse = new ClockSolver();
       double[] timeValues = new double[3];
       cse.handleInitialArguments( args );
       Clock clock = new Clock(cse.targetAngle, cse.timeSlice);
       while( clock.getTotalSeconds() <= 43200 ) {
             if ( cse.targetAngle - clock.getHandAngle() <= EPSILON_VALUE )  {
                 System.out.println( clock.toString());
             }
             clock.tick();
        }
       System.exit( 0 );
    }
 }
