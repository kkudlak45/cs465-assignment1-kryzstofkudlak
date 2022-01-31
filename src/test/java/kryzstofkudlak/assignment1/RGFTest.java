package kryzstofkudlak.assignment1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RGFTest {

	@Test
	public void checkMul2() {
		assertEquals(0b01000101, RGF.mul(0b10101111, 2));
	}
	
	@Test 
	public void checkMul3() {
		assertEquals(0b11101010, RGF.mul(0b10101111, 3));
	}
	
}
