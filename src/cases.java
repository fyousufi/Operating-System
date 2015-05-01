/**
 *
 * Parent class of all the scheduling algorithms, hold key information that algorithm will use 
 * It holds the queue of the process and the main memory that will utilize information 
 *
 */
public class cases {
	int TIME_LIMIT = 20; // specify the time limit here, for our purposes we let it run to 20
	Boolean caseTerminated = false;
	int countoftimeSliceFull = 0;
	Queue processes;
	MainMemory mainMemory;
	Boolean detail = false;
	int numberOfFinishedProcess = 0;
	
	//Run method is the overridden method that will determine which algorithm we use 
	//Each case (FCFS First-Fit, FCFS Best-Fit, SJF First/Best-Fit inherits from this parent)
	public void run() {
		
	}
	
	/***Time Unit- we output each time unit's information and header information in a tabular format ***/
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
			//The case hasn't been run yet, so we return -1 and the process has still not exceeded our limit.
			return -1;
		}
		//return total number of Finished process
		return numberOfFinishedProcess;
	}
}