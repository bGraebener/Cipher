/*
	Created by Basti on 06.03.2017
*/

package ie.gmit.sw;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Class responsible for showing menu options and getting input from the user
 * @author Basti
 *
 */
public class Menu {

	Scanner scan = new Scanner(System.in);

	
	/**
	 * The main entry point for the application. It calls the menu option methods, asks for and retrieves user input. 
	 */
	public void start() {

		// declare variables
		List<String> plainText;
		String path;
		Parser parser = null;
		int userChoice;

		// displaying menu and getting users' choice
		do {
			userChoice = displayMainMenu();

			if (userChoice == 3) {
				System.out.println("\tGoodbye!");
				return;
			}

			// getting the location of the text to parse
			path = getPath();
			

			// starting encryption/decryption process
			System.out.println("\n\tParsing source...");

			//getting source appropriate parser
			if (userChoice == 1) {
				
				//check if source path is a file
				File pathToFile = new File(path);
				if(!pathToFile.exists()){
					System.out.println("\n\tFile doesn't exist, try again!");
					continue;
				}
				
				parser = Parser.getParser(new File(path));
			} else if (userChoice == 2) {
				try {
					parser = Parser.getParser(new URL(path));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}

			//retrieve the plain text from the given source
			plainText = parser.parse();
			System.out.println("\tParsing finished...\n");
			
			//check if text present else show error message
			if(plainText.isEmpty()){
				System.out.println("\tNo text to encrypt/decrypt found!");
				continue;
			}
			
			//ask the user for a key word 
			String key = getKey();
			
			//path to the file with the encrypted message
			path = "./encryptedMessage.txt";

			//start the encryption/decryption
			System.out.println("\n\tStart encrypting...");
			long start = System.currentTimeMillis();
			
			//encrypt/decrypt every line and print it directly out to the specified output file
			//Iterating over the list of lines has a linear runtime of O(n) where n = number of lines
			//The encryption method has also a linear runtime of O(n) where n = number of characters in a line
			//
			try (PrintWriter pw = new PrintWriter(path)) {
				PortaCipher porta = new PortaCipher();

				plainText.forEach((line) -> pw.println(encrypt(porta, key, line)));
		
				System.out.println("\tFinished encrypting...");
				System.out.println("\tTime taken to encrypt/decrypt " + plainText.size() + " lines of text: " + (System.currentTimeMillis() - start) + " ms");
				System.out.println("\tEncrpyted message stored in: " + path);
				
				System.out.println("\n\tPress Enter to continue...");
				System.in.read();
				

			} catch (IOException e) {
				e.printStackTrace();
			}

		} while (userChoice != 3);
	}

	/**
	 * Function to encrypt a given plaintext with a specified instance of an Encryptor interface and a keyword 
	 * 
	 *  @param encryptor
	 *  	 An instance of an Encryptor interface
	 *  @param key
	 *  	A String that serves as a password
	 *  @param plainText
	 *  	 The plain text message as a String
	 *   
	 * @return The encrypted message as a String
	 */
	public String encrypt(Encryptor encryptor, String key, String plainText) {
		return encryptor.encrypt(key, plainText);
	}

	/**
	 * Method that displays the main menu, asks for and takes in users options
	 * @return the users choice as int
	 */
	public int displayMainMenu() {
		int userChoice;

		// get user inout
		do {
			System.out.println("\n\n\n********************************************************************");
			System.out.println("\n\tWelcome to Rapid Encryption with the Porta Cipher!");
			System.out.println("\n\t       *************************************");
			System.out.println("\n\tYou can encrypt or decrypt text\n\tfrom either a file or a Url using\n\tthe Porta Cipher: ");
			System.out.println("\n\t1 - Encrypt/Decrypt a File\n\t2 - Encrypt/Decrypt a URL\n\t3 - Quit");
			System.out.print("\n\tPlease enter your choice: ");
			userChoice = scan.nextInt();
		} while (userChoice > 3 || userChoice < 1);

		return userChoice;
	}

	/**
	 * A method that asks and returns the users chosen path for the source text
	 * @return The path to the source text
	 */
	public String getPath() {
		String path;
		
		System.out.print("\tPlease enter path to source : ");		
		path = scan.next();
		return path;
	}

	/**
	 * A method that asks and returns the users chosen keyword
	 * @return The keyword
	 */
	public String getKey() {
		System.out.print("\tPlease enter a key word: ");
		String key = scan.next().toUpperCase();
		return key;
	}
		

}
