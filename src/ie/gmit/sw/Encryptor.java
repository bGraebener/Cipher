/*
	Created by Basti on 06.03.2017
*/

package ie.gmit.sw;

public interface Encryptor {

	
	/**
	 * The method that is used to encrypt the plain text with the given key.
	 * @param key
	 *            The String that is used to encrypt the message
	 * @param plainText
	 *            The String that is going to be encrypted
	 * @return The encrypted cipher text    
	 */
	String encrypt(String key, String plainText);
	
	/**
	 * The method that is used to decrypt a cipher text with the given key. 
	 * @param key
	 *            The String that is used to decrypt the message
	 * @param plainText
	 *            The String that is going to be decrypted
	 * 
	 * @return The decrypted plain text
	 * 
	 */
	String decrypt(String key, String cipher);

}
