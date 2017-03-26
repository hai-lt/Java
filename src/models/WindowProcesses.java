package models;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WindowProcesses extends ProcessesManagement {
	private final String GET_LIST_PROCESSES_COMMAND = "tasklist ";

	public WindowProcesses(ArrayList<ProcessInfo> processes) {
		super(processes);
	}

	public String getListProcessesCommand() {
		return GET_LIST_PROCESSES_COMMAND;
	}

	@Override
	public ArrayList<ProcessInfo> loadProcesses() {
		try {
			Process p = Runtime.getRuntime().exec(getListProcessesCommand());
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			processes.clear();
			while ((line = input.readLine()) != null) {
				processes.add(WindowProcess.create(line));
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
}
