import java.util.*;


/**
 * Documentation:
 * http://www.cs.duke.edu/csed/java/jdk1.5/docs/api/java/util/Collections.html#binarySearch(java.util.List,java.lang.Object)
 * 
 *
 * @author
 * @version Apr 28, 2017
 * @author Period: 5
 * @author Assignment: Boggle
 *
 * @author Sources: none
 */
public class BinarySearchLexicon implements ILexicon
{

    private ArrayList<String> myWords;


    public BinarySearchLexicon()
    {
        myWords = new ArrayList<String>();
    }


    public void load( Scanner s )
    {
        myWords.clear();
        while ( s.hasNext() )
        {
            myWords.add( s.next().toLowerCase() );
        }
        Collections.sort( myWords );
    }


    public void load( ArrayList<String> list )
    {
        myWords.clear();
        myWords.addAll( list );
        Collections.sort( myWords );
    }


    public LexStatus wordStatus( StringBuilder s )
    {
        return wordStatus( s.toString() );
    }


    public LexStatus wordStatus( String s )
    {
        // PLEASE CHECK CODE BELOW
        // PLEASE CHECK CODE BELOW
        // PLEASE CHECK CODE BELOW
        // PLEASE CHECK CODE BELOW

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


    public Iterator<String> iterator()
    {
        return myWords.iterator();
    }


    public int size()
    {
        return myWords.size();
    }

}
