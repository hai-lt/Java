package views.main;

import javax.swing.JFrame;

import models.os.OperatingSystem;
import models.processes.ProcessesManagement;
import models.processes.UnixProcesses;
import models.processes.WindowProcesses;
import network.Client;

public class RemoteOsManagementView extends BaseOsView {
  private Client client;

  public RemoteOsManagementView(OperatingSystem operatingSystem, Client client) {
    super(operatingSystem);
    this.client = client;
  }

  public RemoteOsManagementView(String osString, Client client) {
    super(OperatingSystem.convertFrom(osString));
    this.client = client;
  }

  @Override
  public JFrame create() {
    return createUi(new RemoteOsManagementView(getOperatingSystem(), client));
  }

  @Override
  public void refreshProcessesAction() {
    String response = client.request("/processes");
    ProcessesManagement processes;
    if (getOperatingSystem().isWindowsOs()) {
      processes = WindowProcesses.convertFrom(response);
    } else {
      processes = UnixProcesses.convertFrom(response);
    }
    setProcesses(processes);
    notifyProcessesChanged();
  }

  @Override
  public boolean killProcess(long pid) {
    System.out.println("kill process: " + pid);
    return true;
  }
}
