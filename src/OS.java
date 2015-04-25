
public class OS {
	
	/***Variables***/
	int caseNumber;
	Boolean verbose;
	
	Queue jobs;
	MainMemory mainMemory;
	JobGenerator jobGenerator;

	/***Constructor***/
	public OS(int casetype, Boolean verbose) {
		this.caseNumber = casetype;
		this.verbose = verbose;

		// Construct the hardware
		jobs = new Queue();
		jobGenerator = new JobGenerator(jobs);
		mainMemory = new MainMemory();
		
		if (verbose == true) {
			System.out.println(jobs.toString());
			//System.out.println(generator.toString());
			System.out.println(mainMemory.toString());
		}
	}

	/***Start***/
	public void start() {
		if (caseNumber == 1) { // Operate caseNumber 1
			caseOne execute = new caseOne(jobs, mainMemory);
			execute.setVerbose(verbose);
<<<<<<< HEAD
			execute.executeOS();
		}

		else if(caseNumber == 2) {
			//Operate casetype 2
			caseTwo execute = new caseTwo(jobs, main);
=======
			execute.run();
		} else if (caseNumber == 2) { // Operate caseNumber 2
			caseTwo execute = new caseTwo(jobs, mainMemory);
>>>>>>> origin/master
			execute.setVerbose(verbose);
			execute.run();
		} else if (caseNumber == 3) { // Operate caseNumber 3
			caseThree execute = new caseThree(jobs, mainMemory);
			execute.setVerbose(verbose);
			execute.run();
		} else if (caseNumber == 4) { // Operate caseNumber 4
			caseOne executeOne;
			caseTwo executeTwo;
			caseThree executeThree;

			// Make a copy of the original array, so that all three executions share the same input data
			Queue queueCopy = new Queue(jobs);

			// Start process 1
			System.out.println("=======================================================");
			System.out.println("==Case One=============================================");
			System.out.println("=======================================================");
			executeOne = new caseOne(jobs, mainMemory);
			executeOne.setVerbose(verbose);
			executeOne.executeOS();

			// Start process 2
			System.out.println("=======================================================");
			System.out.println("==Case Two=============================================");
			System.out.println("=======================================================");
			mainMemory = new MainMemory();
			jobs = new Queue(queueCopy);
			executeTwo = new caseTwo(jobs, mainMemory);
			executeTwo.setVerbose(verbose);
			executeTwo.run();

			// Start process 3
			System.out.println("=======================================================");
			System.out.println("==Case Three===========================================");
			System.out.println("=======================================================");
			mainMemory = new MainMemory();
			jobs = new Queue(queueCopy);
			executeThree = new caseThree(jobs, mainMemory);
			executeThree.setVerbose(verbose);
			executeThree.run();

			// Compare the number of finished jobs for each case type
			System.out.println("\n\n\n");
			System.out.println("Case One Finished " + executeOne.numberOfFinishedJobs() + " Jobs.");
			System.out.println("Case Two Finished " + executeTwo.numberOfFinishedJobs() + " Jobs.");
			System.out.println("Case Three Finished " + executeThree.numberOfFinishedJobs() + " Jobs.");
			
			if (executeOne.numberOfFinishedJobs() > executeTwo.numberOfFinishedJobs() && executeOne.numberOfFinishedJobs() > executeThree.numberOfFinishedJobs()) {
				System.out.println("Case One completed the most processes.");
			} else if (executeTwo.numberOfFinishedJobs() > executeOne.numberOfFinishedJobs() && executeTwo.numberOfFinishedJobs() > executeThree.numberOfFinishedJobs()) {
				System.out.println("Case Two completed the most processes.");
			} else if (executeThree.numberOfFinishedJobs() > executeOne.numberOfFinishedJobs() && executeThree.numberOfFinishedJobs() > executeTwo.numberOfFinishedJobs()) {
				System.out.println("Case Three completed the most processes.");
			} else {
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

	/***Main***/
	public static void main(String[] args) {
		//arg1: Allowed values are 1, 2, 3, OR 4. This represents the three
		//	    different cases the HOS can execute. For example, 1 is
		//	    case 1, 2 is case 2, 3 is case 3. If you use 4, it will
		//	    perform all cases, with identical data.
		//arg2: Allowed value is v. This toggles the HOS to print
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
			System.out.println("Executing the HOS with case " + caseNumber + ".");
		} else if (args.length == 1) {
			//The user only passed an arg1
			caseNumber = Integer.parseInt(args[0]);
			verbose = false;

			// Verify the caseNumber
			if(caseNumber != 1 && caseNumber != 2 && caseNumber != 3 && caseNumber != 4) {
				caseNumber = 1;
			}

			// Execute the OS
			System.out.println("Executing the HOS with case " + caseNumber + ".");
		} else if (args.length == 0) {
			// The user passed no args, make the arg the default values
			System.out.println("NOTE: Arguments not specified. HOS will execute,");
			System.out.println("      assuming default values (case 1, quiet).");
			System.out.println("Please pass either one or two arguments to");
			System.out.println("to specify how the HOS should operate.");
			caseNumber = 1;
			verbose = false;
		} else {
			// The user passed more than 2 arguments, or something else unexpected happened.
			System.out.println("This program must be executed with no more than");
			System.out.println("two arguments.");
			System.out.println("Arg1: The case to execute (case one, case two, or");
			System.out.println("      case three). See README for more information.");
			System.out.println("Arg2: (Optional) Use 'v' to make the HOS print");
			System.out.println("      its process verbosely.");
			System.out.println("Example:");
			System.out.println("java HOS 4 v");
			System.out.println("	-This will execute the HOS, all cases");
			System.out.println("	 consecutively, in a verbose manner.");
			System.out.println("java HOS 2 v");
			System.out.println("     -This will execute the HOS, case 2, verbose.");
			System.out.println("java HOS 3");
			System.out.println("     -This will execute the HOS, case 3, quiet.");
			System.out.println("java HOS");
			System.out.println("     -This will execute the HOS with default");
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