
public class OS {
	
	/***Variables***/
	int caseNumber;
	Boolean verbose;
	
	Queue processes;
	MainMemory mainMemory;
	ProcessGenerator processGenerator;
	
	static caseOne executeOne;
	static caseTwo executeTwo;
	static caseThree executeThree;
	static caseFour executeFour; 

	/***Constructor***/
	public OS(int casetype, Boolean verbose) {
		this.caseNumber = casetype;
		this.verbose = verbose;

		// Construct the hardware
		processes = new Queue();
		processGenerator = new ProcessGenerator(processes);
		mainMemory = new MainMemory();
		
		if (verbose == true) {
			System.out.println(processes.toString());
			//System.out.println(generator.toString());
			System.out.println(mainMemory.toString());
		}
	}

	/***Start***/
	public void start() {
		if (caseNumber == 1) { // Operate caseNumber 1
			executeOne = new caseOne(processes, mainMemory);
			executeOne.setVerbose(verbose);
			executeOne.run();
		} else if (caseNumber == 2) { // Operate caseNumber 2
			executeTwo = new caseTwo(processes, mainMemory);
			executeTwo.setVerbose(verbose);
			executeTwo.run();
		} else if (caseNumber == 3) { // Operate caseNumber 3
			executeThree = new caseThree(processes, mainMemory);
			executeThree.setVerbose(verbose);
			executeThree.run();
		}else if(caseNumber == 4){
			executeFour = new caseFour(processes, mainMemory);
			executeFour.setVerbose(verbose);
			executeFour.run();
		}
		else if (caseNumber == 5) { // Operate caseNumber 4
			// Make a copy of the original array, so that all three executions share the same input data
			Queue queueCopy = new Queue(processes);

			// Start process 1
			System.out.println("-------------------------------------------------------");
			System.out.println("     Case One: First Come First Served (First Fit)     ");
			System.out.println("-------------------------------------------------------");
			executeOne = new caseOne(processes, mainMemory);
			executeOne.setVerbose(verbose);
			executeOne.run();

			// Start process 2
			System.out.println("-------------------------------------------------------");
			System.out.println("     Case Two: First Come First Served (Best Fit)      ");
			System.out.println("-------------------------------------------------------");
			mainMemory = new MainMemory();
			processes = new Queue(queueCopy);
			executeTwo = new caseTwo(processes, mainMemory);
			executeTwo.setVerbose(verbose);
			executeTwo.run();

			// Start process 3
			System.out.println("-------------------------------------------------------");
			System.out.println("      Case Three: Shortest Job First (Best Fit)       ");
			System.out.println("-------------------------------------------------------");
			mainMemory = new MainMemory();
			processes = new Queue(queueCopy);
			executeThree = new caseThree(processes, mainMemory);
			executeThree.setVerbose(verbose);
			executeThree.run();
			
			// Start process 4
			System.out.println("-------------------------------------------------------");
			System.out.println("      Case Four: Shortest Job First (First Fit)       ");
			System.out.println("-------------------------------------------------------");
			mainMemory = new MainMemory();
			processes = new Queue(queueCopy);
			executeThree = new caseThree(processes, mainMemory);
			executeThree.setVerbose(verbose);
			executeThree.run();

			// Compare the number of finished jobs for each case type
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
			} else if (executeFour.numberOfFinishedProcesses() > executeOne.numberOfFinishedProcesses() && executeFour.numberOfFinishedProcesses() > executeTwo.numberOfFinishedProcesses() && executeFour.numberOfFinishedProcesses() > executeThree.numberOfFinishedProcesses())
			{
				System.out.println("Case Four completed the most processes.");
			}
			else {
				System.out.println("There was a tie in the number of completed processes.");
			}
		} else { // Error
			System.out.println("Something, somewhere, went wrong!");
		}
	}

	/***Functions***/

	/***toString***/
	public String toString() {
		return "";
	}
	
	public static int getWinner() {
		if (executeOne.numberOfFinishedProcesses() > executeTwo.numberOfFinishedProcesses() && executeOne.numberOfFinishedProcesses() > executeThree.numberOfFinishedProcesses()) {
			return 1;
		} else if (executeTwo.numberOfFinishedProcesses() > executeOne.numberOfFinishedProcesses() && executeTwo.numberOfFinishedProcesses() > executeThree.numberOfFinishedProcesses()) {
			return 2;
		} else if (executeThree.numberOfFinishedProcesses() > executeOne.numberOfFinishedProcesses() && executeThree.numberOfFinishedProcesses() > executeTwo.numberOfFinishedProcesses()) {
			return 3;
		} else {
			return 0;
		}
	}

	/***Main***/
	public static void main(String[] args) {
		//arg1: Allowed values are 1, 2, 3, OR 4 or 5. This represents the four
		//	    different cases the OS can execute. For example, 1 is
		//	    case 1, 2 is case 2, 3 is case 3. If you use 5, it will
		//	    perform all cases, with identical data.
		//arg2: Allowed value is v. This toggles the OS to print
		//      everything verbosely.
		/***Variables***/
		int caseNumber;
		Boolean verbose;
		OS operatingSystem;

		// Initialize important variables
		verbose = false;
		caseNumber = 1;

		if (args.length == 2) {
			// The user passed both an arg1 and an arg2
			caseNumber = Integer.parseInt(args[0]);
			char[] temp = args[1].toCharArray();
			
			if (temp[0] == 'v') {
				verbose = true;
			} else {
				verbose = false;
			}

			// Verify the caseNumber
			if(caseNumber != 1 && caseNumber != 2 && caseNumber != 3  && caseNumber != 4) {
				caseNumber = 1;
			}

			//Execute the OS
			System.out.println("Executing the OS with case " + caseNumber + ".");
		} else if (args.length == 1) {
			//The user only passed an arg1
			caseNumber = Integer.parseInt(args[0]);
			verbose = false;

			// Verify the caseNumber
			if(caseNumber != 1 && caseNumber != 2 && caseNumber != 3 && caseNumber != 4 && caseNumber !=5 ) {
				caseNumber = 1;
			}

			// Execute the OS
			System.out.println("Executing the OS with case " + caseNumber + ".");
		} else if (args.length == 0) {
			// The user passed no args, make the arg the default values
			System.out.println("NOTE: Arguments not specified. OS will execute,");
			System.out.println("      assuming default values (case 1, quiet).");
			System.out.println("Please pass either one or two arguments to");
			System.out.println("to specify how the OS should operate.");
			caseNumber = 1;
			verbose = false;
		} else {
			// The user passed more than 2 arguments, or something else unexpected happened.
			System.out.println("This program must be executed with no more than");
			System.out.println("two arguments.");
			System.out.println("Arg1: The case to execute (case one, case two, or");
			System.out.println("      case three). See README for more information.");
			System.out.println("Arg2: (Optional) Use 'v' to make the OS print");
			System.out.println("      its process verbosely.");
			System.out.println("Example:");
			System.out.println("java OS 4 v");
			System.out.println("	-This will execute the OS, all cases");
			System.out.println("	 consecutively, in a verbose manner.");
			System.out.println("java OS 2 v");
			System.out.println("     -This will execute the OS, case 2, verbose.");
			System.out.println("java OS 3");
			System.out.println("     -This will execute the OS, case 3, quiet.");
			System.out.println("java OS");
			System.out.println("     -This will execute the OS with default");
			System.out.println("       values - case 1, quiet.");
			caseNumber = -1;
		}

		// Execute the OS
		if(caseNumber != -1) {
			operatingSystem =  new OS(caseNumber, verbose);
			operatingSystem.start();
		}
	}
}