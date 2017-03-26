package models;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class UnixProcesses extends ProcessesManagement {
	private static String LIST_PROCESSES_COMMAND = "ps aux --sort -rss";

	public UnixProcesses(ArrayList<ProcessInfo> processes) {
		super(processes);
	}

	@Override
	public ArrayList<ProcessInfo> loadProcesses() {
		try {
			Process p = Runtime.getRuntime().exec(LIST_PROCESSES_COMMAND);
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			processes.clear();
			while ((line = input.readLine()) != null) {
				processes.add(new UnixProcess(line));
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

}
