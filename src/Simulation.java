/**
 * 
 * Simulation class to run N number of times and compare the results
 *
 */
public class Simulation {
	private static int N = 100; // Specify the number of runs
	private static int[] caseOneResults = new int[N];
	private static int[] caseTwoResults = new int[N];
	private static int[] caseThreeResults = new int[N];
	private static int[] caseFourResults = new int[N];
	private static int[] winner = new int[N];
	
	public static void main(String[] args) {
		// Execute N times for each cases
		for (int i = 0; i < N; i++) {
			OS.main(args);
			caseOneResults[i] = OS.executeOne.numberOfFinishedProcess;
			caseTwoResults[i] = OS.executeTwo.numberOfFinishedProcess;
			caseThreeResults[i] = OS.executeThree.numberOfFinishedProcess;
			caseFourResults[i] = OS.executeFour.numberOfFinishedProcess;
			winner[i] = OS.getWinner();
		}
		
		printResults();
	}
	
	/*
	 * Print the results of the simulation
	 */
	public static void printResults() {
		System.out.println("\n\n");
		System.out.println("------------------------------------------------------");
		System.out.println("Run\t\tOne\tTwo\tThree\tFour\tWinner");
		System.out.println("------------------------------------------------------");
		
		for (int i = 0; i < N; i++) {
			System.out.println("Run " + i + ":\t\t" + caseOneResults[i] + "\t" + caseTwoResults[i] + "\t" + caseThreeResults[i] + "\t" + +caseFourResults[i] + "\t" + winner[i]);
		}
	}
}