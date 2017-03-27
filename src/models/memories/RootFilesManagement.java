package models.memories;

import java.io.File;
import java.util.ArrayList;

public class RootFilesManagement {
  private File[] roots;
  private ArrayList<Memory> memories;

  public RootFilesManagement(File[] roots) {
    memories = new ArrayList<>();
    setRoots(roots);
  }

  public RootFilesManagement() {
    memories = new ArrayList<>();
    setRoots(File.listRoots());
  }

  public File[] getRoots() {
    return roots;
  }

  public void setRoots(File[] roots) {
    this.roots = roots;
    memories.clear();
    for (File root : roots) {
      memories.add(new Memory(root));
    }
  }

  public ArrayList<Memory> getMemories() {
    return memories;
  }
}
