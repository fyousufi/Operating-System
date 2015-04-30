/**
 *
 * Parent class of all the scheduling algorithms.
 *
 */
public class cases {
	int TIME_LIMIT = 20; // specify the time limit here
	Boolean caseTerminated = false;
	int countoftimeSliceFull = 0;
	Queue processes;
	MainMemory mainMemory;
	Boolean detail = false;
	int numberOfFinishedProcess = 0;
	
	public void run() {
		
	}
	
	/***Tick***/
	public void timeUnit() {
		countoftimeSliceFull++;
		mainMemory.timeSliceFull();
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("Time Unit: " + countoftimeSliceFull);
		System.out.println(processes.toString());
		System.out.println("Waiting Processes: " + processes.numberOfWaitingProcess() + "\t\t");
		System.out.println("Total Memory Wasted: " + mainMemory.totalWastedMemory());
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("\n");
	}

	/***Functions***/
	public void setDetail(Boolean detail) {
		this.detail = detail;
	}

	public int numberOfFinishedProcesses() {
		if (countoftimeSliceFull < TIME_LIMIT) {
			//The case hasn't been run yet... return a null value.
			return -1;
		}

		return numberOfFinishedProcess;
	}
}