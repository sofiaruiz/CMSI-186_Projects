import java.util.Random;

public class Die {

	  /**
	   * private instance data
	   */
	   private int sides;
	   private int pips;
	   private final int MINIMUM_SIDES = 4;

	   // public constructor:
	  /**
	   * constructor
	   * @param nSides int value containing the number of sides to build on THIS Die
	   * @throws       IllegalArgumentException
	   * Note: parameter must be checked for validity; invalid value must throw "IllegalArgumentException"
	   */
	   public Die( int nSides ) {
		   sides = nSides;
		   pips = 0;
		   if(sides < MINIMUM_SIDES) {
			   throw new IllegalArgumentException("You need more sides!");
		   }
	   }

	  /**
	   * Roll THIS die and return the result
	   * @return  integer value of the result of the roll, randomly selected
	   */
	   public int roll() {
		   pips = (int)(Math.floor((Math.random()* sides) +1));
		   return pips;
	   }

	  /**
	   * Get the value of THIS die to return to the caller; note that the way
	   *  the count is determined is left as a design decision to the programmer
	   *  For example, what about a four-sided die - which face is considered its
	   *  "value"?
	   * @return the pip count of THIS die instance
	   */
	   public int getValue() {
	      return pips;
	   }

	  /**
	   * @param  int  the number of sides to set/reset for this Die instance
	   * @return      The new number of sides, in case anyone is looking
	   * @throws      IllegalArgumentException
	   */
	   public void setSides( int sides ) {
		   if (sides < MINIMUM_SIDES) {
			   throw new IllegalArgumentException("Invalid number of sides!");
		   }
		   this.sides = sides;
	   }

	  /**
	   * Public Instance method that returns a String representation of THIS die instance
	   * @return String representation of this Die
	   */
	   public String toString() {
		   return "[" + pips + "]";
	   }

	  /**
	   * Class-wide method that returns a String representation of THIS die instance
	   * @return String representation of this Die
	   */
	   public static String toString( Die d ) {
	      return d.toString();
	   }

	  /**
	   * A little test main to check things out
	   */
	   public static void main( String[] args ) {
	      Die d3 = new Die(7);
	      System.out.println("new value =" + d3.roll());
	      System.out.println("current value =" + d3.getValue());
	      System.out.println(d3.toString());

	      Die d4 = new Die(6);
	      System.out.println("new value =" + d4.roll());
	      System.out.println("current value =" + d4.getValue());
	      System.out.println(d4.toString());

	      Die d5 = new Die(12);
	      System.out.println("new value =" + d5.roll());
	      System.out.println("current value =" + d5.getValue());
	      System.out.println(d5.toString());

		  Die d6 = new Die(14);
	      System.out.println("new value =" + d3.roll());
	      System.out.println("current value =" + d3.getValue());
	      System.out.println(d3.toString());

		  Die d7 = new Die(6);
	      System.out.println("new value =" + d3.roll());
	      System.out.println("current value =" + d3.getValue());
	      System.out.println(d3.toString());

	      Die d = new Die(1);
	      Die d2 = new Die(2);

	   }

}
