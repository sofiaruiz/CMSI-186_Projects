public class CalendarStuff {

	  /**
	   * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
	   */
	   private static final int SUNDAY    = 0;
	   private static final int MONDAY    = SUNDAY    + 1;
	   private static final int TUESDAY   = MONDAY + 1;
	   private static final int WEDNESDAY = TUESDAY+ 1;
	   private static final int THURSDAY  = WEDNESDAY + 1;
	   private static final int FRIDAY    = THURSDAY + 1;
	   private static final int SATURDAY  = FRIDAY + 1;

	  /**
	   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
	   */
	   private static final int JANUARY    = 0;
	   private static final int FEBRUARY   = JANUARY   + 1;
	   private static final int MARCH      = FEBRUARY + 1;
	   private static final int APRIL      = MARCH + 1;
	   private static final int MAY        = APRIL + 1;
	   private static final int JUNE       = MAY + 1;
	   private static final int JULY       = JUNE + 1;
	   private static final int AUGUST     = JULY + 1;
	   private static final int SEPTEMBER  = AUGUST + 1;
	   private static final int OCTOBER    = SEPTEMBER + 1;
	   private static final int NOVEMBER   = OCTOBER + 1;
	   private static final int DECEMBER   = NOVEMBER + 1;


	  /**
	   * An array containing the number of days in each month
	   */
	   private static int[]    days        = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	  /**
	   * The constructor for the class
	   */
	   public CalendarStuffEmpty() {
	      System.out.println( "Constructor called..." );  // replace this with the actual code
	   }

	  /**
	   * A method to determine if the year argument is a leap year or not<br />
	   */
	   public static boolean isLeapYear( long year ) {
		  if ( year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			  return true;
		  } else {
			  return false;
		  }
	   }

	  /**
	   * A method to calculate the days in a month, including leap years
	   */
	   public static long daysInMonth( long month, long year ) {
	       month++;
		   if (month == 4 || month == 6 || month == 9 || month == 11) {
	    	      return 30;
	      } else if (month ==2) {
	    	      if(isLeapYear(year)) {
	    	    	      return 29;
	    	      } else {
	    	    	      return 28;
	    	      }
	      } else {
	    	      return 31;
	      }
	   }

	  /**
	   * A method to determine if two dates are exactly equal
	   */
	   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
	      if (month1 == month2 && day1 == day2 && year1 == year2) {
	          return true;
	      } else {
	    	      return false;
	      }
	   }

	  /**
	   * A method to compare the ordering of two dates
	   */
	   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
	      if (dateEquals(month1, day1, year1, month2, day2, year2)) {
	    	      return 0;
	      } else if ( month1 < month2 || day1 < day2 || year1 < year2) {
	    	      return -1;
	      } else {
	    	      return +1;
	      }
	   }

	  /**
	   * A method to return whether a date is a valid date
	   */
	   public static boolean isValidDate( long month, long day, long year ) {
	      month++;
	      if (month > 0 && month <= 12 && day <= daysInMonth(month, year) && day > 0 && year > 0)  {
	    	       return true;
	      } else {
	    	  return false;
	      }
	   }

	  /**
	   * A method to return a string version of the month name
	   */
	   public static String toMonthString( int month ) {
	      switch( month - 1 ) {
	         default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
	      }
	   }

	  /**
	   * A method to return a string version of the day of the week name
	   */
	   public static String toDayOfWeekString( int day ) {
	      switch( day - 1 ) {
	         default       : throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
	      }
	   }

	  /**
	   * A method to return a count of the total number of days between two valid dates
	   */
	   public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
	      long dayCount = 0;
	      if (year1 > year2 || (year1 == year2 & month1 > month2) || (year1 == year2 & month1 == month2 & day1 > day2)) {
	            return daysBetween(day2, month2, year2, day1, month1, year1);
	      }
	      while (year1 != year2 || month1 != month2 || day1 != day2) {
	            day1++;
	            dayCount++;
	            if (day1 > daysInMonth(month1, year1)) {
	                month1++;
	                day1 = 1;
	            }
	            if (month1 > 12) {
	                year1++;
	                month1 = 1;
	            }
	        }

	        return dayCount;
	  }

}
