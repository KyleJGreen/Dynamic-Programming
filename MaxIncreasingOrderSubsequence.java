/* Kyle J Green
 * CSC 364-01
 * Max Increasing Order Subsequence: This program takes a String and converts it to the
 * greatest possible subsequence of character listed in increasing order within the
 * String.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class MaxIncreasingOrderSubsequence 
{
	// Engage Warp Engines
	public static void main(String[] args)
	{
		// Prompt user to enter a string and record it as a String
		System.out.print("Enter a String: ");
		Scanner input = new Scanner(System.in);
		String in = input.next();
		input.close();

		// Call method to derive the greatest possible subsequence from the input String
		deriveSubsequence(in);
	}

	// Derives the greatest possible subsequence from a String
	public static void deriveSubsequence(String in) 
	{
		String string = in;
		int[] score = new int[string.length()];
		int max = 0;
		int count = 0;
		ArrayList<Character> subsequence = new ArrayList<>();
		subsequence.add('A');

		for(int i = 0; i < string.length(); i++)
		{
			score[i] = 1;
			
			// Generate the subsequence
			for(int j = 0; j < i; j++)
			{
				// Generate score for each character of the input String and add
				// increasing scores to the subsequence ArrayList
				if(score[j] > max && string.charAt(j) < string.charAt(i))
				{
					max = score[j];
					score[i] = 1 + max;
					subsequence.add(string.charAt(i));
				}
				// Add the first letter to the subsequence
				if(score[j] == 1 && string.charAt(j) < string.charAt(i) && count == 0)
				{
					subsequence.set(0, string.charAt(j));
					count++;
				}		
			}
		}

		// Print the subsequence
		for(int i = 0; i < subsequence.size(); i++)
		{
			System.out.print(subsequence.get(i));
		}
	}
}
