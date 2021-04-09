package br.gustavoakira.processes.controller;

import java.io.IOException;

import javafx.event.ActionEvent;

public interface ProcessController{
	void excludeByPID(String Pid) throws IOException;
	void excludeByName(String Name) throws IOException;
	void listProcesses() throws IOException;
}
