package views.networks;

import java.awt.BorderLayout;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import models.os.OperatingSystem;
import network.Client;
import network.Server;

public class NetworkPanel extends JPanel {
  private ConnectedAddressPanel connectedAddressPanel;

  public NetworkPanel() {
    super(new BorderLayout(10, 10));
    JPanel container = new JPanel(new BorderLayout());
    JScrollPane scrollPane = new JScrollPane(container);
    scrollPane.getVerticalScrollBar().setUnitIncrement(15);
    add(scrollPane, BorderLayout.CENTER);

    ArrayList<Client> connected = getConnectedClient();

    JPanel remotePanel = new JPanel(new BorderLayout());
    remotePanel.setBorder(BorderFactory.createTitledBorder("Remote Management"));
    remotePanel.add(new RemoteConnectionPanel(), BorderLayout.WEST);

    JPanel serverPanel = new JPanel();
    serverPanel.add(new ServerManagementPanel(), BorderLayout.WEST);
    serverPanel.add(new RemoteConnectionPanel(), BorderLayout.EAST);
    container.add(serverPanel, BorderLayout.NORTH);

    connectedAddressPanel = new ConnectedAddressPanel(connected, 2);
    connectedAddressPanel.setBorder(BorderFactory.createTitledBorder("Connected Server"));
    connectedAddressPanel.setEmptyElementView(new JLabel("Has no server connected!"));
    container.add(connectedAddressPanel, BorderLayout.CENTER);
  }

  private ArrayList<Client> getConnectedClient() {
    ArrayList<Client> connected = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      connected.add(new Client(Server.getInstance().getAdress(), Server.getInstance().getPort()));
    }
    return connected;
  }
}
