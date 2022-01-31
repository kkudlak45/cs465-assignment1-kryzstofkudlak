package kryzstofkudlak.assignment1;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Encryptor {
	
	private VignereCipher vignereCipher;
	private List<Block> blocks;

	public Encryptor() {
		this.vignereCipher = new VignereCipher();
		this.blocks = new LinkedList<Block>();
	}
	
	/**
	 * This function performs the pre-processing stage as specified in section 2.1 of the project doc. This function
	 * 	will read through the input string and get rid of anything that doesn't fit the regex [A-Z]
	 * 
	 * @param input - the input string
	 * @return input string but only with capital letters from A-Z
	 */
	public String performPreprocessing(String input) {
		return input.chars() // turn string into IntStream of characters
				.filter(character -> 'A' <= character && character <= 'Z') // get rid of anything not between A and Z
				.collect(StringBuilder::new,  // turn back into string using StringBuilder
						StringBuilder::appendCodePoint, 
						StringBuilder::append)
				.toString();
	}
	
	/**
	 * Performs a substitution operation on each character in the given input string using a Vignere Cipher
	 * 	along with the corresponding character.
	 * 
	 * @param key - the key to encrypt the input with
	 * @param input - the input to be encrypted
	 * @return ciphertext after using a Vignere Cipher
	 */
	public String performSubstitution(String key, String input) {
		List<Pair<Character, Character>> list = new LinkedList<Pair<Character, Character>>();
		
		for (int i = 0; i < input.length(); i++) { // this effectively loops over the input string & zips it together with corresponding key chars
			char keyCharacter = key.charAt(i%key.length()); // key at corresponding index, have to mod in case key is shorter than input
			char inputCharacter = input.charAt(i); // input character
			list.add(new Pair<Character, Character>(keyCharacter, inputCharacter)); // combine these two into an object for easy storage
		}
		
		return list.stream() // given each pair in the list, transform Pair(key, value) into Vignere encrypted result
				.flatMapToInt(pair -> IntStream.of(this.vignereCipher.substitute(pair.getLeft(), pair.getRight())))
				.collect(StringBuilder::new,  // turn into string using StringBuilder
						StringBuilder::appendCodePoint, 
						StringBuilder::append)
				.toString();
	}
	
	/**
	 * Creates a list of 4x4 blocks to represent the input string. This step also handles padding
	 *  due to the way that the Block constructor works
	 * 
	 * @param input - the input string to transform into blocks
	 */
	public void performPaddingAndCreateBlocks(String input) {
		int blockSize = Block.BLOCK_SIZE * Block.BLOCK_SIZE;
		int blockCount = input.length() / blockSize; // # of blocks to create
		
		for (int i = 0; i < blockCount; i++) {
			String stuffToAdd = input.substring(i*blockSize, (i+1)*blockSize); // pull out a chunk of 16 chars
			this.blocks.add(new Block(stuffToAdd));
		}
		String excess = input.substring(blockCount*blockSize); // pull out whatever's left over
		this.blocks.add(new Block(excess));
	}
	
	/**
	 * This function calls shiftAllRows on every block in the list of blocks
	 */
	public void performShiftRows() {
		this.blocks.forEach(block -> block.shiftAllRows());
	}
	
	/**
	 * This function calls setParity on every block in the list of blocks
	 */
	public void performParityBit() {
		this.blocks.forEach(block -> block.setParity());
	}
	
	/**
	 * This function calls mixColumns on every block in the list of blocks
	 */
	public void performMixColumns() {
		this.blocks.forEach(block -> block.mixColumns());
	}
	
	
	
	
	/**
	 * This function outputs all the blocks in the list of blocks by calling Block.printBlock()
	 * 
	 * @param output - the stream to send output to
	 * @throws IOException
	 */
	public void printBlocks(OutputStream output) throws IOException {
		for (Block block : this.blocks)
			block.printBlock(output);
	}
	
	/**
	 * This function outputs all the blocks in the list of blocks in hex format by calling Block.printBlockAsHex()
	 * 
	 * @param output - the stream to send output to
	 * @throws IOException
	 */
	public void printBlocksAsHex(OutputStream output) throws IOException {
		for (Block block : this.blocks)
			block.printBlockAsHex(output);
	}
	
}
