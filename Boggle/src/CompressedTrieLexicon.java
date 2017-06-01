import java.util.ArrayList;
import java.util.Scanner;


/**
 * 
 * Removes nodes with only one child. A chain of nodes pointed to by one link
 * can be compressed into a node storing a suffix rather than a single
 * character.
 *
 * @author Jessica Peng
 * @version Apr 28, 2017
 * @author Period: 5
 * @author Assignment: Boggle
 *
 * @author Sources: none
 */
public class CompressedTrieLexicon extends TrieLexicon
{
    private ArrayList<Node> container = new ArrayList<Node>();


    /**
     * Performs one-child compression
     */
    public void compress()
    {
        removeIndividual( myRoot );
        for ( int i = 0; i < container.size(); i++ )
        {
            recursivelyCompress( container.get( i ) );
        }

    }


    public void removeIndividual( Node n )
    {
        // root
        if ( n.children.isEmpty() )
        { // if node's children is empty, add node to the arraylist and return
            container.add( n );
            return;
        }
        for ( Character child : n.children.keySet() ) // keep iterating
                                                      // through map and
                                                      // adding the nodes
                                                      // that are not
                                                      // empty
        {
            Node nextNode = n.children.get( child );
            removeIndividual( nextNode );
        }
    }


    private void recursivelyCompress( Node n )
    {
        if ( n.parent.children.size() == 1 && n.parent.isWord ) // check
                                                                // if
                                                                // node's
                                                                // parent
                                                                // only
                                                                // has
                                                                // 1
                                                                // child
                                                                // (itself)
                                                                // and
                                                                // that
                                                                // the
                                                                // parent
                                                                // is
                                                                // not
                                                                // a
                                                                // word
        {
            // consolidate the information of children
            n.parent.info += n.info;
            n.parent.children.clear();
            // make it clear that the parent is a word after consolidation
            n.parent.isWord = true;
            recursivelyCompress( n.parent );
        }
    }


    @Override
    public void load( ArrayList<String> list )
    {
        super.load( list );
        compress();
    }

    // public LexStatus wordStatus( String s )
    // {
    // //fix
    // }

}