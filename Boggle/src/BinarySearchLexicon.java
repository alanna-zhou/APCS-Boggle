import java.util.*;


/**
 * TODO add comments
 * 
 * @author Jessica Peng
 * @version Apr 28, 2017
 * @author Period: 5
 * @author Assignment: Boggle
 *
 * @author Sources: none
 */
public class BinarySearchLexicon implements ILexicon
{

    private ArrayList<String> myWords;


    /**
     * TODO
     */
    public BinarySearchLexicon()
    {
        myWords = new ArrayList<String>();
    }


    /**
     * TODO
     */
    public void load( Scanner s )
    {
        myWords.clear();
        while ( s.hasNext() )
        {
            myWords.add( s.next().toLowerCase() );
        }
        Collections.sort( myWords );
    }


    /**
     * TODO
     */
    public void load( ArrayList<String> list )
    {
        myWords.clear();
        myWords.addAll( list );
        Collections.sort( myWords );
    }


    /**
     * TODO
     */
    public LexStatus wordStatus( StringBuilder s )
    {
        return wordStatus( s.toString() );
    }


    /**
     * TODO
     */
    public LexStatus wordStatus( String s )
    {

        int i = Collections.binarySearch( myWords, s );
        if ( i >= 0 )
        {
            return LexStatus.WORD;
        }
        else if ( myWords.get( ( i * -1 ) - 1 ).startsWith( s ) ) // not sure if
                                                                  // this is OK
        {
            return LexStatus.PREFIX;
        }
        else
        {
            return LexStatus.NOT_WORD;
        }
    }


    /**
     * Returns an iterator that can traverse through the myWords Array list.
     */
    public Iterator<String> iterator()
    {
        return myWords.iterator();
    }


    /**
     * Returns the number of words found by the lexicon.
     */
    public int size()
    {
        return myWords.size();
    }

}
