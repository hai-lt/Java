package views.networks;

import java.awt.BorderLayout;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import models.os.OperatingSystem;
import network.Client;
import network.Server;

public class NetworkPanel extends JPanel {
  private WhitelistAddressesPanel whitelistAddressesPanel;
  private BlacklistAddressesPanel blacklistAddressesPanel;
  private ConnectedAddressPanel connectedAddressPanel;

  public NetworkPanel() {
    super(new BorderLayout(10, 10));
    JPanel container = new JPanel(new BorderLayout());
    JScrollPane scrollPane = new JScrollPane(container);
    scrollPane.getVerticalScrollBar().setUnitIncrement(15);
    add(scrollPane, BorderLayout.CENTER);

    ArrayList<InetAddress> addresses = getAddresses();
    ArrayList<RemoteOsPanel> remoteOsPanels = getRemoteOsPanels();
    ArrayList<Client> connected = getConnectedClient();

    JPanel serverPanel = new JPanel(new BorderLayout());
    serverPanel.setBorder(BorderFactory.createTitledBorder("Server Management"));
    whitelistAddressesPanel = new WhitelistAddressesPanel(remoteOsPanels, 2);
    blacklistAddressesPanel = new BlacklistAddressesPanel(addresses);
    serverPanel.add(new ServerManagementPanel(), BorderLayout.WEST);
    serverPanel.add(blacklistAddressesPanel, BorderLayout.EAST);
    serverPanel.add(whitelistAddressesPanel, BorderLayout.SOUTH);
    container.add(serverPanel, BorderLayout.NORTH);

    JPanel remotePanel = new JPanel(new BorderLayout());
    remotePanel.setBorder(BorderFactory.createTitledBorder("Remote Management"));
    remotePanel.add(new RemoteConnectionPanel(), BorderLayout.WEST);
    connectedAddressPanel = new ConnectedAddressPanel(connected);
    remotePanel.add(connectedAddressPanel, BorderLayout.EAST);
    container.add(remotePanel, BorderLayout.SOUTH);
  }

  private ArrayList<InetAddress> getAddresses() {
    ArrayList<InetAddress> blacklist = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      try {
        blacklist.add(InetAddress.getLocalHost());
      } catch (UnknownHostException e) {
        e.printStackTrace();
      }
    }
    return blacklist;
  }

  private ArrayList<RemoteOsPanel> getRemoteOsPanels() {
    ArrayList<RemoteOsPanel> remoteOsPanels = new ArrayList<>();
    try {
      for (int i = 0; i < 5; i++) {
        remoteOsPanels.add(new RemoteOsPanel(new OperatingSystem(), InetAddress.getLocalHost(), i));
      }
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
    return remoteOsPanels;
  }

  private ArrayList<Client> getConnectedClient() {
    ArrayList<Client> connected = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      connected.add(new Client(Server.getInstance().getAdress(), Server.getInstance().getPort()));
    }
    return connected;
  }
}
