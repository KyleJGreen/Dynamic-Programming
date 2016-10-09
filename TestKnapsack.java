/* Kyle Green
 * CSC 360
 * Knapsack Problem : program selects Projects out of a text file whose values combine to generate
 * the greatest possible sum without the sum of the work weeks for the projects exceeding the
 * limit set by the user.
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestKnapsack {

	// Engage Warp Engines
	public static void main(String[] args) throws FileNotFoundException
	{
		int workWeeks;
		String inFile, outFile;
		ArrayList<Project> projects = new ArrayList<>();
		
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the number of available employee work weeks: ");
		workWeeks = input.nextInt();

		System.out.print("Enter the name of input file: ");
		inFile = input.next();

		System.out.print("Enter the name of output file: ");
		outFile = input.next();

		input.close();

		projects = TextFileIO.readFile(inFile);
		TextFileIO.generateOutput(projects, workWeeks, outFile);
	}
}
