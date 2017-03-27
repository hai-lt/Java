package processes;

public abstract class ProcessInfo {
  private String user, status, name;
  private long id;
  private float cpu, mem;

  protected abstract String getKillProcessCommand();

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
      Runtime.getRuntime().exec(getKillProcessCommand() + getId());
      return this;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public String toString() {
    return id + ", " + user + ", " + cpu + ", " + mem + ", " + status + ", " + name + ", ";
  }
}
