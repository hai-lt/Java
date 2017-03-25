package models;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 
 * @author tanhai
 *         <li>Manage the processes of an OS
 */
public abstract class ProcessesManagement {
	public static final int DESC = -1;
	public static final int ASC = 1;
	private ArrayList<Process> processes;

	public abstract void ProcessesManagement();

	public abstract Process killProcess(long pid);

	public Process find(long pid) {
		for (Process process : processes) {
			if (process.getId() == pid) {
				return process;
			}
		}
		return null;
	}

	public ProcessesManagement(ArrayList<Process> processes) {
		super();
		this.processes = processes;
	}

	/**
	 * 
	 * @return {@link ArrayList} of {@link Process} object in descrease order
	 */
	public ArrayList<Process> orderByPid() {
		return orderByPid(DESC);
	}

	/**
	 * Sort processes by PID
	 *
	 * @param order
	 *            {@link ProcessesManagement}.<b><i>DESC</i></b> in order to
	 *            sort by less than, ortherwise you can use
	 *            {@link ProcessesManagement}.<b><i>ASC</i></b>
	 * @return {@link ArrayList} of {@link Process} object
	 */
	public ArrayList<Process> orderByPid(int order) {
		processes.sort(new Comparator<Process>() {

			@Override
			public int compare(Process p1, Process p2) {
				return p1.getId() > p2.getId() ? order * DESC : order * ASC;
			}
		});
		return processes;
	}

	public ArrayList<Process> getProcesses() {
		return processes;
	}

	public void setProcesses(ArrayList<Process> processes) {
		this.processes = processes;
	}

}
