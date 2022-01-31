package kryzstofkudlak.assignment1;

public class VignereCipher {

	private static final int LETTERS_COUNT = 26;
	
	private static char[][] arr;
	
	/**
	 * This constructor initializes a vignere cipher array. This is static data, but I 
	 * 	didn't want to hardcode it, so I wrote a little loop to populate the array for me.
	 */
	public VignereCipher() {
		arr = new char[LETTERS_COUNT][LETTERS_COUNT];
		
		for (int i = 0; i < LETTERS_COUNT; i++) {
			for (int j = 0; j < LETTERS_COUNT; j++) {
				arr[i][j] = (char) ('A' + ((i + j) % LETTERS_COUNT));
			}
		}
	}
	
	/**
	 * Performs a vignere cipher substitution given a key and input character.
	 * 
	 * @param key - the key character
	 * @param input - the input character
	 * @return the result of a vignere cipher operation
	 */
	public char substitute(char key, char input) {
		return arr[key - 'A'][input - 'A'];
	}
	
}
