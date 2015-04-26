public class ProcessGenerator {
	/***Variables***/
	public Queue jobqueue;

	/***Constructor***/
	public ProcessGenerator(Queue jobqueue) {
		this.jobqueue = jobqueue;

		for (int i = 0; i < 20; i++) {
			// Generate what memory request should be
			int memoryRequest = 0;
			
			while (memoryRequest < 24 || memoryRequest > 100) {
				memoryRequest = (int)(Math.random()*100+24);
			}
			
			jobqueue.setMemoryRequest(i, memoryRequest);

			// Generate time request
			int timeRequest = 0;
			
			while (timeRequest < 2 || timeRequest > 10) {
				timeRequest = (int)(Math.random()*10+2);
			}
			
			jobqueue.setTimeRequest(i, timeRequest);

			// Set other variables for this Job to their defaults.
			jobqueue.setID(i, i);
			jobqueue.setTimeRemaining(i, timeRequest);
		}
	}
}