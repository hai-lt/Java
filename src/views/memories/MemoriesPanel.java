package views.memories;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import models.memories.RootFilesManagement;

public class MemoriesPanel extends JPanel {
  public static final int DEFAULT_COLS = 2;
  private RootFilesManagement memories;
  private int cols;

  public void setMemories(RootFilesManagement memories) {
    this.memories = memories;
  }

  public MemoriesPanel(RootFilesManagement memories) {
    super(new BorderLayout());
    this.memories = memories;
    setUI();
  }

  private void setUI() {
    cols = DEFAULT_COLS;
    JPanel memoriesContainer = new JPanel(new GridBagLayout());

    GridBagConstraints contraint = new GridBagConstraints();
    contraint.fill = GridBagConstraints.HORIZONTAL;
    contraint.weightx = 1;
    int rows = this.memories.getMemories().size() / cols + 1;
    for (int i = 0; i < this.memories.getMemories().size(); i++) {
      contraint.gridx = i % rows;
      contraint.gridy = i / rows;
      memoriesContainer.add(new MemoryPanel(this.memories.getMemories().get(i)), contraint);
    }
    add(memoriesContainer, BorderLayout.NORTH);
  }
}
