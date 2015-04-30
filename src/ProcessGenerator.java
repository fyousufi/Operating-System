/**
 * 
 * ProcessGenerator class to generate random processes
 *
 */
public class ProcessGenerator {
	/***Variables***/
	public Queue processQueue;
	
	// Change these variables to set boundaries for memory request
	public int minMemoryRequest = 50;
	public int maxMemoryRequest = 600;
	
	// Change these variables to set boundaries for time request
	public int minTimeRequest = 2;
	public int maxTimeRequest = 10;

	/***Constructor***/
	public ProcessGenerator(Queue processQueue) {
		this.processQueue = processQueue;
		
		// Generate random memory request and random time request
		for (int i = 0; i < processQueue.getLength(); i++) {
			int memoryRequest = 0;
			
			while (memoryRequest < minMemoryRequest || memoryRequest > maxMemoryRequest) {
				memoryRequest = (int) (Math.random() * maxMemoryRequest + minMemoryRequest);
			}
			
			processQueue.setMemoryRequest(i, memoryRequest);

			int timeRequest = 0;
			
			while (timeRequest < minTimeRequest || timeRequest > maxTimeRequest) {
				timeRequest = (int) (Math.random() * maxTimeRequest + minTimeRequest);
			}
			
			processQueue.setTimeRequest(i, timeRequest);

			processQueue.setID(i, i);
			processQueue.setTimeRemaining(i, timeRequest);
		}
	}
}