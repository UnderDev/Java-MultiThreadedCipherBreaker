package ie.gmit.sw;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class Runner {

	public static void main(String[] args) throws IOException {
		FileParser fp = new FileParser();
		Scanner sc = new Scanner(System.in);
		String message;

		int choice = 0;

		do {
			System.out.print("\nEnter The Number for the Following\n "
							+ "1 : Enter CipherText Text From the Console.\n "
							+ "2 : Enter the .Txt filename Containing the CipherText: ");

			while (!sc.hasNextInt()) {
				System.out.print("\nThat's not a number! Try Again: ");
				sc.next();
			}
			choice = sc.nextInt();
		} while (choice <= 0);

		// Used for Testing text file from the File Path
		// String cipherText = "C:/Users/scottCoyne/Desktop/cipherText.txt";

		// STEP 1 - GET CYCHER TEST FROM USER
		switch (choice) {
		case 1:
			System.out.print("\nEnter Ciphertext: ");
			message = sc.next();
			break;
		case 2:
			System.out.print("\nEnter FileName: ");
			message = sc.next();
			message = (fp.parseTextFile(message)).toString();
			break;
		default:
			message = ("TOHHTDTFIYERRSRFOOMTENMELNEOALDHUNITTRSTHNSWSEIOAUPSLROETDSIYSNIRFEE");
			break;
		}
		sc.close();
		// start timing program running time
		long start = System.currentTimeMillis();

		// Pass in the cipher message to The class CypherBreaker(message);
		CipherBreaker cb = new CipherBreaker(message);
		// Call the method init() from the CypherBreaker class
		cb.init();

		//calls the .toString method in CipherBreaker that has the results
		System.out.println(cb.toString());

		// Stop timer and print to the screen
		long end = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.println("\nTime: " + formatter.format((end - start) / 1000d)+ "ms");
	}
}
