
public class MemoryModule {
	
	/***Variables***/
	int segmentNumber;
	int size; // in kilobytes
	Boolean inUse;
	int wastedSpace;
	Process process;

	/***Constructor***/
	public MemoryModule() {
		segmentNumber = -1;
		size = -1;
		inUse = false;
		wastedSpace = -1;
		process = new Process();
	}

	public MemoryModule(int segmentNumber, int size, Boolean inUse, int wastedSpace, Process myJob) {
		this.segmentNumber = segmentNumber;
		this.size = size;
		this.inUse = inUse;
		this.wastedSpace = wastedSpace;
		this.process = myJob;
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

	public void setJob(Process myJob) {
		this.process = myJob;
	}
}