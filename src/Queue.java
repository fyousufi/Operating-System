public class Queue {
	/***Variables***/
	public Process[] jobQueue;
	private static final int NUMBER_OF_JOBS = 20;
	
	/***Constructor***/
	public Queue() {
		jobQueue = new Process[NUMBER_OF_JOBS];
		
		for (int i = 0; i < NUMBER_OF_JOBS; i++) {
			jobQueue[i] = new Process();
		}
	}

	public Queue(Queue queue) {
		jobQueue = new Process[queue.getLength()];
		
		for(int i = 0; i < queue.getLength(); i++) {
			Process temp = new Process(queue.getJob(i));
			setJob(i, temp);
		}
	}

	/***Getters***/
	public int getID(int segment) {
		return jobQueue[segment].getID();
	}

	public int getMemoryRequest(int segment) {
		return jobQueue[segment].getMemoryRequest();
	}

	public int getTimeRequest(int segment) {
		return jobQueue[segment].getTimeRequest();
	}

	public int getMemoryAssigned(int segment) {
		return jobQueue[segment].getMemoryAssigned();
	}

	public int getTimeRemaining(int segment) {
		return jobQueue[segment].getTimeRemaining();
	}

	public String getStatus(int segment) {
		return jobQueue[segment].getStatus();
	}

	public Process getJob(int segment) {
		return jobQueue[segment];
	}

	public int getLength() {
		return jobQueue.length;
	}

	/***Setters***/
	public void setID(int segment, int id){
		jobQueue[segment].setID(id);
	}

	public void setMemoryRequest(int segment, int memoryRequest) {
		jobQueue[segment].setMemoryRequest(memoryRequest);
	}

	public void setTimeRequest(int segment, int timeRequest) {
		jobQueue[segment].setTimeRequest(timeRequest);
	}

	public void setMemoryAssigned(int segment, int memoryAssigned) {
		jobQueue[segment].setMemoryAssigned(memoryAssigned);
	}

	public void setTimeRemaining(int segment, int timeRemaining) {
		jobQueue[segment].setTimeRemaining(timeRemaining);
	}

	public void setStatus(int segment, String status) {
		jobQueue[segment].setStatus(status);
	}

	public void setJob(int segment, Process process) {
		jobQueue[segment] = process;
	}

	/***Functions***/
	public Boolean getUnassignedJobs() {
		// Return true if there are any jobs that need to be assigned - false otherwise.
		for (int i = 0; i < getLength(); i++) {
			if (getStatus(i) == "Waiting") {
				return true;
			}
		}

		return false;
	}

	public Boolean getUnfinishedJobs() {
		// Return true if there are any unfinished jobs.
		for (int i = 0; i < getLength(); i++) {
			if (getStatus(i) != "Finished") {
				return true;
			}
		}

		return false;
	}

	public int numberOfWaitingJobs() {
		// Return the number of jobs that are waiting.
		int count = 0;
		
		for (int i = 0; i < getLength(); i++) {
			if (getStatus(i) == "Waiting") {
				count++;
			}
		}
		
		return count;
	}

	public int numberOfFinishedJobs() {
		// Return the number of jobs that are finished
		int count = 0;
		
		for (int i = 0; i < getLength(); i++) {
			if (getStatus(i) == "Finished") {
				count++;
			}
		}
		
		return count;
	}

	public Queue shortestJobs() {
		// Return a copy of this queue object ordered from shortest job to longest job
		Queue queueCopy = new Queue();

		// Copy the current Queue into the copy Queue
		for (int i = 0; i < getLength(); i++) {
			queueCopy.setJob(i, getJob(i));
		}

		for (int i = 0; i < getLength() - 1; i++) {
			for (int j = 0; j < getLength() - 1 - i; j++) {
				if (queueCopy.getTimeRequest(j) > queueCopy.getTimeRequest(j + 1)) {
					Process temp = queueCopy.getJob(j);
					queueCopy.setJob(j, queueCopy.getJob(j + 1));
					queueCopy.setJob(j + 1, temp);
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
			output += jobQueue[i].getTimeRequest() + "\t";
			output += jobQueue[i].getID() + "\t";
			output += jobQueue[i].getMemoryAssigned() + "\t";
			output += jobQueue[i].getMemoryRequest() + "\t\t";
			output += jobQueue[i].getTimeRemaining() + "\t\t";
			output += jobQueue[i].getStatus() + "\n";
		}

		output += "=========================================================================";

		return output;
	}
}