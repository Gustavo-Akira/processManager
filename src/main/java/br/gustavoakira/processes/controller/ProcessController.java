package br.gustavoakira.processes.controller;

import javafx.event.ActionEvent;

public interface ProcessController{
	void excludeByPID(ActionEvent event);
	void excludeByName(ActionEvent event);
	void listProcesses();
}
