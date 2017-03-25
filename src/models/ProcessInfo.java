package models;

public class ProcessInfo {
	private String user, status, name;
	private long id;
	private float cpu, mem;

	public ProcessInfo(String user, String status, String name, long id, float cpu, float mem) {
		this.user = user;
		this.status = status;
		this.name = name;
		this.id = id;
		this.cpu = cpu;
		this.mem = mem;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getCpu() {
		return cpu;
	}

	public void setCpu(float cpu) {
		this.cpu = cpu;
	}

	public float getMem() {
		return mem;
	}

	public void setMem(float mem) {
		this.mem = mem;
	}
}
