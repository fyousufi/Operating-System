
public class Simulation {
	public static void main(String[] args) {
		int N = 100; // specify the number of runs
		int[] caseOneResults = new int[N];
		int[] caseTwoResults = new int[N];
		int[] caseThreeResults = new int[N];
		int[] caseFourResults = new int[N];
		int[] winner = new int[N];
		
		for (int i = 0; i < N; i++) {
			OS.main(args);
			caseOneResults[i] = OS.executeOne.numberOfFinishedProcess;
			caseTwoResults[i] = OS.executeTwo.numberOfFinishedProcess;
			caseThreeResults[i] = OS.executeThree.numberOfFinishedProcess;
			caseFourResults[i] = OS.executeFour.numberOfFinishedProcess;
			winner[i] = OS.getWinner();
		}
		
		System.out.println("\n\n");
		System.out.println("----------------------------------------------------");
		System.out.println("Run\t\tOne\tTwo\tThree\tFour\tWinner");
		System.out.println("----------------------------------------------------");
		
		for (int i = 0; i < N; i++) {
			System.out.println("Run " + i + ":\t\t" + caseOneResults[i] + "\t" + caseTwoResults[i] + "\t" + caseThreeResults[i] + "\t" + +caseFourResults[i] + "\t" + winner[i]);
		}
	}
}