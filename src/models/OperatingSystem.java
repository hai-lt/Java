package models;

public class OperatingSystem {
	private ProcessesManagement processes;
	private RootFilesManagement rootFiles;

	public ProcessesManagement getProcesses() {
		return processes;
	}

	public void setProcesses(ProcessesManagement processes) {
		this.processes = processes;
	}

	public RootFilesManagement getRootFiles() {
		return rootFiles;
	}

	public void setRootFiles(RootFilesManagement rootFiles) {
		this.rootFiles = rootFiles;
	}

}
