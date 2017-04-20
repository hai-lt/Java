package views.networks;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controllers.OperatingSystemController;
import network.RestfulRequest;
import views.AppResources;

public class NetworkPanel extends JPanel {
  private ConnectedAddressPanel connectedAddressPanel;
  private JLabel lbStatus;

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

    lbStatus = new JLabel(AppResources.WELCOME_MESSAGE);
    lbStatus.setForeground(AppResources.COLOR_SUCCESS);
    add(lbStatus, BorderLayout.SOUTH);
  }

  private void connect(RestfulRequest request) {
    AppResources.notifyMessage(lbStatus, "Connecting to server... Please waiting for a minute", request.getTimeout());
    String response = request.get(OperatingSystemController.RESOURCES);
    if (response.equals(RestfulRequest.TIMEOUT_MESSAGE)) {
      AppResources.notifyMessage(lbStatus, "Can not connect to server. Please check your connection again!", 3000,
          AppResources.COLOR_WARNING);
      return;
    }
    connectedAddressPanel.add(request);
    connectedAddressPanel.notifyDataHasChanged();
  }
}
