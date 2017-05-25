package views.memories;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import models.memories.Memory;

public class MemoryPanel extends JPanel {
  private static final String NO_NAME = "[No Name]";
  private Memory memory;

  public MemoryPanel(Memory memory) {
    super();
    this.memory = memory;
    setUI();
  }

  private void setUI() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    String bytes = " bytes";
    setBorder(new EmptyBorder(10, 10, 10, 10));
    String name = memory.getName().equals("") ? NO_NAME : memory.getName();
    add(new JLabel("Toal: " + memory.getTotal() + bytes));
    add(new JLabel("Free: " + memory.getFree() + bytes));
    add(new JLabel("Usable: " + memory.getUsable() + bytes));
    setBorder(BorderFactory.createTitledBorder(memory.getPath()));
  }
}
