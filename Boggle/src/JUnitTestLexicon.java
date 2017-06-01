
import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;


/**
 * 
 * Tests any of the Lexicon classes by checking if different types of words are
 * able to be confirmed by the specified Lexicon class.
 *
 */
public class JUnitTestLexicon
{

    private ArrayList<String> myWords;

    private ArrayList<String> myPrefixes;

    private ArrayList<String> myNonWords;

    private ILexicon myLexicon;


    /**
     * Constructs the lexicon class to be tested.
     * 
     * @return lexicon object
     */
    public ILexicon makeLexicon()
    {
        return new BinarySearchLexicon();
    }


    /**
     * Sets up the words and lexicon to be tested with.
     */
    @Before
    public void setUp()
    {
        String[] words = { "apple", "berry", "cherry", "date", "fig", "melon",
            "orange", "pineapple", "blueberry", "cherry-pie", "blueberry-pie",
            "apple-pie", "pineapple-upside-down-cake", "watermelon" };
        String[] prefixes = { "pine", "blue", "water", "melo", "fi", "cherr" };
        String[] nonWords = { "aardvark", "figgy", "melon-ball", "dater",
            "xylophone", "oranges", "goofy", "mickey" };

        myWords = new ArrayList<String>( Arrays.asList( words ) );
        myPrefixes = new ArrayList<String>( Arrays.asList( prefixes ) );
        myNonWords = new ArrayList<String>( Arrays.asList( nonWords ) );
        myLexicon = makeLexicon();
        myLexicon.load( myWords );
    }


    /**
     * Tests if the actual number of words and the words found in the lexicon
     * are the same.
     */
    @Test
    public void wordTest()
    {
        assertEquals( "size of lexicon failed",
            myWords.size(),
            myLexicon.size() );
        for ( String s : myWords )
        {
            LexStatus stat = myLexicon.wordStatus( s );
            assertEquals( "fail for word: " + s, LexStatus.WORD, stat );
        }
    }


    /**
     * Tests if prefix words are able to be found in the lexicon.
     */
    @Test
    public void prefixTest()
    {
        for ( String s : myPrefixes )
        {
            LexStatus stat = myLexicon.wordStatus( s );
            assertEquals( "fail for prefix: " + s, LexStatus.PREFIX, stat );
        }
    }


    /**
     * Tests if words that are not supposed to be found in the lexicon are
     * found.
     */
    @Test
    public void nonWordTest()
    {
        for ( String s : myNonWords )
        {
            LexStatus stat = myLexicon.wordStatus( s );
            assertEquals( "fail for non word: " + s, LexStatus.NOT_WORD, stat );
        }
    }
}
