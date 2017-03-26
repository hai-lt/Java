package models;

public class WindowProcess extends ProcessInfo {
	public final static String[] TITLES = { "User", "Status", "Name", "Pid", "%Cpu", "Mem" };
	private static final int POSITION_USER = 0;
	private static final int POSITION_STATUS = 1;
	private static final int POSITION_PID = 2;
	private static final int POSITION_MEM = 3;
	private static final int POSITION_CPU = 4;
	private static final int POSITION_NAME = 5;
	private final String KILL_PROCESS_COMMAND = "taskkill ";

	@Override
	protected String getKillProcessCommand() {
		return KILL_PROCESS_COMMAND;
	}

	public WindowProcess() {
		super();
	}

	public WindowProcess(String string) {
		String[] values = string.split(" ");
		String user = values[POSITION_USER];
		String status = values[POSITION_STATUS];
		String name = values[POSITION_NAME];
		long id = Long.parseLong(values[POSITION_PID]);
		float cpu = Float.parseFloat(values[POSITION_CPU]);
		float mem = Float.parseFloat(values[POSITION_MEM]);
		setAttributes(user, status, name, id, cpu, mem);
	}

	public WindowProcess(String user, String status, String name, long id, float cpu, float mem) {
		super(user, status, name, id, cpu, mem);
	}
}
