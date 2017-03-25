package models;

import java.io.File;
import java.util.ArrayList;

public class RootFilesManagement {
	private File[] roots;
	private ArrayList<Memory> memories;

	public RootFilesManagement(File[] roots) {
		this.roots = roots;
		memories = new ArrayList<>();
		for (File root : roots) {
			memories.add(new Memory(root));
		}
	}
	
	public RootFilesManagement() {
		new RootFilesManagement(File.listRoots());
	}

	public File[] getRoots() {
		return roots;
	}

	public void setRoots(File[] roots) {
		this.roots = roots;
	}

	public ArrayList<Memory> getMemories() {
		return memories;
	}

	public void setMemories(ArrayList<Memory> memories) {
		this.memories = memories;
	}

}
