package kryzstofkudlak.assignment1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class BlockTest {
	
	@Test
	public void shiftAllRows() throws IOException {
		String testString = "WVOGJTXQHUHXICWY";
		
		Block block = new Block(testString);
		block.shiftAllRows();
		block.printBlock(System.out);
	}
	
	
	@Test
	public void checkOddNumberOfOnes() {
		Block block = new Block("ASDF");
		
		assertTrue(block.hasOddNumberOfOnes(0b01000000));
		assertTrue(block.hasOddNumberOfOnes(0b01000110));
		assertTrue(block.hasOddNumberOfOnes(0b11000001));
		
		assertFalse(block.hasOddNumberOfOnes(0b11111111));
		assertFalse(block.hasOddNumberOfOnes(0b01100110));
		assertFalse(block.hasOddNumberOfOnes(0b10101010));
	}
	
	@Test
	public void checkParity() throws IOException {
		Block block = new Block("WVOGJTXQHUHXICWY");
		block.shiftAllRows();
		block.printBlock(System.out);
		block.printBlockAsHex(System.out);
		
		block.setParity();
		block.printBlockAsHex(System.out);
	}
	
	@Test
	public void checkMixCols() throws IOException {
		Block block = new Block("WVOGJTXQHUHXICWY");
		block.shiftAllRows();
		block.printBlock(System.out);
		block.printBlockAsHex(System.out);
		
		block.setParity();
		block.printBlockAsHex(System.out);
		
		block.mixColumns();
		block.printBlockAsHex(System.out);
	}
	
}
