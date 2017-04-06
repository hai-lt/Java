package views.networks;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import views.base.ListView;

public class ConnectedAddressPanel extends ListView {
  private ArrayList<InetAddress> connectedAddresses;

  public ConnectedAddressPanel(ArrayList<InetAddress> addresses) {
    super(addresses);
    setTitle("Connected");
  }

  @Override
  public Component getItem(int index) {
    JPanel pn = new JPanel();
    pn.add(new JLabel(connectedAddresses.get(index).toString()));
    JButton open = new JButton("Open");
    open.addActionListener(openButtonEvent(index));
    pn.add(open);

    JButton close = new JButton("Close");
    close.addActionListener(closeButtonEvent(index));
    pn.add(close);
    return pn;
  }

  @Override
  public void clearData() {
    connectedAddresses.clear();
  }

  @Override
  public void add(int position, Object object) {
    connectedAddresses.add(position, (InetAddress) object);
  }

  @Override
  public void add(Object object) {
    connectedAddresses.add((InetAddress) object);
  }

  @Override
  public void removeData(int index) {
    connectedAddresses.remove(index);
  }

  @Override
  public int getCount() {
    return connectedAddresses.size();
  }

  @Override
  public void setData(Object object) {
    connectedAddresses = (ArrayList<InetAddress>) object;
  }

  private ActionListener closeButtonEvent(int index) {
    return new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println(index + "");
      }
    };
  }

  private ActionListener openButtonEvent(int index) {
    return new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println(index + "");
      }
    };
  }

}
