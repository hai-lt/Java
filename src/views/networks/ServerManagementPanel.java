package views.networks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import network.Server;

public class ServerManagementPanel extends JPanel {
  public ServerManagementPanel() {
    super();
    setUi();
  }

  private void setUi() {
    setBorder(BorderFactory.createTitledBorder("Your network"));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    add(new JLabel("IP Address: " + Server.getInstance().getAdress()));
    add(new JLabel("Port: " + Server.getInstance().getPort()));

    JPanel actionButtons = new JPanel();
    JButton startBtn = new JButton("Start");
    startBtn.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        start();
      }
    });

    actionButtons.add(startBtn);

    JButton stopBtn = new JButton("Stop");
    stopBtn.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        close();
      }
    });

    actionButtons.add(stopBtn);

    add(actionButtons);

  }

  private void start() {

  }

  private void close() {

  }
}
