public class ProcessGenerator {
	/***Variables***/
	public Queue processQueue;

	/***Constructor***/
	public ProcessGenerator(Queue processQueue) {
		this.processQueue = processQueue;

		for (int i = 0; i < 10; i++) {
			// Generate what memory request should be
			int memoryRequest = 0;
			
			while (memoryRequest < 24 || memoryRequest > 100) {
				memoryRequest = (int) (Math.random()*100+24);
			}
			
			processQueue.setMemoryRequest(i, memoryRequest);

			// Generate time request
			int timeRequest = 0;
			
			while (timeRequest < 2 || timeRequest > 10) {
				timeRequest = (int)(Math.random()*10+2);
			}
			
			processQueue.setTimeRequest(i, timeRequest);

			// Set other variables for this Job to their defaults.
			processQueue.setID(i, i);
			processQueue.setTimeRemaining(i, timeRequest);
		}
	}
}