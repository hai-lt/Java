package models.processes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class UnixProcesses extends ProcessesManagement {
  private static String LIST_PROCESSES_COMMAND = "ps aux --sort -rss";

  public UnixProcesses(ArrayList<ProcessInfo> processes) {
    super(processes);
  }

  public UnixProcesses() {
    super();
  }

  @Override
  public ArrayList<ProcessInfo> loadProcesses() {
    try {
      Process p = Runtime.getRuntime().exec(LIST_PROCESSES_COMMAND);
      BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
      String line;
      processes.clear();
      // remove the first line which is the titles of the processes
      input.readLine();
      while ((line = input.readLine()) != null) {
        processes.add(new UnixProcess(breeze(line)));
      }
      input.close();
      return processes;
    } catch (Exception err) {
      err.printStackTrace();
    }
    return null;
  }

  @Override
  public String[] getTitles() {
    return UnixProcess.TITLES;
  }

  public static UnixProcesses convertFrom(String string) {
    String[] values = string.split("; ");
    ArrayList<ProcessInfo> processes = new ArrayList<>();
    for (String process : values) {
      processes.add(UnixProcess.convertFrom(process));
    }
    return new UnixProcesses(processes);
  }

}
