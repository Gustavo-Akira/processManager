package br.gustavoakira.processes.test.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import br.gustavoakira.processes.model.Process;

import org.junit.jupiter.api.Test;

import br.gustavoakira.processes.service.impl.ProcessServiceImpl;

public class ProcessServiceImplTest {
	
	private ProcessServiceImpl service = new ProcessServiceImpl();

	@Test
	void testKillProcessByPID() throws IOException {
		service.killProcessByPID("a");
	}

	@Test
	void testKillProcessByName() throws IOException {
		service.killProcessByName("");
	}

	@Test
	void testGetAllProccess() throws IOException{
		List<Process> result = service.getAllProccess();
		assertNotEquals(null, result);
	}

}
