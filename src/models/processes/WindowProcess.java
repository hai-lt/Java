package models.processes;

import java.util.Vector;

public class WindowProcess extends ProcessInfo {
  public final static String[] TITLES = { "User", "Status", "Name", "Pid", "%Cpu", "Mem" };
  private static final int POSITION_USER = 2;
  private static final int POSITION_STATUS = 3;
  private static final int POSITION_PID = 1;
  private static final int POSITION_MEM = 4;
//  private static final int POSITION_CPU = 4;
  private static final int POSITION_NAME = 0;
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
//    float cpu = Float.parseFloat(values[POSITION_CPU]);
    float mem = Float.parseFloat(values[POSITION_MEM]);
    setAttributes(user, status, name, id, 0, mem);
  }

  public WindowProcess(String user, String status, String name, long id, float mem) {
    super(user, status, name, id, 0, mem);
  }

  @Override
  public Vector<String> toVector() {
    Vector<String> value = new Vector<>();
    value.add(getUser());
    value.add(getStatus());
    value.add(getName());
    value.add(String.valueOf(getId()));
    value.add(String.valueOf(getCpu()));
    value.add(String.valueOf(getMem()));
    return value;
  }

  public static WindowProcess convertFrom(String string) {
    WindowProcess windowProcess = new WindowProcess();
    windowProcess.setAttributes(string);
    return windowProcess;
  }
}
