import java.util.*;

/**
 * 
 * @author Jessica Peng
 * @version Apr 28, 2017
 * @author Period: 5
 * @author Assignment: Boggle
 *
 * @author Sources: none
 */
public class BinarySearchLexicon implements ILexicon {

	private ArrayList<String> myWords;

	public BinarySearchLexicon() {
		myWords = new ArrayList<String>();
	}

	public void load(Scanner s) {
		myWords.clear();
		while (s.hasNext()) {
			myWords.add(s.next().toLowerCase());
		}
		Collections.sort(myWords);
	}

	public void load(ArrayList<String> list) {
		myWords.clear();
		myWords.addAll(list);
		Collections.sort(myWords);
	}

	public LexStatus wordStatus(StringBuilder s) {
		return wordStatus(s.toString());
	}

	public LexStatus wordStatus(String s) {
		int i = Collections.binarySearch(myWords, s);

		if (i >= 0) {
			return LexStatus.WORD;
		}
		if ((-1 * (i)) - 1 == myWords.size()) {
			return LexStatus.NOT_WORD;
		}
		if (myWords.get((-1 * i) - 1).startsWith(s)) {
			return LexStatus.PREFIX;
		} else {
			return LexStatus.NOT_WORD;
		}
	}

	public Iterator<String> iterator() {
		return myWords.iterator();
	}

	public int size() {
		return myWords.size();
	}

}
