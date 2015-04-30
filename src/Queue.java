/**
 * 
 * Queue class to store processes
 *
 */
public class Queue {
	
	/** Variables **/
	public Process[] processQueue;
	private static final int NUMBER_OF_PROCESSES = 10;
	
	/** Constructor **/
	public Queue() {
		processQueue = new Process[NUMBER_OF_PROCESSES];
		
		for (int i = 0; i < NUMBER_OF_PROCESSES; i++) {
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

	/** Getters **/
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

	/** Setters **/
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

	/** Functions **/
	/*
	 * Returns true if there are any Unassigned processes
	 */
	public Boolean getUnassignedProcess() {		
		for (int i = 0; i < getLength(); i++) {
			if (getStatus(i) == "Waiting") {
				return true;
			}
		}

		return false;
	}

	/*
	 * Returns true if there any Unfinished processes
	 */
	public Boolean getUnfinishedProcess() {		
		for (int i = 0; i < getLength(); i++) {
			if (getStatus(i) != "Finished") {
				return true;
			}
		}

		return false;
	}

	/*
	 * Returns the number of Waiting Processes
	 */
	public int numberOfWaitingProcess() {
		int count = 0;
		
		for (int i = 0; i < getLength(); i++) {
			if (getStatus(i) == "Waiting") {
				count++;
			}
		}
		
		return count;
	}
	
	/*
	 * Returns the number of Finished processes
	 */
	public int numberOfFinishedProcess() {
		int count = 0;
		
		for (int i = 0; i < getLength(); i++) {
			if (getStatus(i) == "Finished") {
				count++;
			}
		}
		
		return count;
	}
	
	/*
	 * Returns the queue with shortest jobs first
	 */
	public Queue shortestProcess() {
		Queue queueCopy = new Queue();

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

		return queueCopy;
	}

	/** toString **/
	@Override
	public String toString() {
		String output = "-------------------------------------------------------------------------\n";

		output += "ID\tSegment\tMem Request\tTime\tTime Remaining\tMessages\n";
		output += "--\t-------\t-----------\t----\t--------------\t--------\n";

		for (int i = 0; i < getLength(); i++) {
			output += processQueue[i].getID() + "\t";
			output += processQueue[i].getMemoryAssigned() + "\t   ";
			output += processQueue[i].getMemoryRequest() + "\t\t";
			output += processQueue[i].getTimeRequest() + "\t";
			output += processQueue[i].getTimeRemaining() + "\t\t";
			output += processQueue[i].getStatus() + "\n";
		}

		output += "-------------------------------------------------------------------------";

		return output;
	}
}