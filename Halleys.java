/**
 * @author Nishanth Palanisamy
 * CIS 36B
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Halleys {
	public static void main(String[] args) throws IOException {// altered this for file handling
        
		File infile = new File("years.txt"); // open file-reading
		Scanner input = new Scanner(infile); // open scanner to read file
		int count = 0; // initialize count variable as 0
		while (input.hasNext()) {
			count++;
			input.next(); // must advance the scanner every time? this is what reads it in
		}
		input.close(); // close the scanner because we exhausted it?

		input = new Scanner(infile); // reopen scanner to read content
		final int ARRAY_SIZE = count; // initialize size of array

		String[] arrayYears = new String[ARRAY_SIZE]; // declare string array

		int i = 0;
		int numNegatives = 0;
		final int SUB_INDEX = 3;
		while (input.hasNext()) { // read the file until end
			String arrayItem = input.next();
			arrayYears[i] = arrayItem; // fill array with each number + comma from file
			int length = arrayYears[i].length();
			if (arrayYears[i].substring(length - SUB_INDEX, length - 1).equals("BC")) { // remove BC,
				arrayYears[i] = arrayYears[i].substring(0, length - SUB_INDEX);
				numNegatives++; // count number of BC values
			} else {
				// arrayYears[i] = arrayItem; //fill array with each number + comma from file
				arrayYears[i] = arrayYears[i].substring(0, length - 1);
			}
			i++; // advance the loop
		}

		int[] intArrayYears = new int[ARRAY_SIZE]; // create empty integer array to store values as ints

		for (int n = 0; n < arrayYears.length; n++) { // converting string array to integer array
			intArrayYears[n] = Integer.parseInt(arrayYears[n]);
		}
		for (int p = 0; p < numNegatives; p++) {
			intArrayYears[p] = 0 - intArrayYears[p]; // converting the BC years into negatives
		}

		// for (int k = 0; k < intArrayYears.length; k++){ //testing out to see if
		// values appear
		// System.out.println(intArrayYears[k]);
		// }

		input.close(); // close scanner

		String userInput = "";
		int inputLength = 0;
		int year = 0;
		int mid = 0;
		String userInputUpdated = "";
		final int MAX_YEAR = 2200;
		final int MIN_YEAR = -240;
		final int CURRENT_YEAR = 2022;

		Scanner inputYear = new Scanner(System.in); // declare new scanner or use old one?

		System.out.println("Welcome!\n"); // opening welcome statement

		while (!userInput.equalsIgnoreCase("x")) {
			// System.out.println("Welcome!\n"); //opening welcome statement

			System.out.print("Enter a year between 240BC and 2200 or 'x' to exit: "); // prompt user

			userInput = inputYear.next(); // get the String year from the user
			// inputYear.nextLine(); //necessary?

			// make if statement here for != x
			if (!(userInput.equalsIgnoreCase("x"))) {
				inputLength = userInput.length(); // declared on line 56

				// System.out.println(userInput); //tesing to see if userInput works
				// System.out.println(inputLength); //tesing to see if userInput length is
				// correct

				if (userInput.substring(inputLength - 2).equals("BC")) { // check this!
					userInputUpdated = userInput.substring(0, inputLength - 2);
					year = Integer.parseInt(userInputUpdated); // declared on line 57
					year = 0 - year;
					// System.out.println(year); //tesing to see if BC year prints as its new
					// negative form
				}

				else {
					year = Integer.parseInt(userInput);
				}

				if (year >= MIN_YEAR && year <= MAX_YEAR) { // between years -240 and 2200
					// call the binary search method

					mid = binarySearch(intArrayYears, year); // call the binary search method
					if (mid == -1) { // if binary search cannot find the year

						if (year >= CURRENT_YEAR && mid == -1) { // use future tense for year after 2022
							System.out.println("Halley's Comet will not appear in the year " + userInput + ".");
						} else { // this covers past tense for before 2022
							System.out.println("Halley's Comet did not appear in the year " + userInput + ".");
						}

					}

					else {

						if (year >= CURRENT_YEAR) {

							System.out.println("Halley's Comet will appear in the year " + userInput + ".\n");

						}

						else {
							System.out.println("Halley's Comet did appear in the year " + userInput + ".\n");

						}
					}

				} else {
					System.out.println("Invalid date!");
				}

			} else {

				System.out.println("Goodbye!");
			}

		}

	}

	/**
	* 
	* 
	* 
	*/

	/**
	 * performs a binary search to check if the user's given year is found in the
	 * array
	 * 
	 * @param dates the int[] that contains the years that Halley's comet appears
	 * @param year  the year that is given by the user
	 * @return mid the integer that indicates whether it is a year the comet
	 *         appeared or not; returns -1 if the year is not found
	 */
	public static int binarySearch(int[] dates, int year) {

		int low = 0;
		int high = dates.length - 1;
		int mid;

		while (low <= high) {
			mid = (low + high) / 2;
			if (dates[mid] == year) {
				return mid;
			} else if (dates[mid] > year) {
				high = mid - 1; // search the left half
			} else {
				low = mid + 1; // search the right half
			}
		}
		return -1;
	}
}
