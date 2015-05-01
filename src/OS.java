/**
 * 
 * Operating System (OS) class which creates the Main Memory, Queue, and Processes and executes each Queue based on the algorithms chosen
 *
 *By Nik Yusof and Farjad Yousufi 
 */
public class OS {
	
	/** Variables **/
	int caseNumber;
	Boolean detail;
	
	Queue processes;
	MainMemory mainMemory;
	ProcessGenerator processGenerator;
	
	static caseOne executeOne;
	static caseTwo executeTwo;
	static caseThree executeThree;
	static caseFour executeFour; 

	/** Constructor **/
	public OS(int casetype, Boolean detail) {
		this.caseNumber = casetype;
		this.detail = detail;

		processes = new Queue();
		processGenerator = new ProcessGenerator(processes);
		mainMemory = new MainMemory();
		
		if (detail == true) {
			System.out.println(processes.toString());
			System.out.println(mainMemory.toString());
		}
	}

	/*
	 * Execute the OS
	 */
	public void start() {
		if (caseNumber == 1) {
			executeOne = new caseOne(processes, mainMemory);
			executeOne.setDetail(detail);
			executeOne.run();
		} else if (caseNumber == 2) {
			executeTwo = new caseTwo(processes, mainMemory);
			executeTwo.setDetail(detail);
			executeTwo.run();
		} else if (caseNumber == 3) {
			executeThree = new caseThree(processes, mainMemory);
			executeThree.setDetail(detail);
			executeThree.run();
		} else if(caseNumber == 4) {
			executeFour = new caseFour(processes, mainMemory);
			executeFour.setDetail(detail);
			executeFour.run();
		} else if (caseNumber == 5) {
			Queue queueCopy = new Queue(processes);

			// Execute case 1
			System.out.println("-------------------------------------------------------");
			System.out.println("     Case One: First Come First Served (First Fit)     ");
			System.out.println("-------------------------------------------------------");
			executeOne = new caseOne(processes, mainMemory);
			executeOne.setDetail(detail);
			executeOne.run();

			// Execute case 2
			System.out.println("-------------------------------------------------------");
			System.out.println("     Case Two: First Come First Served (Best Fit)      ");
			System.out.println("-------------------------------------------------------");
			mainMemory = new MainMemory();
			processes = new Queue(queueCopy);
			executeTwo = new caseTwo(processes, mainMemory);
			executeTwo.setDetail(detail);
			executeTwo.run();

			// Execute case 3
			System.out.println("-------------------------------------------------------");
			System.out.println("      Case Three: Shortest Job First (Best Fit)       ");
			System.out.println("-------------------------------------------------------");
			mainMemory = new MainMemory();
			processes = new Queue(queueCopy);
			executeThree = new caseThree(processes, mainMemory);
			executeThree.setDetail(detail);
			executeThree.run();
			
			// Execute case 4
			System.out.println("-------------------------------------------------------");
			System.out.println("      Case Four: Shortest Job First (First Fit)       ");
			System.out.println("-------------------------------------------------------");
			mainMemory = new MainMemory();
			processes = new Queue(queueCopy);
			executeFour = new caseFour(processes, mainMemory);
			executeFour.setDetail(detail);
			executeFour.run();

			System.out.println("\n\n\n");
			System.out.println("Case One Finished " + executeOne.numberOfFinishedProcesses() + " Processes.");
			System.out.println("Case Two Finished " + executeTwo.numberOfFinishedProcesses() + " Processes.");
			System.out.println("Case Three Finished " + executeThree.numberOfFinishedProcesses() + " Processes.");
			System.out.println("Case Four Finished " + executeFour.numberOfFinishedProcesses() + " Processes.");
			
			if (executeOne.numberOfFinishedProcesses() > executeTwo.numberOfFinishedProcesses() && executeOne.numberOfFinishedProcesses() > executeThree.numberOfFinishedProcesses() && executeOne.numberOfFinishedProcesses() > executeFour.numberOfFinishedProcesses()) {
				System.out.println("Case One completed the most processes.");
			} else if (executeTwo.numberOfFinishedProcesses() > executeOne.numberOfFinishedProcesses() && executeTwo.numberOfFinishedProcesses() > executeThree.numberOfFinishedProcesses() && executeTwo.numberOfFinishedProcesses() > executeFour.numberOfFinishedProcesses()) {
				System.out.println("Case Two completed the most processes.");
			} else if (executeThree.numberOfFinishedProcesses() > executeOne.numberOfFinishedProcesses() && executeThree.numberOfFinishedProcesses() > executeTwo.numberOfFinishedProcesses() && executeThree.numberOfFinishedProcesses() > executeFour.numberOfFinishedProcesses() ) {
				System.out.println("Case Three completed the most processes.");
			} else if (executeFour.numberOfFinishedProcesses() > executeOne.numberOfFinishedProcesses() && executeFour.numberOfFinishedProcesses() > executeTwo.numberOfFinishedProcesses() && executeFour.numberOfFinishedProcesses() > executeThree.numberOfFinishedProcesses()) {
				System.out.println("Case Four completed the most processes.");
			} else {
				System.out.println("There was a tie in the number of completed processes.");
			}
		} else {
			System.out.println("Something, somewhere, went wrong!");
		}
	}

