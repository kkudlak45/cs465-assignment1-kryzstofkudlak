package kryzstofkudlak.assignment1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Main {
	
	private static final String IN_FILE = "input.txt";
	private static final String KEY_FILE = "key.txt";
	private static final String OUT_FILE = "output.txt";
	
	public static void main(String[] args) throws IOException {
		
		// allow the user choose which input/key combo they want to encrypt
		System.out.println("Select which case to evaluate (1, 2, or 3):");
		Scanner scanner = new Scanner(System.in);
		int selection = scanner.nextInt();
		scanner.close();
		
		if (selection < 1 || selection > 3) 
			throw new IllegalArgumentException("Selection must be 1, 2, or 3");
		
		// setup I/O streams
		InputStream inputStream = ClassLoader.getSystemResourceAsStream("case" + selection + "/" + IN_FILE);
		String input = new String(inputStream.readAllBytes());
		inputStream.close();
		
		InputStream keyStream = ClassLoader.getSystemResourceAsStream("case" + selection + "/" + KEY_FILE);
		String key = new String(keyStream.readAllBytes());
		keyStream.close();
		
		File outFile = new File(OUT_FILE);
		outFile.createNewFile();
		OutputStream output = new FileOutputStream(outFile, false);
		
		// perform encryption
		Encryptor encryptor = new Encryptor();
		
		String preprocessedInput = encryptor.performPreprocessing(input);
		output.write("Preprocessing:\n".getBytes());
		output.write(preprocessedInput.getBytes());
		output.write("\n\n".getBytes());
		
		String substitutedInput = encryptor.performSubstitution(key, preprocessedInput);
		output.write("Substitution:\n".getBytes());
		output.write(substitutedInput.getBytes());
		output.write("\n\n".getBytes());
		
		encryptor.performPaddingAndCreateBlocks(substitutedInput);
		output.write("Padding:\n".getBytes());
		encryptor.printBlocks(output);
		
		encryptor.performShiftRows();
		output.write("ShiftRows:\n".getBytes());
		encryptor.printBlocks(output);
		
		encryptor.performParityBit();
		output.write("Parity:\n".getBytes());
		encryptor.printBlocksAsHex(output);
		
		encryptor.performMixColumns();
		output.write("MixColumns:\n".getBytes());
		encryptor.printBlocksAsHex(output);
		
		output.close();
		System.out.println("All done! Generated output file saved to: " + outFile.getAbsolutePath());
	}
	
}
