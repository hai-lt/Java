package models.os;

import models.memories.RootFilesManagement;
import models.processes.ProcessesManagement;
import models.processes.UnixProcesses;
import models.processes.WindowProcesses;

public class OperatingSystem {
  public static final String OS_KEY_NAME = "_OS_; ";
  public static final String PROCESSES_MEMORIES_KEY = " -;- ";
  public static final String WINDOW_OS_NAME = "windows";
  private String name;
  private ProcessesManagement processesManagement;
  private RootFilesManagement rootFilesManagement;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProcessesManagement getProcessesManagement() {
    return processesManagement;
  }

  public void setProcessesManagement(ProcessesManagement processesManagement) {
    this.processesManagement = processesManagement;
  }

  public RootFilesManagement getRootFilesManagement() {
    return rootFilesManagement;
  }

  public void setRootFilesManagement(RootFilesManagement rootFilesManagement) {
    this.rootFilesManagement = rootFilesManagement;
  }

  public OperatingSystem() {
    name = System.getProperty("os.name").toLowerCase().trim();
    if (isWindowsOs()) {
      processesManagement = (ProcessesManagement) new WindowProcesses();
    } else {
      processesManagement = (ProcessesManagement) new UnixProcesses();
    }
    rootFilesManagement = new RootFilesManagement();
  }

  public OperatingSystem(String name, ProcessesManagement processesManagement,
      RootFilesManagement rootFilesManagement) {
    super();
    this.name = name;
    this.processesManagement = processesManagement;
    this.rootFilesManagement = rootFilesManagement;
  }

  public static OperatingSystem covertFrom(String osString) {

    return null;
  }

  public boolean isWindowsOs() {
    return getName().indexOf(WINDOW_OS_NAME) > -1;
  }

  public static boolean isWindowsOs(String osName) {
    return osName.trim().indexOf(WINDOW_OS_NAME) > -1;
  }

  @Override
  public String toString() {
    return getName().concat(OS_KEY_NAME).concat(getProcessesManagement().toString()).concat(PROCESSES_MEMORIES_KEY)
        .concat(getRootFilesManagement().toString());
  }

  public static OperatingSystem convertFrom(String string) {
    String[] values = string.split(OS_KEY_NAME);
    String osName = values[0];
    ProcessesManagement processesManagement;
    String[] os = values[1].split(PROCESSES_MEMORIES_KEY);
    if (OperatingSystem.isWindowsOs(osName)) {
      processesManagement = WindowProcesses.convertFrom(os[0]);
    } else {
      processesManagement = UnixProcesses.convertFrom(os[0]);
    }
    RootFilesManagement rootFilesManagement = RootFilesManagement.convertFrom(os[1]);
    return new OperatingSystem(osName, processesManagement, rootFilesManagement);
  }
}
