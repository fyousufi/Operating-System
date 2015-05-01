/**
 * 
 * Case Four : Shortest Job First Served (First Fit)
 *
 */
public class caseFour extends cases {
	Queue SJFjobs;
	
	/***Constructor- like the rest of the cases takes in processes and Main Memory***/
	public caseFour(Queue processes, MainMemory mainMemory) {
		this.processes = processes;
		this.mainMemory = mainMemory;
		//this is where we use sorting to QuickSort to sort the processes based on time request field 
		SJFjobs = processes.shortestProcess();
	}

	/***Run the OS on Case Four***/
	@Override
	public void run() {
		while (!caseTerminated) {

			// Assign processes to memory
			Boolean assigned = false;
			int count = 0;
			int jobToAssign = 0;

			while (!assigned) {
				// If there are no slots memory left, then our scheduler essentially terminates
				if (!mainMemory.memoryAvailable()) {
					assigned = true;
				}

				// If there are no more processes that have not been completed or currently assigned we terminate
				if (!processes.getUnassignedProcess()) {
					assigned = true;
				}

				// If every process has been checked, but for whatever reason (i.e. none of the processes
				// can fit in the currently available memory) a process cannot be assigned.
				if (count >= processes.getLength()) {
					assigned = true;
				}

				//Increase the count until it equals the position in the processes queue of an unassigned Process within the queue
				Boolean testDone = false;

				while (!testDone) {
					if (count < processes.getLength()) {
						if (SJFjobs.getStatus(count) == "Waiting") {
							jobToAssign = SJFjobs.getID(count);
							testDone = true;
						}
					} else {
						assigned = true;
						testDone = true;
					}

					if (testDone == false) {
						count++;
					}
				}


				// Begin assigning jobs until the above conditions are no longer true.
				if (!assigned) {
					// This section should be skipped if assigned is true.
					if (count < processes.getLength()) {
						// During this unit, there are still processes that haven't been checked that may need to be assigned.
						Boolean success = false;

						for (int i = mainMemory.firstAvailableMemorySlot(); i < mainMemory.numberOfSlots && success == false; i++) {
							if (!mainMemory.getInUse(i)) {
								// This memory module is not in use so we can check if it is a first fit.
								if (processes.getMemoryRequest(jobToAssign) < mainMemory.getSize(i)) {
									// This job can fit in the memory block
									mainMemory.assignMemory(i, processes.getProcess(jobToAssign));
									success = true;
								}
							}
						}
					}
				}

				// Increase the count through the processes queue.
				count++;
			}

			// If detail is ready to be written out, print what the state of main memory looks like
			if (detail) {
				System.out.println(mainMemory.toString());
			}

			//Increase the time counter/unit by 1 meaning we move on to the next unit.
			timeUnit();

			// Break the while loop if all jobs are done.
			if (!processes.getUnfinishedProcess()) {
				caseTerminated = true;

			}

			//Break the while loop if all jobs are done. We say this case has been terminated
			if (countoftimeSliceFull >= TIME_LIMIT) {
				caseTerminated = true;
			}

			//Now that this case has executed, output the total number of processes completed.
			numberOfFinishedProcess = processes.numberOfFinishedProcess();
			System.out.println("Total number of finished processes: " + numberOfFinishedProcess);
		}
	} 
}