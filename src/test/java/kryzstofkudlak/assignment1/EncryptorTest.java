package kryzstofkudlak.assignment1;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class EncryptorTest {
	
	private Encryptor unit;
	
	@Before
	public void setup() {
		this.unit = new Encryptor();
	}
	
	@Test
	public void checkPreprocessor() {
		System.out.println(this.unit.performPreprocessing("ASDF HIJK LMNOP QRSTUV"));
	}
	
	@Test
	public void checkSubstitution() {
		assertEquals(this.unit.performSubstitution("SIMPLEEXAMPLEKEY", "ENCRYPTTHISMESSAGEUSINGTHEALGORITHMDESCRIBEDBELOW"),
				"WVOGJTXQHUHXICWYYMGHTRKQHQPWKYVGLPYSPWGOINTOFOPMO");
	}
	
	@Test
	public void checkTheRest() throws IOException {
		String input = "WVOGJTXQHUHXICWYYMGHTRKQHQPWKYVGLPYSPWGOINTOFOPMO";
		this.unit.performPaddingAndCreateBlocks(input);
		this.unit.performShiftRows();
		this.unit.printBlocks(System.out);
		
		this.unit.performParityBit();
		this.unit.performMixColumns();
		this.unit.printBlocksAsHex(System.out);
	}
	
}
