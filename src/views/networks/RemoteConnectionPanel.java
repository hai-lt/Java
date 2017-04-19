package views.networks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import network.RestfulRequest;

public abstract class RemoteConnectionPanel extends JPanel {
  private JTextField txtIp, txtPassword;

  public RemoteConnectionPanel() {
    super();
    setUi();
  }

  private void setUi() {
    setBorder(BorderFactory.createTitledBorder("Remote Control"));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // set edit ip address
    JPanel ipPanel = new JPanel();
    ipPanel.setBorder(BorderFactory.createTitledBorder("Enter remote address"));
    txtIp = new JTextField(15);
    ipPanel.add(txtIp);
    add(ipPanel);

    // set edit password

    JPanel passwordPanel = new JPanel();
    passwordPanel.setBorder(BorderFactory.createTitledBorder("Password"));
    txtPassword = new JTextField(15);
    passwordPanel.add(txtPassword);
    txtPassword.setBounds(0, 0, 150, 10);
    add(passwordPanel);

    // add button to connect
    JButton btConnect = new JButton("Connect");
    btConnect.addActionListener(connectRemote());
    add(btConnect);

  }

  private ActionListener connectRemote() {
    return new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        InetAddress inetAddress;
        try {
          inetAddress = InetAddress.getByName(txtIp.getText().trim());
          int port = Integer.parseInt(txtPassword.getText().trim());
          new Thread(new Runnable() {
            @Override
            public void run() {
              connectAction(new RestfulRequest(inetAddress, port));
            }
          }).start();
        } catch (UnknownHostException e1) {
          System.out.println("Enter right address plz");
        }
      }
    };
  }

  protected abstract void connectAction(RestfulRequest request);
}
