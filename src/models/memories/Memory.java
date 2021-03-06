package models.memories;

import java.io.File;

public class Memory {
  private String name, path;
  private long total, free, usable;

  public Memory() {

  }

  public Memory(String name, String path, long total, long free, long usable) {
    this.name = name;
    this.path = path;
    this.total = total;
    this.free = free;
    this.usable = usable;
  }

  public Memory(File root) {
    name = root.getName();
    path = root.getAbsolutePath();
    total = root.getTotalSpace();
    free = root.getFreeSpace();
    usable = root.getUsableSpace();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public long getFree() {
    return free;
  }

  public void setFree(long free) {
    this.free = free;
  }

  public long getUsable() {
    return usable;
  }

  public void setUsable(long usable) {
    this.usable = usable;
  }

  @Override
  public String toString() {
    return name + ", " + path + ", " + total + ", " + free + ", " + usable;
  }

  public static Memory convertFrom(String string) {
    String[] values = string.split(", ");
    Memory memory = new Memory();
    memory.setName(values[0]);
    memory.setPath(values[1]);
    memory.setTotal(Long.parseLong(values[2]));
    memory.setFree(Long.parseLong(values[3]));
    memory.setUsable(Long.parseLong(values[4]));
    return memory;
  }

}
