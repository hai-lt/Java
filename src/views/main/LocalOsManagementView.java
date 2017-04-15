package views.main;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import models.os.OperatingSystem;
import views.networks.NetworkPanel;

public class LocalOsManagementView extends BaseOsView {
  private final static String REMOTE_LABEL = "Access Remote";

  public LocalOsManagementView(OperatingSystem operatingSystem) {
    super(operatingSystem);
    addTab(new NetworkPanel(), REMOTE_LABEL, KeyEvent.VK_A);
  }

  public LocalOsManagementView() {
    super(new OperatingSystem());
  }

  @Override
  public JFrame create() {
    return createUi(new LocalOsManagementView(new OperatingSystem()));
  }

  public static void main(String[] args) {
    BaseOsView.turnOffBoldFont();
    new LocalOsManagementView().create();
  }
}
