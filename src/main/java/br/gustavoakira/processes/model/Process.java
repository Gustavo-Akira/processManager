package br.gustavoakira.processes.model;

public class Process {
	@Override
	public String toString() {
		return "Process [pid=" + pid + ", name=" + name + "]";
	}
	private String pid;
	private String name;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
