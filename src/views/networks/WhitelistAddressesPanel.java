package views.networks;

import java.awt.Component;
import java.util.ArrayList;
import views.base.ListView;

public class WhitelistAddressesPanel extends ListView {
  private ArrayList<RemoteOsPanel> oses;

  public WhitelistAddressesPanel(ArrayList<RemoteOsPanel> oses) {
    super(oses);
    setTitle("Whitelist Addresses");
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
