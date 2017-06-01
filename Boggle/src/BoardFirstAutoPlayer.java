import java.util.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Finds all words quickly, facilitates a game of Boggle by finding every
 * possible word on a given Boggle board. Uses lexicons from the previous
 * hierarchy. (Task #3)
 *
 * @author Anvitha Aluri
 * @version Apr 27, 2017
 * @author Period: 5
 * @author Assignment: Boggle
 *
 * @author Sources: none
 */
public class BoardFirstAutoPlayer extends AbstractAutoPlayer {

	/**
	 * Uses recursive backtracking search to find all words on a Boggle board by
	 * calling helper() and going through each cell on the board
	 * 
	 * @param board
	 * @param lex
	 * @param minLength
	 */
	public void findAllValidWords(BoggleBoard board, ILexicon lex, int minLength) {
		myWords.clear();
		List<BoardCell> listOfCells = new ArrayList<BoardCell>();
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.size(); j++) {
				// go through helper for every cell on the board
				helper(board, lex, minLength, i, j, listOfCells, s);
			}
		}
	}

	/**
	 * findAllValidWords's helper method. Finds all the words starting at a
	 * specific given row and column and a given string builder. It searches all
	 * the cells around it for viable words and adds them all to the myWords
	 * treeSet defined in the AbstractPlayer class.
	 * 
	 * @param board
	 * @param lex
	 * @param minLength
	 * @param r
	 * @param c
	 * @param listOfCells
	 * @param s
	 */
	private void helper(BoggleBoard board, ILexicon lex, int minLength, int r, int c, List<BoardCell> listOfCells,
			StringBuilder s) {

		// don't continue if r/c out of bounds
		if (r < 0 || r >= board.size() || c < 0 || c >= board.size()) {
			return;
		}

		BoardCell cell = new BoardCell(r, c);
		// don't continue if computer returns to the same cell
		if (listOfCells.contains(cell)) {
			return;
		}

		// a letter at (r,c)
		String letter = board.getFace(r, c);
		// add this cell to the list of cells already passed through
		listOfCells.add(cell);
		// add letter to the end of the string built so far
		s.append(letter);

		// if the string is either a word or a prefix, continue the search
		if (lex.wordStatus(s).equals(LexStatus.PREFIX) || lex.wordStatus(s).equals(LexStatus.WORD)) {
			// if the word/prefix is a correct word, add it to the list of
			// correct words
			if (lex.wordStatus(s).equals(LexStatus.WORD) && (s.length() >= minLength)) {
				add(s.toString());
			}

			int[] rdelta = { -1, -1, -1, 0, 0, 1, 1, 1 };
			int[] cdelta = { -1, 0, 1, -1, 1, -1, 0, 1 };
			// traverse through the surrounding cells to see if they form words
			// with the prefix
			for (int k = 0; k < rdelta.length; k++) {
				helper(board, lex, minLength, r + rdelta[k], c + cdelta[k], listOfCells, s);
			}
		}

		listOfCells.remove(cell);
		// check for qu
		if (letter.length() > 1) {
			s.setLength(s.length() - 2);
		}
		if (letter.length() == 1) {
			s.deleteCharAt(s.length() - 1);
		}
	}
}
