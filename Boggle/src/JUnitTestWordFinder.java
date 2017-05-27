import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;


/**
 * JUnit Tests for the GoodWordOnBoardFinder. Tests if it correctly finds words
 * on the board and correctly returns the list of board cells that are
 * associated with the letters of the word.
 * 
 */
public class JUnitTestWordFinder
{

    private IBoardMaker myMaker;

    private IWordOnBoardFinder myFinder;


    /**
     * Creates the board needed to find the words on.
     * 
     */
    public class LocalBoardMaker implements IBoardMaker
    {

        private String[] good = { "atru", "sean", "niot", "bden" };


        public BoggleBoard makeBoard( int rows )
        {
            String[] faces = new String[good.length * good.length];
            int count = 0;
            for ( String s : good )
            {
                for ( int k = 0; k < s.length(); k++ )
                    faces[count++] = "" + s.charAt( k );
            }
            return new BoggleBoard( faces );
        }
    }


    /**
     * Initializes the board maker and finder constructors
     */
    @Before
    public void setUp()
    {
        myMaker = new LocalBoardMaker();
        myFinder = new GoodWordOnBoardFinder();
    }


    /**
     * To string method for the list of BoardCell objects in the list.
     * 
     * @param board
     *            boggle board
     * @param list
     *            list of BoardCell objects
     * @return String representation of the list
     */
    private String getWord( BoggleBoard board, List<BoardCell> list )
    {
        String word = "";
        for ( BoardCell cell : list )
        {
            word += board.getFace( cell.row, cell.col );
        }
        return word;
    }


    /**
     * GoodWordOnBoardFinder is expected to find these words in the corner of
     * the board.
     */
    @Test
    public void testGoodCorners()
    {
        String[] cornerWords = { "ate", "noted", "net", "urn", "bind", "aside",
            "noise" };
        BoggleBoard board = myMaker.makeBoard( 4 );
        for ( String s : cornerWords )
        {
            List<BoardCell> list = myFinder.cellsForWord( board, s );
            String word = getWord( board, list );
            assertEquals( "fail for " + s, s, word );
        }
    }


    /**
     * GoodWordOnBoardFinder is expected to find these words that are not in the
     * corner of the board.
     */
    @Test
    public void testNonCorners()
    {
        String[] words = { "reinstate", "resident", "insert", "trains",
            "treated" };
        BoggleBoard board = myMaker.makeBoard( 4 );

        for ( String s : words )
        {
            List<BoardCell> list = myFinder.cellsForWord( board, s );
            String word = getWord( board, list );
            assertEquals( "fail for " + s, s, word );
        }
    }


    /**
     * GoodWordOnBoardFinder is expected to return an empty list because these
     * should not be words found.
     */
    @Test
    public void testBadCorners()
    {
        String[] cornerWords = { "notary", "urine", "need", "diners", "astride",
            "nosier" };
        BoggleBoard board = myMaker.makeBoard( 4 );
        for ( String s : cornerWords )
        {
            List<BoardCell> list = myFinder.cellsForWord( board, s );
            String word = getWord( board, list );
            assertNotSame( "fail for " + s, s, word );
        }
    }


    /**
     * GoodWordOnBoardFinder is expected to return false for duplicate cells.
     */
    @Test
    public void testDuplicates()
    {
        String[] faces = { "t", "o", "h", "o", "e", "t", "p", "n", "o", "a",
            "e", "e", "p", "t", "y", "s" };
        BoggleBoard board = new BoggleBoard( faces );

        List<BoardCell> list = myFinder.cellsForWord( board, "tet" );
        List<BoardCell> correctList = new ArrayList<BoardCell>();
        correctList.add( new BoardCell( 0, 0 ) );
        correctList.add( new BoardCell( 0, 1 ) );
        correctList.add( new BoardCell( 1, 1 ) );
        assertNotSame( "fail for tet", correctList, list );

        List<BoardCell> list1 = myFinder.cellsForWord( board, "ene" );
        List<BoardCell> correctList1 = new ArrayList<BoardCell>();
        correctList.add( new BoardCell( 2, 2 ) );
        correctList.add( new BoardCell( 1, 3 ) );
        correctList.add( new BoardCell( 2, 3 ) );
        assertNotSame( "fail for ene", correctList1, list1 );

    }

}