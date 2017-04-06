/*
	Created by Basti on 07.11.2016
*/

package ie.gmit.sw;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Class that is responsible for parsing text. The Class uses a BuffererdReader
 * with the suitable arguments for the source type to retrieve text from the
 * specified source.
 * 
 * @author Basti
 *
 */
public class Parser {

	private BufferedReader reader;

	private Parser(BufferedReader reader) {
		this.reader = reader;
	};

	/**
	 * Method that tries to retrieve text from the specified source. It stores
	 * the found Strings in a List<String>.
	 * 
	 * @return A new list of Strings retrieved from the source
	 * @throws IllegalArgumentException
	 *             if the reader object is not set
	 */
	public List<String> parse() {

		if (reader == null) {
			new IllegalArgumentException("No reader set");
		}

		List<String> plainText = new ArrayList<>();

		// parsing all upper-case characters leaving whitespace in
		// reader.lines().forEach((line) ->
		// book.add(line.replaceAll("[^a-zA-Z\\s]", "").toUpperCase()));

		// parsing all upper-case characters without any special characters and
		// no whitespace
		reader.lines().forEach((line) -> plainText.add(line.replaceAll("[^a-zA-Z]", "").toUpperCase()));

		// return deep copy of List
		return new ArrayList<String>(plainText);
	}

	/**
	 * Static factory method to retrieve a FileParser.
	 * 
	 * @param source
	 *            Path to the source of the text.
	 * @return A Parser object.
	 */
	public static Parser getParser(File source) {

		if (!source.exists()) {
			throw new IllegalArgumentException("File doesn't exist!");
		}
		BufferedReader reader = null;

		try {
			reader = Files.newBufferedReader(Paths.get(source.getAbsolutePath()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new Parser(reader);
	}

	/**
	 * Static factory method to retrieve a UrlParser.
	 * 
	 * @param source
	 *            URL of the source of the text.
	 * @return A Parser object.
	 */
	public static Parser getParser(URL source) {

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new InputStreamReader(source.openStream(), StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new Parser(reader);
	}

}
