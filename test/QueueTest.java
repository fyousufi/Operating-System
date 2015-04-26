import static org.junit.Assert.*;

import org.junit.Test;

public class QueueTest {
	private static Queue queue = new Queue();
	private static final int MAX = 200;
	private static final int MIN = 100;
	private static String[] statusList = {"Waiting", "Ready", "Running", "Finished"};

	@Test
	public void testID() {
		for (int i = 0; i < queue.getLength(); i++) {
			int segment = (int) Math.random() * queue.getLength();
			int id = (int) (Math.random() * MAX) - MIN;
			queue.setID(segment, id);
			assertEquals(id, queue.getID(segment));
		}
	}

	@Test
	public void testMemoryRequest() {
		for (int i = 0; i < queue.getLength(); i++) {
			int segment = (int) Math.random() * queue.getLength();
			int memoryRequest = (int) (Math.random() * MAX) - MIN;
			queue.setMemoryRequest(segment, memoryRequest);
			assertEquals(memoryRequest, queue.getMemoryRequest(segment));
		}
	}

	@Test
	public void testTimeRequest() {
		for (int i = 0; i < queue.getLength(); i++) {
			int segment = (int) Math.random() * queue.getLength();
			int timeRequest = (int) (Math.random() * MAX) - MIN;
			queue.setTimeRequest(segment, timeRequest);
			assertEquals(timeRequest, queue.getTimeRequest(segment));
		}
	}

	@Test
	public void testMemoryAssigned() {
		for (int i = 0; i < queue.getLength(); i++) {
			int segment = (int) Math.random() * queue.getLength();
			int memoryAssigned = (int) (Math.random() * MAX) - MIN;
			queue.setMemoryAssigned(segment, memoryAssigned);
			assertEquals(memoryAssigned, queue.getMemoryAssigned(segment));
		}
	}

	@Test
	public void testTimeRemaining() {
		for (int i = 0; i < queue.getLength(); i++) {
			int segment = (int) Math.random() * queue.getLength();
			int timeRemaining = (int) (Math.random() * MAX) - MIN;
			queue.setTimeRemaining(segment, timeRemaining);
			assertEquals(timeRemaining, queue.getTimeRemaining(segment));
		}
	}

	@Test
	public void testSetStatus() {
		for (int i = 0; i < queue.getLength(); i++) {
			int segment = (int) Math.random() * queue.getLength();
			String status = statusList[(int) (Math.random() * statusList.length)];
			queue.setStatus(segment, status);
			assertEquals(status, queue.getStatus(segment));
		}
	}

	@Test
	public void testJob() {
		for (int i = 0; i < queue.getLength(); i++) {
			int segment = (int) Math.random() * queue.getLength();
			int id = (int) (Math.random() * MAX) - MIN;
			int memoryRequest = (int) (Math.random() * MAX) - MIN;
			int timeRequest = (int) (Math.random() * MAX) - MIN;
			int memoryAssigned = (int) (Math.random() * MAX) - MIN;
			int timeRemaining = (int) (Math.random() * MAX) - MIN;
			String status = statusList[(int) (Math.random() * statusList.length)];

			Process test = new Process(id, memoryRequest, timeRequest, memoryAssigned, timeRemaining, status);

			queue.setJob(segment, test);
			assertEquals(test, queue.getJob(segment));
		}
	}

	@Test
	public void testUnassignedJobs() {
		int count = 0;

		for (int i = 0; i < queue.getLength(); i++) {
			String status = "Waiting";
			queue.setStatus(i, status);

			if (queue.getUnassignedJobs()) {
				count++;
			}
		}

		assertEquals(count, queue.numberOfWaitingJobs());
	}

	@Test
	public void testUnfinishedJobs() {
		int count = 0;

		for (int i = 0; i < queue.getLength(); i++) {
			String status = "Ready";
			queue.setStatus(i, status);

			if (!queue.getUnfinishedJobs()) {
				count++;
			}
		}

		assertEquals(count, queue.numberOfFinishedJobs());
	}
	
	@Test
	public void testShortestJobs() {
		for (int i = 0; i < queue.getLength(); i++) {
			queue.setMemoryRequest(i, (int) (Math.random() * MAX) - MIN);
			queue.setID(i, i);
			queue.setStatus(i, "Waiting");
			queue.setTimeRequest(i, (int) (Math.random() * MAX) - MIN);
		}
		
		Queue queueSJF = queue.shortestJobs();
		
		for (int i = 0; i < queue.getLength(); i++) {
			assertEquals(queueSJF.getMemoryRequest(i), queue.shortestJobs().getMemoryRequest(i));
			assertEquals(queueSJF.getID(i), queue.shortestJobs().getID(i));
			assertEquals(queueSJF.getStatus(i), queue.shortestJobs().getStatus(i));
			assertEquals(queueSJF.getTimeRequest(i), queue.shortestJobs().getTimeRequest(i));
		}
	}
}