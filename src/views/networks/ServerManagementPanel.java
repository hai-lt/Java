package views.networks;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import network.Server;
import views.AppResources;

public class ServerManagementPanel extends JPanel {
  private final static String STOP_SERVER_MESSAGE = "Server has stopped";
  private final static String RUNNING_SERVER_MESSAGE = "Server is running";
  private final static String HAS_NOT_STARTED_SERVER_MESSAGE = "Server has not started";
  private JLabel statusLb;

  public ServerManagementPanel() {
    super();
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setUi();
  }

  private void setUi() {
    setBorder(BorderFactory.createTitledBorder("Your network"));

    JLabel lbIp = new JLabel("IP Address: " + Server.getInstance().getAdress());
    lbIp.setAlignmentX(Component.CENTER_ALIGNMENT);
    add(lbIp);
    JLabel lbPort = new JLabel("Port : " + Server.getInstance().getPort());
    lbPort.setAlignmentX(Component.CENTER_ALIGNMENT);
    add(lbPort);

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

    JPanel statusPn = new JPanel();
    statusPn.setBorder(BorderFactory.createTitledBorder("Status"));
    statusLb = new JLabel();
    setStatusServerNotStarted();
    statusPn.add(statusLb);
    add(statusPn);

  }

  private void start() {
    new Thread(new Runnable() {

      @Override
      public void run() {
        Server.getInstance().run();
      }
    }).start();
    ;
    setStatusServerRunning();
  }

  private void close() {
    new Thread(new Runnable() {

      @Override
      public void run() {
        Server.getInstance().close();
      }
    }).start();
    setStatusServerStop();
  }

  private void setStatusServerStop() {
    statusLb.setText(STOP_SERVER_MESSAGE);
    statusLb.setForeground(AppResources.COLOR_DANGER);
  }

  private void setStatusServerRunning() {
    statusLb.setText(RUNNING_SERVER_MESSAGE);
    statusLb.setForeground(AppResources.COLOR_SUCCESS);
  }

  private void setStatusServerNotStarted() {
    statusLb.setText(HAS_NOT_STARTED_SERVER_MESSAGE);
    statusLb.setForeground(AppResources.COLOR_WARNING);
  }
}
