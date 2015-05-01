/**
 * 
 * Case Three : Shortest Job First (First Fit)
 *
 */
public class caseThree extends cases {
	Queue SJFjobs; // Copy of jobs ordered from the shortest job first to the longest job last as defined in constructor we have jobs sorted 

	/***Constructor***/
	public caseThree(Queue processes, MainMemory mainMemory) {
		this.processes = processes;
		this.mainMemory = mainMemory;
		//this is where we use sorting to QuickSort to sort the processes based on time request 
		SJFjobs = processes.shortestProcess();
	}

	/***Run the OS on Case Type 3***/
	@Override
	public void run() {
		while (!caseTerminated) {
			if (detail) { System.out.println("I'm running!"); }
			// Assign Jobs to memory
			Boolean assigned = false;
			int count = 0;
			int jobToAssign = 0;
			
			while (!assigned) {
				// If there are no available positions in the memory left
				if (!mainMemory.memoryAvailable()) {
					assigned = true;
				}

				// If there are no more Jobs that have not been completed or currently assigned
				if (!processes.getUnassignedProcess()) {
					assigned = true;
				}

				// If our count exceeds the length of our processes we terminate the scheduler 
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

				// Begin assigning processes until the above conditions are no longer true meaning assigned is set to "true" at that point.
				if (!assigned){
					// This section should be skipped if assigned is true.
					if (count < processes.getLength()) {
						// During the time unit, there are still Jobs that haven't been checked that may need to be assigned.
						Boolean success = false;
						int minimum = Integer.MAX_VALUE;
						int index = -1;
						
						for (int i = mainMemory.firstAvailableMemorySlot(); i < mainMemory.numberOfSlots && success == false; i++) {
							if (!mainMemory.getInUse(i)) {
								// This memory module is not in use.
								if (processes.getMemoryRequest(jobToAssign) < mainMemory.getSize(i)) {
									if (detail) { System.out.println("A"); }
									
									
									if (mainMemory.slotSize[i] - processes.getMemoryRequest(jobToAssign) < minimum && mainMemory.slotSize[i] - processes.getMemoryRequest(jobToAssign) >= 0) {
										minimum = mainMemory.slotSize[i] - processes.getMemoryRequest(jobToAssign);
										index = i;
									}


									if (detail) { System.out.println("B"); }
									
									
								
									if (detail) { System.out.println("C"); }
									// This job can fit in the memory block
									if (index != -1) {
										mainMemory.assignMemory(index, processes.getProcess(jobToAssign));
										success = true;
									}
									
								
								}
							}
						}	    
					}
				}

				// Increase the count through the processes queue.
				count++;
			}

			// If verbose, print what this tick looks like
			if (detail) {
				System.out.println(mainMemory.toString());
			}

			//Increase the time counter/unit by 1 meaning we move on to the next unit.
			timeUnit();

			// Break the while loop if all jobs are done.
			if (!processes.getUnfinishedProcess()) {
				caseTerminated = true;
			}

			//Break the while loop if one more time unit will be the 20th (max) time unit but this can change, per the instructions.
			if (countoftimeSliceFull >= TIME_LIMIT) {
				caseTerminated = true;
			}
		}

		//Now that this case has executed, output the total number of processes completed.
		numberOfFinishedProcess = processes.numberOfFinishedProcess();
		System.out.println("Total number of finished processes: " + numberOfFinishedProcess);
	}
}