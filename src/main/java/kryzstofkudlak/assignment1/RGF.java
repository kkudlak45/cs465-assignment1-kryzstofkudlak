package kryzstofkudlak.assignment1;

public class RGF {

	/**
	 * This method performs a Rijndael Galois field multiplication given an 
	 * 	input and whether to multiply by 2 or by 3. This function works as 
	 *  specified in the project documentation. I don't understand why I'm 
	 *  making these computations, but they function as intended. 
	 * 
	 * @param input - the number to perform an RGF multiplication on
	 * @param byHowMuch - specifies whether to multiply by 2 or by 3
	 * @return the result of the RGF multiplication
	 */
	public static int mul(int input, int byHowMuch) {
		if (byHowMuch != 2 && byHowMuch != 3) 
			throw new IllegalArgumentException();
		
		int result = input << 1; // multiply by 2
		
		if (byHowMuch == 3) { // if we're multiplying by 3, we have to add x
			result = result ^ input; // xor to add x
		}
		
		if ((input & 0b10000000) > 0) { // if MSB is 1
			result = result ^ 0b00011011; // xor with special number
		}
		
		return result & 0b11111111; // i only want the last 8 bits
		
	}
	
}
