package views.networks;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class WhitelistAddressesPanel extends JPanel {
  private static final int DEFAULT_ROWS = 2;
  private ArrayList<RemoteOsPanel> oses;
  private GridBagConstraints constraint;
  private JPanel osContainer;
  private int rows;

  public WhitelistAddressesPanel(ArrayList<RemoteOsPanel> oses) {
    super(new BorderLayout());
    rows = DEFAULT_ROWS;
    this.oses = oses;
    setBorder(BorderFactory.createTitledBorder("Whitelist Addresses"));
    setConstraint();
    setContainer();
    add(osContainer, BorderLayout.NORTH);
  }

  public void setContainer() {
    if (osContainer != null) {
      osContainer.removeAll();
    }
    osContainer = new JPanel(new GridBagLayout());
    for (int i = 0; i < oses.size(); i++) {
      osContainer.add(oses.get(i), getConstraint(i % rows, i / rows));
    }
  }

  public void setConstraint() {
    constraint = new GridBagConstraints();
    constraint.fill = GridBagConstraints.HORIZONTAL;
    constraint.weightx = 1;
  }

  public GridBagConstraints getConstraint() {
    return constraint;
  }

  public GridBagConstraints getConstraint(int gridx, int gridy) {
    constraint.gridx = gridx;
    constraint.gridy = gridy;
    return constraint;
  }

  public void addOs(RemoteOsPanel os) {
    oses.add(os);
    add(os, getConstraint(oses.size() % rows, oses.size() / rows));
  }

  public void removeOs(int posx, int posy) {
    oses.remove(posx * rows + posy);
    refresh();
  }
  
  public void refresh() {
    setContainer();
    add(osContainer);
  }
}
