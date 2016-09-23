package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FileParser {

	// Creates a Map <String, Double>
	private Map<String, Double> map = null;

	/*
	 * Method for Getting the map by calling another Private method parse()
	 * which Parses through a file "4grams.txt" and places the results in The
	 * Map.
	 */
	public Map<String, Double> getMap() {
		try {
			// call the Private method parse() to return the a Map
			map = parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	/*
	 * The following method creates a ConcurrentHashMap of type<String, Double>
	 * and reads in from a file called "4grams.txt" into that map.
	 */
	private Map<String, Double> parse() throws IOException {
		Map<String, Double> map = new ConcurrentHashMap<String, Double>();
		int count = 0;
		String text = "4grams.txt";
		StringBuilder sb = new StringBuilder();

		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(text)));
		try {
			int r;
			int lastNum, lastChar;
			char ch;
			/*
			 * while the next int read from the file is not -1 Read each int and
			 * convert to a char, then append it to a StringBuilder
			 */
			while ((r = br.read()) != -1) {
				ch = (char) r;

				sb.append(ch);

				// Finds the posof the last number before the (\n)newline
				lastNum = sb.indexOf("\n");
				// Finds the Pos of the last Character before the (" ") space
				lastChar = sb.indexOf(" ");

				/*
				 * If count is equal to the position of the last number in the
				 * StringBuilder, Then add to the Map the key(first
				 * Char,lastChar) and The value(lastChar+1,lastNum) from the
				 * StringBuilder, and reset the StringBuilders length to 0
				 */
				if (count == lastNum) {
					map.put(sb.substring(0, lastChar).toString(), Double
							.parseDouble(sb.substring(lastChar + 1, lastNum)));
					count = 0;
					sb.setLength(0);
				} else
					count++;
			}
			br.close();

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// return the Map
		return map;
	}

	/*
	 * The Method below just parses through any text file and adds each
	 * character to a StringBuilder that gets returned
	 */
	public StringBuilder parseTextFile(String text) {
		StringBuilder sb = new StringBuilder();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(text)));
			int r;
			char ch;
			while ((r = br.read()) != -1) {
				ch = (char) r;
				sb.append(ch);
			}
			br.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb;
	}

}
