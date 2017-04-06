package views.base;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public abstract class ListView extends JPanel {
  private static final int DEFAULT_COLS = 1;
  private GridBagConstraints constraint;
  private JPanel container;
  private Component emptyView;
  private int cols;

  public ListView(Object object) {
    super(new BorderLayout());
    container = new JPanel(new GridBagLayout());
    setData(object);
    setCols(DEFAULT_COLS);
    setConstraint();
    notifyDataHasChanged();
    add(container, BorderLayout.NORTH);
  }

  public ListView(Object object, int cols) {
    super();
    setData(object);
    setCols(cols);
    setConstraint();
    notifyDataHasChanged();
    add(container, BorderLayout.NORTH);
  }

  public abstract void setData(Object object);

  public void notifyDataHasChanged() {
    if (container != null) {
      container.removeAll();
    } else {
      container = new JPanel(new GridBagLayout());
    }
    setEmptyElementView();
    setUi();
  }

  private void setConstraint() {
    if (constraint == null) {
      constraint = new GridBagConstraints();
    }
    constraint.fill = GridBagConstraints.HORIZONTAL;
    constraint.weightx = 1;
  }

  public void add(Object object, int position) {
    add(position, object);
    container.add(getItem(position), getConstraint(position % cols, position / cols));
  }

  public GridBagConstraints getConstraint(int gridx, int gridy) {
    constraint.gridx = gridx;
    constraint.gridy = gridy;
    return constraint;
  }

  @Override
  public void remove(int index) {
    removeData(index);
    notifyDataHasChanged();
  }

  @Override
  public void removeAll() {
    container.removeAll();
    clearData();
    setEmptyElementView();
  }

  public void setEmptyElementView(Component component) {
    emptyView = component;
  }

  private void setEmptyElementView() {
    if (container != null && emptyView != null && getCount() == 0) {
      container.add(emptyView);
    }
  }

  public void setTitle(String title) {
    setBorder(BorderFactory.createTitledBorder(title));
  }

  public void setCols(int rows) {
    this.cols = rows;
  }

  public void setUi() {
    for (int i = 0; i < getCount(); i++) {
      container.add(getItem(i), getConstraint(i % cols, i / cols));
    }
  };

  public abstract Component getItem(int index);

  public abstract void add(Object object);

  public abstract void add(int index, Object object);

  public abstract void removeData(int index);

  public abstract void clearData();

  public abstract int getCount();
}
