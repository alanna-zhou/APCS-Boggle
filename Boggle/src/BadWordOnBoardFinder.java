import java.util.List;
import java.util.ArrayList;


/**
 * Cannot find any words on a board. Useful for debugging a game before a good
 * implementation is written.
 * 
 * @author Owen Astrachan
 *
 */
public class BadWordOnBoardFinder implements IWordOnBoardFinder
{

    private ArrayList<BoardCell> myEmptyList = new ArrayList<BoardCell>();


    /**
     * Returns empty list of BoardCell objects for any query so no words will be
     * found.
     */
    public List<BoardCell> cellsForWord( BoggleBoard board, String word )
    {
        return myEmptyList;
    }

}
