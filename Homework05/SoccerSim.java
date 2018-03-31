import java.text.DecimalFormat;

 public class SoccerSim {

    public static double xLocation;
    public static double yLocation;
    public static double xVel;
    public static double yVel;
    public static double timeslice = 1.0;
    private static int ballQuant;
    private int cuatro = 4;
    public static double fieldX = 500;
    public static double fieldY = 500;
    public static double poleX = 20;
    public static double poleY = 20;
    private final static double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;
    private static Timer timer;
    private static Ball[] balls;

    public SoccerSim() {
        super();
    }

    public double validateInitialArguments( String[] argValues) throws IllegalArgumentException, NumberFormatException {
       try {
        xLocation = Double.parseDouble(argValues[0]);
        yLocation = Double.parseDouble(argValues[1]);
        xVel = Double.parseDouble(argValues[2]);
        yVel = Double.parseDouble(argValues[3]);
       }
       catch( NumberFormatException nfe) {
        System.out.println(nfe.toString());
       }
       if (timeslice > 1800) {
        throw new IllegalArgumentException("Not a valid timeslice");
       }
       if (xLocation > fieldX || yLocation > fieldY ) {
        throw new IllegalArgumentException("Location out of range");
       }

       if ((argValues.length % 4) == 1) {
        ballQuant = (argValues.length - 1) / 4;
       }
       if ((argValues.length % 4) == 0) {
        ballQuant = argValues.length / 4;
       } else if ((argValues.length % 4) == 2) {
         throw new IllegalArgumentException("Invalid number of arguments");
       } else if ((argValues.length % 4) == 3) {
         throw new IllegalArgumentException("Invalid number of arguments");
       }
       return ballQuant;

    }
    public boolean isMoving() {
        for (int i = 0 ; i < ballQuant; i++) {
            if (!(balls[i].xVel < 0.000001 && balls[i].yVel < 0.000001)) {
                return true;
            }
       }
       return false;
    }

    public boolean isOnField() {
        for (int i =0 ; i < ballQuant; i++) {
            if (balls[i].xLocation < (fieldX / 2) || balls[i].yLocation < (fieldY / 2 )) {
                return true;
            }
        }
        return false;
    }

     public void isCollision() {
        double xDiff = xLocation - xLocation2;
        double yDiff = yLocation - yLocation2;
        if ((Math.sqrt((Math.pow(xDiff, 2)) + Math.pow(yDiff, 2))) <= 8.9) {
            return true;
        }
        return false;
     }

     public void handleInitialArguments( String args[] ) {
         System.out.println("\n   Hello world, from the SoccerSim program!!\n\n");
         if( 0 == args.length ) {
           System.out.println( "   Sorry you must enter at least cuatro arguments\n" +
                               "   Usage: java SoccerSim <x Location> <y Location> <x velocity> <y velocity> [timeslice]\n" +
                               "   Please try again..........." );
             System.exit(1);
         }
         if ((args.length % 4) == 1) {
           timeslice = Double.parseDouble(args[args.length - 1]);
         }
         else {
           timeslice = DEFAULT_TIME_SLICE_IN_SECONDS;
         }
         timer = new Timer(timeslice);

         try {
           int j = 0;

           balls = new Ball[ballQuant];
           for (int i = 0; i < args.length/cuatro; i++) {
             balls[j] = new Ball(this.timeslice);
             balls[j].xLocation = Double.parseDouble(args[i]);
             balls[j].yLocation = Double.parseDouble(args[4 * i + 1]);
             balls[j].xVel = Double.parseDouble(args[4 * i + 2]);
             balls[j].yVel = Double.parseDouble(args[4 * i +3 ]);
             j++;
            }
         }
         catch( NumberFormatException nfe ) {
           System.out.println(nfe.toString());
         }

     }



     public static void main( String[] args ) {

       SoccerSim ss = new SoccerSim();
       DecimalFormat df = new DecimalFormat("#0.000");
       ss.isCollision();
       try {
         ss.validateInitialArguments( args );
       }
       catch( IllegalArgumentException iae) {
         System.out.println(iae.toString());
         System.exit(-1);
       }
       System.out.println("Test");
       System.out.println(ballQuant);
       ss.handleInitialArguments( args );
       System.out.println("initial report : " + timer.toString());
          for ( int i = 0; i < ballQuant; i++ ) {
            System.out.println(i + ": position <" + df.format(balls[i].xLocation) + "," + df.format(balls[i].yLocation)+ ">" );
            System.out.println(i + ": velocity <" + df.format(balls[i].xVel) + "," + df.format(balls[i].yVel) + ">");
          }
          System.out.println("Pole location : (" + poleX + "," + poleY + ")\n");


       while ( ss.isMoving() && ss.isOnField()) {
         System.out.println(timer.tick());
         System.out.println("progress report :" + timer.toString());

         for ( int i = 0; i < ballQuant; i++ ) {
           balls[i].move();
           System.out.println( i + "position <" + df.format(balls[i].getLocation()[0]) + "," + df.format(balls[i].move()[1]) + ">");
            if(!(balls[i].xVel < 0.000001 && balls[i].yVel < 0.000001))  {
                System.out.println( i + "velocity <" + df.format(balls[i].xVel) + "," + df.format(balls[i].yVel) + ">");
            }
            else {
                System.out.println("  resting");
            }
          }
         }
         ss.isCollision();

       System.out.println("final report :" +  timer.toString() );
       System.out.println("no collision possible!");
       System.exit(-1);
     }

 }
