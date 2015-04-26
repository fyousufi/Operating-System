public class Process {
	/***Variables***/
	int id;
	int memoryRequest;
	int timeRequest;
	int memoryAssigned;
	int timeRemaining;
	String status;

	/***Constructors***/
	public Process() {
		id = -1;
		memoryRequest = -1;
		timeRequest = -1;
		memoryAssigned = -1;
		timeRemaining = timeRequest;
		status = "Waiting";
	}

	public Process(int id, int memRequest, int timeRequest, int memAssigned, int timeRemaining, String status) {
		this.id = id;
		this.memoryRequest = memRequest;
		this.timeRequest = timeRequest;
		this.memoryAssigned = memAssigned;
		this.timeRemaining = timeRemaining;
		this.status = status;
	}

	public Process(Process process) {
		this.id = process.getID();
		this.memoryRequest = process.getMemoryRequest();
		this.timeRequest = process.getTimeRequest();
		this.memoryAssigned = process.getMemoryAssigned();
		this.timeRemaining = process.getTimeRemaining();
		this.status = process.getStatus();
	}

	/***Setters***/
	public void setID(int id) {
		this.id = id;
	}

	public void setMemoryRequest(int memoryRequest) {
		this.memoryRequest = memoryRequest;
	}

	public void setTimeRequest(int timeRequest) {
		this.timeRequest = timeRequest;
	}

	public void setMemoryAssigned(int memoryAssigned) {
		this.memoryAssigned = memoryAssigned;
	}

	public void setTimeRemaining(int timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

	public void setStatus(String status) {
		if (status == "Waiting" || status == "Ready" || status == "Running" || status == "Finished") {
			this.status = status;
		}
	}

	/***Getters***/
	public int getID() {
		return id;
	}

	public int getMemoryRequest() {
		return memoryRequest;
	}

	public int getTimeRequest() {
		return timeRequest;
	}

	public int getMemoryAssigned() {
		return memoryAssigned;
	}

	public int getTimeRemaining() {
		return timeRemaining;
	}

	public String getStatus() {
		return status;
	}

	/***toString***/
	public String toString() {
		return id + " " + memoryRequest + " " + timeRequest + " " + memoryAssigned + " "
				+ timeRemaining + " " + status;
	}
}