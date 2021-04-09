package br.gustavoakira.processes.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import br.gustavoakira.processes.model.Process;

import br.gustavoakira.processes.service.ProcessService;

public class ProcessServiceImpl implements ProcessService {

	private Runtime runtime = Runtime.getRuntime();
	private static final String OS = System.getProperty("os.name");

	@Override
	public void killProcessByPID(String PID) throws IOException {
		String command = "";
		if (OS.contains("Windows")) {
			command = "taskkill /F /PID " + PID;
		} else {
			command = "kill -9 " + PID;
		}
		runtime.exec(command);
	}

	@Override
	public void killProcessByName(String name) throws IOException {
		String command = "";
		if (OS.contains("Windows")) {
			command = "taskkill /IM " + name + " /F";
		} else {
			command = "kill -9 " + name;
		}
		runtime.exec(command);
	}

	@Override
	public List<Process> getAllProccess() throws IOException {
		String command = "";
		if (OS.contains("Windows")) {
			command = "tasklist /fo csv /nh";
		} else if (OS.contains("Linux")) {
			command = "ps --no-headers -e -o %p, -o %c";
		} else {
			command = "ps aux --no-headers -e -o %p, -o %c";
		}
		List<Process> process = new ArrayList<Process>();
		String x = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(runtime.exec(command).getInputStream()));
		while ((x = br.readLine()) != null) {
			if (x.contains("K")) {
				process.add(buildProcess(x));
			}
		}
		br.close();
		return process;
	}

	private Process buildProcess(String line) {
		String regex = ",";
		Process process = new Process();
		String[] array = line.split(regex);
		process.setName(array[0].replaceAll("^\"+|\"+$", ""));
		process.setPid(array[1].replaceAll("^\"+|\"+$", ""));
		return process;
	}
}
