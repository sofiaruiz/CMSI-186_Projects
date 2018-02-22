import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll {

   static DiceSet bob = null;
   static int highscore = 0;

   public static void main( String args[] ) {
      if(0 == args.length) {
          System.out.println("USAGE: java HighRoll <#dice> <#sides>");
          System.exit(-1);
      }
      bob = new DiceSet(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
      while(true){
         System.out.println( "1. roll all the dice\n" +
                             "2. roll one die\n" +
                             "3. calculate the sum\n" +
                             "4. save the sum as high score\n" +
                             "5. display the high score\n" +
                             "6. Quit" );
         System.out.print(">>");


     // This line uses the two classes to assemble an "input stream" for the user to type
     // text into the program
        BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );


           String inputLine = null;
           try {
              inputLine = input.readLine();
              if( 0 == inputLine.length() ) {
                 System.out.println("enter some text!:");
              }
              if('1' == inputLine.charAt(0)) {
                  bob.rollAll();
                  System.out.println(bob.toString());
              } else if('2' == inputLine.charAt(0)) {
                  System.out.println("Which dice do you want to roll?");
                  inputLine = input.readLine();
                  System.out.println(bob.rollIndividual(Integer.parseInt(inputLine) - 1));
                  System.out.println(bob.toString());
              } else if('3' == inputLine.charAt(0)) {
                  System.out.println(bob.sum());
                  System.out.println(bob.toString());
              } else if('4' == inputLine.charAt(0)) {
                  highscore = bob.sum();
                  System.out.println(bob.toString());
              } else if('5' == inputLine.charAt(0)) {
                  System.out.println("The highscore is:" + highscore);
                  System.out.println(bob.toString());
              } else if('6' == inputLine.charAt(0)) {
                  System.exit(0);
              }


         }
         catch( IOException ioe ) {
            System.out.println( "Caught IOException" );
         }
      }
   }
}
