/**
 * 
 * Defines a method that calculates the score of a word on a Boggle board.
 *
 * @version May 25, 2017
 * @author Assignment: Boggle
 *
 */
public class BoggleScore
{
    /**
     * Gives a score to a word found on the board depending on its length and
     * the board size.
     * 
     * @param s
     *            word
     * @param boardSize
     *            size of board
     * @return score
     */
    public int getScore( String s, int boardSize )
    {
        switch ( s.length() )
        {
            case 0:
            case 1:
            case 2:
                return 0;
            case 3:
                return boardSize <= 4 ? 1 : 0;
            case 4:
                return 1;
            case 5:
                return 2;
            case 6:
                return 3;
            case 7:
                return 5;
            default:
                return 11;
        }
    }
}
