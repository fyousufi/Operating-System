import static org.junit.Assert.*;

import org.junit.Test;

public class MainMemoryTest {
	private static MainMemory mainMemory = new MainMemory();
	private static final int MAX = 200;
	private static final int MIN = 100;
	private static String[] statusList = {"Waiting", "Ready", "Running", "Finished"};

	/** For Memory **/
	@Test
	public void testSegmentNumber() {
		for (int i = 0; i < mainMemory.numberOfSlots; i++) {
			int changedSegmentNumber = (int) Math.random() * mainMemory.numberOfSlots;
			mainMemory.setSegmentNumber(i, changedSegmentNumber);
			assertEquals(changedSegmentNumber, mainMemory.getSegmentNumber(i));
		}
	}

	@Test
	public void testSize() {
		for (int i = 0; i < mainMemory.numberOfSlots; i++) {
			int size = (int) (Math.random() * MAX) - MIN;
			mainMemory.setSize(i, size);
			assertEquals(size, mainMemory.getSize(i));
		}
	}

	@Test
	public void testInUse() {
		for (int i = 0; i < mainMemory.numberOfSlots; i++) {
			mainMemory.setInUse(i, null);
			assertEquals(null, mainMemory.getInUse(i));
			mainMemory.setInUse(i, true);
			assertEquals(true, mainMemory.getInUse(i));
			mainMemory.setInUse(i, false);
			assertEquals(false, mainMemory.getInUse(i));
		}
	}

	@Test
	public void testWastedSpace() {
		for (int i = 0; i < mainMemory.numberOfSlots; i++) {
			int wastedSpace = (int) (Math.random() * MAX) - MIN;
			mainMemory.setWastedSpace(i, wastedSpace);
			assertEquals(wastedSpace, mainMemory.getWastedSpace(i));
		}
	}
	
	/** For Memory **/
	@Test
	public void testID() {
		for (int i = 0; i < mainMemory.numberOfSlots; i++) {
			int id = (int) (Math.random() * MAX) - MIN;
			mainMemory.setID(i, id);
			assertEquals(id, mainMemory.getID(i));
		}
	}

	@Test
	public void testMemoryRequest() {
		for (int i = 0; i < mainMemory.numberOfSlots; i++) {
			int memoryRequest = (int) (Math.random() * MAX) - MIN;
			mainMemory.setMemoryRequest(i, memoryRequest);
			assertEquals(memoryRequest, mainMemory.getMemoryRequest(i));
		}
	}

	@Test
	public void testTimeRequest() {
		for (int i = 0; i < mainMemory.numberOfSlots; i++) {
			int timeRequest = (int) (Math.random() * MAX) - MIN;
			mainMemory.setTimeRequest(i, timeRequest);
			assertEquals(timeRequest, mainMemory.getTimeRequest(i));
		}
	}

	@Test
	public void testMemoryAssigned() {
		for (int i = 0; i < mainMemory.numberOfSlots; i++) {
			int memoryAssigned = (int) (Math.random() * MAX) - MIN;
			mainMemory.setMemoryAssigned(i, memoryAssigned);
			assertEquals(memoryAssigned, mainMemory.getMemoryAssigned(i));
		}
	}

	@Test
	public void testTimeRemaining() {
		for (int i = 0; i < mainMemory.numberOfSlots; i++) {
			int timeRemaining = (int) (Math.random() * MAX) - MIN;
			mainMemory.setTimeRemaining(i, timeRemaining);
			assertEquals(timeRemaining, mainMemory.getTimeRemaining(i));
		}
	}
	
	@Test
	public void testStatus() {
		for (int i = 0; i < mainMemory.numberOfSlots; i++) {
			String status = statusList[(int) Math.random() * statusList.length];
			mainMemory.setStatus(i, status);
			assertEquals(status, mainMemory.getStatus(i));
		}
	}
	
	@Test
	public void testAssignMemory() {
		for (int i = 0; i < mainMemory.numberOfSlots; i++) {
			int id = (int) (Math.random() * MAX) - MIN;
			int memoryRequest = (int) (Math.random() * MAX) - MIN;
			int timeRequest = (int) (Math.random() * MAX) - MIN;
			int memoryAssigned = (int) (Math.random() * MAX) - MIN;
			int timeRemaining = (int) (Math.random() * MAX) - MIN;
			String status = statusList[(int) (Math.random() * statusList.length)];

			Process test = new Process(id, memoryRequest, timeRequest, memoryAssigned, timeRemaining, status);

			mainMemory.assignMemory(i, test);
			assertEquals(test.getMemoryAssigned(), mainMemory.getMemoryAssigned(i));
		}
	}
	
	@Test
	public void testMemoryAvailable() {
		Boolean memoryAvailable = false;

		for (int i = 0; i < mainMemory.numberOfSlots; i++) {
			if (mainMemory.getInUse(i) == false) {
				memoryAvailable = true;
				break;
			}
		}

		assertEquals(memoryAvailable, mainMemory.memoryAvailable());
	}
	
	@Test
	public void testFirstAvailableMemorySlot() {
		int index = -1;
		
		if (mainMemory.memoryAvailable()) {
			for (int i = 0; i < mainMemory.numberOfSlots; i++) {
				if (mainMemory.getInUse(i) == false) {
					index = i;
					break;
				}
			}
		}
		
		assertEquals(index, mainMemory.firstAvailableMemorySlot());
	}
	
	@Test
	public void testWastedMemory() {
		int count = 0;
		
		for (int i = 0; i < mainMemory.numberOfSlots; i++) {
			if (mainMemory.getInUse(i)) {
				count += mainMemory.getWastedSpace(i);
			} else {
				count += mainMemory.getSize(i);
			}
		}
		
		assertEquals(count, mainMemory.totalWastedMemory());
	}
}