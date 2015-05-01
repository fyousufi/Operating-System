/**
 * 
 * Case Two : First Come First Served (Best Fit)
 * This algorithm loops through the process queue in order and assesses best fit using custom algoirthm 
 *
 */
public class caseTwo extends cases {

	/***Constructor***/
	public caseTwo(Queue processes, MainMemory mainMemory) {
		this.processes = processes;
		this.mainMemory = mainMemory;
	}

	/***Execute the OS on Case Two***/
	@Override
	public void run() {
		while (!caseTerminated) {
			if (detail) { System.out.println("I'm running!"); }
			
			//Assign Jobs to memory
			Boolean assigned = false;
			int count = 0;
			
			while (!assigned) {
				//If there are no available positions in the memory left
				if (!mainMemory.memoryAvailable()) {
					assigned = true;
				}

				//If there are no more processes that have not been completed or currently assigned
				if (!processes.getUnassignedProcess()) {
					assigned = true;
				}

				//If every job has been checked, but for whatever reason (i.e. none of the jobs
				//can fit in the currently available memory) a Job cannot be assigned.
				if (count >= processes.getLength()) {
					assigned = true;
				}

				//Increase the count until it equals the position in the processes queue of an unassigned Process within the queue
				Boolean testDone = false;
				
				while (!testDone) {
					if (count < processes.getLength()) {
						if (processes.getStatus(count) == "Waiting") {
							testDone = true;
						}
					}

					else {
						assigned = true;
						testDone = true;
					}

					if(testDone == false) {
						count++;
					}
				}


				// Begin assigning processes until the above conditions are no longer true meaning assigned is set to "true" at that point.
				if (!assigned){
					// This section is not true if the count exceeds length of processes
					if (count < processes.getLength()) {
						// During this unit, there are still processes that haven't been checked that may need to be assigned.
						Boolean success = false;
						
						int minimum = Integer.MAX_VALUE;
						int index = -1;
						
						for (int i = mainMemory.firstAvailableMemorySlot(); i < mainMemory.numberOfSlots && success == false; i++) {
							if (!mainMemory.getInUse(i)) {
								// This memory module is not in use.
								if (processes.getMemoryRequest(count) < mainMemory.getSize(i)) {
									if (detail) { System.out.println("A"); }
									
									if (mainMemory.slotSize[i] - processes.getMemoryRequest(count) < minimum && mainMemory.slotSize[i] - processes.getMemoryRequest(count) >= 0) {
										minimum = mainMemory.slotSize[i] - processes.getMemoryRequest(count);
										index = i;
									}

									if (detail) { System.out.println("B"); }

									if (detail) { System.out.println("C"); }					
								}
							}
						}
						
						if (index != -1) {
							mainMemory.assignMemory(index, processes.getProcess(count));
							success = true;
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

			//Break the while loop if all jobs are done. We say this case has been terminated
			if(!processes.getUnfinishedProcess()) {
				caseTerminated = true;
			}

			//Break the while loop if one more time unit will be the 20th (max) time unit but this can change, per the instructions.
	
			if (countoftimeSliceFull >= TIME_LIMIT) {
				caseTerminated = true;
			}
		}

		//Now that this case has executed, output the total number of processes completed.
		numberOfFinishedProcess = processes.numberOfFinishedProcess();
		System.out.println("Total number of finished jobs: " + numberOfFinishedProcess);
	}
}