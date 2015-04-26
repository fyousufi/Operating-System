import static org.junit.Assert.*;

import org.junit.Test;

public class ProcessTest {
	private static Process test = new Process();
	private static final int N = 10;
	private static final int MAX = 200;
	private static final int MIN = 100;
	private static String[] statusList = {"Waiting", "Ready", "Running", "Finished"};
	
	@Test
	public void testID() {
		for (int i = 0; i < N; i++) {
			int id = (int) (Math.random() * MAX) - MIN;
			test.setID(id);
			assertEquals(id, test.getID());
		}
	}
	
	@Test
	public void testMemoryRequest() {
		for (int i = 0; i < N; i++) {
			int memoryRequest = (int) (Math.random() * MAX) - MIN;
			test.setMemoryRequest(memoryRequest);;
			assertEquals(memoryRequest, test.getMemoryRequest());
		}
	}
	
	@Test
	public void testTimeRequest() {
		for (int i = 0; i < N; i++) {
			int timeRequest = (int) (Math.random() * MAX) - MIN;
			test.setTimeRequest(timeRequest);
			assertEquals(timeRequest, test.getTimeRequest());
		}
	}
	
	@Test
	public void testMemoryAssigned() {
		for (int i = 0; i < N; i++) {
			int memoryAssigned = (int) (Math.random() * MAX) - MIN;
			test.setMemoryAssigned(memoryAssigned);
			assertEquals(memoryAssigned, test.getMemoryAssigned());
		}
	}
	
	@Test
	public void testTimeRemaining() {
		for (int i = 0; i < N; i++) {
			int timeRemaining = (int) (Math.random() * MAX) - MIN;
			test.setTimeRemaining(timeRemaining);
			assertEquals(timeRemaining, test.getTimeRemaining());
		}
	}
	
	@Test
	public void testStatus() {
		for (int i = 0; i < N; i++) {
			String status = statusList[(int) Math.random() * statusList.length];
			test.setStatus(status);
			assertEquals(status, test.getStatus());
		}
	}
}