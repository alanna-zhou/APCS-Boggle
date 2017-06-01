import java.util.SortedSet;
import java.util.TreeSet;


/**
 * AutoPlayer that can't find any words. Useful for trying out a game before a
 * good implementation is written.
 * 
 * @author Owen Astrachan
 *
 */
public class BadAutoPlayer extends AbstractAutoPlayer
{

    /**
     * Empty constructor
     */
    public BadAutoPlayer()
    {

    }


    /**
     * Does nothing, hence it is a Bad Auto Player because it temporarily just
     * allows the project to compile until the actual computer player class is
     * written (FirstBoardAutoPlayer).
     */
    public void findAllValidWords(
        BoggleBoard board,
        ILexicon lex,
        int minLength )
    {
        // do nothing!!!
    }

}
