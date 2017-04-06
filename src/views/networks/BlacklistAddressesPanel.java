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

public class BlacklistAddressesPanel extends ListView {
  private static final String PANEL_NAME = "Blacklist Addresses";
  private ArrayList<InetAddress> blacklist;

  public BlacklistAddressesPanel(ArrayList<InetAddress> blacklist) {
    super(blacklist);
    setTitle(PANEL_NAME);
    setEmptyElementView(new JLabel("Has no black Address added"));
  }

  @Override
  public void setData(Object object) {
    blacklist = (ArrayList<InetAddress>) object;
    blacklist = (ArrayList<InetAddress>) blacklist.clone();
  }

  @Override
  public Component getItem(int index) {
    JPanel container = new JPanel();
    container.add(new JLabel("- " + blacklist.get(index).toString()));
    JButton del = new JButton("Del" + getCount());
    del.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        remove(index);
      }
    });
    container.add(del);
    return container;
  }

  @Override
  public void add(Object object) {
    blacklist.add((InetAddress) object);
  }

  @Override
  public void add(int index, Object object) {
    blacklist.add(index, (InetAddress) object);
  }

  @Override
  public void removeData(int index) {
    blacklist.remove(index);
  }

  @Override
  public void clearData() {
    blacklist.clear();
  }

  @Override
  public int getCount() {
    return blacklist.size();
  }
}
