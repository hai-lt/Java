package views.memories;

import java.awt.Component;
import java.util.ArrayList;

import models.memories.Memory;
import views.base.ListView;

public class MemoriesPanel extends ListView {
  public static final int DEFAULT_COLS = 2;
  private ArrayList<Memory> memories;

  public MemoriesPanel(Object object) {
    super(object, DEFAULT_COLS);
  }

  @Override
  public void setData(Object object) {
    memories = (ArrayList<Memory>) object;
  }

  @Override
  public Component getItem(int index) {
    return new MemoryPanel(memories.get(index));
  }

  @Override
  public void add(Object object) {
    memories.add((Memory) object);
  }

  @Override
  public void add(int index, Object object) {
    memories.add(index, (Memory) object);
  }

  @Override
  public void removeData(int index) {
    memories.remove(index);
  }

  @Override
  public void clearData() {
    memories.clear();
  }

  @Override
  public int getCount() {
    return memories.size();
  }
}
