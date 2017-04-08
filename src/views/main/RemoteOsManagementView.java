package views.main;

import javax.swing.JFrame;

import models.os.OperatingSystem;

public class RemoteOsManagementView extends BaseOsView {

  public RemoteOsManagementView(OperatingSystem operatingSystem) {
    super(operatingSystem);
  }
  
  public RemoteOsManagementView(String osString) {
    super(OperatingSystem.convertFrom(osString));
  }

  @Override
  public JFrame create() {
    return createUi(new RemoteOsManagementView(getOperatingSystem()));
  }
}
