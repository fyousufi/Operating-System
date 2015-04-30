/**
 * 
 * Main Memory class which stores information about the Main Memory
 *
 */
public class MainMemory {

	/** Variables **/
	MemorySlot[] Memory;
	int numberOfSlots = 5; // Change this variable to change the number of slots for the Main Memory
	int[] slotSize = {100, 500, 200, 300, 600}; // Set the size of each slot in the Main Memory
	int lastProcessed;

	/** Constructor **/
	public MainMemory() {
		Memory = new MemorySlot[numberOfSlots];

		for(int i = 0; i < numberOfSlots; i++) {
			Memory[i] = new MemorySlot();
			Memory[i].setSegmentNumber(i);
			Memory[i].setSize(slotSize[i]);
			Memory[i].setInUse(false);
			Memory[i].setWastedSpace(0);
		}
	}

	/** Functions **/
	/*
	 * Returns true if the assignment of Process was successful
	 */
	public Boolean assignMemory(int segmentNumber, Process process) {
		if (process.getMemoryRequest() < Memory[segmentNumber].getSize()) {
			if (!Memory[segmentNumber].getInUse()) {
				Memory[segmentNumber].setJob(process);
				setInUse(segmentNumber, true);

				setMemoryAssigned(segmentNumber, segmentNumber);
				setStatus(segmentNumber, "Ready");
				setTimeRemaining(segmentNumber, getTimeRequest(segmentNumber));

				setWastedSpace(segmentNumber, getSize(segmentNumber) - getMemoryRequest(segmentNumber));

				return true;
			}
		}

		return false;
	}

	/*
	 * Returns true if there are any memory available
	 */
	public Boolean memoryAvailable() {
		for (int i = 0; i < numberOfSlots; i++) {
			if (Memory[i].getInUse() == false) {
				return true;
			}
		}

		return false;
	}

	/*
	 * Returns the first available memory slot index
	 */
	public int firstAvailableMemorySlot() {
		if (memoryAvailable()) {
			for (int i = 0; i < numberOfSlots; i++) {
				if (Memory[i].getInUse() == false) {
					return i;
				}
			}
		}

		return -1;
	}

	/*
	 * Remove a process from memory slot and reset the memory slot
	 */
	public Boolean removeMemory(int segmentNumber) {
		Memory[segmentNumber].setInUse(false);
		Memory[segmentNumber] = new MemorySlot();
		Memory[segmentNumber].setSize(slotSize[segmentNumber]);

		return true;
	}

	/*
	 * Returns the total amount of wasted memory for both in use and not in use slots
	 */
	public int totalWastedMemory() {
		int total = 0;

		for (int i = 0; i < numberOfSlots; i++) {
			if (Memory[i].getInUse()) {
				total += getWastedSpace(i);
			} else {
				total += getSize(i);
			}
		}

		return total;
	}

	/*
	 * Checks all processes in memory in a round robin fashion
	 */
	public void timeSliceFull() {
		int processors = 2; // Specify the number of processors to execute at one time
		Boolean hasChecked = false;
		int initialProcessed = lastProcessed;
		int numberProcessed = 0;

		while (!hasChecked) {
			if (lastProcessed == numberOfSlots) {
				lastProcessed = 0;
			}

			Boolean success = false;
			
			if (Memory[lastProcessed].getInUse()) {
				if (getStatus(lastProcessed) == "Ready") {
					// Set the "Ready" process to "Running"
					setStatus(lastProcessed, "Running");
					success = true;
				} else if (getStatus(lastProcessed) == "Running") {
					// Check the "Running" process
					setTimeRemaining(lastProcessed, getTimeRemaining(lastProcessed) - 1);

					// Process has finished execution
					if (getTimeRemaining(lastProcessed) < 0) {
						setTimeRemaining(lastProcessed, 0);
						setStatus(lastProcessed, "Finished");
						removeMemory(lastProcessed);
					}

					success = true;
				}
			}

			if (!success) {
				lastProcessed++;
			} else if (success) {
				lastProcessed++;
				numberProcessed++;
			}

			if (numberProcessed >= processors) {
				hasChecked = true;
			} else if (lastProcessed == initialProcessed) {
				hasChecked = true;
			}
		}
	}

