package kryzstofkudlak.assignment1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class VignereCipherTest {

	private VignereCipher unit;
	
	@Before
	public void constructorTest() {
		this.unit = new VignereCipher();
	}
	
	@Test
	public void substituteTest() { // hardcoded tests for a few sample inputs from the doc
		assertEquals(this.unit.substitute('S', 'E'), 'W');
		assertEquals(this.unit.substitute('I', 'N'), 'V');
		assertEquals(this.unit.substitute('M', 'C'), 'O');
		assertEquals(this.unit.substitute('P', 'R'), 'G');
		assertEquals(this.unit.substitute('L', 'Y'), 'J');
		assertEquals(this.unit.substitute('E', 'P'), 'T');
	}
	
}
