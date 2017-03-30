package models.processes;

import java.util.Vector;

public class UnixProcess extends ProcessInfo {
  private final static String KILL_PROCESS_COMMAND = "kill -9 ";
  public final static String[] TITLES = { "Pid", "User", "Name", "%Cpu", "%Mem", "Status" };
  private static final int POSITION_USER = 0;
  private static final int POSITION_PID = 1;
  private static final int POSITION_CPU = 2;
  private static final int POSITION_MEM = 3;
  private static final int POSITION_STATUS = 7;
  private static final int POSITION_NAME = 10;

  @Override
  protected String getKillProcessCommand() {
    return KILL_PROCESS_COMMAND;
  }

  public UnixProcess() {
    super();
  }

  public UnixProcess(String string) {
    super();
    String[] values = string.split(" ");
    String user = values[POSITION_USER];
    String status = values[POSITION_STATUS];
    String name = values[POSITION_NAME];
    long id = Long.parseLong(values[POSITION_PID]);
    float cpu = Float.parseFloat(values[POSITION_CPU]);
    float mem = Float.parseFloat(values[POSITION_MEM]);
    setAttributes(user, status, name, id, cpu, mem);
  }

  public UnixProcess(String user, String status, String name, long id, float cpu, float mem) {
    super(user, status, name, id, cpu, mem);
  }

  @Override
  public Vector<String> toVector() {
    Vector<String> value = new Vector<>();
    value.add(String.valueOf(getId()));
    value.add(getUser());
    value.add(getName());
    value.add(String.valueOf(getCpu()));
    value.add(String.valueOf(getMem()));
    value.add(getStatus());
    return value;
  }

}
