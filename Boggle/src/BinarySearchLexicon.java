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

        // You need to make this code use Binary Search

        return LexStatus.NOT_WORD;
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
