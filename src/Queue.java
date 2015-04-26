public class Queue {
	/***Variables***/
	public Process[] processQueue;
	private static final int NUMBER_OF_JOBS = 20;
	
	/***Constructor***/
	public Queue() {
		processQueue = new Process[NUMBER_OF_JOBS];
		
		for (int i = 0; i < NUMBER_OF_JOBS; i++) {
			processQueue[i] = new Process();
		}
	}

	public Queue(Queue queue) {
		processQueue = new Process[queue.getLength()];
		
		for(int i = 0; i < queue.getLength(); i++) {
			Process temp = new Process(queue.getProcess(i));
			setProcess(i, temp);
		}
	}

	/***Getters***/
	public int getID(int segment) {
		return processQueue[segment].getID();
	}

	public int getMemoryRequest(int segment) {
		return processQueue[segment].getMemoryRequest();
	}

	public int getTimeRequest(int segment) {
		return processQueue[segment].getTimeRequest();
	}

	public int getMemoryAssigned(int segment) {
		return processQueue[segment].getMemoryAssigned();
	}

	public int getTimeRemaining(int segment) {
		return processQueue[segment].getTimeRemaining();
	}

	public String getStatus(int segment) {
		return processQueue[segment].getStatus();
	}

	public Process getProcess(int segment) {
		return processQueue[segment];
	}

	public int getLength() {
		return processQueue.length;
	}

	/***Setters***/
	public void setID(int segment, int id){
		processQueue[segment].setID(id);
	}

	public void setMemoryRequest(int segment, int memoryRequest) {
		processQueue[segment].setMemoryRequest(memoryRequest);
	}

	public void setTimeRequest(int segment, int timeRequest) {
		processQueue[segment].setTimeRequest(timeRequest);
	}

	public void setMemoryAssigned(int segment, int memoryAssigned) {
		processQueue[segment].setMemoryAssigned(memoryAssigned);
	}

	public void setTimeRemaining(int segment, int timeRemaining) {
		processQueue[segment].setTimeRemaining(timeRemaining);
	}

	public void setStatus(int segment, String status) {
		processQueue[segment].setStatus(status);
	}

	public void setProcess(int segment, Process process) {
		processQueue[segment] = process;
	}

	/***Functions***/
	public Boolean getUnassignedProcess() {
		// Return true if there are any jobs that need to be assigned - false otherwise.
		for (int i = 0; i < getLength(); i++) {
			if (getStatus(i) == "Waiting") {
				return true;
			}
		}

		return false;
	}

	public Boolean getUnfinishedProcess() {
		// Return true if there are any unfinished jobs.
		for (int i = 0; i < getLength(); i++) {
			if (getStatus(i) != "Finished") {
				return true;
			}
		}

		return false;
	}

	public int numberOfWaitingProcess() {
		// Return the number of jobs that are waiting.
		int count = 0;
		
		for (int i = 0; i < getLength(); i++) {
			if (getStatus(i) == "Waiting") {
				count++;
			}
		}
		
		return count;
	}

	public int numberOfFinishedProcess() {
		// Return the number of jobs that are finished
		int count = 0;
		
		for (int i = 0; i < getLength(); i++) {
			if (getStatus(i) == "Finished") {
				count++;
			}
		}
		
		return count;
	}

	public Queue shortestProcess() {
		// Return a copy of this queue object ordered from shortest job to longest job
		Queue queueCopy = new Queue();

		// Copy the current Queue into the copy Queue
		for (int i = 0; i < getLength(); i++) {
			queueCopy.setProcess(i, getProcess(i));
		}

		for (int i = 0; i < getLength() - 1; i++) {
			for (int j = 0; j < getLength() - 1 - i; j++) {
				if (queueCopy.getTimeRequest(j) > queueCopy.getTimeRequest(j + 1)) {
					Process temp = queueCopy.getProcess(j);
					queueCopy.setProcess(j, queueCopy.getProcess(j + 1));
					queueCopy.setProcess(j + 1, temp);
				}
			}
		}

		// Return the copy of the Queue sorted into SJF order.
		return queueCopy;
	}

	/***toString***/
	public String toString() {
		String output = "=========================================================================\n";

		output += "Time\tID\tSegment\tMem Request\tTime Remain\tMessages\n";
		output += "----\t--\t-------\t-----------\t-----------\t--------\n";

		for (int i = 0; i < getLength(); i++) {
			output += processQueue[i].getTimeRequest() + "\t";
			output += processQueue[i].getID() + "\t";
			output += processQueue[i].getMemoryAssigned() + "\t";
			output += processQueue[i].getMemoryRequest() + "\t\t";
			output += processQueue[i].getTimeRemaining() + "\t\t";
			output += processQueue[i].getStatus() + "\n";
		}

		output += "=========================================================================";

		return output;
	}
}