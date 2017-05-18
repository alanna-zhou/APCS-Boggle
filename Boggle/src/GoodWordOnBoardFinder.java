import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 
 * Finds a word on a Boggle board. Will be used to determine where a word
 * appears on a board when a player plays the game. (Task #1)
 * 
 * JUnit Tests for this class are in TestWordFinder.
 *
 * @author Alanna Zhou
 * @version Apr 28, 2017
 * @author Period: 5
 * @author Assignment: Boggle
 *
 * @author Sources: none
 */
public class GoodWordOnBoardFinder implements IWordOnBoardFinder
{

    private List<BoardCell> list;

    private BoggleBoard board;

    private String word;


    @Override
    /**
     * Uses recursive backtracking search to find a word on a Boggle board by
     * calling helper()
     * 
     * @return a list of BoardCell objects that match up with the word
     */
    public List<BoardCell> cellsForWord( BoggleBoard board, String word )
    {
        list = new ArrayList<BoardCell>();
        this.board = board;
        this.word = word.toLowerCase();
        int index = 0;
        for ( int r = 0; r < board.size(); r++ )
        {
            for ( int c = 0; c < board.size(); c++ )
            {
                // helper is recursive, so it'll only return true completely if
                // the entire word has been matched
                if ( helper( r, c, index ) )
                {
                    Collections.reverse( list );
                    return list;
                }
            }
        }
        return list;
    }


    /**
     * 
     * cellsForWord's recursive, helper method. Finds whether or not a word
     * occurs on a board.
     * 
     * @param board
     * @param r
     * @param c
     * @param list
     * @param index
     *            indicates which character in the string is the one being
     *            tentatively matched to the (row, column) board cell. The first
     *            time the helper method is called this parameter is zero
     *            (indicating the first character of the string should be
     *            matched).
     * @return true if the letter at the specific index of the word matches the
     *         row and column of the BoardCell
     */
    public boolean helper( int r, int c, int index )
    {

        if ( index >= word.length() )
        {
            return true;
        }
        // don't continue if row/col out of bounds
        if ( r < 0 || r >= board.size() || c < 0 || c >= board.size() )
        {
            return false;
        }
        // don't continue if cell is already in the list
        if ( list.contains( new BoardCell( r, c ) ) )
        {
            return false;
        }
        // if cell at board matches the char at the specified index of string
        if ( board.getFace( r, c ).equalsIgnoreCase(
            ( Character.toString( word.charAt( index ) ) ) ) )
        {
            index++;
            if ( recursiveIterator( r, c, index ) )
            {
                return true;
            }
            list.clear();
            return false;
        }
        else if ( index + 2 <= word.length() && board.getFace( r, c )
            .equalsIgnoreCase( word.substring( index, index + 2 ) ) )
        {
            index += 2;
            if ( recursiveIterator( r, c, index ) )
            {
                return true;
            }
            list.clear();
            return false;
        }
        list.clear();
        return false;
    }


    public boolean recursiveIterator( int r, int c, int index )
    {
        int[] rdelta = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] cdelta = { -1, 0, 1, -1, 1, -1, 0, 1 };

        for ( int i = 0; i < rdelta.length; i++ )
        {
            if ( helper( r + rdelta[i], c + cdelta[i], index ) )
            {
                list.add( new BoardCell( r, c ) );
                return true;
            }
        }
        return false;
    }


//    public static String boardCellToString( BoardCell b )
//    {
//        return "board cell at (" + b.row + ", " + b.col + ")";
//    }
//
//
//    public static void main( String[] args )
//    {
//        String[] faces = { "QU", "B", "P", "H", "D", "A", "H", "M", "H", "N",
//            "I", "Y", "N", "N", "R", "Y" };
//        BoggleBoard board = new BoggleBoard( faces );
//        System.out.println( "board: \n" + board );
//
//        GoodWordOnBoardFinder finder = new GoodWordOnBoardFinder();
//        List<BoardCell> list = finder.cellsForWord( board, "qubph" );
//        System.out.println(
//            "\nHere is the list of board cells found by GoodWordOnBoardFinder:" );
//        for ( BoardCell b : list )
//        {
//
//            System.out.println(
//                boardCellToString( b ) + ": " + board.getFace( b.row, b.col ) );
//        }
//
//    }

}
