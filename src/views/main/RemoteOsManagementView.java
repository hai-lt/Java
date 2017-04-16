package views.main;

import javax.swing.JFrame;

import models.os.OperatingSystem;
import models.processes.ProcessesManagement;
import models.processes.UnixProcesses;
import models.processes.WindowProcesses;
import network.Client;
import network.RestfulRequest;

public class RemoteOsManagementView extends BaseOsView {
  private RestfulRequest request;

  public RemoteOsManagementView(OperatingSystem operatingSystem, Client client) {
    super(operatingSystem);
    this.request = (RestfulRequest) client;
    this.request = new RestfulRequest(client.getIpAddress(), client.getPort());
  }

  public RemoteOsManagementView(String osString, Client client) {
    super(OperatingSystem.convertFrom(osString));
    this.request = (RestfulRequest) client;
  }

  @Override
  public JFrame create() {
    return createUi(new RemoteOsManagementView(getOperatingSystem(), request));
  }

  @Override
  public void refreshProcessesAction() {
    String response = request.request("/processes");
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
