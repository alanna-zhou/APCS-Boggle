import java.util.*;


/**
 * Basic computer player. Generates all words on a board by checking if the word
 * is also in a lexicon.
 * 
 */
public class LexiconFirstAutoPlayer extends AbstractAutoPlayer
{

    private IWordOnBoardFinder myFinder;


    public LexiconFirstAutoPlayer()
    {
        myFinder = new GoodWordOnBoardFinder();
    }


    @Override
    /**
     * Finds all valid words by searching through a list of words (lexicon) and
     * confirming if the GoodWordOnBoardFinder finder can find the word on the
     * board.
     */
    public void findAllValidWords(
        BoggleBoard board,
        ILexicon lex,
        int minLength )
    {

        clear();
        for ( String word : lex )
        {
            if ( word.length() < minLength )
                continue;
            List<BoardCell> list = myFinder.cellsForWord( board, word );
            if ( list.size() > 0 )
            {
                // found word
                add( word );
            }
        }
    }

}