	/** Functions **/

	/** toString **/
	public String toString() {
		return "";
	}
	
	public static int getWinner() {
		if (executeOne.numberOfFinishedProcesses() > executeTwo.numberOfFinishedProcesses() && executeOne.numberOfFinishedProcesses() > executeThree.numberOfFinishedProcesses() && executeOne.numberOfFinishedProcesses() > executeFour.numberOfFinishedProcesses()) {
			return 1;
		} else if (executeTwo.numberOfFinishedProcesses() > executeOne.numberOfFinishedProcesses() && executeTwo.numberOfFinishedProcesses() > executeThree.numberOfFinishedProcesses() && executeTwo.numberOfFinishedProcesses() > executeFour.numberOfFinishedProcesses()) {
			return 2;
		} else if (executeThree.numberOfFinishedProcesses() > executeOne.numberOfFinishedProcesses() && executeThree.numberOfFinishedProcesses() > executeTwo.numberOfFinishedProcesses() && executeThree.numberOfFinishedProcesses() > executeFour.numberOfFinishedProcesses() ) {
			return 3;
		} else if (executeFour.numberOfFinishedProcesses() > executeOne.numberOfFinishedProcesses() && executeFour.numberOfFinishedProcesses() > executeTwo.numberOfFinishedProcesses() && executeFour.numberOfFinishedProcesses() > executeThree.numberOfFinishedProcesses()) {
			return 4;
		} else {
			return 0;
		}
	}

	/** Main **/
	/*
	 * Argument 1:
	 * 	- 1 for case 1
	 * 	- 2 for case 2
	 * 	- 3 for case 3
	 * 	- 4 for case 4
	 * 	- 5 for all cases
	 * 
	 * Argument 2: v to print in detail
	 */
	public static void main(String[] args) {
		
		/** Variables **/
		int caseNumber;
		Boolean detail;
		OS operatingSystem;

		detail = false;
		caseNumber = 1;

		if (args.length == 2) {
			caseNumber = Integer.parseInt(args[0]);
			char[] temp = args[1].toCharArray();
			
			if (temp[0] == 'v') {
				detail = true;
			} else {
				detail = false;
			}

			if(caseNumber != 1 && caseNumber != 2 && caseNumber != 3  && caseNumber != 4) {
				caseNumber = 1;
			}
			
			System.out.println("Executing the OS with case " + caseNumber + ".");
		} else if (args.length == 1) {
			caseNumber = Integer.parseInt(args[0]);
			detail = false;

			if (caseNumber != 1 && caseNumber != 2 && caseNumber != 3 && caseNumber != 4 && caseNumber != 5 ) {
				caseNumber = 1;
			}

			System.out.println("Executing the OS with case " + caseNumber + ".");
		} else if (args.length == 0) {
			// Default execution if no arguments
			System.out.println("NOTE: Arguments not specified. OS will execute,");
			System.out.println("      assuming default values (case 1, quiet).");
			System.out.println("Please pass either one or two arguments to");
			System.out.println("to specify how the OS should operate.");
			caseNumber = 1;
			detail = false;
		} else {
			// More than 2 arguments or failure
			System.out.println("This program must be executed with no more than two arguments");
			System.out.println("Arg1: The case to execute (case one, case two, case three, case four, or case five for all of them");
			System.out.println("Arg2: (Optional) Use 'v' to make the OS print its process verbosely.");
			System.out.println("Example:");
			System.out.println("java OS 5 v - This will execute the OS for all cases consecutively, in a verbose manner.");
			System.out.println("java OS 2 v - This will execute the OS for case 2, in a verbose manner.");
			System.out.println("java OS 3 - This will execute the OS for case 3 not verbose.");
			System.out.println("java OS - This will execute the OS with default values (case 1 not verbose).");
			caseNumber = -1;
		}

		// Execute the OS
		if(caseNumber != -1) {
			operatingSystem =  new OS(caseNumber, detail);
			operatingSystem.start();
		}
	}
}