	/** Getters **/
	/* For Memory */
	public int getSegmentNumber(int segmentNumber) {
		return Memory[segmentNumber].getSegmentNumber();
	}

	public int getSize(int segmentNumber) {
		return Memory[segmentNumber].getSize();
	}

	public Boolean getInUse(int segmentNumber) {
		return Memory[segmentNumber].getInUse();
	}

	public int getWastedSpace(int segmentNumber) {
		return Memory[segmentNumber].getWastedSpace();
	}

	/* For Job */
	public int getID(int segmentNumber) {
		return (Memory[segmentNumber].getJob()).getID();
	}

	public int getMemoryRequest(int segmentNumber) {
		return (Memory[segmentNumber].getJob()).getMemoryRequest();
	}

	public int getTimeRequest(int segmentNumber) {
		return (Memory[segmentNumber].getJob()).getTimeRequest();
	}

	public int getMemoryAssigned(int segmentNumber) {
		return (Memory[segmentNumber].getJob()).getMemoryAssigned();
	}

	public int getTimeRemaining(int segmentNumber) {
		return (Memory[segmentNumber].getJob()).getTimeRemaining();
	}

	public String getStatus(int segmentNumber) {
		return (Memory[segmentNumber].getJob()).getStatus();
	}

	/** Setters **/
	/* For Memory */
	public void setSegmentNumber(int segmentNumber, int changedSegmentNumber) {
		Memory[segmentNumber].setSegmentNumber(changedSegmentNumber);
	}

	public void setSize(int segmentNumber, int size) {
		Memory[segmentNumber].setSize(size);
	}

	public void setInUse(int segmentNumber, Boolean inUse) {
		Memory[segmentNumber].setInUse(inUse);
	}

	public void setWastedSpace(int segmentNumber, int wastedSpace) {
		Memory[segmentNumber].setWastedSpace(wastedSpace);
	}

	/* For Job */
	public void setID(int segmentNumber, int id) {
		(Memory[segmentNumber].getJob()).setID(id);
	}

	public void setMemoryRequest(int segmentNumber, int memoryRequest) {
		(Memory[segmentNumber].getJob()).setMemoryRequest(memoryRequest);
	}

	public void setTimeRequest(int segmentNumber, int timeRequest) {
		(Memory[segmentNumber].getJob()).setTimeRequest(timeRequest);
	}

	public void setMemoryAssigned(int segmentNumber, int memoryAssigned) {
		(Memory[segmentNumber].getJob()).setMemoryAssigned(memoryAssigned);
	}

	public void setTimeRemaining(int segmentNumber, int timeRemaining) {
		(Memory[segmentNumber].getJob()).setTimeRemaining(timeRemaining);
	}

	public void setStatus(int segmentNumber, String status) {
		(Memory[segmentNumber].getJob()).setStatus(status);
	}

	/** toString **/
	@Override
	public String toString() {
		String output = "";

		for (int i = 0; i < Memory.length; i++) {
			output += "Memory Position " + i;
			output += "\n-----------------------------------\n";
			output += "Segment Number: " + Memory[i].getSegmentNumber() + "\n";
			output += "Size: " + Memory[i].getSize() + "\n";
			output += "InUse: " + Memory[i].getInUse() + "\n";
			output += "Wasted Space: " + Memory[i].getWastedSpace() + "\n";
			output += "Job toString:\n";
			output += "-------------------------------------------\n";
			output += (Memory[i].getJob()).toString() + "\n";
			output += "-------------------------------------------\n";

			if (i != Memory.length - 1) {
				output += "-------------------------------------------\n";
			}
		}

		return output;
	}
}