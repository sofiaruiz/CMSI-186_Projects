public class StringStuff {
	   
	
	public static boolean containsVowel( String s ) {
	       s.toLowerCase();
	       if(s.contains("a")|| s.contains("e") || s.contains("i") || s.contains("o") || s.contains("u")){
	           return true;
	       } else {
	         return false;
	       }
	   }

	 
	   public static boolean isPalindrome( String s ) {
		   s.toLowerCase();
		   String maybePal = reverse(s);
	       if (maybePal.equals(s)) {
	           return true;
	       } else {
	           return false;
	       }
	   }


	   
	   public static String evensOnly( String s ) {
		  s = s.toLowerCase();
	      String evens = " bdfhjlnprtvxz";
	      String evensContained = "";
	      for (int i = 0; i < s.length(); i++) {
	    	    		   if (evens.contains(Character.toString(s.charAt(i)))) {
	    	    			   evensContained = evensContained + (Character.toString(s.charAt(i)));
	    	    	   }
	      }  return evensContained;
	    	    		   
	   }


	   public static String oddsOnly( String s ) {
		   s = s.toLowerCase();
		   String odds = " acegikmoqsuwy";
		   String oddsContained = "";
		      for (int i = 1; i < s.length(); i++) {
		    	      if (odds.contains(Character.toString(s.charAt(i)))) {
	    			   oddsContained = oddsContained + (Character.toString(s.charAt(i)));
	    	          }
		      }return oddsContained;

	   }

	  
	   public static String evensOnlyNoDupes( String s ) {
		   s = s.toLowerCase();
		 String word = evensOnly(s);
	     String evensContained = "";
	     for (int i = 0; i< word.length(); i++) {
	    	     if (!evensContained.contains(Character.toString(s.charAt(i)))) {
	    	    	     evensContained += Character.toString(s.charAt(i));
	    	     }
	     }
	     return evensContained;
	   }

	  
	   public static String oddsOnlyNoDupes( String s) {
		   s = s.toLowerCase();
		   String word = oddsOnly(s);
		   String oddsContained = "";
		   for (int i = 0; i< word.length(); i++) {
	    	     if (!oddsContained.contains(Character.toString(s.charAt(i)))) {
	    	    	     oddsContained += Character.toString(s.charAt(i));
	    	     }
	     }
	     return oddsContained;
	   }
	  

	  
	   public static String reverse( String s ) {
	       StringBuilder input = new StringBuilder(s);
	       input = input.reverse();
	       String backwards = input.toString();
	       return backwards.toLowerCase();
	   }

	  /**
	   * Main method to test the methods in this class
	   *
	   * @param args String array containing command line parameters
	   */
	   public static void main( String args[] ) {
	      String blah = new String( "Blah blah blah" );
	      String woof = new String( "BCDBCDBCDBCDBCD" );
	      String pal1 = new String( "a" );
	      String pal2 = new String( "ab" );
	      String pal3 = new String( "aba" );
	      String pal4 = new String( "amanaplanacanalpanama" );
	      String pal5 = new String( "abba" );
	      System.out.println( containsVowel( blah ) );
	      System.out.println( containsVowel( woof ) );
	      System.out.println( isPalindrome( pal1 ) );
	      System.out.println( isPalindrome( pal2 ) );
	      System.out.println( isPalindrome( pal3 ) );
	      System.out.println( isPalindrome( pal4 ) );
	      System.out.println( isPalindrome( pal5 ) );
	      System.out.println( "evensOnly()        returns: " + evensOnly( "REHEARSALSZ" ) );
	      System.out.println( "evensOnly()        returns: " + evensOnly( "REhearSALsz" ) );
	      System.out.println( "evensOnlyNoDupes() returns: " + evensOnlyNoDupes( "REhearSALsz" ) );
	      System.out.println( "oddsOnly()         returns: " + oddsOnly( "xylophones" ) );
	      System.out.println( "oddsOnly()         returns: " + oddsOnly( "XYloPHonES" ) );
	      System.out.println( "oddsOnlyNoDupes()  returns: " + oddsOnlyNoDupes( "XYloPHonES" ) );
	      System.out.println( "reverse()          returns: " + reverse( "REHEARSALSZ" ) );
	      
	      String sofia = new String( "Sofia" );
	      String noVowels = new String( "" );
	      String noVowels2 = new String( "bcdfghjklmnrs");
	      String pal = new String( "level" );
	      String pal0 = new String( "aha" );
	      String pal01 = new String( "sofia" );
	      String pal10 = new String( "rotor" );
	      String pal11 = new String( "noon" );
	      System.out.println( containsVowel( sofia) );
	      System.out.println( containsVowel( noVowels ) );
	      System.out.println( containsVowel( noVowels2 ) );
	      System.out.println( isPalindrome( pal ) );
	      System.out.println( isPalindrome( pal0 ) );
	      System.out.println( isPalindrome( pal01 ) );
	      System.out.println( isPalindrome( pal10 ) );
	      System.out.println( isPalindrome( pal11 ) );
	      System.out.println( "evensOnly()        returns: " + evensOnly( "sofia" ) );
	      System.out.println( "evensOnly()        returns: " + evensOnly( "isabel" ) );
	      System.out.println( "evensOnlyNoDupes() returns: " + evensOnlyNoDupes( "ALEjandro" ) );
	      System.out.println( "oddsOnly()         returns: " + oddsOnly( "sofia" ) );
	      System.out.println( "oddsOnly()         returns: " + oddsOnly( "ISABEL" ) );
	      System.out.println( "oddsOnlyNoDupes()  returns: " + oddsOnlyNoDupes( "alejandro" ) );
	      System.out.println( "reverse()          returns: " + reverse( "sofia" ) );
	      System.out.println( "reverse()          returns: " + reverse( "isabew") );
	   }

}
