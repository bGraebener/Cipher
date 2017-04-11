/*
	Created by Basti on 02.03.2017
*/
package ie.gmit.sw;

/**
 * Class that implenents the Porta Cipher functionality. It uses a
 * two-dimensional array of char as encryption/decryption grid. It implements
 * the Encryptor interface and its two abstract methods.
 * 
 * @author Basti
 *
 */
public class PortaCipher implements Encryptor {

	private static final char[][] tableau = {
			{ 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
					'I', 'J', 'K', 'L', 'M' },
			{ 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'N', 'M', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
					'H', 'I', 'J', 'K', 'L' },
			{ 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'N', 'O', 'L', 'M', 'A', 'B', 'C', 'D', 'E', 'F',
					'G', 'H', 'I', 'J', 'K' },
			{ 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'N', 'O', 'P', 'K', 'L', 'M', 'A', 'B', 'C', 'D', 'E',
					'F', 'G', 'H', 'I', 'J' },
			{ 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'N', 'O', 'P', 'Q', 'J', 'K', 'L', 'M', 'A', 'B', 'C', 'D',
					'E', 'F', 'G', 'H', 'I' },
			{ 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'N', 'O', 'P', 'Q', 'R', 'I', 'J', 'K', 'L', 'M', 'A', 'B', 'C',
					'D', 'E', 'F', 'G', 'H' },
			{ 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'N', 'O', 'P', 'Q', 'R', 'S', 'H', 'I', 'J', 'K', 'L', 'M', 'A', 'B',
					'C', 'D', 'E', 'F', 'G' },
			{ 'U', 'V', 'W', 'X', 'Y', 'Z', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'A',
					'B', 'C', 'D', 'E', 'F' },
			{ 'V', 'W', 'X', 'Y', 'Z', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
					'A', 'B', 'C', 'D', 'E' },
			{ 'W', 'X', 'Y', 'Z', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
					'M', 'A', 'B', 'C', 'D' },
			{ 'X', 'Y', 'Z', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
					'L', 'M', 'A', 'B', 'C' },
			{ 'Y', 'Z', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
					'K', 'L', 'M', 'A', 'B' },
			{ 'Z', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
					'J', 'K', 'L', 'M', 'A' } };

	/**
	 * The method that is used to encrypt the plain text with the given key.
	 * 
	 * The encrypt method has a run time of O(n). The lookup time for a single
	 * character has a constant runtime O(1) hence the encryption of a String
	 * with length n is O(n).
	 * 
	 */
	public String encrypt(String key, String plainText) {

		StringBuilder tempCipher = new StringBuilder(plainText.length());
		int keyIndex;
		int plainIndex;

		// iterating over the whole plain text string
		for (int i = 0; i < plainText.length(); i++) {

			/*
			 * //skipping whitespace if(plainText.charAt(i) == ' '){
			 * tempCipher.append(' '); continue; }
			 */

			// the key characters' index in the tableau can be found at the
			// characters position in the alphabet divided by two
			keyIndex = (key.charAt(i % key.length()) - 65) >> 1;

			// the plain text characters' index in the tableau is the same as
			// the characters position in the alphabet
			plainIndex = plainText.charAt(i) - 65;

			// store the character found at the intersection of both indexes in
			// the tableau
			tempCipher.append(PortaCipher.tableau[keyIndex][plainIndex]);
		}

		// return the encrypted line
		return tempCipher.toString();
	}

	/**
	 * The method that is used to decrypt a cipher text with the given key.
	 * 
	 * In case of the PortaCipher the decryption of a cipher text uses the same
	 * algorithm as the encryption, so it just delegates.
	 * 
	 */
	public String decrypt(String key, String cipher) {
		return encrypt(key, cipher);
	}

}