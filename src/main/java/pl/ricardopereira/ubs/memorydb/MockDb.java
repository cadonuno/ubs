package pl.ricardopereira.ubs.memorydb;

public class MockDb {
	private static final MemoryDb smsLog = new MemoryDb();
	
	private static final MemoryDb emailLog = new MemoryDb();

	public static MemoryDb getSmsLog() {
		return smsLog;
	}

	public static MemoryDb getEmailLog() {
		return emailLog;
	}
}
