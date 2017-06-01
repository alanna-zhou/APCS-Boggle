
import java.util.*;


/**
 * 
 * TrieLexicon is a special type of data structure that contains maps inside of
 * nodes
 *
 * 
 */
public class TrieLexicon implements ILexicon
{ // implements ILexicon

    /**
     * 
     * The node class contains info which contains the character or strin in the
     * node, a boolean of whether the string contained is a word, a map of nodes
     * named children, and a parent node
     *
     * 
     */
    public static class Node
    {
        String info; // initialize string

        boolean isWord; // initialize boolean

        Map<Character, Node> children; // create map with character key and node
        // stuff

        Node parent; // reference to root or parent node


        /**
         * 
         * Constructor for Node
         * 
         * @param ch
         *            character inside node
         * @param p
         *            the node that becomes parent
         *
         * 
         */
        Node( char ch, Node p )
        { // node constructor, contains a character and a node
            info = "" + ch; // the character
            isWord = false; // sets isWord to false
            children = new TreeMap<Character, Node>(); // children is a treemap,
                                                       // key is a character and
                                                       // map contains nodes
            parent = p; // parent holds reference to the other node
        }


        /**
         * 
         * Determines whether the info in the node is a word or not
         *
         * 
         */
        public boolean isWord()
        {
            return isWord;
        }
    }

    protected Node myRoot; // root of entire trie

    protected int mySize;


    /**
     * Constructor of TrieLexicon that creates a new root node and initializes
     * size to 0
     */
    public TrieLexicon()
    { // actual constructor
        myRoot = new Node( 'x', null ); // creates a new node with character set
        // to x and pointing to null
        mySize = 0; // lol setting size to 0 when it's already 0, nice.
    }


    /**
     * 
     * Returns size of arraylist
     *
     * 
     */
    public int size()
    { // returns size of some strange arraylist
        return mySize;
    }


    /**
     * 
     * Loads the list and adds it to the trie
     * 
     * @param list
     *            with String to add to the trielexicon
     *
     * 
     */
    public void load( ArrayList<String> list )
    { // traverses arraylist and builds it into the trielexicon
        for ( String s : list )
            add( s );
    }


    /**
     * 
     * Adds a string to the trielexicon
     * 
     * @param s
     *            string to add
     * @return whether it is already in set or whether they marked it as a word
     *
     * 
     */
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


    /**
     * 
     * Iterates through trielexicon
     *
     * 
     */
    public Iterator<String> iterator()
    {
        ArrayList<String> list = new ArrayList<String>(); // random string
                                                          // Arraylist

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


    /**
     * 
     * Fills up data structure recursively
     * 
     * @param root
     *            starting root to fillUp
     * @param list
     *            list of strings
     * @param str
     *            the stringbuilder used to add strings to trielexicon
     *
     * 
     */
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


    /**
     * 
     * Loads the words scanned in and adds them to list
     * 
     * @param s
     *            scanner that scans in the words
     *
     * 
     */
    public void load( Scanner s )
    {
        while ( s.hasNext() )
        {
            add( s.next() );
        }
    }


    /**
     * 
     * Returns the WordStatus of the string
     * 
     * @param s
     *            isthe stringbuilder that contains the string to check the
     *            status of
     *
     * 
     */
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


    /**
     * 
     * Counts one-day count of nodes from the root
     * 
     * @return count of the nodes
     *
     * 
     */
    public int oneWayCount()
    {
        return oneWay( myRoot );
    }


    /**
     * 
     * Returns node count in trielexicon
     * 
     * @return counts of the nodes
     *
     * 
     */
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


    /**
     * 
     * Returns count of node from root
     * 
     * @return count of node
     *
     * 
     */
    public int nodeCount()
    {
        return doCount( myRoot );
    }


    /**
     * 
     * Counts children from root of trielexicon
     * 
     * @param root
     *            the root to start counting from
     * 
     */
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

}
