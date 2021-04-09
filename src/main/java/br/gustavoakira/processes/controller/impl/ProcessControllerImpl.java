package br.gustavoakira.processes.controller.impl;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.gustavoakira.processes.controller.ProcessController;
import br.gustavoakira.processes.model.Process;
import br.gustavoakira.processes.service.ProcessService;
import br.gustavoakira.processes.service.impl.ProcessServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProcessControllerImpl implements ProcessController, Initializable {

	private ProcessService service = new ProcessServiceImpl();
	
	@FXML
	private TableView<Process> table;
	
	@FXML
	private TableColumn<Process, String> nameCol;
	
	@FXML
	private TableColumn<Process, String> pidCol;
	
	@FXML
	private TableColumn<Process, Void> actionCol; 
	
	@Override
	public void excludeByPID(ActionEvent event) {

	}

	@Override
	public void excludeByName(ActionEvent event) {

	}

	@Override
	public void listProcesses() throws IOException {
		List<Process> processes = service.getAllProccess();
		ObservableList<Process> process = FXCollections.observableArrayList();
		processes.forEach(x->{
			process.add(x);
		});
		table.setItems(process);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setCellsFactory();
		initializeButtons();
		try {
			listProcesses();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initializeButtons() {
	}
	
	private void setCellsFactory() {
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		pidCol.setCellValueFactory(new PropertyValueFactory<>("pid"));
	}

}
