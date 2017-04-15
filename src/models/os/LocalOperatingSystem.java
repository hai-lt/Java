package models.os;

import models.memories.RootFilesManagement;
import models.processes.ProcessesManagement;
import models.processes.UnixProcesses;
import models.processes.WindowProcesses;

public class LocalOperatingSystem extends OperatingSystem {
  private static LocalOperatingSystem instance;

  private LocalOperatingSystem() {
    super();
  }

  public static LocalOperatingSystem getInstance() {
    if (instance != null) {
      return instance;
    }
    synchronized (LocalOperatingSystem.class) {
      if (instance != null) {
        return instance;
      }
      instance = new LocalOperatingSystem();
      return instance;
    }
  }

  public ProcessesManagement refreshProcesses() {
    if (isWindowsOs()) {
      setProcessesManagement(new WindowProcesses());
    } else {
      setProcessesManagement(new UnixProcesses());
    }
    return getProcessesManagement();
  }

  public RootFilesManagement refreshMemories() {
    setRootFilesManagement(new RootFilesManagement());
    return getRootFilesManagement();
  }

}
