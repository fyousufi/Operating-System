
public class cases {
	Boolean caseTerminated = false;
	int TIME_LIMIT = 20;
	int countoftimeSliceFull = 0;
	Queue processes;
	MainMemory mainMemory;
	Boolean verbose = false;
	int numberOfFinishedProcess = 0;
	
	public void executeOS() {
		
	}
	
	/***Tick***/
	public void timeUnit() {
		countoftimeSliceFull++;
		mainMemory.timeSliceFull();
		System.out.println("=========================================================================");
		System.out.println("Time Unit: " + countoftimeSliceFull);
		System.out.println(processes.toString());
		System.out.println("Waiting: " + processes.numberOfWaitingProcess() + "\t\t");
		System.out.println("Total Memory Wasted: " + mainMemory.totalWastedMemory());
		System.out.println("=========================================================================");
		System.out.println("\n\n\n");
	}

	/***Functions***/
	public void setVerbose(Boolean verbose) {
		this.verbose = verbose;
	}

	public int numberOfFinishedProcesses() {
		if (countoftimeSliceFull < TIME_LIMIT) {
			//The case hasn't been run yet... return a null value.
			return -1;
		}

		return numberOfFinishedProcess;
	}
}