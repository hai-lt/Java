package views.networks;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JLabel;

import views.base.ListView;

public class WhitelistAddressesPanel extends ListView {
  private static final String PANEL_NAME = "Whitelist Addresses";
  private static final String EMPTY_MESSAGE = "Have no address";
  private ArrayList<RemoteOsPanel> oses;

  public WhitelistAddressesPanel(ArrayList<RemoteOsPanel> oses) {
    super(oses);
    setTitle(PANEL_NAME);
    setEmptyElementView(new JLabel(EMPTY_MESSAGE));
  }

  public WhitelistAddressesPanel(ArrayList<RemoteOsPanel> oses, int cols) {
    super(oses, cols);
    setTitle(PANEL_NAME);
    setEmptyElementView(new JLabel(EMPTY_MESSAGE));
  }

  @Override
  public void setData(Object object) {
    oses = (ArrayList<RemoteOsPanel>) object;
  }

  @Override
  public Component getItem(int index) {
    return oses.get(index);
  }

  @Override
  public void add(Object object) {
    oses.add((RemoteOsPanel) object);
  }

  @Override
  public void add(int index, Object object) {
    oses.add(index, (RemoteOsPanel) object);
  }

  @Override
  public void removeData(int index) {
    oses.remove(index);
  }

  @Override
  public void clearData() {
    oses.clear();
  }

  @Override
  public int getCount() {
    return oses.size();
  }
}
