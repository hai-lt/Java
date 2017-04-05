package views.networks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BlacklistAddressesPanel extends JPanel {
  private static final String PANEL_NAME = "Blacklist Addresses";
  private ArrayList<InetAddress> blacklist;

  public BlacklistAddressesPanel(ArrayList<InetAddress> blacklist) {
    super();
    setBlacklist(blacklist);
    setBorder(BorderFactory.createTitledBorder(PANEL_NAME));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setUi();
  }

  public void refresh() {
    removeAll();
    setUi();
  }

  public void setUi() {
    if (blacklist.size() == 0) {
      add(new JLabel("Has no black Address added"));
      return;
    }
    for (int i = 0; i < blacklist.size(); i++) {
      JPanel container = new JPanel();
      int num = i;
      container.add(new JLabel("- " + blacklist.get(i).toString()));
      JButton del = new JButton("Del");
      del.addActionListener(new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent e) {
          removeInet(num);
        }
      });
      container.add(del);
      add(container);
    }
  }
  
  public void removeInet(int index) {
    blacklist.remove(index);
    refresh();
  }

  public ArrayList<InetAddress> getBlacklist() {
    return blacklist;
  }

  public void setBlacklist(ArrayList<InetAddress> blacklist) {
    this.blacklist = blacklist;
  }

}
