/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  B.J. Johnson
 * Date       :  2017-04-04
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
 *                                     validateDigits, two reversers, and valueOf methods; revamped equals
 *                                     and compareTo methods to use the Java String methods; ready to
 *                                     start work on subtractByte and subtractInt methods
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;

public class BrobInt {

   public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
   public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
   public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
   public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
   public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
   public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
   public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
   public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
   public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
   public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
   public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

  /// Some constants for other intrinsic data types
  ///  these can help speed up the math if they fit into the proper memory space
   public static final BrobInt MAX_INT  = new BrobInt( new Integer( Integer.MAX_VALUE ).toString() );
   public static final BrobInt MIN_INT  = new BrobInt( new Integer( Integer.MIN_VALUE ).toString() );
   public static final BrobInt MAX_LONG = new BrobInt( new Long( Long.MAX_VALUE ).toString() );
   public static final BrobInt MIN_LONG = new BrobInt( new Long( Long.MIN_VALUE ).toString() );

  /// These are the internal fields
   private String internalValue = "";        // internal String representation of this BrobInt
   private int    sign          = 0;         // "0" is positive, "1" is negative
   private String reversed      = "";        // the backwards version of the internal String representation
   private int[]  dataArray   = null;      // byte array for storing the string values; uses the reversed string

  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
   public BrobInt( String val ) {
       String value = val.trim();

       if (value.charAt(0) == '+') {
           this.sign = 0;
           value = value.substring(0);
       } else {
           if(value.charAt(0) == '-')
           this.sign = 1;
           value = value.substring(1);
       }

       while (value.charAt(0) == '0' && value.length() > 1) {
           value = value.substring(1);
       }

       if(value.charAt(0) == '0') {
           this.sign = 0;
       }

       dataArray = new int[value.length()];
       for (int i = value.length() - 1; i >= 0; i--) {
           if (value.charAt(i) < '0' || value.charAt(i) > '9') {
               throw new IllegalArgumentException();
           }
           dataArray[value.length() - 1 - i] = Character.getNumericValue(value.charAt(i));
       }
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateDigits() {
       String isValid = "+-0123456789";
       for (int i = 0; i < internalValue.length(); i++) {
           if(!isValid.contains("" + internalValue.charAt(i))) {
               return false;
           }
       }
       return true;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of this BrobInt
   *  @return BrobInt that is the reverse of the value of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt reverser() {
       int j = internalValue.length() - 1;
       char[] output = new char[internalValue.length()];
       for( int i = 0; i < internalValue.length(); i++ ) {
          output[i] = internalValue.charAt(j);
          j-- ;
       }
       return new BrobInt(new String (output));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobIntk passed as argument
   *  Note: static method
   *  @param  gint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt reverser( BrobInt gint ) {
       int j = gint.internalValue.length() - 1;
       char[] output = new char[gint.internalValue.length()];
       for( int i = 0; i < gint.internalValue.length(); i++ ) {
          output[i] = gint.internalValue.charAt(j);
          j-- ;
       }
       return new BrobInt(new String( output ));
   }


   public BrobInt addInt( BrobInt val ) {
       String sum = "";
       int carry = 0;

       if (this.dataArray.length > val.dataArray.length) {
           val.padded(this);
       } else if (val.dataArray.length > this.dataArray.length) {
           this.padded(val);
       }

       if (this.sign == 1 && val.sign == 0) {
           this.sign = 0;
           if (this.equals(val)) {
               return val.subtractInt(this);
           }
           if (this.greaterThan(val)) {
               return val.subtractInt(this);
           }
           if (val.greaterThan(this)) {
               return val.subtractInt(this);
           }
       }
       if (this.sign == 0 && val.sign == 1) {
           val.sign = 0;
           if (this.equals(val)) {
               return val.subtractInt(this);
           }
           if (val.greaterThan(this)) {
               return this.subtractInt(val);
           }
           if (this.greaterThan(val)) {
               return this.subtractInt(val);
           }
       }

       for (int i = 0; i < this.dataArray.length; i++) {
           this.dataArray[i] = (this.dataArray[i] + val.dataArray[i] + carry);
           if (this.dataArray[i] > 9) {
               carry = 1;
               this.dataArray[i] = (this.dataArray[i]) % 10;
           } else {
               carry = 0;
           }
           sum = this.dataArray[i] + sum;
       }
       if (carry > 0) {
           sum = carry + sum;
       }
       if (this.sign == 1 && val.sign == 1) {
           sum = "-" + sum;
       }
       return new BrobInt(sum);
   }

   public BrobInt subtractInt( BrobInt val ) {
       String difference = "";
         int carry = 0;
         int helper = 0;

         if (this.dataArray.length > val.dataArray.length) {
             val.padded(this);
         } else if (val.dataArray.length > this.dataArray.length) {
             this.padded(val);
         }

         if (this.sign == 1 && val.sign == 1) {
             this.sign = 0;
             val.sign = 0;
             if (this.greaterThan(val)) {
                 return this.subtractInt(val);
             } else {
                 return val.subtractInt(this);
             }
         }

         if (this.sign == 0 && val.sign == 1) {
             val.sign = 0;
             return this.addInt(val);
         }

         if (this.sign == 1 && val.sign == 0) {
             val.sign = 1;
             return this.addInt(val);
         }

         if ( val.greaterThan(this) || (this.sign == 1 && val.sign == 0)) {
             for (int i = 0; i < this.dataArray.length; i++) {
                 val.dataArray[i] = (val.dataArray[i] - this.dataArray[i] + carry);
                 if (val.dataArray[i] < 0) {
                     carry = -1;
                     helper = 10;
                     val.dataArray[i] = helper + val.dataArray[i] - this.dataArray[i];
                 }
                 difference = val.dataArray[i] + difference;
             }
             difference = "-" + difference;
         } else {
             for (int i = 0; i < this.dataArray.length; i++) {
                 this.dataArray[i] = (this.dataArray[i] - val.dataArray[i] + carry);
                 if (this.dataArray[i] < 0) {
                     carry = -1;
                     helper = 10;
                     this.dataArray[i] = helper + this.dataArray[i] - val.dataArray[i];
                 }
                 difference = this.dataArray[i] + difference;
             }
         }
         if (helper > 0) {
             difference = helper + difference;
         }
         return new BrobInt(difference);
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
   *  @param  gint         BrobInt to multiply by this
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt gint ) {
       BrobInt a = new BrobInt(this.toString());
       BrobInt b = new BrobInt(gint.toString());
       BrobInt c = new BrobInt("0");
       Halver h = new Halver();
       while (! b.equals(BrobInt.ONE)) {
           a = a.addInt(a);
           b = new BrobInt(h.halve(b.toString()));
           if (b.remainder(BrobInt.TWO).equals(BrobInt.ONE)) {
               c = c.addInt(a);
           }
       }
       return c;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  gint         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  gint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt gint ) {
      return gint.subtractInt(gint.divide(this).multiply(this));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  gint  BrobInt to add to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method performs a lexicographical comparison using the java String "compareTo()" method
   *        THAT was easy.....
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareTo( BrobInt gint ) {
      return (internalValue.compareTo( gint.toString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  gint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" method above
   *        also using the java String "equals()" method -- THAT was easy, too..........
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt gint ) {
      return (internalValue.equals( gint.toString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a BrobInt given a long value passed as argument
   *  @param  value         long type number to make into a BrobInt
   *  @return BrobInt  which is the BrobInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt gi = null;
      try {
         gi = new BrobInt( new Long( value ).toString() );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n  Sorry, the value must be numeric of type long." );
      }
      return gi;
   }

   /**
   * some of my own helper methods
   */
   public void padded (BrobInt makeLength) {
       int[] newLength = new int[makeLength.dataArray.length];

       for (int i = 0; i < this.dataArray.length; i++) {
           newLength[i] = this.dataArray[i];
       }
       this.dataArray = newLength;
   }

   public boolean greaterThan( BrobInt gint ){
       if (this.sign == 1 && gint.sign == -1) {
           return true;
       } else if (this.sign == -1 && gint.sign == 1) {
           return false;
       } else if (this.toString().length() > gint.toString().length()) {
           return true;
       } else {
           for (int i = 0; i < this.toString().length(); i++) {
               if (this.toString().charAt(i) > gint.toString().charAt(i)) {
                   return true;
               } else {
                   return false;
               }
           }
       } return false;
   }


   public String toString() {
      return internalValue;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display an Array representation of this BrobInt as its bytes
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void toArray( int[] d ) {
      System.out.println( Arrays.toString( d ) );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  note:  we don't really care about these
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );

      System.exit( 0 );
   }
}
