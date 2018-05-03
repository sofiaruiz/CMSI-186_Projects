/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangemaker.java
 * Purpose    :  program to find the optimal way to make change with any set of coin denominations
 * @author    :  Sofia Ruiz
 * Date       :  04-24-2018
 * Description:  Program takes in two parameters, an integer array of coin denominations and a target amount
 *               of change. It uses dynamic programming and memoization to find the optimal way to make the
 *               targeted amount of change.
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
import java.util.Arrays;

/**
 *This class uses dynamic programming to find the optimal way
 *to make changemaker for given coin denominations.
*/

public class DynamicChangeMaker{

    private static int[] denominations = null;
    private static Tuple denominationTuple = null;
    private static Tuple[][] memoTable = null;

    /**
     *DynamicChangemaker() is constructed with Super().
    */
    public DynamicChangeMaker(){
        super();
    }

    /**
     * method returns the optimal way to make change with given coin denominations\
     * @param denom integer array of denominations one is trying to find an optimal solution for
     * @param change integer that tells you the number of chage you are trying to make
     * @return a tuple with the number of each coin needed to make the target change
    */
    public static Tuple makeChangeWithDynamicProgramming(int [] denom, int change){
        int size = denom.length;
        if(change <= 0){
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < size; i++){
            for (int j = i + 1; j < size; j++){
                if (denom[i] == denom[j] || denom[i] <= 0 || denom[j] <= 0 || denom.length < 1){
                    throw new IllegalArgumentException();
                }
            }
        }

        memoTable = new Tuple[size][change + 1];
        for (int row = 0; row < size; row++){
            for (int col = 0; col < change + 1; col++){
                if (col == 0){
                    memoTable[row][col] = new Tuple(size);
                } else {
                    if (col < denom[row]){
                        memoTable[row][col] = new Tuple(new int[0]);
                        if (col - denom[row] >= 0){
                            if (!(memoTable[row][col-denom[row]].isImpossible())){
                                memoTable[row][col].add(memoTable[row][col - denom[row]]);
                            }
                        }
                        if (row != 0){
                            if (!(memoTable[row - 1][col].isImpossible())){
                                if(memoTable[row][col].isImpossible() || memoTable[row - 1][col].total() < memoTable[row][col].total()){
                                    memoTable[row][col] = memoTable[row - 1][col];
                                }
                            }
                        }
                    } else {
                        memoTable[row][col] = new Tuple(size);
                        memoTable[row][col].setElement(row, 1);
                        if (col - denom[row] >= 0){
                            if(memoTable[row][col - denom[row]].isImpossible()){
                                memoTable[row][col] = new Tuple(new int[0]);
                            } else {
                                memoTable[row][col] = memoTable[row][col].add(memoTable[row][col - denom[row]]);
                            }
                        }
                        if (row != 0){
                            if(!(memoTable[row - 1][col].isImpossible())){
                                if(memoTable[row][col].isImpossible() || memoTable[row - 1][col].total() < memoTable[row][col].total()){
                                    memoTable[row][col] = memoTable[row - 1][col];
                                }
                            }
                        }
                    }
                }
            }
        }
        return memoTable[size -1][change];
    }

    /**
        *  main method calls DynamicChangeMaker program from the command line.
        * @param args arguments passed in from the command line.
        * Last arg is the value of change you are trying to.
        * The other args are the coins you want to find an optimal solution for.
        */

    public static void main(String args[]){
        if ( args.length >= 2 ){
         denominations = new int[args.length - 1];
         for ( int i = 0; i < args.length - 1; i++ ){
            denominations[i] = Integer.parseInt(args[i]);
         }
         System.out.println( "Minimum coins required is " + makeChangeWithDynamicProgramming(denominations, Integer.parseInt(args[args.length - 1]) ));
      } else {
         System.out.println( "\n   Sorry, the arguments you entered are invalid." );
         System.out.println( "   The first argument must be an integer array of arguments with a value greater than or equal to 0\n" );
         System.out.println( "   For the second argument enter the change you want to make (must be a positive number)");
      }
   }
}
