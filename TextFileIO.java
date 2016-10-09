/* Kyle Green
 * CSC 360
 * Knapsack Problem : program selects Projects out of a text file whose values combine to generate
 * the greatest possible sum without the sum of the work weeks for the projects exceeding the
 * limit set by the user.
 */

import java.util.*;
import java.io.*;

public class TextFileIO
{
	// Reads the file input by the user and interprets it into an ArrayList of Projects
	public static ArrayList<Project> readFile(String fileName) throws FileNotFoundException
	{			
		Scanner dataFile = new Scanner(new File(fileName));
		ArrayList<Project> projects = new ArrayList<>();
		while (dataFile.hasNextLine())
		{
			String line = dataFile.nextLine();
			String[] split = line.split(" ");
			projects.add(new Project(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2])));
		}
		dataFile.close();
		return projects;
	}

	// Generates the output file.
	public static void writeFile(String fileName, int projectsAvailable, int workWeeks, 
			int totalProfit, ArrayList<Project> chosen) throws FileNotFoundException
	{
		PrintWriter outputFile = new PrintWriter(fileName);
		outputFile.println("Number of projects available: " + projectsAvailable);
		outputFile.println("Available employee work weeks: " + workWeeks);
		outputFile.println("Number of projects chosen: " + chosen.size());
		outputFile.println("Total profit: " + totalProfit);

		for(int i = 0; i < chosen.size(); i++)
		{
			outputFile.println(chosen.get(i).projectName + " " + chosen.get(i).workWeeks +
					" " + chosen.get(i).netProfit);
		}
		outputFile.close();
	}

	// Generate the output to be sent to the file
	public static void generateOutput(ArrayList<Project> projects, int workWeeks, String fileName) throws FileNotFoundException
	{
		int totalProfit = 0, count = 0, weeks = workWeeks;
		ArrayList<Project> chosen = new ArrayList<>();
		int[][] profit = new int[workWeeks + 1][projects.size() + 1];

		// Generate table
		for(int w = 1; w <= workWeeks; w++)
		{
			for(int n = 1; n <= projects.size(); n++)
			{
				if(projects.get(n-1).workWeeks > w)
					profit[w][n] = profit[w][n-1];
				else
				{
					profit[w][n] = (Math.max(profit[w - projects.get(n-1).workWeeks][n-1] + profit[w][n] + projects.get(n-1).netProfit, profit[w][n-1]));
				}
			}
		}

		// Create new ArrayList
		for(int n = projects.size(); n > 0; n--)
		{

			if(projects.get(n-1).workWeeks <= weeks)
			{
				if((profit[weeks - projects.get(n - 1).workWeeks][n-1] + projects.get(n - 1).netProfit) >= profit[weeks][n]
						&& !chosen.contains(projects.get(n - 1)))
				{
					chosen.add(chosen.size() - count, projects.get(n - 1));
					weeks -= projects.get(n - 1).workWeeks;
					count++;
				}
			}
		}

		//Calculate total profit
		for(int i = 0; i < chosen.size(); i++)
		{
			totalProfit += chosen.get(i).netProfit;
		}
		writeFile(fileName, projects.size(), workWeeks, totalProfit, chosen);
	}
}

