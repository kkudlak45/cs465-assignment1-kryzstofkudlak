package kryzstofkudlak.assignment1;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class Block {
	
	public static final int BLOCK_SIZE = 4;
	private final char[][] block = new char[BLOCK_SIZE][BLOCK_SIZE];
	
	/**
	 * This is the constructor, but it also performs the "Padding" operation that is
	 * 	specified by the project doc. It initializes the block to be all "A"s and fills
	 * 	it up with characters from the supplied input.
	 * 
	 * @param input - string to form a block out of
	 */
	public Block(String input) {
		for (int i = 0; i < BLOCK_SIZE; i++) // fill with 'A'
			for (int j = 0; j < BLOCK_SIZE; j++)
				block[i][j] = 'A';
		
		for (int i = 0; i < input.length(); i++) { // fill with input
			block[i/BLOCK_SIZE][i%BLOCK_SIZE] = input.charAt(i);
		}
	}
	
	
	//*********************
	//* ShiftRows Helpers *
	//*********************
	
	/**
	 * Calls shiftRow() to shift all rows by the appropriate number of units
	 * 	as specified in the project doc.
	 */
	public void shiftAllRows() {
		for (int i = 0; i < BLOCK_SIZE; i++) 
			shiftRow(i, BLOCK_SIZE - i);
	}
	
	/**
	 * Circular right shifts a given row in the matrix by a specified number of units.
	 * 	Ex: ("ABCD", 3) returns "DABC", ("ABCD", 1) returns "BCDA"
	 * 
	 * @param rowNumber - the row in the block to shift
	 * @param byHowMany - the number of units to shift by
	 */
	private void shiftRow(int rowNumber, int byHowMany) {
		char[] copy = Arrays.copyOf(block[rowNumber], BLOCK_SIZE);
		
		for (int i = 0; i < BLOCK_SIZE; i++) {
			this.block[rowNumber][(i + byHowMany) % BLOCK_SIZE] = copy[i];
		}
	}
	
	
	//**********************
	//* Parity Bit Helpers *
	//**********************
	
	/**
	 * As specified in the project doc, this method iterates over the block in search
	 * 	of a character with an uneven number of set bits. In order to make the number 
	 * 	of set bits even, it flips the MSB of whatever has an odd # of set bits.
	 */
	public void setParity() {
		for (int i = 0; i < BLOCK_SIZE; i++) // for each element in block
			for (int j = 0; j < BLOCK_SIZE; j++) 
				if (hasOddNumberOfOnes(block[i][j])) // if it has an odd number of ones
					block[i][j] = (char) (block[i][j] ^ 0b10000000); // flip its MSB
	}
	
	/**
	 * This method checks a byte to see if it has an odd number of set bits.
	 * 
	 * @param input - the byte to check
	 * @return true if it has an odd number of set bits, false otherwise
	 */
	protected boolean hasOddNumberOfOnes(int input) {
		int numberOfOnes = 0;
		int thingToCheck = input;
		
		while (thingToCheck > 0) { // basically just count the number of 1s
			if ((thingToCheck & 1) > 0) {
				numberOfOnes++;
			}
			thingToCheck = (thingToCheck >> 1);
		}
		
		return numberOfOnes % 2 > 0; // send back whether count is even
		
	}
	
	
	//**********************
	//* MixColumns Helpers *
	//**********************
	
	/**
	 * Performs the mixColumn math on each column of the block
	 */
	public void mixColumns() {
		for (int i = 0; i < BLOCK_SIZE; i++) 
			mixColumn(i);
	}
	
	/**
	 * Given a column, performs the MixColumns step in AES encryption as specified in the project doc.
	 * 
	 * @param col - the column to mix
	 */
	private void mixColumn(int col) {
		int c0 = this.block[0][col];
		int c1 = this.block[1][col];
		int c2 = this.block[2][col];
		int c3 = this.block[3][col];

		int a0 = RGF.mul(c0, 2) ^ RGF.mul(c1, 3) ^ c2             ^ c3;
		int a1 = c0             ^ RGF.mul(c1, 2) ^ RGF.mul(c2, 3) ^ c3; 
		int a2 = c0             ^ c1             ^ RGF.mul(c2, 2) ^ RGF.mul(c3, 3);
		int a3 = RGF.mul(c0, 3) ^ c1             ^ c2             ^ RGF.mul(c3, 2);
		
		this.block[0][col] = (char) a0;
		this.block[1][col] = (char) a1;
		this.block[2][col] = (char) a2;
		this.block[3][col] = (char) a3;
		
	}
	
	
	//********************
	//* Printing Helpers *
	//********************
	
	/**
	 * Sends the block in a nice format to whatever the specified output stream is
	 * 
	 * @param output - desired output stream
	 * @throws IOException
	 */
	public void printBlock(OutputStream output) throws IOException {
		for (int i = 0; i < BLOCK_SIZE; i++) {
			for (int j = 0; j < BLOCK_SIZE; j++) {
				output.write(block[i][j]);
			}
			output.write("\n".getBytes());
		}
		output.write("\n".getBytes());
	}
	
	/**
	 * Sends the block in a nice format to whatever the specified output stream is.
	 * 	All characters in the block are converted to hex before being outputted.
	 * 
	 * @param output - desired output stream
	 * @throws IOException
	 */
	public void printBlockAsHex(OutputStream output) throws IOException {
		for (int i = 0; i < BLOCK_SIZE; i++) {
			for (int j = 0; j < BLOCK_SIZE; j++) {
				String charAsHex = Integer.toHexString((int) this.block[i][j]) + " ";
				output.write(charAsHex.getBytes());
			}
			output.write("\n".getBytes());
		}
		output.write("\n".getBytes());
	}

}
