import static org.junit.Assert.*;

import org.junit.Test;

public class MemoryModuleTest {
	private static MemorySlot memoryModule = new MemorySlot();
	private static final int N = 10;
	private static final int MAX = 200;
	private static final int MIN = 100;
	private static String[] statusList = {"Waiting", "Ready", "Running", "Finished"};
	
	@Test
	public void testSegmentNumber() {
		for (int i = 0; i < N; i++) {
			int segment = (int) (Math.random() * MAX) - MIN;
			memoryModule.setSegmentNumber(segment);
			assertEquals(segment, memoryModule.getSegmentNumber());
		}
	}
	
	@Test
	public void testSize() {
		for (int i = 0; i < N; i++) {
			int size = (int) (Math.random() * MAX) - MIN;
			memoryModule.setSize(size);
			assertEquals(size, memoryModule.getSize());
		}
	}
	
	@Test
	public void testInUse() {
		memoryModule.setInUse(null);
		assertEquals(null, memoryModule.getInUse());
		memoryModule.setInUse(true);
		assertEquals(true, memoryModule.getInUse());
		memoryModule.setInUse(false);
		assertEquals(false, memoryModule.getInUse());
	}
	
	@Test
	public void testWastedSpace() {
		for (int i = 0; i < N; i++) {
			int wastedSpace = (int) (Math.random() * MAX) - MIN;
			memoryModule.setWastedSpace(wastedSpace);
			assertEquals(wastedSpace, memoryModule.getWastedSpace());
		}
	}
	
	@Test
	public void testJob() {
		for (int i = 0; i < N; i++) {
			int id = (int) (Math.random() * MAX) - MIN;
			int memoryRequest = (int) (Math.random() * MAX) - MIN;
			int timeRequest = (int) (Math.random() * MAX) - MIN;
			int memoryAssigned = (int) (Math.random() * MAX) - MIN;
			int timeRemaining = (int) (Math.random() * MAX) - MIN;
			String status = statusList[(int) (Math.random() * statusList.length)];
			
			Process test = new Process(id, memoryRequest, timeRequest, memoryAssigned, timeRemaining, status);
			
			memoryModule.setJob(test);
			assertEquals(test, memoryModule.getJob());
		}
	}
}