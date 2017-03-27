package models.os;

import models.memories.RootFilesManagement;
import models.processes.ProcessesManagement;
import models.processes.UnixProcesses;
import models.processes.WindowProcesses;

public class OperatingSystem {
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
    name = System.getProperty("os.name").toLowerCase();
    if (name.indexOf("windows") > -1) {
      processesManagement = (ProcessesManagement) new WindowProcesses();
    } else {
      processesManagement = (ProcessesManagement) new UnixProcesses();
    }
    rootFilesManagement = new RootFilesManagement();
  }
}
