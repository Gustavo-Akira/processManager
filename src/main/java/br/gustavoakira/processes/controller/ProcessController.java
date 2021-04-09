package br.gustavoakira.processes.controller;

import java.io.IOException;

import javafx.event.ActionEvent;

public interface ProcessController{
	void excludeByPID(ActionEvent event);
	void excludeByName(ActionEvent event);
	void listProcesses() throws IOException;
}
