/* Kyle J Green
 * CSC 364-01
 * Knapsack Problem : program selects Projects out of a text file whose values combine to generate
 * the greatest possible sum without the sum of the work weeks for the projects exceeding the
 * limit set by the user.
 */

public class Project {

	String projectName;
	int workWeeks, netProfit;

	public Project(String name, int weeks, int profit) {
		projectName = name;
		workWeeks = weeks;
		netProfit = profit;
	}
}
