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
	private ArrayList<ProcessInfo> processes;

	public abstract ProcessInfo killProcess(long pid);

	public ProcessInfo find(long pid) {
		for (ProcessInfo process : processes) {
			if (process.getId() == pid) {
				return process;
			}
		}
		return null;
	}

	public ProcessesManagement(ArrayList<ProcessInfo> processes) {
		this.processes = processes;
	}

	/**
	 * 
	 * @return {@link ArrayList} of {@link ProcessInfo} object in descrease order
	 */
	public ArrayList<ProcessInfo> orderByPid() {
		return orderByPid(DESC);
	}

	/**
	 * Sort processes by PID
	 *
	 * @param order
	 *            {@link ProcessesManagement}.<b><i>DESC</i></b> in order to
	 *            sort by less than, ortherwise you can use
	 *            {@link ProcessesManagement}.<b><i>ASC</i></b>
	 * @return {@link ArrayList} of {@link ProcessInfo} object
	 */
	public ArrayList<ProcessInfo> orderByPid(int order) {
		processes.sort(new Comparator<ProcessInfo>() {

			@Override
			public int compare(ProcessInfo p1, ProcessInfo p2) {
				return p1.getId() > p2.getId() ? order * DESC : order * ASC;
			}
		});
		return processes;
	}

	public ArrayList<ProcessInfo> getProcesses() {
		return processes;
	}

	public void setProcesses(ArrayList<ProcessInfo> processes) {
		this.processes = processes;
	}

}
