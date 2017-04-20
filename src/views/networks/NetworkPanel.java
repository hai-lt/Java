package views.networks;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controllers.OperatingSystemController;
import network.RestfulRequest;

public class NetworkPanel extends JPanel {
  private ConnectedAddressPanel connectedAddressPanel;

  public NetworkPanel() {
    super(new BorderLayout(10, 10));
    JPanel container = new JPanel(new BorderLayout());
    JScrollPane scrollPane = new JScrollPane(container);
    scrollPane.getVerticalScrollBar().setUnitIncrement(15);
    add(scrollPane, BorderLayout.CENTER);

    RemoteConnectionPanel remoteConnectionPanel = new RemoteConnectionPanel() {

      @Override
      protected void connectAction(RestfulRequest request) {
        connect(request);
      }
    };

    JPanel serverPanel = new JPanel();
    serverPanel.add(new ServerManagementPanel(), BorderLayout.WEST);
    serverPanel.add(remoteConnectionPanel, BorderLayout.EAST);
    container.add(serverPanel, BorderLayout.NORTH);

    connectedAddressPanel = new ConnectedAddressPanel(new ArrayList<>(), 2);
    connectedAddressPanel.setBorder(BorderFactory.createTitledBorder("Connected Server"));
    connectedAddressPanel.setEmptyElementView(new JLabel("Has no server connected!"));
    container.add(connectedAddressPanel, BorderLayout.CENTER);
  }

  private void connect(RestfulRequest request) {
    String response = request.get(OperatingSystemController.RESOURCES);
    if (response.equals(RestfulRequest.TIMEOUT_MESSAGE)) {
      System.out.println("Can not connect to server. Please, check your address and password again");
      return;
    }
    connectedAddressPanel.add(request);
    connectedAddressPanel.notifyDataHasChanged();
  }
}
