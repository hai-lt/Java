package views.networks;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.os.OperatingSystem;
import network.Client;
import views.base.ListView;
import views.main.RemoteOsManagementView;

public class ConnectedAddressPanel extends ListView {
  private ArrayList<Client> connectedAddresses;

  public ConnectedAddressPanel(ArrayList<Client> addresses) {
    super(addresses);
    setTitle("Connected");
    setEmptyElementView(new JLabel("Have no remote connected"));
  }

  public ConnectedAddressPanel(ArrayList<Client> connectedAddresses, int cols) {
    super(connectedAddresses, cols);
    this.connectedAddresses = connectedAddresses;
  }

  @Override
  public Component getItem(int index) {
    JPanel pn = new JPanel();
    pn.add(new JLabel(connectedAddresses.get(index).toString()));

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
    connectedAddresses.add(position, (Client) object);
  }

  @Override
  public void add(Object object) {
    connectedAddresses.add((Client) object);
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
    connectedAddresses = (ArrayList<Client>) object;
    connectedAddresses = (ArrayList<Client>) connectedAddresses.clone();
  }

  private ActionListener closeButtonEvent(int index) {
    return new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        remove(index);
      }
    };
  }

  @Override
  protected void onItemClickedListener(int index) {
    super.onItemClickedListener(index);
    new RemoteOsManagementView(new OperatingSystem().toString(), connectedAddresses.get(index)).create();
  }
}
