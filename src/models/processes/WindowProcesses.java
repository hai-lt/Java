package models.processes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WindowProcesses extends ProcessesManagement {
  public static final int MAX_ELEMENTS = 6;
  private final String GET_LIST_PROCESSES_COMMAND = "tasklist ";

  public WindowProcesses(ArrayList<ProcessInfo> processes) {
    super(processes);
  }

  public WindowProcesses() {
    super();
  }

  public String getListProcessesCommand() {
    return GET_LIST_PROCESSES_COMMAND;
  }

  @Override
  public ArrayList<ProcessInfo> loadProcesses() {
    try {
      Process p = Runtime.getRuntime().exec(getListProcessesCommand());
      BufferedReader input = new BufferedReader(new InputStreamReader(
          p.getInputStream()));
      String line;
      processes.clear();
      // Remove 4 first lines which is the titles of the processes in
      // Window OS
      input.readLine();
      input.readLine();
      input.readLine();
      input.readLine();
      while ((line = input.readLine()) != null) {
        line = breeze(line);
        if (line.split(" ").length > WindowProcesses.MAX_ELEMENTS) {
          continue;
        }
        processes.add(new WindowProcess(line));
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
    return WindowProcess.TITLES;
  }

  public static WindowProcesses convertFrom(String string) {
    ArrayList<ProcessInfo> processes = new ArrayList<>();
    for (String process : string.split(";")) {
      processes.add(new WindowProcess(process.trim()));
    }
    return new WindowProcesses(processes);
  }
}
