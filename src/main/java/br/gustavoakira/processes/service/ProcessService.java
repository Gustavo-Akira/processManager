package br.gustavoakira.processes.service;

import java.io.IOException;
import java.util.List;
import br.gustavoakira.processes.model.Process;

public interface ProcessService {
	void killProcessByPID(String PID) throws IOException;
	void killProcessByName(String name)throws IOException;
	List<Process> getAllProccess()throws IOException;
}
