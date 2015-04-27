/**
 * 
 * Case Three : Shortest Job First (First Fit)
 *
 */
public class caseThree extends cases {
	Queue SJFjobs; // Copy of jobs ordered from the shortest job first to the longest job last

	/***Constructor***/
	public caseThree(Queue processes, MainMemory mainMemory) {
		this.processes = processes;
		this.mainMemory = mainMemory;

		SJFjobs = processes.shortestProcess();
	}

	/***Run the OS on Case Type 3***/
	@Override
	public void run() {
		while (!caseTerminated) {
			if (verbose) { System.out.println("I'm running!"); }
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

				// If every job has been checked, but for whatever reason (i.e. none of the jobs
				// can fit in the currently available memory) a Job cannot be assigned.
				if (count >= processes.getLength()) {
					assigned = true;
				}

				// Increase the count until it equals the position in the job queue of an unassigned Job
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
				if (!assigned){
					// This section should be skipepd if assigned is true.
					if (count < processes.getLength()) {
						// During this tick, there are still Jobs that haven't been checked that may need to be assigned.
						Boolean success = false;
						
						for (int i = mainMemory.firstAvailableMemorySlot(); i < mainMemory.size && success == false; i++) {
							if (!mainMemory.getInUse(i)) {
								// This memory module is not in use.
								if (processes.getMemoryRequest(jobToAssign) < mainMemory.getSize(i)) {
									if (verbose) { System.out.println("A"); }
									int[] temp = new int[mainMemory.size];
									
									for (int j = 0; j < mainMemory.size; j++) {
										temp[j] = mainMemory.sizeArray[j] - processes.getMemoryRequest(j);
									}

									if (verbose) { System.out.println("B"); }
									int currentSmallest = 0;
									
									for (int j = 0; currentSmallest + j < temp.length; j++) {
										if (temp[currentSmallest + j] < temp[currentSmallest] && temp[currentSmallest + j] >= 0) {
											currentSmallest = currentSmallest + j;
											j = 0;
										}
									}

									if (verbose) { System.out.println("C"); }
									// This job can fit in the memory block
									mainMemory.assignMemory(i, processes.getProcess(jobToAssign));
									success = true;
								}
							}
						}	    
					}
				}

				// Increase the count through the job queue.
				count++;
			}

			// If verbose, print what this tick looks like
			if (verbose) {
				System.out.println(mainMemory.toString());
			}

			// Increase the tick by 1.
			timeUnit();

			// Break the while loop if all jobs are done.
			if (!processes.getUnfinishedProcess()) {
				caseTerminated = true;
			}

			// Break the while loop if one more tick will be the max tick, per the instructions.
			// Comment this out if you wish for the program to execute until ALL jobs reach a "Finished" state.
			if (countoftimeSliceFull >= TIME_LIMIT) {
				caseTerminated = true;
			}
		}

		// Now that this case has executed, output the total number of jobs completed.
		numberOfFinishedProcess = processes.numberOfFinishedProcess();
		System.out.println("Total number of finished processes: " + numberOfFinishedProcess);
	}
}