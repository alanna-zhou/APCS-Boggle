import java.util.ArrayList;


/**
 * 
 * Removes nodes with only one child. A chain of nodes pointed to by one link
 * can be compressed into a node storing a suffix rather than a single
 * character.
 *
 * @author
 * @version Apr 28, 2017
 * @author Period: 5
 * @author Assignment: Boggle
 *
 * @author Sources: none
 */
public class CompressedTrieLexicon extends TrieLexicon
{

    /**
     * Preforms one-child compression
     */
    public void compress()
    {
        // TODO
    }


    @Override
    public void load( ArrayList<String> list )
    {
        super.load( list );
        compress();
    }

}
