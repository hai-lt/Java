package views.memories;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import models.memories.Memory;

public class MemoryPanel extends JPanel {
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
    String name = memory.getName().equals("") ? "[No Name]" : memory.getName();
    add(new JLabel("Name: " + name));
    add(new JLabel("Path: " + memory.getPath()));
    add(new JLabel("Toal: " + memory.getTotal() + bytes));

    add(new JLabel("Free: " + memory.getFree() + bytes));

    add(new JLabel("Usable: " + memory.getUsable() + bytes));
  }

}
