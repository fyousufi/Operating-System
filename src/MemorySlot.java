/**
 * 
 * Memory Slot class which stores information relevant to each Memory Slot
 *
 */
public class MemorySlot {
	
	/***Variables***/
	int segmentNumber;
	int size;
	Boolean inUse;
	int wastedSpace;
	Process process;

	/***Constructor***/
	public MemorySlot() {
		segmentNumber = -1;
		size = -1;
		inUse = false;
		wastedSpace = -1;
		process = new Process();
	}

	public MemorySlot(int segmentNumber, int size, Boolean inUse, int wastedSpace, Process process) {
		this.segmentNumber = segmentNumber;
		this.size = size;
		this.inUse = inUse;
		this.wastedSpace = wastedSpace;
		this.process = process;
	}

	/***Getters***/
	public int getSegmentNumber() {
		return segmentNumber;
	}

	public int getSize() {
		return size;
	}

	public Boolean getInUse() {
		return inUse;
	}

	public int getWastedSpace() {
		return wastedSpace;
	}

	public Process getJob() {
		return process;
	}

	/***Setters***/
	public void setSegmentNumber(int segmentNumber) {
		this.segmentNumber = segmentNumber;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setInUse(Boolean inUse) {
		this.inUse = inUse;
	}

	public void setWastedSpace(int wastedSpace) {
		this.wastedSpace = wastedSpace;
	}

	public void setJob(Process process) {
		this.process = process;
	}
}