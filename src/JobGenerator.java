public class JobGenerator {
	/***Variables***/
	public Queue jobqueue;

	/***Constructor/Execution***/
	public JobGenerator(Queue jobqueue) {
		this.jobqueue = jobqueue;

		for(int i = 0; i < 20; i++) {
			//Generate what memrequest should be
			int memRequest = 0;
			while(memRequest < 24 || memRequest > 100) {
				memRequest = (int)(Math.random()*100+24);
			}
			jobqueue.setMemoryRequest(i, memRequest);

			//Generate time request
			int timeRequest = 0;
			while(timeRequest < 2 || timeRequest > 10) {
				timeRequest = (int)(Math.random()*10+2);
			}
			jobqueue.setTimeRequest(i, timeRequest);

			//Set other variables for this Job to their defaults.
			jobqueue.setID(i, i);
			jobqueue.setTimeRemaining(i, timeRequest);
		}
	}
}