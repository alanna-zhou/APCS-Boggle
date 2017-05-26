
import java.util.*;


/**
 * 
 * TODO Write a one-sentence summary of your class here. TODO Follow it with
 * additional details about its purpose, what abstraction it represents, and how
 * to use it.
 *
 * 
 */
public class TrieLexicon implements ILexicon
{ // implements ILexicon

    public static class Node
    {
        String info; // initialize string

        boolean isWord; // initialize boolean

        Map<Character, Node> children; // create map with character key and node
        // stuff

        Node parent; // reference to root or parent node


        Node( char ch, Node p )
        { // node constructor, contains a character and a node
            info = "" + ch; // the character
            isWord = false; // sets isWord to false
            children = new TreeMap<Character, Node>(); // children is a treemap,
                                                       // key is a character and
                                                       // map contains nodes
            parent = p; // parent holds reference to the other node
        }


        public boolean isWord()
        {
            return isWord;
        }
    }

    protected Node myRoot; // root of entire trie

    protected int mySize;


    public TrieLexicon()
    { // actual constructor
        myRoot = new Node( 'x', null ); // creates a new node with character set
        // to x and pointing to null
        mySize = 0; // lol setting size to 0 when it's already 0, nice.
    }


    public int size()
    { // returns size of some strange arraylist
        return mySize;
    }


    public void load( ArrayList<String> list )
    { // traverses arraylist and builds it into the trielexicon
        for ( String s : list )
            add( s );
    }


    public boolean add( String s )
    {
        Node t = myRoot; // root of trielexicon

        for ( int k = 0; k < s.length(); k++ ) // going through every element in
                                               // string
        {

            char ch = s.charAt( k ); // gets character
            Node child = t.children.get( ch ); // goes to root --> goes into
                                               // children created --> gives the
                                               // character as a key to search
                                               // for node and labels it as
                                               // child
            if ( child == null ) // if node with ch key not in map
            {
                child = new Node( ch, t ); // create a new one
                t.children.put( ch, child ); // and put it where indicated by
                                             // key
            }
            t = child; // uh not sure why t is set as child node
        }

        if ( !t.isWord ) // if not a word, mark it as a word
        {
            t.isWord = true; // walked down path, mark this as a word
            mySize++; // increase size of trie
            return true;
        }
        return false; // already in set
    }


    public Iterator<String> iterator()
    {
        ArrayList<String> list = new ArrayList<String>(); // random string
                                                          // Arraylist (why
                                                          // string and not
                                                          // nodes?)
        StringBuilder str = new StringBuilder(); // constructs "empty" 16
                                                 // character string
        for ( Node n : myRoot.children.values() )
        {
            str.append( n.info ); // adds the string values inside children to
                                  // the stringbuilder, null if it is empty
            fillUp( n, list, str );
            str.deleteCharAt( str.length() - 1 );
        }
        System.out.println( list.iterator() );
        return list.iterator();
    }


    protected void fillUp(
        Node root,
        ArrayList<String> list,
        StringBuilder str )
    {
        if ( root.isWord ) // for root sent, if it is a word, add it to the
                           // stringbuilder
        {
            list.add( str.toString() );
        }
        for ( Node n : root.children.values() ) // ...how does the children have
                                                // children too :'( this is so
                                                // recursive sigh.... but I
                                                // guess so
        {
            str.append( n.info );
            fillUp( n, list, str ); // recursive call
            str.delete( str.length() - n.info.length(), str.length() ); // basically
                                                                        // get
                                                                        // rid
                                                                        // of
                                                                        // the
                                                                        // character
                                                                        // at
                                                                        // end
                                                                        // of
                                                                        // string
                                                                        // builder

        }
    }


    public void load( Scanner s )
    {
        while ( s.hasNext() )
        {
            add( s.next() );
        }
    }


    public LexStatus wordStatus( StringBuilder s )
    {
        Node t = myRoot;
        for ( int k = 0; k < s.length(); k++ )
        {
            char ch = s.charAt( k );
            t = t.children.get( ch );
            if ( t == null )
                return LexStatus.NOT_WORD; // no path below? done
        }
        return t.isWord ? LexStatus.WORD : LexStatus.PREFIX;
    }


    public LexStatus wordStatus( String s )
    {
        return wordStatus( new StringBuilder( s ) );
    }


    public int oneWayCount()
    {
        return oneWay( myRoot );
    }


    protected int oneWay( Node root )
    {
        int count = 0;
        if ( root == null )
            return 0;
        if ( root.children.keySet().size() == 1 )
            count = 1;
        for ( Node n : root.children.values() )
        {
            count += oneWay( n );
        }
        return count;
    }


    public int nodeCount()
    {
        return doCount( myRoot );
    }


    protected int doCount( Node root )
    {
        int count = 1; // count this node
        if ( root == null )
            return 0;
        for ( Node n : root.children.values() )
        {
            count += doCount( n );
        }
        return count;
    }


    public static void main( String[] args )
    {
        System.out.println( "hello" );
    }

}
