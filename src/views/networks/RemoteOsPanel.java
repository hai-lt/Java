package views.networks;

import java.net.InetAddress;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.os.OperatingSystem;

public class RemoteOsPanel extends JPanel {
  private OperatingSystem os;
  private InetAddress address;
  private int port;

  public RemoteOsPanel(OperatingSystem os, InetAddress address, int port) {
    this.os = os;
    this.address = address;
    this.port = port;

    setUi();
  }

  private void setUi() {
    setBorder(BorderFactory.createTitledBorder(address.toString()));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    add(new JLabel("IP Address: " + address.toString()));
    add(new JLabel("Port: " + port));
    add(new JLabel("OS: " + os.getName()));
  }
}
