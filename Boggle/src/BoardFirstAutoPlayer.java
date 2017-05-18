
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * 
 * Finds all words quickly, facilitates a game of Boggle by finding every
 * possible word on a given Boggle board. Uses lexicons from the previous
 * hierarchy. (Task #3)
 *
 * @author Anvitha
 * @version Apr 27, 2017
 * @author Period: 5
 * @author Assignment: Boggle
 *
 * @author Sources: none
 */
public class BoardFirstAutoPlayer extends AbstractAutoPlayer {

	@Override
	public void findAllValidWords(BoggleBoard board, ILexicon lex, int minLength) {
		StringBuilder str = new StringBuilder();
		List<BoardCell> list = new ArrayList<BoardCell>();
		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.size(); j++) {
				helper(board, lex, minLength, list, i, j, str);
			}
		}
	}

	public void helper(BoggleBoard board, ILexicon lex, int minLength, List<BoardCell> listOfCells, int r, int c,
			StringBuilder s) {

		// don't continue if row/col out of bounds
		if (r < 0 || r >= board.size() || c < 0 || c >= board.size()) {
			return;
		}

		BoardCell cell = new BoardCell(r, c);

		// don't continue if computer returns to the same cell
		if (listOfCells.contains(cell)) {
			return;
		}

		// letter at the position (r, c)
		String letter = board.getFace(r, c);
		// add this cell to the list of cells already passed through
		listOfCells.add(cell);
		// add letter to the end of the string built so far
		s.append(letter);

		List<String> listOfWords = new ArrayList<String>();
		// if the string is either a word or a prefix, continue the search
		if (lex.wordStatus(s).equals(LexStatus.WORD) || lex.wordStatus(s).equals(LexStatus.PREFIX)) {
			// if the word/prefix is a correct word, add it to the list of
			// correct words
			if (lex.wordStatus(s).equals(LexStatus.WORD) && s.length() >= minLength) {
				listOfWords.add(s.toString());
			}

			int[] rdelta = { -1, -1, -1, 0, 0, 1, 1, 1 };
			int[] cdelta = { -1, 0, 1, -1, 1, -1, 0, 1 };
			// traverse through the surrounding cells to see if they form words
			// with the prefix
			for (int i = 0; i < rdelta.length; i++) {
				helper(board, lex, minLength, listOfCells, r + rdelta[i], c + cdelta[i], s);
			}
		}
		listOfCells.remove(cell);
		if (letter.length() == 1) {
			s.deleteCharAt(s.length() - 1);
		}
	}
}
