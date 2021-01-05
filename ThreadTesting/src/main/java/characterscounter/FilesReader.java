package characterscounter;

import java.io.*;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * This class will read the file and count the small characters and store the
 * count for each char in AtomicIntegerArray charsCountArray.
 * 
 * @author Malak
 *
 */

public class FilesReader {

	private AtomicIntegerArray charsCountArray;

	public FilesReader() {

		charsCountArray = new AtomicIntegerArray(26);
	}

	/**
	 * Read the passed file character by character and for each char call the
	 * updateCharsCountArray.
	 *
	 * @param file file to read.
	 */
	synchronized void readFile(File file) {
		AtomicIntegerArray tempCharsCountArray = new AtomicIntegerArray(26);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			int character = 0;

			while ((character = reader.read()) != -1) {
				updateCharsCountArray(character, tempCharsCountArray);
			}

			charsCountArray = tempCharsCountArray;
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

	/**
	 * Update charsCountArray by incrementing the occurrence in case the passed
	 * character is a small character.
	 * 
	 *
	 */

	private synchronized void updateCharsCountArray(int character, AtomicIntegerArray tempCharsCountArray) {
		if (character > 96 && character < 123) {

			tempCharsCountArray.getAndIncrement(character - 97);

		}

	}

	/**
	 * This method returns the charsCountArray.
	 * 
	 * @return AtomicIntegerArray that contains the occurrence of small characters
	 *         of this file.
	 */
	public synchronized AtomicIntegerArray getCharsCountArray() {
		return charsCountArray;
	}
}
