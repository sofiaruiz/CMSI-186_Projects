public class DiceSet {

	   private int count;
	   private int sides;
	   private Die[] ds = null;

	   // public constructor:
	  /**
	   * constructor
	   * @param  count int value containing total dice count
	   * @param  sides int value containing the number of pips on each die
	   * @throws IllegalArgumentException if one or both arguments don't make sense
	   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
	   */
	   public DiceSet( int nCount, int nSides ) {
		   count = nCount;
		   ds = new Die[count];
		   sides = nSides;
		   if (sides < 4) {
			   throw new IllegalArgumentException("You need more sides!");
		   }
		   for (int i = 0; i < count; i++) {
			   ds[i] = new Die(sides);
		   }
	   }

	  /**
	   * @return the sum of all the dice values in the set
	   */
	   public int sum() {
		  int sum = 0;
	      for (int i = 0; i < count; i++) {
	          sum += ds[i].getValue();
	      }
	      return sum;
	   }

	  /**
	   * Randomly rolls all of the dice in this set
	   *  NOTE: you will need to use one of the "toString()" methods to obtain
	   *  the values of the dice in the set
	   */
	   public void rollAll() {
		   for( int i = 0; i < count; i++) {
			   ds[i].roll();
		   }
	   }

	  /**
	   * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
	   * @param  dieIndex int of which die to roll
	   * @return the integer value of the newly rolled die
	   * @trhows IllegalArgumentException if the index is out of range
	   */
	   public int rollIndividual( int dieIndex ) {
	      return ds[dieIndex].roll();
	   }

	  /**
	   * Gets the value of the die in this set indexed by 'dieIndex'
	   * @param  dieIndex int of which die to roll
	   * @trhows IllegalArgumentException if the index is out of range
	   */
	   public int getIndividual( int dieIndex ) {
	      return ds[dieIndex].getValue();
	   }

	  /**
	   * @return Public Instance method that returns a String representation of the DiceSet instance
	   */
	   public String toString() {
	      String result = "";
	      for(int i = 0; i < count; i++) {
	    	      result += ds[i].toString();
	      }
	      return result;
	   }

	  /**
	   * @return Class-wide version of the preceding instance method
	   */
	   public static String toString( DiceSet ds ) {
	      return ds.toString();
	   }

	  /**
	   * @return  tru iff this set is identical to the set passed as an argument
	   */
	   public boolean isIdentical( DiceSet ds1 ) {
		   if (count == ds1.count && sides == ds1.sides) {
			   return true;
	      } else {
	    	       return false;
	      }
	   }
	  /**
	   * A little test main to check things out
	   */
	   public static void main( String[] args ) {
	          DiceSet d = new DiceSet(6, 6);
	          System.out.println("rolling all die:" + d.rollAll());
		  System.out.println("rolling third die:" + d.rollIndividual(3));
		  System.out.println("sum of the all die:" + d.sum());

		  DiceSet d1 = new DiceSet(4, 4);
	          System.out.println("rolling all die:" + d.rollAll());
		  System.out.println("rolling third die:" + d.rollIndividual(3));
		  System.out.println("sum of the all die:" + d.sum());

		  DiceSet d2 = new DiceSet(12, 5);
	          System.out.println("rolling all die:" + d.rollAll());
		  System.out.println("rolling second die:" + d.rollIndividual(2));
		  System.out.println("sum of the all die:" + d.sum());

		  DiceSet d3 = new DiceSet(9, 9);
	          System.out.println("rolling all die:" + d.rollAll());
		  System.out.println("rolling fifth die:" + d.rollIndividual(5));
		  System.out.println("sum of the all die:" + d.sum());

		  DiceSet d4 = new DiceSet(5, 6);
	          System.out.println("rolling all die:" + d.rollAll());
		  System.out.println("rolling first die:" + d.rollIndividual(1));
		  System.out.println("sum of the all die:" + d.sum());
	   }	   
}
