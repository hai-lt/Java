package models.memories;

import java.io.File;
import java.util.ArrayList;

public class RootFilesManagement {
  private File[] roots;
  private ArrayList<Memory> memories;

  public RootFilesManagement(ArrayList<Memory> memories) {
    this.memories = memories;
  }

  public RootFilesManagement(File[] roots) {
    memories = new ArrayList<>();
    setRoots(roots);
  }

  public RootFilesManagement() {
    memories = new ArrayList<>();
    setRoots(File.listRoots());
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

  @Override
  public String toString() {
    String string = "";
    for (Memory memory : memories) {
      string += memory.toString() + "; ";
    }
    if (string.equals("")) {
      return "";
    }
    return string.substring(0, string.length() - 2);
  }

  public static RootFilesManagement convertFrom(String string) {
    String[] memoriesString = string.split("; ");
    ArrayList<Memory> memories = new ArrayList<>();
    for (String memory : memoriesString) {
      memories.add(Memory.convertFrom(memory));
    }
    return new RootFilesManagement(memories);
  }
}
