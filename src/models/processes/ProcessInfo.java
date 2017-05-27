package models.processes;

import java.util.Vector;

public abstract class ProcessInfo {
  private String user, status, name;
  private long id;
  private float cpu, mem;

  protected abstract String getKillProcessCommand();

  public abstract Vector<String> toVector();

  public ProcessInfo() {
  }

  public ProcessInfo(String user, String status, String name, long id, float cpu, float mem) {
    this.user = user;
    this.status = status;
    this.name = name;
    this.id = id;
    this.cpu = cpu;
    this.mem = mem;
  }

  public String getUser() {
    return user;
  }

  public String getStatus() {
    return status;
  }

  public String getName() {
    return name;
  }

  public long getId() {
    return id;
  }

  public float getCpu() {
    return cpu;
  }

  public float getMem() {
    return mem;
  }

  public void setAttributes(String user, String status, String name, long id, float cpu, float mem) {
    this.user = user;
    this.status = status;
    this.name = name;
    this.id = id;
    this.cpu = cpu;
    this.mem = mem;
  }

  public ProcessInfo kill() {
    try {
      String cmd = getKillProcessCommand().replace("%", String.valueOf(getId()));
      Runtime.getRuntime().exec(cmd);
      return this;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public String toString() {
    return id + ", " + user + ", " + cpu + ", " + mem + ", " + status + ", " + name;
  }

  public void setAttributes(String string) {
    String[] values = string.split(", ");
    id = Long.parseLong(values[0]);
    user = values[1];
    cpu = Float.parseFloat(values[2]);
    mem = Float.parseFloat(values[3]);
    status = values[4];
    name = values[5];
  }

}
